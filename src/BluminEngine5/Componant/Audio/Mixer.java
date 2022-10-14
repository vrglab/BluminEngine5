package BluminEngine5.Componant.Audio;

import BluminEngine5.Componant.Component;
import BluminEngine5.SceneMannagement.SceneManager;
import BluminEngine5.Utils.Debuging.Debug;


public class Mixer extends Component {

    private listener Listener;
    //sources

    @Override
    public void Update() {

    }

    @Override
    public void OnRender() {

    }

    @Override
    public void Init() {
        Listener = SceneManager.GetCurent().GetActiveScene().ActiveCamera.getComponant(listener.class);

        if(Listener == null) {
            Listener =  SceneManager.GetCurent().GetActiveScene().ActiveCamera.RegisterComponant(new listener());
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
