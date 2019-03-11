/**
 * Lab 6 Struktur Data & Algoritma (Soal B)
 * @author Izzan Fakhril Islam (1606875806)
 */

import java.util.*;
import java.io.*;

public class SDA1606875806L6B {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        miniLibrary lib = new miniLibrary();

        String cmd = br.readLine();
        while (cmd != null){
            String[] command = cmd.split(" ");

            //Perintah pinjam buku: memasukkan ke dalam hash table
            if (command[0].equals("PINJAM")){
                String borrower = command[1];
                String bookTitle = command[2];
                lib.borrowBook(borrower, bookTitle);
            }

            //Perintah daftar peminjam: menampilkan daftar nama-nama peminjam untuk judul buku tertentu
            else if (command[0].equals("DAFTAR_PEMINJAM")){
                int bookHashCode = lib.hashify(command[1]);
                ArrayList<String> borrowers = (ArrayList<String>) lib.getLibData()[bookHashCode].get(lib.getBookInventory()[bookHashCode].indexOf(command[1]));
                String res = "";
                for (int i = 0; i < borrowers.size(); i++){
                    res = res + borrowers.get(i)+" ";
                }
                System.out.println(res);
            }

            //Perintah kembali: mengembalikan buku, menghapus nama peminjam dari hash table
            else if (command[0].equals("KEMBALI")){
                String borrower = command[1];
                int bookHashCode = lib.hashify(command[2]);
                String bookTitle = (String) lib.getBookInventory()[bookHashCode].get(lib.getBookInventory()[bookHashCode].indexOf(command[2]));
                lib.returnBook(borrower, bookTitle);
            }
           cmd = br.readLine();
        }
    }
}

/**
 * Kelas representasi Hash Map (dalam kasus ini digambarkan sebagai sistem perpustakaan)
 */

class miniLibrary {
    private static final int TABLE_SIZE = 10039;
    private ArrayList<ArrayList<String>>[] library;
    private ArrayList<String>[] bookInventory;

    /**
     * Constructor dari kelas miniLibrary
     */

    public miniLibrary(){
        this.library = new ArrayList[TABLE_SIZE];
        this.bookInventory = new ArrayList[TABLE_SIZE];
        Arrays.fill(this.library, new ArrayList<ArrayList<String>>());
        Arrays.fill(this.bookInventory, new ArrayList<String>());
    }

    /**
     * Method hashify: Membuat angka hash code dari nama buku yang ingin dimasukkan
     * @param name nama buku yang ingin digenerate hash code nya
     * @return hash code dari nama buku tersebut
     */

    int hashify(String name){
        name = name.toLowerCase();
        int hash = 0;
        for (int i = 0; i < name.length(); i++){
            hash += name.charAt(i) - 'a';
        }
        return hash % TABLE_SIZE;
    }

    /**
     * Method borrowBook: untuk meminjam buku dan memasukkan peminjam ke dalam hash table
     * @param borrower nama peminjam buku
     * @param bookTitle judul buku yang ingin dipinjam
     */

    public void borrowBook(String borrower, String bookTitle){
        int bookHashCode = hashify(bookTitle);

        //Kondisi jika buku sebelumnya belum pernah terdaftar di perpustakaan
        if (!bookInventory[bookHashCode].contains(bookTitle) || library[bookHashCode].isEmpty()){
            //inisiasi daftar peminjam untuk buku baru
            library[bookHashCode].add(new ArrayList<String>());
            //memasukkan judul buku ke inventory
            bookInventory[bookHashCode].add(bookTitle);
            //memasukkan nama peminjam ke setiap jenis buku yang memiliki hash sama tapi nama beda
            library[bookHashCode].get(bookInventory[bookHashCode].indexOf(bookTitle)).add(borrower);
            Collections.sort(library[bookHashCode].get(bookInventory[bookHashCode].indexOf(bookTitle)));
        }

        //Kondisi jika buku sudah terdaftar di perpustakaan atau hash code sama tetapi judul beda (collision)
        else {
            library[bookHashCode].get(bookInventory[bookHashCode].indexOf(bookTitle)).add(borrower);
            Collections.sort(library[bookHashCode].get(bookInventory[bookHashCode].indexOf(bookTitle)));
        }
    }

    /**
     * Method returnBook: untuk mengembalikan buku ke perpustakaan dan menghapus nama peminjam
     * @param borrower nama peminjam yang ingin mengembalikan buku
     * @param bookTitle judul buku yang ingin dikembalikan
     */

    public void returnBook(String borrower, String bookTitle){
        int bookHashCode = hashify(bookTitle);
        library[bookHashCode].get(bookInventory[bookHashCode].indexOf(bookTitle)).remove(borrower);
    }

    /**
     * Method getter untuk instance variable dari kelas miniLibrary
     * @return Array of ArrayList<ArrayList<String>> daftar peminjam buku dan Array of ArrayList<String>
     *     daftar judul buku
     */

    public ArrayList[] getLibData(){
        return this.library;
    }

    public ArrayList[] getBookInventory(){
        return this.bookInventory;
    }
}
