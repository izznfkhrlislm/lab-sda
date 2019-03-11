import java.util.*;
import java.io.*;

public class SDA1606875806L4A {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(br.readLine());
        String nums = br.readLine();
        StringTokenizer st = new StringTokenizer(nums);
        int[] arr = new int[count];

        for (int i = 0; i < arr.length; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int[] res = quicksort(arr, 0, arr.length-1);
        for (int i = 0; i < res.length; i++){
            System.out.print(res[i] + " ");
        }
    }

    public static int[] quicksort(int[] arr, int low, int high){
        int i = low;
        int j = high;

        int pivot = arr[(low+high)/2];

        while (i <= j){
            while (arr[i] < pivot){
                i++;
            }
            while (arr[j] > pivot){
                j--;
            }
            if (i <= j){
                //TODO swap nilai dari i dan j
                int tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
                //TODO increment i
                i++;
                //TODO decrement j
                j--;
            }
        }
        //Rekursif
        if (low < j){
            quicksort(arr, low, j);
        }
        if (i < high){
            quicksort(arr, i, high);
        }
        return arr;
    }
}
