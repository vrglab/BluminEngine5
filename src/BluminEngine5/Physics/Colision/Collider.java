package BluminEngine5.Physics.Colision;

import BluminEngine5.Componant.Transform;

import java.io.Serializable;

public class Collider  implements Serializable {
    public Transform transform = Transform.DefaultZero;
    public BoundBox bounds = new BoundBox();
    public boolean HitBox = false;
}
