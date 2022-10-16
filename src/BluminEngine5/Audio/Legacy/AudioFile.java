package BluminEngine5.Audio.Legacy;

import BluminEngine5.Utils.Annotations.MustCreate;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;



/**
 * Class used to handel Audio files
 * @author Vrglab
 */
@MustCreate
public class AudioFile {

    public final ByteBuffer data;
    public final int format;
    public final int samplerate;

    private AudioFile(ByteBuffer data, int format, int samplerate) {
        this.data = data;
        this.format = format;
        this.samplerate = samplerate;
    }

    public static AudioFile create(InputStream is) {
        try {
            byte[] wavData = new byte[is.available()];
            ByteBuffer dest = ByteBuffer.allocateDirect(wavData.length);
            dest.put(wavData);
            dest.flip();
            InputStream bufferedIn = new BufferedInputStream(is);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(bufferedIn);
            AudioFormat audioFormat = audioInputStream.getFormat();
            is.close();
            return new AudioFile(dest,  audioFormat.getChannels(), (int)audioFormat.getSampleRate());
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public void dispose() {
        this.data.clear();
    }

    private static AudioInputStream convertToPCM(AudioInputStream audioInputStream)
    {
        AudioFormat m_format = audioInputStream.getFormat();

        if ((m_format.getEncoding() != AudioFormat.Encoding.PCM_SIGNED) &&
                (m_format.getEncoding() != AudioFormat.Encoding.PCM_UNSIGNED))
        {
            AudioFormat targetFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
                    m_format.getSampleRate(), 16,
                    m_format.getChannels(), m_format.getChannels() * 2,
                    m_format.getSampleRate(), m_format.isBigEndian());
            audioInputStream = AudioSystem.getAudioInputStream(targetFormat, audioInputStream);
        }

        return audioInputStream;
    }
}
