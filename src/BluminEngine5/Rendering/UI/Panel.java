package BluminEngine5.Rendering.UI;

import BluminEngine5.Application;
import BluminEngine5.Rendering.Texture;
import BluminEngine5.Rendering.UI.Coponants.ImageRenderer;
import BluminEngine5.Rendering.Shaders.Shader;
import BluminEngine5.Rendering.UI.Obj.Mesh;
import BluminEngine5.Rendering.UI.Obj.UiObject;
import BluminEngine5.Utils.Utils;

public class Panel extends UiObject {


    private ImageRenderer sr = new ImageRenderer();

    public Panel(Rect r) {
        transform = r;
    }

    @Override
    public void Update() {

    }

    @Override
    public void OnRender() {

    }

    @Override
    public void Init() {
        mesh = new Mesh(Utils.QuadAsVertex2D());
        changeImage(Application.getResourceManager().GetTexture(0,4));
        shader = Application.getResourceManager().GetShader(Application.getMetadata().ResourceFolder + "/Shaders/HUD/HUDShader.json");
        RegisterComponant(sr,this);
        mesh.Creat();
        shader.Creat();
    }

    public void changeImage(Texture tex){
        mesh.getMaterial().SetTexture(tex);
    }

    @Override
    public void PreInit() {

    }

    @Override
    public void OnExit() {

    }

    @Override
    public void SceneLoad() {

    }

    @Override
    public void Destroy() {

    }

    @Override
    public void OnUiRender() {

    }
}
