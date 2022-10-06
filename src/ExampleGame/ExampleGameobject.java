package ExampleGame;

import BluminEngine5.Behaviour.BluminBehaviour;
import BluminEngine5.Componant.Rendering.MeshRenderer;
import BluminEngine5.Rendering.Master.Model;
import BluminEngine5.Utils.Debuging.Debug;
import BluminEngine5.Utils.Math.Vector3;

public class ExampleGameobject extends BluminBehaviour {

    public Model model = new Model();

    MeshRenderer mr;



    public ExampleGameobject() {

    }

    @Override
    public void Update() {

        transform.rotation = new Vector3(transform.rotation.x, transform.rotation.y += 1, transform.rotation.z);
    }

    @Override
    public void OnRender() {

    }
    @Override
    public void Init() {
        model.setMesh(1,3);
        mr = new MeshRenderer(model);
        RegisterComponant(mr, this);
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
