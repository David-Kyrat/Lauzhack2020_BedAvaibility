package lauzhack2020;

import lauzhack2020.main.Enums.Canton;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.ArrayList;

import static java.lang.String.valueOf;

public class SqlLoader {

    private Connection conn;
    private Statement statement;
    private static final String JDBC_DRIVER_ = "com.mysql.jdbc.Driver";
    private static final String DB_URL_CONNECTION = "jdbc:mysql://localhost:3306/lauzhack2020";
    private static String USE_SSL = "?useSSL=false";
    private static final String USER = "NoahMunz";

    public static void main(String[] args) throws NullPointerException, SQLException {

        SqlLoader dB = null;

        try {
            dB = new SqlLoader();
            ResultSet rs = dB.getRS("Patient");
            rs.next();
            ArrayList<String> fo = dB.convertToTblValue("CHUV", "Rue de Bugnon", "Vaud", "Lausanne", null);

           /* String values =
                    foo.get(0) + ", " + foo.get(1) + ", " + foo.get(2) + ", " + valueOf(1011) + ", " + "Vaud" + ", " + foo.get(3) + ", " +
                    valueOf(1554) + ", " + valueOf(233);*/

            ArrayList<String> foo = dB.convertToTblValue("hospital_id", "StName", "canton", "Town", null);
            /*String values =
                    foo.get(0) + ", " + foo.get(1) + ", " + foo.get(2) + ", " + valueOf(npa) + ", " + canton + ", " + foo.get(3) + ", " +
                    valueOf(Capacity) + ", " + valueOf(FreeBeds);*/

            //System.out.println(values);

            dB.addToTblHospital("hospital_id", "StName", 2, "'vd", "Town", 2,
                                2);

        } catch (Exception se) {
            //Handle errors for JDBC / connection, and SQL syntax
            se.printStackTrace();
            //Handle errors for Class.forName (registration JDBC)
        } finally {
            //finally dispose of the resources by closing connection directly before it's done automatically
            try {
                if (!dB.getStatement().isClosed() || dB.getStatement() != null) {
                    dB.getConnection().close();
                }
            } catch (SQLException e) {}

            try {
                if (!dB.getConnection().isClosed() || dB.getConnection() != null) {
                    dB.getConnection().close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } //end Finally Try
            System.out.println("\nSuccessfully disconnected from database... ");
        }

    }

    /**
     * Constructor of SQLLoader, initialize/create a connection to the MySQL Server.
     * <br> Always do before trying to retrieve data from the dataBase. <br/>
     * Connection stored in field Connection conn on which must call "createStatement()" method to start executing statements
     **/
    SqlLoader() throws SQLException, ClassNotFoundException {
        //STEP 1: Register JDBC driver
        Class.forName(JDBC_DRIVER_);

        //STEP 2: Open a connection
        System.out.println("___________________________________\nConnecting to selected database...");
        this.conn = DriverManager.getConnection(DB_URL_CONNECTION + USE_SSL, USER, "CL9Y8dPsedoPT");
        assert conn != null;//Throws assertion exception if null ( better than NPE because more precise we know that it comes from here)
        System.out.println("Connected database successfully !");

        //STEP 3: Create Statement
        this.statement = conn.createStatement();
        System.out.println("Don't forget to rs.next()\n__________________________________\n");
    }

    /**
     * Add the " <b>' '</b> " required to put a string in table cell for each string given in parameter, to an arrayList
     * @return correctList
     */
    @NotNull
    private ArrayList<String> convertToTblValue(String s1, String s2, String s3, String s4, String s5) {
        ArrayList<String> list = new ArrayList<>();
        ArrayList<String> list1 = new ArrayList<>();
        list.add(s1);
        list.add(s2);
        list.add(s3);
        list.add(s4);
        list.add(s5);
        String s = "'";

        for (int i = 0 ; i < 5 ; i++) {
            final String str = s + list.get(i) + s;
            list1.add(i, str);
        }
        return list1;
    }

    /**
     * Add a Hospital to the Hospital table (add a new row)
     * @param hospital_id String
     * @param StName String
     * @param npa int
     * @param canton Canton
     * @param Town String
     * @param Capacity int
     * @param FreeBeds int
     */
    public void addToTblHospital(String hospital_id, String StName, int npa, String canton, String Town, int Capacity,
                                 int FreeBeds) throws SQLException {
        ArrayList<String> foo = convertToTblValue(hospital_id, StName, canton, Town, null);
        String values =
                foo.get(0) + ", " + foo.get(1) + ", " + foo.get(2) + ", " + valueOf(npa) + ", " + canton + ", " + foo.get(3) + ", " +
                valueOf(Capacity) + ", " + valueOf(FreeBeds);
        addInTable("hospital", values);
    }

    /**
     * Execute an update statement like INSERT, UPDATE or DELETE or an sql stmt that returns nothing <br/><br/>
     * (Here) Add a row in table
     * @param tableName String
     * @param values String (values for each Column)
     * @throws SQLException
     */
    public void addInTable(String tableName, String values) throws SQLException {
        String stmt = "INSERT INTO  " + tableName
                      + " VALUES (" + values + ")";

        statement.executeUpdate(stmt);
    }

    /**
     * Get the result Set associated with the query (here always return a full table)
     * Method to use to choose from which table in the DB you want to retrieve data from
     * @param tableName String : Name of the table to retrieve the data from (e.g. hospital, unit or patient)
     * @return resultSet -- ResultSet : data from Table asked (further action are required to print the values from this resultSet)
     */
    public ResultSet getRS(String tableName) {
        ResultSet rs = null;
        try {
            rs = statement.executeQuery("select * from " + tableName);
            return rs;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    //TODO: DOES NOT WORK THROWS NPE AND SQLEXCEPTION => PBLM SYNTAX (tested with print table)

    /**
     * Method to use to make personalized SQL Query other than just "select * from table"
     * @param query String SQL query to make
     * @return resultSet -- ResultSet the tbl associated to the query
     */
    public ResultSet getQueryRS(String query) {
        ResultSet rs = null;
        try {
            System.out.println(statement != null);
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            return rs;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    /**
     * Generalised version "getAnyValueType()" method, => search for any value no matter the row.
     * (For all ResultSet)<br>
     * <u>Warning</u>: does not reset the pointer on the Result Set (rs.next())
     * @param column Object
     * @param row int
     * @param rs ResultSet
     * @return CellValue
     *
     * @throws SQLException
     */
    private Object getCellValueFromRS(Object column, int row, ResultSet rs) throws SQLException {
        for (int i = 1 ; i <= row ; i++) {
            rs.next();
        }
        return getAnyValueType(rs, column);
    }

    /**
     * Generalised version "getAnyValueType()" method, => Search for any value no matter the row.<br/><br/>
     * Only for whole table (same as getCellValueFromRS() but with the resultSet equal to a whole table.)<br/>
     * E.g. : getCellValue(Hospital, Capacity, 1) will return the capacity of the 1st hospital in the DB
     * @param tableName String
     * @param column Object
     * @param row int
     * @return value ((String Object) or ((Integer Object)
     */
    public Object getCellValue(String tableName, Object column, int row) throws SQLException {
        return getCellValueFromRS(column, row, getRS(tableName));
    }

    /**
     * Return the given Table as bi-dimensional ArrayList of Objects for any RS
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
     * Return the given Table as bi-dimensional ArrayList of Objects, only for whole tables
     * @param tableName String
     * @return table
     */
    public ArrayList<ArrayList<Object>> getTblAsList(String tableName) {
        return getTblAsList(tableName, getRS(tableName));
    }

    /**
     * <b>Used to get any value of a cell from 1st Row of any ResultSet</b> <br/>
     * Use the right getString() method, depending on if the Object in paramater is indicating the column by it's name (String) or by its
     * position (colNumber - int) <br><br>
     * (NB: Intellij may say casting Integer or String on returned element is useless but it isn't at all. Still need to cast to assign the
     * returned value to an Integer or String though)
     * @param rs ResultSet
     * @param column Object
     * @return cell of resultSet (Object casted as String or Integer)
     */
    private Object getAnyValueType(ResultSet rs, Object column) throws SQLException {
        String cellValue;

        assert (column instanceof String || column instanceof Integer);

        if (column instanceof String) cellValue = rs.getString((String) column);
        else cellValue = rs.getString((Integer) column);

        try {
            Integer intCellValue = Integer.parseInt(cellValue);
            return intCellValue;

        } catch (NumberFormatException nfe) {
            return cellValue;
        }
    }

    /**
     * @param tableName
     * @return An arrayList of the rowNb-th row of a table
     */
    @NotNull
    private ArrayList<Object> fillArray(String tableName, int rowNb) throws SQLException {
        ArrayList<Object> list = new ArrayList<>();

        for (int k = 1 ; k <= getColumnNb(tableName) ; k++) {
            list.add(getCellValue(tableName, k, rowNb));
        }

        return list;
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
            ResultSet rs = getQueryRS("select count(*) from information_schema.columns where table_name =" +
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

    public Connection getConnection() {
        return conn;
    }

    public Statement getStatement() {
        return statement;
    }


    /*************************************                 ********************************/

    /**
     * Used in other method to print the whole required table
     * tables available: (Unit, Patient, Hospital)
     * @param tableName String
     */
    private void printTbl(String tableName) {
        ResultSet rs = getRS(tableName);
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

}