package crimsonMechEngine.GUI;

import crimsonMechEngine.Utils.Loader;
import org.joml.Vector2f;

public class GUI {

    private int textureID;

    private Vector2f position;

    private Vector2f scale;

    public GUI(int textureID, Vector2f position, Vector2f scale){
        this.textureID = textureID;
        this.position = position;
        this.scale = scale;
    }

    public int getTextureID() {
        return textureID;
    }

    public Vector2f getPosition() {
        return position;
    }

    public Vector2f getScale() {
        return scale;
    }

    public void setPosition(float x, float y){
        position.x = x;
        position.y = y;
    }

    public void setTexture(String newTexture){
        try {
            textureID = Loader.loadTexture(newTexture);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setScale(float x, float y) {
        this.scale.x = x;
        this.scale.y = y;
    }

    public void update(float number){}

    public void update(float x, float y){}
}
