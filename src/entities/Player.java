package entities;

import models.TexturedModel;
import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;
import renderEngine.DisplayManager;
import terrains.Terrain;

import java.util.ArrayList;
import java.util.List;

public class Player extends Entity {

    List<Entity> entities = new ArrayList<Entity>();


    private static final float RUN_SPEED = 100;
    private static final float TURN_SPEED = 160;
    private static final float GRAVITY = -50;
    private static final float JUMP_POWER = 0;


    private float currentSpeed=0;
    private float currentTurnSpeed=0;
    private float upwardSpeed=0;
    private boolean backward=false;
    private boolean isInAir=false;
    private char keyDown='\0';


    public Player(List<Entity> entities,float[] vertexes,TexturedModel model, Vector3f position, float rotX, float rotY, float rotZ, float scale) {
        super(vertexes,model, position, rotX, rotY, rotZ, scale);

        this.entities=entities;
    }

    public void move(Terrain terrain){
        if(!isInAir){
            checkInputs();
        }

        checkCollision();


        super.increaseRotation(0,currentTurnSpeed * DisplayManager.getFrameTimeSeconds(),0);
        float distance = currentSpeed * DisplayManager.getFrameTimeSeconds();
        float dx = (float) (distance * Math.sin(Math.toRadians(-super.getRotY())));
        float dz = (float) (distance * Math.cos(Math.toRadians(-super.getRotY())));
        super.increasePosition(dz, 0, dx);

        upwardSpeed += GRAVITY * DisplayManager.getFrameTimeSeconds();
        super.increasePosition(0, upwardSpeed*DisplayManager.getFrameTimeSeconds(),0);

        float terrainHeight = terrain.getHeightOfTerrain(super.getPosition().x,super.getPosition().z);

        if(super.getPosition().y < terrainHeight){
            upwardSpeed=0;
            isInAir=false;
            super.getPosition().y= terrainHeight;
        }
    }

    private void jump(){
        if(!isInAir){
            this.upwardSpeed= JUMP_POWER;
            isInAir = true;
        }


    }

    private void checkInputs(){
        if(Keyboard.isKeyDown(Keyboard.KEY_W)){
            keyDown='W';
            backward=false;
            this.currentSpeed+=0.5;
            if(this.currentSpeed>RUN_SPEED){this.currentSpeed= RUN_SPEED;}

        }
        else if(Keyboard.isKeyDown(Keyboard.KEY_S)){
            keyDown='S';
            backward=true;
            this.currentSpeed-=0.5f;
            if(this.currentSpeed<-RUN_SPEED){this.currentSpeed= -RUN_SPEED;}
        }
        else{

            if(backward){
                this.currentSpeed+=0.5f;
                if(this.currentSpeed>=0){this.currentSpeed=0;}
            }
            else{
                this.currentSpeed-=0.5f;
                if(this.currentSpeed<=0){this.currentSpeed=0;}
            }
        }


        if(Keyboard.isKeyDown(Keyboard.KEY_D)){
            keyDown='D';
            this.currentTurnSpeed=-TURN_SPEED;
        }
        else if(Keyboard.isKeyDown(Keyboard.KEY_A)){
            keyDown='A';
            this.currentTurnSpeed=TURN_SPEED;
        }
        else{

            this.currentTurnSpeed=0;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)){
            jump();
        }
    }

    private boolean checkCollision(){
        //System.out.println(this.entities);
            for(Entity entity : this.entities){
                if(((this.getPosition().x+this.getMinX()) <= (entity.getPosition().x+entity.getMaxX()) && (this.getPosition().x+this.getMaxX()) >= (entity.getPosition().x+entity.getMinX())) &&
                    //(this.getMinY() <= entity.getMaxY() && this.getMaxY() >= entity.getMinY()) &&
                    ((this.getPosition().z+this.getMinZ()) <= (entity.getPosition().z+entity.getMaxZ()) && (this.getPosition().z+this.getMaxZ()) >= (entity.getPosition().z+entity.getMinZ())) ){

                    if(this.keyDown=='W' || this.keyDown=='A' || this.keyDown=='D'){
                        this.currentSpeed=0;
                    }

                    return true;
                }



            }
            return false;

    }
}
