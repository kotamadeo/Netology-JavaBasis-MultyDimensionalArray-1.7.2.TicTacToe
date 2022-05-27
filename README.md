# **Задача № 2 Дописываем крестики-нолики**

## **Цель**:

1. Взять за основу игру крестики-нолики с вебинара и дописать метод проверки победы одного из игроков, переписав его на циклы. Так он будет работать при любом значении ```SIZE```.

### *Пример*:
``` Пример 1
Игровое поле:
X * O 
O X * 
* * X 

Победили крестики
Игра закончена!
```

### **Моя реализация**:

1. Реализация осуществлена в парадигме ООП.
2. Создал структуру классов:

* **Game** - отвечающий за запуск программы, путем инициирования метода *start()*.

#### Класс **Game**:
``` java
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
```

* **Board** - описывающий игровое поле. Имеет следующий важный ```void``` методы: *printBoard()*, который выводит на экран игровое поле.
Также класс имеет геттер методы для доступа к полям: board, size и EMPTY_CELL.

#### Класс **Board**:
``` java   
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
```

2. Использовал кодирование цвета текста (ANSI).

#### Класс **Utils**:

``` java
public class Utils {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_BLACK = "\u001B[30m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_PURPLE = "\u001B[35m";
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_WHITE = "\u001B[37m";

    public void printDelim() {
        System.out.println(ANSI_GREEN + "*********************************************" + ANSI_RESET);
    }

    public String getAnsiReset() {
        return ANSI_RESET;
    }

    public String getAnsiBlack() {
        return ANSI_BLACK;
    }

    public String getAnsiRed() {
        return ANSI_RED;
    }

    public String getAnsiGreen() {
        return ANSI_GREEN;
    }

    public String getAnsiYellow() {
        return ANSI_YELLOW;
    }

    public String getAnsiBlue() {
        return ANSI_BLUE;
    }

    public String getAnsiPurple() {
        return ANSI_PURPLE;
    }

    public String getAnsiCyan() {
        return ANSI_CYAN;
    }

    public String getAnsiWhite() {
        return ANSI_WHITE;
    }
}
```

#### Метод *main()* в классе **Main**:
``` java
public class Main {
    public static void main (String[] args) {
        System.out.println(Utils.ANSI_GREEN + "Введите размер игрового поля:" + Utils.ANSI_RESET);
        var game = new Game();
        game.start();
    }
}
```

## *Вывод в консоль*:

* демонстрация работы:
``` 
Введите размер игрового поля:
3

Игровое поле:
* * * 
* * * 
* * * 

Ходят крестики!
Введите координаты в формате 1 1:
1 1 
Игровое поле:
X * * 
* * * 
* * * 

Ходят нолики!
Введите координаты в формате 1 1:
2 1
Игровое поле:
X * * 
O * * 
* * * 

Ходят крестики!
Введите координаты в формате 1 1:
2 2
Игровое поле:
X * * 
O X * 
* * * 

Ходят нолики!
Введите координаты в формате 1 1:
1 3
Игровое поле:
X * O 
O X * 
* * * 

Ходят крестики!
Введите координаты в формате 1 1:
3 3
Игровое поле:
X * O 
O X * 
* * X 

Победили крестики
Игра закончена!
```