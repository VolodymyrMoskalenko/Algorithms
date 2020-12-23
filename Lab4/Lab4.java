/**
 * cd "/home/vmoskalenko/Documents/KPI/2_semester/алгоритмі/Algorithms/Lab4/" && javac Lab4.java && java Lab4
 * Lab 04
 *
 * @author Moskalenko Volodymyr
 */
import java.io.*;
import java.lang.*;
import java.util.Random;
import java.util.concurrent.TimeUnit;
class Lab4
{
	public static void main(String args[])throws IOException
	{
		int ch;
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		do {
			System.out.println("\n\n\n1.Bubble Sort\n2.Selection Sort\n3.Quick Sort.\n4.Exit.");
			ch=Integer.parseInt(br.readLine());
			if(ch==4)
			{
				return;
			}

			Random r = new Random();
			int n = 2000;
			int [] a = new int[n];
			int last = 0;
			for (int i = 0; i < a.length; i++) 
			{
				last = last + r.nextInt(10) + 1;
				a[i] = last;
			}

			int[] bubbleArr = a.clone();
			int[] selectArr = a.clone();
			int[] quickArr = a.clone();

			switch(ch)
			{
				case 1:
					long timeBub = System.nanoTime();  
					BubbleSort(bubbleArr,n); 
					timeBub = System.nanoTime() - timeBub;  
					System.out.printf("Bubble sort timing: %,9.3f ms\n", timeBub/1_000_000.0);

					long timeBub_dubl = System.nanoTime();
					BubbleSort(bubbleArr,n); 
					timeBub_dubl = System.nanoTime() - timeBub_dubl;  
					System.out.printf("Bubble sort timing: %,9.3f ms\n", timeBub_dubl/1_000_000.0);

					break;
				case 2:
					long timeSel = System.nanoTime();  
					SelectionSort(selectArr,n);
					timeSel = System.nanoTime() - timeSel;  
					System.out.printf("Selection sort timing: %,9.3f ms\n", timeSel/1_000_000.0);

					long timeSel_dubl = System.nanoTime();  
					SelectionSort(selectArr,n);
					timeSel_dubl = System.nanoTime() - timeSel_dubl;  
					System.out.printf("Selection sort timing: %,9.3f ms\n", timeSel_dubl/1_000_000.0);
					break;
				case 3:
					int start=0;
					int end=n-1;
					
					long timeQuick = System.nanoTime();  
					QuickSort(quickArr,start,end);
					timeQuick = System.nanoTime() - timeQuick;  
					System.out.printf("Quick sort timing: %,9.3f ms\n", timeQuick/1_000_000.0);

					long timeQuick_dubl = System.nanoTime();  
					QuickSort(quickArr,start,end);
					timeQuick_dubl = System.nanoTime() - timeQuick_dubl;  
					System.out.printf("Quick sort timing: %,9.3f ms\n", timeQuick_dubl/1_000_000.0);
					//print(a,n);
					break;
			}
			
		} while(ch!=4);
    }
    
	public static void BubbleSort(int a[],int n)
	{
		int temp;
		boolean swap;
		for(int i=0;i<n-1;i++)
		{
			swap=false;
			for(int j=0;j<n-1;j++)
			{
				if(a[j]>a[(j+1)])
				{
					temp=a[j];
					a[j]=a[(j+1)];
					a[(j+1)]=temp;
					swap=true;
				}
			//System.out.print(a[j]);
			}
			//System.out.println();
			if(swap==false)
				break;
		}
		//print(a,n);
	}

	public static void SelectionSort(int a[], int n)
	{
		for (int i=0;i<n-1;i++)
		{
			int imin=i;
			int temp;
			for(int j=i+1;j<n;j++)
			{
				if(a[j]<a[imin])
					imin=j;
			}
					temp=a[i];
					a[i]=a[imin];
					a[imin]=temp;
		}
		//print(a,n);
	}

	public static void InsertionSort(int a[],int n)
	{
		for(int i=1;i<n;i++)
		{
			int val=a[i];
			int hole=i;
			while(hole>0&&a[hole-1]>val)
			{
				a[hole]=a[hole-1];
				hole=hole-1;
			}
			a[hole]=val;
		}
		print(a,n);
	}
	
	public static void QuickSort(int a[],int start,int end)
	{
		if(start<end)
		{
		    int pIndex=QuickPartition(a,start,end);
		    QuickSort(a,start,pIndex-1);
		    QuickSort(a,pIndex+1,end);
		}
		else
			return;
		
	}

	public static int QuickPartition(int a[],int start,int end)
	{
		int temp;
		int pivot=a[end];
		int pIndex=start;
		for(int i=start;i<end;i++)
		{
			if(a[i]<=pivot)
			{
					temp=a[i];
					a[i]=a[pIndex];
					a[pIndex]=temp;
					pIndex++;
			}
		}
        temp=a[pIndex];
        a[pIndex]=a[end];
        a[end]=temp;
        return pIndex;
	}

	public static void print(int a[],int n)
	{
		System.out.println();
		for(int i=0;i<n;i++)
		System.out.print(a[i]+"\t");
    }

    private static long timer(Runnable method, TimeUnit timeUnit) {
        long time = System.nanoTime();
        method.run();
        time = System.nanoTime() - time;
        System.out.println(TimeUnit.NANOSECONDS.convert(time, timeUnit));
        return TimeUnit.NANOSECONDS.convert(time, timeUnit);
    }
}
