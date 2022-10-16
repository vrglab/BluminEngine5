package BluminEngine5.Audio.Legacy;

import BluminEngine5.Application;
import BluminEngine5.Componant.Component;
import BluminEngine5.Utils.Debuging.Debug;
import org.lwjgl.openal.AL10;

import static org.lwjgl.openal.AL10.*;

public class AudioSource extends Component {

    private AudioFile Audiofile;
    private MixerProperty mp;

    int id, buffer;

    public AudioSource(int file, int archive) {
        try {
            Audiofile = Application.getResourceManager().GetAudio(file, archive);
            if(Mixer.instance == null) {
                Debug.logError("Mixer is required in the scene for audio playing");
                return;
            } else {
                mp = Mixer.instance.Get(0);
            }
        } catch (Exception e) {
            Debug.logException(e);
        }
    }

    public void setMixerProperty(MixerProperty mp) {
        this.mp = mp;
    }

    @Override
    public void Update() {
        AL10.alSource3f(id, AL_POSITION, Parent.transform.position.x,Parent.transform.position.y,Parent.transform.position.z);
        SetVolume(mp.Volume);
    }

    @Override
    public void OnRender() {

    }

    @Override
    public void Init() {
        id = AL10.alGenSources();
        buffer = AL10.alGenBuffers();
        alBufferData(buffer, AL_FORMAT_STEREO16, Audiofile.data, Audiofile.samplerate);
        AL10.alSourcei(id, AL_BUFFER, buffer);
        SetVolume(0.4f);
    }


    public void SetVolume(float volume) {

        AL10.alSourcef(id, AL_GAIN, volume);
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
        Stop();
        alDeleteBuffers(buffer);
    }

    public void Play() {
        alSourcePlay(id);
    }

    public void Pause() {
        alSourcePause(id);
    }

    public void Stop() {
        alSourceStop(id);
    }

    public boolean isPlaying() {
        return alGetSourcei(id, AL_SOURCE_STATE) == AL_PLAYING;
    }
}
