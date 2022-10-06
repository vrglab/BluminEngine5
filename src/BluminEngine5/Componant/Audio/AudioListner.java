package BluminEngine5.Componant.Audio;

import BluminEngine5.Componant.IComponent;
import BluminEngine5.Utils.Debuging.Debug;
import BluminEngine5.Utils.Utils;
import com.sun.jdi.connect.spi.TransportService;
import org.lwjgl.openal.AL;
import org.lwjgl.openal.ALC;
import org.lwjgl.openal.ALCCapabilities;
import org.lwjgl.openal.ALCapabilities;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.openal.AL10.*;
import static org.lwjgl.openal.ALC10.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class AudioListner extends IComponent {

    private long device;
    private long context;

    public AudioListner() {
        try{
            String def = alcGetString(0, ALC_DEFAULT_DEVICE_SPECIFIER);
            device = alcOpenDevice(def);
            if (device == NULL) {
                Utils.CrashApp(-20, "Failed to make audio Listener device");
            }
            int[] atribs = {0};

            this.context = alcCreateContext(device, atribs);
            if (context == NULL) {
                Utils.CrashApp(-21, "Failed to make audio Listener context");
            }
            alcMakeContextCurrent(context);
            ALCCapabilities deviceCaps = ALC.createCapabilities(device);
            ALCapabilities capaby = AL.createCapabilities(deviceCaps);

            if(!capaby.OpenAL10) {
                Utils.CrashApp(-26, "OpenAL not supported on this device");
            }

            Debug.log("Audio Listener Initialized");

        } catch (Exception e) {
            Utils.CrashApp(-22, "Failed to load audio Listener");
        }
    }

    @Override
    public void Update() {
        alListener3f(AL_POSITION, Parent.transform.position.x, Parent.transform.position.y, Parent.transform.position.z);
        alListener3f(AL_VELOCITY, 0, 0, 0);
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
        alcCloseDevice(device);
        alcDestroyContext(context);
    }
}
