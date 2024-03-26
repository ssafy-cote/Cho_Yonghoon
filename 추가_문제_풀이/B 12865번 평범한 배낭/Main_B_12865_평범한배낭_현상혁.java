import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 12,668kb / 136ms

public class Main {
	static class Stuff {
		int weight, value;

		public Stuff(int weight, int value) {
			this.weight = weight;
			this.value = value;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); // 물건 개수
		int K = Integer.parseInt(st.nextToken()); // 가방 무게

		Stuff[] stuffs = new Stuff[N + 1];
		for (int i = 1; i < N + 1; i++) {
			st = new StringTokenizer(br.readLine());
			stuffs[i] = new Stuff(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		br.close();

		int[] prev = new int[K + 1];
		int[] current = new int[K + 1];
		for (int i = 1; i < N + 1; i++) { // 물건 index
			for (int j = 1; j < K + 1; j++) { // 무게 index
				if (stuffs[i].weight > j) {
					// 가방에 물건을 넣을 수 없음
					current[j] = prev[j];
					continue;
				}

				// 가방에 물건을 넣을 수 있음
				current[j] = Math.max(prev[j - stuffs[i].weight] + stuffs[i].value, prev[j]);
			}
			// 배열 교체
			System.arraycopy(current, 0, prev, 0, K + 1);
		}

		System.out.println(current[K]);
	}
}
