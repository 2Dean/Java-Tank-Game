package crimsonMechEngine.GUI;

import crimsonMechEngine.Utils.Loader;
import org.joml.Vector2f;

public class LifeCounter extends GUI {

    public LifeCounter(int textureID, Vector2f position, Vector2f scale) {
        super(textureID, position, scale);
    }
    public void update(float lives){
        switch ((int)lives){
            case 3: break;
            case 2: setTexture("2");
                    break;
            case 1: setTexture("1");
                    break;
            default: break;
        }
    }
}
