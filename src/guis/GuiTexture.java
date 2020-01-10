package guis;

import org.lwjgl.util.vector.Vector2f;

public class GuiTexture {

    private int texture;
    private Vector2f scale;
    private Vector2f position;

    public GuiTexture(int texture, Vector2f scale, Vector2f position) {
        this.texture = texture;
        this.scale = scale;
        this.position = position;
    }

    public int getTexture() {
        return texture;
    }

    public Vector2f getScale() {
        return scale;
    }

    public Vector2f getPosition() {
        return position;
    }

    public void inscreaseScaleX(float x){
        this.scale.x=this.scale.x+x;
    }

    public void inscreaseScaleY(float y){
        this.scale.y=y;
    }

    public void setScaleX(float x){
        this.scale.x=x;
    }

    public void inscreasePositionX(float x){
        this.position.x=this.position.x+x;
    }

    public void inscreasePositionY(float y){
        this.position.y=y;
    }

    public void setPositionX(float x){
        this.position.x=x;
    }


}
