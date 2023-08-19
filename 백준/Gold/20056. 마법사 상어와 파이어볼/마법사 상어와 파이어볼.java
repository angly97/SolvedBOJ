import java.util.*;
import java.io.*;

/*
 * 한번에 이동하는 거리를 n으로 모듈러한 것을 받아서 틀림.
 * 앞으로 처음 받을때부터 모듈러로 받지 말자!!
 * 다음 행 =  ( 현재행 + 거리*방향%n + n)%n
 */
public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		
		// fires : 파이어볼 큐
		Queue<int[]> fires = new ArrayDeque<>();
		
		// map[i][j] : (i, j)에 있는 파이어볼들을 큐에 담고 있음
		Queue<int[]>[][] map = new ArrayDeque[n][n];
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++)
				map[i][j] = new ArrayDeque<>();
		}
		
		// 파이어볼 정보 입력 받기
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(in.readLine());
			
			fires.add(new int[] {
					Integer.parseInt(st.nextToken())-1,
					Integer.parseInt(st.nextToken())-1,
					Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken())
				});
		}
		
		
		int[][] deltas = {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};
		
		
		int nxtr, nxtc, nowFire[];
		int sumM, sumS, sumD, fireCnt;
		int renewM, renewS;
		Queue<int[]> fireQ;

		int size;
		
		// k번 이동 명령
		for(int round=0; round<k; round++) {
			
			// 1. 파이어볼 이동 : 파이어볼을 큐에서 뽑아내면서, 이동시키고 그 결과는 map에 저장
			while(!fires.isEmpty()) {
				nowFire = fires.poll();
				
				nxtr = (nowFire[0] + nowFire[3] * deltas[nowFire[4]][0] % n + n)%n;
				nxtc = (nowFire[1] + nowFire[3] * deltas[nowFire[4]][1] % n + n)%n;
				
				map[nxtr][nxtc].offer(new int[] {nowFire[2], nowFire[3], nowFire[4]});
			}

			
			// 2. 파이어볼 2개 이상인 칸은 4개로 분리됨
			for(int i=0; i<n; i++) {
				for(int j=0; j<n; j++) {
					
					if(map[i][j].size() == 1) {			// (i, j)에 파이어볼 1개 있으면 나누지않고, 다음 fires에 추가 
						nowFire = map[i][j].poll();
						fires.offer(new int[] {i, j, nowFire[0], nowFire[1], nowFire[2]});
					}
					
					else if(map[i][j].size() >= 2) {	// (i, j)에 있는 파이어볼을 4개로 나누기
						
						fireQ = map[i][j];				// (i, j)에 있는 파이어볼 큐
						fireCnt = fireQ.size();			// (i, j)에 있는 파이어볼 갯수
						
						// 현재 칸의 질량합, 속력합, 방향%2합 초기화
						sumM = sumS = sumD = 0;
						
						// fireQ에 있는 파이어볼들의 질량 합, 속력 합, 방향%2의 합 계산
						while(!fireQ.isEmpty()) {
							nowFire = fireQ.poll();

							sumM += nowFire[0];
							sumS += nowFire[1];
							sumD += nowFire[2]%2;
						}
						
						// 4개로 나뉠 파이어볼들의 질량과 속력 갱신
						renewM = sumM/5;
						if(renewM == 0) continue;
						
						renewS = sumS/fireCnt;
						
						
						// fireQ에 있는 파이어볼들의 방향이 모두 짝수거나 모두 홀수
						if(sumD == 0 || sumD == fireCnt) {
							
							// 4개로 나뉜 파이어볼의 방향들이 각각 0 2 4 6
							for(int renewD=0; renewD<=6; renewD+=2) {
								
								// fires에 담기
								fires.offer(new int[] {i, j, renewM, renewS, renewD});
							}
						}
						else {
							// 4개로 나뉜 파이어볼의 방향들이 각각 1 3 5 7
							for(int renewD=1; renewD<=7; renewD+=2) {
								
								// fires에 담기
								fires.offer(new int[] {i, j, renewM, renewS, renewD});
							}
						}
					}
				}
			}
		}
		

		int ans = 0;

		// 남은 질량 구하기
		while(!fires.isEmpty()) {
			nowFire = fires.poll();
			ans += nowFire[2];
		}
		
		System.out.println(ans);
	}
}