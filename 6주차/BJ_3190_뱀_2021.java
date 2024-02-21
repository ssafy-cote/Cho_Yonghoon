package algo2020;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ_3190_뱀_2021 {
	static int n, K;
	static int L;
	static int[][] map;
	static int Dir; // 0: 오른쪽, 1: 아래, 2: 왼쪽 , 3: 위
	static int time;
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };
	static ArrayList<DirTimeInfo> timeStamp;
	static Snake snake;
	static ArrayList<Snake> snakePos;
	static int flag = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

		timeStamp = new ArrayList<>();

		n = Integer.parseInt(stringTokenizer.nextToken());
		map = new int[n + 2][n + 2];
		stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
		K = Integer.parseInt(stringTokenizer.nextToken());
		for (int i = 0; i < K; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
			int x = Integer.parseInt(stringTokenizer.nextToken());
			int y = Integer.parseInt(stringTokenizer.nextToken());
			map[x][y] = 1;
		}
		stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
		K = Integer.parseInt(stringTokenizer.nextToken());
		timeStamp = new ArrayList<>();
		for (int i = 0; i < K; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
			int time = Integer.parseInt(stringTokenizer.nextToken());
			String dir = stringTokenizer.nextToken();
			timeStamp.add(new DirTimeInfo(time, dir));
		}
		Dir = 0;
		time = 1;
		snakePos = new ArrayList<>();
		snakePos.add(new Snake(1, 1));
		// 사과: 1
		// 뱀: 2
		map[1][1] = 2;
		while (snakePos.size() > 0) {
			go();
			timeCheck();
			++time;
		}
		System.out.println(time);
	}

	static void go() {
		int nx = snakePos.get(snakePos.size() - 1).x + dx[Dir];
		int ny = snakePos.get(snakePos.size() - 1).y + dy[Dir];

		if (map[nx][ny] == 1) {
			map[nx][ny] = 2;
			snakePos.add(new Snake(nx, ny));
			return;
		}
		if (!gameOverCheck(nx, ny)) {
			System.out.println(time);
			System.exit(0);
		}
		map[nx][ny] = 2;
		snakePos.add(new Snake(nx, ny));
		map[snakePos.get(0).x][snakePos.get(0).y] = 0;
		snakePos.remove(0);
	}

	// false -> gameOver
	static boolean gameOverCheck(int x, int y) {
		// 범위 밖
		if (x < 1 || y < 1 || x > n || y > n) {
			return false;
		}
		// 꼬리 충돌
		if (map[x][y] == 2) {
			return false;
		}
		return true;
	}

	static void timeCheck() {
		if (timeStamp.size() == 0) {
			return;
		}
		if (time == timeStamp.get(0).time) {
			changeDir(timeStamp.get(0).dir);
			timeStamp.remove(0);
		}
	}

	static void changeDir(String dir) {
		if (dir.equals("D")) {
			Dir = (Dir + 1) % 4;
		} else {
			Dir -= 1;
			if (Dir < 0) {
				Dir = 3;
			}
		}
	}

	static class DirTimeInfo {
		int time;
		String dir;

		public DirTimeInfo(int time, String dir) {
			this.time = time;
			this.dir = dir;
		}
	}

	static class Snake {
		int x;
		int y;

		private Snake(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
