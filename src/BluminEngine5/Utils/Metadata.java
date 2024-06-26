package BluminEngine5.Utils;

import org.ini4j.Wini;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

public class Metadata {
    public String GameName = "";
    public Version gameVersion;
    public String ResourceFolder;
    public String MainArchiveFile;
    public String RenderPipline;
    public String Developer = "";
    public String Publisher = "";

    public boolean PostProcessing;

    public Metadata(String file) throws IOException {
        if (!Utils.FileExists(file)) {
            throw new IOException("File " + file + " Not found");
        }

        Wini ini = new Wini(new File(file));

        ResourceFolder = ini.get("EngineConfig", "ResourcesPath");
        MainArchiveFile = ini.get("EngineConfig", "Archive");
        RenderPipline= ini.get("EngineConfig", "RePipline");

        GameName = ini.get("GameConfig", "Name");
        Developer = ini.get("GameConfig", "Developer");
        Publisher = ini.get("GameConfig", "publisher");
        gameVersion= new Version(ini.get("GameConfig", "Version"));
    }
}
