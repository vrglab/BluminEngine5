package BluminEngine5.Rendering.UI;

import BluminEngine5.Rendering.Shaders.Shader;
import BluminEngine5.Rendering.UI.Obj.UiObject;
import BluminEngine5.Utils.ObjLoader;

public class TextField extends UiObject {

    private String text;

    public TextField(int x,int xx,int y,int yy){
        rect = new Rect(y,yy,x,xx);
        //mesh = ObjLoader.LoadFile("Res/Models/Cube.obj");
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
