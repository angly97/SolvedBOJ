import java.util.*;
import java.io.*;

public class Main {
	
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st = new StringTokenizer(in.readLine());

		int n = Integer.parseInt(in.readLine());
		
		// 흰 방 : 갈 수 있는 칸(1), 검은 방 : 못가는 칸(0)
		char[][] map = new char[n][n];
		for(int i=0; i<n; i++)
			map[i] = in.readLine().toCharArray();
		
		
		int[][] deltas = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
		
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(0, 0, 0));
		
		int[][] visited = new int[n][n];
		for(int[] v : visited)
			Arrays.fill(v, Integer.MAX_VALUE);
		
		visited[0][0] = 0;
		
		Node now;
		int r, c, cnt, nxtCnt, nxt[] = new int[2];
		
		int ans = 0;
		
		while(!pq.isEmpty()) {
		
			now = pq.poll();
			
			r = now.r;
			c = now.c;
			cnt = now.cnt;
			
			if(r == n-1 && c == n-1) {
				ans = cnt;
				break;
			}
			
			for(int[] del : deltas) {
				nxt[0] = r + del[0];
				nxt[1] = c + del[1];
				
				if(0<=nxt[0] && nxt[0]<n && 0<=nxt[1] && nxt[1]<n) {
					nxtCnt = cnt + (1-map[nxt[0]][nxt[1]]+'0');
					if(nxtCnt < visited[nxt[0]][nxt[1]]) {
						 visited[nxt[0]][nxt[1]] = nxtCnt;
						 pq.offer(new Node(nxt[0], nxt[1], nxtCnt));
					}
				}
			}
			
		}
		
		System.out.println(ans);
		
	}
	
	static class Node implements Comparable<Node> {
		int r, c, cnt;
		
		public Node(int r, int c, int cnt) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}
		
		public int compareTo(Node n) {
			return Integer.compare(this.cnt, n.cnt);
		}
	}
}