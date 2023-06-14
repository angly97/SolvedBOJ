import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.parseInt(in.readLine());
		
		// 양방향 그래프 그리기
		List<Integer>[] bigraph = new ArrayList[n+1];
		for(int i=1; i<=n; i++) 
			bigraph[i] = new ArrayList<>();
		
		int a, b;
		for(int i=1; i<n; i++) {
			st = new StringTokenizer(in.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			
			bigraph[a].add(b);
			bigraph[b].add(a);
		}
		
		
		// bfs
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {1, 0});
		
		int[] dp = new int[n+1];	// dp[i] : i에서 1로 가는 움직임 횟수
		Arrays.fill(dp, -1);		// 초기화

		int[] now;
		while(!q.isEmpty()) {
			now = q.poll();
			
			dp[now[0]] = now[1];
			
			for(int nxt : bigraph[now[0]]) {
				if(dp[nxt] == -1) {
					q.offer(new int[] {nxt, now[1]+1});
				}
			}
		}
		
		
		int sum = 0;				// 리프노드에서 1까지 가는 움직임 횟수 저장 변수
		
		for(int i=2; i<=n; i++) {
		
			if(bigraph[i].size() == 1) {	// 리프노드
				sum += dp[i];
			}
		}
		
		System.out.println(sum % 2 == 0 ? "No" : "Yes");
	}
	
}