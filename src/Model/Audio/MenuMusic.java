package Model.Audio;

import Controller.Thread.MusicController;

public class MenuMusic extends Thread{
    private boolean _isopen=true;
    MusicController music;
    public void run() {
        super.run();
        while(_isopen){
            music=new MusicController("src/Audio/MenuAudio.wav");
            music.start();
            try {
                this.sleep(45000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void MusicSetting(boolean isOpen) throws InterruptedException {
        _isopen=isOpen;
        if(isOpen==false)
            Thread.sleep(1000000000);
    }
}
