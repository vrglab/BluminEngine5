package BluminEngine5.Componant.Audio;

import BluminEngine5.Application;
import BluminEngine5.Componant.Component;
import BluminEngine5.Utils.Debuging.Debug;
import BluminEngine5.Utils.Utils;
import org.lwjgl.openal.AL10;

import java.io.File;
import java.io.InputStream;

import static org.lwjgl.openal.AL10.*;

public class AudioSource extends Component {

    private WaveData AudioFile;
    private MixerProperty mp;

    int id, buffer;

    public AudioSource(int file, int archive) {
        try {
            File fil = Application.getResourceManager().LoadIntoTempFile(Application.getResourceManager().archive.GeFileFromArchive(file ,archive));
            InputStream istream = Utils.LoadFileAsStream(fil.getAbsolutePath());
            AudioFile = WaveData.create(istream);
            istream.close();
            fil.delete();
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

    }

    @Override
    public void OnRender() {

    }

    @Override
    public void Init() {
        id = AL10.alGenSources();
        buffer = AL10.alGenBuffers();
        alBufferData(buffer, AL_FORMAT_STEREO16, AudioFile.data.asIntBuffer(), AudioFile.samplerate);
        AL10.alSourcei(id, AL_BUFFER, buffer);
        AL10.alSourcef(id, AL_GAIN, 0.4f);
        AL10.alSourcei(id, AL_POSITION, 0);
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
        alDeleteBuffers(buffer);
    }

    public void Play() {
        Debug.log("Should start to play");
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
