/*
 * Copyright (c) 2020, Bram91
 * Copyright (c) 2020, Unmoon <https://github.com/Unmoon>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package net.bram91.modeldumper;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Table;
import com.google.inject.Provides;

import java.awt.Shape;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import javax.inject.Inject;

import lombok.Getter;
import net.bram91.modeldumper.types.ModelExporterData;
import net.runelite.client.ui.ClientToolbar;
import net.runelite.api.*;
import net.runelite.api.coords.WorldPoint;
import net.runelite.api.events.*;
import net.runelite.api.widgets.WidgetInfo;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.events.ConfigChanged;
import net.runelite.client.menus.MenuManager;
import net.runelite.client.menus.WidgetMenuOption;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.NavigationButton;
import net.runelite.client.util.ImageUtil;
import net.runelite.client.util.Text;

@PluginDescriptor(
	name = "Model Exporter",
	description = "Allows exporting models by right clicking them.",
	tags = {"Model", "Dumper","Exporter","3d","obj"}
)
public class ModelDumperPlugin extends Plugin
{
	@Getter
	private static ModelDumperPlugin instance;
	private static final String EXPORT_MODEL = "Export Model";
	private static final String EXPORT_SEQUENCE = "Export Animation Sequence";
	private static final String MENU_TARGET = "Player";
	private static final WidgetMenuOption FIXED_EQUIPMENT_TAB_EXPORT = new WidgetMenuOption(EXPORT_MODEL,
		MENU_TARGET, WidgetInfo.FIXED_VIEWPORT_EQUIPMENT_TAB);
	private static final WidgetMenuOption RESIZABLE_EQUIPMENT_TAB_EXPORT = new WidgetMenuOption(EXPORT_MODEL,
		MENU_TARGET, WidgetInfo.RESIZABLE_VIEWPORT_EQUIPMENT_TAB);
	private static final WidgetMenuOption RESIZABLE_VIEWPORT_BOTTOM_LINE_INVENTORY_TAB_EXPORT = new WidgetMenuOption(EXPORT_MODEL,
		MENU_TARGET,WidgetInfo.RESIZABLE_VIEWPORT_BOTTOM_LINE_INVENTORY_TAB);

	private static final WidgetMenuOption FIXED_EQUIPMENT_TAB_EXPORT_SEQ = new WidgetMenuOption(EXPORT_SEQUENCE,
			MENU_TARGET, WidgetInfo.FIXED_VIEWPORT_EQUIPMENT_TAB);
	private static final WidgetMenuOption RESIZABLE_EQUIPMENT_TAB_EXPORT_SEQ = new WidgetMenuOption(EXPORT_SEQUENCE,
			MENU_TARGET, WidgetInfo.RESIZABLE_VIEWPORT_EQUIPMENT_TAB);
	private static final WidgetMenuOption RESIZABLE_VIEWPORT_BOTTOM_LINE_INVENTORY_TAB_EXPORT_SEQ = new WidgetMenuOption(EXPORT_SEQUENCE,
			MENU_TARGET,WidgetInfo.RESIZABLE_VIEWPORT_BOTTOM_LINE_INVENTORY_TAB);
	private final ImmutableList<String> set = ImmutableList.of(
		"Trade with", "Attack", "Talk-to", "Examine"
	);

	@Inject
	private ModelExporterData modelData;

	@Inject
	private ClientToolbar clientToolbar;

	private NavigationButton navButton;

	@Inject
	private Client client;

	@Inject
	private ClientThread clientThread;

	@Inject
	private MenuManager menuManager;

	@Inject
	private ModelDumperPluginConfig config;

	@Provides
	ModelDumperPluginConfig getConfig(ConfigManager configManager)
	{
		return configManager.getConfig(ModelDumperPluginConfig.class);
	}

	private final Table<WorldPoint, Integer, GroundItem> groundItems = HashBasedTable.create();

	@Override
	protected void startUp() throws Exception
	{
		// We never want to start the plugin with the transmog enabled, since this is more of a config "button"
		// So we make sure its always set to false on startup
		if(config.transmogEnabled()) {
			config.setTransmog(false);
		}
		menuManager.addManagedCustomMenu(FIXED_EQUIPMENT_TAB_EXPORT,this::exportLocalPlayerModel);
		menuManager.addManagedCustomMenu(RESIZABLE_EQUIPMENT_TAB_EXPORT,this::exportLocalPlayerModel);
		menuManager.addManagedCustomMenu(RESIZABLE_VIEWPORT_BOTTOM_LINE_INVENTORY_TAB_EXPORT,this::exportLocalPlayerModel);

		menuManager.addManagedCustomMenu(FIXED_EQUIPMENT_TAB_EXPORT_SEQ,this::exportLocalPlayerSequence);
		menuManager.addManagedCustomMenu(RESIZABLE_EQUIPMENT_TAB_EXPORT_SEQ,this::exportLocalPlayerSequence);
		menuManager.addManagedCustomMenu(RESIZABLE_VIEWPORT_BOTTOM_LINE_INVENTORY_TAB_EXPORT_SEQ,this::exportLocalPlayerSequence);

		ModelDumperPlugin.instance = this;

		if(config.sidepanelEnabled())
		{
			addSidepanel();
		}
	}
	public void addSidepanel()
	{
		ModelPanel panel = injector.getInstance(ModelPanel.class);
		panel.init(modelData);
		final BufferedImage icon = ImageUtil.loadImageResource(getClass(), "icon.png");
		navButton = NavigationButton.builder()
				.tooltip("Model Exporter")
				.icon(icon)
				.priority(10)
				.panel(panel)
				.build();
		clientToolbar.addNavigation(navButton);
	}
	public void removeSidepanel()
	{
		clientToolbar.removeNavigation(navButton);
	}
	@Override
	protected void shutDown()
	{
		menuManager.removeManagedCustomMenu(FIXED_EQUIPMENT_TAB_EXPORT);
		menuManager.removeManagedCustomMenu(RESIZABLE_EQUIPMENT_TAB_EXPORT);
		menuManager.removeManagedCustomMenu(RESIZABLE_VIEWPORT_BOTTOM_LINE_INVENTORY_TAB_EXPORT);

		menuManager.removeManagedCustomMenu(FIXED_EQUIPMENT_TAB_EXPORT_SEQ);
		menuManager.removeManagedCustomMenu(RESIZABLE_EQUIPMENT_TAB_EXPORT_SEQ);
		menuManager.removeManagedCustomMenu(RESIZABLE_VIEWPORT_BOTTOM_LINE_INVENTORY_TAB_EXPORT_SEQ);
		if(config.sidepanelEnabled())
		{
			removeSidepanel();
		}
		groundItems.clear();
	}

	@Subscribe
	public void onMenuOpened(MenuOpened event)
	{
		MenuEntry[] menuEntries = event.getMenuEntries();
		for (int i = 0; i < set.size(); i++)
		{
			boolean addMenuEntry = false;
			MenuEntry target = null;
			for (MenuEntry menuEntry : menuEntries)
			{
				if (menuEntry.getOption().toLowerCase().equals("drop") || menuEntry.getOption().toLowerCase().equals("destroy"))
				{
					return;
				}
				else if (menuEntry.getOption().equals(set.get(i)))
				{
					addMenuEntry = true;
					target = menuEntry;
				}
			}

			if (client.isKeyPressed(KeyCode.KC_SHIFT) && addMenuEntry)
			{
				String entityName = target.getTarget();

				if(target.getPlayer() != null)
				{
					client.createMenuEntry(0)
						.setOption(EXPORT_MODEL)
						.setTarget(entityName)
						.setIdentifier(target.getIdentifier())
						.onClick(this::exportPlayerModel);
				}
				else if(target.getType().equals(MenuAction.EXAMINE_ITEM_GROUND) || target.getType().equals(MenuAction.EXAMINE_OBJECT))
				{
					client.createMenuEntry(0)
						.setOption(EXPORT_MODEL)
						.setTarget(entityName)
						.setIdentifier(target.getIdentifier())
						.onClick(this::exportObjectModel);
				}
				else if(target.getNpc()!=null && !target.getNpc().getComposition().isFollower())
				{
					client.createMenuEntry(0)
						.setOption(EXPORT_MODEL)
						.setTarget(entityName)
						.setIdentifier(target.getIdentifier())
						.onClick(this::exportNpcModel);
				}
				break;

			}
		}
	}
	@Subscribe
	public void onBeforeRender(BeforeRender beforeRender)
	{
		if(config.transmogEnabled() && config.frame() != 0)
		{
			Player player = client.getLocalPlayer();
			Animation animation = client.loadAnimation(config.animationId());
			if(player != null && animation != null)
			{
				if(config.frame() < animation.getNumFrames())
				{
					player.setAnimationFrame(config.frame());
					player.setPoseAnimationFrame(config.frame());
				}
				else
				{
					player.setAnimationFrame(animation.getNumFrames() - 1);
					player.setPoseAnimationFrame(animation.getNumFrames() - 1);
				}
			}
		}
	}

	@Subscribe
	public void onConfigChanged(ConfigChanged configChanged)
	{
		if(!configChanged.getGroup().equals("modeldumper"))
		{
			return;
		}
		if(configChanged.getKey().equals("sidepanelEnabled"))
		{
			if (Boolean.parseBoolean((configChanged.getNewValue()))) {
				addSidepanel();
			} else {
				removeSidepanel();
			}
			return;
		}
		Player player = client.getLocalPlayer();
		if(player != null)
		{
			switch (configChanged.getKey())
			{
				case "npcId":
					if(config.transmogEnabled())
					{
						player.getPlayerComposition().setTransformedNpcId(Integer.parseInt(configChanged.getNewValue()));
					}
					break;
				case "animationId":
					if(config.transmogEnabled())
					{
						int newValue = Integer.parseInt(configChanged.getNewValue());
						if (newValue == 0)
						{
							player.setIdlePoseAnimation(-1);
						} else
						{
							player.setIdlePoseAnimation(newValue);
						}
					}
					break;
				case "transmogEnabled":
					if(Boolean.parseBoolean(configChanged.getNewValue()))
					{
						player.getPlayerComposition().setTransformedNpcId(config.npcId());
						player.setIdlePoseAnimation(config.animationId());
					} else
					{
						player.getPlayerComposition().setTransformedNpcId(-1);
						player.setIdlePoseAnimation(-1);
						player.setPoseAnimation(-1);
					}
					break;
			}
		}
	}

	public void exportLocalPlayerModel(MenuEntry entry)
	{
		Player localPlayer = client.getLocalPlayer();
		if (config.forceRestPose())
		{
			localPlayer.setAnimation(2566);
			localPlayer.setAnimationFrame(0);
		}
		Exporter.export(localPlayer.getModel(), "Player " + client.getLocalPlayer().getName(), false);
	}

	public void exportLocalPlayerSequence(MenuEntry entry) {
		Player localPlayer = client.getLocalPlayer();

		Date dateTime = new Date();
		Animation animation = client.loadAnimation(config.animationId());
		if(animation != null)
		{
			for (int i = 0; i < animation.getNumFrames(); i++)
			{
				if (client.loadAnimation(config.animationId()) != null)
				{
					localPlayer.setAnimation(config.animationId());
					localPlayer.setAnimationFrame(i);
					Exporter.exportSequence(localPlayer.getModel(), config.npcId(), config.animationId(), i, dateTime);
				}
			}
		}
		localPlayer.setAnimationFrame(0);
	}

	private void exportObjectModel(MenuEntry entry)
	{
		int id = entry.getIdentifier();
		String menuTarget = entry.getTarget();
		Scene scene = client.getScene();
		Tile[][][] tiles = scene.getTiles();

		int z = client.getPlane();
		for (int x = 0; x < Constants.SCENE_SIZE; ++x)
		{
			for (int y = 0; y < Constants.SCENE_SIZE; ++y)
			{
				Tile tile = tiles[z][x][y];
				if (tile != null)
				{
					GameObject[] gameObjects = tile.getGameObjects();
					WallObject wallObject = tile.getWallObject();
					Collection<GroundItem> groundItemsOnTile = groundItems.row(tile.getWorldLocation()).values();
					DecorativeObject decoration = tile.getDecorativeObject();

					if (wallObject != null && wallObject.getId() == id)
					{

						Exporter.export(wallObject.getRenderable1(), "Object " + Text.removeFormattingTags(menuTarget));
						return;
					}
					else if (decoration != null && decoration.getId() == id)
					{
						Exporter.export(decoration.getRenderable(), "Object " + Text.removeFormattingTags(menuTarget));
						return;
					}
					else
					{
						for (GameObject gameObject : gameObjects) {
							if (gameObject != null && gameObject.getId() == id) {
								if (gameObject.getRenderable() != null) {
									Exporter.export(gameObject.getRenderable(), "Object " + Text.removeFormattingTags(menuTarget));
									return;
								}
							}
						}

						for (GroundItem groundItem : groundItemsOnTile) {
							if (groundItem.getId() == id) {
								Exporter.export(groundItem.getModel(), "Item " + Text.removeFormattingTags(menuTarget), false);
								return;
							}
						}
					}
				}
			}
		}
	}

	private void exportNpcModel(MenuEntry entry)
	{
		String menuTarget = entry.getTarget();
		int identifier = entry.getIdentifier();
		NPC npc = client.getCachedNPCs()[identifier];
		Exporter.export(npc.getModel(), "NPC " + Text.removeFormattingTags(menuTarget), false);
	}

	private void exportPetModel(MenuEntry entry)
	{
		String menuTarget = entry.getTarget();
		int identifier = entry.getIdentifier();
		NPC npc=null;
		for(NPC npC:client.getNpcs())
		{
			if(npC.getId() == identifier)
			{
				npc = npC;
			}
		}

		if(npc!=null)
		{
			Exporter.export(npc.getModel(), "Pet " + Text.removeFormattingTags(menuTarget), false);
		}
	}

	private void exportPlayerModel(MenuEntry entry)
	{
		String menuTarget = entry.getTarget();
		Pattern REMOVE_TAGS_SECONDARY = Pattern.compile("\\(.+?\\)");
		Matcher m = REMOVE_TAGS_SECONDARY.matcher(menuTarget);
		String trgt = m.replaceAll("");
		trgt = Text.sanitize(Text.removeFormattingTags(trgt.trim()));

		for (int i = 0; i < client.getPlayers().size(); i++)
		{
			if (client.getPlayers().get(i).getName().equals(trgt))
			{
				Exporter.export(client.getPlayers().get(i).getModel(), "Player " + trgt, false);
			}
		}
	}

	@Subscribe
	public void onGameStateChanged(final GameStateChanged event)
	{
		if (event.getGameState() == GameState.LOADING)
		{
			groundItems.clear();
		}
	}

	// borrowed from official Ground Items plugin
	@Subscribe
	public void onItemSpawned(ItemSpawned itemSpawned)
	{
		TileItem item = itemSpawned.getItem();
		Tile tile = itemSpawned.getTile();

		final GroundItem groundItem = GroundItem.builder()
			.id(item.getId())
			.item(item)
			.location(tile.getWorldLocation())
			.quantity(item.getQuantity())
			.build();

		GroundItem existing = groundItems.get(tile.getWorldLocation(), item.getId());
		if (existing != null)
		{
			existing.setQuantity(existing.getQuantity() + groundItem.getQuantity());
		}
		else
		{
			groundItems.put(tile.getWorldLocation(), item.getId(), groundItem);
		}
	}

	// borrowed from official Ground Items plugin
	@Subscribe
	public void onItemDespawned(ItemDespawned itemDespawned)
	{
		TileItem item = itemDespawned.getItem();
		Tile tile = itemDespawned.getTile();

		GroundItem groundItem = groundItems.get(tile.getWorldLocation(), item.getId());
		if (groundItem == null)
		{
			return;
		}

		if (groundItem.getQuantity() <= item.getQuantity())
		{
			groundItems.remove(tile.getWorldLocation(), item.getId());
		}
		else
		{
			groundItem.setQuantity(groundItem.getQuantity() - item.getQuantity());
		}
	}

	// borrowed from official Ground Items plugin
	@Subscribe
	public void onItemQuantityChanged(ItemQuantityChanged itemQuantityChanged)
	{
		TileItem item = itemQuantityChanged.getItem();
		Tile tile = itemQuantityChanged.getTile();
		int oldQuantity = itemQuantityChanged.getOldQuantity();
		int newQuantity = itemQuantityChanged.getNewQuantity();

		int diff = newQuantity - oldQuantity;
		GroundItem groundItem = groundItems.get(tile.getWorldLocation(), item.getId());
		if (groundItem != null)
		{
			groundItem.setQuantity(groundItem.getQuantity() + diff);
		}
	}

	//The following code is taken from the Pet Info plugin by Micro Tavor with permission
	@Subscribe
	public void onClientTick(ClientTick clientTick)
	{
		if (client.getGameState() == GameState.LOGGED_IN && !client.isMenuOpen() && client.isKeyPressed(KeyCode.KC_SHIFT))
		{
			addMenus();
		}
	}

	private void addMenus()
	{
		Point mouseCanvasPosition = client.getMouseCanvasPosition();

		List<NPC> petsUnderCursor = pets.stream().filter(p -> isClickable(p, mouseCanvasPosition)).collect(Collectors.toList());
		if (!petsUnderCursor.isEmpty())
		{
			for (NPC pet : petsUnderCursor)
			{
				client.createMenuEntry(0)
						.setOption(EXPORT_MODEL)
						.setTarget(pet.getName())
						.setIdentifier(pet.getId())
						.onClick(this::exportPetModel);
			}
		}
	}

	private boolean isClickable(NPC npc, Point mouseCanvasPosition)
	{
		Shape npcHull = npc.getConvexHull();

		if (npcHull != null)
		{
			return npcHull.contains(mouseCanvasPosition.getX(), mouseCanvasPosition.getY());
		}

		return false;
	}

	private final List<NPC> pets = new ArrayList<>();

	@Subscribe
	public void onNpcSpawned(NpcSpawned npcSpawned)
	{
		NPC npc = npcSpawned.getNpc();
		if(npc.getComposition().isFollower())
		{
			pets.add(npc);
		}
	}

	@Subscribe
	public void onNpcDespawned(NpcDespawned npcDespawned)
	{
		NPC npc = npcDespawned.getNpc();
		if(npc.getComposition().isFollower())
		{
			pets.remove(npc);
		}
	}

	protected static Client getClient()
	{
		return instance.client;
	}
	protected static ModelDumperPluginConfig getConfig() { return instance.config; }

}
