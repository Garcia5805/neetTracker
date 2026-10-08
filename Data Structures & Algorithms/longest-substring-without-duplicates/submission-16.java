class Solution {
    public int lengthOfLongestSubstring(String s) {
        int l = 0;
        int ans = 0;
        HashSet<Character> chars = new HashSet<>();
        for(int r = 0; r < s.length(); r++){
            while(chars.contains(s.charAt(r))){
                chars.remove(s.charAt(l));
                l++;
            }
            chars.add(s.charAt(r));
            ans = Math.max(ans, (r-l)+1);
        }
        return ans;
    }
}
