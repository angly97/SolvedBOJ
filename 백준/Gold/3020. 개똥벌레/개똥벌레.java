import java.util.*;
import java.io.*;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int h = Integer.parseInt(st.nextToken());
		
		int len = n/2;
		
		int[] bottom = new int[len];
		int[] top = new int[len];
		
		for(int i=0; i<len; i++) {
			bottom[i] = Integer.parseInt(in.readLine());
			top[i] = Integer.parseInt(in.readLine());
		}

		Arrays.sort(bottom);
		Arrays.sort(top);
		
		int mincnt = n, cnt = 0, bcnt, tcnt;
		int termcnt = 0;
		
		for(int hei=1; hei<=h; hei++) {
			bcnt = len - bisearch(bottom, hei-1+0.1, len);
			tcnt = len - bisearch(top, h-hei+0.1, len);
			
			cnt =  bcnt + tcnt;
			
			if(cnt == mincnt) {
				termcnt++;
			}
			else if(cnt < mincnt) {
				mincnt = cnt;
				termcnt = 1;
			}
		}
		
		System.out.println(mincnt+" "+termcnt);
		
	}
	
	static int bisearch(int[] arr, double key, int len) {
		int max = len-1;
		int min = 0;
		int mid = 0;
		
		while(min <= max) {
			mid = (max + min)/2;
			
			if(arr[mid] > key)
				max = mid - 1;
			else
				min = mid + 1;
		}
		
		return max + 1;
	}
}