package com.rajch.sorting;

import java.util.Arrays;
import java.util.Comparator;

public class AllSortingAlgorithms {

    /************************************************ BUBBLE SORT ******************************************************
    * Time complexity : O(N^2)
    * Best Case: O(N)
    * */
    public static void bubbleSort(int[] nums) {
        boolean sorted = true;
        for(int i=0; i<nums.length-1; i++) {
            for(int j=0; j<nums.length-1-i; j++) {
                if(nums[j] > nums[j+1]) {
                    int temp = nums[j+1];
                    nums[j+1] = nums[j];
                    nums[j] = temp;
                    sorted = false;
                }
            }
            if(sorted)  return;
        }
    }

    /********************************************* SELECTION SORT ******************************************************
    * Time Complexity: O(N^2)
    * */
    public static void selectionSort(int[] nums) {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;

        for(int i=0; i<nums.length-1; i++) {
            min = Integer.MAX_VALUE;
            minIndex = -1;
            for(int j=i; j<nums.length; j++) {
                if(nums[j] < min) {
                    min = nums[j];
                    minIndex = j;
                }
            }
            // bring min at front
            int temp = min;
            nums[minIndex] = nums[i];
            nums[i] = temp;
        }
    }

    /*********************************************** INSERTION SORT ****************************************************
    * Time Complexity: O(N^2)
    * Best Case: O(N)
    * */
    public static void insertionSort(int[] nums) {
        for(int i=0; i<nums.length; i++) {
            int key = nums[i];
            int j = i - 1;

            while(j>=0 && nums[j]>key) {
                nums[j+1] = nums[j];
                j--;
            }

            // j+1 is the correct pos for key, elements to right are already shifted
            nums[j+1] = key;
        }
    }

    /********************************************** MERGE SORT *********************************************************
     * Time Complexity: O(N*logN)
     */
    public static void mergeSort(int[] nums, int start, int end) {
        if(start < end) {
            int mid = (start+end)/2;
            mergeSort(nums, start, mid);
            mergeSort(nums, mid+1, end);
            merge(nums, start, mid, end);
        }
    }

    private static void merge(int[] nums, int start, int mid, int end) {
        // creating aux arrays
        int[] left = new int[mid-start+1];
        int[] right = new int[end-mid];

        for(int i=0; i<left.length; i++) left[i] = nums[start+i];
        for(int i=0; i<right.length; i++) right[i] = nums[mid+1+i];

        // merging 2 lists
        int i=0; // ptr to left
        int j=0; // ptr to right
        int k=start;

        while(i<left.length && j<right.length) {
            if(left[i] <= right[j]) {
                nums[k++] = left[i++];
            } else {
                nums[k++] = right[j++];
            }
        }

        // copying remaining elements if any
        while(i<left.length) nums[k++] = left[i++];
        while(j<right.length) nums[k++] = right[j++];
    }
    /********************************************** QUICK SORT *********************************************************
     * Time Complexity: O(N*logN)
     * Avg Case: O(N*logN)
     * Worst Case: O(N^2)
     */

    public static void quickSort(int[] nums, int start, int end) {
        if(start < end) {
            int pivotIndex = end;
            int pivotPos = partition(nums, start, end, pivotIndex);
            System.out.println("Pivot: "+nums[pivotPos]+" PivotPos: "+pivotPos);
            quickSort(nums, start, pivotPos-1);
            quickSort(nums, pivotPos+1, end);
        }
    }

    private static int partition(int[] nums, int start, int end, int pivotIndex) {
        int pivot = nums[pivotIndex];

        int i = start-1; // ptr for end of left partition
        int j = start; // ptr for end of right partition

        while(j<end) {
            if(nums[j] < pivot) {
                // expand left partition
                int temp = nums[i+1];
                nums[i+1] = nums[j];
                nums[j] = temp;
                i++;
            }
            j++;
        }

        // correct position of pivot: end of left partition: i+1
        nums[pivotIndex] = nums[i+1];
        nums[i+1] = pivot;
        return i+1;
    }
    /******************************************************************************************************************/

    public static void main(String[] args) {
        int[] nums = new int[]{10, 5, 6, 20, 13, 40, 1, 5};
//        bubbleSort(nums);
//        selectionSort(nums);
//        insertionSort(nums);
//        mergeSort(nums, 0, nums.length-1);
        quickSort(nums, 0, nums.length-1);
        for(int x: nums)
            System.out.print(x + " ");
        System.out.println();
    }

    public int removeCoveredIntervals(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        int result = intervals.length;
        int previous = 0;

        for(int i=1; i<intervals.length; i++) {
            if(isInPreviousInterval(intervals[i], intervals[0])) {
                result--;
            } else {
                previous = i;
            }
        }

        return result;
    }

    private boolean isInPreviousInterval(int[] interval, int[] previous) {
        if(previous[0]<=interval[0] && previous[1]>=interval[1]) return true;
        return false;
    }
}
