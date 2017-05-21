package Controller.Menu;

import Controller.Thread.GameController;

/**
 * Created by MFunction on 2017/4/17.
 *
 * @author MFunction
 */
public class GameMenu {
    private GameController _gc;

    GameMenu(GameController gc) {
        _gc = gc;
        _gc.Pause();
    }
}
