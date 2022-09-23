package ExampleGame;

import BluminEngine5.Behaviour.BluminBehaviour;
import BluminEngine5.Componant.Physics.MeshColider;
import BluminEngine5.Componant.Rendering.MeshRenderer;
import BluminEngine5.Rendering.Master.Mesh;
import BluminEngine5.Utils.Debuging.Debug;
import BluminEngine5.Utils.ObjLoader;

public class ExampleGameobject extends BluminBehaviour {

    public Mesh mesh = ObjLoader.LoadFile("Res/Models/dragon.obj");
    MeshRenderer mr = new MeshRenderer(mesh);

    MeshColider collider = new MeshColider(mr);

    BluminBehaviour sun;

    public ExampleGameobject() {
        RegisterComponant(mr, this);
        RegisterComponant(collider, this);
    }

    @Override
    public void Update() {

    }

    @Override
    public void OnRender() {

    }

    @Override
    public void Init() {
        sun = parent.getGameObject(new ExampleSun());
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
