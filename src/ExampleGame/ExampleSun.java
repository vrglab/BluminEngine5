package ExampleGame;

import BluminEngine5.Application;
import BluminEngine5.Behaviour.BluminBehaviour;
import BluminEngine5.Componant.Rendering.MeshRenderer;
import BluminEngine5.Rendering.Lighting.Sun;
import BluminEngine5.Rendering.Master.Mesh;
import BluminEngine5.Utils.Debuging.Debug;
import BluminEngine5.Utils.Math.Vector3;
import BluminEngine5.Utils.ObjLoader;

public class ExampleSun extends BluminBehaviour {

    public Mesh mesh;
    MeshRenderer mr;
    public Sun SunComponant = new Sun();


    public ExampleSun() {
        RegisterComponant(SunComponant, this);

    }

    @Override
    public void Update() {
        if(transform.position.z <= -10) {
            transform.position.z = 16;
        } else{
            transform.position.z -= 0.01f;
        }
    }

    @Override
    public void OnRender() {
    }

    @Override
    public void Init() {
        mesh = Application.getResourceManager().GetMesh(0,3);
        mr = new MeshRenderer(mesh);
        RegisterComponant(mr, this);
        transform.scale = new Vector3(0.5f,0.5f,0.5f);
        transform.position.y = 1;
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
