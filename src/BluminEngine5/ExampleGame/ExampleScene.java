package BluminEngine5.ExampleGame;

import BluminEngine5.Rendering.UI.Canvas;
import BluminEngine5.Rendering.UI.Obj.Panel;
import BluminEngine5.Rendering.UI.TextField;
import BluminEngine5.SceneMannagement.Scene;
import BluminEngine5.SceneMannagement.SceneManager;
import BluminEngine5.Utils.Input;
import org.lwjgl.glfw.GLFW;

public class ExampleScene extends Scene {

    private ExampleGameobject obj = new ExampleGameobject();

    public Canvas UiCanvas = new Canvas();

    public Panel p = new Panel();
    public ExampleScene() {
        RegsiterGameObject(obj);
        RegsiterGameObject(UiCanvas);
        UiCanvas.RegisterUiObj(p);
    }
    @Override
    public void Update() {
        if(Input.Instance().WasHeld(GLFW.GLFW_KEY_A)){
            ActiveCamera.transform.position.x -= 0.01;
        }
        if(Input.Instance().WasHeld(GLFW.GLFW_KEY_W)){
            ActiveCamera.transform.position.z -= 0.01;
        }
        if(Input.Instance().WasHeld(GLFW.GLFW_KEY_S)){
            ActiveCamera.transform.position.z += 0.01;
        }
        if(Input.Instance().WasHeld(GLFW.GLFW_KEY_D)){
            ActiveCamera.transform.position.x += 0.01;
        }
        if(Input.Instance().WasHeld(GLFW.GLFW_KEY_SPACE)){
            ActiveCamera.transform.position.y += 0.01;
        }
        if(Input.Instance().WasHeld(GLFW.GLFW_KEY_LEFT_SHIFT)){
            ActiveCamera.transform.position.y -= 0.01;
        }
        if(Input.Instance().WasPressed(GLFW.GLFW_KEY_ESCAPE)){
            SceneManager.GetCurent().SetActiveScene(new SecondExampleScene());
        }
    }

    @Override
    public void OnRender() {

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
    public void Load() {
    }
}
