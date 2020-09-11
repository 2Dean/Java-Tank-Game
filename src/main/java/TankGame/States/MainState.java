package TankGame.States;

import TankGame.GameObjects.BreakableOBJ;
import TankGame.GameObjects.HealthPack;
import TankGame.GameObjects.Tank;
import TankGame.GameObjects.UnBreakableOBJ;
import crimsonMechEngine.CollisionEngine.Collision;
import crimsonMechEngine.GUI.*;
import crimsonMechEngine.Model.ModelTexture;
import crimsonMechEngine.Model.TexturedModel;
import crimsonMechEngine.RenderEngine.*;
import crimsonMechEngine.Utils.Loader;
import crimsonMechEngine.Utils.MapMaker;
import crimsonMechEngine.Utils.OBJReader;
import crimsonMechEngine.Model.RawModel;
import org.joml.Vector2f;
import org.joml.Vector3f;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL11C.glViewport;

public class MainState {

    private static List<Entity> collidableObjects;
    private static List<Entity> entities;
    private static List<TexturedModel>usableObjects;
    private static List<GUI>guis;

    private static boolean playable;

    private static int loser = 0;

    public static void setPlayable(boolean zero, int id){
        playable = zero;
        loser = id;
    }

    public static void addStuff(Entity entity){
        collidableObjects.add(entity);
    }
    public static Entity getStuff(int i){return collidableObjects.get(i);}

    public static void addEntity(Entity entity){
        entities.add(entity);
    }

    public static int stuffSize(){ return collidableObjects.size(); }

    public static TexturedModel getObjects(int i){
        return usableObjects.get(i);
    }

    public static void removeStuff(int index){
        collidableObjects.remove(index);}

    public static int MainState(){
        playable = true;
        int screenHeight = GameState.getScreenHeight();
        int screenWidth = GameState.getScreenWidth();

        //allow for files to be read
        Loader loader = new Loader();

        //CollisionEngine
        Collision collider = new Collision();

        //instantiate all unique models once
        RawModel model1 = OBJReader.loadObjModel("Mech", loader);
        RawModel model2 = model1;

        RawModel modelS = OBJReader.loadObjModel("claymore", loader);

        RawModel modelW = OBJReader.loadObjModel("CastleWall", loader);
        RawModel modelB = OBJReader.loadObjModel("CastleWall", loader);

        RawModel modelH = OBJReader.loadObjModel("MedKit", loader);

        //create new texture buffers to hold the .png info
        ModelTexture textureW = null;
        ModelTexture textureB = null;

        //read in the texture .png
        try {
            textureW = new ModelTexture(loader.loadTexture("PaintWorkWhite"));
            textureB = new ModelTexture(loader.loadTexture("PaintWorkBlue"));
        } catch (Exception e) {
            System.out.println("problems loading texture files in main");
            e.printStackTrace();
        }

        //create the models required for play
        //players
        TexturedModel texturedModel1 = new TexturedModel(model1, textureW);
        TexturedModel texturedModel2 = new TexturedModel(model2, textureB);
        //sword
        TexturedModel texturedModelS = new TexturedModel(modelS, textureB);
        //health
        TexturedModel texturedModelH = new TexturedModel(modelH, textureB);

        usableObjects = new ArrayList<TexturedModel>();
        usableObjects.add(texturedModelS);
        usableObjects.add(texturedModelH);
        collidableObjects = new ArrayList<Entity>();

        //holds all drawable objects
        entities = new ArrayList<Entity>();

        //player 1 controls/tank
        int inputP1[] = {KeyEvent.VK_W, KeyEvent.VK_A,
                KeyEvent.VK_S,KeyEvent.VK_D,
                KeyEvent.VK_E, KeyEvent.VK_SPACE};
        Tank entity1 = new Tank(texturedModel1, new Vector3f(-5,0,480), 0,180,0,1, inputP1, 2);

        //player 2 controls/tank
        int inputP2[] = {KeyEvent.VK_I,KeyEvent.VK_J,
                KeyEvent.VK_K,KeyEvent.VK_L,
                KeyEvent.VK_N, KeyEvent.VK_M};
        Tank entity2 = new Tank(texturedModel2, new Vector3f(5,0,30), 0,0,0,1, inputP2, 3);

        collidableObjects.add(entity1);
        collidableObjects.add(entity2);


        MapMaker mapMaker = new MapMaker();
        mapMaker.MakeMap(loader);

        //creating a light source for testing
        Light light = new Light(new Vector3f(0,1000,300), new Vector3f(1,1,1));

        //create each person's view
        Camera cameraP1 = new Camera(entity1);
        Camera cameraP2 = new Camera(entity2);
        Vector3f globalPosition = new Vector3f(0,20,50);
        Camera cameraGlobal = new Camera(globalPosition);
        cameraGlobal.setPitch(-90);

        //ready to render
        MasterRender renderer = new MasterRender();

        guis = new ArrayList<GUI>();
        GUI livesP1 , healthP1 , livesP2, healthP2, minimap, dotP1, dotP2;
        try {
            guis.add(new HealthBar(loader.loadTexture("TANK"),new Vector2f(-.8f, 1f), new Vector2f(.2f, .2f)));
             guis.add(new LifeCounter(loader.loadTexture("3"), new Vector2f(-.9f, -.9f),new Vector2f(.05f, .05f)));
             guis.add(new HealthBar(loader.loadTexture("TANK"),new Vector2f(.8f, 1f), new Vector2f(.2f, .2f)));
             guis.add(new LifeCounter(loader.loadTexture("3"), new Vector2f(.9f, -.9f),new Vector2f(.05f, .05f)));
             guis.add(new DOT(loader.loadTexture("DotP1"),new Vector2f(0f, -1f), new Vector2f(.02f, .02f)));
             guis.add( new DOT(loader.loadTexture("DotP2"),new Vector2f(0f, -1f), new Vector2f(.02f, .02f)));
            guis.add(new GUI(loader.loadTexture("minimap"),new Vector2f(0f, -.8f), new Vector2f(.2f, .2f)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        GUIRender guiRenderer = new GUIRender(loader);


        //MAIN GAME LOOP
        while (!GameState.windowShouldClose() && playable) {
            //move everything that wants to, can't do an "all entities" call
            for(int i = 0; i < collidableObjects.size(); ++i){
                collidableObjects.get(i).move();
            }

            collider.BruteForce(collidableObjects);

            cameraP1.move();
            cameraP2.move();

            //setup the renderer for processing
            renderer.startSection();

            //render all objects
            for(Entity entity : entities){
                renderer.processEntity(entity);
            }

            for(Entity entity : collidableObjects){
                renderer.processEntity(entity);
            }

            //split screen for player 1
            glViewport(0,0,screenWidth/2,screenHeight);
            renderer.render(light,cameraP1);

            //split screen for player 2
            glViewport(screenWidth/2,0,screenWidth/2,screenHeight);
            renderer.render(light,cameraP2);

            //cleanup
            renderer.endSection();

            //gui
            guis.get(0).update(entity1.getHealth());
            guis.get(1).update(entity1.getLives());
            guis.get(2).update(entity2.getHealth());
            guis.get(3).update(entity2.getLives());
            guis.get(4).update(entity1.getPosition().x, entity1.getPosition().z);
            guis.get(5).update(entity2.getPosition().x, entity2.getPosition().z);
            for(int i = 6; i < guis.size(); i++){
                guis.get(i).update(0);
            }
            glViewport(0,0,screenWidth,screenHeight);
            guiRenderer.render(guis);

            //update window
            GameState.windowUpdate();
        }

        //cleanup
        glViewport(0,0,screenWidth,screenHeight);
        guiRenderer.cleanUp();
        renderer.cleanUp();
        loader.cleanUp();
        return loser;
    }
}
