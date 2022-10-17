package BluminEngine5.Audio.Fmod;

import BluminEngine5.Componant.Component;
import BluminEngine5.Utils.Annotations.Obsolete;
import org.fmod.FMODLoader;
import org.fmod.studio.Bank;
import org.fmod.studio.FmodStudioSystem;

import static org.fmod.jni.FMODConstants.FMOD_INIT_NORMAL;
import static org.fmod.jni.FMODConstants.FMOD_STUDIO_INIT_NORMAL;


@Obsolete @Deprecated
public class Fmod extends Component {

    FmodStudioSystem studioSystem;

    @Override
    public void Update() {

    }

    @Override
    public void OnRender() {

    }

    @Override
    public void Init() {
        FMODLoader.loadNatives();
        studioSystem = FmodStudioSystem.create();
        studioSystem.initialize(32, FMOD_STUDIO_INIT_NORMAL, FMOD_INIT_NORMAL, null);
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

    public Bank GetBank(String path) {
        return studioSystem.getBank(path);
    }
}
