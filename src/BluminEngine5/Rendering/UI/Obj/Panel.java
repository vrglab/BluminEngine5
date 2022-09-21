package BluminEngine5.Rendering.UI.Obj;

import BluminEngine5.Rendering.Shaders.Shader;
import BluminEngine5.Utils.Math.Vector2;

public class Panel extends UiObject{

    public Panel() {
        Vertex vpos[] = {
                new Vertex(new Vector2(-1,1)),
                new Vertex(new Vector2(-1,-1)),
                new Vertex(new Vector2(1,1)),
                new Vertex(new Vector2(1,-1)),
        };
        mesh = new Mesh(vpos);
        shader = new Shader("Res/Shaders/HUD/HUDShader.json");
    }

    @Override
    public void Update() {

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
