package com.fefie.sound_recorder;  
  
import java.awt.event.ActionEvent;  
import java.util.ArrayList;  
import java.util.List;  
  
import javax.sound.sampled.AudioFormat;
import javax.swing.BoxLayout;  
import javax.swing.JButton;  
import javax.swing.JComboBox;
import javax.swing.JPanel;  
  
import com.fefie.sound_recorder.audio.AudioException;  
import com.fefie.sound_recorder.audio.RecordableClip;
import com.fefie.sound_recorder.utils.SwingUtils;  
  
/** 
 * A control panel.  It communicates with an AudioCtrlListener when buttons are pushed. 
 * @author stefie10 
 * 
 */  
public class AudioCtrlPanel {  
      
    private final JPanel mPanel;  
    private final JButton mPlayButton;  
    private final JButton mPauseButton;  
    private final JButton mStopButton;  
    private final JButton mRecordButton; 
    private JComboBox mSamplingRates = new JComboBox();
    public static int samplingRate = 16000;
    public static  AudioFormat format=new AudioFormat(16000, 16, 1, false, false);
    RecordableClip mClip;
    public AudioCtrlPanel(RecordableClip mClip) {
    	this.mClip = mClip;
        mPanel = SwingUtils.boxPanel(BoxLayout.LINE_AXIS);  
          
        mPlayButton = new JButton("Play");  
        mPanel.add(mPlayButton);  
        mPauseButton = new JButton("Pause");  
        mPanel.add(mPauseButton);  
        mStopButton = new JButton("Stop");  
        mPanel.add(mStopButton);  
        mRecordButton = new JButton("Record");  
        mPanel.add(mRecordButton);  
        mSamplingRates.addItem(800);
        mSamplingRates.addItem(1000);
        mSamplingRates.addItem(1200);
        mSamplingRates.addItem(1400);
        mSamplingRates.addItem(1600);  
        mPanel.add(mSamplingRates);  
        mPlayButton.addActionListener(new ExceptionActionListener() {  
            public void doActionPerformed(ActionEvent ae) throws AudioException {  
                for (AudioCtrlListener l : mListeners) {  
                    l.play();  
                }  
            }  
        });  
          
        mPauseButton.addActionListener(new ExceptionActionListener() {  
            public void doActionPerformed(ActionEvent ae) throws AudioException {  
                for (AudioCtrlListener l : mListeners) {  
                    l.pause();  
                }  
            }  
        });  
          
          
        mStopButton.addActionListener(new ExceptionActionListener() {  
            public void doActionPerformed(ActionEvent ae) throws AudioException {  
                for (AudioCtrlListener l : mListeners) {  
                    l.stop();  
                }  
            }  
        });  
          
        mRecordButton.addActionListener(new ExceptionActionListener() {  
            public void doActionPerformed(ActionEvent ae) throws AudioException {  
                for (AudioCtrlListener l : mListeners) {  
                    l.record();  
                }  
            }  
        }); 
       
        mSamplingRates.addActionListener(new ExceptionActionListener() {
            public void doActionPerformed(ActionEvent e) {
            	 format = new AudioFormat((Integer) ((JComboBox) e.getSource()).getSelectedItem(), 16, 1, false, false); 
            	 if(null != getmClip()){
            		 getmClip().setmFormat(format);
            	 }
            	
            }
          });
    }  
    public JPanel getPanel() {  
        return mPanel;  
    }  
      
    public RecordableClip getmClip() {
		return mClip;
	}
	public void setmClip(RecordableClip mClip) {
		this.mClip = mClip;
	}

	private final List<AudioCtrlListener> mListeners = new ArrayList();  
    public void addListener(AudioCtrlListener l) {  
        mListeners.add(l);  
    }
	
    
}  