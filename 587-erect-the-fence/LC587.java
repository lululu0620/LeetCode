public class LC587 {
    private int[][] points;
    public int[][] outerTrees(int[][] points) {
        this.points = points;
        int start = 0, len = points.length;
        for (int i = 0; i < len; i++) {
            if (points[i][0] < points[start][0]) start = i;
        }
        Set<int[]> hull = new HashSet<>();
        if (len < 4) {
            for (int[] point: points) hull.add(point);
            return hull.toArray(new int[hull.size()][]);
        }

        int p = start;
        do {
            int q = (p + 1) % len;
            for (int i = 0; i < len; i++) {
                // 向量pq 逆时针旋转 得到 向量pi
                if (crossProduct(getVector(p, q), getVector(p, i)) > 0) q = i;
            }
            // 判断是否存在三点共线的情况
            for (int i = 0; i < len; i++) {
                // 如果三点或多点共线，需要把这条线上的点都加上
                if (i != p && i != q && crossProduct(getVector(p, q), getVector(p, i)) == 0 && isBetween(p, i, q)) hull.add(points[i]);
            }
            hull.add(points[q]);
            p = q;
        }
        while (p != start);
        return hull.toArray(new int[hull.size()][]);
    }

    // 向量 pq: 起点为p 终点为q
    private int[] getVector(int p, int q) {
        return new int[]{points[q][0] - points[p][0], points[q][1] - points[p][1]};
    }

    // 向量a 和 向量b 的叉积
    // > 0 => 逆时针旋转 
    // < 0 => 顺时针旋转 
    private int crossProduct(int[] a, int[] b) {
        return a[0] * b[1] - a[1] * b[0];
    }
    
    private boolean isBetween(int p, int s, int q) {
        boolean a = (points[p][0] <= points[s][0] && points[s][0] <= points[q][0]) || (points[q][0] <= points[s][0] && points[s][0] <= points[p][0]);
        boolean b = (points[p][1] <= points[s][1] && points[s][1] <= points[q][1]) || (points[q][1] <= points[s][1] && points[s][1] <= points[p][1]);
        return a && b;
    }
}