/**
 * Lab 01 Struktur Data dan Algoritma (Soal B)
 * Izzan Fakhril Islam (1606875806)
 * originally made by Jahns Christian Albert
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class SDA1718L1B_Jumat {
	/**
	 * Method main untuk mengambil input dan menampilkan output
	 * Jangan mengubah method ini. Pengubahan method dapat menyebabkan program mengalami error
	 */
	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());

		int n = Integer.parseInt(st.nextToken());
		long k = Long.parseLong(st.nextToken());
		long[] arr = new long[n];

		st = new StringTokenizer(in.readLine());

		for (int i = 0; i < n; i++) {
			arr[i] = Long.parseLong(st.nextToken());
		}

		System.out.println(solution(arr, k));

	}

	/**
	 * Solution Method (untuk menampilkan solusi dari soal ini)
	 * Menggunakan algoritma tabel frekuensi
	 * @param arr Array Long yang didapat dari input
	 * @param k Bilangan yang ingin dicek jumlah pasangannya
	 * @return Banyaknya pasangan bilangan yang merupakan kelipatan dari k
	 */

	public static long solution(long[] arr, long k) {
		//Constraint soal
		int MAX_INPUT = 100;

		//Membuat array freq, berisi frekuensi angka hasil modulo k
		long[] freq = new long[MAX_INPUT];
		for (int i = 0; i < arr.length; i++) {
			long eNow = arr[i] % k;
			freq[(int) eNow]++;
		}

		//Pengecekan dua nilai pada array frekuensi modulo
		long num = 0;
		for (int i = 1; i <= k/2; i++) {
			if (freq[i] != 0) {
				//Mencari banyaknya pasangan angka pada freq[i]
				if (i != (k-i)){
					num += freq[i] * freq[(int) k - i];
				}
				//Untuk kasus banyak data ganjil, dan nilai tengah
				else {
					num += ((freq[i] * (freq[(int)k-i] - 1)) / 2);
				}
			}
		}
		//Untuk freq[0]
		num += ((freq[0] * (freq[0] - 1)) / 2);
		return num;
	}
}