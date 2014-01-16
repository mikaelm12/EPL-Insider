package com.manedevelopers.eplinsider;

import android.app.Activity;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by mikemikael3 on 1/4/14.
 */
public class SquadList extends Activity {
    public static final String TAG = "LeagueTable";
    TextView clubname;
    TextView html_res;
    public static String club;
    private String CLUB_NAME = "club_name";

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_squadlist);




        club = getIntent().getStringExtra(CLUB_NAME);

        clubname  = (TextView) findViewById(R.id.tvclub_name);

        clubname.setText(club);

        new FetchItemsTask().execute();




    }
    private class FetchItemsTask extends AsyncTask<Void,Void,String> {
        @Override
        protected String doInBackground(Void...params){
            try{
                String result = new getLeagueTable().getURL("http://m.premierleague.com/en-gb/players/playersbyclubresults.html/"+ club.toLowerCase());
                Log.i(TAG, "Please work" + result);



                int firstIndex = result.indexOf("<td class=\"col-club\"><a href=\"/en-gb/clubs/club-profile.html");
                int secondIndex = result.lastIndexOf("</a></td>");
                StringBuilder teams = new StringBuilder();

                Log.d("Index", "Index 1: "+ firstIndex+ "Index 2:  " +secondIndex);







                //String leagueTable = teams(result);
                return result;
            }catch (IOException ioe){
                Log.e(TAG,"Failed to fetch URL:", ioe);
            }

            return null;
        }

        private String players(String html){
            /* TODO Write team list parser*/

            return "Hi there";

        }

        @Override
        protected void onPostExecute(String players){



            html_res = (TextView)findViewById(R.id.html);

            html_res.setText(players);





            //setupAdapter();

        }

    }
}
