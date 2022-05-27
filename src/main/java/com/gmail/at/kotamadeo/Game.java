package com.gmail.at.kotamadeo;

import com.gmail.at.kotamadeo.utils.Utils;

import java.util.Scanner;

public class Game {
    private final Scanner scanner = new Scanner(System.in);
    private final Board board = new Board(Integer.parseInt(scanner.nextLine()));
    private boolean isCrossTurn = true;
    private final char CROSS = 'X';
    private final char ZERO = 'O';

    public void start() {
        while (true) {
            board.printBoard(board.getBoard());
            System.out.println("Ходят " + (isCrossTurn ? "крестики" : "нолики") + "!");
            System.out.println(Utils.ANSI_BLUE + "Введите координаты в формате 1 1:" + Utils.ANSI_RESET);
            String[] allInput = scanner.nextLine().split(" ");
            var row = Integer.parseInt(allInput[0]) - 1;
            var cell = Integer.parseInt(allInput[1]) - 1;
            var boardForPlay = board.getBoard();
            if (boardForPlay[row][cell] != board.getEmptyCell()) {
                System.out.println(Utils.ANSI_RED + "В эту ячейку ходить нельзя!" + Utils.ANSI_RESET);
                continue;
            }
            boardForPlay[row][cell] = isCrossTurn ? CROSS : ZERO;
            if (isWin(boardForPlay, isCrossTurn ? CROSS : ZERO)) {
                board.printBoard(board.getBoard());
                System.out.println(Utils.ANSI_GREEN + "Победили " + (isCrossTurn ? "крестики" : "нолики") +
                        Utils.ANSI_RESET);
                break;
            } else {
                isCrossTurn = !isCrossTurn;
            }
        }
        System.out.println(Utils.ANSI_GREEN + "Игра закончена!" + Utils.ANSI_RESET);
    }


    public boolean isWin(char[][] boardForPlay, char player) {
        for (var row = 0; row < board.getSize(); row++) {
            var playerRowCount = 0;
            for (var cell = 0; cell < board.getSize(); cell++) {
                if (boardForPlay[row][cell] == player) {
                    playerRowCount++;
                }
            }
            if (playerRowCount == board.getSize()) {
                return true;
            }
        }
        for (var cell = 0; cell < board.getSize(); cell++) {
            var playerCellCount = 0;
            for (var row = 0; row < board.getSize(); row++) {
                if (boardForPlay[row][cell] == player) {
                    playerCellCount++;
                }
            }
            if (playerCellCount == board.getSize()) {
                return true;
            }
        }
        int playerMainDiagonalCount = 0;
        for (var row = 0; row < board.getSize(); row++) {
            for (var cell = 0; cell < board.getSize(); cell++) {
                if (cell == row && boardForPlay[row][cell] == player) {
                    playerMainDiagonalCount++;
                }
            }
            if (playerMainDiagonalCount == board.getSize()) {
                return true;
            }
        }
        var playerSecondDiagonalCount = 0;
        for (int cell = 0, row = (board.getSize() - 1) - cell; row >= 0; row--, cell++) {
            if (boardForPlay[row][cell] == player) {
                playerSecondDiagonalCount++;
            }
            if (playerSecondDiagonalCount == board.getSize()) {
                return true;
            }
        }
        return false;
    }
}
