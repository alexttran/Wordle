/*
 * File: Wordle.java
 * -----------------
 * This module is the starter file for the Wordle assignment.
 * BE SURE TO UPDATE THIS COMMENT WHEN YOU COMPLETE THE CODE.
 */

import edu.willamette.cs1.wordle.WordleDictionary;
import edu.willamette.cs1.wordle.WordleGWindow;

public class Wordle {
    /* Private instance variables */
    private WordleGWindow gw;
    private String word;
    private int currRow = 0;
    private String[] wordLetters;


    public void run() {
        String[] letters = new String[5];
        gw = new WordleGWindow();
        int randNum = (int) (Math.random() * WordleDictionary.FIVE_LETTER_WORDS.length);
        word = WordleDictionary.FIVE_LETTER_WORDS[randNum];
        for (int i = 0; i < 5; i++)
            letters[i] = (word.substring(i, i + 1));
        gw.addEnterListener((s) -> enterAction(s));
        wordLetters = letters;
    }

    public boolean isValidWord(String str) {
        int hi = WordleDictionary.FIVE_LETTER_WORDS.length - 1;
        int lo = 0;
        int mid = hi / 2;
        while (hi >= lo) {
            if (WordleDictionary.FIVE_LETTER_WORDS[mid].equalsIgnoreCase(str)) {
                return true;
            } else if (str.compareToIgnoreCase(WordleDictionary.FIVE_LETTER_WORDS[mid]) > 0) {
                lo = mid + 1;
            } else if (str.compareToIgnoreCase(WordleDictionary.FIVE_LETTER_WORDS[mid]) < 0) {
                hi = mid - 1;
            }
            mid = (hi + lo) / 2;
        }
        return false;
    }

    public void changeColor(String guess) {
        for (int c = 0; c < 5; c++) {
            gw.setSquareColor(gw.getCurrentRow(), c, WordleGWindow.MISSING_COLOR);
            gw.setKeyColor(guess.substring(c, c + 1), WordleGWindow.MISSING_COLOR);
            if (word.indexOf(guess.charAt(c)) != -1) {
                gw.setSquareColor(gw.getCurrentRow(), c, WordleGWindow.PRESENT_COLOR);
                gw.setKeyColor(guess.substring(c, c + 1), WordleGWindow.PRESENT_COLOR);
            }
            if (word.indexOf(guess.charAt(c)) == c) {
                gw.setSquareColor(gw.getCurrentRow(), c, WordleGWindow.CORRECT_COLOR);
                gw.setKeyColor(guess.substring(c, c + 1), WordleGWindow.CORRECT_COLOR);
            }
        }
    }

    /*
     * Called when the user hits the RETURN key or clicks the ENTER button,
     * passing in the string of characters on the current row.
     */

    public void enterAction(String s) {
        if (isValidWord(s)) {
            gw.showMessage(word);
            changeColor(s);
            gw.setCurrentRow(gw.getCurrentRow() + 1);
        } else {
            gw.showMessage("Invalid Word");
        }
    }

    /* Startup code */

    public static void main(String[] args) {
        new Wordle().run();
    }

}


