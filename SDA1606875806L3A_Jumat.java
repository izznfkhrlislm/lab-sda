/**
 * Lab 3 Struktur Data & Algoritma Jumat (Soal A)
 * Created by: Izzan Fakhril Islam (1606875806)
 */

import java.io.*;

public class SDA1606875806L3A_Jumat {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(br.readLine());
        for (int i = 0; i < count; i++){
            try{
                //Memisahkan input dari user per karakter
                char[] words = br.readLine().toCharArray();
                //Inisiasi objek baru yang dibuat
                IniStack<Character> stack = new IniStack<>(words.length);
                //Untuk setiap iterasi pada array of char, dicek apakah karakter yang diiterasi merupakan karakter
                //pembuka atau penutup
                for (char x : words){
                    if (x == 'L' || x == 'V' || x == '<'){
                        stack.push(x);
                    }
                    //Pengecekan karakter teratas di stack oleh method peek, jika sesuai dengan karakter pembuka nya, baru bisa di pop
                    else if (x == 'O'){
                        if (stack.peek() == 'L'){
                            stack.pop();
                        }
                        else {
                            break;
                        }
                    }
                    else if (x == 'E'){
                        if (stack.peek() == 'V'){
                            stack.pop();
                        }
                        else {
                            break;
                        }
                    }
                    else if (x == '3'){
                        if (stack.peek() == '<'){
                            stack.pop();
                        }
                        else {
                            break;
                        }
                    }
                }
                if (stack.isEmpty()) System.out.println("BAGUS");
                else System.out.println("TIDAK BAGUS");

            }
            catch (IndexOutOfBoundsException e){
                System.out.println("TIDAK BAGUS");
            }
            catch (NullPointerException e){
                System.out.println("TIDAK BAGUS");
            }
        }
    }
}

/**
 * Struktur Data baru, berbasis Array, mengimplementasi perilaku yang dimiliki oleh Stack pada umumnya, T Generic
 * @param <T> Jenis Data Generic yang akan dimasukkan
 */
class IniStack<T> {
    private T[] stack;
    private int top;
    private static int SIZE;

    public IniStack(int s){
        SIZE = s;
        stack = (T[]) new Object[SIZE];
        top = -1;
    }

    public T peek(){
        return stack[top];
    }

    public boolean isEmpty(){
        return (top == -1);
    }

    public T pop(){
        top--;
        T item = stack[top+1];
        return item;
    }

    public void push(T item){
        stack[++top] = item;
    }
}