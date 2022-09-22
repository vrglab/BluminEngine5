package BluminEngine5.Rendering.UI;
import BluminEngine5.Behaviour.BluminBehaviour;
import BluminEngine5.Rendering.UI.Obj.UiObject;
import BluminEngine5.Utils.Debuging.Debug;
import BluminEngine5.Utils.Math.Matrix;
import BluminEngine5.Utils.Math.Vector2;
import BluminEngine5.Utils.Math.Vector3;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL14.*;


public class Canvas extends BluminBehaviour {

    private List<UiObject> UiObjects = new ArrayList<>();

    @Override
    public void Update() {
        for (UiObject comp: UiObjects) {
            comp.ActionData.OnUpdate.Run();
        }
    }

    @Override
    public void OnRender() {
        for (UiObject comp: UiObjects) {
            GL30.glBindVertexArray(comp.mesh.getVAO());
            GL20.glEnableVertexAttribArray(0);
            GL20.glEnableVertexAttribArray(1);
            if(comp.mesh.getMaterial().getColor().GetA() < 1) {
                glEnable(GL_BLEND);
                glBlendEquation(GL_FUNC_ADD);
                glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
                glBlendFuncSeparate(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA, GL_ONE, GL_ZERO);
            }
            comp.shader.Run();
            comp.shader.SetUniform("CenterPos", comp.transform.Center );
            comp.ActionData.OnRender.Run();
            comp.shader.Stop();
            if(comp.mesh.getMaterial().getColor().GetA() < 1) {
                glDisable(GL_BLEND);
            }
            GL20.glDisableVertexAttribArray(1);
            GL20.glDisableVertexAttribArray(0);
            GL30.glBindVertexArray(0);
        }
    }

    @Override
    public void Init() {
        for (UiObject comp: UiObjects) {
            comp.ActionData.OnInit.Run();
        }
    }

    @Override
    public void PreInit() {
        for (UiObject comp: UiObjects) {
            comp.ActionData.OnPreInit.Run();
        }
    }

    @Override
    public void OnExit() {
        for (UiObject comp: UiObjects) {
            comp.ActionData.OnExit.Run();
        }
    }

    @Override
    public void SceneLoad() {
        for (UiObject comp: UiObjects) {
            comp.ActionData.OnSceneLoad.Run();
        }
    }

    @Override
    public void Destroy() {
        for (UiObject comp: UiObjects) {
            comp.ActionData.OnDestroy.Run();
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
