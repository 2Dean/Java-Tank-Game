package TankGame.States;

import crimsonMechEngine.Utils.Window;

public class GameState {
    private int id;
    private int length;

    private static final int screenWidth = 1280;
    private static final int screenHeight = 720;
    private static String name = "Tank Game";
    private static boolean vSync = true;
    private static Window window;

    public GameState(int phase){
        window  = new Window(name, screenWidth, screenHeight, vSync);
        window.init();

        setPhase(phase);
    }

    public void setPhase(int phase){
        id = phase;
        run();
    }

    private int loser;

    public static int getScreenHeight() {
        return screenHeight;
    }

    public static int getScreenWidth() {
        return screenWidth;
    }

    public static boolean windowShouldClose(){
        return window.windowShouldClose();
    }

    public static void windowUpdate(){
        window.update();
    }

    public void run(){
        switch (id) {
            case 0: StartState.StartMenu();
            break;
            case 1: loser = MainState.MainState();
            break;
            case 2: EndState.EndState(loser);
            break;
        }
    }

    public int getLength(){
        return 3;
    }

}
