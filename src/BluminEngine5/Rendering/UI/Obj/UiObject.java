package BluminEngine5.Rendering.UI.Obj;

import BluminEngine5.Behaviour.IUiLogics;
import BluminEngine5.Componant.Transform;
import BluminEngine5.Rendering.Shaders.Shader;
import BluminEngine5.Rendering.UI.Rect;
import BluminEngine5.Utils.Math.Vector3;


public abstract class UiObject implements IUiLogics {

    public Transform transform = new Transform(new Vector3(0,0,2),Vector3.Zero,Vector3.Zero);
    public Shader shader;
    public Mesh mesh;
    public Rect rect;
    public abstract void OnUiRender();
}
