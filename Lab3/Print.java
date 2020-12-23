/**
 * cd "/home/vmoskalenko/Documents/KPI/2_semester/алгоритмі/Algorithms/Lab3/" && javac Print.java && java Print
 * Lab 03
 *
 * @author Moskalenko Volodymyr
 */

import java.io.*;
import java.util.Scanner;
import java.lang.String;

public class Print { 
    public static void main(String []args) {
        //int num = 12345;
        Scanner scan = new Scanner(System.in);
        System.out.print("Please enter the number: ");
        int num = scan.nextInt();
        scan.close();
        System.out.print("Recursive solution: ");
        WriteNumberRecursive(num);
        System.out.println("");
        System.out.print("Iterative solution: ");
        WriteNumberIterative(num);
        System.out.println("");
    }
    
    public static void WriteNumberRecursive(int a) {
        if (a < 10)
            System.out.print(a);
        else {
            WriteNumberRecursive(a / 10);
            System.out.print(a % 10);
        }
    }  
    
    public static void WriteNumberIterative(int a) {
        Integer iInteger = new Integer(a);
        String s = iInteger.toString();
        for(int i = 0; i < s.length(); i++){
            System.out.print(i < s.length() -1 ? s.substring(i, i + 1) : s.substring(i));
        }
        
    } 
}