//Lab 2A SDA Hari Selasa (Complexity: O(n))
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class SDA17L2A_Selasa {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<String> urls = new ArrayList<>();
        HashMap<String, Integer> occurrence = new HashMap<>();
        urls.add("default");
        int pointer = 0;
        int visit = 0;
        String[] words = br.readLine().split(" ");

        while (words != null) {
            if (words[0].equals("GO_TO")) {
                if (occurrence.containsKey(words[1])) {
                    urls.add(words[1]);
                    pointer++;
                    visit++;
                    occurrence.put(words[1], visit);
                    System.out.println(words[1]);
                } else {
                    urls.add(words[1]);
                    pointer++;
                    visit++;
                    occurrence.put(words[1], 1);
                    System.out.println(words[1]);
                }
            }
            else if (words[0].equals("BACK")) {
                if (pointer == 0) {
                    System.out.println("Tidak bisa melakukan operasi BACK");
                } else {
                    pointer--;
                    visit++;
                    occurrence.put(urls.get(pointer), visit);
                    System.out.println(urls.get(pointer));
                }
            }
            else if (words[0].equals("FORWARD")) {
                if (pointer == urls.size()) {
                    System.out.println("Tidak bisa melakukan operasi FORWARD");
                } else {
                    pointer++;
                    visit++;
                    occurrence.put(urls.get(pointer), visit);
                    System.out.println(urls.get(pointer));
                }
            }
            else if (words[0].equals("CHECK_HISTORY")) {
                if (occurrence.containsKey(words[1])) {
                    System.out.println("Link terakhir kali dikunjungi pada urutan ke " + occurrence.get(words[1]));
                } else {
                    System.out.println("Link belum pernah dikunjungi");
                }
            }
            words = br.readLine().split(" ");
        }
    }
}
