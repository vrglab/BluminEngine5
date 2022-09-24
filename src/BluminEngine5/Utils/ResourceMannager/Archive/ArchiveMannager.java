package BluminEngine5.Utils.ResourceMannager.Archive;

import BluminEngine5.Utils.Debuging.Debug;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.InflaterOutputStream;


public class ArchiveMannager {
    public static final int NULL = -0000000;
    private List<Archive> Archives = new ArrayList<>();

    private static int idFileCounter = 0;
    private static int idArchiveCounter = 0;

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
}
