package crimsonMechEngine.GUI;

import org.joml.Vector2f;

public class HealthBar extends GUI{

    private float saveScale;
    private float savePosition;

    public HealthBar(int textureID, Vector2f position, Vector2f scale) {
        super(textureID, position, scale);
        saveScale = getScale().y;
        savePosition = getPosition().y;
    }

    public void update(float healthValue){
        float y = saveScale - (.002f * (100 - healthValue));
        super.setScale(getScale().x, y);
        super.setPosition(getPosition().x, savePosition - y);
    }
}
