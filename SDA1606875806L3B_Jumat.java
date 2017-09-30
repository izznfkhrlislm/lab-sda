/**
 * Lab 3 Struktur Data & Algoritma Jumat (Soal B)
 * Created by: Izzan Fakril Islam (1606875806)
 */

import java.io.*;

public class SDA1606875806L3B_Jumat {
    //Dynamic Programming implementation, array results untuk menyimpan hasil-hasil penghitungan
    static int[] results;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(br.readLine());
        results = new int[10001];
        for (int i = 0; i < count; i++){
            int banyakAnakTangga = Integer.parseInt(br.readLine());
            System.out.println(stepsTaken(banyakAnakTangga));
        }
    }

    //Method untuk menghitung total banyaknya langkah yang diambil
    public static int stepsTaken(int n){
        //Penggunaan konsep Dynamic Programming, jika hasil sudah ada di array memo, return hasil tersebut
        if (results[n] != 0) return results[n];
        if (n <= 3) return n;

        else {
            int akarKuadrat = (int)Math.sqrt(n);
            //Mengiterasi dari 1 hingga floor(akar n), mencari nilai maksimum yang merupakan faktor dari n
            for (int i = akarKuadrat; i > 1; i--){
                if (n % i == 0){
                    //Jika n sebelumnya ada di dalam memo, dibandingkan apakah lebih besar dari nilai baru atau tidak.
                    if (results[n] != 0){
                        results[n] = Math.min(results[n], 1 + stepsTaken(n/i));
                    }
                    //Jika n belum ada di dalam memo, disimpan ke dalam array memo
                    else {
                        results[n] = 1 + stepsTaken(n/i);
                    }
                }
            }
            //Jenis langkah kedua (Turun ke tangga ke n-1)
            if (results[n] != 0){
                results[n] = Math.min(results[n], 1 + stepsTaken(n-1));
            }
            else{
                results[n] = 1 + stepsTaken(n-1);
            }
        }
        return results[n];
    }
}