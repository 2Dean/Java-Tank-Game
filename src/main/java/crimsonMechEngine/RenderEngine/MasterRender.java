package crimsonMechEngine.RenderEngine;

import crimsonMechEngine.Model.TexturedModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MasterRender {

    private StaticShader shader = new StaticShader();

    private Renderer renderer= new Renderer(shader);

    private Map<TexturedModel, List<Entity>> entities = new HashMap<TexturedModel, List<Entity>>();

    public void render(Light light, Camera camera){
        shader.loadLight(light);
        shader.loadViewMatrix(camera);
        renderer.render(entities);
    }

    public void startSection(){
        renderer.prepare();
        shader.start();
    }

    public void endSection(){
        shader.stop();
        entities.clear();
    }

    public void processEntity(Entity entity){
        TexturedModel entityModel = entity.getModel();
        List<Entity> batch = entities.get(entityModel);
        if(batch != null){
            batch.add(entity);
        }else{
            List<Entity> newBatch = new ArrayList<Entity>();
            newBatch.add(entity);
            entities.put(entityModel,newBatch);
        }
    }

    public void cleanUp(){
        shader.cleanUp();
    }
}
