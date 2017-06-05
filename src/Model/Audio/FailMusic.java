package Model.Audio;

import Controller.Thread.MusicController;

public class FailMusic extends Thread {
    MusicController music;

    public void run() {
        music = new MusicController("src/Audio/Fail.wav");
        music.start();
    }
}
