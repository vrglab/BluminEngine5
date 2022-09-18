package BluminEngine5.Utils.Math;


import javax.vecmath.Matrix3d;
import javax.vecmath.Matrix3f;
import javax.vecmath.Vector3f;

public class Vector3 {

    public float x;
    public float y;
    public float z;

    public Vector3(float x, float y, float z){
        set(x,y,z);
    }

    public Vector3(Vector2 vec){
        set(vec.x, vec.y,0);
    }

    public float GetX() {
        return x;
    }
    public float GetY() {
        return y;
    }
    public float GetZ() {
        return z;
    }


    public void set(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3f GetAsVec3f() {
        Vector3f f = new Vector3f();
        f.x =x;
        f.y = y;
        f.z = z;
        return f;
    }

    public static Vector3 Zero = new Vector3(0,0,0);

    @Override
    public String toString() {
        return "Vector3(" +x + "," + y +","+ z+")";
    }

}
