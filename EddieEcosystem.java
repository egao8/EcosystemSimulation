// Import libraries
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
import javax.swing.Timer;

/**
 * EddieEcosystem
 * 
 * Utilises EddieBiotic, EddieEnvironment obects to create a functioning ecosystem that can naturally progress. Class allows movement of all animals, the ability for biotic elements to interact
 * with each other, and the process of sorting and searching elements.
 * Class holds the population of all elements inside and inhabits an environment which together processes natural selection and the hierarchy of animals.
 * 
 * @author (Eddie Gao)
 * @version (01/19/2023)
 */
public class EddieEcosystem
{
    
    // Step 1. Declare and initialise instance variables
    // ALL VARIABLES SHOULD NOT BE CHANGED UNLESS GIVEN CONFIRMATION
    // FROM THE USER/ADMINISTRATOR -- THUS, THEY ARE private and ENCAPSULATED.
    private EddieBiotic[][] ecosystem; // a 2d array of EddieBiotic objects that represents the physical portrayal of the ecosystem and the positions of all biotic factors inside it.
    private EddieEnvironment environment; // the environment in which the ecosystem is inhabited in; an EddieEnvironment object.
    
    // ArrayLists holding EddieBiotic objects that can be defined as herbivores, carnivores, or plants; these arraylists are utilised to keep track of which elements are currently in the 
    // ecosystem.
    private ArrayList<EddieBiotic> herbivores;
    private ArrayList<EddieBiotic> carnivores;
    private ArrayList<EddieBiotic> plants;
    
    // Instance variables depicting the height, width, and area of the ecosystem in meters.
    // Integer data type as it is completely plausible for an ecosystem to expand beyond a short range (~32,000) in meters.
    private int height, width, area;

    
    // CONSTANT default ecosystem dimensions (x,y) in meters.
    public static final byte[] DEFAULT_ECOSYSTEM_SIZE = {60, 20};
    
    // GETTERS
    public int getWidth() { return this.width; } // Returns width
    public int getHeight() { return this.height; } // Returns height
    public EddieBiotic[][] getEcoGrid() { return this.ecosystem; } // Returns the entire ecosystem grid
    public EddieEnvironment getEnvironment() { return this.environment; } // Returns the environmnet
    public ArrayList<EddieBiotic> getHerbivores() { return this.herbivores; } // Returns the arraylist of all herbivores
    public ArrayList<EddieBiotic> getCarnivores() { return this.carnivores; } // Returns the arraylist of all carnivores
    public ArrayList<EddieBiotic> getPlants() { return this.plants; } // Returns the arraylist of all plants
    
        /**
         * Method getPopulation
         * 
         * A modified getter to receive
         *
         * @param type A parameter
         * @return The return value
         */
    public ArrayList<EddieBiotic> getPopulation(String type)
    {
        if (type.equalsIgnoreCase("herbivore")) return herbivores;
        else if (type.equalsIgnoreCase("carnivore")) return carnivores;
        else if (type.equalsIgnoreCase("plant")) return plants;
        else
        {
            System.out.println("\nError; invalid type.");
            return null;
        }
    }
    
    /**
     * Method sufficientPopulation
     * 
     * A sufficient population must entail at least 1 carnivore, herbivore, or plant, in order to thrive.
     * The arraylist size contains the population of all types of animals; thus it is utilised to calculate whether the ecosystem holds a sufficient population.
     *
     * @return boolean - true if the population is sufficient, false if not.
     */
    public boolean sufficientPopulation()
    {      
        // If the herbivores, carnivores, and plants arraylists are ALL not EMPTY, return true; meaning, there is a sufficient pop.
        if ((!(this.herbivores.isEmpty())) && (!(this.carnivores.isEmpty())) && (!(this.plants.isEmpty()))) return true;
        
        return false; // Otherwise, return false.
    }
    
    // SETTERS
    /**
     * Method constructNewDimensions
     * 
     * Applies new dimensions to the ecosystem grid by taking in a desired width, height, and reconstructing the ecosystem grid to those measurements; further,
     * will change total area to the new dimensions (area = width * height).
     *
     * @param width - int - represents desired width dimension.
     * @param height - int - represents desired height dimension.
     */
    public void constructNewDimensions(int width, int height)
    {
        // Set the instance variables, width, and height, to new dimensions.
        this.width = Math.abs(width);
        this.height = Math.abs(height);
        // reconstruct the area with new dimensions.
        this.area = this.width*this.height; 
        // reconstruct the ecosystem with the desired dimensions.
        this.ecosystem = new EddieBiotic[this.width][this.height];     
    }
    
    /**
     * Method setEnvironment
     * 
     * Sets the environment to a desired EddieEnvironment object.
     *
     * @param e - EddieEnvironment - desired environment to change.
     */
    public void setEnvironment(EddieEnvironment e)
    {
        this.environment = e;
    }

    /**
     * EddieEcosystem Constructor
     * 
     * Default constructor takes no parameters and sets all instance variables to a default logical value.
     *
     */
    public EddieEcosystem() // <- no param. meaning default constructor
    {
        // DIMENSIONS
        this.width = DEFAULT_ECOSYSTEM_SIZE[0]; // The 0th index in the array, DEFAULT_ECOSYSTEM_SIZE represents the x dimension, i.e width
        this.height = DEFAULT_ECOSYSTEM_SIZE[1]; // The 1st index represents the y dimension, i.e height.
        this.area = this.height * this.width; // A = w*h 
        
        // ECOSYSTEM
        this.ecosystem = new EddieBiotic[Math.abs(this.width)][Math.abs(this.height)]; // Construct the ecosystem 2d grid with the default width and height.
        this.environment = new EddieEnvironment(); // construct a new EddieEnvironment object with its default constructor.
        
        // INSTANTIATE ALL BIOTIC FACTOR ARRAYLISTS.
        this.herbivores = new ArrayList<EddieBiotic>();
        this.carnivores = new ArrayList<EddieBiotic>();
        this.plants = new ArrayList<EddieBiotic>();
    }  
    
    /**
     * EddieEcosystem Constructor
     * 
     * Overloaded constructor that takes dimensions, width, and height, and creates the ecosystem grid with the desired values whilst setting all other instance vars.
     * to a default logical value.
     *
     * @param width A parameter
     * @param height A parameter
     */
    public EddieEcosystem(int width, int height)
    {
        // DIMENSIONS
        // N.B; dimensions cannot be negative, automatically make them positive to check for potential errors.
        this.width = Math.abs(width); 
        this.height = Math.abs(height);
        this.area = this.height * this.width;
        
        // Construct the ecosystem 2d grid with the desired width and height.
        this.ecosystem = new EddieBiotic[Math.abs(this.width)][Math.abs(this.height)];
        this.environment = new EddieEnvironment();
        
        // INSTANTIATE ALL BIOTIC FACTOR ARRAYLISTS.
        this.herbivores = new ArrayList<EddieBiotic>();
        this.carnivores = new ArrayList<EddieBiotic>();
        this.plants = new ArrayList<EddieBiotic>();
    }
    
    /**
     * EddieEcosystem Constructor
     * 
     * Overloaded constructor that takes in an EddieEcosystem object, and sets all instance variables to the passed in ecosystem's instance variables.
     * Should be used to create a shallow copy; more specifically, to store old versions of an EddieEcosystem object (seen in EddieSimulation).
     *
     * @param eco - EddieEcosystem - the new desired EddieEcosystem.
     */
    public EddieEcosystem(EddieEcosystem eco)
    {
        // CALL ALL GETTERS FROM THE eco and set the current ecosystem object to the eco.
        this.width = eco.getWidth();
        this.height = eco.getHeight();
        this.area = this.width*this.height;
        this.ecosystem = eco.getEcoGrid();
        this.environment = eco.getEnvironment();
        
        this.herbivores = eco.getHerbivores();
        this.carnivores = eco.getCarnivores();
        this.plants = eco.getPlants();
    }
    
    /**
     * Method checkCapacity
     * 
     * Helper Method
     * An ecosystem can be full of elements such that it is at max capacity; method checks if the singular positions in the ecosystem can still be filled.
     *
     * @param filled - int - the amount of positions that have already been filled by a biotic factor
     * @param toBeFilled - int - the amount of total positions that can be filled
     * @return - boolean - true if the capacity has not yet been reached; false otherwise.
     */
    private boolean checkCapacity(int filled, int toBeFilled)
    {
        // Firstly, ensure that all values are positive.
        // Check if the currently filled num. is less than the total amount that can be filled; if the filled num is less than, this means that there are still
        // positions to be filled. Ensure that the total amount is greater than 0 as well so that there are indeed valid positions to fill.
        return (Math.abs(filled) < Math.abs(toBeFilled) && Math.abs(toBeFilled) > 0);
    }
    
    /**
     * Method addNewElement
     * 
     * Adds an element at a random position to the ecosystem whilst also adding that biotic factor to the corresponding ArrayList depicting the population.
     * Method will generate a random x and y coordinate, check if the ecosystem can support the element, spawn the biotic factor at that position, and then
     * add that biotic factor to either the population of herbivores, carnivores, or plants.
     *
     * @param element  - EddieBiotic - represents the desired biotic factor to enter the ecosystem.
     */
    public void addNewElement(EddieBiotic element)
    {
        // Step 1. Initialise a random [x,y] coordinate within the bounds of the ecosystem
        int x = new Random().nextInt(this.width); 
        // Using int data type as it is plausible that the ecosystem expands past short max range yet below long min range.
        int y = new Random().nextInt(this.height);
        
        // Step 2. Ensure that the position the element desires to enter is null (meaning an empty space) and that all the biotic factors
        // and their positions are less than the total area. Calculate the amount of biotic factors by receiving the sum of all 
        // carnivores, herbivores, and plants.
        if (ecosystem[x][y] == null && checkCapacity((this.herbivores.size() + this.carnivores.size() + this.plants.size()), this.area))
        {
            // Step 3. Set the previously null position to the desired element.
            ecosystem[x][y] = element;
            
            // Step 4. Check which type of biotic factor the element is; then, add that element to the corresponding arraylist.
            if (ecosystem[x][y] instanceof EddieHerbivore)
            {   
                this.herbivores.add(element);
            }
            else if (ecosystem[x][y] instanceof EddieCarnivore)
            {
                this.carnivores.add(element);
            }
            else if (ecosystem[x][y] instanceof EddiePlant)
            {
                this.plants.add(element);
            }
        }
    }
    
    /**
     * Method introduceVirus
     * 
     * Receiving an EddieVirus object, will attempt to introduce a virus into the ecosystem by searching through the ecosystem[][] grid until a biotic element is found
     * and whilst the virus' still has a chance to spread. Utilising a sequential search algorithm for a 2d array, the amount of times a virus can attempt
     * to implement itself and infect a biotic factor is limited to the frequency in which it travels.
     *
     * @param virus - EddieVirus - the virus to be added into the ecosystem.
     */
    public void introduceVirus(EddieVirus virus)
    {
        // SEQUENTIAL SEARCH :: O(n) :: search through the 2d array until an EddieBiotic factor is found, then ensure that the virus can still spread
        // and that the biotic factor has not yet been infected yet.
        for (int y = 0; y < this.height; y++) // Increment to the height
        {
            for (int x = 0; x < this.width; x++) // Increment to the width
            {
                // Check if the ecosystem position at [x][y] is not null, meaning an EddieBiotic object inhabits it.
                // Also check if the ecosystem has not yet been infected, and then ensure that the spread chance is greater than the positions it has travelled.
                // The amount of times it travels is limited and conveniently increments with every iteration of searching through the position.
                // I.e, Once the maximum position (either x or y) exceeds the virus' ability to spread, it will no longer spread.
                if (ecosystem[x][y] != null && (!(ecosystem[x][y].isInfected())) && virus.getSpreadChance() > (Math.max(x, y)))
                {
                    ecosystem[x][y].setVirus(virus); // set the biotic factor to hold a virus.
                }
            }
        }
    }
    
    /**
     * Method evaluateEncounter
     * 
     * Helper method takes 2 sets of coordinates; presuming that two EddieBiotic factors at the two positions have interacted; and given their class type and
     * specific characteristics will create an evaluation of their encounter and subsequently change properties, i.e, the method adheres to the functionality where
     * carnivores eat herbivores and battle other carnivores, herbivores eat plants, and plants receive nutrition from the environment.
     *
     * @param x - the x coordinate of the first EddieBiotic factor
     * @param y - the y coordinate of the first EddieBiotic factor
     * @param xNewPosition - the x coordinate of the second EddieBiotic factor
     * @param yNewPosition - the y coordinate of the second EddieBiotic factor
     */
    private void evaluateEncounter(int x, int y, int xNewPosition, int yNewPosition) 
    {
        // N.B the instanceof operation is, among modern JVM compilers, the most efficient way to identify what class an object is.
        // github.com/michaeldorner/instanceofBenchmark displays it's performance.     
        
        // Check if the first biotic factor is a carnivore
        if (ecosystem[x][y] instanceof EddieCarnivore)
        {
            // If so, check if the second biotic factor is also a carnivore and that there are still enough carnivores to sustain a battle in the population
            if (ecosystem[xNewPosition][yNewPosition] instanceof EddieCarnivore  && this.carnivores.size() > 0)
            {
                // Allow the two carnivores to battle; both are casted as EddieCarnivores to sufficiently meet the parameter type and method
                // since battle() is a method specific to EddieCarnivores.
                if (((EddieCarnivore) ecosystem[x][y]).battle((EddieCarnivore)ecosystem[xNewPosition][yNewPosition]))
                {
                    // battle() returns true if the current carnivore ([x][y]) has won, and false if the other one.
                    
                    // If the first biotic factor one, remove the second one from the population of carnivores and set the position of the now killed carnivore
                    // to the victor; simulating a movement and usurp of position.
                    this.carnivores.remove(ecosystem[xNewPosition][yNewPosition]);
                    ecosystem[xNewPosition][yNewPosition] = ecosystem[x][y];
                }
                else
                {
                    // Otherwise if the second carnivore one, apply the same functionality however with the first carnivore being killed instead.
                    this.carnivores.remove(ecosystem[x][y]);
                    ((EddieCarnivore) ecosystem[xNewPosition][yNewPosition]).eat(ecosystem[x][y]);
                    ecosystem[x][y] = null;
                }
            }
            // If the second biotic factor was an herbivore...
            else if (ecosystem[xNewPosition][yNewPosition] instanceof EddieHerbivore && this.herbivores.size() > 0)
            {
                //The carnivore will eat the herbivore and take it's position, and then the herbivore will be removed from the population
                this.herbivores.remove(ecosystem[xNewPosition][yNewPosition]);
                ((EddieCarnivore) ecosystem[x][y]).eat(ecosystem[xNewPosition][yNewPosition]);
                ecosystem[xNewPosition][yNewPosition] = ecosystem[x][y];
            }
        }
        // If the first biotic factor was an herbivore, account for the possibility of meeting a carnivore and a plant
        else if (ecosystem[x][y] instanceof EddieHerbivore)
        {
            // If meeting a carnivore, herbivore should die and perform the same functionality as to when a carnivore meets a herbivore
            if (ecosystem[xNewPosition][yNewPosition] instanceof EddieCarnivore && this.herbivores.size() > 0)
            {
                this.herbivores.remove(ecosystem[x][y]); // remove from population
                ((EddieCarnivore) ecosystem[xNewPosition][yNewPosition]).eat(ecosystem[x][y]); // let the carnivore eat the herbivore
                ecosystem[x][y] = null; // set the herbivores position to null, indicating it's dissapearance from the ecosystem.
            }
            // If meeting a plant, herbivore should eat the plant and remove the plant from the ecosystem.
            else if (ecosystem[xNewPosition][yNewPosition] instanceof EddiePlant && this.plants.size() > 0)
            {
                // If herb. moves to plant
                ((EddieHerbivore) ecosystem[x][y]).eat(ecosystem[xNewPosition][yNewPosition]);
                this.plants.remove(ecosystem[xNewPosition][yNewPosition]);
                ecosystem[xNewPosition][yNewPosition] = ecosystem[x][y];
            }
        }
        // Plants only need to account for an interaction with herbivores; in this case, allow the herbivores to eat the plant and remove the plant
        // from the ecosystem and its population.
        else if (ecosystem[x][y] instanceof EddiePlant && ecosystem[xNewPosition][yNewPosition] instanceof EddieHerbivore && this.plants.size() > 0)
        {
            ((EddieHerbivore) ecosystem[xNewPosition][yNewPosition]).eat(ecosystem[x][y]);
            this.plants.remove(ecosystem[x][y]);
            ecosystem[x][y] = null;
        }
    }
    
    /**
     * Method generateNewPosition
     * 
     * Helper method that returns a new position randomly whilst influenced by the mobility of the biotic factor.
     * It will return a byte[] array with 0th index displaying the x movement and the 1st index displaying the y movement.
     * Utilising a byte data type as the new position will always be less than 128 (see EddieAnimal.MAX_MOBILITY))
     *
     * @param mobility - byte - the mobility of the EddieBiotic factor that is attempting to move to a new position.
     * @return byte[] - the new coordinates
     */
    private byte[] generateNewPosition(byte mobility)
    {
        // Generate a random number using the mobility and subtract 1 to ensure it is within the bounds to move in any direction possible
        // i.e up down left right
        // then return it as two elements in a byte array.
        return new byte[] {(byte) (Math.abs((new Random().nextInt(mobility) - 1))), (byte) Math.abs(((new Random().nextInt(mobility) - 1)))};
    }
    
    /**
     * Method moveBiotic
     * 
     * Helper method that, given a current x,y position and a new x,y position, will move the biotic factor to that new position.
     * More specifically, if the biotic is an animal, it will move to the new position and stay there; however, if it is a plant, the plant
     * will GROW to the new position but also remain in the old position.
     *
     * @param x - int - current x pos.
     * @param y - int - current y pos.
     * @param newX - int - new x pos.
     * @param newY - int - new y pos.
     */
    private void moveBiotic(int x, int y, int newX, int newY)
    {
        // if the biotic factor is an animal
        if (this.ecosystem[x][y] instanceof EddieAnimal)
        {
            // set the new position to the animal at the old position, then set the old position to null -- simulating that it has left that spot.
            this.ecosystem[newX][newY] = this.ecosystem[x][y];
            this.ecosystem[x][y] = null;
        }
        else
        {
           // If a plant, grow to the new position, remain in the old position, and increase the population of plants.
           this.ecosystem[newX][newY] = this.ecosystem[x][y];
           this.plants.add(ecosystem[x][y]);
        }
    }
    
    /**
     * Method simulateMovement
     * 
     * Helper method simulates the movement and natural progression/selection of the ecosystem in its entirety for ONE day;
     * thie method takes no parameters and utilises a sequential search with the ecosystem[][] array. It will check each position to ensure
     * that there is a biotic factor in there, if it has the energy to be willing to move, and if it is alive before generating a new position,
     * randomly moving to that position, and then evaluating any encounters (eat, be eaten, do nothing, reproduce) so that it simulates a natural
     * progression of an ecosystem for each element.
     * If the element that has been searched has no energy, it will die at that day and be removed.
     * All biotic factors will deteriorate daily; this deterioration depends on the type of animal and the overriden method for each type.
     *
     */
    private void simulateMovement() // PROGRESSION FOR 1 DAY.
    {
        // Sequential Search in a 2D array;
        for (int y = 0; y < this.height; y++)
        {
            for (int x = 0; x < this.width; x++)
            { 
                // if there is a biotic factor, and by impulsiveness/randomness, it has enough energy to move AND is willing to move, continue
                // to a decision process
                if (ecosystem[x][y] instanceof EddieBiotic && new Random().nextFloat() < (Math.abs(ecosystem[x][y].getEnergyRate()) + 0.1))
                {
                    // If the ecosystem has energy/is alive...
                    if (ecosystem[x][y].isAlive())
                    {
                        // Create a byte array that contains the new position (using byte as most efficient data type) as it works in conjunction with the
                        // generateNewPosition method that also returns a byte arr.
                        byte[] newPos;
                    
                        // if it is an animal, generate a position based off it's individual mobility.
                        if (ecosystem[x][y] instanceof EddieAnimal)
                        {
                            newPos = generateNewPosition(((EddieAnimal) ecosystem[x][y]).getMobility());
                        }
                        // however, if it is a plant, generate it based off the environments plant growth.
                        else
                        {
                            newPos = generateNewPosition(this.environment.getPlantGrowth());
                        }   
                        
                        // Then, naturally deteriorate the biotic factor based off deterioration factors from the environment.
                        ecosystem[x][y].deteriorate(this.environment);
                        
                        // Create a new position to move to.
                        int xNewPosition = x + newPos[0]; // this is the random addition of movement on top of the current position
                        int yNewPosition = y + newPos[1];
                        
                        // As long as the random movement was not to simply stay where they are currently and that it is within the ecosystem, 
                        // attempt to move and encounter other biotic factors.
                        if ((newPos[0] != 0 && xNewPosition >= 0 && xNewPosition < this.width))
                        {
                            // then, check if it can move vertically to (within bounds), if it can not, set it back to the original position that
                            // was indeed in bounds.
                            if (!(newPos[1] != 0 && yNewPosition >= 0 && yNewPosition < this.height))
                            {
                                // set it back to the old y pos.
                                yNewPosition = y;
                            }
                            
                            // If the new position is not inhabited; therefore there is nothing to encounter, simply move the biotic factor
                            // to the new pos.
                            if (ecosystem[xNewPosition][yNewPosition] == null)
                            // It is determined whether a position is un-inhabited if it is null.
                            {
                                // move biotic factor.
                                moveBiotic(x, y, xNewPosition, yNewPosition);
                            } 
                            // However, if the new position is of the same species and opposite sex (essentially what the canReproduce method does)
                            // create a child/baby.
                            else if (ecosystem[x][y].canReproduce(ecosystem[xNewPosition][yNewPosition]))
                            { 
                                // Add a new element to the ecosystem; that element being the reproduced element.
                                addNewElement(ecosystem[x][y].reproduce(ecosystem[xNewPosition][yNewPosition]));     
                            }
                            // If the two elements are different, evaluate their encounter.
                            // Difference in elements can be determined by whether they are of different classes; use Java's getClass() method to check
                            // inequality.
                            else if (ecosystem[x][y].getClass() != ecosystem[xNewPosition][yNewPosition].getClass()) 
                            {
                                // evaluate the encounter between two positions.
                                evaluateEncounter(x, y, xNewPosition, yNewPosition);
                            }
                        }
                    }
                    else
                    {
                        // if the element is not alive, kill it.
                        killElement(x, y);
                    }
                }
            }
        }
    }
    
    /**
     * Method killElement
     * 
     * Helper method identifies what type of biotic (car, herb, plant) the factor is at x,y, and kills it by setting it to null and removing it from the
     * corresponding population.
     *
     * @param x - int - the x coord. of the soon to be killed element
     * @param y - int - the y coord.
     */
    private void killElement(int x, int y)
    {
        // Determing what type of biotic factor it is and then remove it from their population.
        if (this.ecosystem[x][y] instanceof EddieCarnivore) this.carnivores.remove(ecosystem[x][y]);
        else if (this.ecosystem[x][y] instanceof EddieHerbivore) this.herbivores.remove(ecosystem[x][y]);
        else if (this.ecosystem[x][y] instanceof EddiePlant) this.plants.remove(ecosystem[x][y]);
        
        ecosystem[x][y] = null; // set its position to null to erase it from the ecosystem
    }
    
    /**
     * Method moveAndDisplay
     * 
     * Simple method that calls the toString method and then simulates one movement.
     *
     */
    public void moveAndDisplay()
    {
        System.out.println(this); // Call toString()
        this.simulateMovement();
    }
    
    /**
     * Method sortStat
     * 
     * To identify herbivores and carnivores with more clarity, method will take a userChoice, 1 for herbivores by weight, and 2 for carnivores by strength,
     * and sort them (i.e the arraylist) using an insertion sort algorithm.
     *
     * @param userChoice - byte - the user choice as to what elements they want to sort.
     */
    public void sortStat(byte userChoice) 
    {
        // INSERTION SORT :: O(n^2) :: Is used because it's main advantage is that it is significantly more efficient on sorted/partially sorted arrays; so,
        // if the user wishes to sort multiple times, each time after new elements are added, this algorithm will be very efficient.
        
        // If the user selected to sort herbivores by weight
        if (userChoice == 1)
        {
            // Create an outer loop which will iterate through each element
            for (int j = 1; j < this.herbivores.size(); j++)
            {
                // Set the key to the weight of a herbivore at index j; this will be used as a comparison value.
                short key = ((EddieHerbivore) (this.herbivores.get(j))).getWeight();
                // create the iterative variable of the inner while loop
                int i = j-1;
                
                // the inner loop starts at the current index of the outer loop and compares itself to the left neighbour; if it is smaller,
                // the elements are swapped.
                while ((i > -1) && (((EddieHerbivore) (this.herbivores.get(i))).getWeight() > key ))
                {
                    // this loop continues to move an element to the left as long as it is smaller than the element to its left.
                    this.herbivores.set(i+1, this.herbivores.get(i));
                    i--; // decrement the counter.
                }
                
                
                // the element at the current index is in its correct position in the sorted portion of the array.
                this.herbivores.set(i+1, this.herbivores.get(j));
            }
            
            // Call the helper method which prints the arraylist with a title indicating it's sorting functionality.
            printArrayList(this.herbivores, "HERBIVORES (WEIGHT)");
        }
        else if (userChoice == 2) // Carnivore by strength
        {
            // Insertion sort; the reason to not use a helper method is that it is implausible to pass in a parameter that could indicate whether to use the
            // carnivores or herbivores arraylist and whether or not to use the .getStrength() or .getWeight() method. The only way to do so is to pass in a byte value
            // which would depict the process of an if structure; however, that is redundant as this method accomplishes that exact process.
            
            // See previous insertion sort for comments and explanation.
            for (int j = 1; j < this.carnivores.size(); j++)
            {
                byte key = ((EddieCarnivore) (this.carnivores.get(j))).getStrength();
                int i = j-1;
                
                while ((i > -1) && (((EddieCarnivore) (this.carnivores.get(i))).getStrength() > key ))
                {
                    this.carnivores.set(i+1, this.carnivores.get(i));
                    i--;
                }
                
                this.carnivores.set(i+1, this.carnivores.get(j));
            }
            
            // Output the carnivores, sorted, with the title.
            printArrayList(this.carnivores, "CARNIVORES (STRENGTH)");
        }
        else
        {
            // If the user did not select 1 or 2.
            System.out.println("\nError: must enter a valid selection for a characteristic to sort.");
        }
    }
    
    /**
     * Method printArrayList
     * 
     * Simple helper method that outputs all elements in the arraylist and priorly outputs a title describing the arraylist.
     *
     * @param arr - ArrayList<EddieBiotic> - only arraylists holding EddieBiotic objects are utilised; accept that type -- represents the arraylist to be outputted.
     * @param title - String - the title of the arr list.
     */
    private void printArrayList(ArrayList<EddieBiotic> arr, String title)
    {
        System.out.println(title + ":\n========================");
        
        // Iterate through the arraylist and output each element's tostring.
        for (int i = 0; i < arr.size(); i++)
        {
            System.out.println(arr.get(i));
        }
        
        System.out.println("========================");
    }

    /**
     * Method searchBiotic
     * 
     * Utilises a sequential search to search through the ecosystem[][] until it finds an element with the same name as the searchTerm, a String parameter.
     * Then, it will return true if it is found and output the position that it is at.
     *
     * @param searchTerm - String - the desired element to be searched.
     * @return boolean 
     */
    public boolean searchBiotic(String searchTerm) 
    {
        // SEQUENTIAL SEARCH
        for (int y = 0; y < this.height; y++)
        {
            for (int x = 0; x < this.width; x++)
            {
                // if the names of the current element is the same as the search term
                if (ecosystem[x][y] instanceof EddieBiotic && ecosystem[x][y].getName().equalsIgnoreCase(searchTerm))
                {
                    // output the coordinates of the found element and return true.
                    System.out.println("\nSearched: " + searchTerm + " found at coordinates (meters): [" + x + ", " + y + "].");
                    return true;
                }
            }
        }
        
        // if the element was not found.
        return false;
    }
    
    /**
     * Method toString
     * 
     * Returns a string representation of the ecosystem grid with all the elements along with a count of each biotic type population.
     *
     * @return String
     */
    public String toString()
    {
        String result = "";
        
        // Sequential search
        for (int y = 0; y < this.height; y++)
        {
            for (int x = 0; x < this.width; x++)
            {
                // if the ecosystem is null, print an indication that that position is empty.
                if (ecosystem[x][y] == null)
                {
                    result += ".";
                }
                else
                {
                    // otherwise, print the biotic factors icon.
                    result += ecosystem[x][y].boardDisplay();
                }
            }
            
            result += "\n";
        }
        
        // Add the population of all the types of biotic factors at the end.
        result += "CARNIVORES: " + this.carnivores.size() + "      HERBIVORES: " + this.herbivores.size() + "        PLANTS: " + this.plants.size();

        return result; // return String representation.
    }
    
    // END OF PROGRAM
}
