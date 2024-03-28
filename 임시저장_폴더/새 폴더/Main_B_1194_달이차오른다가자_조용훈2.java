package algo0328;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_B_1194_달이차오른다가자_조용훈2 {
	static int n, m;
	static String[][] map;
	static boolean[][][] visit;
	static int[] dx = { 0, 0, 1, -1 };
	static int[] dy = { 1, -1, 0, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

		n = Integer.parseInt(stringTokenizer.nextToken());
		m = Integer.parseInt(stringTokenizer.nextToken());

		map = new String[n][m];
		Pos pos = null;
		for (int i = 0; i < n; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
			map[i] = stringTokenizer.nextToken().split("");
			for (int j = 0; j < m; j++) {
				if (map[i][j].equals("0")) {
					pos = new Pos(i, j);
				}
			}
		}
		
		// 0 키 없음 1부터 a키
		visit = new boolean[n][m][7];
		int ans = bfs(pos);

		System.out.println(ans);
	}

	static int bfs(Pos pos) {
		Queue<Pos> queue = new ArrayDeque<>();
		queue.add(pos);
		visit[pos.x][pos.y][0] = true;
		int cnt = -1;
		while (!queue.isEmpty()) {
			++cnt;
			int size = queue.size();
			System.out.println(size);
			while (size-- > 0) {
				Pos tempPos = queue.poll();
				System.out.println(tempPos);
				if (map[tempPos.x][tempPos.y].equals("1")) {
					return cnt;
				}
				
				for(int i = 0; i < 4; i++) {
					int nx = tempPos.x + dx[i];
					int ny = tempPos.y + dy[i];
					
					if (check(nx, ny) && !map[nx][ny].equals("#") && !visit[nx][ny][tempPos.cnt]) {
						if(!doorCheck(nx, ny)) {
							if (mapKeyCheck(nx, ny)) {
								if(keyCheck(tempPos, map[nx][ny])) {
									// 키를 이미 들고 있음 그럼 그냥 똑같이 진행
									visit[nx][ny][tempPos.cnt] = true;
									boolean[] temp = new boolean[tempPos.key.length];
									for(int z = 0; z < tempPos.key.length; z++) {
										temp[z] = tempPos.key[z];
									}
									queue.add(new Pos(nx, ny, temp, tempPos.cnt));
								}else {
									// 키가 없었으면 
									//visit[nx][ny][tempPos.cnt+1] = true;
									boolean[] temp = new boolean[tempPos.key.length];
									for(int z = 0; z < tempPos.key.length; z++) {
										temp[z] = tempPos.key[z];
									}
									Pos p = new Pos(nx, ny, temp, tempPos.cnt+1);
									int keynum = getKeyNumber(map[nx][ny]);
									p.key[keynum] = true;
									queue.add(p);
								}
							}else {
								visit[nx][ny][tempPos.cnt] = true;
								boolean[] temp = new boolean[tempPos.key.length];
								for(int z = 0; z < tempPos.key.length; z++) {
									temp[z] = tempPos.key[z];
								}
								queue.add(new Pos(nx, ny, temp, tempPos.cnt));
							}
						}else {
							// 문일경우
							System.out.println(map[nx][ny] + " 문");
							System.out.println(Arrays.toString(tempPos.key));
							int needKeyNum = getNeedKeyNum(map[nx][ny]);
							if (tempPos.key[needKeyNum]) {
								visit[nx][ny][tempPos.cnt] = true;
								boolean[] temp = new boolean[tempPos.key.length];
								for(int z = 0; z < tempPos.key.length; z++) {
									temp[z] = tempPos.key[z];
								}
								System.out.println("문들어감");
								queue.add(new Pos(nx, ny, temp, tempPos.cnt));
							}
						}
					}
				}
			}
		}
		return -1;
	}
	
	static int getNeedKeyNum(String door) {
		if (door.equals("A")) {
			return 1;
		}else if(door.equals("B")) {
			return 2;
		}else if(door.equals("C")) {
			return 3;
		}else if(door.equals("D")) {
			return 4;
		}else if(door.equals("E")) {
			return 5;
		}else if(door.equals("F")) {
			return 6;
		}
		return 0;
	}
	
	static boolean mapKeyCheck(int x, int y) {
		return (map[x][y].equals("a") || map[x][y].equals("b") ||map[x][y].equals("c") ||map[x][y].equals("d") ||map[x][y].equals("e") ||map[x][y].equals("f"));
	}
	
	static boolean doorCheck(int x, int y) {
		return (map[x][y].equals("A") || map[x][y].equals("B") ||map[x][y].equals("C") ||map[x][y].equals("D") ||map[x][y].equals("E") ||map[x][y].equals("F"));
	}

	static boolean keyCheck(Pos pos, String newKey) {
		int num = getKeyNumber(newKey);
		return pos.key[num];
	}

	static int getKeyNumber(String key) {
		if (key.equals("a")) {
			return 1;
		} else if (key.equals("b")) {
			return 2;
		} else if (key.equals("c")) {
			return 3;
		} else if (key.equals("d")) {
			return 4;
		} else if (key.equals("e")) {
			return 5;
		} else if (key.equals("f")) {
			return 6;
		}
		return 0;
	}

	static boolean check(int x, int y) {
		return (-1 < x && x < n && -1 < y && y < m);
	}

	static class Pos {
		int x;
		int y;

		public Pos(int x, int y, boolean[] key, int cnt) {
			this.x = x;
			this.y = y;
			this.key = key;
			this.cnt = cnt;
		}

		// 0번 사용 x
		boolean[] key = new boolean[7];
		
		int cnt = 0;

		public Pos(int x, int y, int cnt) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
		
		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "Pos [x=" + x + ", y=" + y + ", key=" + Arrays.toString(key) + ", cnt=" + cnt + "]";
		}
		
	}
}
