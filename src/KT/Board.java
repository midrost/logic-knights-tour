package KT;

public class Board {
    private final int rows;
    private final int columns;
    private final Cell[][] boardMatrix;
    private final int[][] validMoves = {{-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1}};

    public Board() {
        this.rows = 8;
        this.columns = 8;
        this.boardMatrix = new Cell[this.rows][this.columns];
        this.setCells();
    }

    /**
     * Fill boardMatrix with  Cell objects
     */
    private void setCells() {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                this.boardMatrix[i][j] = new Cell(i, j);
            }
        }
    }

    /**
     * "Moves" the knight to a new position; sets the number of the new position to the next move
     *
     * @param posRow    row of the next position
     * @param posColumn column of the next position
     * @param posNumber number of the next move
     */
    public void setCounter(int posRow, int posColumn, int posNumber) {
        this.boardMatrix[posRow][posColumn].setKnight(posNumber);
    }

    /**
     * @param row    of the knight cell
     * @param column of the knight cell
     * @param move   current move
     * @return the next cell
     */
    public Cell findSolution(int row, int column, int move) {
        Cell[] cells = this.accessiblePositions(row, column);
        int temp, tmpIndex = -1, minDegree = Integer.MAX_VALUE;
        for (int i = 0; i < cells.length; i++) {
            temp = this.accessiblePositionsCount(this.accessiblePositions(cells[i].getRow(), cells[i].getColumn()));
            if (temp < minDegree) {
                minDegree = temp;
                tmpIndex = i;
            }
        }

        //if tmpIndex is <0, then no more accessible cells exist and therefore a solution is found
        if (tmpIndex < 0) {
            return null;
        }

        int xTemp = cells[tmpIndex].getRow();
        int yTemp = cells[tmpIndex].getColumn();

        this.setCounter(xTemp, yTemp, move);
        this.boardMatrix[xTemp][yTemp].setKnight(move);

        return this.boardMatrix[xTemp][yTemp];
    }

    /**
     * @param row    of the knight cell
     * @param column of the knight cell
     * @return Cell[] with all accessible positions for the current cell
     */
    public Cell[] accessiblePositions(int row, int column)  m{
        int nextX, nextY, counter = 0;
        boolean isSafe;
        int[][] cellsCoordinates = new int[8][2];

        for (int i = 0; i < cellsCoordinates.length; i++) {
            cellsCoordinates[i][0] = -1;
            cellsCoordinates[i][1] = -1;
        }

        for (int i = 0; i < cellsCoordinates.length; i++) {
            nextX = this.validMoves[i][0] + row;
            nextY = this.validMoves[i][1] + column;

            if (nextX >= this.rows || nextY >= this.columns || nextX < 0 || nextY < 0) {
                isSafe = false;
            } else {
                isSafe = this.boardMatrix[nextX][nextY].isSafe(this.rows, this.columns);
            }

            if (isSafe) {
                cellsCoordinates[i][0] = nextX;
                cellsCoordinates[i][1] = nextY;
                counter++;
            }
        }

        Cell[] cells = new Cell[counter];
        counter = 0;
        for (int i = 0; i < cellsCoordinates.length; i++) {
            int x = cellsCoordinates[i][0];
            int y = cellsCoordinates[i][1];
            if (x != -1 && y != -1) {
                cells[counter++] = this.boardMatrix[x][y];
            }
        }

        return cells;
    }

    private int accessiblePositionsCount(Cell[] cells) {
        return cells.length;
    }

    /**
     * Checks if the found solution is correct (if the matrix contains one or more 0's, then it's not correct)
     *
     * @return true if the solution is correct
     */
    public boolean isCorrectSolution() {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                if (this.boardMatrix[i][j].getMove() == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Prints the board after a solution is found
     */
    public void printBoard() {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                System.out.print(this.boardMatrix[i][j].getMove() + "\t");
            }
            System.out.print("\n");
        }
    }
}
