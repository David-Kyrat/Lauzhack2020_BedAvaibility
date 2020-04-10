package main;

import SqlLoader;
import healthCare.Hospital;

import java.sql.SQLException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
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
