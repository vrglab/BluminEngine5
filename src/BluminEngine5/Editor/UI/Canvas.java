package BluminEngine5.Editor.UI;
import BluminEngine5.Application;
import BluminEngine5.Behaviour.BluminBehaviour;
import BluminEngine5.Editor.EditorUiObject;
import BluminEngine5.Rendering.UI.Obj.UiObject;
import imgui.ImGui;
import imgui.gl3.ImGuiImplGl3;
import imgui.glfw.ImGuiImplGlfw;

import java.util.ArrayList;
import java.util.List;


public class Canvas extends BluminBehaviour {
    private List<EditorUiObject> UiObjects = new ArrayList<>();
    ImGuiImplGl3 s = new ImGuiImplGl3();
    ImGuiImplGlfw a = new ImGuiImplGlfw();

    @Override
    public void Update() {

    }

    @Override
    public void OnRender() {
        a.newFrame();
        ImGui.newFrame();
        for (EditorUiObject dat: UiObjects) {
            dat.OnUiRender();
        }
        ImGui.render();
        s.renderDrawData(ImGui.getDrawData());
    }

    @Override
    public void Init() {
        ImGui.createContext();
        a.init(Application.display.getWindow(), true);
        s.init("#version 460");
    }

    @Override
    public void PreInit() {

    }

    @Override
    public void OnExit() {
        a.dispose();
        s.dispose();
    }

    @Override
    public void SceneLoad() {

    }

    @Override
    public void Destroy() {

    }

    public void RegisterUiObj(EditorUiObject obj) {
        UiObjects.add(obj);
    }

    public void UnegisterUiObj(EditorUiObject obj) {
        if(UiObjects.contains(obj))
            UiObjects.add(obj);
    }
}
