package KT;

public class Main {
    static final int X = 8;

    public static void main(String[] args) {
        //create a new board with x rows and y columns
        Board board = new Board();

        //determine a random position for the knight by the formula Math.random() * (max - min + 1) + min; max -> x - 1; min -> 0
        int xKnight = (int) Math.floor(Math.random() * X);
        int yKnight = (int) Math.floor(Math.random() * X);

        System.out.println("Initial position chosen randomly:\nrow: " + (xKnight + 1) + "\ncolumn: " + (yKnight + 1));

        int move = 1;
        //set the initial position inside the board
        board.setCounter(xKnight, yKnight, move++);

        //find a solution for the initial position
        Cell cell = new Cell(xKnight, yKnight);
        do {
            if (cell != null) {
                cell = board.findSolution(cell.getRow(), cell.getColumn(), move++);
            }
        } while (!board.isCorrectSolution());

        board.printBoard();
    }
}
