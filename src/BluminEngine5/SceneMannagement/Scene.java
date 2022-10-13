package BluminEngine5.SceneMannagement;


import BluminEngine5.Application;
import BluminEngine5.Behaviour.BluminBehaviour;
import BluminEngine5.Behaviour.ILogic;
import BluminEngine5.Componant.Rendering.Lighting.BaseLight;
import BluminEngine5.Rendering.Camera;
import BluminEngine5.Rendering.Color;
import BluminEngine5.Rendering.CubeMap;
import BluminEngine5.Rendering.Lighting.LightData;
import BluminEngine5.Utils.EventSystem.IAction;
import BluminEngine5.Utils.objActionData;

import java.util.ArrayList;
import java.util.List;

public abstract class Scene implements ILogic {

    private List<BluminBehaviour> GameObjects = new ArrayList<>();
    public LightData LightObjects = new LightData();

    public objActionData ActionData =  new objActionData();
    public String name = "New Scene";
    public Camera ActiveCamera = new Camera();



    public Scene() {


        ActionData.OnUpdate = new IAction() {
            @Override
            public void Run() {
                Update();
                for (BluminBehaviour comp: GameObjects) {
                    comp.ActionData.OnUpdate.Run();
                }
                LightObjects.Update();
            }
        };
        ActionData.OnExit = new IAction() {
            @Override
            public void Run() {
                for (BluminBehaviour comp: GameObjects) {
                    comp.ActionData.OnExit.Run();
                }
                LightObjects.OnExit();
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
                LightObjects.OnRender();

            }
        };
        ActionData.OnInit = new IAction() {
            @Override
            public void Run() {
                Init();
                for (BluminBehaviour comp: GameObjects) {
                    comp.ActionData.OnInit.Run();
                }
                LightObjects.Init();
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
                LightObjects.PreInit();
                LightObjects.Init();
            }
        };
        ActionData.OnDestroy =  new IAction() {
            @Override
            public void Run() {
                Unload();
                for (BluminBehaviour comp: GameObjects) {
                    comp.ActionData.OnDestroy.Run();
                }
                LightObjects.Destroy();
            }
        };
        ActionData.OnPreInit =  new IAction() {
            @Override
            public void Run() {
                for (BluminBehaviour comp: GameObjects) {
                    comp.ActionData.OnPreInit.Run();
                }
                LightObjects.PreInit();
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
        LightObjects.miscLight.AddLight(bb);
    }

    public void UnRegsiterLightObject(BaseLight bb) {
        LightObjects.miscLight.RemoveLight(bb);
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

    public <t extends BluminBehaviour> t getGameObject(Class<t> componantType) {
        for (BluminBehaviour comp: GameObjects) {
            if(comp.getClass().isAssignableFrom(componantType.getClass())) {
                try{
                    return componantType.cast(comp);
                } catch(ClassCastException e){

                }
            }
        }
        return null;
    }
}
