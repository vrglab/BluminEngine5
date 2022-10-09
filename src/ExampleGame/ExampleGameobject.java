package ExampleGame;

import BluminEngine5.Behaviour.BluminBehaviour;
import BluminEngine5.Componant.Rendering.MeshRenderer;
import BluminEngine5.Rendering.Master.Material;
import BluminEngine5.Rendering.Master.Model;
import BluminEngine5.Utils.Debuging.Debug;
import BluminEngine5.Utils.Math.Vector3;

import java.io.IOException;

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
            model = new Model();
            model.setMesh(0,1);
            mr = new MeshRenderer(model);
            RegisterComponant(mr, this);
            model.setMaterial(new Material());
            model.getMaterial().Shine = 1f;
            model.getMaterial().reflection= 0.5f;

        } else{
            model = new Model();
            model.setMesh(1,1);
            model.setMaterial(new Material());
            mr = new MeshRenderer(model);
            RegisterComponant(mr, this);
            transform.position = new Vector3(6,0,9);
            transform.scale = new Vector3(0.5f,0.5f,0.5f);
            model.getMaterial().reflection = 0;
            model.getMaterial().Shine = 0;
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
