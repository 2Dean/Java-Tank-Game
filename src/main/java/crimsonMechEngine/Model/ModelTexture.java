package crimsonMechEngine.Model;

public class ModelTexture {
    private int textureID;

    private float shineDamp = 1;
    private float reflectivity = 0;

    public ModelTexture(int id){
        this.textureID = id;
    }

    public int getTextureID() {
        return textureID;
    }

    public float getShineDamp() {
        return shineDamp;
    }

    public float getReflectivity() {
        return reflectivity;
    }

    public void setReflectivity(float reflectivity) {
        this.reflectivity = reflectivity;
    }

    public void setShineDamp(float shineDamp) {
        this.shineDamp = shineDamp;
    }
}
