package BluminEngine5.Utils.ResourceMannager;

import BluminEngine5.Application;
import BluminEngine5.Componant.Audio.WaveData;
import BluminEngine5.Rendering.Master.Mesh;
import BluminEngine5.Rendering.Shaders.Shader;
import BluminEngine5.Rendering.Texture;
import BluminEngine5.Utils.Debuging.Debug;
import BluminEngine5.Utils.ObjLoader;
import BluminEngine5.Utils.ResourceMannager.Archive.ArchiveMannager;
import BluminEngine5.Utils.ResourceMannager.Archive.ArchivedFile;
import BluminEngine5.Utils.Utils;
import org.apache.commons.io.FileUtils;
import org.lwjgl.stb.STBVorbisInfo;

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
    private HashMap<String, WaveData> wavsbacth = new HashMap<String, WaveData>();

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

    public WaveData GetWav(int file, int Archive) {
        try {
            var arch = archive.GeFileFromArchive(file, Archive);
            var f = LoadIntoTempFile(arch);
            var location = Application.getMetadata().ResourceFolder +"/Temp/Temp " + arch.FileName + "." + arch.Extension;

            var shaderLocation = f.getAbsolutePath();
            if(!wavsbacth.containsKey(location)) {
                var dat = WaveData.create(Utils.LoadFileAsStream(shaderLocation));
                wavsbacth.put(location,dat);
                return dat;
            } else{
                return wavsbacth.get(location);
            }
        }catch (Exception e) {
            Debug.logException("Could not load the wav file",e);
            return null;
        }
    }


    public Mesh GetMesh(int file, int Archive, int texArch, int texId) {
        try{
            var arch = archive.GeFileFromArchive(file, Archive);
            var f = LoadIntoTempFile(arch);

            var location = Application.getMetadata().ResourceFolder +"/Temp/Temp " + arch.FileName + "." + arch.Extension;

            var shaderLocation = f.getAbsolutePath();

            if(!meshsbacth.containsKey(location)) {
                var dat = ObjLoader.LoadFile(shaderLocation, texArch, texId);
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

    public Mesh GetMesh(int file, int Archive) {
        try{
            var arch = archive.GeFileFromArchive(file, Archive);
            var f = LoadIntoTempFile(arch);

            var location = Application.getMetadata().ResourceFolder +"/Temp/Temp " + arch.FileName + "." + arch.Extension;

            var shaderLocation = f.getAbsolutePath();

            if(!meshsbacth.containsKey(location)) {
                var dat = ObjLoader.LoadFile(shaderLocation, 0,2);
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




}
