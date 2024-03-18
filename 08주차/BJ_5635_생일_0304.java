package algo0304;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_5635_생일_0304 {
	static Info minInfo;
	static Info maxInfo;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

		int n = Integer.parseInt(stringTokenizer.nextToken());
		stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
		String name = stringTokenizer.nextToken();
		int dd = Integer.parseInt(stringTokenizer.nextToken());
		int mm = Integer.parseInt(stringTokenizer.nextToken());
		int yyyy = Integer.parseInt(stringTokenizer.nextToken());
		minInfo = new Info(name, dd, mm, yyyy);
		maxInfo = new Info(name, dd, mm, yyyy);

		for (int i = 0; i < n - 1; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
			name = stringTokenizer.nextToken();
			dd = Integer.parseInt(stringTokenizer.nextToken());
			mm = Integer.parseInt(stringTokenizer.nextToken());
			yyyy = Integer.parseInt(stringTokenizer.nextToken());
			maxCompare(new Info(name, dd, mm, yyyy));
			minCompare(new Info(name, dd, mm, yyyy));
		}

		System.out.println(minInfo);
		System.out.println(maxInfo);
	}

	static void minCompare(Info info) {
		if (minInfo.yyyy < info.yyyy) {
			minInfo.name = info.name;
			minInfo.yyyy = info.yyyy;
			minInfo.mm = info.mm;
			minInfo.dd = info.dd;
		} else if (minInfo.yyyy == info.yyyy) {
			if (minInfo.mm < info.mm) {
				minInfo.name = info.name;
				minInfo.yyyy = info.yyyy;
				minInfo.mm = info.mm;
				minInfo.dd = info.dd;
			} else if (minInfo.mm == info.mm) {
				if (minInfo.dd < info.dd) {
					minInfo.name = info.name;
					minInfo.yyyy = info.yyyy;
					minInfo.mm = info.mm;
					minInfo.dd = info.dd;
				}
			}
		}
	}

	static void maxCompare(Info info) {
		if (maxInfo.yyyy > info.yyyy) {
			maxInfo.name = info.name;
			maxInfo.yyyy = info.yyyy;
			maxInfo.mm = info.mm;
			maxInfo.dd = info.dd;
		} else if (maxInfo.yyyy == info.yyyy) {
			if (maxInfo.mm > info.mm) {
				maxInfo.name = info.name;
				maxInfo.yyyy = info.yyyy;
				maxInfo.mm = info.mm;
				maxInfo.dd = info.dd;
			} else if (maxInfo.mm == info.mm) {
				if (maxInfo.dd > info.dd) {
					maxInfo.name = info.name;
					maxInfo.yyyy = info.yyyy;
					maxInfo.mm = info.mm;
					maxInfo.dd = info.dd;
				}
			}
		}
	}

	static class Info {
		String name;
		int mm;
		int dd;
		int yyyy;

		@Override
		public String toString() {
			return name;
		}

		public Info(String name, int dd, int mm, int yyyy) {
			this.name = name;
			this.dd = dd;
			this.mm = mm;
			this.yyyy = yyyy;
		}
	}
}
