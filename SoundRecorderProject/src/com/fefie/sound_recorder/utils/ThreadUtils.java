package com.fefie.sound_recorder.utils;  
  
public class ThreadUtils {  
    public static void sleep(long millis) {  
        try {  
            Thread.sleep(millis);  
        } catch (InterruptedException ie) {  
            return;  
        }  
    }  
  
}  