package BluminEngine5.Rendering.Master;


import BluminEngine5.Rendering.Color;
import BluminEngine5.Rendering.Texture;
import BluminEngine5.Rendering.Vertex;
import BluminEngine5.Utils.Math.Vector3;

public class Material {
    private Texture texture;
    private Color color;

    private Texture DefuseMap;
    private Texture SpecularMap;

    public Vector3 Ambient = new Vector3(1,0.5f,0.31f);
    public Vector3 Diffuse = new Vector3(1,0.5f,0.31f);
    public Vector3 Specular = new Vector3(0.5f,0.5f,0.5f);
    public float Shine = 32.0f;


    public Material() {
        texture = new Texture("Res/Textures/Missing.png");
        DefuseMap = new Texture("Res/Textures/Missing.png");
        SpecularMap = new Texture("Res/Textures/Missing.png");
        color = new Color(1,1,1,1);
    }

    public Material(String text) {
        texture = new Texture(text);
    }

    public Material(Texture text) {
        texture = text;
    }

    public void Creat() {
        texture.Create();
    }

    public void Destroy() {
        texture.Destroy();
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
