public class Minesweeper {

    public static void main(String[] args) {

        String enterRows = "Enter the number of rows: ";
        String enterColumns = "Enter the number of columns: ";
        int MIN = 5, MAX = 15;
        int spots;

        int rowSize = InputMethods.readInt(enterRows, MIN, MAX);
        int columnSize = InputMethods.readInt(enterColumns, MIN, MAX);

        boolean grid1[][] = allocateGrid(rowSize, columnSize), grid2[][] = allocateGrid(rowSize, columnSize), grid3[][] = allocateGrid(rowSize, columnSize);

        spots = InputMethods.readInt("Enter the number of spots to set: ", rowSize, (rowSize*columnSize)/2);

        boolean cont = false;
        
        while(!cont) {

            System.out.println("\nTry to guess where the " + spots + " mines have been places");
	        grid1 = setRandomValues(grid1, spots);
	        //grid2 = setRandomValues(grid2, spots);
	        grid2 = setInputValues(grid2, spots, rowSize - 1, columnSize - 1);
	        grid3 = compareGrids(grid1, grid2, grid3);
	        
	        displayGrid("\nYour guesses: ", grid2, 'G' );
	        displayGrid("\nGame mines: ", grid1, '*');
	        displayGrid("\nYour matches: ", grid3, 'M');
	        
	        cont = !InputMethods.wantsToRepeat();
        }

    }

    public static boolean[][] allocateGrid(int x, int y) {

        if( x <= 0)
            x = 1;

        if( y <= 0)
            y = 1;

        boolean[][] grid = new boolean[x][y];

        return grid;
    }

    public static boolean [][] setRandomValues( boolean [][] grid, int spots ) {

        grid = initGrid(grid);

        for(int c = 0; c < spots; ) {
            int row = (int)(Math.random() * (grid.length - 1) );
            int column = (int)(Math.random() * (grid[0].length - 1) );

            if(!grid[row][column]) {
                grid[row][column] = true;
                c++;
            }
        }

        return grid;
    }

    public static boolean [][] setInputValues(boolean [][] grid, int spots, int rowSize, int columnSize) {

        grid = initGrid(grid);
        for(int c = 0; c < spots; ) {
            int row = InputMethods.readInt("\nFor mine " + c + ", Enter row index: ", 0, rowSize);
            int column = InputMethods.readInt("Enter column index: ", 0, columnSize);

            if(!grid[row][column]) {
                grid[row][column] = true;
                c++;
                continue;
            }

            System.out.println("You already set that mine!");
        }

        return grid;
    }

    public static boolean [][] compareGrids(boolean [][] grid1, boolean [][] grid2, boolean [][] grid3) {

        for( int c = 0; c < grid1.length ; c++) {
            for( int j = 0; j < grid1[c].length; j++ ) {
                if(grid1[c][j] && grid2[c][j])
                    grid3[c][j] = true;
                
                else
                	grid3[c][j] = false;
            }

        }

        return grid3;


    }

    public static boolean[][] initGrid( boolean [][] grid ) {

        for(int c = 0; c < grid.length; c++) {
            for(int j = 0; j < grid[0].length; j++) {
                grid[c][j] = false;
            }
        }

        return grid;
    }

    public static void displayGrid(String fieldType, boolean [][] grid, char typeChar) {

        String delim1 = "---";
        String delim3 = "   ";
        String delim4 = " " + typeChar + " ";
        char delim2 = '|';

        System.out.println(fieldType);

        for(int k = 0; k < grid[0].length; k++) 
            System.out.print(delim2 + delim1);        

        System.out.println(delim2);
        

        for(int c = 0; c < grid.length; c++) {
            for(int j = 0; j < grid[c].length; j++) {

                if(grid[c][j])
                    System.out.print(delim2 + delim4);

                else
                    System.out.print(delim2 + delim3);
            }

            System.out.println(delim2);

        }

        for(int d = 0; d < grid[0].length; d++) {
            System.out.print(delim2 + delim1);
        }

        System.out.println(delim2);

    }



}

// Sample output

/*
Enter the number of rows: 75
Input out of range, must be >= 5 and <= 15
Enter the number of rows: 2
Input out of range, must be >= 5 and <= 15
Enter the number of rows: 1
Input out of range, must be >= 5 and <= 15
Enter the number of rows: -3
Input out of range, must be >= 5 and <= 15
Enter the number of rows: 0
Input out of range, must be >= 5 and <= 15
Enter the number of rows: 7
Enter the number of columns: 45
Input out of range, must be >= 5 and <= 15
Enter the number of columns: 30
Input out of range, must be >= 5 and <= 15
Enter the number of columns: 16
Input out of range, must be >= 5 and <= 15
Enter the number of columns: 0
Input out of range, must be >= 5 and <= 15
Enter the number of columns: 2
Input out of range, must be >= 5 and <= 15
Enter the number of columns: -1
Input out of range, must be >= 5 and <= 15
Enter the number of columns: 9
Enter the number of spots to set: 10

Try to guess where the 10 mines have been places

For mine 0, Enter row index: 1
Enter column index: 2

For mine 1, Enter row index: 3
Enter column index: 4

For mine 2, Enter row index: 0
Enter column index: 0

For mine 3, Enter row index: 7
Input out of range, must be >= 0 and <= 6

For mine 3, Enter row index: 8
Input out of range, must be >= 0 and <= 6

For mine 3, Enter row index: 4
Enter column index: 3

For mine 4, Enter row index: 3
Enter column index: 4
You already set that mine!

For mine 4, Enter row index: 4
Enter column index: 8

For mine 5, Enter row index: 5
Enter column index: 9
Input out of range, must be >= 0 and <= 8
Enter column index: 4

For mine 6, Enter row index: 1
Enter column index: 4

For mine 7, Enter row index: 1
Enter column index: 2
You already set that mine!

For mine 7, Enter row index: 4
Enter column index: 5

For mine 8, Enter row index: 6
Enter column index: 4

For mine 9, Enter row index: 3
Enter column index: 6

Your guesses: 
|---|---|---|---|---|---|---|---|---|
| G |   |   |   |   |   |   |   |   |
|   |   | G |   | G |   |   |   |   |
|   |   |   |   |   |   |   |   |   |
|   |   |   |   | G |   | G |   |   |
|   |   |   | G |   | G |   |   | G |
|   |   |   |   | G |   |   |   |   |
|   |   |   |   | G |   |   |   |   |
|---|---|---|---|---|---|---|---|---|

Game mines: 
|---|---|---|---|---|---|---|---|---|
|   |   |   |   | * |   |   |   |   |
|   |   |   |   |   |   |   | * |   |
|   |   | * |   |   |   |   |   |   |
|   |   |   | * | * | * |   |   |   |
|   |   | * |   |   |   | * |   |   |
|   | * |   |   | * |   |   |   |   |
|   |   |   |   |   |   |   |   |   |
|---|---|---|---|---|---|---|---|---|

Your matches: 
|---|---|---|---|---|---|---|---|---|
|   |   |   |   |   |   |   |   |   |
|   |   |   |   |   |   |   |   |   |
|   |   |   |   |   |   |   |   |   |
|   |   |   |   | M |   |   |   |   |
|   |   |   |   |   |   |   |   |   |
|   |   |   |   | M |   |   |   |   |
|   |   |   |   |   |   |   |   |   |
|---|---|---|---|---|---|---|---|---|
Do you want to repeat? (y for yes)
y

Try to guess where the 10 mines have been places

For mine 0, Enter row index: 7
Input out of range, must be >= 0 and <= 6

For mine 0, Enter row index: 4
Enter column index: 1

For mine 1, Enter row index: 3
Enter column index: 45
Input out of range, must be >= 0 and <= 8
Enter column index: 3

For mine 2, Enter row index: 2
Enter column index: 1

For mine 3, Enter row index: 0
Enter column index: -5
Input out of range, must be >= 0 and <= 8
Enter column index: 4

For mine 4, Enter row index: 1
Enter column index: 3

For mine 5, Enter row index: 5
Enter column index: 3

For mine 6, Enter row index: 4
Enter column index: 2

For mine 7, Enter row index: 5
Enter column index: 3
You already set that mine!

For mine 7, Enter row index: 1
Enter column index: 3
You already set that mine!

For mine 7, Enter row index: 5
Enter column index: 3
You already set that mine!

For mine 7, Enter row index: 2
Enter column index: 5

For mine 8, Enter row index: 6
Enter column index: 4

For mine 9, Enter row index: 2
Enter column index: 1
You already set that mine!

For mine 9, Enter row index: 2
Enter column index: 4

Your guesses: 
|---|---|---|---|---|---|---|---|---|
|   |   |   |   | G |   |   |   |   |
|   |   |   | G |   |   |   |   |   |
|   | G |   |   | G | G |   |   |   |
|   |   |   | G |   |   |   |   |   |
|   | G | G |   |   |   |   |   |   |
|   |   |   | G |   |   |   |   |   |
|   |   |   |   | G |   |   |   |   |
|---|---|---|---|---|---|---|---|---|

Game mines: 
|---|---|---|---|---|---|---|---|---|
|   |   | * | * |   |   |   | * |   |
|   | * |   | * | * |   |   |   |   |
|   |   |   |   |   | * |   |   |   |
|   |   |   |   |   |   | * |   |   |
| * |   |   |   | * |   |   |   |   |
|   |   |   |   |   |   |   |   |   |
|   |   |   |   |   |   |   |   |   |
|---|---|---|---|---|---|---|---|---|

Your matches: 
|---|---|---|---|---|---|---|---|---|
|   |   |   |   |   |   |   |   |   |
|   |   |   | M |   |   |   |   |   |
|   |   |   |   |   | M |   |   |   |
|   |   |   |   |   |   |   |   |   |
|   |   |   |   |   |   |   |   |   |
|   |   |   |   |   |   |   |   |   |
|   |   |   |   |   |   |   |   |   |
|---|---|---|---|---|---|---|---|---|
Do you want to repeat? (y for yes)
y

Try to guess where the 10 mines have been places

For mine 0, Enter row index: 4
Enter column index: 3

For mine 1, Enter row index: 1
Enter column index: 2

For mine 2, Enter row index: 3
Enter column index: 5

For mine 3, Enter row index: 2
Enter column index: 4

For mine 4, Enter row index: 5
Enter column index: 1

For mine 5, Enter row index: 2
Enter column index: 3

For mine 6, Enter row index: 5
Enter column index: 3

For mine 7, Enter row index: 4
Enter column index: 1

For mine 8, Enter row index: 63
Input out of range, must be >= 0 and <= 6

For mine 8, Enter row index: 5
Enter column index: 3
You already set that mine!

For mine 8, Enter row index: -5
Input out of range, must be >= 0 and <= 6

For mine 8, Enter row index: 3
Enter column index: 0

For mine 9, Enter row index: 3
Enter column index: 1

Your guesses: 
|---|---|---|---|---|---|---|---|---|
|   |   |   |   |   |   |   |   |   |
|   |   | G |   |   |   |   |   |   |
|   |   |   | G | G |   |   |   |   |
| G | G |   |   |   | G |   |   |   |
|   | G |   | G |   |   |   |   |   |
|   | G |   | G |   |   |   |   |   |
|   |   |   |   |   |   |   |   |   |
|---|---|---|---|---|---|---|---|---|

Game mines: 
|---|---|---|---|---|---|---|---|---|
|   |   |   |   |   |   |   | * |   |
|   | * | * |   |   |   |   | * |   |
|   |   |   |   |   |   |   |   |   |
|   |   |   | * |   |   | * |   |   |
|   |   | * |   |   | * |   | * |   |
|   |   |   |   | * |   |   |   |   |
|   |   |   |   |   |   |   |   |   |
|---|---|---|---|---|---|---|---|---|

Your matches: 
|---|---|---|---|---|---|---|---|---|
|   |   |   |   |   |   |   |   |   |
|   |   | M |   |   |   |   |   |   |
|   |   |   |   |   |   |   |   |   |
|   |   |   |   |   |   |   |   |   |
|   |   |   |   |   |   |   |   |   |
|   |   |   |   |   |   |   |   |   |
|   |   |   |   |   |   |   |   |   |
|---|---|---|---|---|---|---|---|---|
Do you want to repeat? (y for yes)
n
*/