package algo0311;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BJ_6198_옥상정원꾸미기_0311 {
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

		int n = Integer.parseInt(stringTokenizer.nextToken());

		Deque<Building> deque = new ArrayDeque<>();

		long ans = 0;
		for (int i = 0; i < n; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
			int heigth = Integer.parseInt(stringTokenizer.nextToken());
			if (deque.isEmpty()) {
				deque.add(new Building(i, heigth));
			} else {

				while (!deque.isEmpty() && heigth >= deque.peekLast().heigth) {
					Building building = deque.pollLast();
					ans += i - (building.index + 1);
				}
				deque.add(new Building(i, heigth));
			}
		}
		while (!deque.isEmpty() && 1000000001 >= deque.peekLast().heigth) {
			Building building = deque.pollLast();
			ans += n - (building.index + 1);
		}
		System.out.println(ans);
	}

	static class Building {
		int index;
		int heigth;

		public Building(int index, int heigth) {
			this.index = index;
			this.heigth = heigth;
		}
	}
}
