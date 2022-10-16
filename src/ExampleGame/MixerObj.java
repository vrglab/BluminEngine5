package ExampleGame;

import BluminEngine5.Audio.Legacy.Mixer;
import BluminEngine5.Behaviour.BluminBehaviour;

public class MixerObj extends BluminBehaviour {

    Mixer AudioMixer = new Mixer();

    public MixerObj() {
        RegisterComponant(AudioMixer);
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
