package BluminEngine5.Componant.Rendering;

import BluminEngine5.Application;
import BluminEngine5.Componant.IComponent;
import BluminEngine5.Rendering.ThreeD.Master.Mesh;
import BluminEngine5.Rendering.ThreeD.Shaders.Shader;
import BluminEngine5.SceneMannagement.SceneManager;
import BluminEngine5.Utils.Math.Matrix;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL30;

import static org.lwjgl.opengl.GL11.*;

public class MeshRenderer extends IComponent {

    public Mesh mesh;
    private Shader shader;

    public MeshRenderer(Mesh mesh) {
        this.mesh = mesh;
        shader = new Shader("Res/Shaders/Default/DefaultGameShader.json");
    }

    public MeshRenderer(Mesh mesh, Shader shader) {
        this.mesh = mesh;
        this.shader = shader;
    }
    public MeshRenderer(Mesh mesh, String shader) {
        this.mesh = mesh;
        this.shader = new Shader(shader);
    }


    @Override
    public void Update() {

    }

    @Override
    public void OnRender() {
        glDepthFunc(GL_LEQUAL);
        glEnable(GL_DEPTH_TEST);
        GL30.glBindVertexArray(mesh.getVAO());
        GL30.glEnableVertexAttribArray(0);
        GL30.glEnableVertexAttribArray(1);
        GL30.glEnableVertexAttribArray(2);
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, mesh.getIBO());
        if(mesh.getMaterial().getColor().GetA() < 1) {
            glEnable(GL_BLEND);
            glBlendFunc(GL_ONE, GL_ONE_MINUS_SRC_ALPHA);
        }
        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL13.glBindTexture(GL13.GL_TEXTURE_2D, mesh.getMaterial().getTexture().getTextureId());
        shader.Run();
        shader.SetUniform("transform", Matrix.transform(Parent.transform));
        shader.SetUniform("ProjectionMatrix", Application.display.getProjectionMatrix());
        shader.SetUniform("ViewMatrix", Matrix.view(SceneManager.GetCurent().GetActiveScene().ActiveCamera.transform.position,SceneManager.GetCurent().GetActiveScene().ActiveCamera.transform.rotation));
        GL11.glDrawElements(GL11.GL_TRIANGLES, mesh.getIndecies().length, GL11.GL_UNSIGNED_INT, 0);
        shader.Stop();
        if(mesh.getMaterial().getColor().GetA() < 1) {
            glDisable(GL_BLEND);
        }
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
        GL30.glDisableVertexAttribArray(0);
        GL30.glDisableVertexAttribArray(1);
        GL30.glDisableVertexAttribArray(2);
        GL30.glBindVertexArray(0);
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
        mesh.Destroy();
        shader.Delete();
    }
}
