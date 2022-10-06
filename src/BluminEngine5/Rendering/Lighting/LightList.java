package BluminEngine5.Rendering.Lighting;

import BluminEngine5.Componant.Rendering.Lighting.BaseLight;

import java.util.ArrayList;
import java.util.List;

public class LightList<t extends BaseLight>{
    private List<t> Lights = new ArrayList<>();

    public void AddLight(t light){
        Lights.add(light);
    }

    public void RemoveLight(t light){
        Lights.remove(light);
    }

    public t GetLight(int index) {
        return  Lights.get(index);
    }

    public int getIndexOf(t obj) {
        return Lights.lastIndexOf(obj);
    }

    public int getAmnt() {
        return Lights.size();
    }
}
