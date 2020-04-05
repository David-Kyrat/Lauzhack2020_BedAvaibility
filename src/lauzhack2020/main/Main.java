package lauzhack2020.main;

import lauzhack2020.SqlLoader;
import lauzhack2020.gps.Location;
import lauzhack2020.healthCare.Hospital;
import lauzhack2020.healthCare.administration.Canton;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList<Hospital> hospitals = new ArrayList<>();
        SqlLoader loader = new SqlLoader();

       ArrayList<ArrayList<Object>> tableHospital = loader.getTblAsList("hospital");
        for (ArrayList<Object> list : tableHospital){
           System.out.println("_________");
           for (Object cellValue : list) {
               System.out.println(cellValue);
           }
        }
    }

}
