package entities;

import models.TexturedModel;

import org.lwjgl.util.vector.Vector3f;

public class Entity {

    private TexturedModel model;
    private Vector3f position;
    private float rotX, rotY, rotZ;
    private float scale;

    private float MaxX;
    private float MaxY;
    private float MaxZ;

    private float MinX;
    private float MinY;
    private float MinZ;



    public Entity(float[] vertexes,TexturedModel model, Vector3f position, float rotX, float rotY, float rotZ,
                  float scale) {
        this.model = model;
        this.position = position;
        this.rotX = rotX;
        this.rotY = rotY;
        this.rotZ = rotZ;
        this.scale = scale;

        setMaxMinVertexes(vertexes);
    }

    public void increasePosition(float dx, float dy, float dz) {
        this.position.x += dx;
        this.position.y += dy;
        this.position.z += dz;
    }

    public void increaseRotation(float dx, float dy, float dz) {
        this.rotX += dx;
        this.rotY += dy;
        this.rotZ += dz;
    }

    public void setMaxMinVertexes(float[] vertices){
        float CurrX;
        float CurrY;
        float CurrZ;

        float MaxX=vertices[0];
        float MaxY=vertices[1];
        float MaxZ=vertices[2];

        float MinX=vertices[0];
        float MinY=vertices[1];
        float MinZ=vertices[2];


        for(int i =0;i<vertices.length;){
            CurrX=vertices[i];
            CurrY=vertices[i+1];
            CurrZ=vertices[i+2];

            if(CurrX>=MaxX){MaxX = CurrX;}
            if(CurrY>=MaxY){MaxY = CurrY;}
            if(CurrZ>=MaxZ){MaxZ = CurrZ;}

            if(CurrX<=MinX){MinX = CurrX;}
            if(CurrY<=MinY){MinY = CurrY;}
            if(CurrZ<=MinZ){MinZ = CurrZ;}

            i+=3;

        }

        this.MaxX=MaxX;
        this.MaxY=MaxY;
        this.MaxZ=MaxZ;

        this.MinX=MinX;
        this.MinY=MinY;
        this.MinZ=MinZ;

    }


    public TexturedModel getModel() {
        return model;

    }

    public void setModel(TexturedModel model) {
        this.model = model;
    }

    public Vector3f getPosition() {
        return position;
    }

    public void setPosition(Vector3f position) {
        this.position = position;
    }

    public float getRotX() {
        return rotX;
    }

    public void setRotX(float rotX) {
        this.rotX = rotX;
    }

    public float getRotY() {
        return rotY;
    }

    public void setRotY(float rotY) {
        this.rotY = rotY;
    }

    public float getRotZ() {
        return rotZ;
    }

    public void setRotZ(float rotZ) {
        this.rotZ = rotZ;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public float getMaxX() {
        return MaxX;
    }

    public float getMaxY() {
        return MaxY;
    }

    public float getMaxZ() {
        return MaxZ;
    }

    public float getMinX() {
        return MinX;
    }

    public float getMinY() {
        return MinY;
    }

    public float getMinZ() {
        return MinZ;
    }
}