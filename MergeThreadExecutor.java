//********************************************************************
//
//  Author:        Marshal Pfluger
//
//  Project #:     four
//
//  File Name:     MergeThreadExecutor.java
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
//  Description:   This class implements callable to handle the thread 
//                 operation of merging the lists
//
//********************************************************************
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class MergeThreadExecutor implements Callable<List<Integer>> {
	// Class variable to hold lists
	private List<Integer> first;
	private List<Integer> second;
	
	//***************************************************************
	//
	//  Method:       MergeThreadExecutor parameterized constructor
	// 
	//  Description:  takes arguments from call and sets the class variables
	//
	//  Parameters:   List<Integer> first, List<Integer> second
	//
	//  Returns:      N/A 
	//
	//**************************************************************
	public MergeThreadExecutor(List<Integer> first, List<Integer> second) {
		// Set class variables 
		this.first = first;
		this.second = second;
	}
	
	//***************************************************************
	//
	//  Method:       call() method override
	// 
	//  Description:  overrides the call method of callable this method merges the two lists in order
	//
	//  Parameters:   N/A
	//
	//  Returns:      List<Integer> 
	//
	//**************************************************************
	@Override
	public List<Integer> call() {
		// Create List to hold the merged list. 
        List<Integer> mergedList = new ArrayList<>();
        // Starting indexes for each list iteration
        int firstIndex = 0;
        int secondIndex = 0;

        // Start loop to iterate through lists and add lowest number to merged list
        while (firstIndex < this.first.size() && secondIndex < this.second.size()) {
        	// Get the current element from each list
            int element1 = this.first.get(firstIndex);
            int element2 = this.second.get(secondIndex);

            // If the first list element is less than second append to merged list
            if (element1 < element2) {
                mergedList.add(element1);
                // Increment list indices
                firstIndex++;
            // If the second list element is less append to merged list
            } 
            else {
                mergedList.add(element2);
                // Increment list indices
                secondIndex++;
            }
        }
        // Add any remaining elements from firstList
        while (firstIndex < this.first.size()) {
            mergedList.add(this.first.get(firstIndex));
         // Increment list indices
            firstIndex++;
        }
        // Add any remaining elements from secondList
        while (secondIndex < this.second.size()) {
            mergedList.add(this.second.get(secondIndex));
         // Increment list indices
            secondIndex++;
        }

        return mergedList;
	}
}
