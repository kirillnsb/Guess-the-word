package kirillnsb.guesstheword;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    static int trials = 0;

    static void win() {
        System.out.println("Congratulations!");
        if (trials == 1) {
            System.out.println("You got in 1 trial");//Vanga case
        } else System.out.println("You got in " + trials + " trials");
    }

    static void fail() {
        System.out.println("Fail");
        System.exit(0);
    }

    static void play() throws IOException {
        Scanner scanner = new Scanner(System.in);
        Word w = new Word();
        Player p = new Player();
        System.out.println("Enter your name: ");
        p.setName(scanner.next());
        StringBuffer sb = new StringBuffer(w.hiddenWord);
        String word = w.randomWord;
        String hiddenWord = w.hiddenWord;
        String input;
        char c;
        String cs;
        //System.out.println(word); //check
        System.out.println(hiddenWord); //show hidden word at start
        while (hiddenWord.contains("_")) {
            System.out.print("Key in one character or your guess word: ");
            input = scanner.next();
            if (input.length() == 1) {
                c = input.charAt(0);
                cs = String.valueOf(c);
                if (word.contains(cs)) {
                    for (int i = 0; i < word.length(); i++) {
                        if (word.charAt(i) == c)
                            sb.setCharAt(i, c);
                        hiddenWord = sb.toString();
                    }
                }
            } else if (input.length() == word.length()) {
                if (input.equals(word)) {
                    hiddenWord = input;
                } else Main.fail();
            } else {
                System.out.println("You can try only 1 character or whole word (not more than " + word.length() + " chars)");//literals longer than the keyword do not count as a trial
                trials--;
            }
            trials++;
            System.out.println("Trial " + trials + ": " + hiddenWord);
        }
        Main.win();
        p.setSession(word, trials);
        p.saveStats();
    }

    public static void main(String[] args) {

        try {
            Main.play();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
