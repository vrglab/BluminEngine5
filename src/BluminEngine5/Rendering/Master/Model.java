package BluminEngine5.Rendering.Master;

import BluminEngine5.Application;
import BluminEngine5.Physics.Colision.Collider;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Model implements Serializable {

    private Mesh mesh = null;
    private List<Collider> coliders = new ArrayList<>();

    public Model(Mesh mesh, List<Collider> coliders) {
        this.mesh = mesh;
        this.coliders = coliders;
    }

    public Model() {
    }

    public void setMesh(int file, int folder) {
        this.mesh = Application.getResourceManager().GetMesh(file,folder);
    }

    public Mesh getMesh() {
        return mesh;
    }

    public List<Collider> getColiders() {
        return coliders;
    }
}
