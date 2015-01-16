package com.fefie.sound_recorder;  
  
import javax.swing.BoxLayout;  
import javax.swing.JPanel;  
  
import com.fefie.sound_recorder.audio.AudioException;  
import com.fefie.sound_recorder.audio.RecordableClip;  
import com.fefie.sound_recorder.utils.SwingUtils;  
  
/** 
 * Panel that displays an audio clip, and has programmatic methods to  
 * stop, play, and record data to and from the clip.  
 *  
 * @author stefie10 
 * 
 */  
public class AudioPanel implements AudioCtrlListener {  
    private final JPanel mPanel;  
    private final ClipViewerPanel mClipPanel;  
    private RecordableClip mClip;  
    public static final boolean SHOW_CONTROLS = true;  
    public static final boolean HIDE_CONTROLS = false;  
    
    private AudioCtrlPanel p;
    public AudioPanel(boolean showControls) {  
        mPanel = SwingUtils.boxPanel(BoxLayout.PAGE_AXIS);  
        if (showControls) {  
            p = new AudioCtrlPanel( mClip);              
            mPanel.add(p.getPanel());  
            p.addListener(this);  
        }  
        mClipPanel = new ClipViewerPanel();  
        mPanel.add(mClipPanel.getPanel());  
                    
    }  
      
    public RecordableClip getClip() {  
        return mClip;  
    }  
    public void showClip(RecordableClip c) {  
        mClipPanel.showClip(c);  
        mClip = c;  
        p.setmClip(c)  ;
  
    }  
      
    public JPanel getPanel() {  
        return mPanel;  
    }  
      
    public void play() throws AudioException {  
        if (mClip != null) {  
            mClip.start();  
        }  
    }  
    public void pause() throws AudioException {  
        if (mClip != null) {  
            mClip.stop();  
        }  
    }  
    public void stop() throws AudioException {  
        if (mClip != null) {  
            mClip.stop();  
            mClip.reset();  
        }  
    }  
    public void record() throws AudioException {  
        System.out.println("Recording");  
        mClip.record();  
    }

	public AudioCtrlPanel getP() {
		return p;
	}

	public void setP(AudioCtrlPanel p) {
		this.p = p;
	}  
    
    
  
}  