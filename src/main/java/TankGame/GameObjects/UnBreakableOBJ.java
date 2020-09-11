package TankGame.GameObjects;

import crimsonMechEngine.RenderEngine.Entity;
import crimsonMechEngine.Model.TexturedModel;
import org.joml.Vector3f;


public class UnBreakableOBJ extends Entity {
    public UnBreakableOBJ(TexturedModel model, Vector3f position, float rotx, float roty, float rotz, float scale) {
        super(model, position, rotx, roty, rotz, scale, 0);
        super.setHitBoxScale(30, 100, 12);
    }

    @Override
    public void Resolve(int id) {
        //there is nothing to resolve.  The objects just sits there.  Super is also empty, as it has no required game logic.
    }
}
