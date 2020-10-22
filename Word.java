package kirillnsb.guesstheword;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Word {
    String randomWord;
    String hiddenWord;

    public Word() throws IOException {
        randomWord = getWordFromBank();
        hiddenWord = hide();
    }

    private String hide() {
        String hiddenWord = "";
        hiddenWord = randomWord;
        for (int i = 0; i < randomWord.length(); i++) {
            hiddenWord = hiddenWord.replace(randomWord.charAt(i), '_');
        }
        return hiddenWord;
    }

    static String getWordFromBank() throws IOException {
        Random rnd = new Random();
        File file = new File("Word_Bank.txt");
        Scanner sc = new Scanner(file);
        String line = sc.nextLine();
        String[] wb = line.split(" ");
        sc.close();
        return wb[rnd.nextInt(wb.length)];
    }
}
