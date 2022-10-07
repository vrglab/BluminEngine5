package BluminEngine5.Rendering.Master;


import BluminEngine5.Application;
import BluminEngine5.Rendering.Color;
import BluminEngine5.Rendering.Texture;
import BluminEngine5.Rendering.Vertex;
import BluminEngine5.Utils.Debuging.Debug;
import BluminEngine5.Utils.Math.Vector3;
import BluminEngine5.Utils.ResourceMannager.Archive.ArchivedFile;

import java.util.UUID;

public class Material {
    private Texture texture, DefuseMap,  SpecularMap, SkyBox;
    private Color color;

    private UUID ID = UUID.randomUUID();

    public Vector3 Ambient = new Vector3(0.31f,0.31f,0.31f);

    public float Shine = 0f;
    public float reflection = 0f;


    public Material() {
        color = new Color(1,0.5f,1,1);
    }

    public Material(ArchivedFile text) {
        texture = Application.getResourceManager().GetTexture(text.ArchiveId, text.ID);
        DefuseMap = Application.getResourceManager().GetTexture(0,2);
        SpecularMap = Application.getResourceManager().GetTexture(0,2);
        SkyBox = Application.getResourceManager().GetTexture(0,2);
        color = new Color(1,0.5f,1,1);
    }

    public Material(Texture text) {
        texture = text;
        DefuseMap = Application.getResourceManager().GetTexture(0,2);
        SpecularMap = Application.getResourceManager().GetTexture(0,2);
        SkyBox = Application.getResourceManager().GetTexture(0,2);
        color = new Color(1,0.5f,1,1);
        Debug.log(ID);
    }

    public void Creat() {
        texture.Create();
        DefuseMap.Create();
        SpecularMap.Create();
        SkyBox.Create();
    }

    public void Destroy() {
        texture.Destroy();
        DefuseMap.Destroy();
        SpecularMap.Destroy();
        SkyBox.Destroy();
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
    public Texture getSkyBox() {
        return SkyBox;
    }

    public UUID getID() {
        return ID;
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

    public void setSkyBox(Texture skyBox) {
        SkyBox = skyBox;
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
                    if(obj.getSkyBox().getTextureId() == getSkyBox().getTextureId()) {
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
