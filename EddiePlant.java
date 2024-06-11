import java.util.Random;
import java.util.ArrayList;

/**
 * EddiePlant
 * 
 * Class provides the basic functionality of a plant in an ecosystem where it will be chased and eaten by herbivores
 * Subclass of EddieBiotic.
 *
 * @author (Eddie Gao)
 * @version (01/19/2023)
 */
public class EddiePlant extends EddieBiotic
{
    // Constructors
    
    /**
     * EddieAnimal Constructor
     * 
     * Default constructor calls super() default constructor and sets instance vars. to logical default values.
     *
     */
    public EddiePlant()
    {
        super();
    }
    
    
    /**
     * EddieCarnivore Constructor
     * 
     * Overloaded constructor that takes only the name and icon as Strings; should be used when creating a new reproduced plant.
     *
     * @param name - String
     * @param icon - String
     */
    public EddiePlant(String name, String icon)
    {
        super(name, icon);
    }
    
    /**
     * EddiePlant Constructor
     * 
     * Overloaded constructor sets all instance variables to a passed in value; should be used for writing EddiePlants to files.
     *
     * @param name String
     * @param icon String
     * @param foodValue int
     */
    public EddiePlant(String name, String icon, int foodValue)
    {
        super(name, icon, foodValue);
    }
    
    /**
     * Method canReproduce
     * 
     * Evaluates whether the plant can reproduce to an empty spot in the ecosystem and returns a boolean indicating so.
     *
     * @param mate - EddieBiotic
     * @return boolean
     */
    public boolean canReproduce(EddieBiotic openSpot)
    {
        if (super.canReproduce(openSpot))
        {
            return true;
        }
        
        return false;
    }
    
    /**
     * Method deteriorate
     * 
     * Imitiates natural deterioration of a plant with heightened effects due to the environment's stress
     *
     * @param env - EddieEnvironment - depicts the environment and is used to determine how impactful it is.
     */
    @Override
    public void deteriorate(EddieEnvironment env)
    {
        // Randomly determine whether the plant should deterioriate or not.
        if (new Random().nextFloat() < EddieBiotic.PLANT_DECAY)
            this.energy -= env.getEnergyImpact();
        else
        {
            // If it does not deteriorate, it must photosynthesize instead.
            photosynthesize(env);
        }
    }
    
    /**
     * Method photosynthesize
     * 
     * Helper method applies the opposite of deterioration; instead, the plant naturally photosynthesizes and gains energy from the environment.
     *
     * @param env - EddieEnvironment - the environment in which the plant inhabits.
     */
    private void photosynthesize(EddieEnvironment env)
    {
        // Add the energy impact to the energy but cap it at the max energy.
        this.energy = (byte) Math.min(MAX_ENERGY, energy + env.getEnergyImpact());
    }

    /**
     * Method reproduce
     * 
     * Overriden method takes a mate, EddieBiotic object, and constructs a new EddiePlant object with the same species name and icon yet with default instance vars.
     *
     * @param mate - EddieBiotic - mate
     * @return EddieBiotic - the newly constructed baby plant
     */
    public EddieBiotic reproduce(EddieBiotic mate)
    {
        if (mate == null)
            return new EddiePlant(this.getName(), this.icon);
        
        return null;
    }
    
    /**
     * Method toString
     * 
     * returns a String representation of the object.
     *
     * @return String
     */
    public String toString()
    {
        return super.toString();
    }
    
}
