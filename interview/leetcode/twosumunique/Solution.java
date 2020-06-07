import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.Map;
import java.util.Arrays;

class Solution {
    public static void main(String[] args) {
        int[] nums1 = {1,1,2,45,46,46};
        int target1 = 47;
        System.out.println(getUniquePairs(nums1, target1));
        System.out.println("-------------------------------");
        int[] nums2 = {1,1};
        int target2 = 2;
        System.out.println(getUniquePairs(nums2, target2));
    }

    private static int getUniquePairs(int[] nums, int target)
    {
        Set<List<Integer>> set = new HashSet<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++)
        {
            if (map.containsKey(nums[i]))
            {
                int p = map.get(nums[i]);
                set.add(Arrays.asList(nums[p] < nums[i] ? nums[p] : nums[i], nums[p] < nums[i] ? nums[i] : nums[p]));
            }
            // Difference, position
            map.put(target - nums[i], i);
        }

        return set.size();
    }
}