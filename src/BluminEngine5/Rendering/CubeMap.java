package BluminEngine5.Rendering;

import BluminEngine5.Application;
import BluminEngine5.Behaviour.IObjLogic;
import BluminEngine5.Rendering.Master.Mesh;
import BluminEngine5.Rendering.Shaders.Shader;
import BluminEngine5.SceneMannagement.SceneManager;
import BluminEngine5.Utils.Debuging.Debug;
import BluminEngine5.Utils.Math.Matrix;
import BluminEngine5.Utils.Utils;
import org.lwjgl.opengl.*;

import static BluminEngine5.Utils.ResourceMannager.Archive.ArchiveMannager.NULL;
import static org.lwjgl.opengl.GL11.*;

public class CubeMap {

    private static final int FacesAmnt = 6;
    private Texture[] faces = new Texture[FacesAmnt];
    private int ID = 0;

    public Mesh mesh;

    Shader skyBoxShader = null;
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
        });

        Application.Init.addListener(()->{
            skyBoxShader = Application.getResourceManager().GetShader("Res/Shaders/Default/Cubemap/DefaultGameShader.json");
            skyBoxShader.Creat();
            mesh = new Mesh(Utils.CubeAsVertex3D(1000));
            mesh.Creat();
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

        });

        Application.Init.addListener(()->{
            skyBoxShader = Application.getResourceManager().GetShader("Res/Shaders/Default/Cubemap/DefaultGameShader.json");
            skyBoxShader.Creat();
            mesh = new Mesh(Utils.CubeAsVertex3D(1000));
            mesh.Creat();
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

        });

        Application.Init.addListener(()->{
            skyBoxShader = Application.getResourceManager().GetShader("Res/Shaders/Default/Cubemap/DefaultGameShader.json");
            skyBoxShader.Creat();
            mesh = new Mesh(Utils.CubeAsVertex3D(1000));
            mesh.Creat();
        });
    }

    public void Render() {
        glDepthMask(false);
        skyBoxShader.Run();

        Matrix view = Matrix.view(SceneManager.GetCurent().GetActiveScene().ActiveCamera.transform.position,SceneManager.GetCurent().GetActiveScene().ActiveCamera.transform.rotation);
        view.Set(3,0,0);
        view.Set(3,1,0);
        view.Set(3,2,0);

        skyBoxShader.SetUniform("ProjectionMatrix", SceneManager.GetCurent().GetActiveScene().ActiveCamera.getProjectionMatrix());
        skyBoxShader.SetUniform("ViewMatrix", view);

        GL30.glBindVertexArray(mesh.getVAO());
        GL20.glEnableVertexAttribArray(0);
        GL13.glActiveTexture(GL13.GL_TEXTURE4);
        GL11.glBindTexture(GL13.GL_TEXTURE_CUBE_MAP, getID());
        GL11.glDrawArrays(GL_TRIANGLES, 0, mesh.getVertecies().length);
        GL20.glDisableVertexAttribArray(0);
        GL30.glBindVertexArray(0);
        skyBoxShader.Stop();
        glDepthMask(true);
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
