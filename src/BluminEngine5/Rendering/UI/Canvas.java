package BluminEngine5.Rendering.UI;
import BluminEngine5.Application;
import BluminEngine5.Behaviour.BluminBehaviour;
import BluminEngine5.SceneMannagement.SceneManager;
import BluminEngine5.Utils.Math.Matrix;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL30;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjglx.util.glu.GLU.gluOrtho2D;


public class Canvas extends BluminBehaviour {

    private List<UiObject> UiObjects = new ArrayList<>();

    @Override
    public void Update() {
        for (UiObject comp: UiObjects) {
            comp.Update();
        }
    }

    @Override
    public void OnRender() {
        for (UiObject comp: UiObjects) {

            GL30.glBindVertexArray(comp.mesh.getVAO());
            GL30.glEnableVertexAttribArray(0);
            GL30.glEnableVertexAttribArray(1);
            GL30.glEnableVertexAttribArray(2);
            GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, comp.mesh.getIBO());
            GL13.glActiveTexture(GL13.GL_TEXTURE0);
            GL13.glBindTexture(GL13.GL_TEXTURE_2D,comp.mesh.getMaterial().getTexture().getTextureId());
            comp.shader.Run();
            comp.shader.SetUniform("transform", Matrix.transform(comp.transform));
            comp.OnUiRender();
            GL11.glDrawElements(GL11.GL_TRIANGLES, comp.mesh.getIndecies().length, GL11.GL_UNSIGNED_INT, 0);
            comp.shader.Stop();
            comp.OnUiRender();
            GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
            GL30.glDisableVertexAttribArray(0);
            GL30.glDisableVertexAttribArray(1);
            GL30.glDisableVertexAttribArray(2);
            GL30.glBindVertexArray(0);
        }

    }

    @Override
    public void Init() {
        for (UiObject comp: UiObjects) {
            comp.Init();
        }
    }

    @Override
    public void PreInit() {
        for (UiObject comp: UiObjects) {
            comp.PreInit();
        }
    }

    @Override
    public void OnExit() {
        for (UiObject comp: UiObjects) {
            comp.OnExit();
        }
    }

    @Override
    public void SceneLoad() {
        for (UiObject comp: UiObjects) {
            comp.SceneLoad();
        }
    }

    @Override
    public void Destroy() {
        for (UiObject comp: UiObjects) {
            comp.Destroy();
        }
    }

    public void RegisterUiObj(UiObject obj) {
        UiObjects.add(obj);
    }

    public void UnegisterUiObj(UiObject obj) {
        if(UiObjects.contains(obj))
        UiObjects.add(obj);
    }

}
