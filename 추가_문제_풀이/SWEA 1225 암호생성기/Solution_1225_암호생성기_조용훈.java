package com.edu.ssafy_0202;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_1225_암호생성기_조용훈 { // 22,796kb 메모리, 127ms 시간
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		for (int t = 1; t <= 10; t++) {
			StringBuilder builder = new StringBuilder();
			st = new StringTokenizer(br.readLine());
			int T = Integer.parseInt(st.nextToken());
			Queue<Integer> queue = new ArrayDeque<>();
			st = new StringTokenizer(br.readLine());
			// 숫자 입력받고 큐에 푸시!
			for (int i = 0; i < 8; i++) {
				queue.add(Integer.parseInt(st.nextToken()));
			}
			int cnt = 1;
			// 큐에 0 을 포함하고 있다면 즉시 종료!
			while (!queue.contains(0)) {
				// 1 ~ 5 루프를 돌기 위해 6이 되면 다시 1로 변환
				if (cnt == 6)
					cnt = 1;
				// 큐에서 팝! 하고 임시 저장
				int temp = queue.poll() - cnt;
				// 0 보다 작은지 확인, 작다면 0으로 변환
				if (temp < 0)
					temp = 0;
				// 다시 큐에 푸시!
				queue.add(temp);
				cnt++;
			}
			// 출력을 위한 StringBuilder
			for (int i = 0; i < 8; i++) {
				builder.append(queue.poll() + " ");
			}
			System.out.println("#" + T + " " + builder);
		}
	}
}
