import java.util.Scanner;
public class Main {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String[] COLORS = {ANSI_RESET, ANSI_YELLOW, ANSI_GREEN};

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String target = "BALLS"; // TODO: make this randomly choose a target word
        int guess_count = 6;
        boolean win_flag = false;
        while (guess_count > 0) {
            System.out.println("Input: ");
            String inp = input.nextLine().toUpperCase();
            if (!is_valid(inp)) continue;
            int[] alpha = new int[26];
            int[] out = new int[5];
            for (int i = 0; i < 5; i++) alpha[target.charAt(i) - 'A']++;
            int c = 0;
            for (int i = 0; i < 5; i++) {
                if (inp.charAt(i) == target.charAt(i)) { // exact location match
                    out[i] = 2;
                    c++;
                    alpha[inp.charAt(i) - 'A']--;
                }
            }
            if (c == 5) {
                guess_count = 0;
                win_flag = true;
            }
            for (int i = 0; i < 5; i++) {
                if (out[i] != 0) continue;
                if (alpha[inp.charAt(i) - 'A'] > 0) { // if we have that letter somewhere in the word
                    out[i] = 1;
                    alpha[inp.charAt(i) - 'A']--;
                }
            }
            for (int i = 0; i < 5; i++) System.out.print(COLORS[out[i]] + out[i]);
            System.out.println(ANSI_RESET);
            guess_count--;
        }
        if (win_flag) System.out.println("WINIOOFIJWIOFJDOHFOISDHOIFHSDIFHSDIOF");
        else System.out.println("YOU SUCK!!!!!!!!!");
    }

    // checks for fully alpha & length 5; TODO: make this check against the dictionary
    private static boolean is_valid(String inp) {
        if (inp.length() != 5) return false;
        for (int i = 0; i < 5; i++) {
            if (inp.charAt(i) < 'A' || inp.charAt(i) > 'Z') {
                return false;
            }
        }
        return true;
    }
}