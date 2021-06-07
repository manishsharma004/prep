public class CoinChange {

    private static void printArray(int[] arr, int size) {
        System.out.print("[");
        for(int i=0; i<size; i++) {
            System.out.print(arr[i]);
            if (i != size -1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    private static int coinChangeDP(int[] coinList, int N) {
        int[][] dp = new int[coinList.length+1][N+1];
        
        for(int i=0; i<=N; i++) {
            dp[0][i] = 0;
        }
        for(int i=0; i<=coinList.length; i++) {
            dp[i][0] = 1;
        }

        for (int i=1; i<=coinList.length; i++) {
            for (int j=1; j<=N; j++) {
                int coin = coinList[i-1];
                dp[i][j] = (j >= coin ? dp[i][j - coin] : 0) + dp[i-1][j];
            }
        }
        for(int i=0; i<dp.length; i++) {
            printArray(dp[i], dp[i].length);
        }

        return dp[coinList.length][N];
    }

    private static int coinChange(int[] coinList, int last, int N) {
        printArray(coinList, last);
        System.out.println("last=" + last + ", N=" + N);
        if (N < 0) {
            return 0;
        }
        if (N == 0) {
            return 1;
        }

        if (last <= 0 && N >= 1) {
            return 0;
        }



        return coinChange(coinList, last-1, N) + coinChange(coinList, last, N - coinList[last-1]);
    }

    public static void main(String[] args) {
        int[] coinList = new int[] { 1, 2, 3 };
        int total = 4;
        // int count = coinChange(coinList, coinList.length, total);
        // System.out.println(count);
        int count = coinChangeDP(coinList, total);
        System.out.println(count);
    }
}
