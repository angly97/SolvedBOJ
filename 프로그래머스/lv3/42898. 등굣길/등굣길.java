import java.util.*;

class Solution {
    public int solution(int m, int n, int[][] puddles) {
        boolean[][] map = new boolean[m][n];
        for(int[] p : puddles) {
            map[p[0]-1][p[1]-1] = true;
        }
        
        int[][] dp = new int[m][n];
        for(int i=0; i<m; i++)
            Arrays.fill(dp[i], -1);
        
        dp[m-1][n-1] = 1;
        
        return dfs(0, 0, dp, map, m, n);
    }
        
    int[][] deltas = {{1, 0}, {0, 1}};
    int MOD = 1000000007;
    
    public int dfs(int r, int c, int[][] dp, boolean[][] map, int m, int n) {
        if(dp[r][c] > -1) {
            return dp[r][c];
        }
        
        dp[r][c] = 0;
        
        int[] nxt = new int[2];
        
        for(int[] del : deltas) {
            nxt[0] = r + del[0];
            nxt[1] = c + del[1];
            
            if(0<=nxt[0] && nxt[0]<m && 0<=nxt[1] && nxt[1]<n && !map[nxt[0]][nxt[1]]) {
                dp[r][c] += dfs(nxt[0], nxt[1], dp, map, m, n)%MOD;
                dp[r][c] %= MOD;
            }
        }
        
        return dp[r][c];
    }
}