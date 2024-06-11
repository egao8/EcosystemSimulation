import java.io.Serializable;

/**
 * Abiotic class provides a blueprint of an abiotic factor in the ecosystem; it provides the ability to construct the factors that all abiotic factors have and contains a toString method.
 *
 * @author (Eddie Gao)
 * @version (01/19/2023)
 */
public class EddieAbiotic implements Serializable
{
    // Step 1. Declare and initialise instance variables
    //  ALL VARIABLES SHOULD NOT BE CHANGED UNLESS GIVEN CONFIRMATION
    // FROM THE USER/ADMINISTRATOR -- THUS, THEY ARE PROTECTED and ENCAPSULATED.
    protected String name; // depicts the name of abiotic factor
    protected byte energyImpact; // how much energy this Abiotic factor will induce onto a biotic factor; note: most stressors will have a negative effect.
    // for e.g a colder climate will have a higher energy impact meaning animals need more energy to stay warm.
    
    // GETTERS
    public String getName() { return this.name; } // returns the name
    public byte getEnergyImpact() { return this.energyImpact; } // returns the energy impact
    
    
    /**
     * EddieAbiotic Constructor
     * 
     * Default constructor that takes no parameters and sets instance variables to logical default values.
     *
     */
    public EddieAbiotic()
    {
        // An undocumented/unregistered name.
        this.name = "N/A";
        // No energy impact by default
        this.energyImpact = 0;
    }
    
    /**
     * EddieAbiotic Constructor
     * 
     * An overloaded constructor that sets all instance variables to given parameters
     *
     * @param name - String - desired name
     * @param energyImpact - int - desired energyImpact
     */
    // energyImpact param is int data type yet casted to byte afterwards for user convenience and efficiency.
    public EddieAbiotic(String name, int energyImpact)
    {
        this.name = name;
        this.energyImpact = (byte) energyImpact;
    }
    
    /**
     * Method toString
     * 
     * Returns a String representation of the Abiotic factor.
     *
     * @return String
     */
    @Override
    public String toString()
    {
        return this.name.toUpperCase() + " :: Energy Impact - " + this.energyImpact;
    }
}
