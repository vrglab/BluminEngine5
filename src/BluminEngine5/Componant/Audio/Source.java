package BluminEngine5.Componant.Audio;

import BluminEngine5.Application;
import BluminEngine5.Componant.IComponent;
import BluminEngine5.Utils.Debuging.Debug;
import BluminEngine5.Utils.ResourceMannager.Archive.ArchivedFile;
import BluminEngine5.Utils.ResourceMannager.ResourceMannager;
import BluminEngine5.Utils.Utils;
import org.lwjgl.BufferUtils;
import org.lwjgl.openal.AL10;
import org.lwjgl.stb.STBVorbisInfo;

import static org.lwjgl.BufferUtils.createByteBuffer;
import static org.lwjgl.stb.STBVorbis.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.lwjgl.openal.AL10.*;
import static org.lwjgl.system.MemoryUtil.NULL;
import static org.lwjgl.system.MemoryUtil.memSlice;

public class Source extends IComponent {

    private int id;
    private int BufferId;
    String file;
    public Source(String file) {
       this.file=file;
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
    public boolean IsPlaying() {
        return alGetSourcei(id, AL_SOURCE_STATE) == AL_PLAYING;
    }

    @Override
    public void Update() {
        AL10.alSource3f(id,AL_POSITION, Parent.transform.position.x,Parent.transform.position.y, Parent.transform.position.z);
    }

    @Override
    public void OnRender() {

    }

    @Override
    public void Init() {
        try {
            BufferId = alGenBuffers();
            try (STBVorbisInfo info = STBVorbisInfo.malloc()) {

                ArchivedFile file =  Application.getResourceManager().archive.GeFileFromArchive(0,5);
                ShortBuffer pcm = null;
                try {
                  File tempFile = Application.getResourceManager().LoadIntoTempFile(file);
                  pcm = readVorbis(tempFile.getAbsolutePath(), 32 * 1024, info);
                  tempFile.delete();
                } catch (Exception e){

                }




                // Copy to buffer
                alBufferData(BufferId, info.channels() == 1 ? AL_FORMAT_MONO16 : AL_FORMAT_STEREO16, pcm, info.sample_rate());
            }
        } catch(ExceptionInInitializerError e){
            Debug.logError("Source failed to make Buffer");
        }


        id = AL10.alGenSources();

        AL10.alSource3f(id,AL_POSITION, Parent.transform.position.x,Parent.transform.position.y, Parent.transform.position.z);
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
        alDeleteSources(id);
    }


    ShortBuffer readVorbis(String resource, int bufferSize, STBVorbisInfo info) {
        ByteBuffer vorbis;
        try {
            vorbis = ioResourceToByteBuffer(resource, bufferSize);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        IntBuffer error   = BufferUtils.createIntBuffer(1);
        long      decoder = stb_vorbis_open_memory(vorbis, error, null);
        if (decoder == NULL) {
            throw new RuntimeException("Failed to open Ogg Vorbis file. Error: " + error.get(0));
        }

        stb_vorbis_get_info(decoder, info);

        int channels = info.channels();

        ShortBuffer pcm = BufferUtils.createShortBuffer(stb_vorbis_stream_length_in_samples(decoder) * channels);

        stb_vorbis_get_samples_short_interleaved(decoder, channels, pcm);
        stb_vorbis_close(decoder);

        return pcm;
    }

    ByteBuffer ioResourceToByteBuffer(String resource, int bufferSize) throws IOException {
        ByteBuffer buffer;

        Path path = Paths.get(resource);
        if (Files.isReadable(path)) {
            try (SeekableByteChannel fc = Files.newByteChannel(path)) {
                buffer = createByteBuffer((int)fc.size() + 1);
                while (fc.read(buffer) != -1) {
                    ;
                }
            }
        } else {
            try (
                    ReadableByteChannel rbc = Channels.newChannel(Utils.LoadFileAsStream(resource))
            ) {
                buffer = createByteBuffer(bufferSize);

                while (true) {
                    int bytes = rbc.read(buffer);
                    if (bytes == -1) {
                        break;
                    }
                    if (buffer.remaining() == 0) {
                        buffer = resizeBuffer(buffer, buffer.capacity() * 3 / 2); // 50%
                    }
                }
            }
        }

        buffer.flip();
        return memSlice(buffer);
    }

    ByteBuffer resizeBuffer(ByteBuffer buffer, int newCapacity) {
        ByteBuffer newBuffer = BufferUtils.createByteBuffer(newCapacity);
        buffer.flip();
        newBuffer.put(buffer);
        return newBuffer;
    }
}
