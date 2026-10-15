class Solution {
    public int[][] kClosest(int[][] points, int k) {
        PriorityQueue<int[]> point = new PriorityQueue<>(Comparator.comparing(a -> a[0]));

        for(int[] cords : points){

            int dist = (cords[0]*cords[0]) + (cords[1]*cords[1]);
            point.offer(new int[]{dist, cords[0],cords[1]});
        }

        int[][] ans = new int[k][2];

        for(int i = 0; i < k; i++){
            int[] p = point.poll();
            ans[i] = new int[] {p[1],p[2]};
        }


        return ans;
    }
}
