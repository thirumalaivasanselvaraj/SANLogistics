package com.saneforce.logistics.Spinner_Response;

import java.util.ArrayList;
import java.util.Iterator;

public class Spinner_Pojo {
    ArrayList<String>  MonthlyVolume = new ArrayList<>();
    ArrayList<String>  Segment = new ArrayList<>();
    ArrayList<String>  Sector = new ArrayList<>();
    public void SetMonthlyVolume(String arrayList) {



        MonthlyVolume.add(arrayList);

    }


    public ArrayList<String> GetMonthlyVolume() {

        return MonthlyVolume;
    }


    public void SetSegment(String arrayList) {



        Segment.add(arrayList);

    }
    public ArrayList<String> GetSegment() {

        return Segment;
    }
    public void SetSector(String arrayList) {



        Sector.add(arrayList);

    }
    public ArrayList<String> GetSector() {

        return Sector;
    }








}
