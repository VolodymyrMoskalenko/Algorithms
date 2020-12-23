/**
 * cd "/home/vmoskalenko/Documents/KPI/2_semester/алгоритмі/Algorithms/Lab7/" && javac Lab7.java && java Lab7
 * Lab 07
 *
 * @author Moskalenko Volodymyr
 */
import java.io.*;
import java.util.Random;

public class Lab7
{
	public static void main(String[] args) {
	    Random random = new Random();
		int a[][] = new int[][]
		            {{-1, -1, -1, -1, 7, -1, -1, -1, -1},
		             {-1, -1, -1, 3, -1, 8, -1, -1, -1},
		             {-1, -1, 8, -1, 1, -1, 0, -1, -1},
		             {-1, 2, -1, 7, -1, 4, -1, 4,-1},
		             {4, -1, 5,-1, 2, -1, 6, -1, 5}};
	    
	    int min=sumMin(a);
	    int max=sumMax(a);
	    
	    System.out.println("Max: "+max+"\nMin: "+min);
	    
	    for (int i=0;i<5;i++){
	        for (int j=0;j<9;j++){
	            if (a[i][j]!=-1){
	                a[i][j]=random.nextInt((10)+1);
	            }
	        }
	    }
	    
	    for (int i=0;i<5;i++){
	        for (int j=0;j<9;j++){
	            System.out.print(a[i][j]+" ");
	        }
	        System.out.println();
	    }
	    
	    min=sumMin(a);
	    max=sumMax(a);
	    System.out.println("Max: "+max+"\nMin: "+min);
	    
	}
	
	public static int sumMin(int a[][]){
	    int j=4;
	    int min=a[0][j];
	    for (int i = 1; i < 5; i++){
	        if (j!=8 || j!=0){
	        if (a[i][j-1]==a[i][j+1]){
	            
	            min+=a[i][j-1];
	            
	            if (i!=4){
	                if (a[i+1][j-2]<a[i+1][j+2]){
	                    j--;
	                }else
	                if (a[i+1][j-2]>a[i+1][j+2]){
	                    j++;
	                }
	            }
	        }else
	        
	        if (a[i][j-1]<a[i][j+1]){
	            min+=a[i][j-1];
	            j--;
	        }else
	        if (a[i][j-1]>a[i][j+1]){
	            min+=a[i][j+1];
	            j++;
	        }
	        }
	   
	    }
	    return min;
	    
	}
	
	public static int sumMax(int a[][]){
	    int j=4;
	    int max=a[0][j];
	    for (int i = 1; i < 5; i++){
	        if (j!=0 || j!=8){
				if (a[i][j-1]==a[i][j+1]){
					
					max+=a[i][j-1];
					
					if (i!=4){
						if (a[i+1][j-2]<a[i+1][j+2]){
							j++;
						}else 
						if (a[i+1][j-2]>a[i+1][j+2]){
							j--;
						}
				    }
	            }else if (a[i][j-1]<a[i][j+1]){
					max+=a[i][j+1];
					j++;
	        	}else
	       			 if (a[i][j-1]>a[i][j+1]){
	            	max+=a[i][j-1];
					j--;
				}
	        }
	    }
	    
	    return max;
	}
}
