/* *****************************************
* CSCI205 - Software Engineering and Design
* Spring 2017
*
* Name: Iris Fu, Haipu Sun, Junjie Jiang, Zilin Ma
* Date: Apr 23, 2017
* Time: 5:07:24 PM
*
* Project: csci205_final_project
* Package: Util
* File: AudioUtil
* Description:
*
* ****************************************
 */
package Util;

import csci205_final_project.Game.FinalProjectGameSceneController;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author Zilin Ma
 */
public class AudioUtil {

    public static void playMusic(String filenameString) {
        File soundFile = new File(filenameString);
        AudioInputStream sound = null;
        try {
            sound = AudioSystem.getAudioInputStream(soundFile);
        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(FinalProjectGameSceneController.class.getName()).log(
                    Level.SEVERE,
                    null,
                    ex);
        } catch (IOException ex) {
            Logger.getLogger(FinalProjectGameSceneController.class.getName()).log(
                    Level.SEVERE,
                    null,
                    ex);
        }
        // load the sound into memory (a Clip)
        DataLine.Info info = new DataLine.Info(Clip.class, sound.getFormat());
        Clip clip = null;
        try {
            clip = (Clip) AudioSystem.getLine(info);
        } catch (LineUnavailableException ex) {
            Logger.getLogger(FinalProjectGameSceneController.class.getName()).log(
                    Level.SEVERE,
                    null,
                    ex);
        }
        try {
            clip.open(sound);
        } catch (LineUnavailableException | IOException ex) {
            Logger.getLogger(FinalProjectGameSceneController.class.getName()).log(
                    Level.SEVERE,
                    null,
                    ex);
        }
        // due to bug in Java Sound, explicitly exit the VM when
        // the sound has stopped.
        clip.addLineListener(new LineListener() {
            public void update(LineEvent event) {
                if (event.getType() == LineEvent.Type.STOP) {
                    event.getLine().close();
                    System.exit(0);
                }
            }
        });
        clip.loop(1000);
        // play the sound clip
        clip.start();
    }

}
