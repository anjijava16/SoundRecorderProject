package com.fefie.sound_recorder.audio;  
  
import java.io.IOException;  
import java.io.OutputStream;  
import java.util.ArrayList;  
import java.util.List;  
  
public class MyByteArrayOutputStream extends OutputStream {  
    private byte[] mBuf;  
    private int mOffset;  
    public MyByteArrayOutputStream(byte[] myArray) {  
        this(myArray, 0);  
    }  
    public MyByteArrayOutputStream(byte[] myArray, int offset) {  
        super();  
        mBuf = myArray;  
        mOffset = offset;  
    }  
  
  
    public void write(int b) throws IOException {  
        mBuf[mOffset] = (byte) b;  
        mOffset++;  
    }  
  
  
    public void write(byte[] b, int off, int len) throws IOException {  
        int start = mOffset;  
        mOffset = mOffset + len;  
        System.arraycopy(b, off, mBuf, start, len);  
          
        notify(start, len);  
          
          
    }  
    private void notify(int offset, int length) {  
        for (NewDataListener l : mListeners) {  
            l.newData(offset, length);  
        }  
    }  
      
    private List<NewDataListener> mListeners = new ArrayList<NewDataListener>();  
    public void addListener(NewDataListener l) {  
        mListeners.add(l);  
    }  
    public static interface NewDataListener {  
        public void newData(int offset, int length);  
    }  
      
}  