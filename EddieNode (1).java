/**
 * EddieNode class holds three instance variables:
 * cargo - a variable holding an EddieEcosystem object.
 * next - the next EddieNode
 *  * 
 * NOTE: The following EddieNode class is tailored for the ICS4U SUMMATIVE; it is not a node class that should be utilised and implemented
 * for general use. Hence, all methods and instance variables are modified to suit the functionality of the ecosystem simulation.
 *
 * @author (Eddie Gao)
 * @version (01/19/2023)
 */
public class EddieNode 
{
    // Step 1. Declare and initialise instance variables
    // ALL VARIABLES ARE DECLARED NOT AS CONSTANT AS THEY ARE SUBJECT TO CHANGE. ALL VARIABLES SHOULD NOT BE CHANGED UNLESS GIVEN CONFIRMATION
    // FROM THE USER/ADMINISTRATOR -- THUS, THEY ARE private and ENCAPSULATED.
    private EddieEcosystem cargo;
    private EddieNode next;
    
    
    // CONSTRUCTORS
    // ▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀
    /**
     * EddieNode Constructor
     * 
     * A default constructor that takes no parameters and sets instance variables to a logical value.
     * In this case, all instnace variables are set to null as they are not primitives; instead, they are of an Object data type.
     * For instance, cargo is an EddieEcosystem object, next is an EddieNode object.
     *
     */
    public EddieNode() // <- no param. therefore default constructor
    { 
        // set all instance variables to nul
        cargo = null;
        next = null;
    }
    
    /**
     * EddieNode Constructor
     * 
     * An overloaded constructor that takes one parameter, ep, and sets that to the cargo of the node.
     * Constructor should be used when the next EddieNode are unknown.
     *
     * @param ep - EddieEcosystem - represents one day of the ecosystem
     */
    public EddieNode(EddieEcosystem ee) 
    {
        cargo = ee;
        next = null;
    }
    
    /**
     * EddieNode Constructor
     * 
     * An overloaded constructor that takes two parameters, ee and n, and sets them to the cargo and the next EddieNode respectively.
     *
     * @param ep A parameter
     * @param n A parameter
     */
    public EddieNode(EddieEcosystem ee, EddieNode n) 
    {
        cargo = ee;
        next = n;
    }
    
    
    // ▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀
    
    /**
     * Method toString
     *
     * @return String - String representation of the node by simply calling the cargo toString() ...
     * 
     */
    public String toString() 
    {
        return "" + cargo.toString();
    }
    
    // GETTERS AND SETTERS
    // ▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀
    
    /**
     * Method getCargo
     * 
     * Method returns the cargo of the EddieNode, i.e, returning an EddieEcosystem object that the cargo holds.
     *
     * @return EddieEcosystem
     */
    public EddieEcosystem getCargo()
    {
        return this.cargo;
    }
    
    /**
     * Method getNext
     * 
     * Returns the next EddieNode that this EddieNode points to.
     *
     * @return EddieNode
     */
    public EddieNode getNext()
    {
        return this.next;
    }
    
    /**
     * Method setNext
     *
     * @param n - an EddieNode object to set the next to.
     */
    public void setNext(EddieNode n)
    {
        this.next = n;
    }
    
    // ▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀
    
    // END OF PROGRAM
}