package BluminEngine5.Rendering.Master;


import BluminEngine5.Application;
import BluminEngine5.Rendering.Color;
import BluminEngine5.Rendering.Texture;
import BluminEngine5.Rendering.Vertex;
import BluminEngine5.Utils.Math.Vector3;
import BluminEngine5.Utils.ResourceMannager.Archive.ArchivedFile;

public class Material {
    private Texture texture;
    private Color color;

    private Texture DefuseMap;
    private Texture SpecularMap;

    public Vector3 Ambient = new Vector3(0.31f,0.31f,0.31f);
    public Vector3 Diffuse = new Vector3(1,0.5f,0.31f);
    public Vector3 Specular = new Vector3(0.5f,0.5f,0.5f);
    public float Shine = 12.0f;


    public Material() {
        color = new Color(1,0.5f,1,1);
    }

    public Material(ArchivedFile text) {
        texture = Application.getResourceManager().GetTexture(text.ArchiveId, text.ID);
        color = new Color(1,0.5f,1,1);
    }

    public Material(Texture text) {
        texture = text;
        color = new Color(1,0.5f,1,1);
    }

    public void Creat() {
        texture = Application.getResourceManager().GetTexture(0,2);
        DefuseMap = Application.getResourceManager().GetTexture(0,2);
        SpecularMap = Application.getResourceManager().GetTexture(0,2);
        texture.Create();
        DefuseMap.Create();
        SpecularMap.Create();
    }

    public void Destroy() {
        texture.Destroy();
        DefuseMap.Destroy();
        SpecularMap.Destroy();
    }

    public Texture getTexture() {
        return texture;
    }

    public Texture getDefuseMap() {
        return DefuseMap;
    }

    public Texture getSpecularMap() {
        return SpecularMap;
    }

    public void SetTexture(Texture t) {
        texture = t;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
