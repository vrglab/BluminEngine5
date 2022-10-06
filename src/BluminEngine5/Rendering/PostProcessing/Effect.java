package BluminEngine5.Rendering.PostProcessing;

import BluminEngine5.Behaviour.IObjLogic;
import BluminEngine5.Utils.objActionData;

public abstract class Effect implements IObjLogic {
    public String UniformName;
    public boolean Active = false;
    public PostProcessingBehaviour Parent = null;
    public objActionData data = null;
}
