package ExampleGame;

import BluminEngine5.Application;
import BluminEngine5.Behaviour.BluminBehaviour;
import BluminEngine5.Componant.Audio.Source;
import BluminEngine5.Componant.Physics.MeshColider;
import BluminEngine5.Componant.Rendering.MeshRenderer;
import BluminEngine5.Rendering.Master.Mesh;
import BluminEngine5.Utils.Debuging.Debug;
import BluminEngine5.Utils.ObjLoader;

public class ExampleGameobject extends BluminBehaviour {

    public Mesh mesh;

    MeshRenderer mr;


    Source s;

    BluminBehaviour sun;

    public ExampleGameobject() {

    }

    @Override
    public void Update() {

    }

    @Override
    public void OnRender() {

    }

    @Override
    public void Init() {
        mesh = ObjLoader.LoadFile(Application.getResourceManager().archive.GeFileFromArchive(1,4));
        mr = new MeshRenderer(mesh);
        RegisterComponant(mr, this);
        sun = parent.getGameObject(new ExampleSun());
        s  = new Source("Res/testing.ogg");
        RegisterComponant(s, this);
        s.Play();
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
