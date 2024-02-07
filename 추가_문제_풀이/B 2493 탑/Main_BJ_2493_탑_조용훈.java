package com.edu.ssafy_0205;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_BJ_2493_탑_조용훈 {

	public static void main(String[] args) throws IOException {	// 144980kb 메모리, 748ms 시간
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
		StringBuilder builder = new StringBuilder();

		int n = Integer.parseInt(stringTokenizer.nextToken());

		Stack<Integer> stack = new Stack<>();	// 탑 입력받는 스택
		Stack<Integer> big = new Stack<>();		// 이전까지의 최대 높이 탑의 index 저장 스택
		int[] ans = new int[n];
		stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
		big.add(0);	// 첫번째는 무조건 0
		ans[0] = 0; // 첫번째는 무조건 0
		// 스택에 첫번째 담고 시작
		stack.add(Integer.parseInt(stringTokenizer.nextToken()));
		for (int i = 1; i < n; i++) {
			int temp = Integer.parseInt(stringTokenizer.nextToken());
			// 만약 바로 전 탑의 높이가 이제 설치될 탑보다 큰 경우
			if (temp < stack.peek()) {
				// 현재 가장 높은 탑 index 스택에 저장
				big.add(i);
				// 바로 전 탑이 더 높으니까 바로 전 탑 위치가 정답
				ans[i] = i;
			}else {
				// 바로 전 탑 보다 새로운 탑이 더 큰 경우
				// 큰 탑 스택이 비어있지 않고 이전의 큰 탑의 보다 큰 경우 팝! 해버리고 다시 또 그 전에 큰 탑이랑 비교
				while(!big.isEmpty() && (temp > stack.get(big.peek()))) {
					big.pop();
				}
				// 만약 다 팝 해버려서 없다면 지금 위치가 가장 높은 탑
				if(big.isEmpty()) {
					// 지금 탑이 가장 커서 0이 정답
					ans[i] = 0;
					
					
				}else {
					// 스택이 비어서 탈출한게 아니라면 큰 탑을 만난것이다
					// 그 탑의 위치 + 1 이 정답 (정답의 위치는 1부터 시작이라서)
					ans[i] = big.peek() + 1;
				}
				// 지금 위치를 큰탑 위치 기록 스택에 저장
				big.add(i);
			}
			// 탑 기록
			stack.add(temp);
		}
		for(int i : ans) {
			builder.append(i + " ");
		}
		System.out.println(builder);
	}
}
