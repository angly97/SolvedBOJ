import java.util.*;
import java.io.*;

public class Main {
	
	static int[][] deltas = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; // 북 동 남 서 (시계방향)
	static List<int[]> cctvs = new ArrayList<>();
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[n][m];
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(in.readLine());
			
			for(int j=0; j<m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				if(0 < map[i][j] && map[i][j] < 6) {
					cctvs.add(new int[] {i, j, map[i][j]});
				}
			}
		}
		
		permu(0, cctvs.size(), map);
		
		System.out.println(ans);
		
	}
	
	static int ans = Integer.MAX_VALUE;
	
	public static void permu(int idx, int end, int[][] map) throws Exception {
		// 종료조건 : cctv의 idx가 cctvs 리스트 길이와 같아졌을 때
		if(idx == end) {
			
			// map에서 0의 갯수 세고
			int emptyCnt = 0;
			
			for(int[] row : map) {
				for(int col : row)
					if(col == 0)	emptyCnt++;
			}
			
			// ans 갱신
			ans = Math.min(ans, emptyCnt);
			return;
		}
		
		
		// 현재 idx에 해당되는 cctv 위치와 타입
		int[] nowcctv = cctvs.get(idx);
		
		int rlen, clen;
		
		int[][] nowmap = copyMap(map.length, map[0].length, map);
		
		for(int i=0; i<4; i++) {
			
			rlen = nowmap.length;
			clen = nowmap[0].length;
			
			// 감시하는 범위 표시하기
			watchOffice(nowcctv, i, nowmap, rlen, clen);
			
			// 다음 턴으로
			permu(idx+1, end, nowmap);
			
			// 감시하는 범위 표시 되돌리기
			rollback(nowcctv, i, nowmap, rlen, clen);
			
			// 4방 탐색하는 cctv의 경우 회전이 의미없으니까 바로 탈출
			if(nowcctv[2] == 5) break;
			
			// <-> 탐색하는 경우는 1번만 회번해도 됨
			else if(nowcctv[2] == 2 && i ==1) break;
		}
	}
	
	
	private static void rollback(int[] nowcctv, int rotateCnt, int[][] nowmap, int rlen, int clen) {
		
		// 모든 CCIV가 한 방향 모두 수행 : 윗방향
		drawOneWay(1, nowcctv[0], nowcctv[1], (rotateCnt+0)%4, rlen, clen, nowmap);
		
		// 아랫방향
		if(nowcctv[2] == 2 || nowcctv[2] == 5)
			drawOneWay(1, nowcctv[0], nowcctv[1], (rotateCnt+2)%4, rlen, clen, nowmap);
		
		// 오른쪽
		if(nowcctv[2] >= 3)
			drawOneWay(1, nowcctv[0], nowcctv[1], (rotateCnt+1)%4, rlen, clen, nowmap);
		
		if(nowcctv[2] >= 4)
			drawOneWay(1, nowcctv[0], nowcctv[1], (rotateCnt+3)%4, rlen, clen, nowmap);
		
	}


	private static void watchOffice(int[] nowcctv, int rotateCnt, int[][] nowmap, int rlen, int clen) {
		
		// 모든 CCIV가 한 방향 모두 수행 : 윗방향
		drawOneWay(-1, nowcctv[0], nowcctv[1], (rotateCnt+0)%4, rlen, clen, nowmap);
		
		// 아랫방향
		if(nowcctv[2] == 2 || nowcctv[2] == 5)
			drawOneWay(-1, nowcctv[0], nowcctv[1], (rotateCnt+2)%4, rlen, clen, nowmap);
		
		// 오른쪽
		if(nowcctv[2] >= 3)
			drawOneWay(-1, nowcctv[0], nowcctv[1], (rotateCnt+1)%4, rlen, clen, nowmap);
		
		if(nowcctv[2] >= 4)
			drawOneWay(-1, nowcctv[0], nowcctv[1], (rotateCnt+3)%4, rlen, clen, nowmap);
		
	}

	private static void drawOneWay(int flag, int r, int c, int dir, int rlen, int clen, int[][] nowmap) {
		
		int[] del  = deltas[dir];
		
		int[] nxt = {r + del[0], c + del[1]};
		
		while(0<= nxt[0] && nxt[0] < rlen && 0<= nxt[1] && nxt[1] < clen && nowmap[nxt[0]][nxt[1]] != 6) {
			if(nowmap[nxt[0]][nxt[1]] <= 0) 	nowmap[nxt[0]][nxt[1]] += flag;
			nxt[0] += del[0];
			nxt[1] += del[1];
		}
		
	}

	public static int[][] copyMap(int rlen, int clen, int[][] origin) {
		
		int[][] target = new int[rlen][clen];
		
		for(int i=0; i<rlen; i++) {
			
			for(int j=0; j<clen; j++)
				target[i][j] = origin[i][j];
		}
		
		return target;
	}
	
}