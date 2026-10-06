class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> mp = new HashMap<>();
        List<List<String>> ans = new ArrayList<>();

        for(String word : strs){
            //sort word
            char[] chars = word.toCharArray();
            Arrays.sort(chars);
            String sorted = new String(chars);
            //if not add sorted word with new list
            if(!(mp.containsKey(sorted))){
                mp.put(sorted, new ArrayList<>());
            }

            //if sorted word is present add word to list
            mp.get(sorted).add(word);

        }
        //get list of keys
        Set<String> k = mp.keySet();
        //use keys to get values and add to solution list
        for(String key : k){
            ans.add(mp.get(key));
        }
        return ans;
    }
}
