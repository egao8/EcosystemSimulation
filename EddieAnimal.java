// Import libraries
import java.util.ArrayList;
import java.util.Random;


/**
 * EddieAnimal
 * 
 * Provides the basic functionality of all animals, including information regarding sex, mobility, and weight. 
 * Methods pertain to:
 * - GETTERS
 * - SETTERS
 * - canEat()
 * - eat()
 * - canReproduce()
 * - reproduce() 
 * - deterioriate()
 * - increaseMobility()
 * - increaseEnergy()
 * 
 * Superclass to EddieCarnivore and EddieHerbivore, Subclass to EddieBiotic.
 * 
 *
 * @author (Eddie Gao)
 * @version (19/01/2023)
 */
public class EddieAnimal extends EddieBiotic
{
    // Step 1. Declare and initialise instance variables
    // SOME VARIABLES ARE DECLARED NOT AS CONSTANT AS THEY ARE SUBJECT TO CHANGE. ALL VARIABLES SHOULD NOT BE CHANGED UNLESS GIVEN CONFIRMATION
    // FROM THE USER/ADMINISTRATOR -- THUS, THEY ARE PROTECTED and ENCAPSULATED.
    protected char sex; // M or F
    protected byte mobility; // the mobility stat of the animal; this is used to depict how many cells the animal can move to in one simulation of a day.
    // because the max mobility is 10, use a byte value for efficiency.
    protected short weight; // a short in lbs because realistically, no documented animal is ~32,000 pounds or heavier (most efficient data type).
    
    // CONSTANTS
    public final static byte MAX_MOBILITY = 10, MIN_MOBILITY = 3;
    
    // GETTERS
    public char getSex() { return this.sex; } // return the sex
    public byte getMobility() { return this.mobility; } // return the mobility
    public short getWeight() { return this.weight; } // return the weight
      
    /**
     * EddieAnimal Constructor
     * 
     * Default constructor calls super() default constructor and sets instance vars. to logical default values.
     *
     */
    public EddieAnimal()
    {
        super();
        
        if (new Random().nextBoolean()) this.sex = 'F';
        else this.sex = 'M';
        
        this.mobility = 3;
        this.weight = 15;        
    }
    
    /**
     * EddieAnimal Constructor
     * 
     * Overloaded constructor that takes only two parameters, name and icon and sets them to the instance variables whilst setting all other instance variables to a default value.
     * Should be used to create new reproductions of animals.
     *
     * @param name - String - name of animal
     * @param icon - String - icon
     */
    public EddieAnimal(String name, String icon)
    {
        // Call the super constructor
        super(name, icon);
        
        // Randomly set the sex to Male or Female (0.5 chance of either)
        if (new Random().nextBoolean()) this.sex = 'F';
        else this.sex = 'M';
        
        this.mobility = 3;
        this.weight = 15;        
    }
    
    /**
     * EddieAnimal Constructor
     * 
     * Overloaded constructor sets all instance variables to a passed in value; should be used for writing EddieAnimals to files.
     *
     * @param name - String
     * @param icon - String
     * @param fv - int - food value of animal
     * @param mobility - int - mobility of animal
     * @param weight - int - weight of animal
     */
    public EddieAnimal(String name, String icon, int fv, int mobility, int weight)
    {
        // Call the super constructor
        super(name, icon, fv);
        
        // Randomly set the sex to Male or Female (0.5 chance of either)
        if (new Random().nextBoolean()) this.sex = 'F';
        else this.sex = 'M';
        
        this.mobility = (byte) mobility;

        this.weight = (short) weight;   
    }
    
    /**
     * Method canEat
     * 
     * Simple method will evaluate if the animal can eat based off if it can gain or sustain energy.
     *
     * @param food - EddieBiotic - the potential food to eat
     * @return boolean - true if can eat, false elsewise.
     */
    public boolean canEat(EddieBiotic food)
    {
        // So long as energy can be received or sustained from eating, do so.
        return energy <= MAX_ENERGY;
    }

    /**
     * Method eat
     * 
     * Increases energy and weight by the food value of an EddieBiotic object that is the food.
     *
     * @param food  - EddieBiotic - the food to eat
     */
    public void eat(EddieBiotic food)
    {
       // Add energy based off the food value but cap it to the max energy (100) if needed.
       this.energy = (byte) (Math.min(MAX_ENERGY, (food.getFoodValue() + this.energy))); 
       // Add food value to weight.
       this.weight += food.getFoodValue();
    }
    
    /**
     * Method canReproduce
     * 
     * Evaluates whether the animal can reproduce with another animal and returns a boolean indicating so.
     *
     * @param mate - EddieAnimal - potential mate
     * @return boolean
     */
    public boolean canReproduce(EddieAnimal mate)
    {
        // Call the super method and ensure that they are of different sex before returning true (ergo, can mate).
        if (super.canReproduce(mate) && this.sex != mate.sex)
        {
            return true; 
        }

        // elsewise, return false meaning they cannot reproduce.
        return false;
    }
    
    /**
     * Method reproduce
     * 
     * Overriden method takes a mate, EddieBiotic object, and constructs a new EddieAnimal object with the same species name and icon yet with default instance vars.
     *
     * @param mate - EddieAnimal - mate
     * @return EddieBiotic - the newly constructed baby
     */
    @Override
    public EddieBiotic reproduce(EddieBiotic mate)
    {
        // Create a new EddieAnimal object of the same species and icon.
        // Choosing an overloaded constructor that will not set other instance vars as babies spawn with default values.
        return new EddieAnimal(this.getName(), this.icon);
    }
    
    /**
     * Method deteriorate
     * 
     * Imitiates natural deterioration of an animal with heightened effects due to the environment's stress and if the animal is infected or not.
     *
     * @param env - EddieEnvironment - depicts the environment and is used to determine how impactful it is.
     */
    @Override
    public void deteriorate(EddieEnvironment env)
    {
        if (this.virus != null)
        {
            this.energy -= this.virus.getEnergyImpact();
        }
        
        this.energy -= env.getEnergyImpact();
    }
    
    /**
     * Method toString
     * 
     * returns a String representation of the object.
     *
     * @return String
     */
    @Override
    public String toString()
    {
        return super.toString() +  " :: Mobility - " + this.mobility + " :: Weight - " + this.weight + "lbs";
    }
}
