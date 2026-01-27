package december.binarySearch;

import java.util.Arrays;

/**
 * Problem Statement: Given an array ‘arr of integer numbers, ‘ar[i]’ represents the number of pages in the ‘i-th’ book.
 * There are a ‘m’ number of students, and the task is to allocate all the books to the students.
 * Allocate books in such a way that:
 * <p>
 * Each student gets at least one book.
 * Each book should be allocated to only one student.
 * Book allocation should be in a contiguous manner.
 * You have to allocate the book to ‘m’ students such that the maximum number of pages assigned to a student is minimum.
 * If the allocation of books is not possible. return -1
 * Example 1: Input Format: n = 4, m = 2, arr[] = {12, 34, 67, 90}
 * Result: 113
 * Explanation: The allocation of books will be 12, 34, 67 | 90. One student will get the first 3 books and the other will get the last one.
 * Example 2: Input Format: n = 5, m = 4, arr[] = {25, 46, 28, 49, 24}
 * Result:71
 * Explanation: The allocation of books will be 25, 46 | 28 | 49 | 24.
 */
public class AllocateMinNumPages {
    public static void main(String[] args) {
        int[] pages = new int[]{12, 34, 67, 90};
        //int[] pages = new int[]{25, 46, 28, 49, 24};
        int k = 2;
        System.out.println("Pages---" + Arrays.toString(pages));
        int maxPagesToStudentsIsMin = findMaxPagesToStudentsMin(pages, k);
        System.out.println("maxPagesToStudentsIsMin--" + maxPagesToStudentsIsMin);

        int maxPagesToStudentsIsMinOptimal = findMaxPagesToStudentsMinOptimal(pages, k);
        System.out.println("maxPagesToStudentsIsMinOptimal--" + maxPagesToStudentsIsMinOptimal);

    }

    private static int findMaxPagesToStudentsMinOptimal(int[] pages, int k) {
        int minPages = Integer.MIN_VALUE; // this will have max Pages in given array
        int maxPages = 0; // All total pages
        for (int page : pages) {
            minPages = Math.max(minPages, page);
            maxPages += page;
        }

        while (minPages <= maxPages) {
            int mid = (minPages + maxPages) / 2;
            if (canAllocatePagesToKStudents(pages, mid) > k) {
                minPages = mid + 1;
            } else {
                maxPages = mid - 1;
            }
        }

        return minPages;
    }

    private static int findMaxPagesToStudentsMin(int[] pages, int k) {
        int maxPagesPerStudent = 0;

        int minPages = Integer.MIN_VALUE; // this will have max Pages in given array
        int maxPages = 0; // All total pages
        for (int page : pages) {
            minPages = Math.max(minPages, page);
            maxPages += page;
        }

        for (int currPage = minPages; currPage <= maxPages; currPage++) {
            if (canAllocatePagesToKStudents(pages, currPage) == k) {
                maxPagesPerStudent = currPage;
                break;
            }
        }
        return maxPagesPerStudent;
    }

    private static int canAllocatePagesToKStudents(int[] pages, int currPage) {
        int students = 1;
        int currAllocated = 0;

        for (int page : pages) {
            if (currAllocated + page <= currPage) {
                currAllocated += page;
            } else {
                students++;
                currAllocated = page;
            }
        }
        return students;
    }
}
