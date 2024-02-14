package com.edu.ssafy_0214;

import java.util.Scanner;

public class BinarySearchTest {
	static int[] arr = { 2, 4, 7, 9, 11, 19, 23 };

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int num = scanner.nextInt();

		int idx = binarySearch(arr, num, 0, arr.length - 1);
		System.out.println(idx);
		scanner.close();
	}

	static int binarySearch(int[] arr, int num, int start, int end) {
		int mid = 0;
		while (true) {
			mid = (start + end) / 2;
			if (start == end) {
				mid = start;
				break;
			}
			if (arr[mid] == num) {
				break;
			}

			if (arr[mid] < num) {
				start = mid + 1;
			} else {
				end = mid - 1;
			}
		}
		return mid;
	}
}
