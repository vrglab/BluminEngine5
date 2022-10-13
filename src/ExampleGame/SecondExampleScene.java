package ExampleGame;

import BluminEngine5.Rendering.Color;
import BluminEngine5.Rendering.CubeMap;
import BluminEngine5.SceneMannagement.Scene;
import BluminEngine5.SceneMannagement.SceneManager;
import BluminEngine5.Utils.Input;
import org.lwjgl.glfw.GLFW;

public class SecondExampleScene extends Scene {

    Skybox sb = new Skybox();


    public SecondExampleScene() {

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
            SceneManager.GetCurent().SetActiveScene(new ExampleScene());
        }
    }

    @Override
    public void OnRender() {
    }

    @Override
    public void Init() {
        RegsiterGameObject(sb);
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
