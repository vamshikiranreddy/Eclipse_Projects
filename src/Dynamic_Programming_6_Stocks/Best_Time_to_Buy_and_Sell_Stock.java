package Dynamic_Programming_6_Stocks;

public class Best_Time_to_Buy_and_Sell_Stock{

    public static void main(String[] args) {

        int[] prices = {7, 1, 5, 3, 6, 4};

        int brute = bruteForce(prices);
        System.out.println("Maximum Profit (Brute Force): " + brute);

        int opt = optimized(prices);
        System.out.println("Maximum Profit (Optimized): " + opt);
    }

    private static int bruteForce(int[] prices) {
        int n = prices.length;
        int maxProfit = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (prices[j] > prices[i]) {
                    maxProfit = Math.max(maxProfit, prices[j] - prices[i]);
                }
            }
        }

        return maxProfit;
    }

    private static int optimized(int[] prices) {
    	int min = Integer.MAX_VALUE;
        int maxprofit = 0;
        for(int num : prices){
            if(num < min){
                min = num;
            }
            int profit = Math.abs(num - min);
            maxprofit = Math.max(profit,maxprofit);
        }
        System.gc();
        return maxprofit;
    }
}
