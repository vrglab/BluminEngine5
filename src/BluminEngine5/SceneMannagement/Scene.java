package BluminEngine5.SceneMannagement;


import BluminEngine5.Application;
import BluminEngine5.Behaviour.BluminBehaviour;
import BluminEngine5.Behaviour.ILogic;
import BluminEngine5.Componant.IComponent;
import BluminEngine5.Componant.Rendering.Lighting.BaseLight;
import BluminEngine5.Rendering.Camera;
import BluminEngine5.Rendering.Color;
import BluminEngine5.Rendering.Lighting.Sun;
import BluminEngine5.Utils.EventSystem.IAction;
import BluminEngine5.Utils.objActionData;

import java.util.ArrayList;
import java.util.List;

public abstract class Scene implements ILogic {

    private List<BluminBehaviour> GameObjects = new ArrayList<>();
    public List<BaseLight> LightObjects = new ArrayList<>();

    public objActionData ActionData =  new objActionData();

    public String name = "New Scene";
    /* public LightBase Sun =  new Sun(new Transform(
             new Vector3(0,0,0),
             new Vector3(0,0,0)),
             new Color(0,0,0,1));*/
    public Camera ActiveCamera = new Camera();
    public Color SkyColor = new Color(0.2f,0.5f,0.5f,1);



    public Scene() {


        ActionData.OnUpdate = new IAction() {
            @Override
            public void Run() {
                Update();
                for (BluminBehaviour comp: GameObjects) {
                    comp.ActionData.OnUpdate.Run();
                }
                for (BaseLight comp: LightObjects) {
                    comp.data.OnUpdate.Run();
                }
            }
        };
        ActionData.OnExit = new IAction() {
            @Override
            public void Run() {
                for (BluminBehaviour comp: GameObjects) {
                    comp.ActionData.OnExit.Run();
                }
                for (BaseLight comp: LightObjects) {
                    comp.data.OnExit.Run();
                }
                OnExit();
            }
        };
        ActionData.OnRender = new IAction() {
            @Override
            public void Run() {
                OnRender();
                for (BluminBehaviour comp: GameObjects) {
                    comp.ActionData.OnRender.Run();
                }
                for (BaseLight comp: LightObjects) {
                    comp.data.OnRender.Run();
                }
            }
        };
        ActionData.OnInit = new IAction() {
            @Override
            public void Run() {
                Init();
                for (BluminBehaviour comp: GameObjects) {
                    comp.ActionData.OnInit.Run();
                }
                for (BaseLight comp: LightObjects) {
                    comp.data.OnInit.Run();
                }
            }
        };
        ActionData.OnSceneLoad =  new IAction() {
            @Override
            public void Run() {
                Load();
                for (BluminBehaviour comp: GameObjects) {
                    comp.ActionData.OnPreInit.Run();
                }
                for (BluminBehaviour comp: GameObjects) {
                    comp.ActionData.OnInit.Run();
                }
                for (BaseLight comp: LightObjects) {
                    comp.data.OnPreInit.Run();
                }
                for (BaseLight comp: LightObjects) {
                    comp.data.OnInit.Run();
                }
            }
        };
        ActionData.OnDestroy =  new IAction() {
            @Override
            public void Run() {
                Unload();
                for (BluminBehaviour comp: GameObjects) {
                    comp.ActionData.OnDestroy.Run();
                }
                for (BaseLight comp: LightObjects) {
                    comp.data.OnDestroy.Run();
                }
            }
        };
        ActionData.OnPreInit =  new IAction() {
            @Override
            public void Run() {
                for (BluminBehaviour comp: GameObjects) {
                    comp.ActionData.OnPreInit.Run();
                }
                for (BaseLight comp: LightObjects) {
                    comp.data.OnPreInit.Run();
                }
            }
        };

        Application.PreInit.addListener(ActionData.OnPreInit);
        Application.Init.addListener(ActionData.OnInit);
        Application.Update.addListener(ActionData.OnUpdate);
        Application.OnExit.addListener(ActionData.OnExit);
        Application.getRenderer().OnRender.addListener(ActionData.OnRender);
    }

    public void RegsiterGameObject(BluminBehaviour bb) {
        if(!GameObjects.contains(bb)) {
            GameObjects.add(bb);
            bb.parent = this;
        }
    }

    public void UnRegsiterGameObject(BluminBehaviour bb) {
        if(GameObjects.contains(bb)) {
            GameObjects.remove(GameObjects.lastIndexOf(bb));
        }

    }

    public void RegsiterLightObject(BaseLight bb) {
        if(!LightObjects.contains(bb)) {
            LightObjects.add(bb);
        }
    }

    public void UnRegsiterLightObject(BaseLight bb) {
        if(LightObjects.contains(bb)) {
            LightObjects.remove(GameObjects.lastIndexOf(bb));
        }

    }

    public abstract void Load();
    public void Unload() {
        Application.PreInit.removeListener(ActionData.OnPreInit);
        Application.Init.removeListener(ActionData.OnInit);
        Application.Update.removeListener(ActionData.OnUpdate);
        Application.OnExit.removeListener(ActionData.OnExit);
        Application.getRenderer().OnRender.removeListener(ActionData.OnRender);

        for (BluminBehaviour comp: GameObjects) {
            comp.Destroy();
        }
    }



    public BluminBehaviour getGameObject(BluminBehaviour componantType) {
        for (BluminBehaviour comp: GameObjects) {
            if(comp.getClass().getName() == componantType.getClass().getName()) {
                return comp;
            }
        }
        return null;
    }



    public BluminBehaviour[] getGameObjectArray(BluminBehaviour componantType) {
        List<BluminBehaviour> bs = new ArrayList<>();
        for (BluminBehaviour comp: GameObjects) {
            if(comp.getClass().getName() == componantType.getClass().getName()) {
                bs.add(comp);
            }
        }
        BluminBehaviour[] b = new BluminBehaviour[bs.size()];

        for (int i = 0; i < b.length; i++){
            b[i] = bs.get(i);
        }

        return b;
    }

    public BluminBehaviour[] getGameObjectArray(BluminBehaviour componantType, Scene bb) {
        List<BluminBehaviour> bs = new ArrayList<>();
        for (BluminBehaviour comp: bb.GameObjects) {
            if(comp.getClass().getName() == componantType.getClass().getName()) {
                bs.add(comp);
            }
        }
        BluminBehaviour[] b = new BluminBehaviour[bs.size()];

        for (int i = 0; i < b.length; i++){
            b[i] = bs.get(i);
        }

        return b;
    }

    public static BluminBehaviour getGameObject(BluminBehaviour componantType, Scene bb) {
        for (BluminBehaviour comp: bb.GameObjects) {
            if(comp.getClass().getName() == componantType.getClass().getName()) {
                return comp;
            }
        }
        return null;
    }
}
