package javafoundations;

import javafoundations.LinkedMaxHeap;
import java.util.Vector;

/**
 * HeapSort: Sorts a collection of Comparable object, in ascending order.
 * Uses a MaxHeap to do the sorting

 * @author (SK)
* @version (3/25/2021)

 */
public class HeapSort<T extends Comparable<T>> {
    //instance variables
    private MaxHeap<T> maxHeap;

    /**
     * constructor
     */
    public HeapSort() {
        maxHeap = new LinkedMaxHeap<T>();
    }

    /**
     * sorts the items in the input vector in descending order.
     * @param toSort<T>	Vector of items to be sorted
     * @return 		Vector containing the items in ascending order
     */
    public Vector<T> sortInDescending(Vector<T> toSort) {
        //create the Vector to be returned at the end
        Vector<T> result = new Vector<T>();
        //add each item from the input Vector to the maxHeap
        for (int i = 0; i< toSort.size(); i++)
            maxHeap.add(toSort.get(i));
        //remove item by item from the maxHeap,
        //and add them to the vector to be returned
        for (int i = 0; i<toSort.size(); i++) {
            result.add(maxHeap.removeMax());
        }
        //return the sorted vector
        return result;
    }

    /**
     * sorts the items in the input vector in ascending order.
     * @param toSort<T>	Vector of items to be sorted
     * @return 		Vector containing the items in ascending order
     */
    public Vector<T> sortInAscending(Vector<T> toSort) {
        //create the Vector to be returned at the end
        Vector<T> result = new Vector<T>();

        //sort input in descending order first
        Vector<T> temp = sortInDescending(toSort);

        //sort in *increasing order* just by reading the temp vector
        //from right to left
        for (int i=temp.size()-1; i>=0; i--) {
            result.add(temp.get(i));
        }
        //return the sorted vector
        return result;
    }

    public static void main(String[] args) {
        //create the (heap) sorter
        HeapSort<Integer> mySortingMachine = new HeapSort<Integer>();

        //create input data
        Vector<Integer> data = new Vector<Integer>();
        data.add(2); data.add(-2); data.add(20); data.add(11); data.add(8);
        System.out.println("Input data: " + data);

        //sort input in descending order
        Vector<Integer> outVector = mySortingMachine.sortInDescending(data);
        System.out.println("\nPrinting sorted results, from hi to low:");
        System.out.println(outVector);

        System.out.println("\nPrinting sorted results, from low to hi:");
        outVector = mySortingMachine.sortInAscending(data);
        System.out.println(outVector);
    }
}
