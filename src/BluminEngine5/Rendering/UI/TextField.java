package BluminEngine5.Rendering.UI;

import BluminEngine5.Application;
import BluminEngine5.Rendering.Shaders.Shader;
import BluminEngine5.Rendering.UI.Obj.UiObject;

public class TextField extends UiObject {

    private String text;

    public TextField(){

        //mesh = ObjLoader.LoadFile("Res/Models/Cube.obj");
        shader = Application.getResourceManager().GetShader(Application.getMetadata().ResourceFolder + "/Shaders/HUD/HUDShader.json");
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
