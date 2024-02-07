package algo240205;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
/**
 * 
 * 메모리:19,448kb, 시간:103ms
 * 
 */
public class Solution_1228_암호문1_연민호_addAll사용 {
 
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
         
        List<String> list = new ArrayList<>();      //암호문 담을 자료구조
         
        for(int tc = 1; tc<=10;tc++) {
            list.clear();   //자료구조 초기화
             
            int N = Integer.parseInt(br.readLine());    //암호문의 길이 입력
             
            //압호문 입력
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i=0;i<N;i++) {
                 list.add(st.nextToken());
            }
             
            N = Integer.parseInt(br.readLine());    //명령어의 개수
             
            st = new StringTokenizer(br.readLine());
             
            //명령어의 개수만큼 반복
            for(int i=0;i<N;i++) {
                st.nextToken(); //I 값 버리기
                 
                int x = Integer.parseInt(st.nextToken());   //삽입할 인덱스
                int y = Integer.parseInt(st.nextToken());   //삽입할 개수
                 
                 
                //암호문 삽입
                //1. 추가할 암호문 담을 List
                List<String> addList = new ArrayList<>();
                //2. List에 추가할 암호문 담기
                for(int j=0; j<y; j++) {
                    addList.add(st.nextToken());
                }
                //3. 암호문에 추가할 list 삽입
                list.addAll( x , addList);
            }
            //출력
            sb.append('#').append(tc).append(' ');
            for(int i=0;i<10;i++) {
                sb.append(list.get(i)).append(' ');
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }
 
}