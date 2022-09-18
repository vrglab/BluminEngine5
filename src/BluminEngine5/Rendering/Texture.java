package BluminEngine5.Rendering;

import BluminEngine5.Utils.Debuging.Debug;
import BluminEngine5.Utils.Utils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.newdawn.slick.opengl.TextureLoader;

import java.io.IOException;
import java.io.InputStream;

public class Texture {
    private org.newdawn.slick.opengl.Texture texture;
    private float width, height;
    private int textureId;
    private String file;

    public Texture(String file) {
        this.file = file;
    }

    public void Create() {

        try{
            texture = TextureLoader.getTexture(file.split("[.]")[1], Utils.LoadFileAsStream(file), GL11.GL_LINEAR);
            width = texture.getWidth();
            height = texture.getHeight();
            textureId = texture.getTextureID();
        } catch(IOException e){
            Debug.logException("Failed to load texture with exception", e);
        }

    }

    public void Destroy() {
        GL13.glDeleteTextures(textureId);
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public int getTextureId() {
        return textureId;
    }
}
