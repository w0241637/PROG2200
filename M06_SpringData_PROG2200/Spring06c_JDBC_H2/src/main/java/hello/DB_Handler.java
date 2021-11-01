package hello;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Since this class implements the interface, it
 * can be used as a data repository by Spring.
 */
@Repository
public class DB_Handler implements CarModel_Interface {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    // local copy of jdbcTemplate
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public int count() {
        return 0;
    }

    @Override
    public int save(CarModel carModel) {
//        String command = "INSERT INTO GreetingTable(str_col,num_col,num_col2) VALUES('" + carModel.getModelName() + "', " + carModel.getModelNumber() + ")";
        String command = "INSERT INTO GreetingTable(str_col,num_col,num_col2) VALUES('" + carModel.getWinLose() + "', " + carModel.getNumToGuess()+ ',' + carModel.getNumOfGuesses() +")";

        log.info("################ DB_Handler command=" + command);

        jdbcTemplate.batchUpdate(command);

        // debug output to see what we're doing
        dump();

        return 0;
    }

    @Override
    public int update(CarModel carModel) {
        return 0;
    }

    @Override
    public int deleteById(Long id) {
        return 0;
    }

    @Override
    public List<CarModel> findAll() {

        List<CarModel> carModels = new ArrayList<CarModel>();

        log.info("Querying for all carModels:");
        jdbcTemplate.query(
                "SELECT id, str_col, num_col, num_col2 FROM GreetingTable",
                (rs, rowNum) -> new CarModel(rs.getLong("id"), rs.getString("str_col"), rs.getInt("num_col"), rs.getInt("num_col2"))
        ).forEach(CarModel -> carModels.add(CarModel));

        return carModels;
    }

    @Override
    public String getNameById(Long id) {
        return null;
    }

    // Dump the DB as a test
    private void dump(){

        // get all rows with query; lambda is run for each returned row
        // https://docs.spring.io/spring/docs/3.0.x/spring-framework-reference/html/jdbc.html
        log.info("Querying for customer records where num_col > 0:");
        jdbcTemplate.query(
                "SELECT id, str_col, num_col, num_col2 FROM GreetingTable",
                (rs, rowNum) -> new CarModel(rs.getLong("id"), rs.getString("str_col"), rs.getInt("num_col"), rs.getInt("num_col2"))
        ).forEach(CarModel -> log.info(CarModel.toString()));

    }
}
