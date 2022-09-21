package BluminEngine5.Rendering.UI;

import BluminEngine5.Componant.Rendering.ImageRenderer;
import BluminEngine5.Rendering.Shaders.Shader;
import BluminEngine5.Rendering.UI.Obj.Mesh;
import BluminEngine5.Rendering.UI.Obj.UiObject;
import BluminEngine5.Utils.Math.Vector2;
import BluminEngine5.Utils.Utils;

public class Panel extends UiObject {


    private ImageRenderer sr = new ImageRenderer();

    public Panel(Vector2 pos, Vector2 scale) {
        transform.position = pos.GetAsVec3();
        transform.scale = scale.GetAsVec3();
        mesh = new Mesh(Utils.QuadAsVertex2D());
        shader = new Shader("Res/Shaders/HUD/HUDShader.json");
        RegisterComponant(sr,this);
    }

    @Override
    public void Update() {

    }

    @Override
    public void OnRender() {

    }

    @Override
    public void Init() {
        mesh.Creat();
        shader.Creat();
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
