/**
 * Lab 01
 *
 * @author Moskalenko Volodymyr
 */
import java.util.Scanner;

public class Lab1 {
    /**
     * Entry point. Tests the method {@code simpleDivision(num)}
     *
     * @param num parameter for {@code simpleDivision(num)}.
     */
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        System.out.print("Please enter the number: ");
        int num = scan.nextInt();
        scan.close();

        System.out.print(num + " = ");
        simpleDivision(num);
    }

    /**
     * Returns the math calculated result
     *
     * @params number the entire parameter.
     * @return void
     */
    static void simpleDivision(int number) {        
        int divisor = 2;

        while (divisor*divisor <= number)
        {
            if (0 == number % divisor)
            {
                System.out.print(divisor);
                number = number / divisor;
                System.out.print('*');
            }
            else if (2 == divisor) 
            {
                divisor = 3;
            }  
            else 
            {
                divisor += 2;
            }
        }
        
        System.out.println(number);
    }

}