package BluminEngine5.Rendering.UI;

import BluminEngine5.Rendering.Master.Mesh;
import BluminEngine5.Rendering.Shaders.Shader;
import BluminEngine5.Rendering.Texture;
import BluminEngine5.Rendering.Vertex;
import BluminEngine5.Utils.Math.Vector2;
import BluminEngine5.Utils.Math.Vector3;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class TextField extends UiObject {

    private String text;

    public TextField(int x,int xx,int y,int yy){
        rect = new Rect(y,yy,x,xx);
        mesh.getMaterial().SetTexture(new Texture(new Vector2(xx,yy)));
        shader = new Shader("Res/Shaders/HUDShader.json");
    }



    @Override
    public void Update() {

    }

    @Override
    public void Init() {

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
