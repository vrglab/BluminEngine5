package BluminEngine5.Rendering.Shaders;

import BluminEngine5.Utils.Debuging.Debug;
import BluminEngine5.Utils.Math.*;
import BluminEngine5.Utils.Utils;
import org.json.JSONObject;
import org.lwjgl.opengl.*;
import org.lwjgl.system.MemoryUtil;

import java.nio.FloatBuffer;

public class Shader {

    private String VertexShader, FragmentShader, name;
    private int vertexId, fragmentId, programid;
    private boolean Raytracing;


    public Shader(String ShaderFile) {
        String data = Utils.LoadFile(ShaderFile);
        JSONObject obj = new JSONObject(data);
        name = obj.getString("name");
        Raytracing = obj.getBoolean("Raytracing");
        VertexShader = Utils.LoadFile(obj.getString("Vertex"));
        FragmentShader = Utils.LoadFile(obj.getString("Fragment"));
    }

    public void Creat() {
        programid = GL20.glCreateProgram();
        vertexId = GL20.glCreateShader(GL20.GL_VERTEX_SHADER);
        GL20.glShaderSource(vertexId, VertexShader);
        GL20.glCompileShader(vertexId);

        if(GL20.glGetShaderi(vertexId, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE) {
            Debug.logError(GL20.glGetShaderInfoLog(vertexId));
            Utils.CrashApp(-14, "Failed to compile vertex shader");
        }

        fragmentId = GL20.glCreateShader(GL20.GL_FRAGMENT_SHADER);
        GL20.glShaderSource(fragmentId, FragmentShader);
        GL20.glCompileShader(fragmentId);

        if(GL20.glGetShaderi(fragmentId, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE) {
            Debug.logError(GL20.glGetShaderInfoLog(fragmentId));
            Utils.CrashApp(-15, "Failed to compile fragment shader");
        }

        GL20.glAttachShader(programid, vertexId);
        GL20.glAttachShader(programid, fragmentId);

        GL20.glLinkProgram(programid);
        if(GL20.glGetProgrami(programid, GL20.GL_LINK_STATUS) == GL11.GL_FALSE) {
            Debug.logError(GL20.glGetProgramInfoLog(programid));
            Utils.CrashApp(-16, "Failed to link shader program");
        }

        GL20.glValidateProgram(programid);
        if(GL20.glGetProgrami(programid, GL20.GL_VALIDATE_STATUS) == GL11.GL_FALSE) {
            Debug.logError(GL20.glGetProgramInfoLog(programid));
            Utils.CrashApp(-17, "Failed to validate shader program");
        }
    }

    public int GetUniformLocation(String name) {
        return GL20.glGetUniformLocation(programid, name);
    }

    public void SetUniform(String name, int data) {
        GL20.glUniform1i(GetUniformLocation(name), data);
    }
    public void SetUniform(String name, float data) {
        GL20.glUniform1f(GetUniformLocation(name), data);
    }
    public void SetUniform(String name, boolean data) {
        GL20.glUniform1i(GetUniformLocation(name), data ? 1 : 0);
    }
    public void SetUniform(String name, Vector3 data) {
        GL20.glUniform3f(GetUniformLocation(name), data.x, data.y, data.z);
    }
    public void SetUniform(String name, Vector2 data) {
        GL20.glUniform2f(GetUniformLocation(name), data.x, data.y);
    }
    public void SetUniform(String name, Matrix data) {
        FloatBuffer matrix = MemoryUtil.memAllocFloat(Matrix.SIZE * Matrix.SIZE);
        matrix.put(data.Get()).flip();
        GL20.glUniformMatrix4fv(GetUniformLocation(name), true, matrix);
    }

    public void Run() {
        GL20.glUseProgram(programid);
    }

    public void Stop() {
        GL20.glUseProgram(0);
    }

    public void Delete() {
        GL20.glDetachShader(programid, vertexId);
        GL20.glDetachShader(programid, fragmentId);
        GL20.glDeleteShader(vertexId);
        GL20.glDeleteShader(fragmentId);
        GL20.glDeleteProgram(programid);
    }

}
