class Sudoku {
    private static final int SIZE = 9;     // size of the grid e.g. 9 -> 9x9
    private static final int DMAX = 9;     // max digit to be filled in e.g. 9
    private static final int BOXSIZE = 3;  // size of the boxes e.g. 3 -> 3x3
    int[][] grid = new int[][] {
        { 0, 6, 0,  0, 0, 1,  0, 9, 4 },
        { 3, 0, 0,  0, 0, 7,  1, 0, 0 },
        { 0, 0, 0,  0, 9, 0,  0, 0, 0 },
        { 7, 0, 6,  5, 0, 0,  2, 0, 9 },
        { 0, 3, 0,  0, 2, 0,  0, 6, 0 },
        { 9, 0, 2,  0, 0, 6,  3, 0, 1 },
        { 0, 0, 0,  0, 5, 0,  0, 0, 0 },
        { 0, 0, 7,  3, 0, 0,  0, 0, 2 },
        { 4, 1, 0,  7, 0, 0,  0, 8, 0 },
    };

    int solutionnr = 0; //solution counter

    void run() {
        printGrid();
        
        if(!givesConflict(2,2,5)) {
            System.out.println("a");
        }
        if(givesConflict(5,4,5)) {
            System.out.println("b");
        }
    }
    
    //is there a conflict when we fill in d at position r,c?
    boolean givesConflict(int r, int  c, int d) {
        if(rowConflict(r,d) || colConflict(c,d) || boxConflict(r,c,d)) {
            return true;
        }
        
        
        return false;
    }

    //if there's a conflict in row r when filling in d, return true
    boolean rowConflict(int r, int d) {
        int[] nums = new int[SIZE]; //create array of right size
        for(int i = 0; i < SIZE; i++) { //add the relevent row to the array
            nums[i] = grid[r][i];
        }
       
        for(int num : nums) { //go through every element of the array
            if(d == num) { //if any of them equal d, there's a conflict
                return true;
            }
        }
        return false; //if none of them conflict, return false
    }

    
    //is there a conflict in column c when we fill in d?
    boolean colConflict(int c, int d) {
        //works similar to rowConflict
        
        int nums[] = new int[SIZE];
        for(int i = 0; i< SIZE; i++) {
            nums[i] = grid[i][c];
        }
        
        for(int num : nums) {
            if(d == num) {
                return true;
            }
        }
        return false;
    }

    //looks for conflict in the box if we want to fill in an integer
    //rr and cc are the row and column we where we want to fill in d
    boolean boxConflict(int rr, int cc, int d) {
        
        //coordinate divided by BOXSIZE gives the 'coordinates' of the box(compared to other boxes)
        int boxR = rr/BOXSIZE;
        int boxC = cc/BOXSIZE;
        
        
        int[] nums = new int[SIZE];
        
        int i = 0; //iterator to make sure we go forward in the nums array in the coming for loop
        
        //we first iteratro through each row of the box, and then through the colums within the row
        for(int r = boxR; r < boxR + BOXSIZE - 1; r++) { //boxR is the leftmost coord, boxR + BOXSIZE -1 the right most
            for(int c = boxR; c < boxR + BOXSIZE - 1; c++) { //same idea as above, but top to bottom
                nums[i] = grid[r][c]; //add the the array
                i = i + 1; //and make the iterator go one up so we don't assign values to an already used place
            }
        }
        
        //from here on same as row/colConflict
        for(int num : nums) {
            if(d == num) {
                return true;
            }
        }
            
        
        return false;
    }


    int[] findEmptySquare() {
        //TODO return the next empty square (See assignment).



        //END TODO
        return null;
    }

    void solve() {
        //TODO see (4)



        //END TODO
    }

    // print the grid, 0s are printed as spaces
     void printGrid() {
        String[] output = new String[13]; //13 to accomodate for extra lines
        
        
        
        output[0] = "+------------------+";
        output[12] = output[0];
        
        output[4] = "------------------";
        output[8] = output[4];
        
        for(int i = 1; i < 4; i++) {//iterate first thre rows
            int[] row = grid[i-1];
            output[i] = "";
            for(int n = 0; n < 9; n++) {
                output[i] = output[i] + Integer.toString(row[n]);
            }
        }
        for(int i = 5; i < 8; i++) {//iterate second thre rows
            int[] row = grid[i-2];
            output[i] = "";
            for(int n = 0; n < 9; n++) {
                output[i] = output[i] + Integer.toString(row[n]);
            }
        }
        for(int i = 9; i < 12; i++) {//iterate first thre rows
            int[] row = grid[i-3];
            output[i] = "";
            for(int n = 0; n < 9; n++) {
                output[i] = output[i] + Integer.toString(row[n]);
            }
        }
        
        for(String a : output) {
            System.out.println(a);
        }
    }

    //TODO extra methods



    //END TODO


    public static void main(String[] args) {
        new Sudoku().run();
    }
}
