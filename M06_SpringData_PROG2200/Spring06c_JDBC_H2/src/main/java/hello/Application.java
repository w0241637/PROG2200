package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;

@SpringBootApplication
public class Application implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String args[]) {
        SpringApplication.run(Application.class, args);
    }

    // Auto-reads the Datasource entries from the application.properties
    // file and prepares JDBC.
    @Autowired
    JdbcTemplate jdbcTemplate;

    // Use autowired ... Spring creates one DB_Handler object and use that
    // for DB access.  DB class needs an interface so that
    // Spring can use that interface fot setup.  carModel
    // is declared as that interface type.
    @Autowired
    @Qualifier("DB_Handler")  // Use this class, which in turn uses CarModel_Interface
    private CarModel_Interface carModel;


    @Override
    public void run(String... strings) throws Exception {

        log.info("StartApplication...");

        // Create table and add test data.
        // Eventually comment this out...but ensure tables are created
        runJDBC();

    }

    // Add stuff into tables for testing
    void runJDBC() {

        log.info("Creating tables");

        // Check "application.properties" file to configure persistent H2 DB file
//        jdbcTemplate.execute("DROP TABLE GreetingTable IF EXISTS");
//        jdbcTemplate.execute("CREATE TABLE GreetingTable(" +
//                " id SERIAL, str_col VARCHAR(256), num_col INTEGER)");

//        // Check "application.properties" file to configure persistent H2 DB file
        jdbcTemplate.execute("DROP TABLE GreetingTable IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE GreetingTable(" +
        " id SERIAL, str_col VARCHAR(256), num_col INTEGER, num_col2 INTEGER )");

//        jdbcTemplate.batchUpdate("INSERT INTO GreetingTable(str_col,num_col, num_col2) VALUES('Ford', 100, 9)");
//        jdbcTemplate.batchUpdate("INSERT INTO GreetingTable(str_col,num_col, num_col2) VALUES('Toyota', 200, 8)");
//        jdbcTemplate.batchUpdate("INSERT INTO GreetingTable(str_col,num_col, num_col2) VALUES('Honda', 300, 7)");
//        jdbcTemplate.batchUpdate("INSERT INTO GreetingTable(str_col,num_col, num_col2) VALUES('GM', 400, 6)");
//        jdbcTemplate.batchUpdate("INSERT INTO GreetingTable(num_col, num_col2, num_col3,str_col) VALUES(11,22,33,'test')");


        // get all rows with query; lambda is run for each returned row
        // https://docs.spring.io/spring/docs/3.0.x/spring-framework-reference/html/jdbc.html
        log.info("Querying for customer records where num_col > 0:");
        jdbcTemplate.query(
                "SELECT id, str_col, num_col, num_col2 FROM GreetingTable WHERE num_col < 250",
                (rs, rowNum) -> new CarModel(rs.getLong("id"), rs.getString("str_col"), rs.getInt("num_col"), rs.getInt("num_col2") )
        ).forEach(CarModel -> log.info(CarModel.toString()));


        // get all rows with query; lambda is run for each returned row
        jdbcTemplate.query(
                "SELECT id, str_col, num_col, num_col2 FROM GreetingTable WHERE num_col > 150",
                (rs, rowNum) -> new CarModel(rs.getLong("id"), rs.getString("str_col"), rs.getInt("num_col"), rs.getInt("num_col2") )
        ).forEach(CarModel -> log.info(CarModel.toString()));


        // Get row using id
//        long id = 1;
//        String sql = "SELECT * FROM GreetingTable WHERE ID = ?";
//        CarModel carModel_1  = jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) ->
//                new CarModel(
//                        rs.getLong("id"),
//                        rs.getString("str_col"),
//                        rs.getInt("num_col"),
//                        rs.getInt("num_col2")
//
//                ));
//        if (carModel_1!=null) {
//            log.info("carModel_1 => " + carModel_1.toString());
//        }


        // get all rows with query, save in ArrayList
        ArrayList<CarModel> carModelsList = new ArrayList<CarModel>();
        jdbcTemplate.query(
                "SELECT id, str_col, num_col, num_col2 FROM GreetingTable WHERE num_col > 150",
                (rs, rowNum) -> carModelsList.add(new CarModel(rs.getLong("id"), rs.getString("str_col"), rs.getInt("num_col"), rs.getInt("num_col2"))
                ));

        // now arrayList contains all query rows
        log.info("====================================");
        log.info("Dump ArrayList of matched carModels ");
        log.info("====================================");
        for(CarModel c : carModelsList)
        {
            log.info("ArrayList of carModels => " + c.toString());
        }

    }

}