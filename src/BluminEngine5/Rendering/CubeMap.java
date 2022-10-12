package BluminEngine5.Rendering;

import BluminEngine5.Application;
import BluminEngine5.Behaviour.IObjLogic;
import BluminEngine5.Rendering.Master.Mesh;
import BluminEngine5.Utils.Debuging.Debug;
import BluminEngine5.Utils.Utils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL30;

import static BluminEngine5.Utils.ResourceMannager.Archive.ArchiveMannager.NULL;

public class CubeMap {

    private static final int FacesAmnt = 6;
    private Texture[] faces = new Texture[FacesAmnt];
    private int ID = 0;

    public Mesh mesh;


    public CubeMap() {
        Application.Awake.addListener(()->{
            ID = GL11.glGenTextures();
            GL13.glActiveTexture(GL13.GL_TEXTURE4);
            GL11.glBindTexture(GL13.GL_TEXTURE_CUBE_MAP, ID);

            for (int i = 0; i < FacesAmnt; i++) {
                Texture data = Application.getResourceManager().GetTexture(0,4);
                GL11.glTexImage2D(GL13.GL_TEXTURE_CUBE_MAP_POSITIVE_X + i, 0, GL11.GL_RGBA, data.getWidth(),
                        data.getHeight(), 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, data.getDecodedbytes());
                faces[i] = data;
            }
            GL30.glGenerateMipmap(GL13.GL_TEXTURE_CUBE_MAP);
            GL11.glTexParameteri(GL13.GL_TEXTURE_CUBE_MAP, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
            GL11.glTexParameteri(GL13.GL_TEXTURE_CUBE_MAP, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR_MIPMAP_LINEAR);
            GL11.glTexParameteri(GL13.GL_TEXTURE_CUBE_MAP, GL11.GL_TEXTURE_WRAP_S, GL12.GL_CLAMP_TO_EDGE);
            GL11.glTexParameteri(GL13.GL_TEXTURE_CUBE_MAP, GL11.GL_TEXTURE_WRAP_T, GL12.GL_CLAMP_TO_EDGE);
            mesh = new Mesh(Utils.CubeAsVertex3D(1000));
        });
    }

    public CubeMap(int File, int Archive) {

        Application.Awake.addListener(()->{
            ID = GL11.glGenTextures();
            GL13.glActiveTexture(GL13.GL_TEXTURE4);
            GL11.glEnable(GL13.GL_TEXTURE_CUBE_MAP);
            GL11.glBindTexture(GL13.GL_TEXTURE_CUBE_MAP, ID);

            for (int i = 0; i < FacesAmnt; i++) {
                Texture tex = Application.getResourceManager().GetTexture(File ,Archive);
                GL11.glTexImage2D(
                        GL13.GL_TEXTURE_CUBE_MAP_POSITIVE_X + i, 0, GL11.GL_RGBA,
                        (int) tex.getWidth(), (int) tex.getHeight(), 0,
                        GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, tex.getDecodedbytes());
                faces[i] = tex;
            }
            GL11.glTexParameteri(GL13.GL_TEXTURE_CUBE_MAP, GL11.GL_TEXTURE_WRAP_S, GL12.GL_CLAMP_TO_EDGE);
            GL11.glTexParameteri(GL13.GL_TEXTURE_CUBE_MAP, GL11.GL_TEXTURE_WRAP_T, GL12.GL_CLAMP_TO_EDGE);
            mesh = new Mesh(Utils.CubeAsVertex3D(600));
        });
    }

    public CubeMap(int[] File, int[] Archive) {

        Application.Awake.addListener(()->{
            ID = GL11.glGenTextures();
            GL13.glActiveTexture(GL13.GL_TEXTURE4);
            GL11.glEnable(GL13.GL_TEXTURE_CUBE_MAP);
            GL11.glBindTexture(GL13.GL_TEXTURE_CUBE_MAP, ID);

            for (int i = 0; i < FacesAmnt; i++) {
                Texture tex = Application.getResourceManager().GetTexture(File[i], Archive[i]);
                GL11.glTexImage2D(
                        GL13.GL_TEXTURE_CUBE_MAP_POSITIVE_X + i, 0, GL11.GL_RGBA,
                        (int) tex.getWidth(), (int) tex.getHeight(), 0,
                        GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, tex.getDecodedbytes());
                faces[i] = tex;
            }
            GL13.glTexParameteri(GL13.GL_TEXTURE_CUBE_MAP, GL11.GL_TEXTURE_WRAP_S, GL12.GL_CLAMP_TO_EDGE);
            GL11.glTexParameteri(GL13.GL_TEXTURE_CUBE_MAP, GL11.GL_TEXTURE_WRAP_T, GL12.GL_CLAMP_TO_EDGE);
            mesh = new Mesh(Utils.CubeAsVertex3D(600));
        });
    }


    public Texture GetFace(int face) {
        if(face < 0 || face > FacesAmnt) {
            return null;
        }
        return faces[face];
    }

    public int getID() {
        return ID;
    }
}
