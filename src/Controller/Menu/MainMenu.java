package Controller.Menu;

import Controller.Thread.GameController;
import Model.Framework.Map;

/**
 * Created by MFunction on 2017/4/17.
 * This class is about game menu.
 */
public class MainMenu {
    public static void main(String args[]) {
        String filename = "";
        StartGame(filename);
    }

    private static void StartGame(String filename) {
        GameController _gc = new GameController(new Map(filename));
        _gc.Start();
    }
}
