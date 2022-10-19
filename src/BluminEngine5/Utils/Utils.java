package BluminEngine5.Utils;

import BluminEngine5.Application;
import BluminEngine5.Rendering.Texture;
import BluminEngine5.Rendering.UI.Obj.Vertex;
import BluminEngine5.Utils.Debuging.Debug;
import BluminEngine5.Utils.Math.Vector2;
import BluminEngine5.Utils.Math.Vector3;
import org.lwjgl.openal.AL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import org.lwjgl.opengl.GL13;

import java.io.*;

public class Utils {
    public static String LoadFile(String fileLocation) {
        StringBuilder bufferSource = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileLocation));
            String line;
            while ((line = reader.readLine()) != null) {
                bufferSource.append(line).append("\n");
            }
            reader.close();
            return bufferSource.toString();
        } catch (IOException e) {
            CrashApp(-1, "Couldn't load file: " + fileLocation, e);
            return null;
        }
    }

    public static InputStream LoadFileAsStream(String fileLocation) {
        try {
            File initialFile = new File(fileLocation);
            InputStream stream = new FileInputStream(initialFile);

            return stream;
        } catch (FileNotFoundException e){
            Utils.CrashApp(-18, "Failed to load stream", e);
            return null;
        }
    }
    public static boolean FileExists(String file) {
        File tempFile = new File(file);
        return tempFile.exists();
    }
    public static void CrashApp(int status, String reason) {
        Debug.logError(reason);
        Application.OnExit.Invoke();
        System.exit(status);
    }
    public static void CrashApp(int status, String reason, Exception e) {
        Debug.logError(reason);
        e.printStackTrace();
        Application.OnExit.Invoke();
        System.exit(status);
    }
    public static void CrashApp(int status, Exception e) {
        Debug.logException(e);
        Application.OnExit.Invoke();
        System.exit(status);
    }

    public static void CrashApp(int status, Throwable e) {
        Debug.logException(e.getMessage(), e.getStackTrace());
        Application.OnExit.Invoke();
        System.exit(status);
    }

    public static String GetStacktrace(Exception e){
        String s = "";

        for (int i = 0; i < e.getStackTrace().length; i++) {
            s += e.getStackTrace()[i] + "\n";
        }

        return  s;
    }

    public static String GetStacktrace(StackTraceElement[] e){
        String s = "";

        for (int i = 0; i < e.length; i++) {
            s += e[i] + "\n";
        }

        return  s;
    }

    public static Vertex[] QuadAsVertex2D() {
        Vertex vpos[] = {
                new Vertex(new Vector2(-1,1)),
                new Vertex(new Vector2(-1,-1)),
                new Vertex(new Vector2(1,1)),
                new Vertex(new Vector2(1,-1)),
        };
        return vpos;
    }


    public static BluminEngine5.Rendering.Vertex[] CubeAsVertex3D(int SIZE ) {
        BluminEngine5.Rendering.Vertex vpos[] = {
                new BluminEngine5.Rendering.Vertex(new Vector3(-SIZE,  SIZE, -SIZE)),
                new BluminEngine5.Rendering.Vertex(new Vector3(-SIZE, -SIZE, -SIZE)),
                new BluminEngine5.Rendering.Vertex(new Vector3(SIZE, -SIZE, -SIZE)),
                new BluminEngine5.Rendering.Vertex(new Vector3(SIZE, -SIZE, -SIZE)),
                new BluminEngine5.Rendering.Vertex(new Vector3(SIZE,  SIZE, -SIZE)),
                new BluminEngine5.Rendering.Vertex(new Vector3(-SIZE,  SIZE, -SIZE)),

                new BluminEngine5.Rendering.Vertex(new Vector3(-SIZE, -SIZE,  SIZE)),
                new BluminEngine5.Rendering.Vertex(new Vector3(-SIZE, -SIZE, -SIZE)),
                new BluminEngine5.Rendering.Vertex(new Vector3(-SIZE,  SIZE, -SIZE)),
                new BluminEngine5.Rendering.Vertex(new Vector3(-SIZE,  SIZE, -SIZE)),
                new BluminEngine5.Rendering.Vertex(new Vector3(-SIZE,  SIZE,  SIZE)),
                new BluminEngine5.Rendering.Vertex(new Vector3(-SIZE, -SIZE,  SIZE)),

                new BluminEngine5.Rendering.Vertex(new Vector3(SIZE, -SIZE, -SIZE)),
                new BluminEngine5.Rendering.Vertex(new Vector3(SIZE, -SIZE,  SIZE)),
                new BluminEngine5.Rendering.Vertex(new Vector3(SIZE,  SIZE,  SIZE)),
                new BluminEngine5.Rendering.Vertex(new Vector3(SIZE,  SIZE,  SIZE)),
                new BluminEngine5.Rendering.Vertex(new Vector3(SIZE,  SIZE, -SIZE)),
                new BluminEngine5.Rendering.Vertex(new Vector3(SIZE, -SIZE, -SIZE)),

                new BluminEngine5.Rendering.Vertex(new Vector3(-SIZE, -SIZE,  SIZE)),
                new BluminEngine5.Rendering.Vertex(new Vector3(-SIZE,  SIZE,  SIZE)),
                new BluminEngine5.Rendering.Vertex(new Vector3(SIZE,  SIZE,  SIZE)),
                new BluminEngine5.Rendering.Vertex(new Vector3(SIZE,  SIZE,  SIZE)),
                new BluminEngine5.Rendering.Vertex(new Vector3(SIZE, -SIZE,  SIZE)),
                new BluminEngine5.Rendering.Vertex(new Vector3(-SIZE, -SIZE,  SIZE)),

                new BluminEngine5.Rendering.Vertex(new Vector3(-SIZE,  SIZE, -SIZE)),
                new BluminEngine5.Rendering.Vertex(new Vector3(SIZE,  SIZE, -SIZE)),
                new BluminEngine5.Rendering.Vertex(new Vector3(SIZE,  SIZE,  SIZE)),
                new BluminEngine5.Rendering.Vertex(new Vector3(SIZE,  SIZE,  SIZE)),
                new BluminEngine5.Rendering.Vertex(new Vector3(-SIZE,  SIZE,  SIZE)),
                new BluminEngine5.Rendering.Vertex(new Vector3(-SIZE,  SIZE, -SIZE)),

                new BluminEngine5.Rendering.Vertex(new Vector3(-SIZE, -SIZE, -SIZE)),
                new BluminEngine5.Rendering.Vertex(new Vector3(-SIZE, -SIZE,  SIZE)),
                new BluminEngine5.Rendering.Vertex(new Vector3(SIZE, -SIZE, -SIZE)),
                new BluminEngine5.Rendering.Vertex(new Vector3(SIZE, -SIZE, -SIZE)),
                new BluminEngine5.Rendering.Vertex(new Vector3(-SIZE, -SIZE,  SIZE)),
                new BluminEngine5.Rendering.Vertex(new Vector3(SIZE, -SIZE,  SIZE)),



        };
        return vpos;
    }


    public static int CreateCubeMap(int[] File, int[] Archive) {
        int ID = GL11.glGenTextures();
        GL13.glActiveTexture(GL13.GL_TEXTURE4);
        GL11.glEnable(GL13.GL_TEXTURE_CUBE_MAP);
        GL11.glBindTexture(GL13.GL_TEXTURE_CUBE_MAP, ID);

        for (int i = 0; i < 6; i++) {
            Texture tex = Application.getResourceManager().GetTexture(File[i], Archive[i]);
            GL11.glTexImage2D(
                    GL13.GL_TEXTURE_CUBE_MAP_POSITIVE_X + i, 0, GL11.GL_RGBA,
                    (int) tex.getWidth(), (int) tex.getHeight(), 0,
                    GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, tex.getDecodedbytes());

        }
        GL13.glTexParameteri(GL13.GL_TEXTURE_CUBE_MAP, GL11.GL_TEXTURE_WRAP_S, GL12.GL_CLAMP_TO_EDGE);
        GL11.glTexParameteri(GL13.GL_TEXTURE_CUBE_MAP, GL11.GL_TEXTURE_WRAP_T, GL12.GL_CLAMP_TO_EDGE);
        return ID;
    }

    public static int CreateCubeMap(int File, int Archive) {
        int ID = GL11.glGenTextures();
        GL13.glActiveTexture(GL13.GL_TEXTURE4);
        GL11.glEnable(GL13.GL_TEXTURE_CUBE_MAP);
        GL11.glBindTexture(GL13.GL_TEXTURE_CUBE_MAP, ID);

        for (int i = 0; i < 6; i++) {
            Texture tex = Application.getResourceManager().GetTexture(File, Archive);
            GL11.glTexImage2D(
                    GL13.GL_TEXTURE_CUBE_MAP_POSITIVE_X + i, 0, GL11.GL_RGBA,
                    (int) tex.getWidth(), (int) tex.getHeight(), 0,
                    GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, tex.getDecodedbytes());

        }
        GL13.glTexParameteri(GL13.GL_TEXTURE_CUBE_MAP, GL11.GL_TEXTURE_WRAP_S, GL12.GL_CLAMP_TO_EDGE);
        GL11.glTexParameteri(GL13.GL_TEXTURE_CUBE_MAP, GL11.GL_TEXTURE_WRAP_T, GL12.GL_CLAMP_TO_EDGE);
        return ID;
    }

    public static int CreateCubeMap(int shh) {
        int ID = GL11.glGenTextures();
        GL13.glActiveTexture(GL13.GL_TEXTURE4);
        GL11.glEnable(GL13.GL_TEXTURE_CUBE_MAP);
        GL11.glBindTexture(GL13.GL_TEXTURE_CUBE_MAP, ID);

        for (int i = 0; i < 6; i++) {
            Texture tex = Application.getResourceManager().GetTexture(0, shh);
            GL11.glTexImage2D(
                    GL13.GL_TEXTURE_CUBE_MAP_POSITIVE_X + i, 0, GL11.GL_RGBA,
                    (int) tex.getWidth(), (int) tex.getHeight(), 0,
                    GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, tex.getDecodedbytes());
        }
        GL13.glTexParameteri(GL13.GL_TEXTURE_CUBE_MAP, GL11.GL_TEXTURE_WRAP_S, GL12.GL_CLAMP_TO_EDGE);
        GL11.glTexParameteri(GL13.GL_TEXTURE_CUBE_MAP, GL11.GL_TEXTURE_WRAP_T, GL12.GL_CLAMP_TO_EDGE);
        return ID;
    }

}
