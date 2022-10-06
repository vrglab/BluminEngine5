package BluminEngine5.Physics.Colision;

import BluminEngine5.Componant.Transform;
import BluminEngine5.Utils.Math.Vector3;

public class BoundBox {
    public Transform transform = Transform.DefaultZero;

    public Vector3 TopLeftFront, TopLeftBack,
               TopRightFront, TopRightBack,
               BottomLeftFront,  BottomLeftBack,
               BottomRightFront,  BottomRightBack = Vector3.Zero;


    public BoundBox(Vector3 topLeftFront, Vector3 topLeftBack, Vector3 topRightFront, Vector3 topRightBack, Vector3 bottomLeftFront, Vector3 bottomLeftBack, Vector3 bottomRightFront, Vector3 bottomRightBack) {
        TopLeftFront = topLeftFront;
        TopLeftBack = topLeftBack;
        TopRightFront = topRightFront;
        TopRightBack = topRightBack;
        BottomLeftFront = bottomLeftFront;
        BottomLeftBack = bottomLeftBack;
        BottomRightFront = bottomRightFront;
        BottomRightBack = bottomRightBack;
    }

    public BoundBox() {

    }
}
