import java.util.*;
import java.io.*;


public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder out = new StringBuilder();
		
		int T = Integer.parseInt(in.readLine());
		for(int t=0; t<T; t++) {
			
			int n = Integer.parseInt(in.readLine());
			
			int[] students = new int[n];

			// 학생들이 같이 텀프하고싶은 사람 입력받기
			st = new StringTokenizer(in.readLine());
			for(int i=0; i<n; i++) {
				students[i] = Integer.parseInt(st.nextToken())-1;
			}

			int[] visited = new int[n];
			
			for(int i=0; i<n; i++) {
				if(visited[i] == 0) {
					dfs(i, visited, students);
				}
			}
			
			int notCycleCnt = 0;
			for(int flag : visited) {
				if(flag != 1)	notCycleCnt++;
			}
			
			out.append(notCycleCnt+"\n");
		}
		
		System.out.print(out);
	}
	
	
	// cycle[now] (0 : 방문 전, 1: 사이클임, -1 : 방문함, -2 : 방문한적있고 사이클 아닌거 확실) 
	public static int dfs(int now, int[] visited, int[] students) {
		
		// 방문한 적 있는 곳에 다시 들렀으면, 사이클 생성
		if(visited[now] == -1) {
			return now;
		}
		
		// now 방문처리
		visited[now] = -1;
		
		// students[now]은 사이클이 될 수 없음
		if(visited[students[now]] == 1 || visited[students[now]] == -2) {
			visited[now] = -2;
			return -1;
		}
		
		int c = dfs(students[now], visited, students);
		if(c != -1) {
			visited[now] = 1;				// now는 사이클
			if(now == c)		return -1;
			else				return c;
		}
		
		visited[now] = -2;					// now는 사이클이 될 수 없음
		return -1;
	}
}