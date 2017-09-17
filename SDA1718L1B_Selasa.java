import java.io.*;

public class SDA1718L1B_Selasa {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(br.readLine());
        int[] numbers = new int[count];
        int min = 100000;
        int max = 0;

        for (int i = 0; i < count; i++){
            int temp = Integer.parseInt(br.readLine());
            numbers[i] = temp;
        }

        for (int i = 0; i < numbers.length; i++){
            min = Math.min(numbers[i], min);
            max = Math.max(numbers[i], max);
        }
        System.out.println(Math.abs(max-min));
    }
}
