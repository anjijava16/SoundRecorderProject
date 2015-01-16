package com.fefie.sound_recorder;  
  
import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;  
  
public abstract class ExceptionActionListener implements ActionListener {  
      
    public final void actionPerformed(ActionEvent ae) {  
        try {  
            doActionPerformed(ae);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
      
    protected abstract void doActionPerformed(ActionEvent ae) throws Exception;  
  
}  