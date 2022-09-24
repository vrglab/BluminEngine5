package BluminEngine5.Utils.ResourceMannager.Archive;

import java.util.ArrayList;
import java.util.List;

import static BluminEngine5.Utils.ResourceMannager.Archive.ArchiveMannager.NULL;

public class Archive {
    public String name;
    public List<ArchivedFile> archivedFiles = new ArrayList<>();
    public int Id = NULL;
    public int ParentArchiveId = NULL;
}
