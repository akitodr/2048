package com.example.marinadelara.a2048.Utils;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;

/**
 * Created by 1513 X-MXTI on 17/04/2018.
 */

public class SoundPlayer {
    MediaPlayer mediaPlayer = new MediaPlayer();

    public void loadMusic(AssetManager manager,
                          String fileName){
        try{
            AssetFileDescriptor descriptor =
                    manager.openFd(fileName);
            mediaPlayer.setDataSource(
                    descriptor.getFileDescriptor(),
                    descriptor.getStartOffset(),
                    descriptor.getLength());
            mediaPlayer.prepare();
            mediaPlayer.setLooping(true);
            mediaPlayer.setVolume(0.15f, 0.15f);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void playMusic(){
        mediaPlayer.start();
    }

    SoundPool soundPool;
    public int loadSoundEffect(AssetManager manager, String filename){
        if(soundPool == null){
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                soundPool = new SoundPool.Builder().setMaxStreams(20).build();
            }else{
                soundPool = new SoundPool(20, AudioManager.STREAM_MUSIC, 1);
            }
        }
        try{
            AssetFileDescriptor descriptor = manager.openFd(filename);
            return soundPool.load(descriptor,1);
        }catch (Exception e){
            e.printStackTrace();
        }
        return -1;
    }

    public void playSoundEffect(int sound){
        soundPool.play(sound, 1,1, 1,0,1);
    }
}
