package BluminEngine5.Rendering.Master;


import BluminEngine5.Rendering.Texture;

public class Material {
    private Texture texture;

    public Material() {
        texture = new Texture("Res/Textures/Missing.png");
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

    public void SetTexture(Texture t) {
        texture = t;
    }
}
