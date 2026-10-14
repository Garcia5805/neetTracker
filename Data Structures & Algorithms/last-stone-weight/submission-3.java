class Solution {
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> stone = new PriorityQueue<>(Collections.reverseOrder());
        for(int s : stones){
            stone.offer(s);
        }

        while(stone.size() > 1){
            int s1 = stone.poll();
            int s2 = stone.poll();
            
            if(s1 < s2){
                stone.offer(s2 - s1);
            }
            else if (s1 > s2){
                stone.offer(s1 - s2);
            }

        }
        if(stone.size() < 1) return 0;
        return stone.poll();
    }
}
