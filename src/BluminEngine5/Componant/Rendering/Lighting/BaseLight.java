package BluminEngine5.Componant.Rendering.Lighting;

import BluminEngine5.Componant.Component;
import BluminEngine5.Componant.Transform;
import BluminEngine5.Rendering.Color;

public abstract class BaseLight extends Component {
    public Transform transform = new Transform();
    public int Intesity = 4;
    public Color color = Color.White;
}
