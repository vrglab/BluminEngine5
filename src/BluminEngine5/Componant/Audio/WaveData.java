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
            return create(AudioSystem.getAudioInputStream(is));
        } catch (Exception var2) {
            Debug.logException(var2);
            return null;
        }
    }

    public static WaveData create(AudioInputStream ais) {
        AudioFormat audioformat = ais.getFormat();
        int channels = 0;
        if (audioformat.getChannels() == 1) {
            if (audioformat.getSampleSizeInBits() == 8) {
                channels = 4352;
            } else if (audioformat.getSampleSizeInBits() == 16) {
                channels = 4353;
            } else {
                assert false : "Illegal sample size";
            }
        } else if (audioformat.getChannels() == 2) {
            if (audioformat.getSampleSizeInBits() == 8) {
                channels = 4354;
            } else if (audioformat.getSampleSizeInBits() == 16) {
                channels = 4355;
            } else {
                assert false : "Illegal sample size";
            }
        } else {
            assert false : "Only mono or stereo is supported";
        }

        ByteBuffer buffer = null;

        try {
            int available = ais.available();
            if (available <= 0) {
                available = ais.getFormat().getChannels() * (int)ais.getFrameLength() * ais.getFormat().getSampleSizeInBits() / 8;
            }

            byte[] buf = new byte[ais.available()];

            int read;
            for(int total = 0; (read = ais.read(buf, total, buf.length - total)) != -1 && total < buf.length; total += read) {
            }

            buffer = convertAudioBytes(buf, audioformat.getSampleSizeInBits() == 16, audioformat.isBigEndian() ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
        } catch (IOException var9) {
            return null;
        }

        WaveData wavedata = new WaveData(buffer, channels, (int)audioformat.getSampleRate());

        try {
            ais.close();
        } catch (IOException var8) {
        }

        return wavedata;
    }


    private static ByteBuffer convertAudioBytes(byte[] audio_bytes, boolean two_bytes_data, ByteOrder order) {
        ByteBuffer dest = ByteBuffer.allocateDirect(audio_bytes.length);
        dest.order(ByteOrder.nativeOrder());
        ByteBuffer src = ByteBuffer.wrap(audio_bytes);
        src.order(order);
        if (two_bytes_data) {
            ShortBuffer dest_short = dest.asShortBuffer();
            ShortBuffer src_short = src.asShortBuffer();

            while(src_short.hasRemaining()) {
                dest_short.put(src_short.get());
            }
        } else {
            while(src.hasRemaining()) {
                dest.put(src.get());
            }
        }

        dest.rewind();
        return dest;
    }
    public void dispose() {
        this.data.clear();
    }
}
