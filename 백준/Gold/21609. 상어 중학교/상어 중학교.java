import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class Main {

	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder out = new StringBuilder();
	static StringTokenizer st;

	static int[][] deltas = {{1,0},{-1,0},{0,1},{0,-1}};
	static int n, m, field[][], visited[][];
	static List<int[]> curBlocks = new ArrayList<>();
	static int curRainbow;
	
	public static void dfs(int y, int x, int color, int flag) {
		visited[y][x] = flag;
		
		if(field[y][x] == 0)	curRainbow++;
		curBlocks.add(new int[] {y, x});
		
		int[] nxt = new int[2];
		for(int[] del: deltas) {
			nxt[0] = y+del[0];
			nxt[1] = x+del[1];
			if(0<=nxt[0] && nxt[0]<n && 0<=nxt[1] && nxt[1]<n && (field[nxt[0]][nxt[1]] == color ||field[nxt[0]][nxt[1]]==0) && visited[nxt[0]][nxt[1]] != flag) {
				dfs(nxt[0], nxt[1], color, flag);
			}
		}
	}
	
	public static void gravity() {
		int temp;
		
		for(int row = n-2; row>=0; row--) {
			for(int col = 0; col<n; col++) {
				if(field[row][col] < 0) 	continue;
				
				int ny = row+1;
				while(ny<n && field[ny][col] == -2) {
					ny++;
				}
				
				temp = field[row][col];
				field[row][col] = field[--ny][col];
				field[ny][col] = temp;
			}
		}
	}
	
	public static void rotate() {
		int[][] rotatedField = new int[n][n];
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				rotatedField[n-j-1][i] = field[i][j];
			}
		}

		copyArray(rotatedField, field);		// 회전된 것을 field에 복사
	}
	
	
	public static void main(String[] args) throws IOException {
		
		st = new StringTokenizer(in.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		// -1: 검, 0: 무지개, 1~m : 일반블록
		field = new int[n][n];
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(in.readLine());
			for(int j=0; j<n; j++) {
				field[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 1순위 그룹 찾기: 크기 제일 큼, 무지개 블록 제일 많음, 기준 행이 큼, 기준 열이 큼
		int score = 0;
		int flag;
		List<int[]> firstGroupBlocks;
		PriorityQueue<Group> pq = new PriorityQueue<>();
		
		while(true) {
			visited = new int[n][n];
			flag = 1;
			
			pq.clear();
			
			for(int i=0; i<n; i++) {
				for(int j=0; j<n; j++) {
					if(visited[i][j] == 0 && field[i][j] > 0) {
						curBlocks.clear();
						curRainbow = 0;
						
						dfs(i, j, field[i][j], flag++);
						
						if(curBlocks.size() > 1) {
							pq.offer(new Group(i, j, curRainbow, curBlocks));
						}
					}
				}
			}
			
			if(pq.size() == 0)	break;
			
			
			// 1순위 그룹에 속한 블록 빈칸 처리 + 점수 증가
			firstGroupBlocks = pq.poll().blocks;
			for(int[] block: firstGroupBlocks) {
				field[block[0]][block[1]] = -2;
			}
			score += firstGroupBlocks.size()*firstGroupBlocks.size();
			
			gravity();							// 중력
			
			rotate();
			
			gravity();							// 중력
		}
		
		System.out.println(score);

	}
	
	static class Group implements Comparable<Group>{
		int sy, sx, rainbow;
		List<int[]> blocks = new ArrayList<>();


		public Group(int sy, int sx, int rainbow, List<int[]> blocks) {
			super();
			this.sy = sy;
			this.sx = sx;
			this.rainbow = rainbow;
			this.blocks.addAll(blocks);
		}


		public int compareTo(Group g) {
			if(this.blocks.size() == g.blocks.size()) {
				if(this.rainbow == g.rainbow) {
					if(this.sy == g.sy)
						return Integer.compare(this.sx, g.sx)*-1;
					return Integer.compare(this.sy, g.sy)*-1;
				}
				return Integer.compare(this.rainbow, g.rainbow)*-1;
			}
			return Integer.compare(this.blocks.size(), g.blocks.size())*-1;
		}
	}
	
	public static void copyArray(int[][] origin, int[][] copied) {
		for(int i=0; i<n; i++)
			for(int j=0; j<n; j++)
				copied[i][j] = origin[i][j];
	}
}