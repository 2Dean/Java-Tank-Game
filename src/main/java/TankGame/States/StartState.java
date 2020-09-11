package TankGame.States;

import crimsonMechEngine.GUI.GUI;
import crimsonMechEngine.GUI.GUIRender;
import crimsonMechEngine.Utils.Loader;
import crimsonMechEngine.Utils.Window;
import org.joml.Vector2f;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class StartState {

    public static void StartMenu(){
        Loader loader = new Loader();
        boolean shouldRun = false;
        GUI startScreen = null;
        GUIRender guiRender = new GUIRender(loader);
        List<GUI> guis = new ArrayList<GUI>();
        GUI lives;
        GUI health;
        try {
            lives = new GUI(loader.loadTexture("MOTIVATIONE"),new Vector2f(0f, 0f), new Vector2f(1f, 1f));
            //health = new GUI(loader.loadTexture("MOTIVATION"),new Vector2f(.8f, .6f), new Vector2f(.2f, .2f));
            guis.add(lives);
            //guis.add(health);
        } catch (Exception e) {
            e.printStackTrace();
        }

        while (!shouldRun && !GameState.windowShouldClose()){
            //update window

            guiRender.render(guis);

            if(Window.isKeyPressed(KeyEvent.VK_E)){
                shouldRun = true;
            }

            GameState.windowUpdate();
        }
        guiRender.cleanUp();
        loader.cleanUp();
    }
}
