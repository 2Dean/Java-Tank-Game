package crimsonMechEngine.RenderEngine;

import crimsonMechEngine.CollisionEngine.HitBox;
import crimsonMechEngine.Model.TexturedModel;
import crimsonMechEngine.Utils.Loader;
import org.joml.Vector3f;

public abstract class Entity {

    private TexturedModel model;
    private Vector3f position;
    private HitBox hitbox;
    private float rotx, roty, rotz;
    private float scale;
    private int team;

    public Entity(TexturedModel model, Vector3f position, float rotx, float roty, float rotz, float scale, int team){
        this.model = model;
        this.position = position;
        this.rotx = rotx;
        this.roty = roty;
        this.rotz = rotz;
        this.scale = scale;
        this.team = team;
        this.hitbox = new HitBox(position, 3,5,3);
    }

    public void setHitBoxScale(float x, float y, float z){
        this.hitbox.setScaleX2(x);
        this.hitbox.setScaleY(y);
        this.hitbox.setScaleZ2(z);
    }

    public int getTeam() {
        return team;
    }

    public HitBox getHitbox() {
        return hitbox;
    }

    public void Resolve(int id){}

    public void increasePosition(float dx, float dy, float dz){
        this.position.x += dx;
        this.position.y += dy;
        this.position.z += dz;
    }

    public void increaseRotation(float dx, float dy, float dz){
        this.rotx += dx;
        this.roty += dy;
       this.rotz += dz;
    }

    public void setPosition(float dx, float dy, float dz){
        this.position.x = dx;
        this.position.y = dy;
        this.position.z = dz;
    }

    public float getRotx() {
        return rotx;
    }

    public float getRoty() {
        return roty;
    }

    public float getRotz() {
        return rotz;
    }

    public float getScale() {
        return scale;
    }

    public TexturedModel getModel() {
        return model;
    }

    public Vector3f getPosition() {
        return position;
    }

    public void move(){}

    public void setTexture(String newTexture){
        try {
            model.setTexture(Loader.loadTexture(newTexture));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
