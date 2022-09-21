package BluminEngine5.Rendering.UI;

import BluminEngine5.Behaviour.IUiLogics;
import BluminEngine5.Componant.Transform;
import BluminEngine5.Rendering.Master.Mesh;
import BluminEngine5.Rendering.Shaders.Shader;


public abstract class UiObject implements IUiLogics {

    public Transform transform;
    public Shader shader;
    public Mesh mesh;
    public Rect rect;
    public abstract void OnUiRender();
}
