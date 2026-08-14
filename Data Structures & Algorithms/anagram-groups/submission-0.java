class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String,List<String>> count = new HashMap<>();
        for(String word : strs){
            char[] chars = word.toCharArray();
            Arrays.sort(chars);
            String sorted = new String(chars);
            
            count.putIfAbsent(sorted, new ArrayList<>());
            count.get(sorted).add(word);
        }
        return new ArrayList<>(count.values());
    }
}
