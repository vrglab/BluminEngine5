package BluminEngine5.Rendering.UI;

import BluminEngine5.Componant.Transform;
import BluminEngine5.Utils.Math.Vector2;

public class Rect{
    private int maxY,minY,maxX,minX;
    public Transform transform = new Transform();

    public Rect(int may, int miy, int max, int mix) {
        maxY = may;
        minY = miy;
        maxX = max;
        minX = mix;
    }
}
