package algo0220;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ_3190_¹ì_2021 {
	static int n;
	static int[][] map;
	static int L;
	static int Dir; // 0: ¿À¸¥ÂÊ, 1: ¾Æ·¡, 2: ¿ÞÂÊ , 3: À§
	static int time;
	static int[] dx = {0, -1};
	static int[] dy = {1, };
	static ArrayList<DirTimeInfo> timeStamp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
		
		timeStamp = new ArrayList<>();
		
		n = Integer.parseInt(stringTokenizer.nextToken());
		
		
		
	}
	
	static void timeCheck() {
		if(time == timeStamp.get(0).time) {
			changeDir(timeStamp.get(0).dir);
			timeStamp.remove(0);
		}
	}

	static void changeDir(String dir) {
		if (dir.equals("D")) {
			Dir = (Dir + 1) % 4;
		} else {
			Dir = (Dir - 1) % 4;
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
}
