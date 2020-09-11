package crimsonMechEngine.CollisionEngine;

import org.joml.Vector3f;


public class HitBox {
    private Vector3f position;

    //Need to know how big the "box" should be
    //These are the halves of each edge length
    //except for Y, which goes from 0 to scale
    private float scaleX2;
    private float scaleY;
    private float scaleZ2;

    public HitBox(Vector3f position, float scaleX, float scaleY, float scaleZ){
        //let me explain:
        //each Vector3f is a constant set,
        //by equating them without calling "New,"
        //they actually point to the same memory address,
        //so changing one, will effect the other
        this.position = position;

        //we need each half of the total length,
        //because the position is centered in the middle of the box
        //so we need to + and - the scale of the box
        /*
         ---------------------------
         |           + scaleX/2    |
         |           |             |
         | <- ------ + -------- +> | this entire arrow is scaleZ
         |           |             |    position is the center "+"
         |           - scaleX/2    |  Y is going straight up
         ---------------------------
         */
        this.scaleX2 =  scaleX/2;
        this.scaleY = scaleY;
        this.scaleZ2 = scaleZ/2;
    }

    public Vector3f getPosition() {
        return position;
    }

    public float getScaleX2() {
        return scaleX2;
    }

    public float getScaleY() {
        return scaleY;
    }

    public float getScaleZ2() {
        return scaleZ2;
    }

    public void setScaleX2(float scaleX2) {
        this.scaleX2 = scaleX2;
    }

    public void setScaleY(float scaleY) {
        this.scaleY = scaleY;
    }

    public void setScaleZ2(float scaleZ2) {
        this.scaleZ2 = scaleZ2;
    }
}
