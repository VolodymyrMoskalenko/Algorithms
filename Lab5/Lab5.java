/**
 * cd "/home/vmoskalenko/Documents/KPI/2_semester/алгоритмі/Algorithms/Lab5/" && javac Lab5.java && java Lab5
 * Lab 05
 *
 * @author Moskalenko Volodymyr
 */

import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Lab5{
    public static void main(String[] args) {
        Tree<Integer> tree = new Tree<>(2500, null);

        Random r = new Random();
        
        int last = 0;
        long totalTime = 0;
        for (int i = 0; i < 1000; i++) 
        {
            last = last + r.nextInt(10) + 1;
            totalTime += tree.add(last);
        }
        System.out.printf("Add speed total:%,9.3f \n",totalTime/1_000_000.0);
		
        long start = System.nanoTime();
        tree.search(43);
        long finish = System.nanoTime();
        long ans = finish - start;
        System.out.printf("Search speed:%,9.3f\n",ans/1_000_000.0);
		
        tree.remove(43);


        System.out.println("Head of tree " + tree);
        
    }
}

class Tree<T extends Comparable<T>> {
    private T val;
    private Tree left;
    private Tree right;
    private Tree parent;
    private List<T> listForPrint = new ArrayList<>();

    public T val() {
        return val;
    }

    public Tree left() {
        return left;
    }

    public Tree right() {
        return right;
    }

    public Tree parent() {
        return parent;
    }

    public Tree(T val, Tree parent) {
        this.val = val;
        this.parent = parent;
    }


    public void add(T...vals){
        long start = System.nanoTime();
        for(T v : vals){
            add(v);
        }
        long finish = System.nanoTime();
        long timeConsumedMillis = finish - start;
        System.out.printf("Add speed total:%,9.3f \n",timeConsumedMillis/1_000_000.0);
    }

    public long add(T val){
        long start = System.nanoTime();
        if(val.compareTo(this.val) < 0){
            if(this.left==null){
                this.left = new Tree(val, this);
            }
            else if(this.left != null)
                this.left.add(val);
        }
        else{
            if(this.right==null){
                this.right = new Tree(val, this);
            }
            else if(this.right != null)
                this.right.add(val);
        }
        long finish = System.nanoTime();
        long ans = finish - start;
        return ans;
       //System.out.printf("Add speed current element:%,9.3f\n",ans/1_000_000.0);
    }

    private Tree<T> _search(Tree<T> tree, T val){
        if(tree == null) return null;
        switch (val.compareTo(tree.val)){
            case 1: return _search(tree.right, val);
            case -1: return _search(tree.left, val);
            case 0: return tree;
            default: return null;
        }
    }

    public Tree<T> search(T val){
        return _search(this, val);
    }

    public boolean remove(T val){
        long start = System.nanoTime();
        Tree<T> tree = search(val);
        if(tree == null){
            return false;
        }
        Tree<T> curTree;

        if(tree == this){
            if(tree.right!=null) {
                curTree = tree.right;
            }
            else curTree = tree.left;

            while (curTree.left != null) {
                curTree = curTree.left;
            }
            T temp = curTree.val;
            this.remove(temp);
            tree.val = temp;
            long fin1 = System.nanoTime();
            long ans = fin1 - start;
            System.out.printf("Remove speed:%,9.3f\n",ans/1_000_000.0);
            return true;
        }

        if(tree.left==null && tree.right==null && tree.parent != null){
            if(tree == tree.parent.left)
                tree.parent.left = null;
            else {
                tree.parent.right = null;
            }
            return true;
        }

        if(tree.left != null && tree.right == null){
            tree.left.parent = tree.parent;
            if(tree == tree.parent.left){
                tree.parent.left = tree.left;
            }
            else if(tree == tree.parent.right){
                tree.parent.right = tree.left;
            }
            return true;
        }

        if(tree.left == null && tree.right != null){
            tree.right.parent = tree.parent;
            if(tree == tree.parent.left){
                tree.parent.left = tree.right;
            }
            else if(tree == tree.parent.right){
                tree.parent.right = tree.right;
            }
            return true;
        }

        if(tree.right!=null && tree.left!=null) {
            curTree = tree.right;

            while (curTree.left != null) {
                curTree = curTree.left;
            }

            if(curTree.parent == tree) {
                curTree.left = tree.left;
                tree.left.parent = curTree;
                curTree.parent = tree.parent;
                if (tree == tree.parent.left) {
                    tree.parent.left = curTree;
                } else if (tree == tree.parent.right) {
                    tree.parent.right = curTree;
                }
                return true;
            }
            else {
                if (curTree.right != null) {
                    curTree.right.parent = curTree.parent;
                }
                curTree.parent.left = curTree.right;
                curTree.right = tree.right;
                curTree.left = tree.left;
                tree.left.parent = curTree;
                tree.right.parent = curTree;
                curTree.parent = tree.parent;
                if (tree == tree.parent.left) {
                    tree.parent.left = curTree;
                } else if (tree == tree.parent.right) {
                    tree.parent.right = curTree;
                }

                return true;
            }
        }
        return false;
    }


    private void _print(Tree<T> node){
        if(node == null) return;
        _print(node.left);

        listForPrint.add(node.val);
        System.out.print(node + " ");

        if(node.right!=null)
            _print(node.right);
    }

    public void print(){
        System.out.print("Tree: ");
        listForPrint.clear();
        _print(this);
        System.out.println();
    }

    @Override
    public String toString() {
        return val.toString();
    }
}
