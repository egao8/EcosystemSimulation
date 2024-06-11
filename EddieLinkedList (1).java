import java.util.ArrayList;


/**
 * EddieLinkedList 
 *
 * Abstract-data-type that holds a EddieNode head and a length -- representing the num. nodes in the linkedlist. Methods entail: findNode, addFirst, removeFirst, toString, GETTERS AND SETTERS.
 * 
 *
 * @author (Eddie Gao)
 * @version (01/18/2023)
 */
public class EddieLinkedList
{
    // Step 1. Declare and initialise instance variables
    // ALL VARIABLES ARE DECLARED NOT AS CONSTANT AS THEY ARE SUBJECT TO CHANGE. ALL VARIABLES SHOULD NOT BE CHANGED UNLESS GIVEN CONFIRMATION
    // FROM THE USER/ADMINISTRATOR -- THUS, THEY ARE private and ENCAPSULATED.
    private EddieNode head; // the head of the linked list is an EddieNode at index of 0. 
    private int length; // Int as the amount of different ecosystem iterations could expand past a short value yet logically will never exceed Integer.MAX_VALUE
    
    /**
     * EddieLinkedList Constructor
     * 
     * A default constructor that takes no parameters and sets instance variables to a logical value.
     *
     */
    public EddieLinkedList() // <- no param. = default constructor
    {
        length = 0; // By default, there have been nodes in the linkedlist; so, the length is set to 0.
        head = null; // Set the head to null to represent that the Linkedlist is empty.
    }
    
    
    /**
     * Method findNode
     * 
     * Given an index, the EddieNode at that index will be found and returned.
     *
     * @param index - int - represents the index at which the desired EddieNode is located in the linkedlist; integer data type as, in terms of the ecosystem simulation, it is completely plausible to have
     * more than the short max range yet less than the long min range.
     * @return EddieNode - the node at the index.
     */
    public EddieNode findNode(int index)
    {
        // Logic check
        // if the index is greater than or equal to the length (given first element starts at index 0) or the index is negative, return null as these indices are impossible.
        if (index >= this.length || index < 0) return null;
        
        // Create a temporary var and set it to the head in order to iterate.
        EddieNode temp = this.head;
        
        // iterate through the ll until the desired node at index is reached.
        for (int i = 0; i < index; i++)
        {
            temp = temp.getNext();
        }
        
        // return the EddieNode
        return temp;
    }
    
    /**
     * Method addFirst
     * 
     * With the appropriate cargo type (EddieEcosystem), add an EddieNode as the head with the passed cargo and increment the length.
     *
     * @param e - EddieEcosystem -- the desired cargo of the new head
     */
    public void addFirst(EddieEcosystem e)
    {
        // Set the current head to a new EddieNode object with the param. e as cargo and the current head as the next node; essentially, replacing the head yet storing it as the next pointed-to node.
        this.head = new EddieNode(e, this.head);
        // increment the length
        this.length++; 
    }
    
    /**
     * Method removeFirst
     * 
     * Removes the head of the linked list and returns it.
     *
     * @return The return value
     */
    public EddieNode removeFirst() 
    {
        // Logic check: if there is no head -- meaning, there are no nodes in the ll, return null as you cannot remove a non-existent first node.
        if (this.head == null) return null;
        
        // Set a temp node to the head in order to keep track of it.
        EddieNode temp = this.head;
        // now replace the head with its next pointed to node; the old head is now lost EXCEPT since we stored it in a temp variable.
        // we store the old head in order to be able to return it.
        this.head = temp.getNext();
        this.length--; // decrement length
        
        return temp; // return old head/temp.
    }
    
    /**
     * Method toString
     * 
     * Returns a string representation of the linkedlist by printing all nodes (calling their toString())
     *
     * @return String
     */
    public String toString() 
    {
        // If the ll is empty, return a String declaring that to the console.
        if (isEmpty()) return "Empty";
        
        // Otherwise, set a temp EddieNode variable to the head.
        EddieNode n = this.head;
        
        String result = "";
        // Iterate through the ll.
        for (int i = 0; i < this.length; i++) 
        {
            // Add each node's toString() call to the result String.
            result += n.toString() + ", ";
            // go to the next node.
            n = n.getNext();
        }
        
        // return the final result.
        return result + " ";
    }
    
    // GETTERS AND SETTERS
    
    /**
     * Method length
     * 
     * Returns the length of the linkedlist as a int.
     *
     * @return - int
     */
    public int length() 
    {
        return length;
    }
    
    /**
     * Method head
     * 
     * returns the head.
     *
     * @return EddieNode
     */
    public EddieNode head() 
    {
        return head;
    }
    
    /**
     * Method isEmpty
     * 
     * Returns a boolean statement as to if the linkedlist is empty or not (true = empty).
     *
     * @return boolean
     */
    public boolean isEmpty()
    {
        return length == 0;
    }
    
    /**
     * Method setHead
     * 
     * Sets the current this.head to a new EddieNode object.
     *
     * @param h - EddieNode - the new this.head that is specified by the user.
     */
    public void setHead(EddieNode h)
    {
        this.head = h;
    }
    
    
    // END OF PROGRAM
}