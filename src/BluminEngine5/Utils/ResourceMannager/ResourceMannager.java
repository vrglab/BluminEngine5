package BluminEngine5.Utils.ResourceMannager;

import BluminEngine5.Application;
import BluminEngine5.Utils.Debuging.Debug;
import BluminEngine5.Utils.EventSystem.IAction;
import BluminEngine5.Utils.ResourceMannager.Archive.ArchiveMannager;
import BluminEngine5.Utils.ResourceMannager.Archive.ArchivedFile;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ResourceMannager {

    public ArchiveMannager archive = new ArchiveMannager();

    private int Counter = 0;
    public ResourceMannager() {
        archive.CreateArchive(1, "Textures");
            archive.PutFileInArchive("Res/Textures/Missing.png", 2);
            archive.PutFileInArchive("Res/Textures/groundImage 1.png", 2);
            archive.PutFileInArchive("Res/Textures/uhhhh.png", 2);

                archive.CreateArchive(2, "UI");
                    archive.PutFileInArchive("Panel.png", 3);

        archive.CreateArchive(1, "Models");
            archive.PutFileInArchive("Res/Models/Cube.obj", 4);
            archive.PutFileInArchive("Res/Models/dragon.obj", 4);

        archive.CreateArchive(1, "sound");
            archive.PutFileInArchive("Res/testing.ogg", 5);



        Application.OnExit.addListener(new IAction() {
            @Override
            public void Run() {
                Exit();
            }
        });
    }

    public File LoadIntoTempFile(ArchivedFile file)
    throws IOException {
        String path = "Res/Temp/Temp " + Counter + " " + file.FileName + "." + file.Extension;
        Counter++;
        File f = Files.createFile(Paths.get(path)).toFile();
        FileUtils.writeByteArrayToFile(f, file.GetDecodedData());
        return f;
    }

    private void Exit() {
        Debug.log(archive.GeFileFromArchive(1,5));
    }
}
