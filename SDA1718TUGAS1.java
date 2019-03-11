/**
 * Tugas Pemrograman 1 Struktur Data & Algoritma
 * Created by: Izzan Fakhril Islam (1606875806)
 * SDA - C
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SDA1718TUGAS1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Stack<String>> tabs = new ArrayList<>();
        TreeSet<String> history = new TreeSet<>();
        Iterator<String> historyCount;
        int lineCount = Integer.parseInt(br.readLine());
        tabs.add(new Stack<String>());
        int currentTab = 0;

        for (int i = 0; i < lineCount; i++){
            String[] cmd = br.readLine().split(";");
            if (cmd[0].equals("VIEW")){
                tabs.get(currentTab).push(cmd[1]+":"+cmd[2]);
                System.out.println("VIEWING(["+cmd[1]+":"+cmd[2]+"])");
                history.add(cmd[1]+":"+cmd[2]);
            }
            else if (cmd[0].equals("BACK")){
                if (tabs.get(currentTab).isEmpty()) {
                    System.out.println("EMPTY TAB");
                }
                else {
                    if (tabs.get(currentTab).isEmpty()) {
                        System.out.println("EMPTY TAB");
                    }
                    else {
                        tabs.get(currentTab).pop();
                        if (tabs.get(currentTab).isEmpty()){
                            System.out.println("EMPTY TAB");
                        }
                        else {
                            System.out.println("VIEWING(["+tabs.get(currentTab).peek()+"])");
                        }
                    }
                }
            }
            else if (cmd[0].equals("NEWTAB")){
                tabs.add(new Stack<String>());
                currentTab++;
            }
            else if (cmd[0].equals("CHANGETAB")){
                if ((Integer.parseInt(cmd[1]) >= tabs.size()) || (Integer.parseInt(cmd[1]) < 0)){
                    System.out.println("NO TAB");
                }
                else {
                    currentTab = Integer.parseInt(cmd[1]);
                    System.out.println("TAB: "+cmd[1]);
                }
            }
            else if (cmd[0].equals("HISTORY")){
                if (history.isEmpty()){
                    System.out.println("NO RECORD");
                }
                else {
                    historyCount = history.iterator();
                    while (historyCount.hasNext()){
                        System.out.println(historyCount.next());
                    }
                }
            }
        }
    }
}
