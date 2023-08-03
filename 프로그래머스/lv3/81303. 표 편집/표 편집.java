import java.util.*;

class Solution {
    public String solution(int n, int k, String[] cmdArr) {
        
        // up[i] : i의 바로 윗칸
        int[] up = new int[n];
        for(int i=0; i<n; i++)
            up[i] = i-1;
        
        // down[i] : i의 바로 아랫칸
        int[] down = new int[n];
        for(int i=0; i<n; i++)
            down[i] = i+1;
        
        // deleted[i] : i번째 칸이 삭제 되었는지
        boolean[] deleted = new boolean[n];
        
        // recentD : 최근 삭제된 칸의 위치
        Stack<Integer> recentD = new Stack<>();
        
        // 현재 선택된 행
        int now = k;
        
        StringTokenizer st;
        char order;
        int x, alive, nxt;
        
        for(String cmd : cmdArr) {
            st = new StringTokenizer(cmd);
            
            order = st.nextToken().charAt(0);
            // System.out.println("-------------------------------");
            // System.out.println("up: "+ Arrays.toString(up));
            // System.out.println("down: "+ Arrays.toString(down));
            // System.out.print(now+" -> ");
            switch(order) {
                case 'U':
                    x = Integer.parseInt(st.nextToken());   // 움직일 칸 갯수
                    now = ux(now, x, up, deleted, n);
                    break;
                case 'D':
                    x = Integer.parseInt(st.nextToken());   // 움직일 칸 갯수
                    now = dx(now, x, down, deleted, n);
                    break;
                case 'C':
                    // 현재 칸 삭제하고, 그 아랫칸 선택
                    // 현재 칸이 맨 마지막일 경우, 제일 윗칸 선택
                    
                    nxt = down[now] == n ? up[now] : down[now];
                    
                    c(now, up, down, n);
                    recentD.push(now);
                    deleted[now] = true;
                    
                    now = nxt;
                    break;
                case 'Z':
                    alive = recentD.pop();
                    z(alive, up, down, deleted, n);
                    deleted[alive] = false;
                    break;
            }
            // System.out.println(now);
            // System.out.println("up: "+ Arrays.toString(up));
            // System.out.println("down: "+ Arrays.toString(down));
        }
        
        // System.out.println(Arrays.toString(deleted));
        
        StringBuilder out = new StringBuilder();
        for(int i=0; i<n; i++)
            if(deleted[i]) 
                out.append('X');
            else
                out.append('O');
        
        return out.toString();
    }
    
    int ux(int now, int x, int[] up, boolean[] deleted, int n) {
        while(x--> 0) {
            now = up[now];
        }
        return now;
    }
    
    int dx(int now, int x, int[] down, boolean[] deleted, int n) {
        while(x-->0) {
            now = down[now];
        }
        return now;
    }
    
    void c(int now, int[] up, int[] down, int n) {
        // now의 윗칸과 아랫칸을 연결
        if(up[now] > -1)                       // 이거 if 처리 안해서 에러남
            down[up[now]] = down[now];
        
        if(down[now] < n)                    // 이거 if 처리 안해서 에러남: now가 맨 마지막일 때
            up[down[now]] = up[now];
    }
    
    void z(int now, int[] up, int[] down, boolean[] deleted, int n) {
        // now의 바로 윗칸 구하기
        int nxtUp = up[now];
        while(nxtUp > -1) {
            if(!deleted[nxtUp]) break;
            nxtUp = up[nxtUp];
        }
        
        // now의 바로 아랫칸 구하기
        int nxtDown = down[now];
        while(nxtDown < n) {
            if(!deleted[nxtDown]) break;
            nxtDown = down[nxtDown];
        }
        
        // now와 now의 윗칸 연결
        if(nxtUp > -1) {                 // if문 없어서 에러났었음: now가 맨 앞일 때
            down[nxtUp] = now;
            up[now] = nxtUp;
        }
        
        // now와 now의 아랫칸 연결
        if(nxtDown < n) {              // if문 없어서 에러났었음: now가 맨 끝일 때
            down[now]=  nxtDown;
            up[nxtDown] = now;
        }
    }
    
}