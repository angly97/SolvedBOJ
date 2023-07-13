import java.util.*;
import java.io.*;


public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st = new StringTokenizer(in.readLine());
		StringTokenizer st;
		
		int n = Integer.parseInt(in.readLine());
		
		int[][] map = new int[n][n];
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(in.readLine());
			
			for(int j=0; j<n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		int[][] deltas = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
		
		
		int minlen = n*n;
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				
				// 1. 섬을 만나면, 그 섬의 1들을 모두 2로 바꾸고, 섬의 가장자리를 starts 리스트에 담을 것
				// 2. starts 리스트를 돌면서 다른 섬(-1이 되지 않은 1들)까지 최단거리 구할것
				if(map[i][j] == 1) {
					
					Queue<int[]> q = new ArrayDeque<>();
					q.offer(new int[] {i, j});

					boolean[][] visited = new boolean[n][n];
					visited[i][j] = true;
					
					Queue<int[]> starts = new ArrayDeque<>();		// 다리가 시작될 수 있는 좌표들
					
					
					// 1. 현재 섬 2처리하면서, 현재 섬의 가장자리를 starts 리스트에 넣기
					map[i][j] = 2;				// 섬 방문 처리
					
					int[] now, nxt = new int[2];
					
					while(!q.isEmpty()) {
						now = q.poll();

						boolean isStart = false;
						
						for(int[] del : deltas) {
							nxt[0] = now[0] + del[0];
							nxt[1] = now[1] + del[1];
							
							if(0<=nxt[0] && nxt[0]<n && 0<=nxt[1] && nxt[1]<n) {
								
								if(map[nxt[0]][nxt[1]] == 1) {
									map[nxt[0]][nxt[1]] = 2;
									
									q.offer(new int[] {nxt[0], nxt[1]});
								}
								else if(map[nxt[0]][nxt[1]] == 0) {
									isStart = true;
								}
							}
						}
						if(isStart)
							starts.add(new int[] {now[0], now[1]});
					}
					
					
					// 2. starts에서 다른 섬까지 최단 거리 구하기
					int len = 0;
					
					out : while(!starts.isEmpty()) {
					
						int size = starts.size();
						
						while(size-->0) {
							now = starts.poll();
							
                            if(map[now[0]][now[1]] == 1) {
								minlen = Math.min(minlen, len-1);
								break out;
							}
							
							for(int[] del : deltas) {
								nxt[0] = now[0] + del[0];
								nxt[1] = now[1] + del[1];
								
								if(0<=nxt[0] && nxt[0]<n && 0<=nxt[1] && nxt[1]<n && !visited[nxt[0]][nxt[1]]) {
									visited[nxt[0]][nxt[1]] = true;
									starts.offer(new int[] {nxt[0], nxt[1]});
								}
							}
						}
						
						len++;
					}
					
				}
			}
		}
		
		
		System.out.println(minlen);
		
		
	}
}