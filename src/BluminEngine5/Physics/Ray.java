package BluminEngine5.Physics;

import BluminEngine5.Componant.Transform;
import BluminEngine5.Physics.Colision.Collider;
import BluminEngine5.Utils.Math.Vector2;
import BluminEngine5.Utils.Math.Vector3;

public class Ray {
    public Transform origin = Transform.DefaultZero;
    public Vector3 Direction;
    public Collider hit;

    public Ray(Transform origin, Vector3 direction) {
        this.origin = origin;
        Direction = direction;
    }
}
