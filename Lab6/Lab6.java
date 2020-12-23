/**
 * cd "/home/vmoskalenko/Documents/KPI/2_semester/алгоритмі/Algorithms/Lab6/" && javac Lab6.java && java Lab6
 * Lab 06
 *
 * @author Moskalenko Volodymyr
 */
import java.util.ArrayList;
import java.util.Scanner;

public class Lab6{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        //System.out.print("Введите размерность таблицы: ");
		//int n = sc.nextInt();
		int n = 1000;
        System.out.println();
        Hasher<Integer, String> hash = new Hasher<Integer, String>(n);
		long start = System.nanoTime();
        for (int i=0;i<n;i++)
            hash.put(i, "John", "Freek");
		long finish = System.nanoTime();
		long ans = finish -start;
        //hash.printTable();
		System.out.println("Add time - "+ ans/1_000_000.0);
		
		long start1 = System.nanoTime();
		for (int i=0;i<500;i++){
		    String s = hash.get(i);
		}
		long finish1 = System.nanoTime();
		long ans1 = finish1 -start1;
        System.out.println("Search time - "+ ans1/1_000_000.0);
    }
}

class Hasher<K, V> {

	private static class LinkedListNode<K, V> {
		public LinkedListNode<K, V> next;
		public LinkedListNode<K, V> prev;
		public K key;
		public V value, value1;
		public LinkedListNode(K k, V v, V v1) {
			key = k;
			value = v;
			value1 = v1;
		}
		
		public String printForward() {
			String data = "(" + value + " " + value1 + ")";
			if (next != null) {
				return data + "->" + next.printForward();
			} else {
				return data;
			}
		}
	}	
	
	private ArrayList<LinkedListNode<K, V>> arr;
	public Hasher(int capacity) {

		arr = new ArrayList<LinkedListNode<K, V>>();
		arr.ensureCapacity(capacity);
		for (int i = 0; i < capacity; i++) {
			arr.add(null);
		}
	}
	

	public V put(K key, V value, V value1) {
		LinkedListNode<K, V> node = getNodeForKey(key);
		if (node != null) {
			V oldValue = node.value;
			node.value = value;
			return oldValue;
		}
		
		node = new LinkedListNode<K, V>(key, value, value1);
		int index = getIndexForKey(key);
		if (arr.get(index) != null) {
			node.next = arr.get(index);
			node.next.prev = node;
		}
		arr.set(index, node);
		return null;
	}
	
	public V remove(K key) {
		LinkedListNode<K, V> node = getNodeForKey(key);
		if (node == null) {
			return null;
		}
		
		if (node.prev != null) {
			node.prev.next = node.next;
		} else {
			int hashKey = getIndexForKey(key);
			arr.set(hashKey, node.next);
		}
		
		if (node.next != null) {
			node.next.prev = node.prev;
		}
		return node.value;
	}
	
	public V get(K key) {
		if (key == null) return null;
		LinkedListNode<K, V> node = getNodeForKey(key);
		return node == null ? null : node.value;
	}
	
	private LinkedListNode<K, V> getNodeForKey(K key) {
		int index = getIndexForKey(key);
		LinkedListNode<K, V> current = arr.get(index);
		while (current != null) {
			if (current.key == key) {
				return current;
			}
			current = current.next;
		}
		return null;		
	}
	
	public int getIndexForKey(K key) {
		return Math.abs(key.hashCode() % arr.size());
	}
	
	public void printTable() {
		for (int i = 0; i < arr.size(); i++) {
			String s = arr.get(i) == null ? "" : arr.get(i).printForward();
			System.out.println(i + ": " + s);
		}
	}
}