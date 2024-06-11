    
/**
 * EddieEnvironment
 * 
 * Stores information about a generic environment; including temperature and the plant growth rate. Methods entail getters, setters, and toString.
 * 
 * Subclass of EddieAbiotic.
 *
 * @author (Eddie Gao)
 * @version (19/01/2023)
 */
public class EddieEnvironment extends EddieAbiotic
{
    // Step 1. Declare and initialise instance variables
    // SOME VARIABLES ARE DECLARED NOT AS CONSTANT AS THEY ARE SUBJECT TO CHANGE. ALL VARIABLES SHOULD NOT BE CHANGED UNLESS GIVEN CONFIRMATION
    // FROM THE USER/ADMINISTRATOR -- THUS, THEY ARE private and ENCAPSULATED.
    private float temperature; // float data type for efficiency as well as accuracy to temperatures to decimal points.
    private byte plantGrowthRate; // byte as plant growth rate will never exceed 10; efficient.
    
    // CONSTANTS
    public static final byte MAX_PLANT_GROWTH = 10; // The growth rate cannot exceed 10.
    
    // GETTERS
    public float getTemperature() { return this.temperature; } // returns temperature
    public byte getPlantGrowth() { return this.plantGrowthRate; } // returns plant growth rate as a natural number
    public float getPlantGrowthRate() { return (float) (this.plantGrowthRate) / MAX_PLANT_GROWTH; } // returns plant growth rate as a float.
    
    // SETTERS
    /**
     * Method setPlantGrowth
     * 
     * Sets plantGrowthRate to a desired value.
     *
     * @param pgr - int - the desired plant growth rate.
     */
    public void setPlantGrowth(int pgr) // passed in as int and casted as byte for user convenience + efficiency.
    {
        this.plantGrowthRate = (byte) pgr;
    }
    
    /**
     * EddieEnvironment Constructor
     * 
     * Default constructor that sets all instance variables to a default logical value whilst priorly calling the super() default constructor.
     * Superclass being EddieAbiotic.
     *
     */
    public EddieEnvironment()
    {
        // Call the default constructor of the superclass (EddieAbiotic)
        super();

        this.temperature = 24.0f; // By default, the environment is of room temperature (approximately).
        this.plantGrowthRate = 3; // By default, plants have a growth rate of 3.
    }
    
    /**
     * EddieEnvironment Constructor
     * 
     * Overloaded constructor that sets all instance variables to a desired value.
     *
     * @param name - String - name of the environment
     * @param e - int (see line 36 for reasoning of data type) - desired energy impact value
     * @param temp - double - (see line 36 for reasoning of data type) - desired temperature of the environment (in celsius)
     * @param pgr - int - desierd plant growth rate value.
     */
    public EddieEnvironment(String name, int e, double temp, int pgr)
    {
        // Call the overloaded EddieAbiotic constructor.
        super(name, e);

        // Set the temperature to the passed in temp as a float for efficiency.
        this.temperature = (float) temp;
        this.plantGrowthRate = (byte) Math.min(pgr, MAX_PLANT_GROWTH); // Set the plant growth rate to the parameter, if pgr > MAX_PLANT_GROWTH, cap it to the max.
    }
    
    /**
     * Method toString
     * 
     * returns String representation of EddieEnvironment object.
     *
     * @return String
     */
    @Override
    public String toString()
    {
        return super.toString() + " :: Temperature (C): " + temperature + " :: Plant Growth Rate - " + plantGrowthRate;
    }
    
    
    
}
