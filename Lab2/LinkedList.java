/**
 * cd "/home/vmoskalenko/Documents/KPI/2_semester/алгоритмі/Algorithms/Lab2/" && javac LinkedList.java && java LinkedList
 * Lab 02
 *
 * @author Moskalenko Volodymyr
 */

import java.io.*; 
import java.util.Scanner;
import java.util.Random;

public class LinkedList { 
	Node head; 

	static class Node { 
		int data; 
		Node next; 

		Node(int d) 
		{ 
			data = d; 
			next = null; 
		} 
	} 

	public static LinkedList insert(LinkedList list, int data) 
	{ 
		Node new_node = new Node(data); 
		new_node.next = null; 
 
		if (list.head == null) { 
			list.head = new_node; 
		} 
		else { 
			Node last = list.head; 
			while (last.next != null) { 
				last = last.next; 
			} 
			last.next = new_node; 
		} 

		return list; 
	} 

	public static LinkedList insertHead(LinkedList list, int data) 
	{ 
		Node new_node = new Node(data); 
		new_node.next = null; 
 
		if (list.head == null) { 
			list.head = new_node; 
		} 
		else { 
			new_node.next = list.head;
			list.head = new_node; 
		} 

		return list; 
	} 

    public static LinkedList insertPreDefinedPlace(LinkedList list, int data) 
	{ 
        Node new_node = new Node(data); 
        Node temp = new Node(data);  
		new_node.next = null; 
		temp.next = null;
       // Scanner scan = new Scanner(System.in);
      //  System.out.print("Please enter the number for location: ");
      //  int num = scan.nextInt();
      //  scan.close();
       int num = 5;
		if (list.head == null) { 
			list.head = new_node; 
		} 
		else { 
            temp = list.head;
            for(int i=0;i<num;i++)  
            {  
                temp = temp.next;    
            }  
            new_node.next = temp.next;   
            temp.next = new_node; 
		} 
		return list; 
    } 

	public static LinkedList changeHeadElement(LinkedList list, int data) 
	{ 
		Node new_node = new Node(data); 
		new_node.next = null; 
 
		if (list.head == null) { 
			list.head = new_node; 
		}
		else { 
			new_node.next = list.head;
			list.head = new_node; 
		} 

		return list; 
	} 


	public static void printList(LinkedList list) 
	{ 
		Node currNode = list.head; 

		System.out.print("\nLinkedList: "); 

		while (currNode != null) { 
			System.out.print(currNode.data + " "); 
			currNode = currNode.next; 
		} 
		System.out.println("\n"); 
	} 

	public static LinkedList deleteByKey(LinkedList list, int key) 
	{ 
		Node currNode = list.head, prev = null; 

		if (currNode != null && currNode.data == key) { 
			list.head = currNode.next; 
			System.out.println(key + " found and deleted"); 
			return list; 
		} 

		while (currNode != null && currNode.data != key) { 
			prev = currNode; 
			currNode = currNode.next; 
		} 

		if (currNode != null) { 
			prev.next = currNode.next; 
			System.out.println(key + " found and deleted"); 
		} 

		if (currNode == null) {  
			System.out.println(key + " found"); 
		} 
 
		return list; 
	} 
 
	public static LinkedList deleteAtPosition(LinkedList list, int index) 
	{ 
		Node currNode = list.head, prev = null; 

		if (index == 0 && currNode != null) { 
			list.head = currNode.next; 
			System.out.println(index + " position element deleted "); 
			return list; 
		} 

		int counter = 0; 
 
		while (currNode != null) { 

			if (counter == index) { 
				prev.next = currNode.next; 
				System.out.println(index + " position element deleted "); 
				break; 
			} 
			else { 
				prev = currNode; 
				currNode = currNode.next; 
				counter++; 
			} 
		} 

		if (currNode == null) { 
			System.out.println(index + " position element not found"); 
		} 

		return list; 
	} 

	public static LinkedList deleteZeroPosition(LinkedList list) 
	{ 
		Node currNode = list.head; 

		if ( currNode != null) { 
			list.head = currNode.next;  
		  }
		  return list;
	} 

	public static LinkedList deleteLastPosition(LinkedList list) 
	{ 
		
		Node currNode = list.head, prev = null; 
 
		while (currNode.next != null) { 
			prev = currNode;
			currNode = currNode.next;
		} 
		
		prev.next = null;

		return list; 
	} 
	public static void main(String[] args) 
	{ 
		LinkedList list = new LinkedList(); 
		 

		Random r = new Random();
		int n = 2000;
		int last = 0;
		long ins = System.nanoTime(); 
		for (int i = 0; i < n; i++) 
		{
			last = last + r.nextInt(10) + 1;
			list = insert(list, last);
		}
		ins = System.nanoTime() - ins;  
		System.out.println("Total sum of 2000 elements :  " + last);
		System.out.printf("Inserting in  2000 elements timing: %,9.3f ms\n", ins/1_000_000.0);
	
		long insHead = System.nanoTime(); 
		for (int i = 0; i < n; i++) 
		{
			last = last + r.nextInt(10) + 1;
			list = insertHead(list, last);
		}
		insHead = System.nanoTime() - insHead;  
		System.out.printf("Inserting in head 2000 elements timing: %,9.3f ms\n", insHead/1_000_000.0);

		long insAny = System.nanoTime(); 
		for (int i = 0; i < n; i++) 
		{
			last = last + r.nextInt(10) + 1;
			list = insertPreDefinedPlace(list, last);
		}
		insAny = System.nanoTime() - insAny;  
		System.out.printf("Inserting in pre defined place 2000 elements timing: %,9.3f ms\n", insAny/1_000_000.0);

		long delLast = System.nanoTime(); 
		for (int i = 0; i < n; i++) 
		{
			list = deleteLastPosition(list);
		}
		delLast = System.nanoTime() - delLast;  
		System.out.printf("Delete last position 2000 elements timing: %,9.3f ms\n", delLast/1_000_000.0);
        
		long delZero = System.nanoTime(); 
		for (int i = 0; i < n; i++) 
		{
			list = deleteZeroPosition(list);
		}
		delZero = System.nanoTime() - delZero;  
		System.out.printf("Delete zero position 2000 elements timing: %,9.3f ms\n", delZero/1_000_000.0);
 
		deleteByKey(list, 1); 
		//printList(list); 

		deleteByKey(list, 4); 
		//printList(list); 

		deleteByKey(list, 10); 
		//printList(list); 

		deleteAtPosition(list, 0); 
		//printList(list); 

		deleteAtPosition(list, 2); 
		//printList(list); 

		deleteAtPosition(list, 10); 
		//printList(list); 
	} 
} 
