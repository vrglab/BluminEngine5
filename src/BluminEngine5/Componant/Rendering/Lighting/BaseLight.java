package BluminEngine5.Componant.Rendering.Lighting;

import BluminEngine5.Componant.IComponent;
import BluminEngine5.Componant.Transform;
import BluminEngine5.Rendering.Color;
import BluminEngine5.Rendering.Lighting.Sun;

public abstract class BaseLight extends IComponent {
    public Transform transform = new Transform();
    public int Intesity = 4;
    public Color color = Color.White;
}
