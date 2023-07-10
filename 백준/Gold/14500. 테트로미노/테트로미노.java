import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int n, m, paper[][], ans;
	static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		StringBuilder out = new StringBuilder();

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		paper = new int[n][m];
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(in.readLine());
			for(int j=0; j<m; j++)
				paper[i][j] = Integer.parseInt(st.nextToken());
		}
		
		boolean[][] visited = new boolean[n][m];
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				dfs(i, j, paper[i][j], 0, visited);
			}
		}
		
		System.out.println(ans);
	}
	
	private static void dfs(int i, int j, int sum, int depth, boolean[][] visited) {
		if(depth == 3) {
			ans = Math.max(ans, sum);
			return;
		}
		
		visited[i][j] = true;
		
		int[] nxt = new int[2];
        
		for(int[] del: deltas) {
			nxt[0] = i + del[0];
			nxt[1] = j + del[1];
            
			if(0<=nxt[0] && nxt[0] < n && 0<=nxt[1] && nxt[1] < m && !visited[nxt[0]][nxt[1]]) {
				dfs(nxt[0], nxt[1], sum+paper[nxt[0]][nxt[1]], depth+1, visited);
				
				if(depth == 1) {
					
                    int curSum =  sum+paper[nxt[0]][nxt[1]];
                    
					for(int[] innerDel: deltas) {
                        
						if(innerDel[0] != del[0] && innerDel[1] != del[1]) {
							nxt[0] = i + innerDel[0];
							nxt[1] = j + innerDel[1];
						
                            if(0<=nxt[0] && nxt[0] < n && 0<=nxt[1] && nxt[1] < m && !visited[nxt[0]][nxt[1]]) {
								dfs(nxt[0], nxt[1], curSum+paper[nxt[0]][nxt[1]], depth+2, visited);
							}
						}
					}
				}
			}
		}
		
		visited[i][j] = false;
		
	}

}