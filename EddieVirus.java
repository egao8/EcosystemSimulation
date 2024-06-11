
/**
 * EddieVirus
 * 
 * Provides the basic functionality of a virus in an ecosystem -- inheriting from the EddieAbiotic superclass.
 *
 * @author (Eddie Gao)
 * @version (19/01/2023)
 */
public class EddieVirus extends EddieAbiotic
{
    // Step 1. Declare and initialise instance variables
    //ALL VARIABLES SHOULD NOT BE CHANGED UNLESS GIVEN CONFIRMATION
    // FROM THE USER/ADMINISTRATOR -- THUS, THEY ARE private and ENCAPSULATED.
    private byte chanceToSpread; // a byte value representing the chance the virus will spread into other positions/cells of the ecosystem
    // byte data type as the chanceToSpread is capped at 128, conveniently the same as the byte max range.
    
    // GETTERS
    public byte getSpreadChance() { return this.chanceToSpread; } // returns chance to spread
    
    /**
     * EddieVirus Constructor
     * 
     * Default constructor
     *
     */
    public EddieVirus()
    {
        super(); // call super() constructor.
        // By default the virus will not have a chance to spread; it is docile.
        this.chanceToSpread = 0;
    }
    
    /**
     * EddieVirus Constructor
     * 
     * Overloaded constructor 
     *
     * @param name - String - name of the virus
     * @param energyImpact - int (for user convenience, casted as byte for efficiency in constructor) - the desired energy impact value.
     * @param cts - int - chance to spread value.
     */
    public EddieVirus(String name, int energyImpact, int cts)
    {
        super(name, energyImpact); // call overloaded super constructor.
        
        this.chanceToSpread = (byte) Math.abs(cts); // chance to spread must be a positive value.
    }
    
    /**
     * Method toString
     * 
     * returns String representation of the EddieVirus object.
     *
     * @return String
     */
    public String toString()
    {
        return super.toString() + " :: Chance to Spread - " + this.chanceToSpread;
    }
    
    
}
