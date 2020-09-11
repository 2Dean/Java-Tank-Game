package crimsonMechEngine.GUI;

import org.joml.Vector2f;

public class DOT extends GUI {
    public DOT(int textureID, Vector2f position, Vector2f scale) {
        super(textureID, position, scale);
    }

    @Override
    public void update(float x, float z) {
        //260 by 510 is the map size, 5 and 2.5 come from the scaling, .2 and half of that.
        setPosition((x / (260*5)),
                (z / (510*2.5f)) - 1 );
    }
}
