/**
 * Lab 6 Struktur Data & Algoritma (Soal A)
 * @author Izzan Fakhril Islam (1606875806)
 */

import java.util.*;
import java.io.*;

public class SDA1606875806L6A {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        minHeapTree mhp = new minHeapTree();
        String cmd = br.readLine();

        while (cmd != null){
            String[] commands = cmd.split(" ");

            //Perintah untuk memasukkan angka kedalam Binary Heap Tree
            if (commands[0].equals("INSERT")){
                mhp.insert(Integer.parseInt(commands[1]));
            }

            //Perintah untuk menghapus angka dari Binary Heap Tree
            else if (commands[0].equals("REMOVE")){
                mhp.remove();
            }

            //Perintah untuk menampilkan jumlah operasi Percolate Up pada suatu bilangan di Binary Heap Tree
            else if (commands[0].equals("NUM_PERCOLATE_UP")){
                int num = Integer.parseInt(commands[1]);
                if (!mhp.getHeapArray().contains(num)){
                    System.out.println("percolate up 0");
                }
                else {
                    mhp.percolatingUp(mhp.getHeapArray().indexOf(num));
                    System.out.println("percolate up "+mhp.getPercolateUpFreq()[num]);
                }
            }

            //Perintah untuk menampilkan jumlah operasi Percolate Down pada suatu bilangan di Binary Heap Tree
            else if (commands[0].equals("NUM_PERCOLATE_DOWN")){
                int num = Integer.parseInt(commands[1]);
                if (!mhp.getHeapArray().contains(num)){
                    System.out.println("percolate down 0");
                }
                else {
                    mhp.percolatingDown(mhp.getHeapArray().indexOf(num));
                    System.out.println("percolate down "+mhp.getPercolateDownFreq()[num]);
                }
            }

            //Perintah untuk menampilkan banyaknya elemen di dalam Binary Heap Tree
            else if (commands[0].equals("NUM_ELEMENT")){
                System.out.println("element "+mhp.size());
            }
            cmd = br.readLine();
        }
    }
}

/**
 * Kelas untuk merepresentasikan objek Binary Heap Tree dan properti nya
 */

class minHeapTree {
    private ArrayList<Integer> heapArray; //ArrayList representasi Binary Heap Tree
    private int[] percolateUp; //Array untuk menyimpan frekuensi operasi percolate up setiap angka
    private int[] percolateDown; //Array untuk menyimpan frekuensi operasi percolate down setiap angka
    private int index;
    private static final int CAPACITY  = 100000;

    /**
     * Constructor dari kelas minHeapTree
     */

    public minHeapTree(){
        heapArray = new ArrayList<>();
        percolateUp = new int[CAPACITY];
        percolateDown = new int[CAPACITY];
        index = 0;
    }

    /**
     * Method insert: untuk memasukkan elemen ke dalam Binary Heap Tree
     * @param i elemen yang ingin dimasukkan
     */

    public void insert(int i){
        //Jika Tree kosong, maka i akan langsung ditambahkan di root (index pertama)
        if (heapArray.isEmpty()){
            heapArray.add(i);
            percolateUp[i] = 0;
            percolateDown[i] = 0;
            index++;
        }
        //Jika Tree tidak kosong, maka i akan ditambahkan di index terakhir dan dilakukan operasi percolate down
        else {
            heapArray.add(i);
            percolateUp[i] = 0;
            percolateDown[i] = 0;
            index++;
            percolatingUp(index-1);
        }
        System.out.println("elemen dengan nilai "+i+" telah ditambahkan");
    }

    /**
     * Method remove: untuk menghapus elemen pada index pertama (root) dari Tree
     */

    public void remove(){
        if (heapArray.isEmpty()){
            System.out.println("min heap kosong");
        }
        else {
            int removed = heapArray.get(0);
            swap(0, index-1);
            heapArray.remove(index-1);
            index--;
            percolatingDown(0);
            System.out.println(removed+" dihapus dari heap");
        }
    }

    /**
     * Method percolatingUp: Untuk melakukan operasi Percolating Up pada Tree secara rekursif
     * @param indexOfArray index pada array Tree sebagai starting point operasi
     */

    public void percolatingUp(int indexOfArray){
        if (indexOfArray > 0){ //bukan root
            int parentIndex = (indexOfArray-1)/2;
            int node = heapArray.get(indexOfArray);
            int parentNode = heapArray.get(parentIndex);
            if (parentNode > node){
                swap(parentIndex, indexOfArray);
            }
            percolateUp[heapArray.get(parentIndex)]++;
            percolatingUp(parentIndex);
        }
    }

    /**
     * Method percolatingDown: Untuk melakukan operasi Percolating Down pada Tree secara rekursif
     * @param indexOfArray index pada array Tree sebagai starting point operasi
     */

    public void percolatingDown(int indexOfArray){
        int leftIndex, rightIndex, minValue;

        leftIndex = 2*indexOfArray + 1;
        rightIndex = 2*indexOfArray + 2;

        if (leftIndex < index && heapArray.get(leftIndex) < heapArray.get(indexOfArray)){
            minValue = leftIndex;
        }
        else {
            minValue = indexOfArray;
        }
        if (rightIndex < index && heapArray.get(rightIndex) < heapArray.get(minValue)){
            minValue = rightIndex;
        }
        if (minValue != indexOfArray){
            swap(indexOfArray, minValue);
            percolateDown[heapArray.get(minValue)]++;
            percolatingDown(minValue);
        }
    }

    /**
     * Method swap: untuk menukar dua objek di array berdasarkan index nya
     * @param origin index dari objek yang ingin ditukar
     * @param target index dari objek yang akan ditukar
     */

    public void swap(int origin, int target){
        int originVal = heapArray.get(origin);
        int targetVal = heapArray.get(target);
        heapArray.set(origin, targetVal);
        heapArray.set(target, originVal);
    }

    /**
     * Method size: untuk mendapatkan informasi ukuran array Tree berdasarkan pointer index
     * @return informasi ukuran array Tree
     */

    public int size(){
        return index;
    }

    /**
     * Getter dari variabel yang ada di objek minHeapTree (heapArray, percolateUp, percolateDown)
     * @return
     */

    public ArrayList<Integer> getHeapArray() {
        return this.heapArray;
    }

    public int[] getPercolateUpFreq(){
        return this.percolateUp;
    }

    public int[] getPercolateDownFreq(){
        return this.percolateDown;
    }
}
