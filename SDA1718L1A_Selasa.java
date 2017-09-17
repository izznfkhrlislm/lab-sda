import java.io.*;

public class SDA1718L1A_Selasa {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int lala = 0;
        int momo = 0;
        int nana = 0;
        int max = 0;
        String winner=" ";

        String[] line1 = br.readLine().split(" ");

        int dataCount = Integer.parseInt(line1[0]);
        for (int i = 0; i < dataCount; i++){
            String temp = br.readLine();
            if (temp.equals("Lala")){
                lala++;
            }
            else if (temp.equals("Momo")){
                momo++;
            }
            else if (temp.equals("Nana")){
                nana++;
            }
            else if(temp.equals("Rito")){
                lala--;
                if (lala < 0) lala = 0;
                momo--;
                if (momo < 0) momo = 0;
                nana--;
                if (nana < 0) nana = 0;
            }
        }

        if ((lala > momo) && (lala > nana)){
            max = lala;
            winner = "LALA";
        }
        else if ((momo > lala) && (momo > nana)){
            max = momo;
            winner = "MOMO";
        }
        else if ((nana > momo) && (nana > lala)){
            max = nana;
            winner = "NANA";
        }

        else if ((lala == momo) || (lala == nana)){
            max = lala;
            winner = "DRAW";
        }
        else if ((momo == nana)){
            max = momo;
            winner = "DRAW";
        }

        System.out.println(winner+" "+max);
    }
}
