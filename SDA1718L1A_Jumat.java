/**
 * Lab 01 Struktur Data dan Algoritma (Soal A)
 * Izzan Fakhril Islam (1606875806)
 * originally made by Jahns Christian Albert
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class SDA1718L1A_Jumat {
	/**
		* Method main untuk mengambil input dan menampilkan output
		* Jangan mengubah method ini. Pengubahan method dapat menyebabkan program mengalami error
		*
	*/
	public static void main(String[] args) throws IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int key = Integer.parseInt(in.readLine());
		String str = in.readLine();
		
		while(str != null){
			System.out.println(decrypt(str, key));
			str = in.readLine();
		}
	}

	public static String decrypt(String str, int key){
		key = key % 26;
		char[] chArr = str.toCharArray();
		//@TODO: Lengkapi method dibawah kalimat ini
		for (int i = 0; i < chArr.length; i++){
			int num = (int) chArr[i];
			if (num >= 97 && num <= 122){
				num -= key;
				if (num < 97){
					num = 122 - (96-num);
				}
			}
			else if (num >= 65 && num <= 90) {
				num -= key;
				if (num < 65){
					num = 90 - (64-num);
				}
			}
			chArr[i] = (char) num;
		}
		
		String res = new String(chArr);
		return res;
	}
}