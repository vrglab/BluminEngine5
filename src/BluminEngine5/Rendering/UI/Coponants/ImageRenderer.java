package BluminEngine5.Rendering.UI.Coponants;

import BluminEngine5.Componant.Component;
import BluminEngine5.Rendering.UI.Obj.UiObject;
import BluminEngine5.Utils.Math.Matrix;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;

import static org.lwjgl.opengl.GL11.*;

public class ImageRenderer extends Component {

    public UiObject Parent;

    @Override
    public void Update() {

    }

    @Override
    public void OnRender() {
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, Parent.mesh.getMaterial().getTexture().getRenderMode().GetValue());
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, Parent.mesh.getMaterial().getTexture().getRenderMode().GetValue());
        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL13.glBindTexture(GL_TEXTURE_2D, Parent.mesh.getMaterial().getTexture().getTextureId());
        Parent.shader.SetUniform(
                "transformationMatrix",
                Matrix.transform(
                Parent.transform.position,
                Parent.transform.rotation,
                Parent.transform.scale)
        );
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
