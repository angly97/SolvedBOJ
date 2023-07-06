import java.util.*;
import java.io.*;

public class Main {
	
	static int n, m;
	static int[][] deltas = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
	static char[][] board;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		int[] marbles = new int[4];		// {빨간공 행, 빨간공 열, 파란공 행, 파란공 열}
		
		board = new char[n][];
		
		for(int i=0; i<n; i++) {
			
			board[i] = in.readLine().toCharArray();
			
			for(int j=0; j<m; j++) {
				
				if(board[i][j] == 'R') {
					
					marbles[0] = i;
					marbles[1] = j;
					board[i][j] = '.';
				}
				else if(board[i][j] == 'B') {

					marbles[2] = i;
					marbles[3] = j;
					board[i][j] = '.';
				}
			}
		}
		
		
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {marbles[0], marbles[1], marbles[2], marbles[3]});

		// visited[9451] : 빨간 공 위치 (9, 4), 파란공 위치(5, 1) 인 적 있는지 여부
		boolean[] visited = new boolean[10000];
		visited[toCode(marbles)] = true;
		
		int isPossible = 0;
		
		int size, round = 0;
		int[] now, nxt = null;
		while(round++<=10 && !q.isEmpty()) {		// <= 를 <로 써서 틀렸었음;;
			
			size = q.size();
			while(size-->0) {
				
				now = q.poll();
				
				if(board[now[2]][now[3]] == 'O') {	// 파란공이 구멍이 골인 => 실패한거니까 패스
					continue;
				}
				else if(board[now[0]][now[1]] == 'O') {	// 파란건 구멍에 안들어갔으면서 빨간것만 들어간 경우 성공!!
					isPossible = 1;
					break;
				}
				
				for(int dir=0; dir<4; dir++) {
					switch(dir) {
					case 0:
						nxt = up(now, deltas[dir]);
						break;
					case 1:
						nxt = down(now, deltas[dir]);
						break;
					case 2:
						nxt = right(now, deltas[dir]);
						break;
					case 3:
						nxt = left(now, deltas[dir]);
						break;
					}
					
					
					int code = toCode(nxt);
					
					if(!visited[code]) {
						visited[code] = true;
						q.offer(new int[] {nxt[0], nxt[1], nxt[2], nxt[3]});
					}
				}
			}
			
			// 종료조건
			if(isPossible == 1) break;
		}
		
		System.out.println(isPossible);
	}
	
	private static int[] left(int[] marbles, int[] del) {
		
		int[] nxt = new int[4];
		
		// R가 B보다 먼저 움직임
		if(marbles[1] <= marbles[3]) {

			move(nxt, 0, 1, del, marbles);
			
			nxt[2] = marbles[2];
			nxt[3] = marbles[3];
			move(nxt, 2, 3, del, nxt);
		}
		
		// B가 R보다 먼저 움직임
		else {
			
			move(nxt, 2, 3, del, marbles);
			
			nxt[0] = marbles[0];
			nxt[1] = marbles[1];
			move(nxt, 0, 1, del, nxt);
		}
		
		return nxt;
	}

	private static int[] right(int[] marbles, int[] del) {
		
		int[] nxt = new int[4];
		
		// R가 B보다 먼저 움직임
		if(marbles[1] >= marbles[3]) {

			move(nxt, 0, 1, del, marbles);
			
			nxt[2] = marbles[2];
			nxt[3] = marbles[3];
			move(nxt, 2, 3, del, nxt);
		}
		
		// B가 R보다 먼저 움직임
		else {
			
			move(nxt, 2, 3, del, marbles);
			
			nxt[0] = marbles[0];
			nxt[1] = marbles[1];
			move(nxt, 0, 1, del, nxt);
		}
		
		return nxt;
	}

	private static int[] down(int[] marbles, int[] del) {
		
		int[] nxt = new int[4];
		
		// R가 B보다 먼저 움직임
		if(marbles[0] >= marbles[2]) {

			move(nxt, 0, 1, del, marbles);
			
			nxt[2] = marbles[2];
			nxt[3] = marbles[3];
			move(nxt, 2, 3, del, nxt);
		}
		
		// B가 R보다 먼저 움직임
		else {
			
			move(nxt, 2, 3, del, marbles);
			
			nxt[0] = marbles[0];
			nxt[1] = marbles[1];
			move(nxt, 0, 1, del, nxt);
		}
		
		return nxt;
	}

	static int[] up(int[] marbles, int[] del) {
		
		int[] nxt = new int[4];
		
		// R가 B보다 먼저 움직임
		if(marbles[0] <= marbles[2]) {

			move(nxt, 0, 1, del, marbles);
			
			nxt[2] = marbles[2];
			nxt[3] = marbles[3];
			move(nxt, 2, 3, del, nxt);
		}
		
		// B가 R보다 먼저 움직임
		else {
			
			move(nxt, 2, 3, del, marbles);
			
			nxt[0] = marbles[0];
			nxt[1] = marbles[1];
			move(nxt, 0, 1, del, nxt);
		}
		
		return nxt;
	}
	
	static void move(int[] pos, int r, int c, int[] del, int[] marbles) {

		pos[r] = marbles[r] + del[0];
		pos[c] = marbles[c] + del[1];
		
		while(board[pos[r]][pos[c]] == '.' && (pos[r] != marbles[2-r] || pos[c] != marbles[4-c])) {

			
			pos[r] += del[0];
			pos[c] += del[1];
			
		}
		
		if(board[pos[r]][pos[c]] != 'O') {
			pos[r] -= del[0];
			pos[c] -= del[1];
		}
	}
	
	static int toCode(int[] marbles) {
		int code = 0;
		for(int i=0; i<4; i++) {
			code *= 10;
			code += marbles[i];
		}
		return code;
	}
}