package BluminEngine5.Rendering.Master;

import BluminEngine5.Rendering.Color;
import BluminEngine5.SceneMannagement.SceneManager;
import BluminEngine5.Utils.EventSystem.Action;
import BluminEngine5.Utils.EventSystem.IAction;

import static org.lwjgl.opengl.GL11.*;

public class MasterRenderer {
    public Action<IAction> OnRender = new Action<>();

    public void Render() {
        Color SkyColor = SceneManager.GetCurent().GetActiveScene().SkyColor;
        glClearColor(SkyColor.GetR(), SkyColor.GetG(), SkyColor.GetB(), SkyColor.getA());
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        OnRender.Invoke();
    }
}
