/**
 * Lab 2 SDA Soal A (Jumat)
 * Created by Izzan Fakhril Islam (1606875806)
 */
import java.io.*;
import java.util.*;

public class SDA1718L2B {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //LinkedList mobil: Untuk mendata mobil-mobil yang ada di garasi
        LinkedList<String> mobil = new LinkedList<>();

        //HashMap mobilStatus: Untuk menyimpan status mogok mobil
        HashMap<String, Boolean> mobilStatus = new HashMap<>();

        //List Iterator
        Iterator<String> iterator;
        Iterator<String> backIterator;
        String inp = br.readLine();

        while (inp != null){
            String[] words = inp.split(" ");

            //Perintah MASUK
            if (words[0].equals("MASUK")){
                //Masuk dari Timur
                if (words[2].equals("TIMUR")){
                    mobil.addLast(words[1]);
                    mobilStatus.put(words[1], false);
                    System.out.println(words[1]+" masuk melalui pintu TIMUR");
                }
                //Masuk dari Barat
                else {
                    mobil.addFirst(words[1]);
                    mobilStatus.put(words[1], false);
                    System.out.println(words[1]+" masuk melalui pintu BARAT");
                }
            }

            //Perintah KELUARKAN
            else if (words[0].equals("KELUARKAN")){
                iterator = mobil.listIterator();
                backIterator = mobil.descendingIterator();
                int left = 0;
                int right = 0;
                String mogokTerdekatKanan = ""; //Nama mobil yang terdekat di sisi kanan dari target yang mogok
                String mogokTerdekatKiri = ""; //Nama mobil yang terdekat di sisi kiri dari target yang mogok
                String mobilSekarang; //Penunjuk nama mobil yang sedang ditunjuk oleh iterator

                //Jika tidak ada mobil di garasi
                if (!mobilStatus.containsKey(words[1])){
                    System.out.println(words[1]+" tidak ada di garasi");
                }

                //Jika mobil yang ingin dikeluarkan sedang mogok
                else if (mobilStatus.get(words[1]) == true){
                    System.out.println("Mobil "+words[1]+" sedang mogok");
                }

                else {
                    //Memeriksa mobil yang ada di garasi dari garasi sisi Barat
                    while (iterator.hasNext()){
                        mobilSekarang = iterator.next();
                        //Jika ada mobil yang mogok
                        if (mobilStatus.get(mobilSekarang) == true){
                            mogokTerdekatKiri = mobilSekarang;
                        }
                        else if (mobilSekarang.equals(words[1])){
                            break;
                        }
                        left++;
                    }

                    //Memeriksa mobil yang ada di garasi dari garasi sisi Timur
                    while (backIterator.hasNext()){
                        mobilSekarang = backIterator.next();
                        //Jika ada mobil yang mogok
                        if (mobilStatus.get(mobilSekarang) == true){
                            mogokTerdekatKanan = mobilSekarang;
                        }
                        else if (mobilSekarang.equals(words[1])){
                            break;
                        }
                        right++;
                    }

                    //Jika ada mobil yang mogok, baik di sisi kanan maupun sisi kiri
                    if (!mogokTerdekatKanan.equals("") && !mogokTerdekatKiri.equals("")){
                        System.out.println(words[1]+" tidak bisa keluar, mobil "+mogokTerdekatKiri+" dan "+mogokTerdekatKanan+" sedang mogok");
                    }
                    //Jika mobil yang mogok hanya ada di sisi kiri
                    else if (mogokTerdekatKanan.equals("") && !mogokTerdekatKiri.equals("")){
                        System.out.println(words[1]+" keluar melalui pintu TIMUR");
                        mobil.remove(words[1]);
                        mobilStatus.remove(words[1]);
                    }
                    //Jika mobil yang mogok hanya ada di sisi kanan
                    else if (!mogokTerdekatKanan.equals("") && mogokTerdekatKiri.equals("")){
                        System.out.println(words[1]+" keluar melalui pintu BARAT");
                        mobil.remove(words[1]);
                        mobilStatus.remove(words[1]);
                    }
                    //Jika tidak ada mobil yang mogok baik di sisi kanan maupun sisi kiri
                    else if (mogokTerdekatKanan.equals("") && mogokTerdekatKiri.equals("")){
                        if (left > right){
                            System.out.println(words[1]+" keluar melalui pintu TIMUR");
                        }
                        else {
                            System.out.println(words[1]+" keluar melalui pintu BARAT");
                        }
                        mobil.remove(words[1]);
                        mobilStatus.remove(words[1]);
                    }
                }
            }
            //Mobil mogok
            else if (words[0].equals("MOGOK")){
                mobilStatus.put(words[1], true);
            }
            //Mobil servis
            else if (words[0].equals("SERVIS")){
                mobilStatus.put(words[1], false);
            }

            inp = br.readLine();
        }
    }

}
