class Solution {
    public int numSubmat(int[][] mat) {
        int row = mat.length, col = mat[0].length, res = 0;
        for (int j = 0; j < col; j++) {
            colOnes = 0;
            for (int i = 0; i < row; i++) {
                if (mat[i][j] == 0) colOnes = 0;
                else colOnes++;
            }
            mat[i][j] = colOnes;
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int k = j, min = Integer.MAX_VALUE;
                while (k >= 0 && mat[i][k] > 0) {
                    min = Math.min(min, mat[i][k]);
                    res += min;
                }
            }
        }
        return res;
    }
}