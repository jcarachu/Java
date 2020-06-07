import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Amazon Echo has lots of competitors
 * Web crawler got list of reviews
 *
 * Given list of reviews, list of competitors, N, return most frequently mentioned top N competitors in the reviews.
 *
 * Clarifying questions:
 * 1) why are numCompetitors and numReviews inputs? we don't seem to use them for any useful comparison
 * 2) should a repeated mention in the same review count as one, or multiple mentions?
 * 3) are the Strings for each review we are expecting to be long, or fairly standard short sentences left for reviews?
 * 4) can we get a lower and upper bounds for inputs such as reviews and competitors?
 * 5) is case-sensitivity needed? (can two companies exist with the same name but different case?)
 * 6) what if a review positively mentions one competitor, and negatively mentions another? which one do we count?
 * do we need to add detections for this?
 */
class Solution {

    public List<String> getTopNCompetitors(int numCompetitors, int topNCompetitors, List<String> competitors, int numReviews, List<String> reviews)
    {
        Map<String, Integer> competitorsMap = new HashMap<>();
        List<String> result = new ArrayList<>();
        for (String s: reviews)
        {
            String[] words = s.split(" ");
            for (String word: words)
            {
                if (competitors.contains(word.toLowerCase()))
                {
                    competitorsMap.put(word, competitorsMap.getOrDefault(word, 0) + 1);
                }
            }
        }

        List<Competitor> competitorList = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : competitorsMap.entrySet())
        {
            competitorList.add(new Competitor(entry.getKey(), entry.getValue()));
        }

        competitorList.sort((a, b) -> {
            if (a.occurrances.equals(b.occurrances))
                return b.name.compareTo(a.name);
            else
                return b.occurrances - a.occurrances;
        });

        for (int i = 0; i < topNCompetitors; i++)
        {
            result.add(competitorList.get(i).name);
        }

        return result;
    }

    public class Competitor {
        Competitor(String name, Integer occurrences)
        {
            this.name = name;
            this.occurrences = occurrences;
        }
    
        String name;
        Integer occurrances;
    
    }
}

