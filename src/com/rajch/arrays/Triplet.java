package com.rajch.arrays;

import java.util.*;

// 3 Sum
public class Triplet {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 15};
        int target = 18;

        List<List<Integer>> triplets = tripletsNaive(arr, target);
        System.out.println("Triplets Naive: " + triplets);

        List<List<Integer>> triplets2 = tripletsUsingSet(arr, target);
        System.out.println("Triplets Using Set: " + triplets2);

        List<List<Integer>> triplets3 = tripletsUsing2Pointers(arr, target);
        System.out.println("Triplets Using 2 Pointers : " + triplets3);
    }

    /*
     * Time Complexity: O(NlogN) + O(N^3) = O(N^3)
     * Space Complexity: O(1)
     * */
    private static List<List<Integer>> tripletsNaive(int[] arr, int target) {
        List<List<Integer>> result = new ArrayList<>();

        // Step 1: Sort the array O(NlogN) since sorted output is required
        Arrays.sort(arr);

        int sum = 0;
        // Step 2: Iterate all possible triplets with distinct elements and compare with target O(N^3)
        for (int i = 0; i < arr.length - 2; i++)
            for (int j = i + 1; j < arr.length - 1; j++)
                for (int k = j + 1; k < arr.length; k++) {
                    sum = arr[i] + arr[j] + arr[k];
                    if (sum == target)
                        result.add(Arrays.asList(arr[i], arr[j], arr[k]));
                }

        return result;
    }

    /*
     * Time Complexity: O(NlogN) + O(N^2) = O(N^2)
     * Space Complexity: O(N)
     * Gives incorrect answer if sorted triplets are required
     * */
    private static List<List<Integer>> tripletsUsingSet(int[] arr, int target) {
        List<List<Integer>> result = new ArrayList<>();

        Arrays.sort(arr);

        Set<Integer> arrSet = new HashSet<>();
        for (int i = 0; i < arr.length - 1; i++) {
            // Fixing arr[i] to reduce it to 2 sum problem with range [i+1, N)
            int twoSumTarget = target - arr[i];

            // 2 Sum
            arrSet.clear();
            for (int j = i + 1; j < arr.length; j++) {
                int key = twoSumTarget - arr[j];
                if (arrSet.contains(key))
                    result.add(Arrays.asList(arr[i], key, arr[j]));
                arrSet.add(arr[j]);
            }
        }

        return result;
    }

    /*
     * Time Complexity: O(NlogN) + O(N^2) = O(N^2)
     * Space Complexity: O(1)
     * */
    private static List<List<Integer>> tripletsUsing2Pointers(int[] arr, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();

        Arrays.sort(arr);

        for (int i = 0; i < arr.length - 2; i++) {
            // Fixing arr[i] to reduce it to 2 sum problem within range [i+1,N)
            int j = i + 1;
            int k = arr.length - 1;
            while (j < k) {
                int sum = arr[i] + arr[j] + arr[k];
                if (sum == targetSum) {
                    result.add(Arrays.asList(arr[i], arr[j], arr[k]));
                    j++;
                    k--;
                } else if (sum > targetSum) {
                    k--;
                } else {
                    j++;
                }
            }
        }

        return result;
    }
}
