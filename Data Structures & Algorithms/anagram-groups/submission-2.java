class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> count = new HashMap<>();

        for(String word : strs){

            char[] chars = word.toCharArray();
            Arrays.sort(chars);
            String sorted = new String(chars);

            count.computeIfAbsent(sorted,k -> new ArrayList<>()).add(word);
        }

        Set<String> keys = count.keySet();
        List<List<String>> ans = new ArrayList<>();
        
        for(String key : keys){
            ans.add(count.get(key));
        }
        
        return ans;
    }
}
