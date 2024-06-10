package algo0610;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_11000_강의실배정_0610 {

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

		int n = Integer.parseInt(stringTokenizer.nextToken());
		cla[] list = new cla[n];
		for (int i = 0; i < n; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
			int s = Integer.parseInt(stringTokenizer.nextToken());
			int f = Integer.parseInt(stringTokenizer.nextToken());
			list[i] = new cla(s, f, f - s);
		}

		Arrays.sort(list);

		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for (cla c : list) {

			if (pq.size() == 0) {
				pq.add(c.f);
				continue;
			}

			if (pq.peek() <= c.s) {
				pq.poll();
			}
			pq.add(c.f);
		}
		System.out.println(pq.size());
	}

	static class cla implements Comparable<cla> {
		int s;
		int f;
		int h;

		public cla(int s, int f, int h) {
			this.s = s;
			this.f = f;
			this.h = h;
		}

		@Override
		public String toString() {
			return "[s: " + s + " f: " + f + " h: " + h + "]";
		}

		@Override
		public int compareTo(cla o) {
			if (o.s == this.s) {
				return this.h - o.h;
			}
			return this.s - o.s;
		}

	}
}
