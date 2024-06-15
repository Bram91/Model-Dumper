package net.bram91.modeldumper;

import net.runelite.api.Model;

import java.awt.Color;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static net.bram91.modeldumper.JagexColor.createPalette;

public class PLYExporter
{

    private final static int[] colorPalette = createPalette(JagexColor.BRIGHTNESS_MIN);

    public static void export(Model m, String name) throws IOException
    {
        List<Vertex> vertices = new ArrayList<>();
        for (int fi=0; fi < m.getFaceCount(); fi++)
        {
            // determine vertex colors (textured or colored?)
            Color vc1;
            Color vc2;
            Color vc3;
            int textureId = -1;
            if (m.getFaceTextures() != null)
                textureId = m.getFaceTextures()[fi];
            if (textureId != -1)
            {
                // get average color of texture
                vc1 = TextureColor.getColor(textureId);
                vc2 = vc1;
                vc3 = vc1;
            }
            else
            {
                if (m.getFaceColors3()[fi] == -1)
                {
                    // face should be shaded flat
                    int colorIndex = m.getFaceColors1()[fi];
                    int rgbColor = colorPalette[colorIndex];
                    vc1 = vc2 = vc3 = new Color(rgbColor);
                } else {
                    // get color for each vertex
                    vc1 = new Color(JagexColor.HSLtoRGB((short) m.getFaceColors1()[fi], JagexColor.BRIGHTNESS_MIN));
                    vc2 = new Color(JagexColor.HSLtoRGB((short) m.getFaceColors2()[fi], JagexColor.BRIGHTNESS_MIN));
                    vc3 = new Color(JagexColor.HSLtoRGB((short) m.getFaceColors3()[fi], JagexColor.BRIGHTNESS_MIN));
                }
            }

            int vi1 = m.getFaceIndices1()[fi];
            int vi2 = m.getFaceIndices2()[fi];
            int vi3 = m.getFaceIndices3()[fi];

            int vx1 = (int) m.getVerticesX()[vi1];
            int vx2 = (int) m.getVerticesX()[vi2];
            int vx3 = (int) m.getVerticesX()[vi3];
            int vy1 = (int) -m.getVerticesY()[vi1];
            int vy2 = (int) -m.getVerticesY()[vi2];
            int vy3 = (int) -m.getVerticesY()[vi3];
            int vz1 = (int) m.getVerticesZ()[vi1];
            int vz2 = (int) m.getVerticesZ()[vi2];
            int vz3 = (int) m.getVerticesZ()[vi3];

            vertices.add(new Vertex(vx1, vy1, vz1, vc1.getRed(), vc1.getGreen(), vc1.getBlue()));
            vertices.add(new Vertex(vx2, vy2, vz2, vc2.getRed(), vc2.getGreen(), vc2.getBlue()));
            vertices.add(new Vertex(vx3, vy3, vz3, vc3.getRed(), vc3.getGreen(), vc3.getBlue()));
        }

        OutputStream ply = new FileOutputStream(name + ".ply");
        PrintWriter plyHeader = new PrintWriter(ply);
        plyHeader.println("ply");
        plyHeader.println("format binary_little_endian 1.0");
        plyHeader.println("element vertex " + vertices.size());
        plyHeader.println("property int16 x");
        plyHeader.println("property int16 y");
        plyHeader.println("property int16 z");
        plyHeader.println("property uint8 red");
        plyHeader.println("property uint8 green");
        plyHeader.println("property uint8 blue");
        plyHeader.println("element face " + m.getFaceCount());
        plyHeader.println("property list uint8 int16 vertex_indices");
        plyHeader.println("end_header");
        plyHeader.flush();

        ByteArrayOutputStream w = new ByteArrayOutputStream();

        for (Vertex v: vertices)
        {
            // Y and Z axes are flipped
            w.write(le(v.x));
            w.write(le(v.z));
            w.write(le(v.y));
            w.write((byte) v.r);
            w.write((byte) v.g);
            w.write((byte) v.b);
        }

        for (int i=0; i < m.getFaceCount(); ++i)
        {
            int vi = i*3;
            w.write((byte) 3);
            w.write(le(vi));
            w.write(le(vi+1));
            w.write(le(vi+2));
        }

        w.flush();
        ply.write(w.toByteArray());
        ply.flush();
        ply.close();

    }

    // int to little endian byte array
    private static byte[] le(int n)
    {
        byte[] b = new byte[2];
        b[0] = (byte) n;
        b[1] = (byte) (n >> 8);
        return b;
    }

    private static class Vertex
    {
        public int x, y, z;
        public int r, g, b;

        public Vertex(int x, int y, int z, int r, int g, int b)
        {
            this.x = x;
            this.y = y;
            this.z = z;
            this.r = r;
            this.g = g;
            this.b = b;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Vertex vertex = (Vertex) o;
            return x == vertex.x && y == vertex.y && z == vertex.z && r == vertex.r && g == vertex.g && b == vertex.b;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y, z, r, g, b);
        }
    }

}
