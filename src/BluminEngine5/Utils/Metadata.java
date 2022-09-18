package BluminEngine5.Utils;

import org.json.JSONObject;

import java.io.IOException;

public class Metadata {
    public String GameName;
    public Version gameVersion;
    public Version engineVersion;
    public String Developer;
    public String Publisher;

    public boolean Raytracing;

    public Metadata(String file) throws IOException {
        if (!Utils.FileExists(file)) {
            throw new IOException("File " + file + " Not found");
        }
        String fileData = Utils.LoadFile(file);
        JSONObject obj = new JSONObject(fileData);
        GameName = obj.getString("GameName");
        gameVersion = Version.FromJson(obj.getJSONObject("GameVersion"));
        engineVersion = Version.FromJson(obj.getJSONObject("EngineVersion"));
        Developer = obj.getString("Developer");
        Publisher = obj.getString("publisher");
        Raytracing = obj.getBoolean("Raytracing");
    }
}
