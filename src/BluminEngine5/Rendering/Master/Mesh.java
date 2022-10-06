package BluminEngine5.Rendering.Master;

import BluminEngine5.Rendering.Vertex;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.system.MemoryUtil;

import java.io.Serializable;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class Mesh implements Serializable {
    private Vertex[] vertecies;
    private int[] indecies;
    private int vao, pbo, ibo, cbo, tbo, nbo;
    private Material mat;

    public Mesh(Vertex[] vertecies, int[] indecies) {
        this.vertecies = vertecies;
        this.indecies = indecies;
        mat = new Material();
    }

    public Mesh(Vertex[] vertecies, int[] indecies, Material m) {
        this.vertecies = vertecies;
        this.indecies = indecies;
        mat = m;
    }

    public void Creat(){
        mat.Creat();

        vao = GL30.glGenVertexArrays();
        GL30.glBindVertexArray(vao);

        FloatBuffer positionBuffer = MemoryUtil.memAllocFloat(vertecies.length * 3);
        float[] positionData = new float[vertecies.length * 3];
        for (int i = 0; i < vertecies.length; i++) {
            positionData[i * 3] = vertecies[i].getPosition().x;
            positionData[i * 3 + 1] = vertecies[i].getPosition().y;
            positionData[i * 3 + 2] = vertecies[i].getPosition().z;
        }
        positionBuffer.put(positionData).flip();

        pbo = glStoreBuffer(positionBuffer, 0, 3);



        FloatBuffer colorBuffer = MemoryUtil.memAllocFloat(vertecies.length * 4);
        float[] colorData = new float[vertecies.length * 4];
        for (int i = 0; i < vertecies.length; i++) {
            colorData[i * 4] = mat.getColor().GetR();
            colorData[i * 4 + 1] = mat.getColor().GetG();
            colorData[i * 4 + 2] = mat.getColor().GetB();
            colorData[i * 4 + 3] = mat.getColor().GetA();
        }
        colorBuffer.put(colorData).flip();
        cbo = glStoreBuffer(colorBuffer, 1, 4);


        FloatBuffer textBuffer = MemoryUtil.memAllocFloat(vertecies.length * 2);
        float[] texData = new float[vertecies.length * 2];
        for (int i = 0; i < vertecies.length; i++) {
            texData[i * 2] = vertecies[i].getTexturePosition().x;
            texData[i * 2 + 1] = vertecies[i].getTexturePosition().y;
        }
        textBuffer.put(texData).flip();

        tbo = glStoreBuffer(textBuffer, 2, 2);


        FloatBuffer normalsBuffer = MemoryUtil.memAllocFloat(vertecies.length * 3);
        float[]  normalsData = new float[vertecies.length * 3];
        for (int i = 0; i < vertecies.length; i++) {
            normalsData[i * 3] = vertecies[i].getNormals().x;
            normalsData[i * 3 + 1] = vertecies[i].getNormals().y;
            normalsData[i * 3 + 2] = vertecies[i].getNormals().z;
        }
        normalsBuffer.put(normalsData).flip();

        nbo = glStoreBuffer(normalsBuffer, 3, 3);


        IntBuffer indeciesBuffer = MemoryUtil.memAllocInt(indecies.length);
        indeciesBuffer.put(indecies).flip();
        ibo = GL15.glGenBuffers();
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER,ibo);
        GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, indeciesBuffer, GL15.GL_STATIC_DRAW);
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER,0);
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
        GL15.glDeleteBuffers(pbo);
        GL15.glDeleteBuffers(ibo);
        GL15.glDeleteBuffers(cbo);
        GL15.glDeleteBuffers(tbo);
        GL30.glDeleteVertexArrays(vao);
    }

    public Vertex[] getVertecies() {
        return vertecies;
    }
    public int[] getIndecies() {
        return indecies;
    }
    public int getVAO() {
        return vao;
    }
    public int getPBO() {
        return pbo;
    }
    public int getIBO() {
        return ibo;
    }
    public int getCBO() {
        return cbo;
    }
    public int getTBO() {
        return tbo;
    }
    public int getNBO() {
        return nbo;
    }

    public Material getMaterial() {
        return mat;
    }
}
