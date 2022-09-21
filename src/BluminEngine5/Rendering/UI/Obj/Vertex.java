package BluminEngine5.Rendering.UI.Obj;

import BluminEngine5.Rendering.Color;
import BluminEngine5.Utils.Math.Vector2;
import BluminEngine5.Utils.Math.Vector3;

public class Vertex {
    private Vector2 position;

    public Vertex(Vector2 pos) {
        position = pos;
    }

    public Vector2 getPosition() {
        return position;
    }
}