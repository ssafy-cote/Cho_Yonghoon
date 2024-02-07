package algo240202;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class Solution_1873_상호의배틀필드_연민호 {
    final static int UP=0, DOWN=1, LEFT=2, RIGHT=3;    //명시적으로 사용위해
     
    //상하좌우 delta 값
    static int[] dr = {-1, 1, 0, 0};  
    static int[] dc = {0, 0, -1, 1};
     
    static char[] mark = {'^', 'v', '<', '>'};  //마지막 좌표 찍을 때 사용하기 위해
     
    static int H,W; // 맵의 높이와 넓이
    static char[][] map; // 맵 정보
     
    static int N; //지령의 개수
    static char[] cmds; //사용자 지령
     
    //전차 정보
    static int R,C,dir; //좌표와 방향
     
    public static void main(String[] args) throws IOException{
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
         
        int T = Integer.parseInt(br.readLine());
         
        for(int tc=1; tc<=T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
             
            //맵의 크기 정보
            H = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
             
            map = new char[H][W];
             
            //map 정보 입력
            for(int i=0;i<H;i++) {
                map[i] = br.readLine().toCharArray();   //String.toCharArray() - 문자열 => char배열
            }
            //지령의 개수
            N = Integer.parseInt(br.readLine());
            //지령 입력받기
            cmds = br.readLine().toCharArray();
             
            //step 01. 전차 탐색
            for(int i=0;i<H;i++) {
                for(int j=0;j<W;j++) {
                    if(map[i][j]=='^'||map[i][j]=='v'||map[i][j]=='<'||map[i][j]=='>') {
                        //전차 위치좌표 저장
                        R = i;
                        C = j;
                        //전차 방향 정보 받아오기
                        if(map[i][j]=='^') dir = UP;
                        else if(map[i][j]=='v') dir = DOWN;
                        else if(map[i][j]=='<') dir = LEFT;
                        else if(map[i][j]=='>') dir = RIGHT;
                         
                        //평지로 변경
                        map[i][j] = '.';
                         
                        break;  //전차는 한 개 뿐이 없으므로 탐색 중지
                    }
                }
            }
             
            //step 02. 지령 처리
            for(char cmd : cmds) {
                //shoot인경우
                if(cmd=='S') shoot(); //포탄 발사
                //move 인경우
                else move(cmd);
            }
             
            //step 03. 전차 정보 맵에 표기
            if(dir==UP) map[R][C] = '^';
            else if(dir==DOWN) map[R][C] = 'v';
            else if(dir==LEFT) map[R][C] = '<';
            else map[R][C] = '>';
            //mark 배열 사용 시
//          map[R][C] = mark[dir];
             
            //출력
            sb.append('#').append(tc).append(' ');
            for(int i=0; i<H; i++) {
                //new String(char[]) - char배열 => 문자열
                //String.valueof(char[]) - char배열 => 문자열
//              sb.append(new String(map[i])).append('\n');
                sb.append(String.valueOf(map[i])).append('\n');
            }
        }
        System.out.println(sb);
    }
 
    //이동
    private static void move(char cmd) {
        //1. 방향전환
        if(cmd=='U') dir=UP;
        else if(cmd=='D') dir=DOWN;
        else if(cmd=='L') dir=LEFT;
        else if(cmd=='R') dir=RIGHT;
         
        //2. 이동
        //해당 방향으로 이동했을 때 좌표
        int nr = R + dr[dir];
        int nc = C + dc[dir];
         
        if(nr < 0 || nr >= H || nc <0 || nc >= W) return;  //경계 벗어남
		if( map[nr][nc] !='.') return;	//평지 아님
         
        //실제 한 칸 이동
        R = nr;
        C = nc;
    }
 
    //포탄 발사
    private static void shoot() {
        //최초 포탄의 위치
        int r = R;
        int c = C;
         
        //포탄이 얼마나 이동할지 모르므로 무한 반복(반복문 한 번마다 한 칸 이동)
        while(true) {
             
            //포탄 한 칸 이동
            r += dr[dir];
            c += dc[dir];
            
            if(r < 0 || r >= H || c <0 || c >= W) break;	//경계 벗어남
			if( map[r][c]=='#') break;		//강철벽 만남
             
            //벽돌벽을 만났을 때
            else if(map[r][c] == '*') {
                map[r][c] = '.';  //부수기(평지 변경)
                break;  //소멸
            }
            //조건에 걸리지 않을 경우 다음 반복문으로(계속 이동)
        }
    }
}