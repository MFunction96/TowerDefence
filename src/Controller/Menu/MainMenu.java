package Controller.Menu;

import Controller.Thread.GameController;
import Model.Framework.TestMap;

/**
 * Created by MFunction on 2017/4/17.
 * This class is about game menu.
 */
public class MainMenu {

    private static void StartGame() {
        GameController _gc = new GameController(new TestMap());
        _gc.Start();
    }
}
