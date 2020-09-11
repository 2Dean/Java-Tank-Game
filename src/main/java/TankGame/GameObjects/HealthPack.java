package TankGame.GameObjects;

import crimsonMechEngine.RenderEngine.Entity;
import crimsonMechEngine.Model.TexturedModel;
import org.joml.Vector3f;

public class HealthPack extends Entity {
    public HealthPack(TexturedModel model, Vector3f position, float rotx, float roty, float rotz, float scale) {
        super(model, position, rotx, roty, rotz, scale, 5);
    }

    @Override
    public void Resolve(int id) {
        setPosition(0,-100,0);
    }
}
