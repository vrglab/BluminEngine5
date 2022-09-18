package BluminEngine5.Componant;

import BluminEngine5.Behaviour.BluminBehaviour;
import BluminEngine5.Behaviour.ILogic;
import BluminEngine5.Behaviour.IObjLogic;
import BluminEngine5.Utils.objActionData;


public abstract class IComponent implements IObjLogic {
     public BluminBehaviour Parent = null;
     public objActionData data = null;

}
