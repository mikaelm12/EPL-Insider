package com.manedevelopers.eplinsider;

import java.util.ArrayList;

/**
 * Created by mikemikael3 on 1/2/14.
 */
public class Team {

    private String mClubName;
    private ArrayList<String> mSquad;
    private String mManager;
    private int mRank;


    public Team(String club, int rank){
        mClubName = club;
        mRank = rank+1;



    }


    @Override
    public String toString(){
        return ""+mRank+ "                    "+ mClubName;
    }
    public String getmClubName() {
        return mClubName;
    }

    public void setmClubName(String mClubName) {
        this.mClubName = mClubName;
    }

    public ArrayList<String> getmSquad() {
        return mSquad;
    }

    public void setmSquad(ArrayList<String> mSquad) {
        this.mSquad = mSquad;
    }

    public String getmManager() {
        return mManager;
    }

    public void setmManager(String mManager) {
        this.mManager = mManager;
    }


}
