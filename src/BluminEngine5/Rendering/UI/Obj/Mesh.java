package BluminEngine5.Rendering.UI.Obj;

import BluminEngine5.Rendering.Master.Material;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.system.MemoryUtil;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class Mesh {
    private Vertex[] vertecies;
    private int vao, pbo, cbo;
    private Material mat;
    public Mesh(Vertex[] vertecies) {
        this.vertecies = vertecies;

        mat = new Material();
    }
    public Mesh(Vertex[] vertecies, Material m) {
        this.vertecies = vertecies;

        mat = m;
    }
    public void Creat(){
        mat.Creat();

        vao = GL30.glGenVertexArrays();
        GL30.glBindVertexArray(vao);

        FloatBuffer positionBuffer = MemoryUtil.memAllocFloat(vertecies.length * 2);
        float[] positionData = new float[vertecies.length * 2];
        for (int i = 0; i < vertecies.length; i++) {
            positionData[i * 2] = vertecies[i].getPosition().x;
            positionData[i * 2 + 1] = vertecies[i].getPosition().y;
        }
        positionBuffer.put(positionData).flip();
        pbo = glStoreBuffer(positionBuffer, 0, 2);

        FloatBuffer colorBuffer = MemoryUtil.memAllocFloat(vertecies.length * 3);
        float[] colorData = new float[vertecies.length * 3];
        for (int i = 0; i < vertecies.length; i++) {
            colorData[i * 3] = mat.getColor().GetR();
            colorData[i * 3 + 1] = mat.getColor().GetG();
            colorData[i * 3 + 2] = mat.getColor().GetB();
        }
        colorBuffer.put(colorData).flip();

        cbo = glStoreBuffer(colorBuffer, 1, 3);
    }
    private int glStoreBuffer(FloatBuffer bufferdata, int index, int size){
        int Bufferid = GL15.glGenBuffers();
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER,Bufferid);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, bufferdata, GL15.GL_STATIC_DRAW);
        GL20.glVertexAttribPointer(index, size, GL20.GL_FLOAT, false, 0 ,0);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER,0);
        return Bufferid;
    }
    public void Destroy() {
        mat.Destroy();
        GL30.glDeleteVertexArrays(vao);
    }
    public Vertex[] getVertecies() {
        return vertecies;
    }
    public int getVAO() {
        return vao;
    }
    public int getPBO() {
        return pbo;
    }
    public int getCBO() {
        return cbo;
    }
    public Material getMaterial() {
        return mat;
    }
}
