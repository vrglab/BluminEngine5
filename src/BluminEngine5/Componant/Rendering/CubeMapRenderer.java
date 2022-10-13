package BluminEngine5.Componant.Rendering;

import BluminEngine5.Componant.IComponent;
import BluminEngine5.Rendering.Master.Mesh;
import BluminEngine5.Rendering.Shaders.Shader;
import BluminEngine5.SceneMannagement.SceneManager;
import BluminEngine5.Utils.Math.Matrix;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import static org.lwjgl.opengl.GL11.GL_TRIANGLES;

public class CubeMapRenderer extends IComponent {

    Mesh mesh;
    int cubemap;
    Shader shader;

    public CubeMapRenderer(Mesh mesh, int map, Shader shad) {
        this.mesh = mesh;
        cubemap = map;
        shader = shad;
    }

    @Override
    public void Update() {

    }

    @Override
    public void OnRender() {
        shader.Run();
        Matrix view = Matrix.view(SceneManager.GetCurent().GetActiveScene().ActiveCamera.transform.position,SceneManager.GetCurent().GetActiveScene().ActiveCamera.transform.rotation);
        view.Set(3,0,0);
        view.Set(3,1,0);
        view.Set(3,2,0);

        shader.SetUniform("ProjectionMatrix", SceneManager.GetCurent().GetActiveScene().ActiveCamera.getProjectionMatrix());
        shader.SetUniform("ViewMatrix", view);

        GL30.glBindVertexArray(mesh.getVAO());
        GL20.glEnableVertexAttribArray(0);
        GL13.glActiveTexture(GL13.GL_TEXTURE4);
        GL11.glBindTexture(GL13.GL_TEXTURE_CUBE_MAP, cubemap);
        GL11.glDrawArrays(GL_TRIANGLES, 0, mesh.getVertecies().length);
        GL20.glDisableVertexAttribArray(0);
        GL30.glBindVertexArray(0);
        shader.Stop();
    }

    @Override
    public void Init() {
        mesh.Creat();
        shader.Creat();
    }

    @Override
    public void PreInit() {

    }

    @Override
    public void OnExit() {

    }

    @Override
    public void SceneLoad() {

    }

    @Override
    public void Destroy() {

    }
}
