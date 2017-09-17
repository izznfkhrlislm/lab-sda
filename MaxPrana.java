/*
* Lab SDA Senin 1B
* Created by Izzan Fakhril Islam
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MaxPrana {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] line1 = br.readLine().split(" ");
        int dataCount = Integer.parseInt(line1[0]);
        int subArrayCount = Integer.parseInt(line1[1]);
        int[] ints = new int[dataCount];
        int currentVal = 0;
        int max = 0;

        for (int i = 0; i < dataCount; i++) {
            ints[i] = Integer.parseInt(br.readLine());
        }

        //first n values
        for (int i = 0; i < subArrayCount; i++) {
            currentVal += ints[i];
            if (currentVal > max) max = currentVal;
        }

        //next values
        for (int i = subArrayCount; i < dataCount; i++) {
            currentVal += ints[i];
            currentVal -= ints[i - subArrayCount];
            if (currentVal > max) max = currentVal;
        }
        System.out.println(max);
    }
}
