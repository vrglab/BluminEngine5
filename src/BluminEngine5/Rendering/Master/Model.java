package BluminEngine5.Rendering.Master;

import BluminEngine5.Application;
import BluminEngine5.Componant.Transform;
import BluminEngine5.Physics.Colision.Collider;
import BluminEngine5.Rendering.Color;
import BluminEngine5.Rendering.Texture;
import BluminEngine5.Utils.Debuging.Debug;
import BluminEngine5.Utils.Math.Vector3;
import BluminEngine5.Utils.ObjLoader;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.SerializationUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterOutputStream;

public class Model implements Serializable {

    public Transform transform = Transform.DefaultZero;
    private MeshData mesh = null;
    private SerliazedMaterial material;
    private List<Collider> coliders = new ArrayList<>();

    public Model(Mesh mesh, List<Collider> coliders, Material m) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        DeflaterOutputStream defl = new DeflaterOutputStream(out);

        byte[] meshdata = SerializationUtils.serialize(mesh);
        try {

            defl.write(meshdata);
            defl.flush();
            defl.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.mesh = new MeshData(mesh, out.toByteArray());
        this.coliders = coliders;
        setMaterial(m);
    }

    public Model(int file, int archive) {
       var dat = ObjLoader.LoadModel(Application.getResourceManager().archive.GeFileFromArchive(file,archive));
        ByteArrayOutputStream out2 = new ByteArrayOutputStream();
        InflaterOutputStream infl = new InflaterOutputStream(out2);
        Mesh m = null;

        try {
            infl.write(dat.getRawMesh());
            infl.flush();
            infl.close();
            ByteArrayInputStream in = new ByteArrayInputStream(out2.toByteArray());
            ObjectInputStream is = new ObjectInputStream(in);

            m = (Mesh)is.readObject();
        } catch (Exception e) {
            Debug.logException(e);
        }

        mesh = new MeshData(m, dat.getRawMesh());
        coliders = dat.getColliders();
        setMaterial(dat.getMaterial());
    }

    public Model() {

    }

    public void setMesh(int file, int folder) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        DeflaterOutputStream defl = new DeflaterOutputStream(out);

        byte[] meshdata = SerializationUtils.serialize(Application.getResourceManager().GetMesh(file,folder));
        try {

            defl.write(meshdata);
            defl.flush();
            defl.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Mesh m = null;
        try {

            ByteArrayInputStream in = new ByteArrayInputStream(meshdata);
            ObjectInputStream is = new ObjectInputStream(in);

            m = (Mesh)is.readObject();
        } catch (Exception e) {
            Debug.logException(e);
        }

        this.mesh = new MeshData(m, out.toByteArray());
        setMaterial(new Material());
    }

    public Mesh getMesh() {
        return mesh.mesh;
    }

    public byte[] getRawMesh() {
        return mesh.rawMesh;
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

    public Material getMaterial() {
        return SerliazedMaterial.Parse(material);
    }

    public void setMaterial(Material material) {
        this.material = SerliazedMaterial.Parse(material);
    }


    private static class SerliazedMaterial implements Serializable {

        public SerliazedTexture texture, DefuseMap,  SpecularMap, ReflectionsMap;

        private Color color = Color.Black;

        public Vector3 Ambient = new Vector3(0.31f,0.31f,0.31f);

        public float Shine = 0f;
        public float reflection = 0f;


        private static class SerliazedTexture implements Serializable{
            int file = 0;
            int Archive = 0;

            public SerliazedTexture(int file, int archive) {
                this.file = file;
                Archive = archive;
            }
        }

        public Color getColor() {
            return color;
        }

        public void setColor(Color color) {
            this.color = color;
        }

        public static Material Parse(SerliazedMaterial material) {
            Material m = new Material();
            m.setSpecularMap(Application.getResourceManager().GetTexture(material.SpecularMap.file-5, material.SpecularMap.Archive));
            m.setDefuseMap(Application.getResourceManager().GetTexture(material.DefuseMap.file-5, material.DefuseMap.Archive));
            m.setReflectionsMap(Application.getResourceManager().GetTexture(material.ReflectionsMap.file-5, material.ReflectionsMap.Archive));
            m.SetTexture(Application.getResourceManager().GetTexture(material.texture.file-5, material.texture.Archive));
            m.setColor(material.color);
            m.reflection = material.reflection;
            m.Shine = material.Shine;
            m.Ambient = material.Ambient;
            return m;
        }

        public static SerliazedMaterial Parse(Material material) {
            SerliazedMaterial m = new SerliazedMaterial();
            m.color = material.getColor();
            m.Ambient = material.Ambient;
            m.Shine = material.Shine;;
            m.reflection = material.reflection;
            m.texture = new SerliazedMaterial.SerliazedTexture(material.getTexture().file.ID, material.getTexture().file.ArchiveId);
            m.DefuseMap = new SerliazedMaterial.SerliazedTexture(material.getDefuseMap().file.ID, material.getDefuseMap().file.ArchiveId);
            m.SpecularMap = new SerliazedMaterial.SerliazedTexture(material.getSpecularMap().file.ID, material.getSpecularMap().file.ArchiveId);
            m.ReflectionsMap = new SerliazedMaterial.SerliazedTexture(material.getReflectionsMap().file.ID, material.getReflectionsMap().file.ArchiveId);
            return m;
        }

    }

    private static class MeshData implements Serializable{
        Mesh mesh = null;
        byte[] rawMesh = null;

        public MeshData(Mesh mesh, byte[] rawMesh) {
            this.mesh = mesh;
            this.rawMesh = rawMesh;
        }
    }


    public void SaveToFile(String file) throws IOException {
        Mesh meshUntoutched = mesh.mesh;
        mesh.mesh = null;
        FileOutputStream fileOutputStream = new FileOutputStream(Application.getMetadata().ResourceFolder + "/" + file + ".bmd");
        ObjectOutputStream oos = new ObjectOutputStream(fileOutputStream);
        oos.writeObject(this);
        oos.flush();
        oos.close();
        mesh.mesh = meshUntoutched;
    }

}


