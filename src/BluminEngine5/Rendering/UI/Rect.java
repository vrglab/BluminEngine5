package BluminEngine5.Rendering.UI;

import BluminEngine5.Componant.Component;
import BluminEngine5.Utils.Math.Vector2;
import BluminEngine5.Utils.Math.Vector3;

public class Rect extends Component {

    public Vector2 position = Vector2.Zero;
    public Vector2 scale = Vector2.Zero;
    public Vector3 rotation = Vector3.Zero;
    public UICenter Center = UICenter.Center;


    public Rect(Vector2 pos, Vector2 scal, Vector3 r){
        position = pos;
        scale = scal;
        rotation = r;
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
}
