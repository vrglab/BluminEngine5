package BluminEngine5.Componant;

import BluminEngine5.Utils.Math.Vector3;

import javax.vecmath.Matrix3f;
import javax.vecmath.Matrix4f;
import javax.vecmath.Quat4f;

public class Transform extends IComponent {
    public Vector3 position = Vector3.Zero;
    public Vector3 rotation = Vector3.Zero;
    public Vector3 scale = Vector3.Zero;

    public Transform() {
        position = Vector3.Zero;
        rotation = Vector3.Zero;
        scale = new Vector3(1,1,1);
    }

    public Transform(Vector3 position) {
        this.position = position;
        rotation = Vector3.Zero;
        scale = new Vector3(1,1,1);
    }
    public Transform(Vector3 position, Vector3 rotation) {
        this.position = position;
        this.rotation = rotation;
        scale = new Vector3(1,1,1);
    }

    public Transform(Vector3 position, Vector3 rotation, Vector3 scale) {
        this.position = position;
        this.rotation = rotation;
        this.scale = scale;
    }

    public com.bulletphysics.linearmath.Transform GetAsPhysicsTransform() {
        com.bulletphysics.linearmath.Transform t = new com.bulletphysics.linearmath.Transform();
        t.setRotation(new Quat4f(rotation.x,rotation.y,rotation.z,1));
        t.transform(position.GetAsVec3f());
        return t;
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
