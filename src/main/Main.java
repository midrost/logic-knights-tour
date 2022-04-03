package main;

import java.util.Scanner;

public class Main {
    //board size
    static int N = 8;

    public static void main(String[] args) {
        //matrix for the board
        int[][] board = new int[N][N];

        //matrix for the valid moves, first element is for the X-axis, second element is for the Y-axis;
        //all numbers are according to the current position
        int[][] validMoves = {{1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1}, {-2, 1}, {-1, 2}};

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

        if (solve(x, y, validMoves, board, 1)) {
            printSolution(board);
        } else {
            System.out.println("No solution found.");
        }
    }

    //checks if the position is safe to step on (is in the board and has not been used for the current solution)
    public static boolean isSafe(int x, int y, int[][] board) {
        return (x >= 0 && x < N && y >= 0 && y < N && board[x][y] == 0);
    }

    //print the board
    public static void printSolution(int[][] board) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(board[i][j] + "\t");
            }
            System.out.println();
        }
    }

    //x and y are coordinates of the current position;
    //validMoves is the matrix with the valid moves
    //board is the chess board
    //move is counter for the moves
    public static boolean solve(int x, int y, int[][] validMoves, int[][] board, int move) {
        if (move == N * N) {
            return true;
        }

        int nextX, nextY;

        //try all moves from the current position
        for (int i = 0; i < 8; i++) {
            nextX = x + validMoves[i][0];
            nextY = y + validMoves[i][1];
            if (isSafe(nextX, nextY, board)) {  //check if the next position is safe
                board[nextX][nextY] = ++move;
                if (solve(nextX, nextY, validMoves, board, move)) {
                    return true;
                } else {
                    board[nextX][nextY] = 0;    //backtracking -> make the position available again, if no more valid moves
                    move--;
                }
            }
        }

        //default return -> no solution found
        return false;
    }
}
