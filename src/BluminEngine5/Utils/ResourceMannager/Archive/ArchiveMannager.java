package BluminEngine5.Utils.ResourceMannager.Archive;

import BluminEngine5.Utils.Debuging.Debug;
import BluminEngine5.Utils.Utils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;

public class ArchiveMannager implements Serializable{
    public static final int NULL = -0000000;
    private List<Archive> Archives = new ArrayList<>();

    private static int idFileCounter = 0;
    private static int idArchiveCounter = 0;

    private static int idCounterFiles = 0;

    public ArchiveMannager() {
        if(Archives.size() <= 0) {
            Archive a = CreateArchive();
            Archives.add(a);
        }
    }
    public ArchivedFile ArchiveFile(String File) {
        ArchivedFile af = new ArchivedFile();
        Path path = Paths.get(File);
        af.FileName = FilenameUtils.getBaseName(File);
        af.Extension = FilenameUtils.getExtension(File);
        try {

            byte[] fileContent = FileUtils.readFileToByteArray(path.toFile());
            String encodedString = Base64.getEncoder().encodeToString(fileContent);

            af.fileData = encodedString;
            af.ID = idFileCounter;
            idFileCounter++;
        } catch (IOException e) {
        }
        return  af;
    }
    public void PutFileInArchive(ArchivedFile file, int ArchiveId) {
        Archive ar = Archives.get(ArchiveId);
        file.ArchiveId = ArchiveId;
        ar.archivedFiles.add(file);
        Archives.remove(ArchiveId);
        Archives.add(ar);
    }
    public void PutFileInArchive(String file, int ArchiveId) {
        Archive ar = Archives.get(ArchiveId);
        ArchivedFile af = ArchiveFile(file);
        af.ArchiveId = ArchiveId;
        ar.archivedFiles.add(af);
        var dat = Archives.remove(ArchiveId);
        Archives.add(ar);
    }
    public void PutFileInArchive(String file) {
        Archive ar = Archives.get(0);
        ArchivedFile af = ArchiveFile(file);
        af.ArchiveId = 0;
        ar.archivedFiles.add(af);
        Archives.remove(0);
        Archives.add(ar);
    }
    public ArchivedFile GeFileFromArchive(int FileId, int ArchiveId) {
        try {
            return Archives.get(ArchiveId).archivedFiles.get(FileId);
        } catch(IndexOutOfBoundsException e) {
            return null;
        }
    }
    public Archive CreateArchive() {
        Archive ar = new Archive();
        ar.Id = idArchiveCounter;
        ar.name = "Default";
        idArchiveCounter++;
        Archives.add(ar);
        return ar;
    }
    public Archive CreateArchive(String name) {
        Archive ar = new Archive();
        ar.Id = idArchiveCounter;
        ar.name = name;
        idArchiveCounter++;
        Archives.add(ar);
        return ar;
    }
    public Archive CreateArchive(int parentArchive) {
        Archive ar = new Archive();
        ar.Id = idArchiveCounter;
        ar.ParentArchiveId = parentArchive;
        ar.name = "Default Child of " + parentArchive + " with the id of " + ar.Id;
        idArchiveCounter++;
        Archives.add(ar);
        return ar;
    }
    public Archive CreateArchive(int parentArchive, String name) {
        Archive ar = new Archive();
        ar.Id = idArchiveCounter;
        ar.ParentArchiveId = parentArchive;
        ar.name = name;
        idArchiveCounter++;
        Archives.add(ar);
        return ar;
    }
    public Archive GetArchive(int id) {
        return Archives.get(id);
    }

    //TODO: Write Archive/File deletion functionality

    public static void Compress(ArchiveMannager archive, String file) throws Exception{
        try {
            UUID id = UUID.randomUUID();
            if(Files.exists(Paths.get("Res/" +file))) {
                Files.delete(Paths.get("Res/" +file));
            }

            FileOutputStream fileOutputStream = new FileOutputStream("Res/temp " + id.toString() +".baf");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(archive);
            objectOutputStream.flush();
            objectOutputStream.close();
            fileOutputStream.close();
            FileInputStream fis = new FileInputStream("Res/temp " + id.toString() +".baf");
            FileOutputStream fos = new FileOutputStream("Res/" +file);
            DeflaterOutputStream dos = new DeflaterOutputStream(fos);
            int data;
            while ((data=fis.read())!=-1)
            {
                dos.write(data);
            }
            fis.close();
            dos.close();
            Files.delete(Paths.get("Res/temp " +id.toString()  +".baf"));

        }catch (Exception e) {

        }
    }

    public static ArchiveMannager Decompress(String file) throws Exception{
        UUID id = UUID.randomUUID();
        FileInputStream fis = new FileInputStream("Res/" + file);
        FileOutputStream fos = new FileOutputStream("Res/temp " + id.toString() +".baf");
        InflaterInputStream iis = new InflaterInputStream(fis);
        fos.write(iis.readAllBytes());
        fos.close();

        FileInputStream fileInputStream = new FileInputStream("Res/temp " + id.toString() +".baf");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        ArchiveMannager r =  (ArchiveMannager) objectInputStream.readObject();
        objectInputStream.close();
        Files.delete(Paths.get("Res/temp " + id.toString() +".baf"));
        return r;
    }

    private static class Decompress implements Runnable {

        String file;
        ArchiveMannager r;
        public Decompress(String file) {
            this.file = file;
        }
        @Override
        public void run() {
            try {
                FileInputStream fis=new FileInputStream("Res/" + file);
                FileOutputStream fos=new FileOutputStream("Res/temp.baf");
                InflaterInputStream iis=new InflaterInputStream(fis);

                fos.write(iis.readAllBytes());

                FileInputStream fileInputStream = new FileInputStream("Res/temp.baf");
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                r =  (ArchiveMannager) objectInputStream.readObject();
                objectInputStream.close();
                Files.delete(Paths.get("Res/temp.baf"));

            } catch(Exception e) {
                Utils.CrashApp(-200, "Failed to Decompress archive");
            }
        }

        public ArchiveMannager getValue() {
            return r;
        }
    }
}
