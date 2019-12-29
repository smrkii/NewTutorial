package toolbox;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import entities.Camera;

public class Maths {

    public static Matrix4f createTransformationMatrix(Vector3f translation, float rx, float ry,
                                                      float rz, float scale) {
        Matrix4f matrix = new Matrix4f();
        matrix.setIdentity();
        Matrix4f.translate(translation, matrix, matrix);
        Matrix4f.rotate((float) Math.toRadians(rx), new Vector3f(1,0,0), matrix, matrix);
        Matrix4f.rotate((float) Math.toRadians(ry), new Vector3f(0,1,0), matrix, matrix);
        Matrix4f.rotate((float) Math.toRadians(rz), new Vector3f(0,0,1), matrix, matrix);
        Matrix4f.scale(new Vector3f(scale,scale,scale), matrix, matrix);
        return matrix;
    }

    public static Matrix4f createViewMatrix(Camera camera) {
        Matrix4f viewMatrix = new Matrix4f();
        viewMatrix.setIdentity();
        Matrix4f.rotate((float) Math.toRadians(camera.getPitch()), new Vector3f(1, 0, 0), viewMatrix,
                viewMatrix);
        Matrix4f.rotate((float) Math.toRadians(camera.getYaw()), new Vector3f(0, 1, 0), viewMatrix, viewMatrix);
        Vector3f cameraPos = camera.getPosition();
        Vector3f negativeCameraPos = new Vector3f(-cameraPos.x,-cameraPos.y,-cameraPos.z);
        Matrix4f.translate(negativeCameraPos, viewMatrix, viewMatrix);
        return viewMatrix;
    }

    public static float barryCentric(Vector3f p1, Vector3f p2, Vector3f p3, Vector2f pos) {
        float det = (p2.z - p3.z) * (p1.x - p3.x) + (p3.x - p2.x) * (p1.z - p3.z);
        float l1 = ((p2.z - p3.z) * (pos.x - p3.x) + (p3.x - p2.x) * (pos.y - p3.z)) / det;
        float l2 = ((p3.z - p1.z) * (pos.x - p3.x) + (p1.x - p3.x) * (pos.y - p3.z)) / det;
        float l3 = 1.0f - l1 - l2;
        return l1 * p1.y + l2 * p2.y + l3 * p3.y;
    }

    public static Vector3f crossProduct(Vector3f v1, Vector3f v2){
        float x = v1.y*v2.z - v1.z*v2.y;

        float y = v1.z*v2.x - v1.x*v2.z;

        float z = v1.x*v2.y - v1.y* v2.x;

        return new Vector3f(x,y,z);
    }

    public static int NormalAngle(Vector3f vector,Vector3f vector2){

        double rez1 = vector2.x*vector.x+vector2.y*vector.y+vector2.z*vector.z;

        double rez2 =  Math.pow(vector.x,2)+Math.pow(vector.y,2)+Math.pow(vector.z,2);
        double rez3 = rez1 / Math.sqrt(rez2);


        return (int) Math.toDegrees(Math.acos(rez3));
    }

    public static Matrix4f createTransformationMatrix(Vector2f translation, Vector2f scale) {
        Matrix4f matrix = new Matrix4f();
        matrix.setIdentity();
        Matrix4f.translate(translation, matrix, matrix);
        Matrix4f.scale(new Vector3f(scale.x, scale.y, 1f), matrix, matrix);
        return matrix;
    }

    public static void main(String[] args) {
        //Vector3f vr = crossProduct(new Vector3f(5,-19,-30),new Vector3f(-4,30,-8));
        //System.out.println(vr.x+" "+vr.y+" "+vr.z);

        //System.out.println(NormalAngle(new Vector3f(10,-4,3)));
    }

}