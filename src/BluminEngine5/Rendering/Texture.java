package BluminEngine5.Rendering;

import BluminEngine5.Application;
import BluminEngine5.Utils.Annotations.MustCreate;
import BluminEngine5.Utils.Debuging.Debug;
import BluminEngine5.Utils.Math.Vector2;
import BluminEngine5.Utils.ResourceMannager.Archive.ArchivedFile;
import BluminEngine5.Utils.Utils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.newdawn.slick.opengl.InternalTextureLoader;
import org.newdawn.slick.opengl.TextureLoader;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL14.GL_MIRRORED_REPEAT;

@MustCreate
public class Texture implements Serializable{

    private org.newdawn.slick.opengl.Texture texture;
    private float width, height;
    private ByteBuffer decodedbytes;
    private int textureId;
    public ArchivedFile file;
    private String fileBackup;
    private TextureMode renderMode = TextureMode.ClampToBorder;

    public Texture(ArchivedFile file) {
        this.file = file;
    }

    public Texture(String file) {
        this.fileBackup = file;
    }
    public Texture(Vector2 size) {
        width = (int)size.x;
        height = (int)size.y;
    }

    public void Create() {
        if(file != null) {
            try{
                texture = GetTexFromFile();
                width = texture.getWidth();
                height = texture.getHeight();
                textureId = texture.getTextureID();

                decodedbytes = ByteBuffer.allocate(file.GetDecodedData().length * 4);
                decodedbytes.put(file.GetDecodedData());
                decodedbytes.flip();

                glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_MIRRORED_REPEAT);
            } catch(IOException e){
                Debug.logException("Failed to load texture with exception", e);
            }
        } else{
            try{
                InputStream is = Utils.LoadFileAsStream(fileBackup);
                File f = new File(fileBackup);
                texture =  TextureLoader.getTexture(FilenameUtils.getExtension(fileBackup), is , GL11.GL_LINEAR);
                width = texture.getWidth();
                height = texture.getHeight();
                textureId = texture.getTextureID();
                byte[] data = texture.getTextureData();

                decodedbytes = ByteBuffer.allocate(data.length * 4);
                decodedbytes.put(data);
                decodedbytes.flip();

                glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_MIRRORED_REPEAT);
                f.delete();
            } catch(IOException e){
                Debug.logException("Failed to create texture with exception", e);
            }
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

    public ByteBuffer getDecodedbytes() {
        return decodedbytes;
    }

    public int getTextureId() {
        return textureId;
    }

    public TextureMode getRenderMode() {
        return renderMode;
    }

    public void setRenderMode(TextureMode renderMode) {
        this.renderMode = renderMode;
    }

    private org.newdawn.slick.opengl.Texture GetTexFromFile()
    throws IOException{

        File f = Application.getResourceManager().LoadIntoTempFile(file);
        InputStream is = Utils.LoadFileAsStream(f.getAbsolutePath());
        org.newdawn.slick.opengl.Texture tex =  TextureLoader.getTexture(file.Extension, is , GL11.GL_LINEAR);
        is.close();
        f.delete();
        return  tex;
    }
}
