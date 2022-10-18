package ExampleGame;

import BluminEngine5.Audio.Legacy.AudioSource;
import BluminEngine5.Behaviour.BluminBehaviour;
import BluminEngine5.Componant.Rendering.MeshRenderer;
import BluminEngine5.Rendering.Master.Material;
import BluminEngine5.Rendering.Master.Model;
import BluminEngine5.Utils.Debuging.Debug;

public class ExampleGameobject extends BluminBehaviour {

    public Model model = new Model();

    MeshRenderer mr;

    String sdd = null;

    AudioSource s;


    public ExampleGameobject() {

    }
    public ExampleGameobject(String sd) {
      sdd = sd;
    }

    boolean bs = false;

    @Override
    public void Update() {
        if(!s.isPlaying()) {
            s.Play();
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
            model.getMaterial().Shine = 1;
            model.getMaterial().reflection = 0.4f;
            try {
                model.SaveToFile("Cube");
            } catch (Exception e) {
                Debug.logException(e);
            }
            mr = new MeshRenderer(model);
            RegisterComponant(mr);

        } else{
            model = new Model();
            model.setMesh(1,1);
            mr = new MeshRenderer(model);
            RegisterComponant(mr);
        }
        s = new AudioSource(0,3);
        //RegisterComponant(s);
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
