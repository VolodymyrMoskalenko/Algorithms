/**
 * cd "/home/vmoskalenko/Documents/KPI/2_semester/алгоритмі/Algorithms/Lab8/" && javac Lab8.java && java Lab8
 * Lab 08
 *
 * @author Moskalenko Volodymyr
 */
import java.io.*;
import java.util.Scanner;


public class Lab8{
    public static void main(String[] args) {
        binToInt();
    }

    private static String[] inputNumber(String s) {

        String x[] = new String[5];
        String y;
        int i=0;
        for (String z : s.split(" ", 2)){
            x[i]=z;
            i++;
        }

        return x;
    }

    private static int[] binaryToInt(String str[]) {
        int sum = 0, a;
        char c;
        int z[] = new int[5];
        
        for (int j = 0; j < 2; j++){
        for (int i = 0; i < str[j].length(); i++) {
            c = str[j].charAt(str[j].length() - 1 - i);

            if(c == '1' || c == '0') {
                if (c == '1')
                    a = 1;
                else a = 0;
            } else break;
            sum += (a * Math.pow(2, i));
        }
        z[j]=sum;
        }
        return z;
    }
    
    private static int ka(int z[]){
        int sum=0;
        
        if (z[0] > z[1])
           sum = 2*(z[0]+z[1]);
        else if(z[0] <= z[1]) sum = z[0] + (z[1]*z[1]);
        
        return sum;
    }

    public static void binToInt() {
        Scanner scan = new Scanner(System.in);
            int sum = 0;
            System.out.println("Введите число в двоичном формате:");
            String s = scan.nextLine();
            
            sum = ka(binaryToInt(inputNumber(s)));
            
            System.out.println("\033[1A\033["+'C'+"\t"+" "+sum);

    }
}