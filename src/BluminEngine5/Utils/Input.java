package BluminEngine5.Utils;

import BluminEngine5.Behaviour.ILogic;
import BluminEngine5.Utils.Debuging.Debug;
import BluminEngine5.Utils.EventSystem.Action;
import BluminEngine5.Utils.EventSystem.IActionArgBased;

import java.util.ArrayList;
import java.util.List;

public class Input  {

    public List<Integer> Held = new ArrayList<>();
    public List<Integer> Pressed = new ArrayList<>();
    public List<Integer> Released = new ArrayList<>();
    private static Input instance;

    public static Input Instance() {
        if(instance == null) {
            instance = new Input();
        }
        return instance;
    }

    public boolean WasHeld(int key) {
        if (Held.contains(key)) {
            return true;
        }
        return false;
    }

    public boolean WasPressed(int key) {
        if (Pressed.contains(key)) {
            Pressed.remove(Pressed.lastIndexOf(key));
            return true;
        }
        return false;
    }

    public boolean WasReleased(int key) {
        if (Released.contains(key)) {
            Released.remove(Released.lastIndexOf(key));
            return true;
        }
        return false;
    }

}
