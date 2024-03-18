package algo0229;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SW_2382_미생물격리_0229 {

	static int ans;
	static int[][] map;
	static Microbe[][] sumMap;
	static int N, M, K;

	// 0 버리는 인덱스
	static int[] dx = { 0, -1, 1, 0, 0 };
	static int[] dy = { 0, 0, 0, -1, 1 };
	static Microbe[] microbes;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

		int t = Integer.parseInt(stringTokenizer.nextToken());
		StringBuilder builder = new StringBuilder();
		for (int T = 1; T <= t; T++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
			N = Integer.parseInt(stringTokenizer.nextToken());
			M = Integer.parseInt(stringTokenizer.nextToken());
			K = Integer.parseInt(stringTokenizer.nextToken());
			microbes = new Microbe[K];

			for (int i = 0; i < K; i++) {
				stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
				int x = Integer.parseInt(stringTokenizer.nextToken());
				int y = Integer.parseInt(stringTokenizer.nextToken());
				int sum = Integer.parseInt(stringTokenizer.nextToken());
				int dir = Integer.parseInt(stringTokenizer.nextToken());
				microbes[i] = new Microbe(x, y, sum, dir, true);
			}

			// 1은 움직이기 전의 미생물, 2는 움직이고 난 후의 미생물
			// 움직일때 1이 있으면 상관없이 움직이기, 2가 있다면 합쳐지는 지점
			for (int i = 0; i < M; i++) {
				map = new int[N][N];
				sumMap = new Microbe[N][N];
				move();
			}
			ans = 0;
			for (Microbe microbe : microbes) {
				if (microbe.isAlive)
					ans += microbe.sum;
			}
			builder.append("#").append(T).append(" ").append(ans).append("\n");
		}
		System.out.println(builder);
	}

	static void move() {
		Arrays.sort(microbes);
		for (Microbe microbe : microbes) {
			int dir = microbe.dir;
			if (microbe.isAlive) {
				int nx = microbe.x + dx[dir];
				int ny = microbe.y + dy[dir];

				// 만약 이동할 위치가 테두리에 해당된다면
				if (check(nx, ny)) {
					// 방향 바꿔주기
					if (dir > 2) {
						microbe.dir = dir % 2 + 3;
					} else {
						microbe.dir = dir % 2 + 1;
					}
					microbe.sum /= 2;
					if (microbe.sum < 1) {
						microbe.isAlive = false;
						continue;
					}
				}

				// 움직일곳에 숫자 확인
				// 합치는 경우
				if (map[nx][ny] == 2) {
					Microbe temp = sumMap[nx][ny];
					if (temp.sum > microbe.sum) {
						temp.sum += microbe.sum;
						sumMap[nx][ny] = temp;
						microbe.isAlive = false;
					} else {
						microbe.sum += temp.sum;
						sumMap[nx][ny] = microbe;
						temp.isAlive = false;
					}

				} else {
					map[nx][ny] = 2;
					sumMap[nx][ny] = microbe;
				}
				microbe.x = nx;
				microbe.y = ny;
			}
		}
	}

	static boolean check(int x, int y) {
		return (x <= 0 || N - 1 <= x || y <= 0 || N - 1 <= y);
	}

	static class Microbe implements Comparable<Microbe> {
		int x;
		int y;
		int sum;
		int dir;
		boolean isAlive;

		public Microbe(int x, int y, int sum, int dir, boolean isAlive) {
			this.x = x;
			this.y = y;
			this.sum = sum;
			this.dir = dir;
			this.isAlive = isAlive;
		}

		@Override
		public int compareTo(Microbe o) {
			return -(this.sum - o.sum);
		}

	}
}
