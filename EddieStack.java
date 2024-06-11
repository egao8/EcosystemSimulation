
/**
 * EddieStack
 * 
 * An extension of the linked list, the stack ADT utilises the EddieLinkedList as an instance variable and exhibits the FIFO principle. Methods entail push, pop, peek, and size.
 *
 * @author (Eddie Gao)
 * @version (19/01/2023)
 */
public class EddieStack
{
    // INSTANCE VARIABLES
    
    // A private EddieLinkedList which will be utilised to build the Stack ADT upon.
    private EddieLinkedList stack;
    
    /**
     * EddieStack Constructor
     * 
     * Default constructor that takes no parameters and instantiates the stack instance variable.
     *
     */
    public EddieStack() // <- no param. = default constructor
    {
        this.stack = new EddieLinkedList();
    }
    
    /**
     * EddieStack Constructor
     * 
     * An overloaded constructor that takes in an EddieLinkedList and sets the stack to that. Constructor should be used to change the stack to another stack if one is already established.
     *
     * @param ll - EddieLinkedList - represents the new stack.
     */
    public EddieStack(EddieLinkedList ll)
    {
        this.stack = ll;
    }
    
    /**
     * Method push
     * 
     * Adds an EddieNode to the top of the stack (first element/0th). Obeys FIFO principle.
     *
     * @param element - EddieEcosystem -- represents the cargo of the top EddieNode of the stack.
     */
    public void push(EddieEcosystem element)
    {
        stack.addFirst(element);
    }
    
    /**
     * Method pop
     * 
     * Removes and returns the EddieNode at the top of the stack -- obeys FIFO.
     *
     * @return EddieNode - represents the first node at the top of the stack.
     */
    public EddieNode pop()
    {
        return stack.removeFirst();
    }
    
    /**
     * Method peek
     * 
     * Returns the cargo of the EddieNode at the top of the stack but does not remove it.
     *
     * @return EddieEcosystem - the cargo of the first node/top of the stack.
     */
    public EddieEcosystem peek()
    {
        return stack.head().getCargo();
    }
    
    /**
     * Method size
     * 
     * returns the size of the stack, i.e, the num. nodes in the stack.
     *
     * @return int - represents size of stack.
     */
    public int size()
    {
        return stack.length();
    }
    
    // END OF PROGRAM
}
