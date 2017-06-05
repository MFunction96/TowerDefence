package Model.Audio;

import Controller.Thread.MusicController;

public class WinMusic extends  Thread{
    MusicController music;

    public void run() {
        music = new MusicController("src/Audio/Win.wav");
        music.start();
    }
}
