package BluminEngine5.Rendering.Master;

import BluminEngine5.Application;
import BluminEngine5.Rendering.Color;
import BluminEngine5.Rendering.CubeMap;
import BluminEngine5.Rendering.PostProcessing.Effect;
import BluminEngine5.Rendering.Shaders.Shader;
import BluminEngine5.SceneMannagement.SceneManager;
import BluminEngine5.Utils.Debuging.Debug;
import BluminEngine5.Utils.EventSystem.Action;
import BluminEngine5.Utils.EventSystem.IAction;
import BluminEngine5.Utils.Math.Matrix;
import org.lwjgl.opengl.GL30;

import static org.lwjgl.opengl.GL11.*;

public class MasterRenderer {
    public Action<IAction> OnRender = new Action<>();

    Shader skyBoxShader;
    public void Render() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glClearColor(1,0.5f,0.2f,1);
       CubeMap skybox = SceneManager.GetCurent().GetActiveScene().SkyColor;

        if(skyBoxShader == null) {
            skyBoxShader = Application.getResourceManager().GetShader("Res/Shaders/Default/Mesh/DefaultGameShader.json");
            skyBoxShader.Creat();
            skybox.mesh.Creat();
        }


        skyBoxShader.Run();
        skyBoxShader.SetUniform("ProjectionMatrix", SceneManager.GetCurent().GetActiveScene().ActiveCamera.getProjectionMatrix());
        skyBoxShader.SetUniform("ViewMatrix", Matrix.view(SceneManager.GetCurent().GetActiveScene().ActiveCamera.transform.position,SceneManager.GetCurent().GetActiveScene().ActiveCamera.transform.rotation));

        GL30.glBindVertexArray(skybox.mesh.getVAO());

        skyBoxShader.Stop();
        OnRender.Invoke();
    }
}
