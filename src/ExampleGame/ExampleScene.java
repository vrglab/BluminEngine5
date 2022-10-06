package ExampleGame;

import BluminEngine5.Editor.UI.Canvas;
import BluminEngine5.Rendering.Color;
import BluminEngine5.Rendering.Lighting.PointLight;
import BluminEngine5.Rendering.Lighting.Sun;
import BluminEngine5.Rendering.UI.Panel;
import BluminEngine5.Rendering.UI.Rect;
import BluminEngine5.SceneMannagement.Scene;
import BluminEngine5.SceneMannagement.SceneManager;
import BluminEngine5.Utils.Debuging.Debug;
import BluminEngine5.Utils.Input;
import BluminEngine5.Utils.Math.Vector2;
import BluminEngine5.Utils.Math.Vector3;
import org.lwjgl.glfw.GLFW;

import java.util.Random;

public class ExampleScene extends Scene {

    private ExampleGameobject obj = new ExampleGameobject();


    public Panel p = new Panel(new Rect(new Vector2(0,0), new Vector2(0.5f,0.5f), Vector3.Zero));

    public ExampleScene() {
        RegsiterGameObject(ActiveCamera);
        RegsiterGameObject(obj);


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
        Random r = new Random();
        int dat = 4;

        PointLight pl = new PointLight();
        pl.color = Color.Red;
        pl.transform.position = new Vector3(r.nextInt(-dat,dat),r.nextInt(-dat,dat), r.nextInt(-dat,dat));
        pl.Intesity = 5;
        LightObjects.PointLights.AddLight(pl);

        PointLight pl2 = new PointLight();
        pl2.transform.position = new Vector3(r.nextInt(-dat,dat), r.nextInt(-dat,dat),r.nextInt(-dat,dat));
        pl2.color = Color.Blue;
        pl2.Intesity = 5;
        LightObjects.PointLights.AddLight(pl2);

        PointLight pl3 = new PointLight();
        pl3.transform.position = new Vector3(r.nextInt(-dat,dat), r.nextInt(-dat,dat),r.nextInt(-dat,dat));
        pl3.color = Color.Green;
        pl3.Intesity = 5;
        LightObjects.PointLights.AddLight(pl3);

        PointLight pl4 = new PointLight();
        pl4.transform.position = new Vector3(r.nextInt(-dat,dat), r.nextInt(-dat,dat),r.nextInt(-dat,dat));
        pl4.color = new Color(0.5f, 0.3f, 0.4f,1f);
        pl4.Intesity = 5;
        LightObjects.PointLights.AddLight(pl4);
        LightObjects.SceneSun.Intesity =2;

        LightObjects.SetSunPos(new Vector3(0,11,12));
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
