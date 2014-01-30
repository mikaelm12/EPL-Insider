package com.manedevelopers.eplinsider;

import android.app.Activity;
import android.content.Intent;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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
    ArrayList<Player> mPLayers;
    private ListView PlayerList;
    public static String club;
    private String CLUB_NAME = "club_name";

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_squadlist);


        PlayerList = (ListView)findViewById(R.id.lvPLayers);

        club = getIntent().getStringExtra(CLUB_NAME);

        clubname  = (TextView) findViewById(R.id.tvclub_name);

        clubname.setText(club);

        new FetchItemsTask().execute();

        PlayerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView <?> parent, View view, int position,
                                    long id) {
                Intent intent = new Intent(SquadList.this, PlayerActivity.class);
                Player selected_player = mPLayers.get(position);
                intent.putExtra("Player",selected_player.getName());



                startActivity(intent);
            }
        });





    }
    private class FetchItemsTask extends AsyncTask<Void,Void,ArrayList<Player>> {
        @Override
        protected ArrayList<Player> doInBackground(Void...params){
            try{


                String result = new getLeagueTable().getURL("http://m.premierleague.com/en-gb/players/playersbyclubresults.html/" + club.toLowerCase());
                if (club.split(" ").length> 1){
                    result = new getLeagueTable().getURL("http://m.premierleague.com/en-gb/players/playersbyclubresults.html/" + club.split(" ")[0]+ "-"+ club.split(" ")[1]);

                }

                ArrayList<Player> squadList = players(result);







                //String leagueTable = teams(result);
                return squadList;
            }catch (IOException ioe){
                Log.e(TAG,"Failed to fetch URL:", ioe);
            }

            return null;
        }

        /**
         * Returns an Array list of all the players on the selected Team
         * @param html
         * @return
         */


        private ArrayList<Player> players(String html){


            ArrayList<Player> teamMates = new ArrayList<Player>();

            String[] textArray = html.split("");


            // Setting the flags for each individual teams

            int firstIndex = html.indexOf("<div class=\"players-by-clubs\">");
            int secondIndex = html.lastIndexOf("</a></div></div></td");
            StringBuilder playerlist = new StringBuilder();


            //Checking the flags



            for (int i = firstIndex; i <= secondIndex; i++){
                playerlist.append(textArray[i]);

            }



            String splitPlayers[] =  playerlist.toString().split("<tr class=\"alt\">");

            

            // String split2Players[] = splitPlayers[0].split()

            // int thirdIndex = splitPlayers[5].lastIndexOf("\">");
            // int fourthIndex = splitPlayers[5].indexOf("</a></div");
            //System.out.println(splitPlayers[5].substring(thirdIndex+2,fourthIndex));


            for (int i = 0; i<= splitPlayers.length -2; i++){

                int thirdIndex = splitPlayers[i].lastIndexOf("\">");
                int fourthIndex = splitPlayers[i].indexOf("</a></div");
                String  player = splitPlayers[i].substring(thirdIndex+2,fourthIndex);
                Player person = new Player(player);
                
                String num = getNumber(splitPlayers[i]);
                person.setNumber(num);

                teamMates.add(person);
            }

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
        protected void onPostExecute(ArrayList<Player> Players){
            mPLayers = Players;



            PlayerAdapter adapter = new PlayerAdapter(Players);
            PlayerList.setAdapter(adapter);




        }


        private class PlayerAdapter extends ArrayAdapter<Player>{

            TextView tvPlayerNumber;
            TextView  tvPlayerName;

            public PlayerAdapter(ArrayList<Player> players){
                super(SquadList.this, 0, players);
            }
            @Override
            public View getView(int position, View convertView, ViewGroup parent ){
                if (convertView== null){
                    convertView = SquadList.this.getLayoutInflater().inflate(R.layout.list_item_player,null);
                }

                Player player = getItem(position);
                TextView tvPlayerNumber = (TextView) convertView.findViewById(R.id.tvSquadNumber);
                tvPlayerNumber.setText(player.getNumber());

                TextView tvPlayerName = (TextView) convertView.findViewById(R.id.tvPlayerName);
                tvPlayerName.setText(player.getName());



                return convertView;
            }
        }
    }
}
