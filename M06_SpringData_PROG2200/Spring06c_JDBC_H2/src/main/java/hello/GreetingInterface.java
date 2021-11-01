package hello;

import java.util.List;
import java.util.Optional;

/**
 * This interface can be used upon any class you wish to
 * act as the DB controller.  Implement with different DB
 * products or whatever.
 *
 * Nasty SQL statements are encapsulated inside the implementations
 * of these methods.
 */
public interface GreetingInterface {

    int count();

    int save(Greeting greeting);

    int update(Greeting greeting);

    int deleteById(Long id);

    List<Greeting> findAll();

//        List<CarModel> findByModel(String model, Long id);
//
//        Optional<CarModel> findById(Long id);

    String getNameById(Long id);

}
