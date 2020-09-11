package TankGame.GameObjects;

import crimsonMechEngine.RenderEngine.Entity;
import crimsonMechEngine.Model.TexturedModel;
import crimsonMechEngine.Utils.Timer;
import org.joml.Vector3f;

public class BreakableOBJ extends Entity {

    private int health;
    private float speed;
    private float time;
    private float distance;
    private float dx;
    private float dz;
    private Timer movement;

    public BreakableOBJ(TexturedModel model, Vector3f position, float rotx, float roty, float rotz, float scale) {
        super(model, position, rotx, roty, rotz, scale, 1);
        health = 500;
        movement = new Timer();
        speed = 0.05f;
        distance = 0;
        time = 0;
        setHitBoxScale(30, 40, 12);
    }

    public void move(){
        if(health < 0){
            time = movement.getElapsedTime() / 1000f;
                distance += speed * time;
                super.increasePosition(0, -1*distance, 0);
        }
    }

    public void setHealth(int change){
        health -= change;
    }

    public void Resolve(int id){
        if((id == 4 || id == 6) && health == 500){
            super.setTexture("BricksBreak");
        }

        if(id == 4 || id == 6){
            setHealth(50);
        }
    }
}
