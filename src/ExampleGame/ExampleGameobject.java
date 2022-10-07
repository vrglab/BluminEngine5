package ExampleGame;

import BluminEngine5.Behaviour.BluminBehaviour;
import BluminEngine5.Componant.Rendering.MeshRenderer;
import BluminEngine5.Rendering.Master.Model;
import BluminEngine5.Utils.Debuging.Debug;
import BluminEngine5.Utils.Math.Vector3;

public class ExampleGameobject extends BluminBehaviour {

    public Model model = new Model();

    MeshRenderer mr;

    String sdd = null;


    public ExampleGameobject() {

    }
    public ExampleGameobject(String sd) {
      sdd = sd;
    }

    @Override
    public void Update() {
        if (sdd == null) {
            transform.rotation = new Vector3(transform.rotation.x, transform.rotation.y += 1, transform.rotation.z);

        }
    }

    @Override
    public void OnRender() {

    }
    @Override
    public void Init() {


        if (sdd == null) {
            model.setMesh(1,3);
            mr = new MeshRenderer(model);
            RegisterComponant(mr, this);
            model.getMesh().getMaterial().Shine = 12f;
            model.getMesh().getMaterial().reflection= 1f;

        } else{
            model.setMesh(0,3);
            mr = new MeshRenderer(model);
            RegisterComponant(mr, this);
            transform.position = new Vector3(6,0,9);
            transform.scale = new Vector3(0.5f,0.5f,0.5f);
            model.getMesh().getMaterial().reflection = 0;
            model.getMesh().getMaterial().Shine = 0;
        }
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
