package ExampleGame;

import BluminEngine5.Behaviour.BluminBehaviour;
import BluminEngine5.Rendering.Lighting.Sun;

public class ExampleSun extends BluminBehaviour {

    public Sun SunComponant = new Sun();

    public ExampleSun() {
        RegisterComponant(SunComponant, this);
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
