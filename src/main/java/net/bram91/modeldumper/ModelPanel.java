package net.bram91.modeldumper;

import com.google.inject.Inject;
import jdk.internal.org.jline.utils.Log;
import net.bram91.modeldumper.types.*;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.Player;
import net.runelite.client.ui.PluginPanel;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;

import net.runelite.client.callback.ClientThread;
import net.runelite.client.util.LinkBrowser;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.PatternSyntaxException;

import static net.runelite.client.RuneLite.RUNELITE_DIR;

public class ModelPanel extends PluginPanel {
    @Inject
    Client client;
    @Inject
    ClientThread clientThread;
    @Inject
    ModelDumperPluginConfig config;
    private JTable npcList;
    private JTable animationList;
    private final String npcFilterHint = "Search for npcs...";
    private final String animationFilterHint = "Search for animations...";
    private JTextField npcFilter = new JTextField(npcFilterHint);
    private JTextField animationFilter = new JTextField(animationFilterHint);
    public static final File MODEL_DIR = new File(RUNELITE_DIR, "models");

    public void init(ModelExporterData modelExporterData) {
        if (modelExporterData.getNpcData() == null || modelExporterData.getAnimationGroup() == null) {
            JTextArea errorDescription = new JTextArea("There was a problem loading npc information, you can still transmog in the plugin config.");
            errorDescription.setLineWrap(true);
            errorDescription.setWrapStyleWord(true);
            add(errorDescription);
            return;
        }
        Object[] animationGroups = modelExporterData.getAnimationGroup().toArray();
        JPanel settingsPanel = new JPanel();
        JCheckBox freePick = new JCheckBox("Free animation pick");
        freePick.setToolTipText("This will allow you to apply any animation to any npc.");
        freePick.addActionListener(e -> setSelection(animationGroups, freePick, modelExporterData));
        settingsPanel.add(freePick);

        final JPanel container = new JPanel();
        container.setLayout(new GridLayout(0, 1, 3, 3));

        for (Object animationGroup : animationGroups) {
            AnimationGroup animGroup = (AnimationGroup) animationGroup;
            animGroup.getAnimationGroup().forEach((anim) -> {
                String name = modelExporterData.getAnimationNames().get(anim.getId());
                anim.setName(name);
            });
        }
        modelExporterData.getNpcData().add(new NPCData(" Player", -1, 808, 637));
        Object[] npcData = Arrays.stream(modelExporterData.getNpcData().toArray()).sorted().toArray();
        npcData = Arrays.stream(npcData).filter(x -> !StringUtils.isBlank(x.toString()) && !x.toString().equals("null")).toArray();

        AnimationTableModel model = new AnimationTableModel(npcData, "Name", modelExporterData.getAnimationNames());
        npcList = new JTable(model);
        container.add(npcFilter);
        npcFilter.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (npcFilter.getText().equals(npcFilterHint)) {
                    npcFilter.setText("");
                }
            }
        });
        animationFilter.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (animationFilter.getText().equals(animationFilterHint)) {
                    animationFilter.setText("");
                }
            }
        });
        addSearchBox(npcList, npcFilter);
        KeyListener keyListener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {
                NPCData npcData = null;
                Animation animation = null;
                if (npcList.getSelectedRow() != -1) {
                    npcData = (NPCData) npcList.getValueAt(npcList.getSelectedRow(), 0);
                }
                if (animationList.getSelectedRow() != -1 && animationList.getValueAt(animationList.getSelectedRow(), 0) instanceof Animation) {
                    animation = (Animation) animationList.getValueAt(animationList.getSelectedRow(), 0);
                }
                if (npcData != null) {
                    if (animation != null) {
                        applyAnimation(npcData.getId(), animation.getId());
                    } else {
                        applyAnimation(npcData.getId(), npcData.getStandingAnimation());
                    }
                }
            }
        };
        ListSelectionListener npcListListener = e -> {
            if (npcList.getSelectedRow() == -1) {
                return;
            }
            setSelection(animationGroups, freePick, modelExporterData);
        };

        MouseListener animationListListener = new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int rowAtPoint = animationList.rowAtPoint(e.getPoint());
                animationList.setRowSelectionInterval(rowAtPoint, rowAtPoint);
                if (!(animationList.getValueAt(animationList.getSelectedRow(), 0) instanceof Animation)) {
                    return;
                }
                Animation animation = (Animation) animationList.getValueAt(animationList.getSelectedRow(), 0);
                if (e.getButton() == 3) {
                    String m = JOptionPane.showInputDialog("Animation name for " + animation.getId(), animation.getName());
                    if (m != null)
                        if (m.equals("")) {
                            ((Animation) animationList.getValueAt(animationList.getSelectedRow(), 0)).setName(null);
                        } else {
                            ((Animation) animationList.getValueAt(animationList.getSelectedRow(), 0)).setName(m);
                        }
                } else {
                    int npcId = ((NPCData) npcList.getValueAt(npcList.getSelectedRow(), 0)).getId();
                    applyAnimation(npcId, animation.getId());
                }
            }
        };

        container.add(freePick);
        JPanel animationPanel = new JPanel();
        animationPanel.setLayout(new GridLayout(0, 1, 3, 3));
        animationList = new JTable();
        animationPanel.add(new JScrollPane(animationList));
        JPanel exportPanel = new JPanel();
        exportPanel.setLayout(new GridLayout(2, 2, 3, 3));
        JButton configButton = new JButton("Set Config");
        configButton.addActionListener(e -> setConfig());
        JButton exportButton = new JButton("Export Sequence");
        exportButton.addActionListener(e -> exportSequence());
        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(e -> applyAnimation(-1, -1));
        JButton openButton = new JButton("Open Folder");
        JButton saveButton = new JButton("Save Animation Names");
        saveButton.addActionListener(e -> {
            try {
                new DataFetcher().saveAnimationNames(animationGroups);
            } catch (FileNotFoundException ex) {
                Log.warn("Failed to write animation names to file.");
            }
        });
        openButton.addActionListener(e -> LinkBrowser.open(MODEL_DIR.toString()));
        exportPanel.add(configButton);
        exportPanel.add(exportButton);
        exportPanel.add(resetButton);
        exportPanel.add(openButton);
        npcList.getSelectionModel().addListSelectionListener(npcListListener);
        npcList.addKeyListener(keyListener);
        animationList.addMouseListener(animationListListener);
        animationList.addKeyListener(keyListener);
        JScrollPane jScrollPane = new JScrollPane(npcList);
        Dimension d = npcList.getPreferredSize();
        d.height = 200;
        jScrollPane.setPreferredSize(d);
        add(new JLabel("NPC selection:"));
        add(jScrollPane, BorderLayout.CENTER);
        add(container);
        d = animationPanel.getPreferredSize();
        d.height = 200;
        animationPanel.setPreferredSize(d);
        add(animationPanel);
        add(animationFilter);
        add(saveButton);
        add(exportPanel);
    }

    private void setSelection(Object[] animationGroups, JCheckBox freePick, ModelExporterData modelExporterData) {
        if (npcList.getSelectedRow() == -1) {
            return;
        }
        NPCData data = (NPCData) npcList.getValueAt(npcList.getSelectedRow(), 0);
        List<Animation> matchingAnimations = new ArrayList<>();
        matchingAnimations.add(new Animation(-1));
        for (Object animationGroup : animationGroups) {
            AnimationGroup anim = (AnimationGroup) animationGroup;
            if (freePick.isSelected()) {
                matchingAnimations.addAll(((AnimationGroup) animationGroup).getAnimationGroup());
            } else if (anim.getAnimationGroup().contains(new Animation(data.getStandingAnimation())) || anim.getAnimationGroup().contains(new Animation(data.getWalkingAnimation()))) {
                matchingAnimations.addAll(anim.getAnimationGroup());
                //unless this is for player animations only 1 group can match, so we break after finding it.
                if (data.getId() != -1) {
                    break;
                }
            }
        }
        animationList.setModel(new AnimationTableModel(matchingAnimations.stream().sorted().toArray(), "Animations", modelExporterData.getAnimationNames()));
        addSearchBox(animationList, animationFilter);
        applyAnimation(data.getId(), data.getStandingAnimation());
    }

    private boolean setConfig() {
        if (npcList.getSelectedRow() == -1)
            return false;
        int npcId = ((NPCData) npcList.getValueAt(npcList.getSelectedRow(), 0)).getId();
        config.setFrame(0);
        config.setNpcId(npcId);
        config.setAnimationId(getAnimationID());
        return true;
    }

    public int getAnimationID() {
        int animation;
        if (animationList.getSelectedRow() != -1)
            animation = ((Animation) animationList.getValueAt(animationList.getSelectedRow(), 0)).getId();
        else
            animation = ((NPCData) npcList.getValueAt(npcList.getSelectedRow(), 0)).getStandingAnimation();
        return animation;
    }

    private void applyAnimation(int id, int animation) {
        clientThread.invoke(() -> {
            if (client.getGameState() == GameState.LOGGED_IN && client.getLocalPlayer() != null) {
                Player player = client.getLocalPlayer();
                player.getPlayerComposition().setTransformedNpcId(id);
                player.setIdlePoseAnimation(animation);
            }
        });
    }

    private void exportSequence() {
        if (!setConfig())
            return;
        int animation = getAnimationID();
        clientThread.invoke(() -> {
            if (client.getGameState() == GameState.LOGGED_IN && client.getLocalPlayer() != null) {
                if (animation == -1) {
                    client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "Exported a normal model since no animation was selected.", "");
                    ModelDumperPlugin.getInstance().exportLocalPlayerModel(null);
                } else {
                    client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "Exported animation sequence", "");
                    ModelDumperPlugin.getInstance().exportLocalPlayerSequence(null);
                }
            }
        });
    }

    public void addSearchBox(JTable jTable, JTextField filter) {
        TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(jTable.getModel());
        jTable.setRowSorter(rowSorter);
        filter.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = filter.getText();

                if (text.trim().isEmpty()) {
                    rowSorter.setRowFilter(null);
                } else {
                    try {
                        rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                    } catch (PatternSyntaxException ex) {
                        rowSorter.setRowFilter(null);
                    }
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = filter.getText();

                if (text.trim().isEmpty()) {
                    rowSorter.setRowFilter(null);
                } else {
                    try {
                        rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                    } catch (PatternSyntaxException ex) {
                        rowSorter.setRowFilter(null);
                    }

                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {}
        });
    }
}
