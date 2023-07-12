import java.util.*;
import java.io.*;

public class Main {
	
	static int n, m, map[][];
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		List<int[]> aircons = new ArrayList<>();
		
		map = new int[n][m];
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(in.readLine());
		
			for(int j=0; j<m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			
				if(map[i][j] == 9) {
					aircons.add(new int[] {i, j});
				}
			}
		}

		boolean[][] seat = new boolean[n][m];
		boolean[][][] visited = new boolean[n][m][4];
		
		for(int[] aircon : aircons) {
			for(int d=0; d<4; d++)
				if(!visited[aircon[0]][aircon[1]][d])
					dfs(aircon[0], aircon[1], d, visited, seat);
		}
	
		int ans = 0;
		for(int i=0; i<n; i++)
			for(int j=0; j<m; j++)
				if(seat[i][j])
					ans++;
				
		System.out.println(ans);
	}
	
	static int[][] deltas = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	
	static void dfs(int r, int c, int d, boolean[][][] visited, boolean[][] seat) {
		
		
		seat[r][c] = true;
		visited[r][c][d] = true;
		
		int nxtr = r + deltas[d][0];
		int nxtc = c + deltas[d][1];
		
		
		if(0<=nxtr && nxtr<n && 0<=nxtc && nxtc<m) {
			
			switch(map[nxtr][nxtc]) {
			case 1:
				if(d%2 == 1)	d = 4-d;
				break;
			case 2:
				if(d%2 == 0)	d = 2-d;
				break;
			case 3:
				if(d < 2)		d = 1-d;
				else			d =5-d;
				break;
			case 4:
				d = 3-d;
			}
			
			if(!visited[nxtr][nxtc][d])
				dfs(nxtr, nxtc, d, visited, seat);
		}
	}
	
}