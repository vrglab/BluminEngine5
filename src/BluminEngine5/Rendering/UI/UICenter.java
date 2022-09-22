package BluminEngine5.Rendering.UI;

import BluminEngine5.Utils.Math.Vector2;

import static java.sql.Types.NULL;
import static org.lwjgl.opengl.GL11.GL_REPEAT;
import static org.lwjgl.opengl.GL12.GL_CLAMP_TO_EDGE;
import static org.lwjgl.opengl.GL13.GL_CLAMP_TO_BORDER;
import static org.lwjgl.opengl.GL14.GL_MIRRORED_REPEAT;

public enum UICenter {
    Center("cent")
    ;


    String value;
    UICenter(String data) {
        value = data;
    }

    public Vector2 GetValue() {
        switch(value) {
            case "cent":
                return new Vector2(0.9f,1.51147f);
        }
        return new Vector2(0.9f,1.51147f);
    }
}
