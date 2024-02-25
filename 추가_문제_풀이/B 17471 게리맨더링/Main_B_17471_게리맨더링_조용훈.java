package algo0225;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_B_17471_게리맨더링_조용훈 { // 14312kb 메모리, 112ms 시간
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
		subset(1, 0);
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
			visit = new boolean[N + 1];
			visit[listA.get(0)] = true;
			makeTeam(listA.get(0), listA);
			if (!isTeam(listA)) {
				return;
			}
			visit = new boolean[N + 1];
			visit[listB.get(0)] = true;
			makeTeam(listB.get(0), listB);
			if (!isTeam(listB)) {
				return;
			}

			int aP = 0;
			int bP = 0;

			for (int n : listA) {
				aP += people[n];
			}

			for (int n : listB) {
				bP += people[n];
			}

			int temp = Math.abs(aP - bP);
			ans = (ans > temp) ? temp : ans;
			return;
		}
		checked[index] = true;
		subset(index + 1, trueCnt + 1);
		checked[index] = false;
		subset(index + 1, trueCnt);
	}

	// 처음 시작 부분 부터 다른 연결된 노드를 이어주는 함수
	static void makeTeam(int n, ArrayList<Integer> list) {
		for (int i : list) {
			if (map[n][i] && !visit[i]) {
				visit[i] = true;
				makeTeam(i, list);
			}
		}
	}

	// 순열의 노드들이 모두 방문 체크가 되었는지 확인하는 함수
	static boolean isTeam(ArrayList<Integer> list) {
		for (int n : list) {
			if (visit[n])
				continue;
			return false;
		}
		return true;
	}
}
