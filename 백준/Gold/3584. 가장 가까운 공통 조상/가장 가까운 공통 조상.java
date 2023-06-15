import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st = new StringTokenizer(in.readLine());
		StringTokenizer st;
		
		StringBuilder out = new StringBuilder();
		
		int T = Integer.parseInt(in.readLine());
		
		for(int t=0; t<T; t++) {
			
			int n = Integer.parseInt(in.readLine());
			
			// parent 설정
			int[] parent = new int[n+1];
			
			int a, b;
			for(int i=1; i<n; i++) {
				st = new StringTokenizer(in.readLine());

				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				
				parent[b] = a;
			}
			
			
			// 루트 찾기
			int r = 1;
			while(true) {
				if(parent[r] == 0) break;
				r = parent[r];
			}
			
			
			// 노드 입력
			st = new StringTokenizer(in.readLine());

			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			
			
			// a, b의 깊이 계산
			int[] depth = new int[n+1];
			depth[r] = 1;
			
			depth[a] = getDepth(a, parent, depth);
			depth[b] = getDepth(b, parent, depth);
			
			
			// 공통 조상 찾기
			
			while(depth[a] != depth[b]) {
				if(depth[a] < depth[b]) {
					b = parent[b];
				}
				else {
					a = parent[a];
				}
			}
			
			while(a != b) {
				a = parent[a];
				b = parent[b];
			}
			
			out.append(a+"\n");
		}
		
		System.out.print(out);
		
	}
	
	public static int getDepth(int now, int[] parent, int[] depth) {
		if(depth[now] != 0) {
			return depth[now];
		}
		depth[now] = getDepth(parent[now], parent, depth) + 1;
		return depth[now];
	}

}