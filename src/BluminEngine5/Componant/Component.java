package BluminEngine5.Componant;

import BluminEngine5.Behaviour.BluminBehaviour;
import BluminEngine5.Behaviour.IObjLogic;
import BluminEngine5.Rendering.UI.Obj.UiObject;
import BluminEngine5.Utils.objActionData;


public abstract class Component implements IObjLogic {
     public BluminBehaviour Parent = null;
     public UiObject UIParent = null;
     public objActionData data = null;
     public String name = getClass().getSimpleName();

}
