package BluminEngine5.Componant.Audio;

import BluminEngine5.Application;
import BluminEngine5.Componant.IComponent;
import BluminEngine5.Utils.Debuging.Debug;
import org.lwjgl.BufferUtils;
import org.lwjgl.openal.AL10;
import org.lwjgl.stb.STBVorbisInfo;
import javax.sound.sampled.*;


import java.nio.IntBuffer;

import static org.lwjgl.openal.AL10.*;
import static org.lwjgl.system.MemoryUtil.memSlice;

public class Source extends IComponent {


    IntBuffer id = BufferUtils.createIntBuffer(1);

    IntBuffer BufferId = BufferUtils.createIntBuffer(1);

    public Vorbis AudioFileToPlay;

    int archiveid, ArchivedFileid;

    public Source(int file, int archive) {
        archiveid = archive;
        ArchivedFileid = file;
    }


    public void Play() {
        alSourcePlay(id.get(0));

    }
    public void Pause() {
        alSourcePause(id.get(0));
    }
    public void Stop() {
        alSourceStop(id.get(0));
    }
    public boolean IsPlaying() {
        return alGetSourcei(id.get(0), AL_SOURCE_STATE) == AL_PLAYING;
    }

    @Override
    public void Update() {

        AL10.alSource3f(id.get(0), AL10.AL_POSITION, Parent.transform.position.x,Parent.transform.position.y, Parent.transform.position.z);
    }

    @Override
    public void OnRender() {

    }

    @Override
    public void Init() {

        // Load wav data into a buffer.
        AL10.alGenBuffers(BufferId);

        if(AL10.alGetError() != AL10.AL_NO_ERROR){

        }


        WaveData waveFile =  Application.getResourceManager().GetWav(ArchivedFileid,archiveid);
        AL10.alBufferData(BufferId.get(0), waveFile.format, waveFile.data, waveFile.samplerate);
        waveFile.dispose();


        AL10.alSourcei(id.get(0), AL10.AL_BUFFER,   BufferId.get(0) );
        AL10.alSourcef(id.get(0), AL10.AL_PITCH,1.0f);
        AL10.alSourcef(id.get(0), AL10.AL_GAIN,1.0f);
        AL10.alSource3f(id.get(0), AL10.AL_POSITION, Parent.transform.position.x,Parent.transform.position.y, Parent.transform.position.z);
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
        alDeleteSources(id.get(0));
    }
}
