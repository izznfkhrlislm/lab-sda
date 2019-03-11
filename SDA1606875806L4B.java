/**
 * Lab 04 Struktur Data & Algoritma (Soal B)
 * Created by: Izzan Fakhril Islam (1606875806)
 */

import java.util.*;
import java.io.*;

public class SDA1606875806L4B {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Kartu> deck = new ArrayList<>();

        String cmd = br.readLine();

        while (cmd != null){
            String[] command = cmd.split(" ");
            
            //Perintah PICK: Untuk mengambil Kartu baru, kemudian mengurutkannya agar sesuai dengan persyaratan yang ada di soal
            if (command[0].equals("PICK")){
                Kartu newCard = new Kartu(command[1], Integer.parseInt(command[2]));
                deck.add(newCard);
                quicksort(deck, 0, deck.size()-1);
                System.out.println(newCard.getCardName()+" dengan power "+newCard.getCardPower()+" diambil");
            }

            //Perintah ATTACK: Mengambil kartu dengan nilai Power tertinggi, dikeluarkan dari deck
            else if (command[0].equals("ATTACK")){
                if (deck.isEmpty()){
                    System.out.println("Tidak bisa melakukan Attack");
                }
                else{
                    System.out.println(deck.get(0).getCardName()+ " "+ deck.get(0).getCardPower()+ " dikeluarkan");
                    deck.remove(0);
                }
            }

            //Perintah DEFENSE: Mengambil 3 kartu dengan nilai Power terendah, dikeluarkan dari deck
            else if (command[0].equals("DEFENSE")){
                if (deck.size() < 3){
                    System.out.println("Tidak bisa melakukan Defense");
                }
                else {
                    for (int i = 0; i < 3; i++){
                        System.out.println(deck.get(deck.size()-1).getCardName()+" "+deck.get(deck.size()-1).getCardPower()+" dikeluarkan");
                        deck.remove(deck.size()-1);
                    }
                }
            }

            //Perintah SEE CARD: Melihat sisa kartu yang tersedia di deck, dengan urutan sesuai dengan yang ada di soal
            else if (command[0].equals("SEE")){
                if (deck.isEmpty()){
                    System.out.println("Kartu kosong");
                }
                else {
                    for (int i = 0; i < deck.size(); i++){
                        System.out.println(deck.get(i).getCardName()+ " "+deck.get(i).getCardPower());
                    }
                }
            }
            cmd = br.readLine();
        }

    }

    /**
     * Fungsi quicksort: Untuk mengurutkan objek Kartu sesuai dengan nilai Power. Jika nilai Power sama, akan diurutkan secara
     * leksikografis nama dari Kartu tersebut
     * @param  arr   ArrayList objek Kartu yang akan diurutkan
     * @param  low   Nilai Low
     * @param  high  Nilai High
     */

    public static void quicksort(ArrayList<Kartu> arr, int low, int high) {
        int i = low;
        int j = high;

        //Pivot: Objek Kartu yang menjadi pembatas
        Kartu pivot = arr.get((low + high) / 2);

        //Menggeser objek di sebelah kanan dan kiri Pivor
        while (i <= j) {
            while (arr.get(i).compareTo(pivot) < 0) {
                i++;
            }

            while (arr.get(j).compareTo(pivot) > 0) {
                j--;
            }

            if (i <= j) {
                Kartu tmp = arr.get(i);
                arr.set(i, arr.get(j));
                arr.set(j, tmp);
                i++;
                j--;
            }
        }

        //Menjalankan fungsi quickSort secara rekursif selama belum terurut
        if (low < j) {
            quicksort(arr, low, j);
        }
        if (i < high) {
            quicksort(arr, i, high);
        }
    }
}

/**
 * Object Kartu (mengimplementasi Comparable)
 * Untuk Implementasi fitur dari setiap Kartu dan nilai-nilai yang terkandung di dalamnya
 */

class Kartu implements Comparable<Kartu>{
    private String name;
    private int power;

    public Kartu(String name, int power){
        this.name = name;
        this.power = power;
    }

    public String getCardName(){
        return this.name;
    }

    public int getCardPower(){
        return this.power;
    }

    @Override
    public int compareTo(Kartu otherKartu) {

        //Jika power dari dua kartu sama, akan dibandingkan nama dari objek kartu secara leksikografis
        if (this.power == otherKartu.power){
            return (this.name.compareTo(otherKartu.name));
        }
        return -(this.power - otherKartu.power);
    }
}