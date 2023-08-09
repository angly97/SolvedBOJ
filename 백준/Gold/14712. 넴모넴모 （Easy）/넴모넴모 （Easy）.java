import java.util.*;
import java.io.*;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		dfs(new int[n][m], 0, n*m, n, m);
		
		System.out.println(ans);
	}
	
	static int ans;
	
	public static void dfs(int[][] map, int start, int end, int n, int m) {
		
		ans++;
		
		int r, c;
		
		for(int i=start; i<end; i++) {
			r = i/m;
			c = i%m;
			if(r >= 1 && c>=1 && (map[r-1][c] + map[r][c-1] + map[r-1][c-1])== 3 ) continue;
			
			map[r][c] = 1;
			dfs(map, i+1, end, n, m);
			map[i/m][i%m] = 0;
		}
	}
}