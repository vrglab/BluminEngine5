package ExampleGame;

import BluminEngine5.Application;
import BluminEngine5.Behaviour.BluminBehaviour;
import BluminEngine5.Componant.Audio.Source;
import BluminEngine5.Componant.Physics.MeshColider;
import BluminEngine5.Componant.Rendering.MeshRenderer;
import BluminEngine5.Rendering.Master.Mesh;
import BluminEngine5.SceneMannagement.SceneManager;
import BluminEngine5.Utils.Debuging.Debug;
import BluminEngine5.Utils.ObjLoader;

public class ExampleGameobject extends BluminBehaviour {

    public Mesh mesh;

    MeshRenderer mr;


    Source s;

    public ExampleGameobject() {

    }

    @Override
    public void Update() {
        if(s.IsPlaying()) {
            Debug.log("audio should be playing");
        }else{
            s.Play();
        }
    }

    @Override
    public void OnRender() {

    }
    @Override
    public void Init() {
        mesh =  Application.getResourceManager().GetMesh(1,3);
        mr = new MeshRenderer(mesh);
        s = new Source(2,4);
        RegisterComponant(mr, this);
        RegisterComponant(s, this);
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
