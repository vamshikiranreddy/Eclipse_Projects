package Graphs_Problems;

public class MaxArea_Island {

    static int max = 0;
    static int currCount = 0;

    public static void main(String[] args) {
        int[][] grid = {
                {0, 0, 1, 0, 0},
                {1, 1, 1, 0, 1},
                {0, 1, 0, 0, 1},
                {1, 0, 0, 1, 1}
        };

        System.out.println(maxAreaOfIsland(grid));  
    }

    public static int maxAreaOfIsland(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    currCount = 0;
                    DFS(i, j, grid, visited);
                    max = Math.max(max, currCount);
                }
            }
        }
        return max;
    }

    private static void DFS(int i, int j, int[][] grid, boolean[][] visited) {
        int m = grid.length, n = grid[0].length;

        if (i < 0 || j < 0 || i >= m || j >= n) return;
        if (visited[i][j] || grid[i][j] == 0) return;

        visited[i][j] = true;
        currCount++;

        DFS(i - 1, j, grid, visited);
        DFS(i + 1, j, grid, visited);
        DFS(i, j - 1, grid, visited);
        DFS(i, j + 1, grid, visited);
    }
}
