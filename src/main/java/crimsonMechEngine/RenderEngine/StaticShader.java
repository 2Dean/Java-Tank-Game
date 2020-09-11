package crimsonMechEngine.RenderEngine;

import crimsonMechEngine.Utils.Maths;
import org.joml.Matrix4f;

public class StaticShader extends Shader {


    private static final String vertexFile = "src\\main\\resources\\vertex.vs";
    private static final String fragmentFile = "src\\main\\resources\\fragment.fs";

    private int locationtransformationMatrix;
    private int locationprojectionMatrix;
    private int locationviewMatrix;
    private int locationLightPosition;
    private int locationLightColor;
    private int locationShineDamp;
    private int locationReflectivity;

    public StaticShader(){
        super(vertexFile, fragmentFile);
    }

    @Override
    protected void getAllUniformLocations() {
        locationtransformationMatrix = super.getUniformLocation("transformationMatrix");
        locationprojectionMatrix = super.getUniformLocation("projectionMatrix");
        locationviewMatrix = super.getUniformLocation("viewMatrix");
        locationLightPosition = super.getUniformLocation("lightPosition");
        locationLightColor = super.getUniformLocation("lightColor");
        locationShineDamp = super.getUniformLocation("shineDamp");
        locationReflectivity = super.getUniformLocation("reflectivity");
    }

    public void loadShineVariables(float damper, float reflectivity){
        super.loadFloat(locationShineDamp, damper);
        super.loadFloat(locationReflectivity, reflectivity);
    }

    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "position");
        super.bindAttribute(1, "textureCoords");
        super.bindAttribute(2, "normals");
    }

    public void loadViewMatrix(Camera camera){
        Matrix4f viewMatrix = Maths.createViewMatrix(camera);
        super.loadMatrix(locationviewMatrix, viewMatrix);
    }

    public void loadTransformationMatrix(Matrix4f matrix){
        super.loadMatrix(locationtransformationMatrix, matrix);
    }

    public void loadProjectionMatrix(Matrix4f projection){
        super.loadMatrix(locationprojectionMatrix, projection);
    }

    public void loadLight(Light light){
        super.loadVector(locationLightPosition, light.getPosition());
        super.loadVector(locationLightColor, light.getColor());
    }


}
