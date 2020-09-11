package TankGame.States;

import TankGame.GameObjects.Tank;
import TankGame.GameObjects.UnBreakableOBJ;
import crimsonMechEngine.GUI.GUI;
import crimsonMechEngine.GUI.GUIRender;
import crimsonMechEngine.Model.ModelTexture;
import crimsonMechEngine.Model.RawModel;
import crimsonMechEngine.Model.TexturedModel;
import crimsonMechEngine.RenderEngine.Camera;
import crimsonMechEngine.RenderEngine.Light;
import crimsonMechEngine.RenderEngine.MasterRender;
import crimsonMechEngine.Utils.Loader;
import crimsonMechEngine.Utils.OBJReader;
import org.joml.Vector2f;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL11C.glViewport;

public class EndState {

    public static void EndState(int id){
        int screenHeight = GameState.getScreenHeight();
        int screenWidth = GameState.getScreenWidth();

        Loader loader = new Loader();


        List<GUI> guis = new ArrayList<GUI>();
        GUI gui;


        try {
            if(id == 0) {
                gui = new GUI(loader.loadTexture("Player2Wins"), new Vector2f(0, 0), new Vector2f(1f, 1f));
            }else{
                gui = new GUI(loader.loadTexture("Player1Wins"), new Vector2f(0, 0), new Vector2f(1f, 1f));
            }
            guis.add(gui);
        } catch (Exception e) {
            e.printStackTrace();
        }

        GUIRender guiRenderer = new GUIRender(loader);


        RawModel model1 = OBJReader.loadObjModel("Mech", loader);
        RawModel model2 = model1;
        ModelTexture textureW = null;

        try {
            textureW = new ModelTexture(loader.loadTexture("PaintWorkWhite"));
        }catch (Exception e) {
            System.out.println("problems loading texture files in main");
            e.printStackTrace();
        }

        TexturedModel texturedModel1 = new TexturedModel(model1, textureW);

       UnBreakableOBJ entity1 = new UnBreakableOBJ(texturedModel1, new Vector3f(-5,0,480), 0,180,0,1);

        Camera cameraP1 = new Camera(entity1);

        Light light = new Light(new Vector3f(0,1000,300), new Vector3f(1,1,1));

        MasterRender renderer = new MasterRender();



        while (!GameState.windowShouldClose()){



            //setup the renderer for processing
            renderer.startSection();

            //update window
            renderer.render(light,cameraP1);

            renderer.endSection();

            guiRenderer.render(guis);

            GameState.windowUpdate();
        }
        guiRenderer.cleanUp();
        loader.cleanUp();
    }
}
