package net.bram91.modeldumper.types;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import net.runelite.http.api.RuneLiteAPI;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Set;

import static net.runelite.client.RuneLite.RUNELITE_DIR;
@Slf4j
public class DataFetcher {

    private static final Gson gson = RuneLiteAPI.GSON.newBuilder().create();

    private String getRemoteJson(String path) throws IOException {
        String url = "https://bram91.github.io/" + path;
        if (System.getProperty("devmode") != null) {
            url = "http://localhost/" + path;
        }

        try (InputStream inputStream = new URL(url).openStream()) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            return readAll(reader);
        }
    }

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public Set<NPCData> getNPCData() throws IOException {
        String data = getRemoteJson("data.json");

        return gson.fromJson(data, new TypeToken<Set<NPCData>>() {
        }.getType());
    }

    public Set<NPCData> getAnimationGroups() throws IOException {
        String data = getRemoteJson("animationgroups.json");
        return gson.fromJson(data, new TypeToken<Set<AnimationGroup>>() {
        }.getType());
    }

    public HashMap<Integer, String> getAnimationNames() {
        HashMap<Integer, String> animationNames = new HashMap<>();
        File MODEL_DIR = new File(RUNELITE_DIR, "models/animationNames.txt");
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(MODEL_DIR));
            String line = reader.readLine();

            while (line != null) {
                String[] lineData = line.split("=");
                animationNames.put(Integer.valueOf(lineData[0]), lineData[1]);
                line = reader.readLine();
            }

            reader.close();
        } catch (IOException e) {

        } catch (ArrayIndexOutOfBoundsException e) {
            log.warn("Invalid line in models/animationNames.txt");
        }
        return animationNames;
    }

    public void saveAnimationNames(Object[] animationNames) throws FileNotFoundException {
        PrintWriter animationName = new PrintWriter(new File(RUNELITE_DIR, "models/animationNames.txt"));
        for (Object name : animationNames) {
            AnimationGroup animationGroup = (AnimationGroup) name;
            animationGroup.getAnimationGroup().forEach((a) -> {
                if (a.getName() != null && !a.getName().equals("null"))
                    animationName.println(a.getId() + "=" + a.getName());
            });

        }
        animationName.flush();
        animationName.close();
    }
}
