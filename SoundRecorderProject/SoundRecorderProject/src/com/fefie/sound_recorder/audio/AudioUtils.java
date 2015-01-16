package com.fefie.sound_recorder.audio;  
  
import javax.sound.sampled.AudioFileFormat;  
import javax.sound.sampled.AudioFormat;  
import javax.sound.sampled.AudioInputStream;  
import javax.sound.sampled.AudioSystem;  
import javax.sound.sampled.DataLine;  
import javax.sound.sampled.Line;  
import javax.sound.sampled.LineUnavailableException;  
import javax.sound.sampled.Mixer;  
import javax.sound.sampled.TargetDataLine;  
  
public class AudioUtils {  
      
    public static AudioFileFormat.Type RAW_TYPE = new AudioFileFormat.Type("raw", "raw");  
      
    public static class LineAndStream {  
        private final TargetDataLine mLine;  
        private final AudioInputStream mStream;  
        public TargetDataLine getLine() {  
            return mLine;  
        }  
        public AudioInputStream getStream() {  
            return mStream;  
        }  
        public LineAndStream(TargetDataLine line, AudioInputStream stream) {  
            mLine = line;  
            mStream = stream;  
        }  
    }  
  
    /** 
     * Gets an AudioInputStream that reads from a microphone and outputs the specified format.  
     * Throws an exception if it can't.  
     */  
    public static LineAndStream getRecordingStream(AudioFormat format) {  
        try {  
              
            TargetDataLine line = AudioSystem.getTargetDataLine(format);  
            return new LineAndStream(line, new AudioInputStream(line));  
              
        } catch (Exception iae) {  
            int mNum = -1;  
            for (Mixer.Info mInfo : AudioSystem.getMixerInfo()) {  
                mNum++;  
                Mixer m = AudioSystem.getMixer(mInfo);  
                for (Line.Info lInfo : m.getTargetLineInfo()) {  
                    try {  
                        if (lInfo.getLineClass().equals(TargetDataLine.class)) {  
                            DataLine.Info dInfo = (DataLine.Info) lInfo;  
                            for (AudioFormat f: dInfo.getFormats()) {  
                                if (AudioSystem.isConversionSupported(format, f)) {  
                                    try {  
                                        TargetDataLine candidate = (TargetDataLine) m.getLine(dInfo);  
                                        AudioFormat specifiedFormat = new AudioFormat(f.getEncoding(), format.getSampleRate(), f.getSampleSizeInBits(), f.getChannels(), f.getFrameSize(), format.getSampleRate(), f.isBigEndian());  
                                          
                                        candidate.open(specifiedFormat);  
                                          
  
                                        AudioInputStream baseStream = new AudioInputStream(candidate);  
  
                                        return new LineAndStream(candidate, AudioSystem.getAudioInputStream(format, baseStream));  
                                    } catch (IllegalArgumentException iae1) {  
                                        iae1.printStackTrace();  
                                    }  
                                }  
                            }  
                          
                              
  
                        }  
                    } catch (LineUnavailableException lue) {  
                        //lue.printStackTrace();  
                    }  
  
                }  
            }  
              
        }  
        throw new IllegalArgumentException("Couldn't make line for format " + format);  
    }  
  
}  