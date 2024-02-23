package algo0223;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_B_17471_게리맨더링_조용훈 {
	static int N;
	static boolean[] checked;
	static int[] people;
	static boolean[][] map;
	static ArrayList<Integer> listA;
	static ArrayList<Integer> listB;
	static int ans;
	static boolean[] visit;
	static int cnt;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

		N = Integer.parseInt(stringTokenizer.nextToken());

		checked = new boolean[N + 1];
		people = new int[N + 1];
		map = new boolean[N + 1][N + 1];
		stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
		for (int i = 1; i <= N; i++) {
			people[i] = Integer.parseInt(stringTokenizer.nextToken());
		}

		for (int i = 1; i <= N; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
			int n = Integer.parseInt(stringTokenizer.nextToken());
			for (int j = 0; j < n; j++) {
				int a = Integer.parseInt(stringTokenizer.nextToken());
				map[i][a] = true;
				map[a][i] = true;
			}
		}
		ans = Integer.MAX_VALUE;
		subset(2, 0);
		ans = (ans == Integer.MAX_VALUE) ? -1 : ans;
		System.out.println(ans);
	}

	static void subset(int index, int trueCnt) {

		if (index == N + 1) {
			if (trueCnt == 0 || trueCnt == N)
				return;
			listA = new ArrayList<>();
			listB = new ArrayList<>();
			for (int i = 1; i <= N; i++) {
				if (checked[i])
					listA.add(i);
				else {
					listB.add(i);
				}
			}

			int flagA = 0;
			int flagB = 0;

			int aP = 0;
			int bP = 0;

			for (int n : listA) {
				aP += people[n];
				visit = new boolean[N + 1];
				visit[n] = true;
				cnt = 0;
				if (isTeam(listA, n)) {
					flagA = 1;
					break;
				}
			}
			for (int n : listB) {
				bP += people[n];
				visit = new boolean[N + 1];
				visit[n] = true;
				cnt = 0;
				if (isTeam(listB, n)) {
					flagB = 1;
					break;
				}
			}

			if ((flagA + flagB) != 2) {
				return;
			}

			int temp = Math.abs(aP - bP);
			ans = (ans > temp) ? temp : ans;
			System.out.println("A: " + listA);
			System.out.println("B: " + listB);
			System.out.println(ans);
			return;
		}
		checked[index] = true;
		subset(index + 1, trueCnt + 1);
		checked[index] = false;
		subset(index + 1, trueCnt);
	}

	static boolean isTeam(ArrayList<Integer> list, int a) {
		if(list.size() == 1) return true;
		int c = 0;
		for(boolean v : visit) {
			if(v) {
				++c;
			}
		}
		if(c == list.size()) {
			return true;
		}

		for (int n : list) {
			if (map[a][n]) {
				if (!visit[n]) {
					visit[n] = true;
					if (isTeam(list, n)) {
						return true;
					}
					visit[n] = false;
				}
			}
		}
		return false;
	}
}
