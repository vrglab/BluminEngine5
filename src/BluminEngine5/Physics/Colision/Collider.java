package BluminEngine5.Physics.Colision;

import BluminEngine5.Componant.Transform;
import BluminEngine5.Utils.Math.Math;

import java.io.Serializable;

public class Collider  implements Serializable {
    public Transform transform = Transform.DefaultZero;
    public BoundBox bounds = new BoundBox();
    public boolean HitBox = false;

    public boolean intersecting(Collider othercol) {
        BoundBox otherBound = othercol.bounds;

       var otherectx = Math.Plus(otherBound.BottomLeftFront, otherBound.BottomRightFront);
       var otherrectWith = Math.Modual(otherBound.BottomLeftFront, otherBound.BottomRightFront);


        return false;
    }
}
