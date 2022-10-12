package BluminEngine5.SceneMannagement;





import BluminEngine5.Application;
import BluminEngine5.Utils.Debuging.Debug;
import BluminEngine5.Utils.EventSystem.Action;
import BluminEngine5.Utils.EventSystem.IAction;

import static org.lwjgl.system.MemoryUtil.NULL;

public class SceneManager {

    public Action<IAction> Unload = new Action<>();

    private static SceneManager CurenMannager;
    private Scene CurrentActiveScene;
    public static Scene DefaultEmptyScene = new Scene() {
        @Override
        public void Load() {

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
    };

    public static SceneManager GetCurent() {
        if(CurenMannager == null) {
            Debug.log("Starting SceneManager");
            CurenMannager = new SceneManager();
        }
        return CurenMannager;
    }

    public void SetActiveScene(Scene scene) {
        if(CurrentActiveScene != null) {
            CurrentActiveScene.ActionData.OnDestroy.Run();
            CurrentActiveScene = scene;
            Application.PreInit.Invoke();
            Application.Init.Invoke();
            CurrentActiveScene.ActionData.OnSceneLoad.Run();
        }else{
            scene.Load();
            CurrentActiveScene = scene;
        }
    }

    public Scene GetActiveScene() {
        if(CurrentActiveScene == null) {
            CurrentActiveScene = DefaultEmptyScene;
        }
        return CurrentActiveScene;
    }
}
