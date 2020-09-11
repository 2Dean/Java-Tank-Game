package crimsonMechEngine.Utils;

import TankGame.GameObjects.BreakableOBJ;
import TankGame.GameObjects.HealthPack;
import TankGame.GameObjects.UnBreakableOBJ;
import crimsonMechEngine.Model.ModelTexture;
import crimsonMechEngine.Model.RawModel;
import crimsonMechEngine.Model.TexturedModel;
import org.joml.Vector3f;

import static TankGame.States.MainState.addEntity;
import static TankGame.States.MainState.addStuff;
import static TankGame.States.MainState.getStuff;

public class MapMaker {

    public void MakeMap(Loader loader) {
        //instantiate all unique models once

        RawModel modelW = OBJReader.loadObjModel("CastleWall", loader);
        RawModel modelB = OBJReader.loadObjModel("CastleWall", loader);
        RawModel modelH = OBJReader.loadObjModel("MedKit", loader);
        //create new texture buffers to hold the .png info
        ModelTexture textureS = null;
        ModelTexture textureWall = null;
        ModelTexture textureBWall = null;

        //read in the texture .png
        try {
            textureS = new ModelTexture(loader.loadTexture("PaintWorkBlue"));
            textureBWall = new ModelTexture(loader.loadTexture("Bricks"));
            textureWall = new ModelTexture(loader.loadTexture("Marble"));
        } catch (Exception e) {
            System.out.println("problems loading texture files in main");
            e.printStackTrace();
        }

        textureBWall.setReflectivity(1);
        textureBWall.setShineDamp(1);

        //create the models required for play
        //unbreakable objects
        TexturedModel texturedModelW = new TexturedModel(modelW, textureWall);
        //health
        TexturedModel texturedModelH = new TexturedModel(modelH, textureS);



        //north walls
        for (int i = 0; i < 11; i++) {
            addStuff(new UnBreakableOBJ(texturedModelW, new Vector3f(i * 52 - 260, -10, 10), 0, 0, 0, .1f));
            getStuff(2 + i).setHitBoxScale(52, 100, 12);
        }
        //west walls
        for (int i = 0; i < 11; i++) {
            addStuff(new UnBreakableOBJ(texturedModelW, new Vector3f(260, -10, i * 52), 0, 270, 0, .1f));
            getStuff(13 + i).setHitBoxScale(12, 100, 52);
        }
        //east wall
        for (int i = 0; i < 11; i++) {
            addStuff(new UnBreakableOBJ(texturedModelW, new Vector3f(-260, -10, i * 52), 0, 90, 0, .1f));
            getStuff(24 + i).setHitBoxScale(12, 100, 52);
        }
        //south wall
        for (int i = 0; i < 11; i++) {
            addStuff(new UnBreakableOBJ(texturedModelW, new Vector3f(i * 52 - 260, -10, 510), 0, 180, 0, .1f));
            getStuff(35 + i).setHitBoxScale(52, 100, 12);
        }
        UnBreakableOBJ floor = new UnBreakableOBJ(texturedModelW, new Vector3f(0, -70, 0), 90, 0, 0, 1);
        floor.setHitBoxScale(0, 0, 0);

        HealthPack healthPack = new HealthPack(texturedModelH, new Vector3f(50, 0, 200), 90, 0, 0, 10);
        addStuff(healthPack);
        addEntity(floor);

        //south breakable walls
        for(int i = 0; i < 11; i++){
            addStuff(new BreakableOBJ(new TexturedModel(modelB, textureBWall), new Vector3f(i * 52 - 260, -10, 350), 0, 180, 0, .1f));
        }

        //north breakable walls
        for(int i = 0; i < 11; i++){
            addStuff(new BreakableOBJ(new TexturedModel(modelB, textureBWall), new Vector3f(i * 52 - 260, -10, 150), 0, 180, 0, .1f));
        }
    }
}