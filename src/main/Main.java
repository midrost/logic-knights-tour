package main;

import java.util.Scanner;

public class Main {
    //board size
    static int N = 8;

    //matrix for the valid moves, first element is for the X-axis, second element is for the Y-axis;
    //all numbers are according to the current position
    static int[][] validMoves = {{-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1}};

    //matrix for the board
    static int[][] board = new int[N][N];

    public static void main(String[] args) {
        //input for the initial position of the knight
        int x, y;
        Scanner input = new Scanner(System.in);

        do {
            System.out.println("Type X position of the knight (0-7): ");
            x = input.nextInt();
        } while (x < 0 || x > 7);

        do {
            System.out.println("Type Y position of the knight (0-7): ");
            y = input.nextInt();
        } while (y < 0 || y > 7);

        board[x][y] = 1;

        if (solve(x, y, 2)) {
            printSolution();
        } else {
            System.out.println("No solution found.");
        }
    }

    //checks if the position is safe to step on (is in the board and has not been used for the current solution)
    public static boolean isSafe(int x, int y) {
        return (x >= 0 && x < N && y >= 0 && y < N && board[x][y] == 0);
    }

    //print the board
    public static void printSolution() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(board[i][j] + "\t");
            }
            System.out.println();
        }
    }

    //x and y are coordinates of the current position;
    //move is counter for the moves
    public static boolean solve(int x, int y, int move) {
        if (move == N * N && isCorrectSolution()) {
            return true;
        }

        int nextX, nextY;

        //try all moves from the current position
        for (int i = 0; i < 8; i++) {
            nextX = x + validMoves[i][0];
            nextY = y + validMoves[i][1];
            if (isSafe(nextX, nextY)) {  //check if the next position is safe
                board[nextX][nextY] = move;
                if (solve(nextX, nextY, move + 1)) {
                    return true;
                } else {
                    board[nextX][nextY] = 0;    //backtracking -> make the position available again, if no more valid moves
                }
            }
        }

        //default return -> no solution found
        return false;
    }

    //checks if the solution is correct (no 0's in the final matrix)
    public static boolean isCorrectSolution() {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                if (board[i][j] == 0) {
                    return false;
                }
            }
        }

        return true;
    }
}
