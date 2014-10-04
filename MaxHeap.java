import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Random;

/**
 * Heap - A class implementing the heap data structure
 * 
 * @author Colin Heffernan
 * Pseudocode and API from MIT Open Courseware
 * 	Course 6.006: Introduction To Algorithms, Lecture 5: Heaps and Heap Sort
 * 
 */
public class MaxHeap <T extends Comparable<T>> {
	ArrayList<T> heap;
	
	/**
	 * MaxHeap - constructor that builds the heap from a collection of like elements
	 * @param els - the collection of elements with which to build the heap
	 */
	public MaxHeap(Collection<T> els){
		this.heap = new ArrayList<T>(els);
		this.buildMaxHeap();
	}
	
	/**
	 * MaxHeap - overloaded constructor creates a heap with just the specified element
	 * @param el - the element to be added 
	 */
	public MaxHeap(T el){
		this.heap = new ArrayList<T>(); // default capacity of 10
		this.insert(el);
	}
	
	/**
	 * MaxHeap - overloaded constructor creates an empty heap
	 */
	public MaxHeap(){
		this.heap = new ArrayList<T>(); // default capacity of 10
	}
	
	/**
	 * heapSort - sorts the elements in the heap in descending order
	 * @return the array of sorted elements
	 */
	public Collection<T> heapSort(){
		ArrayList<T> temp = new ArrayList<T> (this.heap);
		ArrayList<T> sorted = new ArrayList<T>(this.heap.size());
		for (int i = 0; i < this.heap.size(); i++){
			sorted.add(i, this.extractMax());
		}
		this.heap = temp;
		return sorted;
	}
	
	/** 
	 * buildMaxHeap - builds the heap by sifting down on each non-leave node tree
	 */
	public void buildMaxHeap(){
		/* call maxHeapify on all non-leave nodes of tree */
		for (int i = (this.heap.size() - 1)/2; i >= 0; i --){
			this.siftDown(i);
		}
	}
	
	/**
	 * siftDown - moves the element at the given index down the tree as needed to restore the heap property
	 * @param the index at which to start sifting down
	 */
	private void siftDown(int index){
		int leftIndex = 2*index + 1;
		int rightIndex = 2*index + 2;
		int largestIndex = index;
	    T largest = this.heap.get(largestIndex);
		if (leftIndex < this.heap.size()){
			T leftChild = this.heap.get(leftIndex);
			if (largest.compareTo(leftChild) < 0){
				largestIndex = leftIndex;
				largest = leftChild;
			}
		}
		if (rightIndex < this.heap.size()){
			T rightChild = this.heap.get(rightIndex);
			if (largest.compareTo(rightChild) < 0){
				largestIndex = rightIndex;
				largest = rightChild;
			}
		}
		if (largestIndex != index){
			this.heap.set(largestIndex, this.heap.get(index));
			this.heap.set(index, largest);
			siftDown(largestIndex); // now contains the smaller element
		}
	}
	
	/**
	 * siftUp - siftDown - moves the element at the given index up the tree as needed to restore the heap property
	 * @param the index from which to start sifting
	 */
	private void siftUp(int index){
		int parentIndex = (index - 1) / 2;
		T parent = this.heap.get(parentIndex);
		T child = this.heap.get(index);
		/* swap the child with its parent if it is bigger */
		if (parent.compareTo(child) < 0){
			this.heap.set(parentIndex, child);
			this.heap.set(index, parent);
			siftUp(parentIndex); // up another level
		}
	}
	
	/**
	 * peekMax - look at the max of the heap without removing it
	 */
	public T peekMax(){
		return this.heap.get(0);
	}
	
	/**
	 * extractMax - retrieve and remove the maximum element from the heap
	 * restores the heap property
	 */
	public T extractMax(){
		T max = this.heap.get(0);
		T end = this.heap.get(this.heap.size() -1);
		this.heap.set(0, end);
		this.siftDown(0);
		return max;
	}
	
	/**
	 * insert - inserts the specified element at the end of the heap
	 * @param the element to be inserted
	 */
	public void insert(T el){
		this.heap.add(el);
		this.siftUp(this.heap.size() - 1);
	}

	/**
	 * insertAll - inserts all of the elements at the end of the heap
	 * @param the collection of elements to be inserted
	 */
	public void insertAll(Collection<T> els){
		this.heap.addAll(els);
		this.buildMaxHeap();
	}
	
	/**
	 * toString - prints each element of the heap separated by a comma
	 * order specifies root and relationships among nodes
	 * this really will only work will with simple types
	 * @return the representation of the heap
	 */
	public String toString(){
		String rep = "";
		for (int i = 0; i < this.heap.size() - 1; i++){
			rep += this.heap.get(i).toString() + ", ";
		}
		rep += this.heap.get(this.heap.size() - 1).toString();
		return rep;
	}
	
	/**
	 * main - test construction of heap and its associated methods
	 */
	public static void main(String [] args){
		int [] oldNums = {73, 74, 74, 90, 120, 234, 2, 81, 79, 90, 93};
		ArrayList<Integer> nums = new ArrayList<Integer>(oldNums.length);
		for (int i = 0; i < oldNums.length; i++){
			nums.add(new Integer(oldNums[i]));
		}
		System.out.println("Original set : " + Arrays.toString(nums.toArray()));
		MaxHeap<Integer> mine = new MaxHeap<Integer>(nums);
		System.out.println("As Heap: " + mine.toString());
		Collection<Integer> sorted = mine.heapSort();
		System.out.println("Sorted :" + Arrays.toString(sorted.toArray()));
	}
}
