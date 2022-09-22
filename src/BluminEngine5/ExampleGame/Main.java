package BluminEngine5.ExampleGame;

import BluminEngine5.Application;
import BluminEngine5.Rendering.DisplayDimension;
import BluminEngine5.Rendering.DisplayMode;
import BluminEngine5.Rendering.Resolution;
import BluminEngine5.SceneMannagement.SceneManager;

public class Main {
    public static void main(String[] args) {
        SceneManager.GetCurent().SetActiveScene(new ExampleScene());
        Application.Run(Resolution.CurentRes(), DisplayMode.Fullscreen, DisplayDimension.ThreeD);
    }
}
