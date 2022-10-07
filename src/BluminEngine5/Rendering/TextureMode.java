package BluminEngine5.Rendering;
import java.io.Serializable;

import static java.sql.Types.NULL;
import static org.lwjgl.opengl.GL11.GL_REPEAT;
import static org.lwjgl.opengl.GL12.GL_CLAMP_TO_EDGE;
import static org.lwjgl.opengl.GL13.GL_CLAMP_TO_BORDER;
import static org.lwjgl.opengl.GL14.GL_MIRRORED_REPEAT;

public enum TextureMode implements Serializable {
    ClampToBorder("clamp"),
    Mirror("mirror"),
    Repeat("repeat"),
    ClampToEdge("MirReapt");

    String value;
    TextureMode(String data) {
        value = data;
    }

    public int GetValue() {
        switch(value) {
            case "clamp":
                return GL_CLAMP_TO_BORDER;
            case "mirror":
                return GL_MIRRORED_REPEAT;
            case "repeat":
                return GL_REPEAT;
            case "MirReapt":
                return GL_CLAMP_TO_EDGE;
        }
        return NULL;
    }
}
