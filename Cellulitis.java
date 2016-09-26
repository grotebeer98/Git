/* Cellulitis
 * by Menno Hofste
  and Joost Roordink
 */

import java.util.*;


/* the general idea of how the program works is that it asks for the input
 * and then prints a line for the first generation
 * then calcualtes to next generation and prints the one
 * and so on until we've reached the needed amount of generations
 */
public class Cellulitis {
    boolean[] currentGeneration;
    Scanner sc = new Scanner(System.in);
    
    
    String type; //whether we have automaton A, B or the universal one
    int length; //length of automaton
    int numGen; //number of generations it has to run
    
    
    //ints to store whether a rule is active or not (for universal automaton)
    int rule1;
    int rule2;
    int rule3;
    int rule4;
    int rule5;
    int rule6;
    int rule7;
    int rule8;
    
    
    
    //for the first input line (type, length, generations)
    public void readGeneral() {
      
        type = sc.next();
        length = sc.nextInt();
        numGen = sc.nextInt();
        
        //Making boolean 2 longer so first and last element can always be false
        currentGeneration = new boolean[length + 2]; 
        
        for(int i = 0; i < currentGeneration.length; i++) { //Set every element to false at the start
            currentGeneration[i] = false;
        }
        
        readInitial(); //go to the second line
       
    }
    
    //read the second input line
    public void readInitial() {
       
        String init = sc.next(); //not needed to actually save this, but needed to get the input window up
        //and we do not need the text
        
        
        //if an int has been given, set the corresponding place to being alive
        //and then check if another int has been given
        while(sc.hasNextInt()) {
            int next = sc.nextInt();
            if(next <= currentGeneration.length - 1) {
                currentGeneration[next] = true;
            }
        }
        
        
        //either go to the third input line if universal
        //or set the rules to the right preset if not
        if(type.equals("U")) { //ask for input for rules
            readRules();
        } else if(type.equals("A")) { //set the rules for A
            rule1 = 0;
            rule2 = 1;
            rule3 = 0;
            rule4 = 1;
            rule5 = 1;
            rule6 = 1;
            rule7 = 1;
            rule8 = 0;
        } else { //set the rules for B
            rule1 = 0;
            rule2 = 1;
            rule3 = 1;
            rule4 = 0;
            rule5 = 1;
            rule6 = 0;
            rule7 = 1;
            rule8 = 0;
        }
      
    }
    
    
    public void readRules() {
        String init = sc.next(); //this takes the last string(init_end) out of the scanner and brings
        //up the next input otherwise nexInt() gets either no input or a type mismatch
        
    
        rule1 = sc.nextInt();
        rule2 = sc.nextInt();
        rule3 = sc.nextInt();
        rule4 = sc.nextInt();
        rule5 = sc.nextInt();
        rule6 = sc.nextInt();
        rule7 = sc.nextInt();
        rule8 = sc.nextInt();
     
    }
    
    boolean newCellValueByA(int k) {
    
        
        
        
        /* this could be done by calling newCellValueByRules directly
         * but the assignment says we have to define and use this method
         * so we've got no code that's not as efficient and messier than it could be
         * (we asked whether we could leave these methods out, 
         * the answer was that we should do it like this)
         */
        return newCellValueByRules(k);
        
      
    }
    
    boolean newCellValueByB(int k) {
      
        
        
        //see newCellValueByA, same story
        return newCellValueByRules(k);
        
    
    }
    
    boolean newCellValueByRules(int k){
       
        
        
        /* the patterns for the rules, each one corresponds to a rule above
         * these patterns mean that of the neighbourhood of a cell is equal to the pattern
         * the ceell becomes alive in the next generation, and otherwise dead
         * 
         * commented letter behind are rules applying to that scenarios ruleset
         * in other words: the given rules for A and B come down to those neighbourhouds resulting in true
         */
        String p1 = "000";
        String p2 = "001"; //A,B
        String p3 = "010"; //B
        String p4 = "011"; //A
        String p5 = "100"; //A,B
        String p6 = "101"; //A
        String p7 = "110"; //A,B
        String p8 = "111";
        
        
        
        //figure what the neighbours are
        String neighbourhood = ""; //empty string
        if(currentGeneration[k - 1]){ //if left neighbour alive, add 1 else 0 to the string
            neighbourhood += 1;
        } else {
            neighbourhood += 0;
        }
        
        if(currentGeneration[k]){//same for itself
            neighbourhood += 1;
        } else {
            neighbourhood += 0;
        }
        
        if(currentGeneration[k+1]){//and the other neughbour
            neighbourhood +=1;
        } else {
            neighbourhood += 0;
        }
        
        
        
        //check which pattern it is, and if the relevant ruleset is set to active
        //if it is, return true(cell is alive) else false(cell is dead)
        if(neighbourhood.equals(p1) && rule1 == 1){
            return true;
        } else if(neighbourhood.equals(p2) && rule2 == 1){
            return true;
        } else if(neighbourhood.equals(p3) && rule3 == 1){
            return true;
        } else if(neighbourhood.equals(p4) && rule4 == 1){
            return true;
        } else if(neighbourhood.equals(p5) && rule5 == 1){
            return true;
        } else if(neighbourhood.equals(p6) && rule6 == 1){
            return true;
        } else if(neighbourhood.equals(p7) && rule7 == 1){
            return true;
        } else if(neighbourhood.equals(p8) && rule1 == 1){
            return true;
        } else {
            return false;
        }
        
        
        
    
    }
    
    
    public void draw() {
        //TODO draw the current state of the automaton; in other words: print currentGeneration
        String output = ""; //empty string to add stuff to
        
        //each boolean in the array gets checked to see if a space or * needs to be added
        for(int i = 1; i < currentGeneration.length - 1; i++) { //-1 to account for the added false at the end
            if(currentGeneration[i]) {
                output += "*";
            } else {
                output += " ";
            }
        }
        
        System.out.println(output); //and print the line
        
     
    }
    
    
    public void computeNextGeneration() {
      
        boolean[] newGeneration = new boolean[length + 2]; //second array for the new generation
        
        newGeneration[0] = false; //outermost values are always false
        newGeneration[length + 1] = false;
        
        //go through all others and check if they are dead or alive
        for(int i = 1; i < currentGeneration.length - 1; i++) { 
            
            /* the if checks wether automaton A,B or universal are used and checks and relevent rules
             * due to how the newCellValueBy... methods work, this could also be done using only newCellByRules
             * but the assignment says we must define and use seperate methods for A and B as well
             * resulting in code that's both less efficient and messier, but the assignment says so...
             * (we asked wether we could leave those two methods out and the answer was that we should do it like this)
             */
            if((newCellValueByRules(i) && type.equals("U")) || (newCellValueByA(i) && type.equals("A")) || 
               (newCellValueByB(i) && type.equals("B")) ){
                newGeneration[i] = true;
            } else {
                newGeneration[i] = false;
            }
        }
        
        
        currentGeneration = newGeneration; //update the currentgeneration to be the new one
        

    }
    
    
    /* runs the program in general
     * so first makes the input part do its thing
     * and the the actual running of all the generations
     */
    public void run() {
        
        readGeneral(); //deals with all the input. goes to readInitial and, if needed, readRules as well
        
        //while there's still a generation that needs to be processed, do so and print it's line
        while(numGen>0) {
            draw(); //current generation gets its line
            
            computeNextGeneration(); //and the next gets calculated.
            
            numGen = numGen - 1; //reduce the number of generations that still need processing by 1
        }

    }

    
    public static void main(String[] args) {
        (new Cellulitis()).run();
    }
}