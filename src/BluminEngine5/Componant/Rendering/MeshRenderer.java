package BluminEngine5.Componant.Rendering;

import BluminEngine5.Application;
import BluminEngine5.Componant.IComponent;
import BluminEngine5.Componant.Rendering.Lighting.BaseLight;
import BluminEngine5.Rendering.Lighting.PointLight;
import BluminEngine5.Rendering.Master.Mesh;
import BluminEngine5.Rendering.Master.Model;
import BluminEngine5.Rendering.Shaders.Shader;
import BluminEngine5.SceneMannagement.SceneManager;
import BluminEngine5.Utils.Debuging.Debug;
import BluminEngine5.Utils.Math.Matrix;
import BluminEngine5.Utils.Math.Vector3;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL30;

import static org.lwjgl.opengl.GL11.*;

public class MeshRenderer extends IComponent {

    public Mesh mesh;
    private Shader shader;

    public MeshRenderer(Model model) {
        this.mesh = model.getMesh();
        shader = Application.getResourceManager().GetShader(Application.getMetadata().ResourceFolder + "/Shaders/Default/DefaultGameShader.json");
    }

    public MeshRenderer(Model model, Shader shader) {
        this.mesh = model.getMesh();
        this.shader = shader;
    }
    public MeshRenderer(Model model, String shader) {
        this.mesh = model.getMesh();
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
        shader.Run();
        shader.SetUniform("transform", Matrix.transform(Parent.transform));
        shader.SetUniform("ProjectionMatrix", Application.display.getProjectionMatrix());
        shader.SetUniform("ViewMatrix", Matrix.view(SceneManager.GetCurent().GetActiveScene().ActiveCamera.transform.position,SceneManager.GetCurent().GetActiveScene().ActiveCamera.transform.rotation));
        shader.SetUniform("viewPos", SceneManager.GetCurent().GetActiveScene().ActiveCamera.transform.position);
        shader.SetUniform("material.ambient", mesh.getMaterial().Ambient);
        shader.SetUniform("material.shininess", mesh.getMaterial().Shine);
        shader.SetUniform("material.reflectivenes", mesh.getMaterial().reflection);


        shader.SetUniform("levelLightData.sunlight.intensity", SceneManager.GetCurent().GetActiveScene().LightObjects.SceneSun.Intesity);
        shader.SetUniform("levelLightData.sunlight.position", SceneManager.GetCurent().GetActiveScene().LightObjects.SceneSun.transform.position);
        shader.SetUniform("levelLightData.sunlight.color", SceneManager.GetCurent().GetActiveScene().LightObjects.SceneSun.color);

        shader.SetUniform("pointLightsIntheLevel", SceneManager.GetCurent().GetActiveScene().LightObjects.PointLights.getAmnt());
        for (int i = 0; i < SceneManager.GetCurent().GetActiveScene().LightObjects.PointLights.getAmnt(); i++) {
            shader.SetUniform("levelLightData.pointlights" + "[" + i + "]" +".position", SceneManager.GetCurent().GetActiveScene().LightObjects.PointLights.GetLight(i).transform.position);
            shader.SetUniform("levelLightData.pointlights" + "[" + i + "]" +".intensity", SceneManager.GetCurent().GetActiveScene().LightObjects.PointLights.GetLight(i).Intesity);
            shader.SetUniform("levelLightData.pointlights" + "[" + i + "]" +".color", SceneManager.GetCurent().GetActiveScene().LightObjects.PointLights.GetLight(i).color);
            shader.SetUniform("levelLightData.pointlights" + "[" + i + "]" +".attenuation", SceneManager.GetCurent().GetActiveScene().LightObjects.PointLights.GetLight(i).attenuation);
        }

        //Set the Textures
        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL13.glBindTexture(GL13.GL_TEXTURE_2D, mesh.getMaterial().getTexture().getTextureId());
        shader.SetUniform("material.Texture",  0);

        GL13.glActiveTexture(GL13.GL_TEXTURE1);
        GL13.glBindTexture(GL13.GL_TEXTURE_2D, mesh.getMaterial().getDefuseMap().getTextureId());
        shader.SetUniform("material.diffuse",  1);

        GL13.glActiveTexture(GL13.GL_TEXTURE2);
        GL13.glBindTexture(GL13.GL_TEXTURE_2D, mesh.getMaterial().getSpecularMap().getTextureId());
        shader.SetUniform("material.specular", 2);

        GL13.glActiveTexture(GL13.GL_TEXTURE3);
        GL13.glBindTexture(GL13.GL_TEXTURE_2D, mesh.getMaterial().getSpecularMap().getTextureId());
        shader.SetUniform("material.specular", 3);

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
