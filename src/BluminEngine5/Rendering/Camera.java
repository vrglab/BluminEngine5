package BluminEngine5.Rendering;


import BluminEngine5.Behaviour.BluminBehaviour;
import BluminEngine5.Componant.Audio.AudioListner;
import BluminEngine5.Utils.Input;
import org.lwjgl.glfw.GLFW;

public class Camera extends BluminBehaviour {

    public AudioListner al = new AudioListner();

    public Camera() {
        transform.position.z = 0;
        RegisterComponant(al, this);
    }

    @Override
    public void Update() {

    }

    @Override
    public void OnRender() {

    }

    @Override
    public void Init() {

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
