class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
                List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);
        findCombinations(candidates, 0, 0, target, new ArrayList<Integer>(), result);
        return result;
    }
    
    public void findCombinations(int[] candidates, int index, int sum, int target, List<Integer> current, List<List<Integer>> result)
    {
        if (sum == target)
        {
            result.add(new ArrayList<>(current));
            return;
        }
        
        if (sum > target)
            return;
        
        for (int i = index; i < candidates.length; i++)
        {
                current.add(candidates[i]);
                findCombinations(candidates, i, sum  + candidates[i], target, current, result);
                current.remove(current.size() - 1);
        }
    }
}