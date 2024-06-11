import java.util.ArrayList;
import java.util.Random;


/**
 * EddieHerbivore
 * 
 * Class provides the basic functionality of a herbivore in an ecosystem where it will be chased and eaten by carnivores and eat plants.
 * Subclass of EddieAnimal.
 *
 * @author (Eddie Gao)
 * @version (01/19/2023)
 */
public class EddieHerbivore extends EddieAnimal
{    
    /**
     * EddieHerbivore Constructor
     * 
     * Default constructor that takes no param. and merely calls the super default constructor.
     *
     */
    public EddieHerbivore()
    {
        super();
    }
    
    /**
     * EddieHerbivore Constructor
     * 
     * Overloaded constructor that takes only the name and icon as Strings; should be used when creating a new reproduced herbivore.
     *
     * @param name String
     * @param icon String
     */
    public EddieHerbivore(String name, String icon)
    {
        super(name, icon);
    }
    
    /**
     * EddieHerbivore Constructor
     *
     * Overloaded constructor sets all instance variables to a passed in value; should be used for writing EddieHerbivore to files.
     * 
     * @param name - String
     * @param icon - String
     * @param fv - int - the food value of the herbivore
     * @param mobility - int
     * @param weight - int
     */
    public EddieHerbivore(String name, String icon, int fv, int mobility, int weight)
    {
        super(name, icon, fv, mobility, weight);
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
        // Randomly determine whether the herbivore should deterioriate or not.
        if (new Random().nextFloat() < EddieBiotic.HERBIVORE_DECAY)
            super.deteriorate(env); // call super method
    }
    
    /**
     * Method canEat
     * 
     * Returns a boolean statement as to whether or not the herbivore can eat a biotic factor.
     *
     * @param food - EddieBiotic - the potential element to eat.
     * @return boolean - true if can eat, false otherwise.
     */
    @Override
    public boolean canEat(EddieBiotic food)
    {
        // As long as the super method returns true AND the food object is an instance of the EddiePlant class, the herbivore can eat it.
        if (super.canEat(food))
        {
            return (food instanceof EddiePlant);
        }
        else // otherwise return false indicating it cannot eat.
        {
            return false;
        }
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
        // Ensure that the herbivore can in fact eat the food prior to calling the super method.
        if (canEat(food))
        {
            super.eat(food);
        }
    }
    
    /**
     * Method reproduce
     * 
     * Overriden method takes a mate, EddieBiotic object, and constructs a new EddieHerbivore object with the same species name and icon yet with default instance vars.
     *
     * @param mate - EddieBiotic - mate
     * @return EddieBiotic - the newly constructed baby
     */
    @Override
    public EddieBiotic reproduce(EddieBiotic mate)
    {
        return new EddieHerbivore(this.getName(), this.icon);
    }
    
    /**
     * Method toString
     * 
     * returns a String representation of the EddieHerbivore object by merely calling the super toString()
     *
     * @return String
     */
    @Override
    public String toString()
    {
        return super.toString();
    }
}
