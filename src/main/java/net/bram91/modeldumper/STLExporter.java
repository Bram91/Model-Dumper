package net.bram91.modeldumper;

import net.runelite.api.Model;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.ByteArrayOutputStream;

public class STLExporter {

    public static void export(Model m, String name) throws IOException {
        OutputStream os = new FileOutputStream(name + ".stl");
        PrintWriter stlHeader = new PrintWriter(os);
        // 80 byte header
        stlHeader.print("UNITS=mm STL Exported from RuneLite Model-Dumper Plugin, using non-color binary!");
        stlHeader.flush();

        ByteArrayOutputStream w = new ByteArrayOutputStream();
        w.write(le(m.getFaceCount()));

        for (int fi = 0; fi < m.getFaceCount(); ++fi) {
            int vi1 = m.getFaceIndices1()[fi];
            int vi2 = m.getFaceIndices2()[fi];
            int vi3 = m.getFaceIndices3()[fi];

            // face normal vector
            w.write(le(0.0f));
            w.write(le(0.0f));
            w.write(le(0.0f));

            // vertex 1
            w.write(le((float) m.getVerticesX()[vi1]));
            w.write(le((float) m.getVerticesZ()[vi1]));
            w.write(le((float) -m.getVerticesY()[vi1]));

            // vertex 2
            w.write(le((float) m.getVerticesX()[vi2]));
            w.write(le((float) m.getVerticesZ()[vi2]));
            w.write(le((float) -m.getVerticesY()[vi2]));

            // vertex 3
            w.write(le((float) m.getVerticesX()[vi3]));
            w.write(le((float) m.getVerticesZ()[vi3]));
            w.write(le((float) -m.getVerticesY()[vi3]));

            // "attribute byte count" unused
            w.write(0);
            w.write(0);
        }

        w.flush();
        os.write(w.toByteArray());
        os.flush();
        os.close();
    }

    // int to little endian byte array
    private static byte[] le(int n) {
        byte[] b = new byte[4];
        b[0] = (byte) n;
        b[1] = (byte) (n >> 8);
        b[2] = (byte) (n >> 16);
        b[3] = (byte) (n >> 24);
        return b;
    }

    private static byte[] le(float f)
    {
        byte[] b = new byte[4];
        int n = Float.floatToIntBits(f);
        b[0] = (byte) n;
        b[1] = (byte) (n >> 8);
        b[2] = (byte) (n >> 16);
        b[3] = (byte) (n >> 24);
        return b;
    }

}
