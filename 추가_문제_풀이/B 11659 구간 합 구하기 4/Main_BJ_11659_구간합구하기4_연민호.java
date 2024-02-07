package algo230131;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_11659_구간합구하기4_연민호 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 학생 수
		int M = Integer.parseInt(st.nextToken());// 합을 구해야 하는 횟수

		// 숫자 입력받기
		int[] arr = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		arr[1] = Integer.parseInt(st.nextToken());
		// 누적합 받아놓기
		for (int i = 2; i <= N; i++) {
			arr[i] = arr[i - 1] + Integer.parseInt(st.nextToken());
		}

		// M번의 누적합 구하기
		for (int num = 0; num < M; num++) {
			st = new StringTokenizer(br.readLine());
			int i = Integer.parseInt(st.nextToken()); // 누적합 시작 인덱스
			int j = Integer.parseInt(st.nextToken()); // 누적합 끝 인덱스

			sb.append(arr[j] - arr[i - 1]).append('\n');
		}

		System.out.println(sb);
	}
}