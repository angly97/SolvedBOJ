import java.io.*;
import java.util.*;

public class Main {

	static int n, c;
	
	static Deque<Integer>[] cards;
	static List<Character>[] oper;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
//		StringTokenizer st;
		
		n = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		
		// cards[i] : i번째 사람이 내는 카드 순서
		cards = new ArrayDeque[n+1];
		for(int i=0; i<=n; i++)
			cards[i] = new ArrayDeque<>();
		
		for(int i=1; i<=n; i++) {
			st = new StringTokenizer(in.readLine());
			
			for(int j=1, cnt = Integer.parseInt(st.nextToken()); j<=cnt; j++) {
				cards[i].add(Integer.parseInt(st.nextToken()));
			}
		}
		
		// oper[i] : 카드 i에 적용되는 연산 순서
		oper = new ArrayList[c+1];
		for(int i=0; i<=c; i++)
			oper[i] = new ArrayList<>();
		
		for(int i=1; i<=c; i++) {
			st = new StringTokenizer(in.readLine(), ",");
			
			while(st.hasMoreTokens()) {
				oper[i].add(st.nextToken().split(" ")[1].charAt(0));
			}
		}
		
		
		// 순열 돌리면서 계산
		permu(0, -1, new char[10], new boolean[c+1]);

		
		// 결과 출력
		StringBuilder out = new StringBuilder();
		for(String word : words) {
			out.append(word+"\n");
		}
		
		System.out.println(out);
		
	}
	
	
	// 결과물들
	static Set<String> words = new TreeSet<>();

	
	// cnt : 현재까지 사용한 카드 갯수
	// str : 현재까지 만들어진 문자열
	// idx : 현재까지 만들어진 문자열의 미지막 인덱스
	// used[i] : 카드 i의 사용 여부
	public static void permu(int cnt, int idx, char[] str, boolean[] used) {

		// 종료조건 : 더 사용할 카드 없는 경우
		if(cnt == c) {
			if(idx == -1)
				words.add("EMPTY");
			else{
				StringBuilder out = new StringBuilder();
				for(int i=0; i<=idx; i++)
					out.append(str[i]);
				words.add(out.toString());
			}
			return;
		}
		
		
		int nxtidx;			// 다음턴에서 문자열 길이 (는 아니고 사실 마지막 인덱스 : 길이-1)
		int delidx = 0;		// 삭제될 인덱스
		
		// 사람 턴
		turn : for(int person = 1; person <= n; person++) {
			
			// 현재 사람이 내야할 카드가 없다면 PASS !!!
			if(cards[person].size() == 0) continue;
			
			// 현재 사람이 내야할 카드
			int nowcard = cards[person].getFirst();
			
			// 현재 카드 사용한 적 없을 경우
			if(!used[nowcard]) {
				
				// 다음 턴에 사용될 문자열
				char[] nxtstr = new char[10];
				for(int i=0; i<=idx; i++)
					nxtstr[i] = str[i];
				
				// 다음턴에 사용될 문자열 길이 초기화
				nxtidx = idx;
				
				// 현재 카드 사용 처리
				used[nowcard] = true;
				cards[person].pollFirst();
				
				// 현재 카드에 적용되는 모든 연산 수행
				for(char op : oper[nowcard]) {
					
					// ADD : 문자열 뒤에 op 추가 & nxtidx 값 증가
					if('a' <= op && op <= 'z') {
						nxtstr[++nxtidx] = op;
					}
					
					// DEL : delidx 위치의 문자 삭제 & nxtidx 값 감소
					else {
						delidx = (int)(op-'0');		// 삭제되는 위치
						
						if(nxtidx < delidx) {		// 삭제될 수 없는 위치면 ERROR
							words.add("ERROR");

							used[nowcard] = false;
							cards[person].offerFirst(nowcard);
							
							continue turn;				// 다음 사람으로 넘어가
						}
						
						// delidx 위치의 문자 삭제 : 한 칸씩 당김
						for(; delidx<nxtidx; delidx++) {
							nxtstr[delidx] = nxtstr[delidx+1];
						}
						nxtidx--;
					}
				}
				
				// 다음 턴으로
				permu(cnt+1, nxtidx, nxtstr, used);
				
				used[nowcard] = false;
				cards[person].offerFirst(nowcard);
			}
			
		}
		
	}
}