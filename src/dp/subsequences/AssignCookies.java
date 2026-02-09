package dp.subsequences;

import java.util.Arrays;

/**
 * Problem Statement: Consider a scenario where a teacher wants to distribute cookies to students,
 * with each student receiving at most one cookie. Given two arrays, student and cookie,
 * the ith value in the student array describes the minimum size of cookie that the ith student can be assigned.
 * The jth value in the cookie array represents the size of the jth cookie.
 * If cookie[j] >= student[i], the jth cookie can be assigned to the ith student.
 * Maximize the number of students assigned with cookies and output the maximum number.
 * Input : Student = [1, 2, 3] , Cookie = [1, 1], Output :1
 * Explanation : Only the first cookie (1) satisfies the first student (1), therefore only 1 student is content.
 * Input : Student = [1, 2] , Cookie = [1, 2, 3], Output : 2
 * Explanation : Cookie 1 satisfies student 1 and cookie 2 satisfies student 2. Therefore, 2 students are content.
 */
public class AssignCookies {
    public static void main(String[] args) {
        int[] student = new int[]{1, 2};
        int[] cookie = new int[]{1, 2, 3};

        int maxSatisfiedStudent = findMaxSatisfiedStudents(student, cookie);
        System.out.println("maxSatisfiedStudent--->" + maxSatisfiedStudent);
    }

    private static int findMaxSatisfiedStudents(int[] student, int[] cookie) {
        int maxCount = 0;
        Arrays.sort(student);
        Arrays.sort(cookie);

        int s = 0;
        int c = 0;

        while (s < student.length && c < cookie.length) {
            if (cookie[c] >= student[s]) {
                c++;
                s++;
                maxCount++;
            } else {
                c++;
            }
        }
        return maxCount;
    }
}
