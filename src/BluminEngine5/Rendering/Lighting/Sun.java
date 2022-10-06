package BluminEngine5.Rendering.Lighting;

import BluminEngine5.Componant.Rendering.Lighting.BaseLight;
import BluminEngine5.Rendering.Color;
import BluminEngine5.Utils.Debuging.Debug;

public class Sun extends BaseLight {

    @Override
    public void Update() {
            transform = Parent.transform;
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
