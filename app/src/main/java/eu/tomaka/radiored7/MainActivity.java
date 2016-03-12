package eu.tomaka.radiored7;


import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends Activity implements View.OnClickListener {

    private MediaPlayer mp;
    private Button buttonPlay;
    private Button buttonStop;
    private Spinner spinnerRaioChannel;
    private HashMap<String, String> channelSCAddressHash = new HashMap<String, String>();
    private String choosenChannel = "Główny";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnerRaioChannel = (Spinner) findViewById(R.id.spinnerRaioChannel);
        buttonPlay = (Button) findViewById(R.id.buttonPlay);
        buttonStop = (Button) findViewById(R.id.buttonStop);
        buttonPlay.setOnClickListener(this);
        buttonStop.setEnabled(false);
        buttonStop.setOnClickListener(this);
        setupShoutcastAddresses();
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
            mp.setDataSource(channelSCAddressHash.get(choosenChannel));
//            mp.setDataSource("http://sluchaj.radiors.pl:19182");
        } catch (IOException e) {
            Log.e("Red7", "Unable initialize player with address " + channelSCAddressHash.get(choosenChannel));
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

    private void setupShoutcastAddresses(){
        channelSCAddressHash.put("Główny", "http://sluchaj.radiors.pl:19182");
        channelSCAddressHash.put("Fly", "http://sluchaj.radiors.pl:19204");
        channelSCAddressHash.put("Disco-Polo", "http://sluchaj.radiors.pl:19206");
        List<String> channelList = new ArrayList(channelSCAddressHash.keySet());
        Collections.sort(channelList,Collections.reverseOrder());
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item,channelList);
        spinnerRaioChannel.setAdapter(dataAdapter);
        // Spinner item selection Listener
        addListenerOnSpinnerItemSelection();

        // Button click Listener
        addListenerOnButton();
    }
    // Add spinner data

    public void addListenerOnSpinnerItemSelection(){

//        spinnerRaioChannel.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }

    //get the selected dropdown list value

    public void addListenerOnButton() {


    }
}
