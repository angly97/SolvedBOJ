import java.util.*;
import java.io.*;


/*
 * 재방문 가능
 * 바로 이미 지나왔던 위치가 불이 켜져서 기존에는 방문할 수 없었지만 이제 접근이 가능하게 된 경우
 */
public class Main {
	
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
	
		// switches[i][j] : (i, j)에서  스위치를 건드릴수있는 좌표 리스트
		List<int[]>[][] switches = new ArrayList[n][n];
		for(int i=0; i<n; i++)
			for(int j=0; j<n; j++)
				switches[i][j] = new ArrayList<>();
		
		int x, y, a, b;
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(in.readLine());

			x = Integer.parseInt(st.nextToken())-1;
			y = Integer.parseInt(st.nextToken())-1;
			a = Integer.parseInt(st.nextToken())-1;
			b = Integer.parseInt(st.nextToken())-1;
			
			switches[x][y].add(new int[] {a, b});
		}
		
		
		int[][] deltas = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

		int ans = 1;
		
		boolean[][] switchOn = new boolean[n][n];
		switchOn[0][0] = true;
		
		
		while(true) {
			boolean on = false;
			
			Queue<int[]> q = new ArrayDeque<>();
			q.offer(new int[] {0, 0});
			
			boolean[][] visited = new boolean[n][n];
			visited[0][0] = true;

			int[] now, nxt = new int[2];
			
			while(!q.isEmpty()) {
				now = q.poll();
				
				// 불 켜기
				for(int[] point : switches[now[0]][now[1]]) {
					if(!switchOn[point[0]][point[1]]) {
						on = true;
						ans++;
						switchOn[point[0]][point[1]] = true;
					}
				}
				
				
				for(int d=0; d<4; d++) {
					nxt[0] = now[0] + deltas[d][0];
					nxt[1] = now[1] + deltas[d][1];
					
					if(0<=nxt[0] && nxt[0]<n && 0<=nxt[1] && nxt[1]<n && !visited[nxt[0]][nxt[1]]) {
						if(switchOn[nxt[0]][nxt[1]]) {
							visited[nxt[0]][nxt[1]] = true;
							q.offer(new int[] {nxt[0], nxt[1]});
						}
					}
				}
				
			}
			
			if(!on) break;
		}

		
		System.out.println(ans);
		
	}
	
}