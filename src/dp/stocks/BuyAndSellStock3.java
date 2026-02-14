package dp.stocks;

/**
 * Same as BuyAndSellStock2 but with 2 max txn allowed.
 * txn = buy +sell
 * Array={3, 3, 5, 0, 0, 1, 4}
 * N=8
 */
public class BuyAndSellStock3 {
    public static void main(String[] args) {
        int[] st = new int[]{3, 3, 5, 0, 0, 1, 4};
        int maxProfitWithCap = findBuyAndSellStock3Rec(st, 0, 1, 2);
        System.out.println("BuyAndSellStock3 Rec-->" + maxProfitWithCap);

        Integer[][][] memo = new Integer[st.length][2][3]; // 2 for  buy or sell, 3 for 0, 1, 2 txnCap
        maxProfitWithCap = findBuyAndSellStock3Memo(st, 0, 1, 2, memo);
        System.out.println("BuyAndSellStock3 Memo-->" + maxProfitWithCap);


        int txnCap = 2;
        maxProfitWithCap = findBuyAndSellStock3Tabular(st, txnCap);
        System.out.println("BuyAndSellStock3 Tabular-->" + maxProfitWithCap);
    }

    private static int findBuyAndSellStock3Rec(int[] st, int ithDay, int buy, int txnCap) {
        if (ithDay == st.length || txnCap == 0) return 0;
        int pick, skip;
        if (buy == 1) {
            pick = -st[ithDay] + findBuyAndSellStock3Rec(st, ithDay + 1, 0, txnCap);
            skip = findBuyAndSellStock3Rec(st, ithDay + 1, 1, txnCap);
        } else {
            pick = st[ithDay] + findBuyAndSellStock3Rec(st, ithDay + 1, 1, txnCap - 1);
            skip = findBuyAndSellStock3Rec(st, ithDay + 1, 0, txnCap);
        }
        return Math.max(pick, skip);
    }

    private static int findBuyAndSellStock3Memo(int[] st, int ithDay, int buy, int txnCap, Integer[][][] memo) {
        if (ithDay == st.length || txnCap == 0) return 0;

        if (memo[ithDay][buy][txnCap] != null) return memo[ithDay][buy][txnCap];
        int pick, skip;
        if (buy == 1) {
            pick = -st[ithDay] + findBuyAndSellStock3Memo(st, ithDay + 1, 0, txnCap, memo);
            skip = findBuyAndSellStock3Memo(st, ithDay + 1, 1, txnCap, memo);
        } else {
            pick = st[ithDay] + findBuyAndSellStock3Memo(st, ithDay + 1, 1, txnCap - 1, memo);
            skip = findBuyAndSellStock3Memo(st, ithDay + 1, 0, txnCap, memo);
        }
        return memo[ithDay][buy][txnCap] = Math.max(pick, skip);
    }

    private static int findBuyAndSellStock3Tabular(int[] st, int txnCap) {
        int n = st.length;
        int[][][] dp = new int[n + 1][2][txnCap + 1];


        for (int i = n - 1; i >= 0; i--) {
            for (int canBuy = 0; canBuy <= 1; canBuy++) {
                for (int cap = 1; cap <= txnCap; cap++) {
                    if (canBuy == 1) {
                        int pick = -st[i] + dp[i + 1][0][cap];
                        int skip = dp[i + 1][1][cap];
                        dp[i][canBuy][cap] = Math.max(pick, skip);
                    } else {
                        int pick = st[i] + dp[i + 1][1][cap - 1];
                        int skip = dp[i + 1][0][cap];
                        dp[i][canBuy][cap] = Math.max(pick, skip);
                    }
                }
            }
        }
        return dp[0][1][2];
    }
}
