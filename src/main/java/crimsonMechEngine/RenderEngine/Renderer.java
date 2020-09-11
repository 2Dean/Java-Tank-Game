package crimsonMechEngine.RenderEngine;

import crimsonMechEngine.Model.ModelTexture;
import crimsonMechEngine.Model.TexturedModel;
import crimsonMechEngine.Utils.Maths;
import crimsonMechEngine.Model.RawModel;
import crimsonMechEngine.Utils.Window;
import org.joml.Matrix4f;
import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import java.nio.IntBuffer;
import java.util.List;
import java.util.Map;

public class Renderer {

    private static final float FOV = 120;
    private static final float NEAR_PLANE = 0.1f;
    private static final float FAR_PLANE = 1000f;
    private Matrix4f projectionMatrix;
    private StaticShader shade;

    public Renderer(StaticShader shader){
        shade = shader;
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glCullFace(GL11.GL_BACK);
        createProjectionMatrix();
        shader.start();
        shader.loadProjectionMatrix(projectionMatrix);
        shader.stop();
    }

    public void prepare(){
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT|GL11.GL_DEPTH_BUFFER_BIT);
        GL11.glClearColor(1,0,0,1);
    }

    public void render(Map<TexturedModel, List<Entity>> entities){
            for(TexturedModel model:entities.keySet()){
                prepareTexturedModel(model);
                List<Entity> batch = entities.get(model);
                for(Entity entity:batch) {
                    prepareInstance(entity);
                    GL11.glDrawElements(GL11.GL_TRIANGLES, model.getRawModel().getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
                }
            }
            unBindTexturedModel();
    }

    private void prepareTexturedModel(TexturedModel texturedModel){
        RawModel model = texturedModel.getRawModel();
        GL30.glBindVertexArray(model.getVaoID());
        GL20.glEnableVertexAttribArray(0);
        GL20.glEnableVertexAttribArray(1);
        GL20.glEnableVertexAttribArray(2);

        ModelTexture texture = texturedModel.getTexture();
        shade.loadShineVariables(texture.getShineDamp(), texture.getReflectivity());

        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, texturedModel.getTexture().getTextureID());
    }

    private void unBindTexturedModel(){
        GL20.glDisableVertexAttribArray(0);
        GL20.glDisableVertexAttribArray(1);
        GL20.glDisableVertexAttribArray(2);
        GL30.glBindVertexArray(0);
    }

    private void prepareInstance(Entity entity){
        Matrix4f transformationMatrix = Maths.createTransformationMatrix(entity.getPosition(), entity.getRotx(), entity.getRoty(), entity.getRotz(), entity.getScale());

        shade.loadTransformationMatrix(transformationMatrix);
    }

    private void createProjectionMatrix(){
        IntBuffer w = BufferUtils.createIntBuffer(4);
        IntBuffer h = BufferUtils.createIntBuffer(4);
        long id = Window.getWindowID();
        GLFW.glfwGetWindowSize(id, w, h);
        int width = w.get(0);
        int height = h.get(0);

        projectionMatrix = new Matrix4f().perspective((float) Math.toRadians(FOV), width / height , NEAR_PLANE, FAR_PLANE);
    }
}
