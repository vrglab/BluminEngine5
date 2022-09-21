package BluminEngine5.Rendering.UI;
import BluminEngine5.Behaviour.BluminBehaviour;
import BluminEngine5.Rendering.UI.Obj.UiObject;
import BluminEngine5.Utils.Debuging.Debug;
import BluminEngine5.Utils.Math.Matrix;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import java.util.ArrayList;
import java.util.List;


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
            GL20.glEnableVertexAttribArray(0);
            comp.shader.Run();
            comp.OnUiRender();
            comp.shader.SetUniform("transformationMatrix", Matrix.transform(comp.transform));
            GL11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 0, comp.mesh.getVertecies().length);
            comp.shader.Stop();
            GL20.glDisableVertexAttribArray(0);
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
