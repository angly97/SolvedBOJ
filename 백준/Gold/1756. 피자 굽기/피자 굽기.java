import java.io.*;
import java.util.*;

public class Main {
    static int D, N, pos;
    static int[] oven;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        D = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        oven = new int[D + 1];
        st = new StringTokenizer(br.readLine());
        oven[0] = Integer.MAX_VALUE;
        for (int i = 1; i < D + 1; i++) {
            oven[i] = Integer.parseInt(st.nextToken());
            oven[i] = Math.min(oven[i - 1], oven[i]);
        }

        st = new StringTokenizer(br.readLine());
        pos = D + 1;
        for (int i = 0; i < N; i++) {
            int size = Integer.parseInt(st.nextToken());
            binaryPizza(size, 0, pos - 1);
        }

        System.out.println(pos);
    }

    private static void binaryPizza(int size, int start, int end) {
        while (start <= end) {
            int mid = (start + end) / 2;
            if (size <= oven[mid]) {
                start = mid + 1;
                pos = mid;
            } else {
                end = mid - 1;
            }
        }
    }

}