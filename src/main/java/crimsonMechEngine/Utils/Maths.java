package crimsonMechEngine.Utils;

import crimsonMechEngine.RenderEngine.Camera;
import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;

public class Maths {

    public static Matrix4f createTransformationMatrix(Vector2f translation, Vector2f scale){
        Matrix4f matrix = new Matrix4f();
        matrix.translate(translation.x, translation.y, 0);
        matrix.scale(scale.x, scale.y, 0);
        return matrix;
    }

    public static Matrix4f createTransformationMatrix(Vector3f translation, float rx, float ry, float rz, float scale){
        Matrix4f matrix = new Matrix4f();
        matrix.translate(translation);
        matrix.rotate((float) Math.toRadians(rx), 1.0f, 0.0f, 0.0f);
        matrix.rotate((float) Math.toRadians(ry), 0.0f, 1.0f, 0.0f);
        matrix.rotate((float) Math.toRadians(rz), 0.0f, 0.0f, 1.0f);
        matrix.scale(scale);
        return matrix;
    }

    public static Matrix4f createViewMatrix(Camera camera){
        Matrix4f viewMatrix = new Matrix4f();
        viewMatrix.rotate((float) Math.toRadians(camera.getPitch()), 1.0f, 0.0f, 0.0f);
        viewMatrix.rotate((float) Math.toRadians(camera.getYaw()), 0.0f, 1.0f, 0.0f);
        viewMatrix.rotate((float) Math.toRadians(camera.getRoll()), 0.0f, 0.0f, 1.0f);
        Vector3f cameraPos = camera.getPosition();
        Vector3f negativeCameraPos = new Vector3f(-cameraPos.x, -cameraPos.y, -cameraPos.z);
        viewMatrix.translate(negativeCameraPos);
        return viewMatrix;
    }
}
