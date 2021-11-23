package org.lwjglb.game;

import org.lwjglb.engine.items.Vehicle02;

import java.sql.*;

public class dbHelper02 {

    private static Connection conn;
//    Connection conn;

    public static synchronized void startDB() throws SQLException {
        try {
            System.out.println("startDB Good");
            conn = DriverManager.getConnection("jdbc:hsqldb:" + "testing01", "sa", "");   //new way
        }catch (SQLException e){
            System.out.println("startDB Error: "+e);
        }
    }

    public static void createTable() throws SQLException {
        Statement st = null;
        st = conn.createStatement();

//        String makeTable = "CREATE TABLE game_table01 ( id INTEGER IDENTITY, str_col VARCHAR(256), num_col INTEGER)";
        String makeTable2 = "CREATE TABLE game_table01 ( id INTEGER IDENTITY, type VARCHAR(256), posX FLOAT, posY FLOAT, posZ FLOAT, volX FLOAT, volY FLOAT, volZ FLOAT)";

        try {
            int i = st.executeUpdate(makeTable2);  // run the query
            System.out.println("executeUpdate : " + i);
        } catch (SQLException sQLException) {
            System.out.println("table exists, did not create table");
            System.out.println("did not create this because it exists already: " + makeTable2);
        }

        st.close();
    }

    public static void dropTable() throws SQLException {
        Statement st = null;
        st = conn.createStatement();

        String dropTable = "DROP TABLE game_table01";

        try {
            int i = st.executeUpdate(dropTable);  // run the query
            System.out.println("execute dropTable : " + i);
        } catch (SQLException sQLException) {
            System.out.println("table couldn't drop because it doesn't exists");
            System.out.println("didnt drop table error : " + dropTable);
        }

        st.close();
    }

    public static synchronized void RowTest() throws SQLException {
        Statement st = null;
        st = conn.createStatement();

//        String statement = "INSERT INTO game_table01(str_col, num_col) VALUES('Tim wuz here', 123)";
        String statement = "INSERT INTO game_table01(type, posX, posY, posZ, volX, volY, volZ) VALUES('Tim', 1.0, 2.0, 3.0, 4.0, 5.0, 6.0)";


        try {
            int i = st.executeUpdate(statement);  // run the query
            System.out.println("executeUpdate : " + i);
        } catch (SQLException sQLException) {
            System.out.println("INSERT didnt' work");
            System.out.println("INSERT db error : " + statement);
        }

        st.close();
    }

    public static synchronized void insertRow(String objType, float x, float y, float z, float dx, float dy, float dz) throws SQLException {
        Statement st = null;
        st = conn.createStatement();

        String statement = "INSERT INTO game_table01(type, posX, posY, posZ, volX, volY, volZ) VALUES("+"'"+objType+"'"+", "+x+", "+y+", "+z+", "+dx+", "+dy+", "+dz+")";

        try {
            int i = st.executeUpdate(statement);  // run the query //look at the statements TIM
//            st.executeUpdate(statement);
            System.out.println("executeUpdate : " + i);
        } catch (SQLException sQLException) {
            System.out.println("INSERT didnt' work");
            System.out.println("INSERT db error : " + statement);
        }

        st.close();
    }



    public static synchronized void update() throws SQLException {
        Statement st = null;
        st = conn.createStatement();

        String statement = "UPDATE game_table01 SET type = 'abc' WHERE id = 1";

        try {
            int i = st.executeUpdate(statement);  // run the query
            System.out.println("executeUpdate() : " + i);
        } catch (SQLException sQLException) {
            System.out.println("Update failed :(");
            System.out.println("db error : " + statement);
        }

        st.close();
    }


    public static synchronized void deleteAllRows() throws SQLException {
        Statement st = null;
        st = conn.createStatement();

//        String statement = "DELETE FROM game_table01 WHERE ID >= 0";
        String statement = "TRUNCATE TABLE game_table01";


        try {
            int i = st.executeUpdate(statement);  // run the query
            System.out.println("execute deleteAllRows() : " + i);
        } catch (SQLException sQLException) {
            System.out.println("deleteAllRows failed ");
            System.out.println("db error : " + statement);
        }

        st.close();
    }



    public static synchronized void createObjs() throws SQLException{
        Statement st = null;
        ResultSet rs = null;

        String statement = "SELECT * FROM game_table01";

        st = conn.createStatement();


        try {
//            int i = st.executeUpdate(statement);  // run the query
            rs = st.executeQuery(statement);
            System.out.println("execute getAllRows() : ");


            for (; rs.next(); ) {
                System.out.print("\t" + rs.getInt(1) //id
                        + "\t" + rs.getString(2) // type
                        + "\t" + rs.getFloat(3) //x
                        + "\t" + rs.getFloat(4) //y
                        + "\t" + rs.getFloat(5)//z
                        + "\t" + rs.getFloat(6)//dx
                        + "\t" + rs.getFloat(7)//dy
                        + "\t" + rs.getFloat(8)//dz
                        + "\n");

                DummyGame.addDbMeshOnScreen(
                        rs.getString(2),
                        rs.getFloat(3),
                        rs.getFloat(4),
                        rs.getFloat(5),
                        rs.getFloat(6),
                        rs.getFloat(7),
                        rs.getFloat(8));
            }

            System.out.println("rs: "+rs);


        } catch (SQLException sQLException) {
            System.out.println("createObjs  failed :(");
            System.out.println("rs: "+rs);
            System.out.println("createObjs() db error : " + statement);
        }

        st.close();
    }




    public static synchronized void printAll() throws SQLException{
        Statement st = null;
        ResultSet rs = null;

        String statement = "SELECT * FROM game_table01";

        st = conn.createStatement();


        try {
//            int i = st.executeUpdate(statement);  // run the query
            rs = st.executeQuery(statement);
            System.out.println("executePrintAll() : " + "testing");


            for (; rs.next(); ) {
                System.out.print("\t" + rs.getInt(1) //id
                        + "\t" + rs.getString(2) // type
                        + "\t" + rs.getFloat(3) //x
                        + "\t" + rs.getFloat(4) //y
                        + "\t" + rs.getFloat(5)//z
                        + "\t" + rs.getFloat(6)//dx
                        + "\t" + rs.getFloat(7)//dy
                        + "\t" + rs.getFloat(8)//dz
                        + "\n");
            }

            System.out.println("rs: "+rs);


        } catch (SQLException sQLException) {
            System.out.println("printAll failed :(");
            System.out.println("rs: "+rs);
            System.out.println("printAll() db error : " + statement);
        }

        st.close();
    }




//    public int save(Vehicle02 vehicle02) {
//        String command = "INSERT INTO CarModels(str_col,num_col) VALUES('" + vehicle02.getPosition() + "', " + vehicle02.getModelNumber() + ")";
//
//        log.info("################ DB_Handler command=" + command);
//
//
//        // debug output to see what we're doing
//
//        return 0;
//    }






}
