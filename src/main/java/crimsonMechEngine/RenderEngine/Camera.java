package crimsonMechEngine.RenderEngine;

import org.joml.Vector3f;

public class Camera {

    private Vector3f position = new Vector3f();

    private float angle = 0;
    private float distance = 3f;

    private float pitch = 10;
    private float yaw;
    private float roll;
    private float theta;

    private float hdistance;
    private float vdistance;

    private Entity focus;


    //follow camera
    public Camera(Entity entity){
        focus = entity;
    }

    //free camera
    public Camera(Vector3f vector){
        this.position = vector;
    }

    //forced perspective camera
    public void setPosition(Vector3f position) {
        this.position = position;
    }

    public void move(){

        hdistance = calculateHorizontalDistance();
        vdistance = calculateVerticalDistance();
        cameraPosition();
    }

    public void setPitch(int pitchChange){
            pitch -= pitchChange;
    }

    public void setAngle(){

    }

    private float calculateHorizontalDistance(){
        return (float)( distance * Math.cos(Math.toRadians(pitch)));
    }

    private float calculateVerticalDistance(){
        return (float)( distance * Math.sin(Math.toRadians(pitch)));
    }

    private void cameraPosition(){
        position.y = focus.getPosition().y + vdistance + 4;
        float theta = focus.getRoty() + angle;
        float offsetX = (float) (hdistance * Math.sin(Math.toRadians(theta)));
        float offsetZ = (float) (hdistance * Math.cos(Math.toRadians(theta)));
        position.x = focus.getPosition().x - offsetX;
        position.z = focus.getPosition().z - offsetZ;
        yaw = 180 - focus.getRoty() + angle;
    }

    public Vector3f getPosition() {
        return position;
    }

    public float getPitch() {
        return pitch;
    }

    public float getRoll() {
        return roll;
    }

    public float getYaw() {
        return yaw;
    }
}
