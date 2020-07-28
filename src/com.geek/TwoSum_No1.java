package com.geek;

import java.util.HashMap;

/**
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 * <p>
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
 * 示例:
 * <p>
 * 给定 nums = [2, 7, 11, 15], target = 9
 * <p>
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 */
public class TwoSum_No1 {
    public int[] twoSum(int[] a, int target) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < a.length; i++) {
            if (hashMap.containsKey(target - a[i])) {
                return new int[]{hashMap.get(target - a[i]), i};
            }
            hashMap.put(a[i], i);
        }
        System.out.println("未找到对应的目标");
        return null;
    }

    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        TwoSum_No1 twoSumNo1 = new TwoSum_No1();
        int[] ints = twoSumNo1.twoSum(nums, 17);
        System.out.println("[" + ints[0] + "," + ints[1] + "]");
    }
}
