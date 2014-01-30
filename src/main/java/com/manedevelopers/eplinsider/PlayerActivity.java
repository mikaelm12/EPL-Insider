package com.manedevelopers.eplinsider;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by mikemikael3 on 1/18/14.
 */
public class PlayerActivity extends Activity {

    public static String playerName;
    TextView tvplayer_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        

        Intent intent = getIntent();
        playerName = intent.getStringExtra("Player");

        tvplayer_name = (TextView)findViewById(R.id.tvPlayerName);
        new FetchItemsTask().execute();

        tvplayer_name.setText(playerName);


}
    /**
     * This method returns an array of the players stats. The order of the entries are
     * Appearances, goals, Yellow Cards, Red Cards
     *
     * @param playerHtml
     * @return
     */
    private String[] getStats(String playerHtml){
        String splitStats[] = new String[4];
        int thirdIndex = playerHtml.lastIndexOf("Total</td");
        int fourthIndex = playerHtml.lastIndexOf("/tbody");

        String  stats = playerHtml.substring(thirdIndex+1, fourthIndex);
        splitStats[0] = stats.split("</td>")[0].substring(4);

        // 0 - Appearance  1- goals 2- Yellow Cards 3- Reds
        return splitStats;

    }



    private class FetchItemsTask extends AsyncTask<Void,Void,String[]> {
        @Override
        protected String[] doInBackground(Void...params){
            try{


                String result = new getLeagueTable().getURL("http://m.premierleague.com/en-gb/players/playersbyclubresults.html/" + playerName.toLowerCase());
                if (playerName.split(" ").length> 1){
                    result = new getLeagueTable().getURL("http://m.premierleague.com/en-gb/players/playersbyclubresults.html/" + playerName.split(" ")[0]+ "-"+ playerName.split(" ")[1]);

                }









                //String leagueTable = teams(result);
                return playerStats(result);
            }catch (IOException ioe){
                Log.e("Test","Failed to fetch URL:", ioe);
            }

            return null;
        }

        /**
         * Returns an Array list of all the players on the selected Team
         * @param html
         * @return
         */


        private String[] playerStats(String html){



            // Setting the flags for each individual teams



            //Checking the flags





            // String split2Players[] = splitPlayers[0].split()

            // int thirdIndex = splitPlayers[5].lastIndexOf("\">");
            // int fourthIndex = splitPlayers[5].indexOf("</a></div");
            //System.out.println(splitPlayers[5].substring(thirdIndex+2,fourthIndex));
            String[] teamMates;



                teamMates = getStats(html);


            return teamMates;
        }

        private String getNumber(String playerBlock){



            int thirdIndex = playerBlock.indexOf("squadNo\">");
            int fourthIndex = playerBlock.indexOf(".&");
            String  number = playerBlock.substring(thirdIndex+ 9, fourthIndex);
            System.out.println(number);
            return number;

        }






        @Override
        protected void onPostExecute(String[] playerStats){


        }

    }
}






