package net.bram91.modeldumper;

import java.io.File;
import net.runelite.api.Model;
import net.runelite.api.Renderable;
import net.runelite.client.RuneLite;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class OBJExporter
{

    private final static String PATH = RuneLite.RUNELITE_DIR + "//models//";

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

    public static void export(Model m, String path, String name, boolean seq)
    {
        try
        {
			File folder = new File(PATH);

			if (!folder.exists())
			{
				folder.mkdir();
			}
            exportModel(m, path, name, seq);
        }
		catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    private static void exportModel(Model m, String path, String name, boolean seq) throws FileNotFoundException
    {
        if (m == null)
            return;

        String mtlName = name;
        if(seq)
        {
            mtlName = name.split("-")[0]+"-"+name.split("-")[1];
        }

        // Open writers
        PrintWriter obj = new PrintWriter(path+ name + ".obj");
        PrintWriter mtl = new PrintWriter(path + mtlName + ".mtl");
        obj.println("# Made by RuneLite Model-Dumper Plugin");
        obj.println("mtllib " + mtlName + ".mtl");
        obj.println("o " + name);

        // Write vertices
        for (int vi=0; vi < m.getVerticesCount(); ++vi)
        {
            // Y and Z axes are flipped
            int vx = m.getVerticesX()[vi];
            int vy = -m.getVerticesY()[vi];
            int vz = -m.getVerticesZ()[vi];
            obj.println("v " + vx + " " + vy + " " + vz);
        }

        // Write faces
        List<Color> knownColors = new ArrayList<>();
        int prevMtlIndex = -1;
        for (int fi=0; fi < m.getFaceCount(); ++fi)
        {
            // determine face color (textured or colored?)
            Color c;
            int textureId = -1;
            if (m.getFaceTextures() != null)
                textureId = m.getFaceTextures()[fi];
            if (textureId != -1)
            {
                // get average color of texture
                c = TextureColor.getColor(textureId);
            }
            else
            {
                // get average color of vertices
                int c1 = m.getFaceColors1()[fi];
                int c2 = m.getFaceColors2()[fi];
                int c3 = m.getFaceColors3()[fi];
                c = JagexColor.HSLtoRGBAvg(c1, c2, c3);
            }

            // see if our color already has a mtl
            int ci = knownColors.indexOf(c);
            if (ci == -1)
            {
                // add to known colors
                ci = knownColors.size();
                knownColors.add(c);

                // int to float color conversion
                double r = (double) c.getRed() / 255.0d;
                double g = (double) c.getGreen() / 255.0d;
                double b = (double) c.getBlue() / 255.0d;

                // write mtl
                mtl.println("newmtl c" + ci);
                mtl.printf("Kd %.4f %.4f %.4f\n", r, g, b);
            }

            // only write usemtl if the mtl has changed
            if (prevMtlIndex != ci)
            {
                obj.println("usemtl c" + ci);
            }

            // OBJ vertices are indexed by 1
            int vi1 = m.getFaceIndices1()[fi] + 1;
            int vi2 = m.getFaceIndices2()[fi] + 1;
            int vi3 = m.getFaceIndices3()[fi] + 1;
            obj.println("f " + vi1 + " " + vi2 + " " + vi3);

            prevMtlIndex = ci;
        }

        // flush output buffers
        obj.flush();
        mtl.flush();
        obj.close();
        mtl.close();
    }

}
