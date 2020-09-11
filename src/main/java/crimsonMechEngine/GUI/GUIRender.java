package crimsonMechEngine.GUI;

import crimsonMechEngine.Utils.Loader;
import crimsonMechEngine.Utils.Maths;
import crimsonMechEngine.Model.RawModel;
import org.joml.Matrix4f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import java.util.List;

public class GUIRender {

    private final RawModel quad;
    private GUIShader shader;

    public GUIRender(Loader loader){
        float[] positions = {-1, 1, -1, -1, 1, 1, 1, -1};
        quad = loader.loadToVAO(positions);
        shader = new GUIShader();
    }

    public void render(List<GUI> guis){
        shader.start();
        GL30.glBindVertexArray(quad.getVaoID());
        GL20.glEnableVertexAttribArray(0);

        for(GUI gui : guis){
            GL13.glActiveTexture(GL13.GL_TEXTURE0);
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, gui.getTextureID());
            Matrix4f matrix = Maths.createTransformationMatrix(gui.getPosition(), gui.getScale());
            shader.loadTransformation(matrix);
            GL11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 0, quad.getVertexCount());
        }

        GL20.glDisableVertexAttribArray(0);
        GL30.glBindVertexArray(0);
        shader.stop();
    }

    public void cleanUp(){
        shader.cleanUp();
    }
}
