// Importing libraries
import java.util.Scanner;
import java.lang.Thread;
import java.io.*;

/**
 * EddieSimulation
 * 
 * Sets up the ecosystem simulation by simulating all features, properties, and processes that a ecosystem naturally progresses for. Class allows users specific tools
 * using EddieEcosystem, EddieBiotic/Abiotic (and all subclasses), and the ADT's (linkedlist, stack) such as sorting, searching, writing to a file, reading from a file,
 * undoing days, controlling populations, and receiving quantitative information
 * 
 *
 * @author (Eddie Gao)
 * @version (01/19/2023)
 */
public class EddieSimulation
{
    // Step 1. Declare and initialise variables
    // ALL VARIABLES SHOULD NOT BE CHANGED UNLESS GIVEN CONFIRMATION
    // FROM THE USER/ADMINISTRATOR -- THUS, THEY ARE private and ENCAPSULATED.
    
    // STATIC variable representing the number of times the simulation has taken place.
    private static int trial = 0;
    
    // An EddieEcosystem object representing the ecosystem.
    private EddieEcosystem ecosystem;
    
    // A stack which depicts the days that have elapsed; each node has a version of the ecosystem for each day.
    // For e.g, one EddieNode in the stack will represent one day with a cargo of an EddieEcosystem object.
    // Using stack adt to be able to undo days using FIFO PRINCIPLE.
    private EddieStack daysElapsed;
    
    // DECLARING ALL CONSTANTS
    
    // an array of Strings that holds the file name of all files that will be written to or read from.
    public static final String[] ALL_FILE_NAMES = {"carnivores.txt", "herbivores.txt", "plants.txt", "viruses.txt", "environments.txt"};
    
    // GETTERS
    public EddieEcosystem getEcosystem() { return this.ecosystem; } // return the ecosystem
    public int getDays() { return this.daysElapsed.size(); } // return the no. days that have passed
    public int getTrials() { return trial; } // return the number of trials that have occurred.
    
    /**
     * EddieSimulation Constructor
     * 
     * Default constructor accepts no param. and sets all instance vars to a default logical value whilst incrementing the trial count.
     *
     */
    public EddieSimulation()
    {
        // Instantiate new ecosystem with default ecosystem sizes for x and y
        this.ecosystem = new EddieEcosystem(EddieEcosystem.DEFAULT_ECOSYSTEM_SIZE[0], EddieEcosystem.DEFAULT_ECOSYSTEM_SIZE[1]);
        // Instantiate a new EddieStack adt object.
        this.daysElapsed = new EddieStack();
        
        trial++; // increment the trial num
    }
    
    private void run(int days)
    {        
        // A for loop to run the ecosystem's simulation for a no. days specified by the user.
        for (int i = 0; i < days; i++) // int data type as there could be more than the short max range num. days that the user would like to run the simulation for.
        {
            try
            {
                // Create a buffer time so that it simulates a progression visually.
                Thread.sleep(500);
                
                // As long as there is a sufficient peek at the current iteration of the ecosystem:
                if (this.daysElapsed.peek().sufficientPopulation())
                {
                    // display the current ecosystem and simulate its movement
                    this.daysElapsed.peek().moveAndDisplay();
                    // then push a shallowed copy head as the new head, we use an overloaded constructor that takes an EddieEcosystem object so that
                    // a shallow copy of the current ecosystem can be pushed which will create a new EddieEcosystem in the stack.
                    // This 
                    daysElapsed.push(new EddieEcosystem(this.ecosystem)); 
                }
                else
                {
                    // Elsewise if the ecosystem does not have a sufficient pop. it cannot thrive; so, end the program abruptly.
                    System.out.println("\nThe ecosystem does not have a sufficient population of herbivores, carnivores, or plants to prosper; the program will end.");
                    return;
                }
            }
            catch (InterruptedException ie)
            {
                // Catch an interrupted exception.
                ie.printStackTrace();
            }
        }   
        
        // Then, check if the population after the final movement of simulation allows for a sufficient population.
        if (this.daysElapsed.peek().sufficientPopulation())
        {
            // Prompt the continueEcosystem() method so that the user can continue with functionality before the ecosystem is progressed again.
            continueEcosystem();
            // Recursively run the program again and prompt the user to select the amount of days to run again.
            run(selectDays());
        }
    }
    
    /**
     * Method selectDimensions
     * 
     * Helper method that is utilised for the user to select a dimension value for a specific coordinate, given the parameter 'coordinate'.
     * Further, try-catch implementation ensures that the coordinate is a positive integer value.
     * 
     * Dimension is then returned.
     *
     * @param coordinate - char - the coordinate to enter a dimension for.
     * @return - int - the dimension vlalue.
     */
    private int selectDimensions(char coordinate)
    {
        // Initialising local variables
        int coord; // coord represents a numeric digit which specifies a dimension; it is of integer data type because realistically, ecosystems
        // can expand farther beyond the short maximum range (~32,000) in meters but not below the Integer.MAX_VALUE.
       
        do
        {
            try
            {
                // Prompt the user to enter the dimension for the given coordinate in meters.
                System.out.println("\nEnter the " + coordinate + " dimension for the ecosystem (in meters):\n>>> ");
                
                // Any valid positive integer value is a sufficient dimension; so, if that is the case, record the dimension.
                if ((coord = new Scanner(System.in).nextInt()) > 0)
                {
                    System.out.println("\nDimension recorded.");
                }
                else // Error check: if the user entered a negative numeric value as a dimension, allow them to try again.
                {
                    System.out.println("\nMust enter a positive value as a dimension -- try again.");
                }
            }
            catch (Exception e) // If the user did not enter an int...
            {
                System.out.println("\nMust enter a numeric natural integer value as a dimension -- try again.");
                coord = 0; // Set the coord to 0 for clarity as to how to continue the while loop.
            }
        }
        while (coord <= 0);
        
        return coord; // return the coordinate.
    }
    
    /**
     * Method selectDays
     * 
     * Helper method allows user to select an amount of days to run the ecosystem for. 
     *
     * @return int - the num. days to run the ecosystem for.
     */
    private int selectDays()
    {
        // Initialise local variables.
        int days;
        // Integer type to allow for the most flexibility possible; it is entirely plausible if a user would like to simulate an ecosystem for hundreds of thousands
        // of days or more.
        
        do
        {
            try
            {
                // Output how many days the ecosystem should be simulated as a numeric positive value.
                System.out.println("\nHow many days would you like to simulate the ecosystem?\n>>> ");
                
                // Logic check: If the user did not enter a positive amount of days, output an error message allowing them to try again.
                if (!((days = new Scanner(System.in).nextInt()) > 0))
                {
                    System.out.println("\nMust enter a numeric positive value determining the no. days to simulate -- try again.");
                }
            }
            // If the user did not enter an Integer value.
            catch (Exception e)
            {
                System.out.println("\nMust enter a numeric integer value for the no. days to simulate -- try again.");
                days = 0;
            }
        }
        while (days < 1);
        
        // return days.
        return days;
    }
    
    /**
     * Method continueEcosystem
     * 
     * Helper method prompts the menu; a list of processes and functionalities that the user can decide on that relate to the ecosystem; this method is only called after
     * the first iteration of the ecosystem's progression as certain functionality will only logically work after at least one day of the simulation has occurred.
     * The method will continue to run until the user chooses to continue with the simulation.
     *
     */
    private void continueEcosystem()
    {
        // Initialise local variable
        
        // byte value type for efficiency, menu ranges from 1-5.
        byte userChoice, sortSelection;;
        
        do
        {
            try
            {
                // MENU OUTPUT w/ the amount of days that have surpassed with the size of the stak
                System.out.println("\n(DAYS ELAPSED: " + (Math.max(this.daysElapsed.size()-1, 0)) + ")\n\nMENU\n====================================\n (1) ADD A BIOTIC FACTOR\n (2) SORT ANIMALS\n (3) SEARCH FOR NEAREST FACTOR"
                + "\n (4) UNDO TO PREVIOUS DAY\n (5) CONTINUE WITH SIMULATION\n====================================\n\n>>> ");
                
                // input a byte value representing user choice
                userChoice = new Scanner(System.in).nextByte();
                
                if (userChoice == 1) // If user desires to add a biotic factor.
                {
                    addFactor();
                }
                else if (userChoice == 2) // If user desires to sort animals
                {
                    try
                    {
                        // Prompt user to select a characteristic to sort:
                        System.out.println("Select a characteristic to sort by:\n\n (1) Weight (Herbivores)\n (2) Strength (Carnivores)\n\n>>> "); 
                        
                        // Take input
                        sortSelection = new Scanner(System.in).nextByte();

                        // Logic check: ensure that the user makes a valid choice, that being 1 or 2; then, pass that selection into the sortStat method
                        // which will sort the correct arraylist.
                        if (sortSelection == 1 || sortSelection == 2) this.ecosystem.sortStat(sortSelection);
                        else System.out.println("\nMust enter a valid choice.");
                    }
                    // Error check: ensure that there is a numeric byte value
                    catch (Exception e)
                    {
                        System.out.println("\nMust enter a numeric value determining the selection of characteristics to sort by -- try again.");
                        sortSelection = 0;
                    }
                }
                // If the user chooses to search for an element in the eco.
                else if (userChoice == 3)
                {
                    // Prompt user to select a factor to be searched
                    System.out.println("\nEnter the name of the factor to be searched:\n>>> ");
                    
                    // the searchBiotic factor will return a boolean; so, if the method returns false; meaning, it did not find the element -- indicate to the user
                    // by outputting a message saying it was not in the eco.
                    if (!(this.ecosystem.searchBiotic(new Scanner(System.in).nextLine()))) // pass into the method a Scanner input
                    {
                        System.out.println("\nThe searched factor is not present in the ecosystem.");
                    }
                }
                // Undoing a day -- requires popping the stack to the previous day; essentially setting the head node to the previous node.
                else if (userChoice == 4)
                {
                    // Indicate to the user that the day will reverse to the previous one.
                    System.out.println("\nUndoing simulation progress to previous day...");
                    
                    // Remove the head node.
                    this.daysElapsed.pop();
                }
                // If the user wants to continue the simulation; ergo, they want to quit from the menu, simply output that the simulation will proceed.
                else if (userChoice == 5)
                {
                    // Output msg.
                    System.out.println("\nProceeding with simulation...");
                }
                // Logic check: user must enter a value between 1-5;
                else
                {
                    System.out.println("\nMust enter a valid selection -- try again.");
                }
            }
            // if user did not enter a byte value.
            catch (Exception e)
            {
                System.out.println("\nError -- try again.");
                userChoice = 0;
            }
        }
        // As long as user selects NOT to continue with sim, rerun the menu.
        while (userChoice != 5);
    }
    
    private void promptCreation()
    {
        // INITIALISE and DECLARE local variables.
        
        // userChoice as a byte because the user will only select 1 or 2 in order to progress -- using most efficient data type.
        byte userChoice; 
    
        do
        {
            try
            {
                // Menu
                System.out.println("\nMENU\n====================================\n (1) ADD A BIOTIC FACTOR\n (2) FINISH ECOSYSTEM CREATION\n"
                + "====================================\n\n>>> ");
                userChoice = new Scanner(System.in).nextByte();
                
                if (userChoice == 1)
                {
                    // BIOTIC FACTORS description
                    System.out.println("\nBIOTIC FACTORS: Organisms, Animals, and Plants can be introduced into the ecosystem." +
                        "\nCertain combinations will yield a thriving ecosystem; however, other variations may lead to invasive occurences.");
                        
                    addFactor();
                }   
                else if (userChoice == 2)
                {
                    // A simple output to show progression from the default menu.
                    System.out.println("\nAdvancing...");
                }
                else
                {
                    System.out.println("\nMust enter a value of either 1 or 2 to continue -- try again.");
                }
            }
            catch (Exception e)
            {
                System.out.println("\nMust enter a numeric whole value to continue -- try again.");
                userChoice = 0;
            }
        }
        while (userChoice != 2); // Unless the user has not desired to finish ecosystem creation, allow them to keep adding different biotic factors
        // by looping through the menu user input structure.
        
        //System.out.println("\nDays: " + days);
        //return days; // return the no. days to run the ecosystem
    }
    
    /**
     * Method enterEnvironment
     * 
     * Simple helper method that allows users to enter a desired environment by taking a String input; searching through a file of EddieEnvironment objects and matching
     * the right object to the search term. Then, set the environment to the searched and returned environment.
     * If the desired environment was not found in the file, prompt the user to write a new environment to the file.
     *
     */
    private void enterEnvironment()
    {
        // Initialise local variables
        String desiredEnvironment; // represents the desired environment the user wants, this will be a key to compare to the toString of all EddieEnvironment objects
        // in the environments.txt file.
        EddieEnvironment env; // this will be the EddieEnvironment object that the ecosystem sets its environment to.
        
        // Prompt user to enter an environment name
        System.out.println("\nProvide an environment for the ecosystem to inhabit (e.g desert, tropical, arctic):\n>>> ");
        // Take their input.
        desiredEnvironment = new Scanner(System.in).nextLine().toLowerCase();
        
        // Firstly, declare the env variable to the EddieBiotic object (casted as EddieEnvironment) that is returned from the searchFile() method
        // if the method did not find the object corresponding to the key, it will return null; thus, check if that is the case and then prompt the user
        // to write to the file an EddieEnvironment object and set that newly documented environment to the env variable to set as the ecosystem's environment.
        if ((env = (EddieEnvironment) (searchFile(ALL_FILE_NAMES[4], desiredEnvironment))) == null)
        // ALL_FILE_NAMES[4] = environments.txt
        {
            // Prompt user to write an environment to the file,
            System.out.println("\n" + desiredEnvironment.toUpperCase() + " is not documented; please record it manually:");
            
            // 4 represents the file index that should be written to; that index being environments.txt.
            env = (EddieEnvironment) writeFile((byte) 4, desiredEnvironment);
        }
        
        // Set the ecosystem's environment to the newly read/written EddieEnvironment object.
        this.ecosystem.setEnvironment(env);
    }
    
    /**
     * Method addFactor
     * 
     * Helper method provides the complete functionality to ADD a biotic factor, specified by the user, to the simulation.
     * The type of element, the name of the element, the desired population to enter, will be received via user IO and such element will be added to the ecosystem. If the ecosystem has not been documented, i.e,
     * it is not an object in the text files in ALL_FILE_NAMES, the user will construct the object themselves and write the object to the corresponding file.
     *
     */
    private void addFactor()
    {
        // Initialise local variables
        EddieBiotic factor; // the EddieBiotic factor that will be added to the ecosystem
        byte userChoice; // the choice of either 1-4 that the user will choose that corresponds to the type of element the user would like to implement into the ecosystem
        int desiredPopulation; // the desired population of said element
        String name = ""; // the name of the factor.
        
        do
        {
            try
            {
                // Prompt the user to enter a factor to implement
                System.out.println("\nSelect a factor to implement:\n===================================\n" + 
                    " (1) Carnivore\n (2) Herbivore\n (3) Plant\n (4) Virus\n===================================\n>>> ");
                
                // Store the user input in a variable, userChoice
                userChoice = new Scanner(System.in).nextByte();
                
                // So long as the userChoice is a valid choice (1-4), all elements have a name; so, prompt the user to enter a name.
                if (userChoice > 0 && userChoice < 5)
                {
                    // Prompting user to enter a name and store it.
                    System.out.println("\nEnter the species name of the factor:\n>>> ");
                    name = new Scanner(System.in).nextLine().toLowerCase(); // .toLowerCase() because all Strings in text files are lower case.
                        
                    // VIRUS ONLY
                    // Becaue viruses are not biotic factors whereas carnivores, herbivores, and plants are, it is desired to seclude it from the general structure of adding an element to the ecosystem.
                    if (userChoice == 4) 
                    {
                        // Instead, the searched EddieVirus will be added to the ecosystem via the introduceVirus(EddieVirus v) method.
                        this.ecosystem.introduceVirus((EddieVirus) searchFile(ALL_FILE_NAMES[userChoice-1], name));
                    }
                    else // BIOTIC FACTORS ONLY
                    {
                        // Firstly, declare the factor variable to the EddieBiotic object that is returned from the searchFile() method
                        // if the method did not find the object corresponding to the key, it will return null; thus, check if that is the case and then prompt the user
                        // to write to the file an EddieBiotic object and set that newly documented factor to the factor variable to be added to the ecosystem.
                        if ((factor = (EddieBiotic) searchFile(ALL_FILE_NAMES[userChoice-1], name)) == null)
                        {
                            System.out.println("\nBiotic factor not documented; please record it manually.");
                            // set factor to a new object of that type and then construct it, then write it.
                            factor = (EddieBiotic) writeFile((byte) (userChoice - 1), name); // Must be userChoice - 1 because, arrays start at 0 index whereas humans count from 1; account for this
                            // difference in interpretation.
                        }
                        
                        // Once the factor is successfully searched and found or manually constructed and written: prompt the user to enter the population of such element to add to the ecosystem
                        do
                        {
                            try
                            {
                                // Prompt the user to enter the population 
                                System.out.println("\nEnter the " + name + " population of that will enter the ecosystem:\n>>> ");
                                
                                // In this if condition, set the desiredPopulation to a scanner that takes an int (because a user can specify to add more than the short max.range but realistically less than
                                // the long min range. Then, check if the desiredPopulation is NOT a valid pop. size; ergo, if it is less than 1.
                                // If so, prompt the user to enter a new population by continuing the loop.
                                if (!((desiredPopulation = new Scanner(System.in).nextInt()) >= 1))
                                {
                                    System.out.println("\nMust enter a positive number determining the population -- try again.");
                                }
                            }
                            // Error check: if the user did not enter an int
                            catch (Exception e)
                            {
                                // Reset the desired population and notify the user of the error.
                                System.out.println("Must enter a numeric integer value determining the population of the " + name + " species -- try again.");
                                desiredPopulation = 0;
                            }
                        }
                        while (desiredPopulation < 1);
                        
                        // Finally, use the desiredPopulation to continually loop through to that desired number and add the factor to the ecosystem with every iteration.
                        for (int i = 0; i < desiredPopulation; i++)
                        {
                            this.ecosystem.addNewElement(factor);
                        }
                    }
                }
                // If the user did not enter a valid selection between 1 - 4
                else
                {
                    System.out.println("\nMust enter a valid selection -- try again.");
                }
            }
            catch (Exception e)
            {
                System.out.println("\nMust enter a byte value indicating the choice -- try again.");
                userChoice = 0;
            }
        }
        while (userChoice < 1 && userChoice > 4);
        // Essnetially, while the user has NOT made a valid choice for what element to add, continue the loop.
        
    }
    
    /**
     * Method searchFile
     * 
     * Helper method that serves a generic purpose: to search a given file for a given searchTerm and return whatever object contains that searchTerm in its toString() method.
     * The method will return null if the object was not found, this method will search through every line of the file until an object has been returned or until it has reached the end.
     *
     * @param fileName - String - the name of the file to be searched through
     * @param searchTerm - String - the term that is to be searched for in the file and more specifically the object's toString()
     * @return Object - the object that is found that contains the searchTerm
     */
    private Object searchFile(String fileName, String searchTerm)
    {
        // Declare a new ObjectInputStream and set it to 
        // search for a file in the location: fileName.
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName)))
        {
            // Priorly set the object to an empty String object so that it can be initialised beforehand.
            Object object = "";
            
            // Keep looping through the file until the object is null.
            while ((object = (Object) ois.readObject()) != null)
            {
                // Check if the object.toString contains the search term; if it does, return the object and stop the loop in doing so; otherwise, keep looping,
                if (object.toString().toLowerCase().contains(searchTerm.toLowerCase()))
                {
                    return object;
                }
            }
        }
        // try catch blocks for errors that will simply print the stack trace.
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        
        // Footer return statement if the entire loop finished without the object being returned meaning the desired object was not found
        return null;
    }
    
    /**
     * Method enterByteValue
     * 
     * A simple helper method for when it is desired that a user enters a byte value. Method will ensure the user enters a valid positive byte value and will return that value.
     * Ability to specify what exactly the value represents through the parameter, specification.
     *
     * @param specification String - represents what the value should be or what it is for.
     * @return byte
     */
    private byte enterByteValue(String specification)
    {
        // Initialise local variables
        byte value; // this is the value the user will input and return.
        
        // Allow user to enter a valid byte
        do
        {
            try
            {
                // Prompt user to enter a byte value in subjectivity to the specification
                System.out.println("\nEnter the " + specification + ":\n>>> ");
                value = (byte) (Math.abs(new Scanner(System.in).nextByte())); // set the value to an inputted byte that is absoluted to prevent negative numbers
            }
            catch (Exception e)
            // If the user did not enter a byte, display the error and prompt them to try again,
            {
                value = 0;
                System.out.println("\nMust enter a numeric byte value representing the " + specification + " -- try again.");
            }
        }
        while (value < 1); // while the entered value is not positive, loop again
        
        // return the value.
        return value;
    }
    
    /**
     * Method writeFile
     * 
     * Helper method provides the generic functionality of writing an object of any type to a specified file. Depending on what file the user desires to write to (depending on the index of the 
     * ALL_FILE_NAMES constant array), the method provides to the user the ability to enter all notable instance variables of the type of object that relates to the desired file.
     * The method then returns the object.
     *
     * @param fileIndex - byte - the index of the ALL_FILE_NAMES array which depicts which file to write to.
     * @param name - String - the name of the object that is to be written to a file
     * @return Object - the newly constructed and written object
     */
    private Object writeFile(byte fileIndex, String name)
    {
        // Declare an object to an empty String object to initialise it.
        Object object = "";
        
        try
        {
            // Create a new ObjectOutputStream set to the file name at the fileIndex in ALL_FILE_NAMES
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(ALL_FILE_NAMES[fileIndex]));
            
            // All biotic factors share similar instance variables; for efficiency, allow all biotic factors to follow the same functionality and use nested if structures to separate them into specific
            // types of biotic factors
            if (fileIndex >= 0 && fileIndex <= 2) // BIOTIC FACTORS
            {
                // GENERIC SETTINGS
                byte foodValue; // most efficient data type as the food value will never exceed 100.
                String icon; // the icon of the object since all biotic factors are displayed on the grid.
                
                // set the foodValue to a byte val. that is entered by the user with the specification that it is for the food value.
                foodValue = enterByteValue("food value");
                
                // Prompt the user to enter an icon
                System.out.println("\nEnter an icon to be displayed for the " + name + ";a list of icons can be retrieved here:\n"
                    + "github.com/wooorm/gemoji/blob/main/support.md\n\n>>>");
                    
                icon = new Scanner(System.in).nextLine();

                // More specifically, if the user desires to write an EddieAnimal, regardless of if it is an EddieHerbivore or EddieCarnivore, prompt the user to enter the mobility and weight
                // as all EddieAnimals share those instance vars.
                if (fileIndex == 0 || fileIndex == 1) // ANIMALS
                {
                    // GENERIC SETTINGS
                    byte mobility; // efficient data types that match the data types found in the EddieAnimal class.
                    short weight;
                    
                    // allow user to enter a mobility stat
                    mobility = enterByteValue("mobility stat");
                    
                    // Because weight is a short data type, it is unwise to use the enterByteValue helper method; it is also unnecessary to create a enterShortValue method
                    // as this is the only instance in the program where a short value is needed.
                    
                    // So; simply allow the user to enter a weight,
                    do
                    {
                        try
                        {
                            // Prompt user io
                            System.out.println("\nEnter the weight (lbs):\n>>> ");
                            weight = (short) Math.abs(new Scanner(System.in).nextShort()); // Absolute value the short so that it is a positive number regardless of if the user enters a negative val or not.
                        }
                        catch (Exception e)
                        {
                            // Error check if the user did not enter a short.
                            System.out.println("\nMust enter a numeric whole value representing the mobility of the animal -- try again.");
                            weight = 0;
                        }
                    }
                    while (weight < 1);
                    
                    // Carnivores have specific instance variables, account for those instance vars.
                    if (fileIndex == 0) // carnivore only
                    {
                        // Prompt the user to enter the strength value of the EddieCarnivore
                        byte strength;
                        strength = enterByteValue("strength stat.");
                        // construct an EddieCarnivore object with all entered instance variables.
                        object = new EddieCarnivore(name, icon, foodValue, mobility, weight, strength);
                    }
                    else // herbivore only
                    {
                        // construct an EddieHerbivore object with all entered instance variables.
                        object = new EddieHerbivore(name, icon, foodValue, mobility, weight);
                    }
                }
                else // PLANTS
                {
                    // construct an EddiePlant object with all entered instance variables.
                    object = new EddiePlant(name, icon, foodValue);
                }
            }
            else if (fileIndex == 3 || fileIndex == 4)
            {
                // GENERIC SETTINGS
                byte energyImpact;
                
                energyImpact = enterByteValue("energy impact value");
                
                if (fileIndex == 3) // virus only
                {
                    byte chanceToSpread;
                    chanceToSpread = enterByteValue("chance to spread stat.");
                    
                    object = new EddieVirus(name, energyImpact, chanceToSpread);
                }
                else // environment only
                {
                    byte plantGrowth;
                    float temp = 0.0f;
                    boolean validTemperature;
                    
                    do
                    {
                        try
                        {
                            System.out.println("\nEnter the average temperature (celsius) of the " + name + ":\n>>> ");
                            temp = new Scanner(System.in).nextFloat();
                            validTemperature = true;
                        }
                        catch (Exception e)
                        {
                            System.out.println("\nMust enter a numeric value representing the temperature of the environment -- try again.");
                            validTemperature = false;
                        }
                    }
                    while (!validTemperature);
                    
                    plantGrowth = enterByteValue("plant growth value");
                    
                    // construct an EddieEnvironment object with all entered instance variables.
                    object = new EddieEnvironment(name, energyImpact, temp, plantGrowth);
                }
            }
            else
            {
                System.out.println("\nError: the file was not found.");
                return null;
            }
            
            // Since the object has successfully been constructed to either an EddieBiotic or EddieAbiotic object, now write it to the file that it is desired to be located at.
            out.writeObject(object);
            // Output to the user that the new factor has now been documented and added to the ecosystem.
            System.out.println("\nNew factor successfully documented.");
            
            // Close the ObjectOutputStream
            out.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        
        // return the object to be added to the EddieEcosystem grid.
        return object;
    }
    
    /**
     * Method runSimulation
     * 
     * Initiates a full run of the ecosystem simulation.
     *
     */
    public void runSimulation()
    {
        // Provide a header title and description.
        System.out.println("╔════════════════════════════════════╗\n"
                + "║░░▒▒▒▓▓▓  ECOSYSTEM SIMULATOR  ▓▓▓▒▒▒░░║\n" + "║    Intended for experimental use only    ║\n"
                + "║      Auth: Eddie Gao :: Version 1.0      ║\n"
                + "║░░░░░░░░▒▒▒▒▒▒▓▓▓▓▓▓▓▓▒▒▒▒▒▒░░░░░░░░║\n╚════════════════════════════════════╝\n");
        // Initialising local variables
        boolean continueProgram; // true if the program will continue to run; false if the user quits or if the ecosystem can no longer thrive.
        byte userReady; // a prior check to ensure that the user is ready to start the sim. Byte type as user can only validly choose 1, 2, or 3.
        char defaultChoice; // a char (y/n) that will depict whether the user wants to select default settings for the ecosystem or not.
        
        do
        {
            // Firstly, automatically set the continueProgram to true which allows the simulation to run indefinetly until a specific quitting condition is met.
            continueProgram = true;
            
            try
            {
                // Prompt a welcome screen to the user along with the trial number. Then, prompt a menu as to what the user would like to do.         
                System.out.println("Welcome to the Ecosystem Simulator (Trial: " + 1 + ") Are you ready to begin?"
                +"\n╔════════════════╗\n║ (1) Begin Sim.    ║\n╚════════════════╝\n╔════════════════╗\n║ (2) Details       ║"
                +"\n╚════════════════╝\n╔════════════════╗\n║ (3) Quit Program  ║\n╚════════════════╝\n>>> ");        
                
                // Then, take a byte value inputted to decide what functionality to perform
                userReady = new Scanner(System.in).nextByte();
            
                // If the user selects to begin the ecosystem
                if (userReady == 1)
                { 
                    // Initiate the head of the stack, ergo, set the first day to the current ecosystem instance var.
                    this.daysElapsed.push(this.ecosystem);
                    // Prompt the user to enter either the default dimension values OR specified values that they will select.
                    System.out.println("\nThe ecosystem requires dimensions; by default they are " + EddieEcosystem.DEFAULT_ECOSYSTEM_SIZE[0] + "m by " + EddieEcosystem.DEFAULT_ECOSYSTEM_SIZE[1] + "m.");
                    
                    // Do while loop so that the user cannot continue unless they choose a dimension setting.
                    do
                    {
                        // Prompt the user to select whether they would like to enter default settings or continue with specified values.
                        System.out.println("\nWould you like to continue with default settings (" + EddieEcosystem.DEFAULT_ECOSYSTEM_SIZE[0] + ", " + EddieEcosystem.DEFAULT_ECOSYSTEM_SIZE[1] + ")? Enter Y/N:\n>>> ");
                    
                        // Take a nextLine (String) BUT set the defaultChoice to ONLY the first character.
                        // This allows users more convenience because "n", "no", or "nope" will all communicate the same decision to the program.
                        defaultChoice = Character.toLowerCase(new Scanner(System.in).nextLine().charAt(0));
                    }
                    while (defaultChoice != 'n' && defaultChoice != 'y');
                    
                    // If the user desires to enter their own dimensions, construct new dimensions and a new grid with the inputted values from the user.
                    if (Character.toLowerCase(defaultChoice) == 'n')
                    {
                        // Enter a x dimension and a y dimension.
                        this.ecosystem.constructNewDimensions(selectDimensions('x'), selectDimensions('y'));
                    }
                    
                    // Then, call the enterEnvironment() which will allow the user to enter an environment.
                    enterEnvironment();
                    // Allow the user to fully CREATE the ecosystem.
                    promptCreation();
                        
                    // Then run the ecosystem for an inputted amount of days from the user. In theory, unless the ecosystem dies or the user desires to leave, the ecosystem will run forever as the run
                    // method runs recursively with a base case of the ecosystem dying.
                    run(selectDays());
                    
                    // So, after the base case is reached, meaning the ecosystem has dead, ensure that the program will no longer continue.
                    continueProgram = false;
                    
                    // Write a summary as to what had occurred in the ecosystem simulation and write it to a file.
                    writeReport();
                }
                else if (userReady == 2) // details
                {
                    details();
                }
                else if (userReady == 3) // quit
                {
                    System.out.println("\nExiting program...");
                    continueProgram = false; // break from the loop
                }
                else
                {
                    // Logic check: ensure only 1, 2 or 3 is entered.
                    System.out.println("\nMust enter 1, 2, or 3 to continue -- try again.");
                }
            }
            // Error check: ensure the user enters a valid selection of 1, 2, or 3.
            catch (Exception e)
            {
               System.out.println("\nError: must enter a numeric byte value indicating your selection -- try again.");
            }
        }
        while (continueProgram);
        
        // Footer statement
        System.out.println("╔════════════════════════════════════╗\n"
        + "║░░▒▒▒▒ THANK YOU FOR PARTICIPATING ▒▒▒▒░░║\n" + "║░░ A report has been generated to your  ░░║\n"
        + "║░░▒   local directory as a text file   ▒░░║\n"
        + "║░░░░░░░░▒▒▒▒▒▒▓▓▓▓▓▓▓▓▒▒▒▒▒▒░░░░░░░░║\n╚════════════════════════════════════╝\n\n[ Contact the creator: egao1@ocdsb.ca ]");
    }
    
    /**
     * Method details
     * 
     * Helper method displays the details of the program
     *
     */
    private void details()
    {
        System.out.println("╔════════════════════════════════════╗\n"
            + "║░░░░▒▒▒▒▓▓▓     DETAILS    ▓▓▓▒▒▒▒░░░░║\n╚════════════════════════════════════╝\n"
            + "\n\nThe following simulation program is intended for anyone to simulate the progression of an ecosystem."
            + "\nUsers are prompted to implemenet an environment, dimensions, and any abiotic/biotic factors."
            + "\nProgression will occur in the unit of days; thus, selection of an amount of days will display how the ecosystem interacts."
            + "\n\nTools allow the user to perform specific functions; sorting animals, searching for a specific element, undoing days, etc."
            + "\n\nThe ecosystem will not continue to progress if there is not a sufficient population of herbivores, carnivores, and plants."
            + "\nThis simulation follows a multivariate dependency where carnivores eat herbivores, herbivores eat plants, and plants receive energy from the environment"
            + "\nAt the end of the simulation, a report will summarise the ecosystem simulation and write it to a .txt file: report.txt.\n\n");
    }
    
    /**
     * Method writeReport
     * 
     * Helper method will write to a file, report.txt a summary of the ecosystem simulation with the amount of days it progressed and the trial number.
     *
     */
    private void writeReport()
    {
        try
        {
            // Create a new FileWriter that creates a new file, report.txt
            FileWriter writer = new FileWriter("report.txt");
            // Write to that file a brief summary of the ecosystem simulation
            writer.write("ECOSYSTEM SIMULATION (TRIAL " + trial + ")\nThe ecosystem had succesfully run for " + this.daysElapsed.size() + " before losing a sufficient population.");
            // Close the writer
            writer.close();
        }
        // Catch any IOExceptions and print the stack trace.
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}

// END OF PROGRAM