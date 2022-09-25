package BluminEngine5.Utils.ResourceMannager;

import BluminEngine5.Application;
import BluminEngine5.Utils.Debuging.Debug;
import BluminEngine5.Utils.EventSystem.IAction;
import BluminEngine5.Utils.ResourceMannager.Archive.ArchiveMannager;
import BluminEngine5.Utils.ResourceMannager.Archive.ArchivedFile;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;
import java.util.zip.DeflaterOutputStream;

public class ResourceMannager {

    public ArchiveMannager archive = new ArchiveMannager();


    public ResourceMannager() {
        LoadArchiveAsMainArchive("Res.baf");
    }

    public File LoadIntoTempFile(ArchivedFile file)
    throws IOException {
        UUID id = UUID.randomUUID();
        String path = "Res/Temp/Temp " + id.toString() + " " + file.FileName + "." + file.Extension;
        File f = Files.createFile(Paths.get(path)).toFile();
        FileUtils.writeByteArrayToFile(f, file.GetDecodedData());
        return f;
    }

    public void LoadArchiveAsMainArchive(String file){
        try{
            archive = ArchiveMannager.Decompress(file);
        }catch (Exception e) {

        }
    }
}
