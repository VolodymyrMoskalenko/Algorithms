
/**
 * cd "/home/vmoskalenko/Documents/KPI/2_semester/алгоритмі/Algorithms/Lab3/" && javac BinomKoef.java && java BinomKoef
 * Lab 03
 *
 * @author Moskalenko Volodymyr
 */

import java.io.*; 
import java.util.Scanner;

public class BinomKoef { 
    public static void main(String []args){
        //int n = 10, k = 5;
        Scanner scan = new Scanner(System.in);
        System.out.print("Please enter the n: ");
        int n = scan.nextInt();
        System.out.print("Please enter the k: ");
        int k = scan.nextInt();
        scan.close();
        int res = Cnk(n,k);
        System.out.println(res);
    }
    
    public static int Cnk(int n, int k)
    {
        if (k == 0 || k == n) return 1;
        return Cnk(n - 1, k) + Cnk(n - 1, k - 1);
    }
}