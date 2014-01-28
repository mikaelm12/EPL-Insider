package com.manedevelopers.eplinsider;

import java.util.ArrayList;

/**
 * Created by mikemikael3 on 1/2/14.
 */
public class Team {

    private String mClubName;
    private ArrayList<String> mSquad;
    private String mManager;



    public Team(String club, int rank, String pts){
        mClubName = club;
        mRank = rank+1;
        mPoints= pts;



    }


    public int getmRank() {
        return mRank;
    }

    public void setmRank(int mRank) {
        this.mRank = mRank;
    }

    private int mRank;

    public String getmPoints() {
        return mPoints;
    }

    public void setmPoints(String mPoints) {
        this.mPoints = mPoints;
    }

    private String mPoints;




    @Override
    public String toString(){
        return ""+mRank+ "                    "+ mClubName + "              "+ mPoints;
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
