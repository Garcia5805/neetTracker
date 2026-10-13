class KthLargest {
    int K;
    ArrayList<Integer> list;

    public KthLargest(int k, int[] nums) {
        this.K = k;
        list = new ArrayList<>();
        for(int num : nums){
            list.add(num);
        }
    }
    
    public int add(int val) {
        list.add(val);
        list.sort(Comparator.naturalOrder());
        return list.get(list.size()-K);
    }

}
