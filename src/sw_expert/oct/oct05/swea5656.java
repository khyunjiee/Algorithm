package sw_expert.oct.oct05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
SWEA 5656 벽돌깨기
 */
public class swea5656 {

    private static class Point{
        int r,c,cnt; // 행,열,벽돌숫자
        public Point(int r, int c, int cnt) {
            super();
            this.r = r;
            this.c = c;
            this.cnt = cnt;
        }
    }
    private static int[] dr = {-1,1,0,0};
    private static int[] dc = {0,0,-1,1};
    private static int N,W,H,min;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(in.readLine());

        StringTokenizer st = null;
        for (int tc = 1; tc <= TC; tc++) {

            st = new StringTokenizer(in.readLine(), " ");
            N =  Integer.parseInt(st.nextToken());
            W =  Integer.parseInt(st.nextToken());
            H =  Integer.parseInt(st.nextToken());

            int[][] map = new int[H][W];

            for (int i = 0; i < H; i++) {
                st = new StringTokenizer(in.readLine(), " ");
                for (int j = 0; j < W; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            min = Integer.MAX_VALUE;
            go(0,map);
            System.out.println("#"+tc+" "+min);
        }
    }

    // 중복 순열로 구슬을 던짐
    private static void go(int cnt,int[][] map) {

        if(cnt == N) { //구슬을 다 던짐.
            // 남아있는 벽돌수 카운트 최소값 갱신
            int result = getRemain(map);
            min = Math.min(result, min);
            return;
        }

        int[][] newMap = new int[H][W];
        for (int c = 0; c < W; c++) { // 0열부터 마지막 열까지 시도

            // c열에 구슬이 던져졌을때 위에서 처음만나는 벽돌 찾기
            int r = 0;
            while(r<H && map[r][c]==0) r++;

            if(r==H) { // 구슬을 던졌지만 맞은 벽돌이 없는 경우(해당 열에 모두 빈칸)
                go(cnt+1,map); // 다음 구슬 던지기
            }else { // 맞은 벽돌이 있는 경우

                // 이전 cnt 까지의 map 상태를 복사해서 사용
                copy(map,newMap);
                // 맞은 벽돌 및 주변 벽돌 함께 제거 처리(연쇄적 처리)
                boom(newMap, r,c );
                // 제거된 벽돌들 내리기
                down(newMap);
                // 다음 구슬 던지기
                go(cnt+1, newMap);
            }
        }
    }

    private static int getRemain(int[][] map) {
        int count = 0;
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if(map[i][j]>0) count++;
            }
        }
        return count;
    }

    private static ArrayList<Integer>
            list = new ArrayList<Integer>();
    private static void down(int[][] map) {

        for (int c = 0; c < W; c++) {
            int r ;
            for (r = H-1; r >=0 ; --r) {
                if(map[r][c]>0) {
                    list.add(map[r][c]);
                    map[r][c] = 0;// 벽돌이 있던 자리는 빈칸으로
                }
            }// 부서지지 않은 벽돌만 리스트에 담기

            // 리스트에 있는 벽돌 제일 아래 행부터 채우기
            r = H;
            for(int b:list) map[--r][c] = b;
            list.clear();
        }
    }
    private static void boom(int[][] map, int r, int c) {
        // BFS로 함께 부숴질 벽돌 처리
        Queue<Point> queue = new LinkedList<Point>();
        if(map[r][c]>1) { // 벽돌의 숫자가 1보다 클때만 주변 벽돌 연쇄 처리
            queue.offer(new Point(r,c,map[r][c]));
        }
        map[r][c] = 0; // 벽돌을 제거하고 빈칸을 채우기

        Point p = null;
        while(!queue.isEmpty()) {
            p = queue.poll();
            for (int d = 0; d < 4; d++) {
                int nr = p.r;
                int nc = p.c;
                for (int k = 1; k < p.cnt; k++) {
                    nr += dr[d];
                    nc += dc[d];
                    if(nr>=0 && nr<H && nc>=0 && nc<W && map[nr][nc] != 0) {
                        if(map[nr][nc]>1) { // 벽돌의 숫자가 1보다 클때만 주변 벽돌 연쇄 처리
                            queue.offer(new Point(nr,nc,map[nr][nc]));
                        }
                        map[nr][nc] = 0;
                    }
                }
            }
        }

    }

    private static void copy(int[][] map, int[][] newMap) {
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                newMap[i][j] = map[i][j];
            }
        }
    }

}