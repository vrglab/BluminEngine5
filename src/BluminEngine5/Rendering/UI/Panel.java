package BluminEngine5.Rendering.UI;

import BluminEngine5.Rendering.Shaders.Shader;
import BluminEngine5.Rendering.UI.Obj.Mesh;
import BluminEngine5.Rendering.UI.Obj.UiObject;
import BluminEngine5.Rendering.UI.Obj.Vertex;
import BluminEngine5.Utils.Math.Vector2;
import BluminEngine5.Utils.Math.Vector3;

public class Panel extends UiObject {

    public Panel(Vector2 pos, Vector2 scale) {
        Vertex vpos[] = {
                new Vertex(new Vector2(-1,1)),
                new Vertex(new Vector2(-1,-1)),
                new Vertex(new Vector2(1,1)),
                new Vertex(new Vector2(1,-1)),
        };
        transform.position = pos.GetAsVec3();
        transform.scale = scale.GetAsVec3();
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
