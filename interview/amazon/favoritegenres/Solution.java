import java.util.Map;

class Solution {
    public Map<String, List<String>> favoritegenere(Map<String, List<String>> userMap, Map<String, List<String>> genreMap)
    {
        Map<String, List<String>> res = new HashMap<>();
        Map<String, String> songstostore = new HashMap<>();
        Map<String, Integer> count;
        int max;

        for (String genre : genreMap.keySet())
        {
            List<String> songs = genreMap.get(genre);
            for (String song : songs)
            {
                songstogenre.put(song, genre);
            }
        }

        for (String user : userMap.keySet)
        {
            List<String> songs = userMap.get(user);
            res.put(user, new ArrayList ());
            count = new HashMap();
            max = 0;
            
            for (String song: songs)
            {
                String genre = songstostore.get(song);
                int c = count.getOrDefault(genre, 0) + 1;
                count.put(genre, c);
                max = Math.max(c, max);
            }

            for (String key : count.keySet())
            {
                if (count.get(key) == max)
                {
                    if (count.get(key) == max)
                        res.get(user).add(key);
                }
            }
        }

        return res;
    }
    public static void main(String[] args) {
        
    }
}