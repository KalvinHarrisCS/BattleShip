import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;


public class BattleShipGame {
    public static void main(String[] args){
        System.out.println("Enter your cordinates  by row and colums. Row first then Colums");
        int gameBoardLength = 10;


        char water = '~';
        char ship = 's';
        char hit = 'X';
        char miss = '0';
        int shipNumber = 3;


        // char[][] intlizes my gameboard in a 2D array of char.
    char[][] gameBoard = createGameBoards(gameBoardLength,water,ship,shipNumber);
    printGameBoard(gameBoard,water,ship);
    int undetectedShipNumber = shipNumber;
    while(undetectedShipNumber > 0){
        int[] guessCoordinates = getUserCoordinates(gameBoardLength);
        char locationViewUpdate = evaluateGuessAndGetTheTarget(guessCoordinates, gameBoard, ship, water,hit,miss);
        if(locationViewUpdate == hit){
            undetectedShipNumber--;

        }
        gameBoard = updateGameBoard(gameBoard, guessCoordinates,locationViewUpdate);
        printGameBoard(gameBoard,water,ship);
    }
    }

    private static char[][] updateGameBoard(char[][] gameBoard, int[] guessCoordinates, char locationViewUpdate) {
        int row = guessCoordinates[0];
        int col = guessCoordinates[1];
        gameBoard[row][col] = locationViewUpdate;
        return gameBoard;

    }

    private static char evaluateGuessAndGetTheTarget(int[] guessCoordinates, char[][] gameBoard, char ship, char water, char hit, char miss) {
        String message;
        int row = guessCoordinates[0];
        int col = guessCoordinates[1];
        char target = gameBoard[row][col];
        // possibly use a switch
        if(target == ship){
            message = "Hit!";
            target = hit;

        }else if (target == water){
            message = "Miss!";
            target = water;
        }else{
            message = "Already hit!";

        }
        System.out.println(message);
        return target;
    }



    //checks if user actaully put corrdinates on board
    private static int[] getUserCoordinates(int gameBoardLength){


        //prompt user for input
        int row;
        int col;


        // do while loop
        do{
            System.out.println("Row: ");
            row = new Scanner(System.in).nextInt();

        }while (row < 0 || row > gameBoardLength + 1);
        do{
            System.out.println("Column ");
            col = new Scanner(System.in).nextInt();
        }while (col < 0 || col > gameBoardLength + 1);
        return new int[]
                {
                row -1, col -1};
    }

    private static void printGameBoard(char[][] gameBoard, char water, char ship) {
        // iterate over it
        // when handling the user input when we display 1234 player will type 1234 not 0123 index rules


        int gameBoardLength = gameBoard.length;
        System.out.println("  ");
        for (int i = 0; i < gameBoardLength; i ++){
            System.out.println(i + 1 + " ");
        }
        System.out.println();
        for (int row = 0; row< gameBoardLength; row++){
            System.out.println(row + 1 +" ");
            for(int col = 0; col< gameBoardLength; col++){
                char position = gameBoard[row][col];
                if(position == ship){
                    System.out.println(water + " ");

                } else{
                    System.out.print(position + " ");
                }

            }
            System.out.println();


        }
        System.out.println();
    }

    private static char[][] createGameBoards(int gameBoardLength, char water, char ship, int shipNumber) {
        char[][] gameBoard = new char[gameBoardLength][gameBoardLength];

        //iterate
        for(char[] row:gameBoard) {
            Arrays.fill(row, water);

        }
        return  placeShips(gameBoard, shipNumber, water,  ship);


    }

    private static char[][] placeShips(char[][] gameBoard, int shipNumber, char water, char ship) {
        int placeShips = 0;
        int gameBoardLength = gameBoard.length;

        //while loop will place the ships at a given location
        while (placeShips < shipNumber){

            // could make another java class but it can complicate the code.
            int[] location = generateShipCoordinates(gameBoardLength);

            // cannot stack the ships on one corrdinate so make a if statment posisble palcement is water.
            char possiblePlacement = gameBoard[location[0]][location[1]];

//  if we generate at the same location line 38 char posisble placement
//  will be exicuted wont be true then we go on without error

            if(possiblePlacement == water){
                // put ship in to the game board at location 1
                gameBoard[location[0]][location[1]] = ship;
                placeShips++;

            }



        }
        return gameBoard;
    }
    private static int[] generateShipCoordinates(int gameBoardLength){
        int[] coordinates = new int[2];
        // to get 1 cordinate make my loop the cordinates length are 2 after this iteration we will drop out of loop and i ++
        for(int i = 0; i < coordinates.length; i++){
            coordinates[i] = new Random().nextInt(gameBoardLength);
        }
        return coordinates;

    }
}
