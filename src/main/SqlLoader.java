package main;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.*;
import java.util.ArrayList;

import static java.lang.String.valueOf;

public class SqlLoader {

    private static final String JDBC_DRIVER_ = "com.mysql.jdbc.Driver";
    private static final String DB_URL_CONNECTION = "jdbc:mysql://localhost:3306/lauzhack2020";
    private static String USE_SSL = "?useSSL=false";
    private static final String USER = "NoahMunz";
    public static final String SELECT_ALL_FROM = "select * from ";
    private Connection conn;
    private Statement currentStatement;

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        SqlLoader dB = null;

        //TODO: CREATE A NEW STATEMENT EACH TIME UPON EACH METHOD CALL, AND CLOSE IT AFTERWARDS in the finally block (already done, was
        // just here to remember how it works)
        // 2 Types of method : the privates ones that are just here as tools in other method ( used to  generify and modulify)
        // The methods that use those tools are the "final ones" they are public and more importantly => They have
        // the try catch block, that try to execute the currentStatement/query and that **closes it after code execution**
        // A new Statement must be used for each "final method" execution and each of those statements must be closed at the end.
        // At then end of the main the Try catch is just here to close the connection afterward.
        try {
            dB = new SqlLoader();




        }  //Handle errors for JDBC / classForName, and SQL syntax
        catch (Exception e) { e.printStackTrace(); }

        finally {
            try {
                if (dB.getCurrentStmt() != null) { dB.getCurrentStmt().close(); }
            }
            catch (SQLException e) { e.printStackTrace(); }

            //Close the connection at then end of all method calls
            try {
                if (dB.getConnection() != null) { dB.getConnection().close(); }
            }
            catch (SQLException e) { e.printStackTrace(); } //end Finally Try

            System.out.println("\nSuccessfully disconnected from database... ");
        }

    }

    /**
     * Constructor of SQLLoader, initialize/create a connection to the MySQL Server.
     * <br> Always do before trying to retrieve data from the dataBase. <br/>
     * Connection stored in field Connection conn on which must call "createStatement()" method to start executing statements
     **/
    public SqlLoader() throws SQLException, ClassNotFoundException {
        //STEP 1: Register JDBC driver
        Class.forName(JDBC_DRIVER_);

        //STEP 2: Open a connection
        System.out.println("___________________________________\nConnecting to selected database...");
        this.conn = DriverManager.getConnection(DB_URL_CONNECTION + USE_SSL, USER, "CL9Y8dPsedoPT");
        assert conn != null;//Throws assertion exception if null ( better than NPE because more precise we know that it comes from here)
        System.out.println("Connected database successfully !");

        System.out.println("Don't forget to rs.next()\n__________________________________\n");
    }

    /**
     * Generalized version of getQueryRS where the query will always be "return this whole table" (where "this" is the parameter value
     * tableName)
     * @param tableName String : Name of the table to retrieve the data from (e.g. hospital, unit or patient)
     * @return resultSet -- ResultSet : data from Table asked (further action are required to print the values from this resultSet)
     */
    public ResultSet getRS(String tableName) {
        ResultSet rs = null;
        try {
            return getQueryRS(SELECT_ALL_FROM + tableName);
        }
        catch (Exception e) {
            e.printStackTrace();
            return rs;
        }
    }

    /**
     * Check if the field "currentStatement" is closed before assigning to a new Statement, then
     * get the result Set associated with the query.<br/> ( ResultSet = the returned data from a query displayed as a table  ) <br/>
     * Method to use to choose from which table in the DB you want to retrieve data from
     * @param query String SQL query to make
     * @return resultSet the tbl associated to the query
     */
    public ResultSet getQueryRS(String query) {
        ResultSet rs = null;
        try {
            createStmt();
            rs = currentStatement.executeQuery(query);
            return rs;

        }
        catch (Exception e) {
            e.printStackTrace();
            return rs;
        }
    }

    /**
     * Return the given ResultSet as bi-dimensional ArrayList of Objects for any query
     * @param tableName String
     * @param query String
     * @return table
     */
    private ArrayList<ArrayList<Object>> getRSAsList(String tableName, String query) {
        ArrayList<ArrayList<Object>> table = new ArrayList<ArrayList<Object>>();
        int i = 1;

        try {
            ResultSet rs = getQueryRS(query);
            while (rs.next()) {
                table.add(fillArray(tableName, i, rs));
                i++;
            }
            return table;
        }

        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            closeStmt();
        }

        return table;
    }

    /**
     * Return the given Table as bi-dimensional ArrayList of Objects, only for whole tables
     * @param tableName String
     * @return table
     */
    public ArrayList<ArrayList<Object>> getTblAsList(String tableName) {
        return getRSAsList(tableName, SELECT_ALL_FROM + tableName);
    }

    /**
     * Generalised version "getAnyValueType()" method => Search for any value no matter the row,<br/>
     * (but only for whole table, like "hospital" or "patient". For specific query use getCellValueFromRS() )
     * .<br/><br/>
     * E.g. : getCellValue(Hospital, Capacity, 1) will return the capacity of the 1st hospital in the DB
     * @param tableName String
     * @param column Object
     * @param row int
     * @return value ((String Object) or ((Integer Object)
     */
    public Object getCellValue(String tableName, Object column, int row) {
        String query = SELECT_ALL_FROM + tableName;
        return getCellValueFromRS(query, column, row);
    }

    /**
     * Generalised version "getAnyValueType()" method, => search for any value of a resultSet no matter the row.
     * (For all ResultSet)<br>
     * <u>Warning</u>: does not reset the pointer on the Result Set (rs.next())
     * <br>Close the currentStatement
     * @param query String
     * @param column Object
     * @param row int
     * @return CellValue
     */
    private Object getCellValueFromRS(String query, Object column, int row) {
        try {
            final ResultSet rs = getQueryRS(query);
            for (int i = 1 ; i <= row ; i++) {
                rs.next();
            }
            return getAnyValueType(rs, column);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        finally {
            closeStmt();
        }
    }

    //TODO: FIX this method
    /**
     * Same as other getCellValueFromRS but here the parameter (String query) is replaced by (ResultSet rs)
     * Method used when the loader must fetch lots of values that come from the same ResultSet =>
     * Enable the possibility to just store that ResultSet and give it as parameter value later to prevent to much statement creation
     * @param rSet ResultSet
     * @param column Object
     * @param row int
     * @return the cell value
     */
    private Object getCellValueFromRS(ResultSet rSet, Object column, int row) {
        final ResultSet rs = rSet;
        try {
            for (int i = 1 ; i <= row ; i++) {
                rs.next();
            }
            return getAnyValueType(rs, column);
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
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

        }
        catch (NumberFormatException nfe) {
            return cellValue;
        }
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
                                 int FreeBeds) {
        try {
            ArrayList<String> foo = convertToTblValue(hospital_id, StName, canton, Town, null);
            String values =
                    foo.get(0) + ", " + foo.get(1) + ", " + foo.get(2) + ", " + valueOf(npa) + ", " + canton + ", " + foo.get(3) + ", " +
                    valueOf(Capacity) + ", " + valueOf(FreeBeds);

            addInTable("hospital", values);
        }
        catch (Exception e) { e.printStackTrace(); }
        finally {
            closeStmt();
        }
    }

    /**
     * Execute an update currentStatement like INSERT, UPDATE or DELETE or an sql currentStatement that returns nothing <br/><br/>
     * (Here) Add a row in table
     * @param tableName String
     * @param values String (values for each Column)
     * @throws SQLException ex
     * @throws ClassNotFoundException ex
     */
    public void addInTable(String tableName, String values) throws SQLException, ClassNotFoundException {
        String stmt = "INSERT INTO  " + tableName
                      + " VALUES (" + values + ")";

        currentStatement.executeUpdate(stmt);

    }

    /**
     * Try Catch block closing the currentStatement
     */
    public void closeStmt() {
        try {
            if (currentStatement != null && !currentStatement.isClosed()) {
                currentStatement.close();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create a Statement upon which to call the executeQuery() method to get a ResultSet
     * If an already existing Statement object is used => result into a bug saying that the currentStatement is already closed
     */
    public void createStmt() throws ClassNotFoundException, SQLException {
        try {
            this.currentStatement = this.conn.createStatement();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Used in the filling of ArrayList of ArrayList => Stores the ResultSet associated to parameter value tableName, to prevent multiple
     * creations of statement when it's not necessary
     * @param tableName String
     * @param rowNb int
     * @return An arrayList of the rowNb-th row of a table
     */
    private ArrayList<Object> fillArray(String tableName, int rowNb, ResultSet rs) throws SQLException {
        ArrayList<Object> list = new ArrayList<>();
        final int colNb = getColumnNb(tableName);
        String query = SELECT_ALL_FROM + tableName;

        for (int k = 1 ; k < colNb + 1 ; k++) {
            list.add(getCellValue(tableName, k, rowNb));
        }

        return list;
    }

    /**
     * Add the " <b>' '</b> " required to put a string in table cell for each string given in parameter, to an arrayList
     * close Stmt
     * @return correctList
     */
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
     * Count the number of column of table
     * @param tableName String
     * @return columnNb
     */
    public int getColumnNb(String tableName) {
        int colNb = 0;
        String input = "'" + tableName + "'";

        try {
            ResultSet rs = getQueryRS("select count(*) from information_schema.columns where table_name =" + input);
            rs.next();
            colNb = rs.getInt(1);
            return colNb;

        }
        catch (Exception e) {
            System.out.println();
            e.printStackTrace();
        }
        return colNb;
    }

    private Statement getCurrentStmt() {
        return currentStatement;
    }

    public Connection getConnection() {
        return conn;
    }


    /*************************************                 ********************************/

    /**
     * Print the whole required Table
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

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}