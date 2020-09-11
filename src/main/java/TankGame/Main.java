package TankGame;

import TankGame.States.GameState;

//all main has to do is call the correct game phase, nothing else, the bulk
// of the code is in TankGame.States.MainState, and the Engine
public class Main {

    public static void main(String[] args) {
        GameState gameState = new GameState(0);
        for(int i =0; i < gameState.getLength() && !GameState.windowShouldClose(); ++i) {
            gameState.setPhase(i);
        }
    }
}
