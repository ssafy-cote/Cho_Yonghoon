package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P_조이스틱_0201 {
	// A -> 65
	static int[] map; // 버튼을 눌러야하는 회수를 담은 배열
	static int[] check; // 바꿔줬는지 체크하는 배열
	static int ans;	// 정답

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String name = bf.readLine();

		System.out.println(make(name));
	}

	static int make(String name) {
		// 정답 초기화
		ans = Integer.MAX_VALUE;
		String[] str = name.split("");
		map = new int[name.length()];
		for (int i = 0; i < str.length; i++) {
			int temp = str[i].charAt(0);
			if (temp > 78) {
				temp -= 91;
			} else {
				temp -= 65;
			}
			// 아스키코드 중앙 값을 적절히 계산해서 빼주고 절대 값으로 저장
			// 그러면 몇번 움직여야 원하는 문자가 나오는지 양수로 나옴
			map[i] = Math.abs(temp);
		}
		// 체크는 문자를 바꿨는지 확인하는 배열
		check = new int[map.length];
		// 현재 위치
		int cur = 0;
		// 양 옆 확인하는거 지금으로부터 떨어진 거리라고 생각
		// ex) index = n -> 현재 위치로부터 n 칸 만큼 떨어져 있는 부분 탐색
		int index = 1;
		// 임시 결과
		int sum = 0;
		// 첫번째 칸은 더하고 시작
		sum += map[0];
		// 방문 표시
		check[0] = 1;
		// A 자리는 안바꿀거니까 바로 방문 표시
		for (int i = 0; i < map.length; i++) {
			if (map[i] == 0) {
				check[i] = 1;
			}
		}

		find(cur, index, sum);

		return ans;
	}

	static void find(int cur, int index, int sum) {
		if(ans < sum) {
			return;
		}
		int c = 0;
		for (int i = 0; i < check.length; i++) {
			c += check[i];
		}
		if (c == check.length) {
			ans = Math.min(ans, sum);
			return;
		}
		// 오른쪽 탐색
		int go = cur + index;
		// 왼쪽 탐색
		int back = cur - index;
		// 배열을 넘어갈 경우 배열 반대편으로 계산
		if (back < 0) {
			back = map.length - index + cur;
		}
		// 배열을 넘어갈 경우 배열 반대편으로 계산
		if (go >= map.length) {
			go = cur + index - map.length;
		}
		
		// 계산 이상해질까봐 index 담아놓기
		int tempIndex = index;
		
		// 방문표시 없다면 그 자리로 이동
		if (check[go] == 0) {
			int tempSum = sum;
			tempSum += map[go];
			tempSum += tempIndex;
			check[go] = 1;
			int ncur = go;
			index = 1;
			find(ncur, index, tempSum);
			// dfs 라서 나오면 다시 방문표시 풀어줘야함
			check[go] = 0;
		}
		// 여기도 이동 완탐해야해서 else 사용X
		if (check[back] == 0) {
			int tempSum = sum;
			tempSum += map[back];
			tempSum += tempIndex;
			check[back] = 1;
			int ncur = back;
			index = 1;
			find(ncur, index, tempSum);
			// dfs 라서 나오면 다시 방문표시 풀어줘야함
			check[back] = 0;
		}
		
		// 한칸씩 더 먼곳 체크하기 위해
		tempIndex++;
		if (tempIndex > map.length) {
			return;
		}else {
			find(cur, tempIndex, sum);
		}
		
	}
}
