package BluminEngine5.Rendering;

import BluminEngine5.Application;
import BluminEngine5.Rendering.Master.Mesh;
import BluminEngine5.Rendering.Shaders.Shader;
import BluminEngine5.SceneMannagement.SceneManager;
import BluminEngine5.Utils.Annotations.Obsolete;
import BluminEngine5.Utils.Annotations.PreInitOnly;
import BluminEngine5.Utils.Debuging.Debug;
import BluminEngine5.Utils.Math.Matrix;
import BluminEngine5.Utils.Utils;
import org.lwjgl.opengl.*;

import static org.lwjgl.opengl.GL11.*;

@Obsolete @Deprecated
public class CubeMap {

    private static final int FacesAmnt = 6;
    private int ID = 0;

    public Mesh mesh;

    Shader skyBoxShader = null;
    public CubeMap() {

    }

    public int getID() {
        return ID;
    }
}
