package KT;

public class Cell {
    private final int row;
    private final int column;
    private int move = 0;

    public Cell(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return this.row;
    }

    public int getColumn() {
        return this.column;
    }

    public int getMove() {
        return this.move;
    }

    /**
     * "Move" the knight to the this cell
     *
     * @param move Counter for the moves
     */
    public void setKnight(int move) {
        this.move = move;
    }

    /**
     * @param rowCount    Rows of the board
     * @param columnCount Columns of the board
     * @return true if the cell is inside the board
     */
    public boolean isSafe(int rowCount, int columnCount) {
        return this.row >= 0 && this.row < rowCount && this.column >= 0 && this.column < columnCount && this.move == 0;
    }
}
