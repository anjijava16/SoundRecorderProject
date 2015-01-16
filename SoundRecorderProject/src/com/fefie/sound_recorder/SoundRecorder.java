package com.fefie.sound_recorder;  
  
import java.io.File;  
  
import javax.swing.JFrame;  
  
import com.fefie.sound_recorder.utils.SwingUtils;  
  
/** 
 * Example main program using the sound recorder 
 * 
 * 
 */  
public class SoundRecorder {  
    public static void main(String[] args) throws Exception {  
        MainPanel mp = new MainPanel();  
        if (args.length == 1) {  
            mp.open(new File(args[0]));  
        } else {  
            mp.open(new File("tmp.raw"));  
        }  
        JFrame f = SwingUtils.showPanel(mp.getPanel());  
        f.setSize(1000,300);  
           
          
        Object o = new Object();  
        synchronized(o) {  
            o.wait();  
        }  
  
          
    }  
  
}  