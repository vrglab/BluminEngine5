package BluminEngine5.Rendering.Master;

import BluminEngine5.Rendering.Shaders.Shader;
import BluminEngine5.Utils.EventSystem.Action;
import BluminEngine5.Utils.EventSystem.IAction;

import static org.lwjgl.opengl.GL11.*;

public class MasterRenderer {
    public Action<IAction> OnRender = new Action<>();

    Shader skyBoxShader = null;
    public void Render() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glClearColor(1,0.5f,0.2f,1);
        OnRender.Invoke();

    }
}
