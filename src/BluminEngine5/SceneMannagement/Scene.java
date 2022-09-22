package BluminEngine5.SceneMannagement;


import BluminEngine5.Application;
import BluminEngine5.Behaviour.BluminBehaviour;
import BluminEngine5.Behaviour.ILogic;
import BluminEngine5.Rendering.ThreeD.Camera;
import BluminEngine5.Rendering.ThreeD.Color;
import BluminEngine5.Utils.EventSystem.IAction;
import BluminEngine5.Utils.objActionData;

import java.util.ArrayList;
import java.util.List;

public abstract class Scene implements ILogic {

    private List<BluminBehaviour> GameObjects = new ArrayList<>();

    public objActionData ActionData =  new objActionData();



    public Scene() {

        ActionData.OnUpdate = new IAction() {
            @Override
            public void Run() {
                Update();
                for (BluminBehaviour comp: GameObjects) {
                    comp.Update();
                }
            }
        };
        ActionData.OnExit = new IAction() {
            @Override
            public void Run() {
                for (BluminBehaviour comp: GameObjects) {
                    comp.OnExit();
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
            }
        };
        ActionData.OnInit = new IAction() {
            @Override
            public void Run() {
                Init();
                for (BluminBehaviour comp: GameObjects) {
                    comp.ActionData.OnInit.Run();
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
            }
        };
        ActionData.OnDestroy =  new IAction() {
            @Override
            public void Run() {
                Unload();
                for (BluminBehaviour comp: GameObjects) {
                    comp.ActionData.OnDestroy.Run();
                }
            }
        };
        ActionData.OnPreInit =  new IAction() {
            @Override
            public void Run() {
                for (BluminBehaviour comp: GameObjects) {
                    comp.ActionData.OnPreInit.Run();
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
        }
    }

    public void UnRegsiterGameObject(BluminBehaviour bb) {
        if(GameObjects.contains(bb)) {
            GameObjects.remove(GameObjects.lastIndexOf(bb));
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

    public String name = "New Scene";
   /* public LightBase Sun =  new Sun(new Transform(
            new Vector3(0,0,0),
            new Vector3(0,0,0)),
            new Color(0,0,0,1));*/
    public Camera ActiveCamera = new Camera();
    public Color SkyColor = new Color(0.2f,0.5f,0.5f,1);
}
