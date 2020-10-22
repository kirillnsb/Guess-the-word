package kirillnsb.guesstheword;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class Player {
    private String name;
    private String session;

    public void setName(String name) {
        this.name = name;
    }

    public void setSession(String word, int trials) {
        this.session = word + "(" + trials + ")";
    }

    public int checkNameInFile() throws IOException {
        String nameCheck;
        int rowNum = 0;
        File file = new File("Players.txt");
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            nameCheck = sc.nextLine();
            if (nameCheck.equals(name)) {
                sc.close();
                return rowNum;
            }
            rowNum++;
        }
        sc.close();
        return -1;
    }

    public void saveStats() throws IOException {
        if (checkNameInFile() == -1) {
            File file = new File("Players.txt");
            FileWriter fw = new FileWriter(file, true);
            fw.write(name + "\n" + session + "\n\n");
            fw.flush();
            fw.close();
        } else {
            List<String> l = Files.readAllLines(Paths.get("Players.txt"));
            String s = l.get(checkNameInFile() + 1);
            l.set(checkNameInFile() + 1, s + " " + session);
            Files.write(Paths.get("Players.txt"), l);
        }
    }
}
