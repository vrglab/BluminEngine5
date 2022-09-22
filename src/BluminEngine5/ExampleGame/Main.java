package BluminEngine5.ExampleGame;

import BluminEngine5.Application;
import BluminEngine5.Rendering.ThreeD.DisplayDimension;
import BluminEngine5.Rendering.ThreeD.DisplayMode;
import BluminEngine5.Rendering.ThreeD.Resolution;
import BluminEngine5.SceneMannagement.SceneManager;

public class Main {
    public static void main(String[] args) {
        SceneManager.GetCurent().SetActiveScene(new ExampleScene());
        Application.Run(Resolution.CurentRes(), DisplayMode.Fullscreen, DisplayDimension.ThreeD);
    }
}
