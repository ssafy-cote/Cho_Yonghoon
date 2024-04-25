package algo0401;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_B_17472_다리만들기2_조용훈 { // 11784kb 메모리, 84ms 시간

	static int n, m, islandCnt;

	static int[][] map;
	static boolean[][] visit;

	static int[] dx = { 0, 0, 1, -1 };
	static int[] dy = { 1, -1, 0, 0 };

	static ArrayList<Bridge> bridges;

	static ArrayList<ArrayList<Island>> list;

	static int[] parent;

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
					A: for (Island b : list.get(j)) {
						if (a.x == b.x && Math.abs(a.y - b.y) > 2) {
							if (a.y < b.y) {
								for (int p = a.y + 1; p < b.y; p++) {
									if (map[a.x][p] != 0) {
										continue A;
									}
								}
							} else {
								for (int p = b.y + 1; p < a.y; p++) {
									if (map[a.x][p] != 0) {
										continue A;
									}
								}
							}
							dis = (dis > Math.abs(a.y - b.y) - 1) ? Math.abs(a.y - b.y) - 1 : dis;
						} else if (a.y == b.y && Math.abs(a.x - b.x) > 2) {
							if (a.x < b.x) {
								for (int p = a.x + 1; p < b.x; p++) {
									if (map[p][a.y] != 0) {
										continue A;
									}
								}
							} else {
								for (int p = b.x + 1; p < a.x; p++) {
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
		Collections.sort(bridges);
		parent = new int[islandCnt + 1];
		for (int i = 1; i < islandCnt + 1; i++) {
			parent[i] = i;
		}
		int ans = 0;
		int ccc = 0;
		if (bridges.size() > 0) {
			for (int i = 0; i < bridges.size(); i++) {
				Bridge temp = bridges.get(i);
				if (union(temp.to, temp.from)) {
					ans += temp.dis;
					++ccc;
				}
			}
			for (int i = 2; i < islandCnt + 1; i++) {
				if (find(i - 1) != find(i)) {
					ans = -1;
					break;
				}
			}
		} else {
			ans = -1;
		}
		if (ccc != islandCnt - 1) {
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

	static int find(int a) {
		if (parent[a] == a) {
			return a;
		}
		return parent[a] = find(parent[a]);
	}

	static boolean union(int a, int b) {
		if (find(a) == find(b)) {
			return false;
		}

		if (find(a) < find(b)) {
			parent[find(b)] = find(parent[a]);
		} else {
			parent[find(a)] = find(parent[b]);
		}
		return true;
	}

}
