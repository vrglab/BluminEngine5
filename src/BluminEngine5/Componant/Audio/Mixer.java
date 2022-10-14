package BluminEngine5.Componant.Audio;

import BluminEngine5.Componant.Component;
import BluminEngine5.SceneMannagement.SceneManager;
import BluminEngine5.Utils.Debuging.Debug;

import java.util.ArrayList;
import java.util.List;


public class Mixer extends Component {

    private listener Listener;
    private List<MixerProperty> mixers = new ArrayList<>();

    public MixerProperty Get(int id) {
        return mixers.get(id);
    }

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
            Debug.log("Should no longer be null");
            Listener =  SceneManager.GetCurent().GetActiveScene().ActiveCamera.RegisterComponant(new listener());
            Debug.log(Listener);
        }
        var MasterMixer = new MixerProperty();
        MasterMixer.name = "Master";
        mixers.add(0, MasterMixer);
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

