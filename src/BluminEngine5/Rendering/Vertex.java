package BluminEngine5.Rendering;

import BluminEngine5.Utils.Math.*;

public class Vertex {
    private Vector3 position, normals;
    private Vector2 texturePosition;
    private Color color;

    public Vertex(Vector3 pos,Vector2 texpos) {
        position = pos;
        texturePosition = texpos;
        color = new Color(1,1,1,1);
    }
    public Vertex(Vector3 pos, Vector3 norm, Vector2 texpos) {
        position = pos;
        texturePosition = texpos;
        normals = norm;
        color = new Color(1,1,1,1);
    }
    public Vertex(Vector3 pos, Color c, Vector2 texpos) {
        position = pos;
        texturePosition = texpos;
        color = c;
    }
    public Vector3 getPosition() {
        return position;
    }

    public Color getColor() {
        return color;
    }

    public Vector2 getTexturePosition() {
        return texturePosition;
    }

    public Vector3 getNormals() {
        return normals;
    }
}
