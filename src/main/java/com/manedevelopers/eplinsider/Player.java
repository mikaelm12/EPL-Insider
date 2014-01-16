package com.manedevelopers.eplinsider;

/**
 * Created by mikemikael3 on 1/2/14.
 */
public class Player {


  private String mName;
  private String mNationalTeam;
  private int mAge;
  private double mWeight;
  private double mHeight;
  private int mGoalsScored;
  private int mEPLApps;


    public Player(String name){
        mName = name;


    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getNationalTeam() {
        return mNationalTeam;
    }

    public void setNationalTeam(String mNationalTeam) {
        this.mNationalTeam = mNationalTeam;
    }

    public int getAge() {
        return mAge;
    }

    public void setAge(int mAge) {
        this.mAge = mAge;
    }

    public double getWeight() {
        return mWeight;
    }

    public void setWeight(double mWeight) {
        this.mWeight = mWeight;
    }

    public double getHeight() {
        return mHeight;
    }

    public void setHeight(double mHeight) {
        this.mHeight = mHeight;
    }

    public int getGoalsScored() {
        return mGoalsScored;
    }

    public void setGoalsScored(int mGoalsScored) {
        this.mGoalsScored = mGoalsScored;
    }

    public int getEPLApps() {
        return mEPLApps;
    }

    public void setEPLApps(int mEPLApps) {
        this.mEPLApps = mEPLApps;
    }




}
