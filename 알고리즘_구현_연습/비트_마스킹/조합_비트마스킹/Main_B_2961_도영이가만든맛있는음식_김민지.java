package algo0201;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// 메모리 14204 KB
// 시간 124 ms
public class Main_B_2961_도영이가만든맛있는음식_김민지 {
	static int n, sour=1, bit; // n과 신맛, 쓴맛
	static int[] sours, bits; // 신맛, 쓴맛 배열 생성
	static int result = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		sours = new int[n];
		bits = new int[n];
		for(int i=0; i<n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			sours[i] = Integer.parseInt(st.nextToken());
			bits[i] = Integer.parseInt(st.nextToken());
		}

		if (n == 1) {
			result = Math.abs(sours[0] - bits[0]);
		} else {
			for (int i = 1; i < (1<<n); i++) { // 한번도 안쓰이는 경우는 없으니까 0 제외(비교하는 flag)
				for (int j = 0; j < n; j++) { // 사용하는 자리 
					if ((i & (1 << j)) != 0) {
						sour *= sours[j];
						bit += bits[j];
					}
				}
				result = Math.min(result, Math.abs(sour - bit));
				sour = 1;
				bit = 0;
			}
		}
		System.out.println(result);
	}
}