package com.gmail.at.kotamadeo;

public class Board {
    private final char[][] board;
    private final int size;
    private final char EMPTY_CELL = '*';

    public Board(int size) {
        this.size = size;
        this.board = new char[this.size][this.size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = EMPTY_CELL;
            }
        }
    }

    public void printBoard(char[][] board) {
        System.out.println("Игровое поле:");
        for (var row : board) {
            for (var cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }

    public char[][] getBoard() {
        return board;
    }

    public char getEmptyCell() {
        return EMPTY_CELL;
    }

    public int getSize() {
        return size;
    }
}
