package BluminEngine5.Componant.Audio;

import BluminEngine5.Componant.Component;
import BluminEngine5.Utils.Debuging.Debug;
import org.lwjgl.openal.AL;

import static org.lwjgl.openal.AL10.*;

public class listener extends Component {

    @Override
    public void Update() {
        try {

            alListener3f(AL_POSITION, Parent.transform.position.x, Parent.transform.position.y, Parent.transform.position.z);
            alListener3f(AL_VELOCITY, 0, 0, 0);
        } catch (Exception e) {
            Debug.logException(e);
        }
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
