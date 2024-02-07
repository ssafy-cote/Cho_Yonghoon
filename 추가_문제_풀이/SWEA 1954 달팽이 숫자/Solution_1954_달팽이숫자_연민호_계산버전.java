package algo230131;
import java.io.IOException;
import java.util.Scanner;

public class Solution_1954_달팽이숫자_연민호_계산버전 {

	public static void main(String[] args) throws IOException {
//		System.setIn(new FileInputStream("input.txt"));
		Scanner s = new Scanner(System.in);

		StringBuilder sb = new StringBuilder();

		// 우 -> 하 -> 좌 -> 상
		int[] dr = { 0, 1, 0, -1 };
		int[] dc = { 1, 0, -1, 0 };

		int T = s.nextInt();

		for (int tc = 1; tc <= T; tc++) {

			int N = s.nextInt(); // 달팽이 크기

			int[][] map = new int[N][N]; // 2차원 자료구조

			int dir = 0; // 초기 방향 세팅

			// 현재 좌표
			int r = 0;
			int c = -1;

			// 맨 윗 줄 숫자 찍기
			int num;
			for (num = 1; num <= N; num++) {
				r += dr[dir];
				c += dc[dir];
				map[r][c] = num;
			}
			dir++; // 방향전환

			//방향을 바꿔가며 cnt 번씩 두 줄 찍는 것을 반복
			//cnt : 한 라인의 숫자를 찍을 때 찍을 횟수
			for(int cnt=N-1; cnt>=1; cnt--) {
				for (int i = 0; i < 2; i++) {
					for (int j = 0; j < cnt; j++) {
						r += dr[dir];
						c += dc[dir];
						map[r][c] = num++;
					}
					dir = (dir + 1) % 4;
				}
			}
			// 출력
			sb.append('#').append(tc).append('\n');
			for (int[] arr : map) {
				for (int el : arr) {
					sb.append(el).append(' ');
				}
				sb.append('\n');
			}

		}
		System.out.println(sb);
		s.close();
	}

}