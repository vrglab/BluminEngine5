package BluminEngine5.Rendering.Lighting;

import BluminEngine5.Behaviour.BluminBehaviour;
import BluminEngine5.Behaviour.IObjLogic;
import BluminEngine5.Componant.Rendering.Lighting.BaseLight;
import BluminEngine5.Rendering.Color;
import BluminEngine5.Utils.Math.Vector3;

import java.util.ArrayList;
import java.util.List;

public class LightData implements IObjLogic {


    public LightList<BaseLight> miscLight = new LightList<>();

    public LightList<PointLight> PointLights = new LightList<>();
    public Sun SceneSun = new Sun();


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

    public void SetSunPos(Vector3 pos){
        SceneSun.transform.position = pos;
    }
}
