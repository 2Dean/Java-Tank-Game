package crimsonMechEngine.CollisionEngine;

import crimsonMechEngine.RenderEngine.Entity;

import java.util.List;

public class Collision {

    public void BruteForce(List<Entity> collidable){
        for(int i = 0; i < collidable.size() - 1; ++i){
            HitBox eyeBox = collidable.get(i).getHitbox();
            int eyed = collidable.get(i).getTeam();
            for(int j = i + 1; j < collidable.size(); ++j) {
                int jd = collidable.get(j).getTeam();
                if (Collide(eyeBox, collidable.get(j).getHitbox())) {
                    collidable.get(i).Resolve(jd);
                    collidable.get(j).Resolve(eyed);
                }
            }
        }
    }

    //oh no
    public boolean Collide(HitBox a, HitBox b){
        return(
         (((a.getPosition().x - a.getScaleX2()) <= (b.getPosition().x + b.getScaleX2())) && ((a.getPosition().x + a.getScaleX2()) >= (b.getPosition().x - b.getScaleX2())))&&
        (((a.getPosition().y) <= (b.getPosition().y + b.getScaleY())) && ((a.getPosition().y + a.getScaleY()) >= (b.getPosition().y)))&&
         (((a.getPosition().z - a.getScaleZ2()) <= (b.getPosition().z + b.getScaleZ2())) && ((a.getPosition().z + a.getScaleZ2()) >= (b.getPosition().z - b.getScaleZ2())))
                );
    }
}
