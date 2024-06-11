import java.util.ArrayList;
import java.io.Serializable;
import java.util.Random;

/**
 * EddieCarnivore
 * 
 * Provides the basic functionality of a carnivore in an ecosystem where carnivores eat herbivores and battle other carnivores.
 * Subclass of EddieAnimal.
 *
 * @author (Eddie Gao)
 * @version (01/19/2023)
 */
public class EddieCarnivore extends EddieAnimal
{
    // Step 1. Declare and initialise instance variables
    private byte strength;
    
    // CONSTANT
    public static final byte MAX_STRENGTH = 100;
    
    // GETTERS
    public byte getStrength() { return this.strength; }
    
    /**
     * EddieCarnivore Constructor
     * 
     * Default constructor that takes no parameters, calls the super default constructor, and sets instance vars to a default logical value.
     *
     */
    public EddieCarnivore()
    {
        // Call the super constructor
        super();
        this.strength = 0;
    }
        
    /**
     * EddieCarnivore Constructor
     * 
     * Overloaded constructor that takes only the name and icon as Strings and sets all other IV's to default logical values; should be used when creating a new reproduced carnivore.
     *
     * @param name - String
     * @param icon - String
     */
    public EddieCarnivore(String name, String icon)
    {
        // Call the super constructor
        super(name, icon);
        this.strength = 0;
        this.icon = icon;
    }
    
    /**
     * EddieCarnivore Constructor
     *
     *
     * Overloaded constructor sets all instance variables to a passed in value; should be used for writing EddieCarnivores to files.
     * 
     * @param name - String
     * @param icon - String
     * @param fv - int - the food value of the carnivore
     * @param mobility - int
     * @param weight - int
     * @param strength - int
     */
    public EddieCarnivore(String name, String icon, int fv, int mobility, int weight, int strength)
    {
        // Call the super constructor
        super(name, icon, fv, mobility, weight);
        this.strength = (byte) (Math.abs(strength));
    }
    
    /**
     * Method canEat
     * 
     * Returns a boolean statement as to whether or not the carnivore can eat a biotic factor.
     *
     * @param food - EddieBiotic - the potential element to eat.
     * @return boolean - true if can eat, false otherwise.
     */
    @Override
    public boolean canEat(EddieBiotic food)
    {
        // ensures that the carnivore eats an animal and that it does not perform cannabalism.
        return super.canEat(food) && food instanceof EddieAnimal && food.getName() != this.name;        
    }
    
    /**
     * Method eat
     * 
     * Increases energy, weight, and strength by the food value of an EddieBiotic object that is the food.
     *
     * @param food  - EddieBiotic - the food to eat
     */
    @Override
    public void eat(EddieBiotic food)
    {
        if (canEat(food))
        {
            // eat food
            super.eat(food);
            this.strength += (food.getFoodValue());
        }
    }
    
    /**
     * Method battle
     * 
     * Battle against another carnivore by comparing strength values and eating the enemy if this is stronger than the enemy. Return true if this carnivore wins, false if the enemy wins.
     *
     * @param enemy - EddieCarnivore
     * @return boolean 
     */
    public boolean battle(EddieCarnivore enemy)
    {
        // If the strength is stronger or equal to the enemy's strength, this carnivore has won.
        if (this.strength >= enemy.getStrength())
        {
            // eat enemy to gain strength and energy and then return true indicating it has won.
            eat(enemy);
            return true;
        }
        else
        {
            // It has lost.
            return false;
        }
    }
    
    /**
     * Method reproduce
     * 
     * Overriden method takes a mate, EddieBiotic object, and constructs a new EddieCarnivore object with the same species name and icon yet with default instance vars.
     *
     * @param mate - EddieBiotic - mate
     * @return EddieBiotic - the newly constructed baby carnivore
     */
    @Override
    public EddieBiotic reproduce(EddieBiotic mate)
    {
        return new EddieCarnivore(this.getName(), this.icon);
    }
    
    /**
     * Method deteriorate
     * 
     * Imitiates natural deterioration of a carnivore with heightened effects due to the environment's stress and if the carnivore is infected or not.
     * Will lower the strength as well.
     *
     * @param env - EddieEnvironment - depicts the environment and is used to determine how impactful it is.
     */
    @Override
    public void deteriorate(EddieEnvironment env)
    {
        // Randomly determine whether the carnivore should deterioriate or not.
        if (new Random().nextFloat() < EddieBiotic.CARNIVORE_DECAY)
        {
            // call super method.
            super.deteriorate(env);
            
            // Decrease the strength by the energy impact of the environment yet cap it to a min. of 0.
            this.strength = (byte) (Math.max(this.strength - env.getEnergyImpact(), 0));
        }
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
        return super.toString() + " :: Strength: " + this.strength;
    }
}
