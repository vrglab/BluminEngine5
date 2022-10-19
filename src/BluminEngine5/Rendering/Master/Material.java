package BluminEngine5.Rendering.Master;


import BluminEngine5.Application;
import BluminEngine5.Rendering.Color;
import BluminEngine5.Rendering.Texture;
import BluminEngine5.Rendering.Vertex;
import BluminEngine5.Utils.Annotations.MustCreate;
import BluminEngine5.Utils.Debuging.Debug;
import BluminEngine5.Utils.Math.Vector3;
import BluminEngine5.Utils.ResourceMannager.Archive.ArchivedFile;

import java.io.Serializable;
import java.util.UUID;

@MustCreate
public class Material implements Serializable {
    private Texture texture, DefuseMap,  SpecularMap, ReflectionsMap;
    private Color color;


    public Vector3 Ambient = new Vector3(0.31f,0.31f,0.31f);

    public float Shine = 0f;
    public float reflection = 0f;


    public Material() {
        texture = Application.getResourceManager().GetTexture(0, 5);
        DefuseMap = Application.getResourceManager().GetTexture(0,5);
        SpecularMap = Application.getResourceManager().GetTexture(0,5);
        ReflectionsMap = Application.getResourceManager().GetTexture(0,5);
        color = new Color(1,0.5f,1,1);
    }

    public Material(ArchivedFile text) {
        texture = Application.getResourceManager().GetTexture(text.ArchiveId, text.ID);
        DefuseMap = Application.getResourceManager().GetTexture(0,5);
        SpecularMap = Application.getResourceManager().GetTexture(0,5);
        ReflectionsMap = Application.getResourceManager().GetTexture(0,5);
        color = new Color(1,0.5f,1,1);
    }

    public Material(Texture text) {
        texture = text;
        DefuseMap = Application.getResourceManager().GetTexture(0,5);
        SpecularMap = Application.getResourceManager().GetTexture(0,5);
        ReflectionsMap = Application.getResourceManager().GetTexture(0,5);
        color = new Color(1,0.5f,1,1);
    }

    public void Creat() {
        texture.Create();
        DefuseMap.Create();
        SpecularMap.Create();
        ReflectionsMap.Create();
    }

    public void Destroy() {
        texture.Destroy();
        DefuseMap.Destroy();
        SpecularMap.Destroy();
        ReflectionsMap.Destroy();
    }

    public Texture getTexture() {
        return texture;
    }

    public Texture getDefuseMap() {
        return DefuseMap;
    }

    public Texture getSpecularMap() {
        return SpecularMap;
    }
    public Texture getReflectionsMap() {
        return ReflectionsMap;
    }

    public void SetTexture(Texture t) {
        texture = t;
    }

    public void setDefuseMap(Texture defuseMap) {
        DefuseMap = defuseMap;
    }

    public void setSpecularMap(Texture specularMap) {
        SpecularMap = specularMap;
    }

    public void setReflectionsMap(Texture ReflectionsMap) {
        this.ReflectionsMap = ReflectionsMap;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean equals(Material obj) {
        if(obj.texture.getTextureId() == texture.getTextureId()) {
            if(obj.getDefuseMap().getTextureId() == getDefuseMap().getTextureId()) {
                if(obj.getSpecularMap().getTextureId() == getSpecularMap().getTextureId()) {
                    if(obj.getReflectionsMap().getTextureId() == getReflectionsMap().getTextureId()) {
                        if(obj.Shine == Shine) {
                            if(obj.reflection == reflection) {
                                if(obj.Ambient == Ambient) {
                                    if(obj.getColor() == getColor()) {
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
}
