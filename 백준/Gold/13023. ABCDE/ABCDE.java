import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	public static int dfs(int cur, List<Integer>[] graph, int dept, boolean[] visited) {
		if(dept == 5) {
			return 1;
		}
		
		if(graph[cur] != null)
			for(int adj: graph[cur]) {
				if(!visited[adj]) {
					
					visited[adj] = true;
					if(dfs(adj, graph, dept+1, visited) == 1)
						return 1;
					visited[adj] = false;
				}
			}
			
		return 0;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int a, b;
		
		List<Integer>[] graph = new ArrayList[n];
		
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(in.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());

			if(graph[a] == null) graph[a] = new ArrayList<>();
			if(graph[b] == null) graph[b] = new ArrayList<>();

			graph[a].add(b);
			graph[b].add(a);
		}
		
		int result = 0;
		boolean[] visited = new boolean[n];
		for(int i=0; i<n; i++) {
			visited[i] = true;
			result = dfs(i, graph, 1, visited);
			if(result == 1)
				break;
			visited[i] = false;
		}

		System.out.println(result);
	}


}