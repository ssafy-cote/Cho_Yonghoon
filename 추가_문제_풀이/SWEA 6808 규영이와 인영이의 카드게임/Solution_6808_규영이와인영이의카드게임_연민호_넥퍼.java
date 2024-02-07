package algo240221;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/**
 * 메모리:24,880KB, 시간:818ms
 */
public class Solution_6808_규영이와인영이의카드게임_연민호_넥퍼 {
 
    static boolean[] check; //입력받을 때 인영이의 카드를 알아내기 위한 불린 배열
     
    static int[] roundGyu;  //라운드별 제시한 규영이의 카드
    static int[] roundIn; //라운드별 제시한 인영이의 카드
     
    static int win, lose; //규영이가 이긴횟수와 진횟수
     
    public static void main(String[] args) throws IOException {
        //System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;
         
        int T = Integer.parseInt(br.readLine());
         
        for(int tc=1; tc<=T; tc++) {
            check = new boolean[19];
             
            roundGyu = new int[9];
            roundIn = new int[9];
             
            win = 0;    //규영이가 이긴 횟수 초기화
            lose = 0;   //규영이가 진 횟수 초기화
             
            st = new StringTokenizer(br.readLine());
            for(int i=0;i<9;i++) {
                int num = Integer.parseInt(st.nextToken()); 
                roundGyu[i] = num;  //규영이의 카드 입력
                check[num] = true;  //규영이의 카드번호를 인덱스 삼아 해당 불린 배열을 true 
            }
             
            //규영이의 카드를 제외한 인영이의 카드를 roundIn배열에 입력받기
            int idx = 0;
            for(int i=1;i<=18;i++) {
                if(!check[i]) {
                    roundIn[idx++] = i;
                }
            }
            
            //인영이의 카드만을 가지고 넥퍼를 활용해 순열을 만들어나가며 문제 해결
            Arrays.sort(roundIn);   //인영이의 카드 정렬
            do {
                calcTotal();
            } while (np(roundIn));
             
            sb.append('#').append(tc).append(' ').append(win).append(' ').append(lose).append('\n');
        }
        System.out.println(sb);
    }
	/**
	 * 현재 순열에서 다음으로 큰 순열을 만들어 줌
	 * @param arr
	 * @return
	 */
	private static boolean np(int[] arr) {
		
		int N = arr.length;	//요소의 개수
		
		//1. 꼭대기 인덱스 찾기
		int i=N-1;	//꼭대기 인덱스 담을 변수
		while( i>0 && arr[i-1]>=arr[i] ) i--;
		
		//꼭대기 인덱스가 0이라면? arr[]의 숫자가 내림차순이므로 만들 수 있는 가장 큰 순열인 상태이므로 false 반환 후 종료
		if(i==0) return false;
		
		//2. (i-1)요소와 교환할 요소의 인덱스 찾기
		int j=N-1;
		while(arr[i-1] >= arr[j] ) j--;
		
		
		//3. (i-1) <=> j 요소 스왑
		swap(arr, i-1, j);
		
		
		//4. (i~ N-1) 요소들을 오름차순으로 정렬 ( 작은 수를 만들어내기 위함 )
		// i는 앞의 요소의 포인터, k는 뒤의 요소의 포인터
		int k= N-1;
		while(i<k) {
			swap(arr, i++, k--);
		}
		
		//큰 순열을 만들어내는데 성공했으므로 true 반환
		return true;
	}

 
    //규영이와 인영이의 총합 구하기
    private static void calcTotal() {
        int sumGyu=0;   //규영이의 총점
        int sumIn=0;    //인영이의 총점
        //라운드별 카드 비교 후 점수 계산
        for(int i=0;i<9;i++) {
            //규영이의 카드 숫자가 높을 경우
            if(roundGyu[i] > roundIn[i]) sumGyu+=roundGyu[i]+roundIn[i];
            //인영이의 카드 숫자가 높을 경우
            else sumIn +=roundGyu[i]+roundIn[i];
             
        }
        //총점 비교
         
        if(sumGyu > sumIn) win++;    //규영이의 총점이 클경우 이긴횟수 +1
        else if(sumGyu < sumIn) lose++;  //규영이의 총점이 작은 경우 진횟수 +1
    }
     
	/**
	 * arr 배열의 i, j 요소 swap
	 * @param i 요소1
	 * @param j 요소2
	 */
	private static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

}