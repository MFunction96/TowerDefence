package Controller.Thread;

import javax.sound.sampled.*;
import java.io.File;

public class MusicController extends Thread {
    private String _music;

    /**
     * 构造背景音乐
     *
     * @param music 音乐名
     */
    public MusicController(String music) {
        _music = music;
    }

    @Override
    public void run() {
        super.run();
        File musicfile = new File(_music);
        AudioInputStream musicstream = null;
        try {
            musicstream = AudioSystem.getAudioInputStream(musicfile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        AudioFormat format = musicstream.getFormat();
        SourceDataLine safe = null;
        DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
        /*
        对文件进行安全性的包装
         */
        try {
            safe = (SourceDataLine) AudioSystem.getLine(info);
            safe.open(format);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
        safe.start();
        //字节
        int bytesize = 0;
        //字节缓冲
        byte[] bytebuffer = new byte[1024];
        try {
            while (bytesize != -1) {   //文件未读取完
                bytesize = musicstream.read(bytebuffer, 0, bytebuffer.length);
                if (bytesize >= 0)
                    safe.write(bytebuffer, 0, bytesize);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            safe.drain();//将管道中残留部分读取文件处理掉
            safe.close();
        }
    }
}
