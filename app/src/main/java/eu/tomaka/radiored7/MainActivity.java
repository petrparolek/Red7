package eu.tomaka.radiored7;


import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import java.io.IOException;

public class MainActivity extends Activity implements View.OnClickListener {

    private MediaPlayer mp;
    private Button buttonPlay;
    private Button buttonStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonPlay = (Button) findViewById(R.id.buttonPlay);
        buttonStop = (Button) findViewById(R.id.buttonStop);
        buttonPlay.setOnClickListener(this);
        buttonStop.setEnabled(false);
        buttonStop.setOnClickListener(this);
        initializeMP();
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Enable/disable correct buttons
        if ( mp.isPlaying() ) {

            buttonPlay.setEnabled(false);
            buttonStop.setEnabled(true);
        }
        else{

            buttonPlay.setEnabled(true);
            buttonStop.setEnabled(false);
        }

    }

    public void onClick(View v) {
        if (v == buttonPlay) {
            startRadio();
        } else if (v == buttonStop) {
            stopRadio();
        }
    }

    private void initializeMP(){
        mp = new MediaPlayer();
        try {
            mp.setDataSource("http://sluchaj.radiors.pl:19182");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void startRadio() {

        mp.prepareAsync();
        mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

            public void onPrepared(MediaPlayer mp) {
                MainActivity.this.mp.start();
            }
        });

        buttonPlay.setEnabled(false);
        buttonStop.setEnabled(true);
    }

    private void stopRadio() {

        if ( mp.isPlaying() ) {
            mp.stop();
            mp.release();
            initializeMP();
        }

        buttonPlay.setEnabled(true);
        buttonStop.setEnabled(false);
    }


}
