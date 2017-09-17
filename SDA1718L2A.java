/**
 * Lab 2 SDA Soal A (Jumat)
 * Created by Izzan Fakhril Islam (1606875806)
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SDA1718L2A {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //TreeMap order: Untuk menampung daftar NPM dan banyaknya kertas dalam pesanan, diurutkan berdasarkan NPM tertua
        TreeMap<String, Integer> order = new TreeMap<>();

        //hasOrdered: Untuk menampung daftar NPM yang pernah melakukan transaksi
        ArrayList<String> hasOrdered = new ArrayList<>();

        //paperSum: Sebagai counter banyaknya pesanan kertas dalam sekali order
        int paperSum = 0;

        String inp = br.readLine();
        while (inp != null){
            String[] words = inp.split(" ");
            if (words.length == 1){

                //Perintah PRINT
                if (words[0].equals("PRINT")){
                    if (order.size() == 0){
                        System.out.println("Antrean kosong"); //Jika tidak ada orang dalam antrean
                    }
                    else {
                        //Akan diproses jika jumlah kertas yang diproses kurang dari atau sama dengan 10
                        while (!order.isEmpty() && paperSum + order.get(order.firstKey()) <= 10){
                            paperSum += order.get(order.firstKey());
                            hasOrdered.add(order.firstKey()); //Memasukkan NPM yang bersangkutan ke dalam hasOrdered
                            System.out.println("Submisi "+order.firstKey()+" telah dicetak sebanyak "+order.get(order.firstKey())+" halaman");
                            order.remove(order.firstKey()); //Menghapus NPM yang bersangkutan dari daftar transaksi
                        }
                        paperSum = 0;
                    }
                }
            }
            else {
                //Perintah SUBMIT
                if (words[1].equals("SUBMIT")){
                    if ((Integer.parseInt(words[2]) <= 10) && !(order.containsKey(words[0]))) {
                        order.put(words[0], Integer.parseInt(words[2]));
                        System.out.println("Submisi "+words[0]+" telah diterima");
                    }
                    else {
                        //Jika jumlah halaman terlalu banyak
                        if (Integer.parseInt(words[2]) > 10){
                            System.out.println("Jumlah halaman submisi "+words[0]+" terlalu banyak");
                        }
                        //Jika sebelumnya sudah melakukan submisi dan belum di print
                        else if (order.containsKey(words[0])){
                            System.out.println("Harap tunggu hingga submisi sebelumnya selesai diproses");
                        }
                    }
                }
                //Perintah CANCEL
                else if (words[1].equals("CANCEL")){
                    if (order.containsKey(words[0])){
                        order.remove(words[0]);
                        System.out.println("Submisi "+words[0]+" dibatalkan");
                    }
                    else {
                        System.out.println(words[0]+" tidak ada dalam antrean");
                    }
                }
                //Perintah STATUS
                else if (words[0].equals("STATUS")){
                    if (!(order.containsKey(words[1])) && !(hasOrdered.contains(words[1]))){
                        System.out.println(words[1]+" tidak ada dalam sistem");
                    }
                    else {
                        //Jika orang yang sudah pernah transaksi dicek status nya
                        if (hasOrdered.contains(words[1])){
                            System.out.println("Submisi "+words[1]+" sudah diproses");
                        }
                        //Jika orang yang masih dalam antrean dicek status nya
                        else if (order.containsKey(words[1])){
                            System.out.println("Submisi "+words[1]+" masih dalam antrean");
                        }
                    }
                }
            }
            inp = br.readLine();
        }
    }
}
