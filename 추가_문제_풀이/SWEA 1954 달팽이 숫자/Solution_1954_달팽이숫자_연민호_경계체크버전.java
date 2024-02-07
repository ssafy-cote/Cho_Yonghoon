package algo230131;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Solution_1954_달팽이숫자_연민호_경계체크버전 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));
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
			int c = 0;

			// 1~N^2 까지 숫자 찍기
			for (int num = 1; num <= N * N; num++) {

				map[r][c] = num; // 숫자 채우기

				// 다음 위치 좌표 (방향 전환 여부 체크를 위해)
				int nr = r + dr[dir];
				int nc = c + dc[dir];

				// 다음 위치가 경계를 벗어나거나
				// 다음 위치의 요소가 0이 아닌 경우 => 방향전환
				if (nr < 0 || nr >= N || nc < 0 || nc >= N || map[nr][nc] != 0) {
					dir = dir++ % 4;
					// dir = (dir+1)%4;
				}

				// 실제 해당 방향으로 이동
				r += dr[dir];
				c += dc[dir];
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