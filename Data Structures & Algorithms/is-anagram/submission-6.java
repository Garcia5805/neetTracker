class Solution {
    public boolean isAnagram(String s, String t) {
        if(s.length() != t.length()){return false;}

        HashMap<Character, Integer> s1 = new HashMap<>();
        HashMap<Character, Integer> t1 = new HashMap<>();
    
        for(char word : s.toCharArray()){
            s1.put(word, s1.getOrDefault(word,0)+1);
        }
       for(char word : t.toCharArray()){
            t1.put(word, t1.getOrDefault(word,0)+1);
        }
        if(!s1.equals(t1)){return false;}


        return true;
    }

}
