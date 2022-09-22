package BluminEngine5.Rendering.PostProcessing;

import BluminEngine5.Rendering.Shaders.Shader;

public abstract class PostProcessingProfileBehaviour extends PostProcessingBehaviour {

    public Shader shader = null;

    public void PostProcessingProfileBehaviour(Shader shader) {
        this.shader = shader;
    }

    public abstract void Creat();
    public abstract void Destroy();
}
