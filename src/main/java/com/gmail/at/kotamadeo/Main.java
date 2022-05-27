package com.gmail.at.kotamadeo;

import com.gmail.at.kotamadeo.utils.Utils;

public class Main {
    public static void main (String[] args) {
        System.out.println(Utils.ANSI_GREEN + "Введите размер игрового поля:" + Utils.ANSI_RESET);
        var game = new Game();
        game.start();
    }
}
