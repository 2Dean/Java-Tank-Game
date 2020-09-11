package TankGame.GameObjects;

import TankGame.States.MainState;
import crimsonMechEngine.RenderEngine.Entity;
import crimsonMechEngine.Model.TexturedModel;
import crimsonMechEngine.Utils.Timer;
import crimsonMechEngine.Utils.Window;
import org.joml.Vector3f;

import static TankGame.States.MainState.addStuff;
import static TankGame.States.MainState.removeStuff;
import static TankGame.States.MainState.stuffSize;

public class Tank extends Entity {

    private float speed = 0;
    private float drift = 0;
    private float time = 0;
    private float angleChange = 0;
    private float distance = 0;
    private float dx;
    private float dz;

    private static float gravity = -.003f;
    private static float jumpPower = 3;
    private float upwardSpeed = 0;

    private boolean jumpable;

    private float health;
    private int lives;
    private Timer reload;
    private Timer movement;
    private boolean bullet;

    private float size;

    private int keyEvents[];


    private float xs;
    private float ys;
    private float zs;

    private float startx;
    private float starty;
    private float startz;

    public Tank(TexturedModel model, Vector3f position, float rotx, float roty, float rotz, float scale, int inputs[], int id) {
        super(model, position, rotx, roty, rotz, scale, id);
        startx = position.x();
        starty = position.y();
        startz = position.z();
        keyEvents = inputs;
        health = 100;
        lives = 3;
        size = 1f;
        jumpable = true;
        reload = new Timer();
        movement = new Timer();
        bullet = true;
        super.setHitBoxScale(5,7,5);
    }

    public void move(){
        xs = getPosition().x;
        ys = getPosition().y;
        zs = getPosition().z;
        if(lives < 1){
           MainState.setPlayable(false, super.getTeam());
        }
        if(health < 0){
            --lives;
            setPosition(startx,50,startz);
            health = 100;
        }
        speed = 0;
        drift = 0;
        //up
        if(Window.isKeyPressed(keyEvents[0])){
            speed = .02f;
        }
        //left
        if(Window.isKeyPressed(keyEvents[1])){
            drift = .1f;
        }
        //down
        if(Window.isKeyPressed(keyEvents[2])){
            speed = -.02f;
        }
        //right
        if(Window.isKeyPressed(keyEvents[3])){
            drift = -.1f;
        }
        //jump
        if(Window.isKeyPressed(keyEvents[4])){
            //jump button
            if(jumpable) {
                jump();
                jumpable = false;
            }
        }
        //shoot
        if(Window.isKeyPressed(keyEvents[5])){
            //this is the fire button
            shoot();
        }

        time = (float) movement.getElapsedTime() * 5000f;
        angleChange = drift*time;
        distance = speed*time;
        dx =  (float)(distance*Math.sin(Math.toRadians(super.getRoty())));
        dz = (float)(distance * Math.cos(Math.toRadians(super.getRoty())));
        super.increasePosition(dx, 0, dz);
        super.increaseRotation(0,angleChange,0);
        //jump
        upwardSpeed += gravity * time;

        super.increasePosition(0, upwardSpeed, 0);
        if(super.getPosition().y <= 0){
            upwardSpeed = 0;
            super.getPosition().y = 0;
            jumpable = true;
        }
    }

    private void jump(){
        this.upwardSpeed = jumpPower;
    }


    private void setHealth(float change){
        health -= change;
    }

    public void setSpeed(float speedChange){
        this.speed = speedChange;
    }

    public void setDrift(float angleChange){
        this.drift = angleChange;
    }

    public float getHealth(){
        return health;
    }

    public float getLives() {
        return lives;
    }

    private void shoot(){
            if(bullet) {
                makeBullet(super.getPosition(), super.getRotx(), super.getRoty(), super.getRotz(), super.getTeam()*2);
                bullet = false;
                reload.init();
            }else if(reload.getElapsedTime() > .05){
                bullet = true;
            }
    }

    @Override
    public void Resolve(int id){
        setPosition(xs, ys, zs);
        //bullets
        if(id != getTeam()*2 && id > 3 && id != 5
        ){
            setHealth(10);
        }
        //healthpacks
        if(id == 5){
            setHealth(-20);
        }
    }

    public void makeBullet(Vector3f position, float rotx, float roty, float rotz, int team){
        Vector3f sword = new Vector3f(position.x, position.y, position.z);
        Bullet bullet = new Bullet(MainState.getObjects(0),sword,rotx,roty,rotz,2, team);
        if(stuffSize() > 200){
            removeStuff(150);
        }
        addStuff(bullet);
    }
}
