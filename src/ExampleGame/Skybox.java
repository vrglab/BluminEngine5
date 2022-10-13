package ExampleGame;

import BluminEngine5.Application;
import BluminEngine5.Behaviour.BluminBehaviour;
import BluminEngine5.Componant.Rendering.CubeMapRenderer;
import BluminEngine5.Rendering.Master.Mesh;
import BluminEngine5.Utils.Debuging.Debug;
import BluminEngine5.Utils.Utils;
import jdk.jshell.execution.Util;

public class Skybox extends BluminBehaviour {

    CubeMapRenderer cmr;


    @Override
    public void Update() {

    }

    @Override
    public void OnRender() {

    }

    @Override
    public void Init() {
        Debug.log("What the fuck " + parent.name);
        cmr = new CubeMapRenderer(new Mesh(Utils.CubeAsVertex3D(500)),
                Utils.CreateCubeMap(),
                Application.getResourceManager().GetShader("Res/Shaders/Default/Cubemap/DefaultGameShader.json"));
        RegisterComponant(cmr, this);
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
}
