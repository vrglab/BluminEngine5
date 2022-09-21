package BluminEngine5.Rendering.UI.Coponants;

import BluminEngine5.Componant.IComponent;
import BluminEngine5.Rendering.UI.Obj.UiObject;
import BluminEngine5.Utils.Math.Matrix;
import BluminEngine5.Utils.Math.Vector2;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;

public class ImageRenderer extends IComponent {

    public UiObject Parent;

    @Override
    public void Update() {

    }

    @Override
    public void OnRender() {
        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL13.glBindTexture(GL13.GL_TEXTURE_2D, Parent.mesh.getMaterial().getTexture().getTextureId());
        Parent.shader.SetUniform("transformationMatrix", Matrix.transform(new Vector2(Parent.transform.position.x, Parent.transform.position.y),
                Parent.transform.rotation,
                new Vector2(Parent.transform.scale.x,Parent.transform.scale.z)));
        GL11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 0, Parent.mesh.getVertecies().length);
    }

    @Override
    public void Init() {
        Parent = super.UIParent;
    }

    @Override
    public void PreInit() {

    }

    @Override
    public void OnExit() {

    }

    @Override
    public void SceneLoad() {

    }

    @Override
    public void Destroy() {

    }
}
