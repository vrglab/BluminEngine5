package BluminEngine5.Rendering.PostProcessing;

import BluminEngine5.Behaviour.BluminBehaviour;
import BluminEngine5.Componant.IComponent;
import BluminEngine5.Rendering.Shaders.Shader;
import BluminEngine5.Utils.EventSystem.IAction;
import BluminEngine5.Utils.objActionData;

import java.util.ArrayList;
import java.util.List;

public abstract class PostProcessingBehaviour extends IComponent {
    public Shader ProcessShader;
    private List<Effect> AttachedEffects = new ArrayList<>();

    public objActionData ActionData =  new objActionData();

    public PostProcessingBehaviour() {
        ActionData.OnUpdate = new IAction() {
            @Override
            public void Run() {
                Update();
                for (Effect comp: AttachedEffects) {
                    comp.data.OnUpdate.Run();
                }
            }
        };
        ActionData.OnExit = new IAction() {
            @Override
            public void Run() {
                for (Effect comp: AttachedEffects) {
                    comp.data.OnExit.Run();
                }
                Destroy();
                OnExit();
            }
        };
        ActionData.OnRender = new IAction() {
            @Override
            public void Run() {
                OnRender();
                for (Effect comp: AttachedEffects) {
                    comp.data.OnRender.Run();
                }
            }
        };
        ActionData.OnInit = new IAction() {
            @Override
            public void Run() {
                Init();
                for (Effect comp: AttachedEffects) {
                    comp.data.OnInit.Run();
                }
            }
        };
        ActionData.OnDestroy = new IAction() {
            @Override
            public void Run() {
                Destroy();
                for (Effect comp: AttachedEffects) {
                    comp.data.OnDestroy.Run();
                }
            }
        };
        ActionData.OnPreInit = new IAction() {
            @Override
            public void Run() {
                for (Effect comp: AttachedEffects) {
                    comp.data.OnPreInit.Run();
                }
            }
        };
    }

    public void RegisterComponant(Effect component, PostProcessingBehaviour bb) {
        component.data = new objActionData();
        component.Active = true;
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
        component.Parent = bb;
        AttachedEffects.add(component);
    }

    public void UnregisterComponant(IComponent component) {
        AttachedEffects.remove(AttachedEffects.lastIndexOf(component));
    }

    public List<Effect> getAttachedEffects() {
        return AttachedEffects;
    }
}
