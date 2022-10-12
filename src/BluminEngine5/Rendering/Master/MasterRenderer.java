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
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import static org.lwjgl.opengl.GL11.*;

public class MasterRenderer {
    public Action<IAction> OnRender = new Action<>();

    Shader skyBoxShader = null;
    public void Render() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glClearColor(1,0.5f,0.2f,1);
        OnRender.Invoke();

        CubeMap skybox = SceneManager.GetCurent().GetActiveScene().SkyColor;

        if(skyBoxShader == null) {
            skyBoxShader = Application.getResourceManager().GetShader("Res/Shaders/Default/Cubemap/DefaultGameShader.json");
            skyBoxShader.Creat();
            skybox.mesh.Creat();
        }
        skyBoxShader.Run();

        Matrix view = Matrix.view(SceneManager.GetCurent().GetActiveScene().ActiveCamera.transform.position,SceneManager.GetCurent().GetActiveScene().ActiveCamera.transform.rotation);
        view.Set(3,0,0);
        view.Set(3,1,0);
        view.Set(3,2,0);

        skyBoxShader.SetUniform("ProjectionMatrix", SceneManager.GetCurent().GetActiveScene().ActiveCamera.getProjectionMatrix());
        skyBoxShader.SetUniform("ViewMatrix", view);

        GL30.glBindVertexArray(skybox.mesh.getVAO());
        GL20.glEnableVertexAttribArray(0);
        GL13.glActiveTexture(GL13.GL_TEXTURE4);
        GL11.glBindTexture(GL13.GL_TEXTURE_CUBE_MAP, skybox.getID());
        GL11.glDrawArrays(GL_TRIANGLES, 0, skybox.mesh.getVertecies().length);
        GL20.glDisableVertexAttribArray(0);
        GL30.glBindVertexArray(0);
        skyBoxShader.Stop();
    }
}
