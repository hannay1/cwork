package com.example.mainpc.musicplayer;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import java.io.File;
import java.util.ArrayList;

public class Player extends AppCompatActivity implements View.OnClickListener {

    static MediaPlayer mp; //must be static or songs will play over eachother
    ArrayList<File> the_songs;
    static Button pl, fwd, rev, next, prev; //pl_ps will pause/play song
    SeekBar sk;
    Uri yooo;
    int pos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);


        pl = (Button) findViewById(R.id.play);
        fwd = (Button) findViewById(R.id.fwd_button);
        rev = (Button) findViewById(R.id.rev_button);
        next = (Button) findViewById(R.id.next_button);
        prev = (Button) findViewById(R.id.prev_button);

        pl.setOnClickListener(this);
        fwd.setOnClickListener(this);
        rev.setOnClickListener(this);
        next.setOnClickListener(this);
        prev.setOnClickListener(this);




        if(mp != null)
        {
            //stops song after going back to main menu and clicking on another song
            mp.stop();
            mp.release();
        }

        Intent init = getIntent();
        Bundle bunduru = init.getExtras();
        the_songs =(ArrayList) bunduru.getParcelableArrayList("arr_of_songs");
        pos = bunduru.getInt("pos", 0);

        //GET WHATEVER SONG WAS CLICKED ON
        yooo = Uri.parse(the_songs.get(pos).toString()); //UNIVERSAL RESOURCE INDICATOR
        mp = MediaPlayer.create(getApplicationContext(), yooo);
        mp.start();

    }



    @Override
    public void onClick(View v) {


        int id = v.getId();

        switch(id)
        {
            case R.id.play:
                if(mp.isPlaying())
                {
                    pl.setText(">");
                    mp.pause();

                }else
                {
                    mp.start();
                    pl.setText("||");
                }
                break;
            case R.id.fwd_button:
                mp.seekTo(mp.getCurrentPosition()+600);
                break;
            case R.id.rev_button:
                mp.seekTo(mp.getCurrentPosition()-600);
                break;
            case R.id.next_button:
                mp.stop();
                mp.release(); //STOP PLAYBACK BEFORE NEXT SONG
                pos = (pos + 1) % the_songs.size(); //does not get next song if no next song
                yooo = Uri.parse(the_songs.get(pos).toString()); //get next song...
                mp = MediaPlayer.create(getApplicationContext(), yooo);
                mp.start();
                break;
            case R.id.prev_button:
                mp.stop();
                mp.release(); //STOP PLAYBACK BEFORE PREV SONG
                pos = (pos - 1 < 0) ? the_songs.size() -1 : pos -1; //if song is first, then songs is first in list (size -1); IF NOT THEN its the prev song
                yooo = Uri.parse(the_songs.get(pos).toString()); //get prev song...
                mp = MediaPlayer.create(getApplicationContext(), yooo);
                mp.start();
                break;





        }
    }
}
