package algo240221;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * 메모리:102,328KB, 시간:429ms
 */
public class Solution_3289_서로소집합_연민호 {
    static int n, m;    //집합의 개수, 입력 연산의 개수
    static int[] parents;
     
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader( new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
         
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
        	StringTokenizer st = new StringTokenizer(br.readLine());
             
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
             
            //집합 생성
            parents = new int[n+1];
            makeSet();
             
            sb.append("#").append(tc).append(" ");
            for(int i=0; i<m; i++) {
                st = new StringTokenizer(br.readLine());
                int oper = Integer.parseInt(st.nextToken());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                 
                //합집합 연산의 경우
                if(oper == 0) {
                    union(a,b);
                }
                //a , b 가 같은 집합에 포함됐는지 확인 연산
                else {
                    // 두 원소의 집합의 대표원소가 같다면 1, 다르면 0 출력
                    sb.append( find(a) == find(b) ? 1 : 0 );
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
         
    }
    
    //집합 생성 메소드
    static void makeSet() {
        for (int i = 1; i <= n; i++) {
            parents[i] = i;
        }
    }
    
    //a포함 집합의 대표 원소 반환
    static int find(int a) {
        //인덱스 번호와 해당 인덱스의 요소가 일치한다면? 대표원소이므로 반환
        if(parents[a]==a) return a;
         
        return parents[a] = find(parents[a]);   //Path Compression
//      return find(parents[a]);
    }
     
    //a포함 집합과 b포함 집합 합치기(합집합)
    static boolean union(int a, int b) {
        int aRoot = find(a);    //a가 포함된 집합의 대표원소
        int bRoot = find(b);    //b가 포함된 집합의 대표원소
        
        if(aRoot == bRoot) return false;    //대표원소가 같다면 이미 같은 집합이므로 false 리턴
         
        parents[bRoot] = aRoot; //b집합의 대표원소가 a집합의 대표원소를 가리킴 (a집합에 b집합을 포함시킴)
        return true;
    }
}