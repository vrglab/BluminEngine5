package BluminEngine5.Utils.ResourceMannager;

import BluminEngine5.Application;
import BluminEngine5.Audio.Legacy.AudioFile;
import BluminEngine5.Rendering.Master.Mesh;
import BluminEngine5.Rendering.Master.Model;
import BluminEngine5.Rendering.Shaders.Shader;
import BluminEngine5.Rendering.Texture;
import BluminEngine5.Utils.Debuging.Debug;
import BluminEngine5.Utils.ObjLoader;
import BluminEngine5.Utils.ResourceMannager.Archive.Archive;
import BluminEngine5.Utils.ResourceMannager.Archive.ArchiveMannager;
import BluminEngine5.Utils.ResourceMannager.Archive.ArchivedFile;
import BluminEngine5.Utils.Utils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.UUID;

public class ResourceMannager {

    public ArchiveMannager archive = new ArchiveMannager();

    private HashMap<String, Shader> shadersbacth = new HashMap<String, Shader>();
    private HashMap<String, Texture> texturesbacth = new HashMap<String, Texture>();
    private HashMap<String, Mesh> meshsbacth = new HashMap<String, Mesh>();
    private HashMap<String, AudioFile> audiobatch = new HashMap<String, AudioFile>();

    private HashMap<String, Model> modelssbacth = new HashMap<String, Model>();

    public ResourceMannager(String s) {
        LoadArchiveAsMainArchive( Application.getMetadata().ResourceFolder + "/" + s);
    }


    public File LoadIntoTempFile(ArchivedFile file)
    throws IOException {
        try{
            UUID id = UUID.randomUUID();
            String path = Application.getMetadata().ResourceFolder
                    +"/Temp/Temp " + id.toString() + " " + file.FileName + "." + file.Extension;
            File f = Files.createFile(Paths.get(path)).toFile();
            FileUtils.writeByteArrayToFile(f, file.GetDecodedData());
            return f;
        }catch(Exception e) {
            Debug.logException("Failed to load the Archive file", e);
            return null;
        }

    }

    public void LoadArchiveAsMainArchive(String file){
        try{
            archive = ArchiveMannager.Decompress(file);
        }catch (Exception e) {
            Debug.logError(e.getMessage());
        }
    }

    public Shader GetShader(String shaderLocation) {
        if(!shadersbacth.containsKey(shaderLocation)) {
            var dat = new Shader(shaderLocation);
            shadersbacth.put(shaderLocation,dat);
            return dat;
        } else{
            return shadersbacth.get(shaderLocation);
        }
    }

    public Texture GetTexture(int file, int Archive) {
        var arch = archive.GeFileFromArchive(file, Archive);
        var location = Application.getMetadata().ResourceFolder +"/Temp/Temp " + arch.FileName + "." + arch.Extension;
        if(!texturesbacth.containsKey(location)) {
            var dat = new Texture(arch);
            texturesbacth.put(location,dat);
            return dat;
        } else{
            return texturesbacth.get(location);
        }
    }

    public AudioFile GetAudio(int file, int Archive) {
        try {
            var arch = archive.GeFileFromArchive(file, Archive);
            var f = LoadIntoTempFile(arch);
            var location = Application.getMetadata().ResourceFolder +"/Temp/Temp " + arch.FileName + "." + arch.Extension;

            var shaderLocation = f.getAbsolutePath();
            if(!audiobatch.containsKey(location)) {
                var dat = AudioFile.create(Utils.LoadFileAsStream(shaderLocation));
                audiobatch.put(location,dat);
                f.delete();
                return dat;
            } else{
                return audiobatch.get(location);
            }
        }catch (Exception e) {
            Debug.logException("Could not load the wav file",e);
            return null;
        }
    }
    public Mesh GetMesh(int file, int Archive) {
        try{
            var arch = archive.GeFileFromArchive(file, Archive);
            var f = LoadIntoTempFile(arch);

            var location = Application.getMetadata().ResourceFolder +"/Temp/Temp " + arch.FileName + "." + arch.Extension;

            var shaderLocation = f.getAbsolutePath();

            if(!meshsbacth.containsKey(location)) {
                var dat = ObjLoader.LoadFile(shaderLocation);
                f.delete();
                meshsbacth.put(location,dat);
                return dat;
            } else{
                return meshsbacth.get(location);
            }
        } catch(IOException e){
            return  null;
        }
    }

    public Model GetModel(int file, int Archive) {
        File f = null;
        try{
            var arch = archive.GeFileFromArchive(file, Archive);

            f = LoadIntoTempFile(arch);

            var location = Application.getMetadata().ResourceFolder +"/Temp/Temp " + arch.FileName + "." + arch.Extension;

            if(!modelssbacth.containsKey(location)) {

                if(!FilenameUtils.getExtension(f.getAbsolutePath()).equals("bmd")) {
                    Debug.log(FilenameUtils.getExtension(f.getAbsolutePath()));
                    Debug.logException(new Exception( f.getAbsolutePath() + " is not a BluminEngine Model File"));
                    return null;
                }

                ObjectInputStream objectInputStream = new ObjectInputStream(Utils.LoadFileAsStream(f.getAbsolutePath()));
                var dat = (Model) objectInputStream.readObject();
                modelssbacth.put(location,dat);
                objectInputStream.close();
                f.delete();
                return dat;
            } else{
                return modelssbacth.get(location);
            }
        } catch(Exception e){
            f.delete();
            return  null;
        }
    }

    public static ArchiveMannager DirectoryToArchive(String directory) throws IOException {
        HashMap<String, Archive> Directories = new HashMap<>();
        HashMap<String, ArchivedFile> Files = new HashMap<>();
        File currentDirectory = FileUtils.getFile(directory);
        ArchiveMannager am = new ArchiveMannager();


        for (File p: currentDirectory.listFiles()) {
            if(p.isDirectory()) {
                shush(am, p);
            } else if(p.isFile()) {
                yell(am,p, 0);
            }
        }

        return am;
    }


    private static Archive shush(ArchiveMannager am, File p) {
        Archive us = null;
        if(am.getArchives().contains(p.getPath())) {
            int root = am.getArchives().lastIndexOf(p.getPath());
            us = am.CreateArchive(root,FilenameUtils.getBaseName(p.getAbsolutePath()));
            for (File d: p.listFiles()) {
                if(d.isDirectory()) {
                    shush(am, d);
                } else if(d.isFile()) {
                    yell(am, d, us.Id);
                }
            }
        }else {
            int root = 0;
            us = am.CreateArchive(root,FilenameUtils.getBaseName(p.getAbsolutePath()));
            for (File d: p.listFiles()) {
                if(d.isDirectory()) {
                    shush(am, d);
                } else if(d.isFile()) {
                    yell(am, d, us.Id);
                }
            }
        }
        return us;
    }

    static ArchivedFile yell(ArchiveMannager am, File p, int root) {
        ArchivedFile us = null;
            us = am.ArchiveFile(p.getAbsolutePath());
            am.PutFileInArchive(us, root);
        return us;
    }






}
