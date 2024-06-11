// Import libraries
import java.util.ArrayList;
import java.io.Serializable;

/**
 * EddieBiotic
 * 
 * Abstract superclass provides the basic blueprint for a biotic factor in an ecosystem. Allows the ability to reproduce, deteriorate, and display itself on the ecosystem grid.
 * 
 * Methods pertain to:
 * 
 * - GETTERS
 * - SETTERS
 * - canReproduce - boolean - whether the biotic factor can reproduce with another biotic factor or not.
 * - abstract reproduce
 * - abstract deteriorate - natural deterioration of a biotic factor as time progresses
 * - boardDisplay - displays the biotic factor's icon for the ecosystem grid.
 * - toString - returns String representation of Biotic factor.
 *
 * @author (Eddie Gao)
 * @version (19/01/2023)
 */
public abstract class EddieBiotic implements Serializable // implements Serializable for FILE IO
{
    // Step 1. Declare and initialise instance variables
    // SOME VARIABLES ARE DECLARED NOT AS CONSTANT AS THEY ARE SUBJECT TO CHANGE. ALL VARIABLES SHOULD NOT BE CHANGED UNLESS GIVEN CONFIRMATION
    // FROM THE USER/ADMINISTRATOR -- THUS, THEY ARE PROTECTED and ENCAPSULATED.
    protected byte energy, foodValue; // the energy of the biotic element; if the energy = 0, they are dead.
    // food value depicts how much energy is to be gained if the biotic factor is consumed.
    protected String name; // depicts the name, also it can be used so that carnivores can eat other carnivores, just not the same species (that would be cannabalism).
    protected String icon; // the icon presented on the ecosystem grid.
    protected EddieVirus virus; // isnt in constructor yet
    
    // CONSTANTS
    public static final byte MAX_ENERGY = 100; // The maximum energy any biotic factor can have, byte data type as energy will never exceed 100 (within byte range).
    public static final float CARNIVORE_DECAY = 0.4f, HERBIVORE_DECAY = 0.01f, PLANT_DECAY = 0.5f; 
    // All carnivores, herbivores, and plants will decay over time.
    // These float values depict the decay rate; carnivores decay more as they require more energy to hunt.
    // Plants decay the most as they require more nutrients from soil, sun, and water to survive; and will deteriorate faster without them.
    
    // GETTERS
    public boolean isAlive() { return this.energy > 0; } // return whether the factor still has energy or not.
    public byte getEnergy() { return this.energy; } // return the energy.
    public float getEnergyRate() { return (float) (this.energy) / MAX_ENERGY; } // returns a float value depicting the energy rate (energy/100)
    public String getName() { return this.name; } // returns name
    public byte getFoodValue() { return this.foodValue; } // returns food value
    public boolean isInfected() { return virus != null; } // returns whether the biotic fac. is infected or not.
    public String getIcon() { return icon; } // returns the icon.
    
    // SETTERS
    public void setVirus(EddieVirus v) { this.virus = v; } // sets the virus to a desired EddieVirus object.
    
    // constructors
    /**
     * EddieBiotic Constructor
     * 
     * Default constructor that sets instance vars. to a default logical value.
     *
     */
    public EddieBiotic()
    {
        this.name = "N/A";
        this.icon = "B";
        this.energy = MAX_ENERGY; 
        this.foodValue = 15;
        this.virus = null;
    }
    
    /**
     * EddieBiotic Constructor
     * 
     * Overloaded constructor that only passes in the name and icon as Strings of the EddieBiotic object; used for reproducing new biotic factors.
     * 
     * @param name - String - desired name
     * @param icon - String - desired icon
     */
    public EddieBiotic(String name, String icon)
    {
        this.name = name;
        this.icon = icon;
        this.energy = MAX_ENERGY; 
        this.foodValue = 15;
        this.virus = null;
    }
    
    /**
     * EddieBiotic Constructor
     * 
     * Overloaded constructor that sets instance variables for name, icon, and foodValue. Should be utilised to construct biotic factors when all information is known (e.g file io).
     *
     * @param name - String - desired name
     * @param icon - String - desired icon
     * @param foodValue - int - represents the food value (casted to byte in constructor for efficiency)
     */
    public EddieBiotic(String name, String icon, int foodValue)
    {
        this.name = name;
        this.icon = icon;
        this.energy = MAX_ENERGY;
        this.foodValue = (byte) (Math.abs(foodValue));
        this.virus = null;
    }
    
    // =================================================================================================
    
    /**
     * Method canReproduce
     * 
     * Returns a boolean as to whether or not the factor can reproduce with another biotic factor; this is influenced by their energies and names.
     *
     * @param mate - EddieBiotic - the potential mate that the biotic factor has encountered in the ecosystem
     * @return boolean - true if it can reproduce, false if not.
     */
    public boolean canReproduce(EddieBiotic mate)
    {
        // Check if they are of same species
        if (this.name.equalsIgnoreCase(mate.name))
        {
            // return that it can indeed mate.
            return true;
        }
        
        // return false if the above condition was not met.
        return false;
    }  
    
    
    /**
     * Method reproduce
     * 
     * Abstract method that allows different types of biotic factors to modify a reproduction method.
     *
     * @param mate - EddieBiotic - the potential mate.
     * @return EddieBiotic - the newly reproduced biotic factor (e.g a baby).
     */
    public abstract EddieBiotic reproduce(EddieBiotic mate); 

    /**
     * Method deteriorate
     * 
     A method that instructs the natural deterioation of biotic elements as time passes, to be used to decrease energy levels; thus,
      creating an endless desire to get energy.
    This method will change and alter different stats depending on the element, for eg, carnivores deterioate faster
     and plants should overload this method with the environment parameter.  
     *
     * @param env - EddieEnvrionment - the environment affects the deterioration of energy for all biotic factors.
     */
    public abstract void deteriorate(EddieEnvironment env);
    
    /**
     * Method boardDisplay
     * 
     * returns the icon of the EddieBiotic for the ecosystem grid
     *
     * @return String - the icon.
     */
    public String boardDisplay()
    {
        return this.icon;
    }
    
    /**
     * Method toString
     * 
     * returns String representation of Biotic factor
     *
     * @return String
     */
    @Override
    public String toString()
    {
        return this.name + " :: Energy - " + this.energy;
    }
}
