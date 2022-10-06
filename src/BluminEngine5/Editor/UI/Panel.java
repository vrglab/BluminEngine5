package BluminEngine5.Editor.UI;

import BluminEngine5.Editor.EditorUiObject;
import BluminEngine5.Rendering.UI.Obj.UiObject;
import imgui.ImGui;

import java.util.ArrayList;
import java.util.List;

public class Panel extends EditorUiObject {

    private List<EditorUiObject> UiObjects = new ArrayList<>();
    String name = "Default";

    public Panel(String n) {
        name = n;
    }

    @Override
    public void Update() {

    }

    @Override
    public void OnRender() {

    }

    @Override
    public void Init() {

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

    @Override
    public void OnUiRender() {
        ImGui.begin(name);
        for (EditorUiObject dat: UiObjects) {
            dat.OnUiRender();
        }
        ImGui.end();
    }

    public void RegisterUiObj(EditorUiObject obj) {
        UiObjects.add(obj);
    }

    public void UnegisterUiObj(EditorUiObject obj) {
        if(UiObjects.contains(obj))
            UiObjects.add(obj);
    }
}
