import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[n][m];
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(in.readLine());
			for(int j=0; j<m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		boolean[][] visited = new boolean[n][m];
		
		int[][] deltas = {{-1,0}, {-1, 1}, {-1, -1}, {1,0}, {1,1}, {1,-1}, {0, 1}, {0, -1}};
		
		int ans = 0;
		
		int[] now, nxt = new int[2];
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				
				if(!visited[i][j]) {
					boolean ok = true;
					
					Queue<int[]> q = new ArrayDeque<>();
					q.offer(new int[] {i, j});
					
					visited[i][j] = true;
					
					while(!q.isEmpty()) {
						now = q.poll();
						
						for(int[] del : deltas) {
							nxt[0] = now[0] + del[0];
							nxt[1] = now[1] + del[1];
							
							if(0<=nxt[0] && nxt[0]<n && 0<=nxt[1] && nxt[1]<m) {
								if(map[nxt[0]][nxt[1]] == map[i][j] && !visited[nxt[0]][nxt[1]]) {
									visited[nxt[0]][nxt[1]] = true;
									q.offer(new int[] {nxt[0], nxt[1]});
								}
								else if(map[nxt[0]][nxt[1]] > map[i][j]) {
									ok = false;
								}
							}
						}
					}
					
					if(ok) {
						ans++;
//						System.out.println(i+" "+j);
					}
				}
				
			}
		}
		
		System.out.println(ans);
	}

}