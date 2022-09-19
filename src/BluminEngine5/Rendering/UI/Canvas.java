package BluminEngine5.Rendering.UI;

import BluminEngine5.Application;
import BluminEngine5.Behaviour.BluminBehaviour;
import BluminEngine5.Behaviour.IUiLogics;
import BluminEngine5.Componant.IComponent;
import BluminEngine5.Utils.EventSystem.Action;
import BluminEngine5.Utils.EventSystem.IAction;
import imgui.ImGui;

import imgui.gl3.ImGuiImplGl3;
import imgui.glfw.ImGuiImplGlfw;
import java.util.ArrayList;
import java.util.List;


public class Canvas extends BluminBehaviour {

    private final ImGuiImplGl3 imGuiImplGl3 =  new ImGuiImplGl3();
    private final ImGuiImplGlfw imGuiImplGlfw =  new ImGuiImplGlfw();
    private List<UiObject> UiObjects = new ArrayList<>();


    @Override
    public void Update() {
        for (UiObject comp: UiObjects) {
            comp.Update();
        }
    }

    @Override
    public void OnRender() {
        imGuiImplGlfw.newFrame();
        ImGui.newFrame();
        for (UiObject comp: UiObjects) {
            comp.OnUiRender();
        }
        ImGui.render();
        imGuiImplGl3.renderDrawData(ImGui.getDrawData());
    }

    @Override
    public void Init() {
        InitImGui();
        imGuiImplGlfw.init(Application.display.getWindow(), true);
        imGuiImplGl3.init("#version 460");
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
        imGuiImplGlfw.dispose();
        imGuiImplGl3.dispose();
        ImGui.destroyContext();
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

    private void InitImGui() {
        ImGui.createContext();
    }

    public void RegisterUiObj(UiObject obj) {
        UiObjects.add(obj);
    }

    public void UnegisterUiObj(UiObject obj) {
        if(UiObjects.contains(obj))
        UiObjects.add(obj);
    }

}
