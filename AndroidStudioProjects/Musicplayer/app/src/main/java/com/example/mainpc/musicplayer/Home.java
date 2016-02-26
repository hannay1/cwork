//first android project
package com.example.mainpc.musicplayer;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;


public class Home extends AppCompatActivity {
    private ListView lv;
    String[] itm;
    //private Button play_btn, pause_btn, fwd_btn, bck_btn;  //for buttons
    //private MediaPlayer mp; //media player object
   // private SeekBar sk; //slider for song
   // private final Handler hndlr = new Handler();
   // private void slider_update(){}


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        lv = (ListView) findViewById(R.id.play_list);

        final ArrayList<File> the_list = get_tunes(Environment.getExternalStorageDirectory());
        //the list of songs, get_tunes lists all files in supplied virtual external storage directory
        itm = new String[the_list.size()];

        for(int i = 0; i < the_list.size(); i++)
        {
            toaster(the_list.get(i).getName().toString()); //gets the name of each mp3 file
            itm[i] = the_list.get(i).getName().toString().replace(".mp3", ""); //.replace(".wav", "");
        }

        ArrayAdapter<String> adp = new ArrayAdapter<String>(getApplicationContext(),R.layout.song_item,R.id.textView,itm);
        lv.setAdapter(adp);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getApplicationContext(),Player.class).putExtra("pos",position).putExtra("arr_of_songs", the_list));
            }
        });


    }

    private ArrayList<File> get_tunes(File dir)
    {
        //gets songs from sd card

        ///uses recursion to search thru files
        ArrayList<File> arl = new ArrayList<File>();
        File[] all_files = dir.listFiles(); //all files
        for(File af : all_files) //for each singular file
        {
            if(af.isDirectory() && !af.isHidden()) //also checks if file is hidden
            {
                arl.addAll(get_tunes(af)); //recursion. if af is a directory, go into it again

            }
            else
            {
                if(af.getName().endsWith(".mp3")); //.endsWith(".wav"))
                {
                    arl.add(af); //add  the song to the temp list of files..
                }
            }
        }

        return arl;
    }

    //toaster provides Toast notifications

    private void toaster(String str)
    {
        Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
