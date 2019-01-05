package com.saneforce.logistics.Spinner_Response;

import java.util.ArrayList;

public class Enquiry_Spinner_Pojo {

    ArrayList<String> Comidity = new ArrayList<>();
    ArrayList<String> Priority = new ArrayList<>();
    ArrayList<String> Incoterms = new ArrayList<>();
    ArrayList<String> PortName = new ArrayList<>();
    ArrayList<String> DestiPortName = new ArrayList<>();
    ArrayList<String> PackagingType = new ArrayList<>();
    ArrayList<String> ContainerType = new ArrayList<>();

    public void SetComidity(String arrayList) {


        Comidity.add(arrayList);

    }


    public ArrayList<String> GetComidity() {

        return Comidity;
    }

    public void SetPriority(String arrayList) {


        Priority.add(arrayList);

    }


    public ArrayList<String> GetPriority() {

        return Priority;
    }

    public void SetIncoterms(String arrayList) {


        Incoterms.add(arrayList);

    }


    public ArrayList<String> GetIncoterms() {

        return Incoterms;
    }

    public void SetPortName(String arrayList) {


        PortName.add(arrayList);

    }


    public ArrayList<String> GetPortName() {

        return PortName;
    }

    public void SetDestiPortName(String arrayList) {


        DestiPortName.add(arrayList);

    }


    public ArrayList<String> GetDestiPortName() {

        return DestiPortName;
    }


    public void SetPackagingType(String arrayList) {


        PackagingType.add(arrayList);

    }


    public ArrayList<String> GetPackagingType() {

        return PackagingType;
    }
    public void SetContainerType(String arrayList) {


        ContainerType.add(arrayList);

    }


    public ArrayList<String> GetContainerType() {

        return ContainerType;
    }

    public void Clear() {
        PortName.clear();
    }

    public void DestiprtClear() {
        DestiPortName.clear();
    }

    public void AllClear(){

        PackagingType.clear();

        Comidity.clear();
        Priority.clear();
        Incoterms.clear();


        PackagingType.clear();
        ContainerType.clear();
    }
}
