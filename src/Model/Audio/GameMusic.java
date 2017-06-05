package Model.Audio;

import Controller.Thread.MusicController;

public class GameMusic extends Thread {
    private boolean _isopen = true;
    MusicController music;

    public synchronized void run() {
        super.run();
        while (_isopen) {
            music = new MusicController("src/Audio/GameMusic.wav");
            music.start();
            try {
                wait(45000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void MusicSetting(boolean isOpen) {
        _isopen = isOpen;
        if (isOpen == false)
            music.interrupt();

    }
}
