package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_02_Debug {

	static int ans;
	static int[][] map;
	static boolean[][] visit;
	static int N;
	// 상 우 좌 하
	static int[] dx = { -1, 0, 0, 1 };
	static int[] dy = { 0, 1, -1, 0 };
	static int max;

	static ArrayList<Pos> homeList;
	static ArrayList<Pos> elecList;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
		StringBuilder builder = new StringBuilder();
		int t = Integer.parseInt(stringTokenizer.nextToken());

		for (int T = 1; T <= t; T++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
			N = Integer.parseInt(stringTokenizer.nextToken());
			map = new int[31][31];
			homeList = new ArrayList<>();
			elecList = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
				int x = Integer.parseInt(stringTokenizer.nextToken());
				int y = Integer.parseInt(stringTokenizer.nextToken());
				int len = Integer.parseInt(stringTokenizer.nextToken());
				homeList.add(new Pos(15 - y, 15 + x, len));
			}

			// 겹치는 부분 그리기
			max = 0;
			ans = Integer.MAX_VALUE;
			for (Pos pos : homeList) {
				bfs(pos.x, pos.y, pos.len);
			}

			// 집 표시
			for (Pos pos : homeList) {
				map[pos.x][pos.y] = -1;
			}
//			printMap();
//			System.out.println(max);
			// 한 곳 설치 가능
			if (max == N) {
				for (Pos elecPos : elecList) {
					int sum = 0;
					for (Pos homePos : homeList) {
						sum += cal(homePos, elecPos);
					}
					ans = Math.min(sum, ans);
				}
			} else {
				// 집 리스트 만들기
				for (int i = 0; i < 961; i++) {
					int x = i / 31;
					int y = i % 31;
					if (map[x][y] == 0)
						continue;
					makeList(i);
				}
				
//				for(Pos pos : elecList) {
//					System.out.println(pos);
//				}
				
				// 거리 계산
				for (Pos elecPos : elecList) {
					int sum = 0;
					for (Pos homePos : homeList) {
						sum += cal2(homePos, elecPos);
					}
					ans = Math.min(sum, ans);
				}
				
			}
			if (ans == Integer.MAX_VALUE) {
				ans = -1;
			}

			builder.append("#").append(T).append(" ").append(ans).append("\n");
		}
		System.out.println(builder);
	}

	static int cal2(Pos home, Pos elec) {
		int result = 0;
		// 두개의 집 중 더 가까운 곳을 골라야함
		int r1 = Math.abs(home.x - elec.x) + Math.abs(home.y - elec.y);
		int r2 = Math.abs(home.x - elec.x2) + Math.abs(home.y - elec.y2);
		result = Math.min(r1, r2);
		return result;
	}

	static int cal(Pos home, Pos elec) {
		return Math.abs(home.x - elec.x) + Math.abs(home.y - elec.y);
	}

	static void makeList(int start) {

		for (int i = start + 1; i < 961; i++) {
			int x = i / 31;
			int y = i % 31;
			int x2 = start / 31;
			int y2 = start % 31;
//			System.out.print("x: " + x + " y: " + y + " 값: " + map[x][y] + " ");
//			System.out.println("x2: " + x2 + " y2: " + y2 + " 값: " + map[x2][y2]);
			
			if (map[x][y] + map[x2][y2] >= N) {
				elecList.add(new Pos(x, y, x2, y2));
			}
		}
	}

	static void bfs(int x, int y, int len) {
		Queue<Pos> queue = new ArrayDeque<>();
		queue.add(new Pos(x, y, 1));
		visit = new boolean[31][31];
		visit[x][y] = true;
		int cnt = 0;
		while (cnt++ != len) {

			int size = queue.size();
			while (size-- > 0) {
				Pos pos = queue.poll();

				for (int i = 0; i < 4; i++) {
					int nx = pos.x + dx[i];
					int ny = pos.y + dy[i];
					if (check(nx, ny) && !visit[nx][ny]) {
						if (map[nx][ny] != 0) {
							map[nx][ny] += 1;
							max = Math.max(max, map[nx][ny]);
							if (map[nx][ny] == N) {
								elecList.add(new Pos(nx, ny, -1));
							}
						} else {
							map[nx][ny] = 1;
						}
						visit[nx][ny] = true;
						queue.add(new Pos(nx, ny, len));
					}
				}
			}
		}
	}

	static void printMap() {
		for (int i = 0; i < 31; i++) {
			for (int j = 0; j < 31; j++) {
				System.out.print(map[i][j] + "\t");
			}
			System.out.println();
		}
		System.out.println("===============");
	}

	static class Pos {
		int x;
		int y;
		int len;
		int x2;
		int y2;

		public Pos(int x, int y, int x2, int y2) {
			this.x = x;
			this.y = y;
			this.x2 = x2;
			this.y2 = y2;
		}

		public Pos(int x, int y, int len) {
			this.x = x;
			this.y = y;
			this.len = len;
		}

		@Override
		public String toString() {
			return "Pos [x=" + x + ", y=" + y + ", len=" + len + ", x2=" + x2 + ", y2=" + y2 + "]";
		}

	}

	static boolean check(int x, int y) {
		return (-1 < x && x < 31 && -1 < y && y < 31);
	}
}
