package ExampleGame;

import BluminEngine5.Rendering.UI.Canvas;
import BluminEngine5.Rendering.UI.Panel;
import BluminEngine5.Rendering.UI.Rect;
import BluminEngine5.SceneMannagement.Scene;
import BluminEngine5.SceneMannagement.SceneManager;
import BluminEngine5.Utils.Input;
import BluminEngine5.Utils.Math.Vector2;
import BluminEngine5.Utils.Math.Vector3;
import org.lwjgl.glfw.GLFW;

public class ExampleScene extends Scene {

    private ExampleGameobject obj = new ExampleGameobject();
    private ExampleSun ES = new ExampleSun();

    public Canvas UiCanvas = new Canvas();

    public Panel p = new Panel(new Rect(new Vector2(0,0), new Vector2(0.5f,0.5f), Vector3.Zero));

    public ExampleScene() {
        RegsiterGameObject(obj);
        RegsiterGameObject(ES);
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
