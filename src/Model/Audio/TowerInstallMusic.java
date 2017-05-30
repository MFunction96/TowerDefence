package Model.Audio;

import Controller.Thread.MusicController;

public class TowerInstallMusic extends Thread {
    MusicController music;

    public void run() {
        music = new MusicController("src/Audio/InstallTower.wav");
        music.start();
    }
}
