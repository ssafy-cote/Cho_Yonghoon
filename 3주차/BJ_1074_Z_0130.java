package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_1074_Z_0130 {
	static int cnt;
	static int x, y;
	static int flag;

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine(), " ");

		int n = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		int l = 1;
		for (int i = 0; i < n; i++) {
			l *= 2;
		}
		Z(0, l, 0, l);
	}

	static void Z(int startX, int endX, int startY, int endY) {
		if (flag == 1) {
			// 정답을 출력해도 남은 함수가 돌아서 시간 초과를 발생시킴
			// 모든 함수를 정리하기 위한 표시
			return;
		}
		if ((endX - startX) == 2) {
			for (int i = startX; i < endX; i++) {
				for (int j = startY; j < endY; j++) {
					if (x == i && y == j) {
						System.out.println(cnt);
						flag = 1;
						return;
					} else {
						cnt++;
					}
				}
			}
			return;
		}
		
		int midX = (startX + endX) >> 1;
		int midY = (startY + endY) >> 1;
		
		if (midX <= x && midY <= y) {// 4사분면 일때 나머지 스킵 후 cnt 수정
			cnt += (((endX - startX) * (endX - startX)) >> 2) * 3;
		} else if (midX <= x) {// 3사분면 일때 나머지 스킵 후 cnt 수정
			cnt += ((endX - startX) * (endX - startX) >> 2) * 2;
			Z(midX, endX, startY, midY);
		} else if (midY <= y) {// 1사분면 일때 나머지 스킵 후 cnt 수정
			cnt += ((endY - startY) * (endY - startY) >> 2);
			Z(startX, midX, midY, endY);
			Z(midX, endX, startY, midY);
		} else {// 2사분면 일때
			Z(startX, midX, startY, midY);
			Z(startX, midX, midY, endY);
			Z(midX, endX, startY, midY);
		}
		Z(midX, endX, midY, endY);
	}
}
