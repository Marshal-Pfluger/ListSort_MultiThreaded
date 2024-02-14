//********************************************************************
//
//  Author:        Marshal Pfluger
//
//  Project #:     four
//
//  File Name:     SortThreadExecutor.java
//
//  Course:        COSC 4302 Operating Systems
//
//  Due Date:      10/05/2023
//
//  Instructor:    Prof. Fred Kumi
//
//  Java Version:  17.0.8+7
//
//  Chapter:       4
//
//  Description:   This class uses callable to sort the list and 
//                 return the result to call location
//
//********************************************************************

import java.util.*;
import java.util.concurrent.Callable;

public class SortThreadExecutor implements Callable<List<Integer>> {
	// Class variables to hold sort list
	private List<Integer> listToSort;
	
	//***************************************************************
	//
	//  Method:       SortThreadExecutor parameterized constructor
	// 
	//  Description:  takes arguments from call and sets the class variables
	//
	//  Parameters:   List<Integer> list
	//
	//  Returns:      N/A 
	//
	//**************************************************************
	public SortThreadExecutor(List<Integer> list) {
		this.listToSort = list;
	}
	
	//***************************************************************
	//
	//  Method:       SortingAlgorithm
	// 
	//  Description:  uses quick sort to sort through the list
	//
	//  Parameters:   List<Integer> unsortedList, int low, int high
	//
	//  Returns:      N/A 
	//
	//**************************************************************
	public List<Integer> sortingAlgorithm(List<Integer> unsortedList, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(unsortedList, low, high);
            sortingAlgorithm(unsortedList, low, pivotIndex - 1);
            sortingAlgorithm(unsortedList, pivotIndex + 1, high);
        }
        return unsortedList;
	}
	
	//***************************************************************
	//
	//  Method:       partition
	// 
	//  Description:  the partition of quick sort
	//
	//  Parameters:   List<Integer> unsortedList, int low, int high
	//
	//  Returns:      int 
	//
	//**************************************************************
    public static int partition(List<Integer> sortingAlgorithm, int low, int high) {
        int pivot = sortingAlgorithm.get(high);
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (sortingAlgorithm.get(j) < pivot) {
                i++;
                int temp = sortingAlgorithm.get(i);
                sortingAlgorithm.set(i, sortingAlgorithm.get(j));
                sortingAlgorithm.set(j, temp);
            }
        }
        int temp = sortingAlgorithm.get(i + 1);
        sortingAlgorithm.set(i + 1, sortingAlgorithm.get(high));
        sortingAlgorithm.set(high, temp);
        return i + 1;
    }
	
	//***************************************************************
	//
	//  Method:       call() method override
	// 
	//  Description:  overrides the call method of callable this method sorts the passed list
	//
	//  Parameters:   N/A
	//
	//  Returns:      List<Integer> 
	//
	//**************************************************************
	@Override
	public List<Integer> call() {
		// Return to call location
		return sortingAlgorithm(this.listToSort, 0, this.listToSort.size() - 1);
	}
}