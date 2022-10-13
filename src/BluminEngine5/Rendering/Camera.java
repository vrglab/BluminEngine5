package BluminEngine5.Rendering;


import BluminEngine5.Application;
import BluminEngine5.Behaviour.BluminBehaviour;
import BluminEngine5.Utils.Math.Matrix;

public class Camera extends BluminBehaviour {

    public float fov = 90;
    public float FarPlane = 10000.0f;
    public float NearPlane = 0.1f;
    private Matrix projectionMatrix;

    public Camera() {
        transform.position.z = 0;

    }


    public Matrix getProjectionMatrix() {
        return projectionMatrix;
    }

    @Override
    public void Update() {

    }

    @Override
    public void OnRender() {

    }

    @Override
    public void Init() {
        projectionMatrix = Matrix.projection(fov,
                (Application.display.getCurentScreenRes().getWIDTH() / Application.display.getCurentScreenRes().getHIGHT()) + 0.7f,
                NearPlane,FarPlane);
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
