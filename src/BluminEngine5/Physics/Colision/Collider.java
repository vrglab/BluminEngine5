package BluminEngine5.Physics.Colision;

import BluminEngine5.Componant.Transform;

public class Collider {
    public Transform transform = Transform.DefaultZero;
    public BoundBox bounds = new BoundBox();
    public boolean HitBox = false;
}
