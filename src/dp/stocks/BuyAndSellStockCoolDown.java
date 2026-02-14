package dp.stocks;

/**
 * Same as BuyAndSellStock2, just after selling , very next day we can't buy again.
 * We can’t buy a stock on the very next day of selling it. This is the cooldown clause.
 * So after selling pass i+2 day;
 * Example 1:
 * Arr=[4,9, 0, 4, 10]
 * N=5
 * Max Profit=11 ((9-4)+(10-4))
 */
public class BuyAndSellStockCoolDown {
    public static void main(String[] args) {
        int[] st = new int[]{4, 9, 0, 4, 10};

        int maxProfit = findBuyAndSellStockCoolDown(st, 0, 1);
        System.out.println("BuyAndSellStockCoolDown-->" + maxProfit);
    }

    private static int findBuyAndSellStockCoolDown(int[] st, int ithDay, int buy) {
        if (ithDay >= st.length) return 0; // BuyAndSellStock2 me if( ithDay == st.length)

        int pick, skip;
        if (buy == 1) {
            pick = -st[ithDay] + findBuyAndSellStockCoolDown(st, ithDay + 1, 0);
            skip = findBuyAndSellStockCoolDown(st, ithDay + 1, 1);
        } else {
            pick = st[ithDay] + findBuyAndSellStockCoolDown(st, ithDay + 2, 1); // ithDay + 2 to skip next day after sell
            skip = findBuyAndSellStockCoolDown(st, ithDay + 1, 0);
        }
        return Math.max(pick, skip);
    }
}
