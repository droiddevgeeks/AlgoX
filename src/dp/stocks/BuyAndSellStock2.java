package dp.stocks;

/**
 * We can buy and sell the stock any number of times.
 * In order to sell the stock, we need to first buy it on the same or any previous day.
 * We can’t buy a stock again after buying it once. In other words, we first buy a stock and then sell it.
 * After selling we can buy and sell again. But we can’t sell before buying and can’t buy before selling any previously bought stock.
 * Array={7, 1, 5, 3, 6, 4}
 * N=6
 * MaxProfit=7 as ((5-1) + (6-3))
 */
public class BuyAndSellStock2 {
    public static void main(String[] args) {
        int[] st = new int[]{7, 1, 5, 3, 6, 4};
        int maxProfit = findBuyAndSellStockRec(st, 0, 1);
        System.out.println("BuyAndSellStock2 Rec--->" + maxProfit);

        Integer[][] memo = new Integer[st.length][2];
        maxProfit = findBuyAndSellStockMemo(st, 0, 1, memo);
        System.out.println("BuyAndSellStock2 Memo--->" + maxProfit);

        maxProfit = findBuyAndSellStockTabular(st);
        System.out.println("BuyAndSellStock2 Tabular--->" + maxProfit);
    }

    private static int findBuyAndSellStockRec(int[] st, int ithDay, int buy) {
        if (ithDay == st.length) return 0;
        int take;
        int skip;
        if (buy == 1) {
            take = -st[ithDay] + findBuyAndSellStockRec(st, ithDay + 1, 0);
            skip = findBuyAndSellStockRec(st, ithDay + 1, 1);
        } else {
            take = st[ithDay] + findBuyAndSellStockRec(st, ithDay + 1, 1);
            skip = findBuyAndSellStockRec(st, ithDay + 1, 0);
        }
        return Math.max(take, skip);
    }

    private static int findBuyAndSellStockMemo(int[] st, int ithDay, int buy, Integer[][] memo) {
        if (ithDay == st.length) return 0;

        if (memo[ithDay][buy] != null) return memo[ithDay][buy];
        int take;
        int skip;
        if (buy == 1) {
            take = -st[ithDay] + findBuyAndSellStockMemo(st, ithDay + 1, 0, memo);
            skip = findBuyAndSellStockMemo(st, ithDay + 1, 1, memo);
        } else {
            take = st[ithDay] + findBuyAndSellStockMemo(st, ithDay + 1, 1, memo);
            skip = findBuyAndSellStockMemo(st, ithDay + 1, 0, memo);
        }
        return memo[ithDay][buy] = Math.max(take, skip);
    }

    private static int findBuyAndSellStockTabular(int[] st) {
        int n = st.length;
        int[][] dp = new int[n + 1][2];

        for (int i = n - 1; i >= 0; i--) {
            dp[i][1] = Math.max(-st[i] + dp[i + 1][0], dp[i + 1][1]);
            dp[i][0] = Math.max(st[i] + dp[i + 1][1], dp[i + 1][0]);
        }
        return dp[0][1];
    }
}
