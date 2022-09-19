package BluminEngine5.Rendering.UI;

import imgui.ImGui;

import java.util.ArrayList;
import java.util.List;

public class Panel extends UiObject{

    protected List<UiObject> UiObjects = new ArrayList<>();

    String panelName = "New Panel";

    public Panel(String name){
        panelName = name;

    }


    @Override
    public void Update() {
        for (UiObject comp: UiObjects) {
            comp.Update();
        }
    }

    @Override
    public void OnRender() {
        for (UiObject comp: UiObjects) {
            comp.OnRender();
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

    @Override
    public void OnUiRender() {
        ImGui.begin(panelName);
        for (UiObject comp: UiObjects) {
            comp.OnUiRender();
        }
        ImGui.end();
    }

    public void RegisterUiObj(UiObject obj) {
        UiObjects.add(obj);
    }

    public void UnegisterUiObj(UiObject obj) {
        if(UiObjects.contains(obj))
            UiObjects.add(obj);
    }
}
