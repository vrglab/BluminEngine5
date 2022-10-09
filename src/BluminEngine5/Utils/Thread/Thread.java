package BluminEngine5.Utils.Thread;

import BluminEngine5.Utils.Debuging.Debug;
import BluminEngine5.Utils.EventSystem.Action;
import BluminEngine5.Utils.EventSystem.IActionArgBased;

import java.io.File;

public class Thread<t>{
    public Action<t> OnThreadFinished = new Action<>();

    t value = null;
    ArgEvent<t> function = null;
    java.lang.Thread t;

    boolean Activated = false;
    boolean Destroyed = false;

    public Thread(ArgEvent<t> function) {
        this.function = function;
    }

    public void Run() {
        if(!Destroyed) {
            t = new java.lang.Thread(()->{
                value = function.run();
            });
            t.run();
            Activated = true;
        }
    }

    public void Update() {
        if(Activated && !Destroyed) {
            if(!t.isAlive()) {
                OnThreadFinished.Invoke(value);
                Destroyed=true;
            }
        }

    }

}
