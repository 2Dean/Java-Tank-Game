package crimsonMechEngine.Model;

import crimsonMechEngine.Utils.Loader;

//This class ties a model to an image
public class TexturedModel {
    private RawModel rawModel;
    private ModelTexture texture;


    public TexturedModel(RawModel model, ModelTexture texture){
        this.rawModel = model;
        this.texture = texture;
    }

    public RawModel getRawModel() {
        return rawModel;
    }

    public ModelTexture getTexture() {
        return texture;
    }

    public void setTexture(int id) {
        texture = new ModelTexture(id);
    }

    public void setRawModel(RawModel rawModel) {
        this.rawModel = rawModel;
    }
}
