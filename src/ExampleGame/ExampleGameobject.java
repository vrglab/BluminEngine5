package ExampleGame;

import BluminEngine5.Application;
import BluminEngine5.Behaviour.BluminBehaviour;
import BluminEngine5.Componant.Audio.Source;
import BluminEngine5.Componant.Rendering.MeshRenderer;
import BluminEngine5.Rendering.Master.Mesh;
import BluminEngine5.Rendering.Master.Model;
import BluminEngine5.SceneMannagement.SceneManager;
import BluminEngine5.Utils.Debuging.Debug;
import BluminEngine5.Utils.Math.Vector3;
import BluminEngine5.Utils.ObjLoader;

public class ExampleGameobject extends BluminBehaviour {

    public Model model = new Model();

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
        transform.rotation = new Vector3(transform.rotation.x, transform.rotation.y += 1, transform.rotation.z);
    }

    @Override
    public void OnRender() {

    }
    @Override
    public void Init() {
        model.setMesh(0,3);
        mr = new MeshRenderer(model);
        s = new Source(2,4);
        RegisterComponant(mr, this);
        RegisterComponant(s, this);
        model.getMesh().getMaterial().Shine = 0.5f;
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
