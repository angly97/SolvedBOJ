import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder out = new StringBuilder();

        int T = Integer.parseInt(in.readLine());
        for(int t=0; t<T; t++){
            st = new StringTokenizer(in.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int r1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            int r2 = Integer.parseInt(st.nextToken());

            int dis = (int)(Math.pow(x1-x2, 2) + Math.pow(y1-y2, 2));

            int absDis = r1-r2;
            absDis *= absDis;

            int sumR = r1+r2;
            sumR *= sumR;

            int ans = 0;

            // 1개 - 외접 or 내접
            if(dis == 0 && r1 == r2){
                ans = -1;
            } else if(sumR == dis || absDis == dis){
                ans = 1;
            }
            else if(sumR > dis && dis > absDis){
                ans = 2;
            }

            out.append(ans+"\n");
        }
        System.out.print(out);
    }
}