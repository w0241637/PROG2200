package hello;

/**
 *  This class sets up the data used, a car model has ID, name, and number
 *
 *  Note the use of constructors and getters/setters.  Spring needs to
 *  auto-find these methods to operate on the class's data fields.
 */
public class CarModel {

    private long id;
    private String modelName;
    private Integer modelNumber;
    private Integer num2;


    public CarModel() {
        this.setId(0);
        this.setWinLose("default modelName");
        this.setNumToGuess(0);
        this.setnumOfGuesses(0);
    }

    public CarModel(long id, String modelName, Integer modelNumber, Integer num2) {
        this.setId(id);
        this.setWinLose(modelName);
        this.setNumToGuess(modelNumber);
        this.setnumOfGuesses(num2);
    }

    @Override
    public String toString() {
        return "CarModel{" +
                "id=" + getId() +
                ", modelName='" + getWinLose() + '\'' +
                ", modelNumber='" + getNumToGuess() + '\'' +
                ", num2=" + getNumOfGuesses();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getWinLose() {
        return modelName;
    }

    public void setWinLose(String modelName) {
        this.modelName = modelName;
    }

    public Integer getNumToGuess() {
        return modelNumber;
    }

    public void setNumToGuess(Integer modelNumber) {
        this.modelNumber = modelNumber;
    }

    public Integer getNumOfGuesses() {
        return num2;
    }

    public void setnumOfGuesses(Integer num2) {
        this.num2 = num2;
    }
// getters & setters omitted for brevity

}

