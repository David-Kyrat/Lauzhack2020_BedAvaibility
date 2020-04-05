package lauzhack2020;

import java.sql.*;
import java.util.ArrayList;

public class SqlLoader {

    public static void main(String[] args) {
        SqlLoader loader = new SqlLoader();
        try {
            ArrayList<ArrayList<Object>> table1 = loader.getTblAsList("hospital");

        } catch (Exception e) {
            System.out.println("\n" + e + "\n");
            //e.printStackTrace();
        }
    }

    /**
     * Method to use to choose from which table in the DataBase you want to retrieve data from
     * @param tableName String : Name of the table to retrieve the data from (e.g. hospital, unit or patient)
     * @return resultSet -- ResultSet : data from Table asked (further action are required to print the values from this resultSet)
     */
    public ResultSet getResultSet(String tableName) {
        ResultSet rs = null;
        try {
            Statement stmt = initConnection().createStatement();
            rs = stmt.executeQuery("select * from " + tableName);
            return rs;

        } catch (Exception e) {
            System.out.println(e);
        }
        return rs;
    }

    /**
     * Method to use to make personalized SQL Query other than just "select * from table"
     * @param query String SQL query to make
     * @return resultSet -- ResultSet the tbl associated to the query
     */
    public ResultSet getQueryResultSet(String query) {
        ResultSet rs = null;
        try {
            Statement stmt = initConnection().createStatement();
            rs = stmt.executeQuery(query);
            return rs;

        } catch (Exception e) {
            System.out.println(e);
        }
        return rs;
    }

    /**
     * Return the <b>value</b> of the <b>cell</b> from the <b>table</b> "tableName", <b>located</b> at <b>column</b>
     * "columnName" and <b>row</b> "row" as a <b>String</b> or an <b>Integer</b> <br/>
     * (e.g getValueFrom(Hospital, Capacity, 1) will return the capacity of the 1st hospital in the dataBase) <br/>
     * (<b>Warning</b> : the returned object cannot be used neither
     * with a method that is specific to String Type if the returned object is an Integer
     * and vice versa, nor with a method that is specific to Object Type )
     * @param columnName String
     * @param row int
     * @param rs ResultSet
     * @return value -- Integer or String
     */
    private Object getValueFrom(String columnName, int row, ResultSet rs) {
        String str = null;
        Integer nb = null;
        boolean string = false;

        try {
            for (int i = 1 ; i <= row ; i++) {
                rs.next();
            }
            str = rs.getString(columnName);

            try {
                nb = Integer.parseInt(str);
                return nb;
            } catch (NumberFormatException n) {
                string = true;
                return str;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return string ? (String) str : (Integer) nb;
    }

    /**
     * Return the <b>value</b> of the <b>cell</b> from the <b>table</b> "tableName", <b>located</b> at <b>column</b>
     * "columnNb" and <b>row</b> "row" as a <b>String</b> or an <b>Integer</b> <br/>
     * (e.g getValueFrom(Hospital, Capacity, 1) will return the capacity of the 1st hospital in the dataBase) <br/>
     * (<b>Warning</b> : the returned object cannot be used neither
     * with a method that is specific to String Type if the returned object is an Integer
     * and vice versa, nor with a method that is specific to Object Type )
     * @param columnNb int
     * @param row int
     * @param rs ResultSet
     * @return value -- Integer or String
     */
    private Object getValueFrom(int columnNb, int row, ResultSet rs) {
        String str = null;
        Integer nb = null;
        boolean string = false;

        try {
            for (int i = 1 ; i <= row ; i++) {
                rs.next();
            }
            str = rs.getString(columnNb);

            try {
                nb = Integer.parseInt(str);
                return nb;
            } catch (NumberFormatException n) {
                string = true;
                return str;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return string ? (String) str : (Integer) nb;
    }

    /**
     * Return the <b>value</b> of the <b>cell</b> from the <b>table</b> "tableName", <b>located</b> at <b>column</b>
     * "columnName" and <b>row</b> "row" as a <b>String</b> or an <b>Integer</b> <br/>
     * (e.g getValueFrom(Hospital, Capacity, 1) will return the capacity of the 1st hospital in the dataBase) <br/>
     * (<b>Warning</b> : the returned object cannot be used neither
     * with a method that is specific to String Type if the returned object is an Integer
     * and vice versa, nor with a method that is specific to Object Type )
     * @param tableName String
     * @param columnName String
     * @param row int
     * @return value -- Integer or String
     */
    public Object getValueFrom(String tableName, String columnName, int row) {
        return getValueFrom(columnName, row, getResultSet(tableName));
    }

    /**
     * Return the <b>value</b> of the <b>cell</b> from the <b>table</b> "tableName", <b>located</b> at <b>column</b>
     * "columnNb" and <b>row</b> "row" as a <b>String</b> or an <b>Integer</b> <br/>
     * (e.g getValueFrom(Hospital, Capacity, 1) will return the capacity of the 1st hospital in the dataBase) <br/>
     * (<b>Warning</b> : the returned object cannot be used neither
     * with a method that is specific to String Type if the returned object is an Integer
     * and vice versa, nor with a method that is specific to Object Type )
     * @param tableName String
     * @param columnNb String
     * @param row int
     * @return value -- Integer or String
     */
    public Object getValueFrom(String tableName, int columnNb, int row) {
        return getValueFrom(columnNb, row, getResultSet(tableName));
    }

    /**
     * Return the <b>value</b> of the <b>cell</b> from the <b>table</b> returned by the SQL query, <b>located</b> at <b>column</b>
     * "columnName" and <b>row</b> "row" as a <b>String</b> or an <b>Integer</b> <br/>
     * (e.g getValueFrom(Hospital, Capacity, 1) will return the capacity of the 1st hospital in the dataBase) <br/>
     * (<b>Warning</b> : the returned object cannot be used neither
     * with a method that is specific to String Type if the returned object is an Integer
     * and vice versa, nor with a method that is specific to Object Type )
     * @param columnName String
     * @param row int
     * @param query String
     * @return value -- Integer or String
     */
    public Object getValueFrom(String columnName, int row, String query) {
        return getValueFrom(columnName, row, getQueryResultSet(query));
    }

    /**
     * Return the <b>value</b> of the <b>cell</b> from the <b>table</b> returned by the SQL query, <b>located</b> at <b>column</b>
     * "columnNb" and <b>row</b> "row" as a <b>String</b> or an <b>Integer</b> <br/>
     * (e.g getValueFrom(Hospital, Capacity, 1) will return the capacity of the 1st hospital in the dataBase) <br/>
     * (<b>Warning</b> : the returned object cannot be used neither
     * with a method that is specific to String Type if the returned object is an Integer
     * and vice versa, nor with a method that is specific to Object Type )
     * @param columnNb String
     * @param row int
     * @param query String
     * @return value -- Integer or String
     */
    public Object getValueFrom(int columnNb, int row, String query) {
        return getValueFrom(columnNb, row, getQueryResultSet(query));
    }

    /**
     * Return the given Table as bi-dimensional ArrayList of Objects
     * @param tableName String
     * @param rs ResultSet
     * @return table
     */
    private ArrayList<ArrayList<Object>> getTblAsList(String tableName, ResultSet rs) {
        ArrayList<ArrayList<Object>> table = new ArrayList<ArrayList<Object>>();
        int i = 1;

        try {
            while (rs.next()) {
                table.add(fillArray(tableName, i));
                i++;
            }
            return table;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return table;
    }

    /**
     * Return the given Table as bi-dimensional ArrayList of Objects
     * @param tableName String
     * @return table
     */
    public ArrayList<ArrayList<Object>> getTblAsList(String tableName) {
        return getTblAsList(tableName, getResultSet(tableName));
    }

    /**
     * Return the table returned by the SQL query as bi-dimensional ArrayList of Objects
     * @param tableName String
     * @param query String
     * @return table
     */
    public ArrayList<ArrayList<Object>> getTblAsList(String tableName, String query) {
        return getTblAsList(tableName, getQueryResultSet(query));
    }

    /**
     * Used in other method to print the whole required table
     * tables available: (Unit, Patient, Hospital)
     * @param tableName String
     * @param rs ResultSet
     */
    private void printTbl(String tableName, ResultSet rs) {
        try {
            int i;
            switch (tableName) {
                case "unit":
                case "Unit":
                    System.out.println("Unit : ");
                    i = 1;
                    while (rs.next()) {
                        System.out.println(i + ") " + rs.getString("UnitType_id"));
                        System.out.println("Capacity: " + rs.getInt("Capacity"));
                        System.out.println("UsedBedNb: " + rs.getInt("UsedBedNb"));
                        System.out.println("FreeBedNb: " + rs.getInt("FreeBedNb"));
                        System.out.println("CovidBedNb: " + rs.getInt("CovidBedNb") + ", ");
                        System.out.println("Name of the associated Hospital: " + rs.getString("fk_hospital_id"));
                        System.out.println("_____");
                        i++;
                    }
                    System.out.println();
                    break;

                case "hospital":
                case "Hospital":
                    System.out.println("Hospital : ");
                    i = 1;
                    while (rs.next()) {
                        System.out.println(i + ") " + rs.getString("hospital_id"));
                        System.out.print("StName: " + rs.getString("StName") + ", ");
                        System.out.println("StNb: " + rs.getInt("StNb"));
                        System.out.println("NPA: " + rs.getInt("NPA"));
                        System.out.println("Town: " + rs.getString("Town"));
                        System.out.println("Canton: " + rs.getString("Canton"));
                        System.out.println("Capacity: " + rs.getString("Capacity"));
                        System.out.println("FreeBeds: " + rs.getInt("FreeBeds"));
                        System.out.println("_____");
                        i++;
                    }
                    System.out.println();
                    break;

                case "patient":
                case "Patient":
                    System.out.println("Patient :");
                    i = 1;
                    while (rs.next()) {
                        System.out.println(i + ") " + "Patient_id: " + rs.getInt("Patient_id"));
                        System.out.println("FirstName: " + rs.getString("FirstName"));
                        System.out.println("LastName: " + rs.getString("LastName"));
                        System.out.println("Age: " + rs.getInt("Age"));
                        System.out.println("RiskFactor: " + rs.getInt("RiskFactor"));
                        System.out.println("HealthState: " + rs.getString("HealthState"));
                        System.out.println("Hospital: " + rs.getString("fk_hospital_id"));
                        System.out.println("Unit: " + rs.getString("fk_UnitType_id"));
                        System.out.println("_____");
                        i++;
                    }
                    System.out.println();
                    break;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Will print the whole required table
     * tables available: (Unit, Patient, Hospital)
     * @param tableName String
     */
    public void printTbl(String tableName) {
        printTbl(tableName, getQueryResultSet(tableName));
    }

    /**
     * Print the entire table returned by the SQL query
     * @param tableName String
     * @param query String
     */
    public void printTbl(String tableName, String query) {
        printTbl(tableName, getQueryResultSet(query));
    }

    /**
     * Count the number of column of table
     * @param tableName String
     * @return columnNb
     */
    public int getColumnNb(String tableName) {
        int colNb = 0;
        String str = "'" + tableName + "'";

        try {
            ResultSet rs = getQueryResultSet("select count(*) from information_schema.columns where table_name =" +
                                             str);
            rs.next();
            colNb = rs.getInt(1);
            return colNb;

        } catch (Exception e) {
            System.out.println();
            e.printStackTrace();
        }
        return colNb;
    }

    /**
     * @param tableName
     * @return An arrayList of the rowNb-th row of a table
     */
    private ArrayList<Object> fillArray(String tableName, int rowNb) {
        ArrayList<Object> list = new ArrayList<>();

        for (int k = 1 ; k <= getColumnNb(tableName) ; k++ ) {
            list.add(getValueFrom(tableName, k, rowNb));
        }

        return list;
    }

    /**
     * Initialize the connection to the MySQL Server always before trying to retrieve data from the dataBase
     * @return connection -- Connection (Object of type "Connection" on wich you must call the "createStatement()" method to enable the use
     *         of query (e.g. : Statement stmt = initConnection().createStatement(); will allow you to use the method "executeQuery(query)")
     *         to search for things "manually"
     */
    public Connection initConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/lauzhack2020?useSSL=false", "NoahMunz", "CL9Y8dPsedoPT");
            // Statement stmt = connection.createStatement();
            return connection;
        } catch (Exception e) {
            System.out.println("\n" + e + "\n");
        }
        return connection;
    }


    /****************************           ************************************/

    /**
     * Will return the data of an entire column/field for the specified table, as an ArrayList of Object
     * (e.g. getColumnFrom("Unit", "capacity") will return an ArrayList of the capacity of all unit in the database)
     * @param tableName String
     * @param columnName String
     * @param rs ResultSet
     * @return list
     */
    private ArrayList<Object> getColumnFrom(String tableName, String columnName, ResultSet rs) {
        ArrayList<Object> list = new ArrayList<>();

        try {
            int j = 1;
            while (rs.next()) {
                list.add(getValueFrom(tableName, columnName, j));
                j++;
            }
            return list;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    /**
     * Will return the data of an entire column/field for the specified table, as an ArrayList of Object
     * (e.g. getColumnFrom("Unit", "capacity") will return an ArrayList of the capacity of all unit in the database)
     * @param tableName String
     * @param columnName String
     * @return list
     */
    public ArrayList<Object> getColumnFrom(String tableName, String columnName) {
        return getColumnFrom(tableName, columnName, getResultSet(tableName));
    }

    /**
     * Will return the data of an entire column/field for the specified query, as an ArrayList of Object
     * (e.g. getColumnFrom("Unit", "capacity") will return an ArrayList of the capacity of all unit in the database)
     * @param tableName String
     * @param columnName String
     * @param query String
     * @return list
     */
    public ArrayList<Object> getColumnFrom(String tableName, String columnName, String query) {
        return getColumnFrom(tableName, columnName, getQueryResultSet(query));
    }

}
