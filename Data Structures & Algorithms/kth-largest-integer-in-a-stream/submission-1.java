class KthLargest {
    PriorityQueue<Integer> ans;
    int K;
    public KthLargest(int k, int[] nums) {
        ans = new PriorityQueue<>();
        K = k;
        for(int num : nums){
            ans.offer(num);
            if(ans.size() > K){
                ans.poll();
            }
        }
    }
    
    public int add(int val) {
        ans.offer(val);
        if(ans.size() > K){
            ans.poll();
        }
        return ans.peek();

    }
}
