package com.edu.ssafy_0207;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_B_11286_절댓값힙_조용훈 {	// 26684kb 메모리, 680ms 시간
	static Number[] heap = new Number[100000];
	static int index;
	static int MAX = Integer.MAX_VALUE;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(bufferedReader.readLine());

		for (int i = 0; i < n; i++) {
			int command = Integer.parseInt(bufferedReader.readLine());
			if (command == 0) {
				System.out.println(pop().value);
			} else {
				push(new Number(Math.abs(command), command));
			}
		}
	}
	
	// 배열에서 꺼내고 지우고 우선순위로 다시 정렬 하는 함수
	static Number pop() {
		if (index == 0) {
			return new Number(0, 0);
		}
		Number temp = heap[1];
		heap[1] = heap[index];
		heap[index] = new Number(MAX, MAX);
		--index;
		for (int i = 1; i * 2 <= index;) {
			if (heap[i].AbsValue < heap[i * 2].AbsValue && heap[i].AbsValue < heap[i * 2 + 1].AbsValue) {
				break;
			} else if (heap[i * 2].AbsValue < heap[i * 2 + 1].AbsValue) {
				if (heap[i].AbsValue == heap[i * 2].AbsValue) {
					if (heap[i].value < heap[i * 2].value) {
						break;
					}
				}
				Number tNumber = heap[i];
				heap[i] = heap[i * 2];
				heap[i * 2] = tNumber;
				i = i * 2;
			} else if (heap[i * 2].AbsValue == heap[i * 2 + 1].AbsValue) {
				if (heap[i * 2].value > heap[i * 2 + 1].value) {
					if(heap[i].AbsValue == heap[i*2+1].AbsValue) {
						if (heap[i].value < heap[i * 2 + 1].value) {
							break;
						}
					}
					Number tNumber = heap[i];
					heap[i] = heap[i * 2 + 1];
					heap[i * 2 + 1] = tNumber;
					i = i * 2 + 1;
				} else {
					if(heap[i].AbsValue == heap[i*2].AbsValue) {
						if (heap[i].value < heap[i * 2].value) {
							break;
						}
					}
					Number tNumber = heap[i];
					heap[i] = heap[i * 2];
					heap[i * 2] = tNumber;
					i = i * 2;
				}
			} else {
				if (heap[i].AbsValue == heap[i * 2 + 1].AbsValue) {
					if (heap[i].value < heap[i * 2 + 1].value) {
						break;
					}
				}
				Number tNumber = heap[i];
				heap[i] = heap[i * 2 + 1];
				heap[i * 2 + 1] = tNumber;
				i = i * 2 + 1;
			}

		}
		return temp;
	}

	// 배열에 값을 넣고 우선순위로 다시 정렬 하는 함수
	static void push(Number num) {
		heap[++index] = num;
		for (int i = index; i > 1; i /= 2) {
			if (heap[i].AbsValue > heap[i / 2].AbsValue) {
				break;
			} else if (heap[i].AbsValue == heap[i / 2].AbsValue) {
				if (heap[i].value < heap[i / 2].value) {
					Number temp = heap[i];
					heap[i] = heap[i / 2];
					heap[i / 2] = temp;
				} else {
					break;
				}
			} else {
				Number temp = heap[i];
				heap[i] = heap[i / 2];
				heap[i / 2] = temp;
			}
		}
	}
	
	// 절대값과 원래 값을 동시에 저장하는 객체
	static class Number {
		int AbsValue;
		int value;

		public Number(int absValue, int value) {
			AbsValue = absValue;
			this.value = value;
		}
	}
}
