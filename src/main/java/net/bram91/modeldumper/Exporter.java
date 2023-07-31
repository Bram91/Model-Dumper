package net.bram91.modeldumper;

import net.runelite.api.Model;
import net.runelite.api.Renderable;
import net.runelite.client.RuneLite;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Exporter
{

    private final static String PATH = RuneLite.RUNELITE_DIR + "//models//";
    private static final DateFormat TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");

    public static void export(Renderable r, String name)
    {
        Model m;
        if (r instanceof Model)
        {
            m = (Model) r;
        }
        else
        {
            m = r.getModel();
        }

        export(m, name);
    }

    public static void export(Model m, String name)
    {
        name = PATH + name + " " + TIME_FORMAT.format(new Date());

        switch (ModelDumperPlugin.getConfig().exportFormat())
        {
            case OBJ:
                    OBJExporter.export(m, name);
                break;
            case PLY:
                try
                {
                    PLYExporter.export(m, name);
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
                break;
            case STL:
                try
                {
                    STLExporter.export(m, name);
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            default:
                break;
        }
    }

    public static void exportSequence(Model model, int npcId, int animationId, int frame, Date dateTime)
    {
        String path = PATH + "sequences//" + TIME_FORMAT.format(dateTime) + "//";
        File folder = new File(path);
        if (!folder.exists())
        {
            folder.mkdirs();
        }
        if(npcId < 0)
        {
            OBJExporter.export(model, path + "player-" + animationId + "-" + frame);
        }
        else
        {
            OBJExporter.export(model, path + npcId + "-" + animationId + "-" + frame);
        }
    }
}
