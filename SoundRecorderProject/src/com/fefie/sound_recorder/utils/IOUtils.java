package com.fefie.sound_recorder.utils;  
  
import java.io.BufferedInputStream;  
import java.io.File;  
import java.io.FileInputStream;  
import java.io.IOException;  
import java.io.InputStream;  
import java.io.OutputStream;  
  
public class IOUtils {  
    public static void copy(InputStream istream, OutputStream ostream) throws IOException {  
        byte[] buffer = new byte[1024];  
        int i;  
        while ((i = istream.read(buffer, 0, buffer.length)) != -1) {  
            ostream.write(buffer, 0, i);  
        }  
    }  
    public static int load(File f, byte[] b) throws IOException {  
        FileInputStream fis = new FileInputStream(f);  
        BufferedInputStream bis = new BufferedInputStream(fis);  
          
        int len;  
        int count = 0;  
        while ((len = bis.read(b, count, b.length - count)) != -1) {  
            count = count + len;  
        }  
        return count;  
          
    }  
  
    public static int getInputStreamLengthInBytes(InputStream is) throws IOException {  
        byte[] buffer = new byte[255];  
        int bytesRead;  
        int count = 0;  
        while ((bytesRead = is.read(buffer)) != -1) {  
            count = count + bytesRead;  
        }  
        return count;  
    }  
      
    public static void create(File f) throws IOException {  
        if (! f.createNewFile()) {  
            throw new RuntimeException("Create file " + f + " failed for some reason");  
        }  
    }  
}  