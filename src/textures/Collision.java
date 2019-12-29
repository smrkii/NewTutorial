package textures;

import java.util.Arrays;

public class Collision {

    public void collisionDetected(){

    }



    public static void findMaxVertexes(float[] vertices){
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
        System.out.println("X:"+MaxX+" Y:"+MaxY+" Z:"+MaxZ);
        System.out.println("X:"+MinX+" Y:"+MinY+" Z:"+MinZ);

    }

    public static void main(String[] args) {
        float vertexes[] = {-1.0f, -1.0f, 1.0f, -0.117219f, 1.0f, 0.117219f, -1.0f, -1.0f, -1.0f, -0.117219f, 1.0f, -0.117219f, 1.0f, -1.0f, 1.0f, 0.117219f, 1.0f, 0.117219f, 1.0f, -1.0f, -1.0f, 0.117219f, 1.0f, -0.117219f, -1.0f, -1.0f, -1.0f, -1.0f, -1.0f, -1.0f, -1.0f, -1.0f, 1.0f, -1.0f, -1.0f, 1.0f, -0.117219f, 1.0f, 0.117219f, -0.117219f, 1.0f, 0.117219f, -0.117219f, 1.0f, -0.117219f, -0.117219f, 1.0f, -0.117219f, 1.0f, -1.0f, -1.0f, 1.0f, -1.0f, -1.0f, 0.117219f, 1.0f, -0.117219f, 0.117219f, 1.0f, -0.117219f, 1.0f, -1.0f, 1.0f, 1.0f, -1.0f, 1.0f, 0.117219f, 1.0f, 0.117219f, 0.117219f, 1.0f, 0.117219f};
        Collision.findMaxVertexes(vertexes);
    }

}
