package com.manedevelopers.eplinsider;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.ListFragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import static android.R.layout.simple_list_item_1;

public class
        LeagueTable_Activity extends Activity {

    public static final String TAG = "LeagueTable";
    TextView test;
    ArrayList<Team> mTeams;
    private ArrayAdapter arrayAdapter;
    private ListView TeamList;
    private String CLUB_NAME = "club_name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaguetable);
            



        
        TeamList = (ListView)findViewById(R.id.lvTeams);
        new FetchItemsTask().execute();


        TeamList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView <?> parent, View view, int position,
                                    long id) {
                Intent intent = new Intent(LeagueTable_Activity.this, SquadList.class);
                Team selected_team = mTeams.get(position);
                intent.putExtra(CLUB_NAME,selected_team.getmClubName());



                startActivity(intent);
            }
        });



}










    public ArrayList<Team> getLeagueTable() {
        //Scrapes the premier league website for the current league table
       return new ArrayList<Team>();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.league_table_, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private class FetchItemsTask extends AsyncTask<Void,Void,ArrayList<Team>>{
        @Override
        protected ArrayList<Team> doInBackground(Void...params){
            try{
                String result = new getLeagueTable().getURL("http://m.premierleague.com/en-gb/league-table.html");
                Log.i(TAG,"Please work"+ result);



                int firstIndex = result.indexOf("<td class=\"col-club\"><a href=\"/en-gb/clubs/club-profile.html");
                int secondIndex = result.lastIndexOf("</a></td>");


                Log.d("Index", "Index 1: "+ firstIndex+ "Index 2:  " +secondIndex);







                ArrayList<Team> leagueTable = teams(result);
                return leagueTable;
            }catch (IOException ioe){
                Log.e(TAG,"Failed to fetch URL:", ioe);
            }

            return null;
        }

        private ArrayList<Team> teams(String html){

            String[] textArray = html.split("");
            String inputLine;
            String TOP_OF_THE_TABLE = "<td class=\"col-club\"><a href=\"/en-gb/clubs/profile.overview.html";
            String END_OF_TEAM = "<td class=\"col-pld\">";  //Our flags for finding the league table
            ArrayList<Team> EPLTeams = new ArrayList<Team>();





            // Setting the flags for each individual teams

            int firstIndex = html.indexOf("<td class=\"col-club\"><a href=\"/en-gb/clubs/club-profile.html");
            int secondIndex = html.lastIndexOf("</a></td>");
            StringBuilder teams = new StringBuilder();

            Log.d("Index", "Index 1: "+ firstIndex+ "Index 2:  " +secondIndex);


            //Checking the flags
            //System.out.println("First Index: " + firstIndex + "\nSecond Index: " + secondIndex);


            //Adding league table to the string builder "teams" as one large string
            for (int y = firstIndex+ TOP_OF_THE_TABLE.length()+1; y < (secondIndex+1 ); y++){
                teams.append(textArray[y]);
            }
            //System.out.println(teams.toString());


            String finalTeams = teams.toString();

            String[] splitter =  finalTeams.split("</tr>");

            StringBuilder Teams = new StringBuilder();
            for(int i =0; i<splitter.length; i++){
                Teams.append(splitter[i]);

            }

            for(int i =0; i<splitter.length; i++){

                String pts = getPts(splitter[i]);

                if (splitter[i].contains("Arsenal")){
                    EPLTeams.add(new Team("Arsenal",i,pts));

                }
                else if (splitter[i].contains("Chelsea")){
                    EPLTeams.add(new Team("Chelsea",i,pts));

                }
                else if (splitter[i].contains("Man City")){
                    EPLTeams.add(new Team("Man-City",i, pts));

                }
                else if (splitter[i].contains("Sunderland")){
                    EPLTeams.add(new Team("Sunderland",i, pts));

                }
                else if (splitter[i].contains("West Ham")){
                    EPLTeams.add(new Team("West-Ham",i, pts));

                }
                else if (splitter[i].contains("Crystal Palace")){
                    EPLTeams.add(new Team("Crystal-Palace",i, pts));
                }
                else if (splitter[i].contains("Cardiff")){
                    EPLTeams.add(new Team("Cardiff",i, pts));
                }
                else if (splitter[i].contains("Fulham")){
                    EPLTeams.add(new Team("Fulham",i, pts));
                }
                else if (splitter[i].contains("Norwich")){
                    EPLTeams.add(new Team("Norwich",i, pts));
                }
                else if (splitter[i].contains("West Brom")){
                    EPLTeams.add(new Team("West-Brom",i, pts));
                }
                else if (splitter[i].contains("Swansea")){
                    EPLTeams.add(new Team("Swansea",i, pts));
                }
                else if (splitter[i].contains("Stoke")){
                    EPLTeams.add(new Team("Stoke",i, pts));
                }
                else if (splitter[i].contains("Aston Villa")){
                    EPLTeams.add(new Team("Aston-Villa",i, pts));
                }
                else if (splitter[i].contains("Hull")){
                    EPLTeams.add(new Team("Hull",i, pts));
                }
                else if (splitter[i].contains("Southampton")){
                    EPLTeams.add(new Team("Southampton",i, pts));

                }
                else if (splitter[i].contains("Newcastle")){
                    EPLTeams.add(new Team("Newcastle",i, pts));
                }
                else if (splitter[i].contains("Man Utd")){
                    EPLTeams.add(new Team("Man-Utd",i, pts));
                }
                else if (splitter[i].contains("Spurs")){
                    EPLTeams.add(new Team("Spurs",i, pts));
                }
                else if (splitter[i].contains("Everton")){
                    EPLTeams.add(new Team("Everton",i, pts));
                }
                else if (splitter[i].contains("Liverpool")){
                    EPLTeams.add(new Team("Liverpool",i, pts));
                }




            }




            return EPLTeams;
        }

        private String getPts(String teamBlock){
            int thirdIndex = teamBlock.lastIndexOf("td class=\"col-pts\"");
            int fourthIndex = teamBlock.lastIndexOf("</td>");
            String  pts = teamBlock.substring(thirdIndex+ 19, fourthIndex);

            return pts;
        }

       /* private String getAge(String teamBlock){

        }

        private String getNationality(String teamBlock){

        }

        private String getGoals(String teamBLock){

        }

        private String getPosition(String teamBlock){

        }

        */


    @Override
        protected void onPostExecute(ArrayList<Team> EPLTeams){
                mTeams = EPLTeams;
        String[] songsArray = new String[10];

        Log.d("EPLTeams", "TestSize: "+ EPLTeams.size());


        // Fill the songs array by using a for loop




       // ArrayAdapter arrayAdapter = new ArrayAdapter(LeagueTable_Activity.this, android.R.layout.simple_list_item_1, EPLTeams);
     //   TeamList.setAdapter(arrayAdapter);

        TeamAdapter adapter = new TeamAdapter(EPLTeams);
        TeamList.setAdapter(adapter);

                //setupAdapter();

            }

    }

    private class TeamAdapter extends ArrayAdapter<Team>{

        TextView tvClub;

        public TeamAdapter(ArrayList<Team> teams){
            super(LeagueTable_Activity.this, 0, teams);
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent ){
            if (convertView== null){
                convertView = LeagueTable_Activity.this.getLayoutInflater().inflate(R.layout.list_item_team,null);
            }

            Team team = getItem(position);
            tvClub = (TextView) convertView.findViewById(R.id.tvClub);
            tvClub.setText(team.getmClubName());

            TextView tvRank = (TextView) convertView.findViewById(R.id.tvRank);
            tvRank.setText(String.valueOf(team.getmRank()));

            TextView tvPoints = (TextView)convertView.findViewById(R.id.tvPoints);
            tvPoints.setText(team.getmPoints());

            return convertView;
        }
    }

}




