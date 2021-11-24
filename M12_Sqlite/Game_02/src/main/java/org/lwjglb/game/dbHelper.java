package org.lwjglb.game;

import org.hsqldb.lib.List;
import org.lwjglb.engine.items.Vehicle02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
import java.util.ArrayList;

public class dbHelper {



//import org.hsqldb.jdbc.jdbcDataSource;


    /**
     * Title: Testdb Description: simple hello world db example of a standalone
     * persistent db application
     *
     * every time it runs it adds four more rows to sample_table it does a query and
     * prints the results to standard out
     *
     * Author: Karl Meissner karl@meissnersd.com
     */


        Connection conn;                                                //our connnection to the db - presist for life of program

        // we dont want this garbage collected until we are done
        public dbHelper(String db_file_name_prefix) throws Exception {    // note more general exception
            conn = DriverManager.getConnection("jdbc:hsqldb:" + db_file_name_prefix, "sa", "");   //new way
        }


        public void shutdown() throws SQLException {

            Statement st = conn.createStatement();

            // db writes out to files and performs clean shuts down
            // otherwise there will be an unclean shutdown
            // when program ends
            st.execute("SHUTDOWN");
            conn.close();    // if there are no other open connection
        }

        //use for SQL command SELECT
        public synchronized void query(String expression) throws SQLException {

            Statement st = null;
            ResultSet rs = null;

            st = conn.createStatement();         // statement objects can be reused with

            // repeated calls to execute but we
            // choose to make a new one each time
            rs = st.executeQuery(expression);    // run the query

            // do something with the result set.
            findAll(rs);
            dump(rs);

            st.close();    // NOTE!! if you close a statement the associated ResultSet is

            // closed too
            // so you should copy the contents to some other object.
            // the result set is invalidated also  if you recycle an Statement
            // and try to execute some other query before the result set has been
            // completely examined.
        }

        //use for SQL commands CREATE, DROP, INSERT and UPDATE
        public synchronized void update(String expression) throws SQLException {

            Statement st = null;

            st = conn.createStatement();    // statements

            try {
                int i = st.executeUpdate(expression);  // run the query
                System.out.println("executeUpdate : " + i);
            } catch (SQLException sQLException) {
                System.out.println("db error : " + expression);
            }

            st.close();
        }    // void update()


        public static void dump(ResultSet rs) throws SQLException {

            // the order of the rows in a cursor
            // are implementation dependent unless you use the SQL ORDER statement
            ResultSetMetaData meta = rs.getMetaData();
            int colmax = meta.getColumnCount();
            int i;

            System.out.println("Col 1 table name =>" + meta.getTableName(1) + "    col count " + meta.getColumnCount());

            // the result set is a cursor into the data.  You can only
            // point to one row at a time
            // assume we are pointing to BEFORE the first row
            // rs.next() points to next row and returns true
            // or false if there is no next row, which breaks the loop
            for (; rs.next();) {
                System.out.println("  metadata => " + rs.getMetaData().toString());
                for (i = 0; i < colmax; ++i) {

                    System.out.println("      getColumnLabel => " + rs.getMetaData().getColumnLabel(i + 1)
                            + " getColumnName=> " + rs.getMetaData().getColumnName(i + 1)
                            + " getColumnTypeName=> " + rs.getMetaData().getColumnTypeName(i + 1)
                            + " getColumnDisplaySize=> " + rs.getMetaData().getColumnDisplaySize(i + 1));
                }

                System.out.println(" ======================================== ");

                //void dump( ResultSet rs )
                // Headers
                for (i = 0; i < colmax; ++i) {
                    System.out.print(rs.getMetaData().getColumnLabel(i + 1)
                            + "   " + rs.getMetaData().getColumnTypeName(i + 1)
                            + "(" + rs.getMetaData().getColumnDisplaySize(i + 1)
                            + ")        ");
                }

                System.out.println(" ");

                // Data
                for (; rs.next();) {

                    System.out.print("        " + rs.getInt(1)
                            + "        " + rs.getString(2)
                            + "        " + rs.getInt(3));

//                for (i = 0; i < colmax; ++i) {
//                    // unpack and print an Integer
//                    if (rs.getMetaData().getColumnTypeName(i + 1).equalsIgnoreCase("Integer")) {
//                        int d = rs.getInt(i + 1);
//                        System.out.print("        " + d + "          ");
//                    }
//                    // unpack and print a Varchar
//                    if (rs.getMetaData().getColumnTypeName(i + 1).equalsIgnoreCase("Varchar")) {
//                        String d = rs.getString(i + 1);
//                        System.out.print("          " + d + "        ");
//                    }
//                }

                    System.out.println(" ");

                }
                System.out.println(" ======================================== ");

            }
        }

    public static void findAll(ResultSet rs) throws SQLException {
        System.out.println("findAll(rs)");


        for (; rs.next(); ) {
//            System.out.println("aaaaaaaaaa");
            System.out.print("        " + rs.getInt(1)
                    + "        " + rs.getString(2)
                    + "        " + rs.getInt(3));

        }
    }

    public static void displayAllRows() throws SQLException {

            String sql = "SELECT * FROM sample_table";
    }

        public static void writeAll(){
            System.out.println("writeAll()");
        }

        public static void wipeAll(){
            System.out.println("wipeAll()");
        }


        public static void main(String[] args) {

            dbHelper db = null;

            try {
                db = new dbHelper("db_file");
            } catch (Exception ex1) {
                ex1.printStackTrace();    // could not start db

                return;                   // bye bye
            }

            try {

                //make an empty table
                //
                // by declaring the id column IDENTITY, the db will automatically
                // generate unique values for new rows- useful for row keys
                db.update(
                        "CREATE TABLE sample_table ( id INTEGER IDENTITY, str_col VARCHAR(256), num_col INTEGER)");
            } catch (SQLException ex2) {
                //ignore
                //ex2.printStackTrace();  // second time we run program
                //  should throw execption since table
                // already there
                //
                // this will have no effect on the db
            }

            try {

                // add some rows - will create duplicates if run more then once
                // the id column is automatically generated
                db.update(
                        "INSERT INTO sample_table(str_col,num_col) VALUES('Ford', 100)");
                db.update(
                        "INSERT INTO sample_table(str_col,num_col) VALUES('Toyota', 200)");
                db.update(
                        "INSERT INTO sample_table(str_col,num_col) VALUES('Honda', 300)");
                db.update(
                        "INSERT INTO sample_table(str_col,num_col) VALUES('GM', 400)");

                // do a query
                db.query("SELECT * FROM sample_table WHERE num_col < 450");

                // at end of program
                db.shutdown();
            } catch (SQLException ex3) {
                ex3.printStackTrace();
            }
        }    // main()
    }
