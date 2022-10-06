package BluminEngine5.Rendering.Master;

import BluminEngine5.Application;
import BluminEngine5.Componant.Transform;
import BluminEngine5.Physics.Colision.Collider;
import BluminEngine5.Utils.ObjLoader;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Model implements Serializable {

    public Transform transform = Transform.DefaultZero;
    private Mesh mesh = null;
    private List<Collider> coliders = new ArrayList<>();

    public Model(Mesh mesh, List<Collider> coliders) {
        this.mesh = mesh;
        this.coliders = coliders;
    }


    public Model(int file, int archive) {
       var dat = ObjLoader.LoadModel(Application.getResourceManager().archive.GeFileFromArchive(file,archive));
        mesh = dat.getMesh();
        coliders = dat.getColliders();
    }

    public Model() {
    }

    public void setMesh(int file, int folder) {
        this.mesh = Application.getResourceManager().GetMesh(file,folder);
    }

    public Mesh getMesh() {
        return mesh;
    }

    public List<Collider> getColliders() {
        return coliders;
    }

    public void AddCollider(Collider col) {
        coliders.add(col);
    }

    public void RemoveCollider(Collider col) {
        if(!coliders.contains(col)){
            return;
        }
        coliders.remove(coliders.lastIndexOf(col));
    }
}
