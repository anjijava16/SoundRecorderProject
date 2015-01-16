package com.fefie.sound_recorder.utils;  
  
import javax.swing.BoxLayout;  
import javax.swing.JFrame;  
import javax.swing.JPanel;  
  
public class SwingUtils {  
      
    public static JFrame framePanel(JPanel p) {  
        JFrame f = new JFrame();  
        f.add(p);  
        return f;  
    }  
    public static JFrame showPanel(JPanel p) {  
        JFrame f = framePanel(p);  
        show(f);  
        return f;  
    }  
      
    public static void show(JFrame f) {  
        f.pack();  
        f.setVisible(true);  
    }  
  
    public static JPanel boxPanel(int axis) {  
        JPanel p = new JPanel();  
        p.setLayout(new BoxLayout(p, axis));  
        return p;  
    }  
}  