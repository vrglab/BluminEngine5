package BluminEngine5.Rendering.UI;

import BluminEngine5.Utils.Math.Vector2;

public enum UICenter {
    Center("cent")
    ;


    String value;
    UICenter(String data) {
        value = data;
    }

    public Vector2 GetValue() {
        switch(value) {
            case "cent":
                return new Vector2(0.9f,1.51147f);
        }
        return new Vector2(0.9f,1.51147f);
    }
}
