package BluminEngine5.Rendering.Master;

import BluminEngine5.Application;
import BluminEngine5.Rendering.Color;
import BluminEngine5.Rendering.PostProcessing.Effect;
import BluminEngine5.SceneMannagement.SceneManager;
import BluminEngine5.Utils.EventSystem.Action;
import BluminEngine5.Utils.EventSystem.IAction;

import static org.lwjgl.opengl.GL11.*;

public class MasterRenderer {
    public Action<IAction> OnRender = new Action<>();

    public void Render() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        if(SceneManager.GetCurent().GetActiveScene().SkyColor.Created == false){
            SceneManager.GetCurent().GetActiveScene().SkyColor.Creat();
        }
        OnRender.Invoke();
    }
}
