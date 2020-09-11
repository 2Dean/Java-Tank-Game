package TankGame.GameObjects;

import crimsonMechEngine.RenderEngine.Entity;
import crimsonMechEngine.Model.TexturedModel;
import crimsonMechEngine.Utils.Timer;
import org.joml.Vector3f;

public class Bullet extends Entity {
    private float speed;
    private float drift;
    private float time;
    private float angleChange;
    private float distance;
    private float dx;
    private float dz;
    private float spin;
    private Timer movement;
    private boolean collide;

    public Bullet(TexturedModel model, Vector3f position, float rotx, float roty, float rotz, float scale, int id) {
        super(model, position, rotx, roty, rotz, scale, id);
        movement = new Timer();
        speed = 0.5f;
        distance = 0;
        angleChange = 0;
        drift = 0;
        time = 0;
        dx = 0;
        dz = 0;
        spin =0;
        collide = false;
    }

    public void move() {
        time = movement.getElapsedTime() / 1000f;
        if(!collide){

        angleChange = drift * time;
        distance += speed * time;
        dx = (float) (distance * Math.sin(Math.toRadians(super.getRoty())));
        dz = (float) (distance * Math.cos(Math.toRadians(super.getRoty())));
        super.increasePosition(dx, 0, dz);
        super.increaseRotation(spin += .2, angleChange, 0);
         }
    }

    @Override
    public void Resolve(int id) {
        if((getTeam()/2) != id) {
            collide = true;
            setPosition(0, -100, 0);
        }
    }
}
