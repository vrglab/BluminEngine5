package BluminEngine5.Rendering;

import BluminEngine5.Utils.Math.Vector2;
import BluminEngine5.Utils.Math.Vector3;


public class Color  {

    float r,g,b,a;
    public Color(float r, float g, float b, float a){
        set(r,g,b,a);
    }

    public Color(Vector2 vec){
        set(vec.x, vec.y,0, 1);
    }
    public Color(Vector3 vec){
        set(vec.x, vec.y,vec.z, 1);
    }

    public float GetR() {
        return r;
    }
    public float GetG() {
        return g;
    }
    public float GetB() {
        return b;
    }
    public float GetA() {
        return a;
    }

    public float[] getAsArray() {
        float array[] = {
                r,
                g,
                b,
                a
        };
        return array;
    }
    public void set(float r, float g, float b, float a) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }

    @Override
    public String toString() {
        return "Color(" + r + "," + g +","+ b+ ", " + a + ")";
    }

    public Vector3 ToVec3() {
        return new Vector3(r,g,b);
    }
}