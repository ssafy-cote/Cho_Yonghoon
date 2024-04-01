package algo0401;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_B_17472_다리만들기2_조용훈 {

	static int n, m, islandCnt;

	static int[][] map;
	static boolean[][] visit;

	static int[] dx = { 0, 0, 1, -1 };
	static int[] dy = { 1, -1, 0, 0 };

	static ArrayList<Bridge> bridges;

	static ArrayList<ArrayList<Island>> list;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

		n = Integer.parseInt(stringTokenizer.nextToken());
		m = Integer.parseInt(stringTokenizer.nextToken());

		map = new int[n][m];

		for (int i = 0; i < n; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
			}
		}
		islandCnt = 0;
		visit = new boolean[n][m];

		list = new ArrayList<>();
		list.add(new ArrayList<>());
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (!visit[i][j] && map[i][j] == 1) {
					++islandCnt;
					bfs(i, j);
				}
			}
		}

		bridges = new ArrayList<>();

		// 다리 간선 모두 구하기
		for (int i = 1; i <= islandCnt; i++) {
			for (int j = i + 1; j <= islandCnt; j++) {
				int dis = 101;
				for (Island a : list.get(i)) {
					A:for (Island b : list.get(j)) {
						if (a.x == b.x && Math.abs(a.y - b.y) > 2) {
							if (a.y < b.y) {
								for(int p = a.y + 1; p < b.y; p++) {
//									System.out.println(map[a.x][p] +":"+ a.x + " " + p);
									if (map[a.x][p] != 0) {
										continue A;
									}
								}
							}else {
								for(int p = b.y + 1; p < a.y; p++) {
//									System.out.println(map[a.x][p] +":"+ a.x + " " + p);
									if (map[a.x][p] != 0) {
										continue A;
									}
								}
							}
							dis = (dis > Math.abs(a.y - b.y) - 1) ? Math.abs(a.y - b.y) - 1 : dis;
						} else if (a.y == b.y && Math.abs(a.x - b.x) > 2) {
							if (a.x < b.x) {
								for(int p = a.x+1; p < b.x; p++) {
//									System.out.println(map[p][a.y] +":"+ p + " " + a.y);
									if (map[p][a.y] != 0) {
										continue A;
									}
								}
							}else {
								for(int p = b.x+1; p < a.x; p++) {
//									System.out.println(map[p][a.y] +":"+ p + " " + a.y);
									if (map[p][a.y] != 0) {
										continue A;
									}
								}
							}
							dis = (dis > Math.abs(a.x - b.x) - 1) ? Math.abs(a.x - b.x) - 1 : dis;
						}
					}
				}
				if (dis != 101 && dis > 1) {
					bridges.add(new Bridge(i, j, dis));
				}
			}
		}

//		for (Island a : list.get) {
//			for (Island b : list) {
//				int dis = 0;
//				if (a == b) {
//					continue;
//				}
//
//				// 세로 다리 놓는 경우
//				for (int i = a.left; i <= a.right; i++) {
//					if (b.left == i || b.right == i) {
//						// 누가 위쪽인지
//						if (a.up > b.up) {
//							if (Math.abs(a.down - b.up) > 2) {
//								bridges.add(new Bridge(a.index, b.index, Math.abs(a.down - b.up) - 1));
//							}
//						} else {
//							if (Math.abs(b.down - a.up) > 2) {
//								bridges.add(new Bridge(a.index, b.index, Math.abs(b.down - a.up) - 1));
//							}
//						}
//
//					}
//				}
//
//				// 가로 다리 놓는 경우
//				for (int i = a.up; i <= a.down; i++) {
//					if (b.up == i || b.down == i) {
//						// 누가 더 왼쪽인지
//						if (a.left > b.left) {
//							if (Math.abs(a.left - b.right) > 2) {
//								bridges.add(new Bridge(a.index, b.index, Math.abs(a.left - b.right) - 1));
//							}
//						} else {
//							if (Math.abs(a.right - b.left) > 2) {
//								bridges.add(new Bridge(a.index, b.index, Math.abs(a.right - b.left) - 1));
//							}
//						}
//					}
//				}
//			}
//		}

		Collections.sort(bridges);
//		System.out.println(bridges);

//		for (int i = 0; i < n; i++) {
//			for (int j = 0; j < m; j++) {
//				System.out.print(map[i][j] + " ");
//			}
//			System.out.println();
//		}
//
//		System.out.println(list);

		int ans = 0;
		boolean[] checkIsland = new boolean[islandCnt+1];
		checkIsland[0] = true;
		if (bridges.size() > 0) {
			for (int i = 0; i < islandCnt - 1; i++) {
				Bridge temp = bridges.get(i);
				ans += temp.dis;
				checkIsland[temp.from] = true;
				checkIsland[temp.to] = true;
			}
			
			for(boolean flag : checkIsland) {
				if (!flag) {
					ans = -1;
				}
			}
		}else {
			ans = -1;
		}
		System.out.println(ans);
		
	}

	static void bfs(int x, int y) {
		Queue<Pos> queue = new ArrayDeque<>();
		visit[x][y] = true;
		map[x][y] = islandCnt;
		list.add(new ArrayList<>());
		list.get(islandCnt).add(new Island(x, y, islandCnt));
		queue.add(new Pos(x, y));
		while (!queue.isEmpty()) {

			Pos pos = queue.poll();

			for (int i = 0; i < 4; i++) {
				int nx = pos.x + dx[i];
				int ny = pos.y + dy[i];
				if (check(nx, ny) && !visit[nx][ny] && map[nx][ny] != 0) {
					map[nx][ny] = islandCnt;
					visit[nx][ny] = true;
					list.get(islandCnt).add(new Island(nx, ny, islandCnt));
					queue.add(new Pos(nx, ny));
				}
			}
		}
	}

	static boolean check(int x, int y) {
		return (-1 < x && x < n && -1 < y && y < m);
	}

	static class Pos {
		int x, y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static class Island {
		int x, y;
		int index;

		public Island(int x, int y, int index) {
			this.x = x;
			this.y = y;
			this.index = index;
		}

		@Override
		public String toString() {
			return "Island [x=" + x + ", y=" + y + ", index=" + index + "]";
		}
	}

	static class Bridge implements Comparable<Bridge> {
		int from, to, dis;

		public Bridge(int from, int to, int dis) {
			this.from = from;
			this.to = to;
			this.dis = dis;
		}

		@Override
		public String toString() {
			return "Bridge [from=" + from + ", to=" + to + ", dis=" + dis + "]";
		}

		@Override
		public int compareTo(Bridge o) {
			return this.dis - o.dis;
		}
	}
}
