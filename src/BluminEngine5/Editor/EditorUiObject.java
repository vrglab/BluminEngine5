package BluminEngine5.Editor;

import BluminEngine5.Behaviour.BluminBehaviour;
import BluminEngine5.Componant.Component;
import BluminEngine5.Rendering.UI.Obj.UiObject;
import BluminEngine5.Rendering.UI.Rect;
import BluminEngine5.Utils.EventSystem.IAction;
import BluminEngine5.Utils.Math.Vector2;
import BluminEngine5.Utils.Math.Vector3;
import BluminEngine5.Utils.objActionData;

import java.util.ArrayList;
import java.util.List;

public abstract class EditorUiObject extends BluminBehaviour {

    private List<Component> AttachedComponants = new ArrayList<>();
    public objActionData ActionData =  new objActionData();
    public Rect transform = new Rect(Vector2.Zero,new Vector2(1,1), Vector3.Zero);


    public EditorUiObject() {
        RegisterComponant(transform);
        ActionData.OnUpdate = new IAction() {
            @Override
            public void Run() {
                Update();
                for (Component comp: AttachedComponants) {
                    comp.data.OnUpdate.Run();
                }
            }
        };
        ActionData.OnExit = new IAction() {
            @Override
            public void Run() {
                for (Component comp: AttachedComponants) {
                    comp.data.OnExit.Run();
                }
                Destroy();
                OnExit();
            }
        };
        ActionData.OnRender = new IAction() {
            @Override
            public void Run() {
                OnUiRender();
                for (Component comp: AttachedComponants) {
                    comp.data.OnRender.Run();
                }
            }
        };
        ActionData.OnInit = new IAction() {
            @Override
            public void Run() {
                Init();
                for (Component comp: AttachedComponants) {
                    comp.data.OnInit.Run();
                }
            }
        };
        ActionData.OnDestroy = new IAction() {
            @Override
            public void Run() {
                Destroy();
                for (Component comp: AttachedComponants) {
                    comp.data.OnDestroy.Run();
                }
            }
        };
        ActionData.OnPreInit = new IAction() {
            @Override
            public void Run() {
                for (Component comp: AttachedComponants) {
                    comp.data.OnPreInit.Run();
                }
            }
        };
    }


    public abstract void OnUiRender();

    public void RegisterComponant(Component component, UiObject bb) {
        component.data = new objActionData();
        component.data.OnInit = new IAction() {
            @Override
            public void Run() {
                component.Init();
            }
        };
        component.data.OnRender = new IAction() {
            @Override
            public void Run() {
                component.OnRender();
            }
        };
        component.data.OnUpdate = new IAction() {
            @Override
            public void Run() {
                component.Update();
            }
        };
        component.data.OnPreInit = new IAction() {
            @Override
            public void Run() {
                component.PreInit();
            }
        };
        component.data.OnDestroy = new IAction() {
            @Override
            public void Run() {
                component.Destroy();
            }
        };
        component.data.OnExit = new IAction() {
            @Override
            public void Run() {

            }
        };
        component.UIParent = bb;
        AttachedComponants.add(component);
    }

    public void UnregisterComponant(Component component) {
        AttachedComponants.remove(AttachedComponants.lastIndexOf(component));
    }
}
