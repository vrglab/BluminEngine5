package BluminEngine5.Componant.Audio;

import BluminEngine5.Utils.Debuging.Debug;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ShortBuffer;
import java.util.Locale;

public class WaveData {

    public final ByteBuffer data;
    public final int format;
    public final int samplerate;

    private WaveData(ByteBuffer data, int format, int samplerate) {
        this.data = data;
        this.format = format;
        this.samplerate = samplerate;
    }

    public static WaveData create(InputStream is) {
        try {
            byte[] wavData = new byte[is.available()];
            ByteBuffer dest = ByteBuffer.allocateDirect(wavData.length);
            dest.put(wavData);
            dest.flip();
            InputStream bufferedIn = new BufferedInputStream(is);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(bufferedIn);
            AudioFormat audioFormat = audioInputStream.getFormat();
            is.close();
            return new WaveData(dest,  audioFormat.getChannels(), (int)audioFormat.getSampleRate());
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
