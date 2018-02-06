package com.xiyuan.template.test.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

/**
 * 具体解析参考：https://segmentfault.com/a/1190000004171551
 * 结论：
 * 由于 JIT 在编译过程中对for循环的优化，遍历的过程本身比stream有优势
 * 当每次循环中的操作的计算复杂度很小时，for循环有很大优势
 * for循环所有情况下都比串行stream快
 * 当每次循环中的操作的计算复杂度比较大时，并行stream更快
 * Created by xiyuan_fengyu on 2018/2/6 9:14.
 */
public class ForStreamParalleCompareTest {

    public static void main(String[] args) {
        test1();
        test2();
    }

    private static void test1() {
        int begin = 1;
        int end = 100000;

        long[] nums1 = LongStream.rangeClosed(begin, end).toArray();

        long sum = 0;
        long now;
        long []costs = new long[6];

        for (int j = 0; j < 100; ++j) {
            now = System.nanoTime();
            for (long i : nums1) {
                sum += i;
            }
            costs[0] += System.nanoTime() - now;

            now = System.nanoTime();
            sum = Arrays.stream(nums1).sum();
            costs[1] += System.nanoTime() - now;

            now = System.nanoTime();
            sum = Arrays.stream(nums1).parallel().sum();
            costs[2] += System.nanoTime() - now;

            List<Long> nums2 = LongStream.rangeClosed(begin, end).boxed().collect(Collectors.toList());
            sum = 0;
            now = System.nanoTime();
            for (Long i : nums2) {
                sum += i;
            }
            costs[3] += System.nanoTime() - now;

            now = System.nanoTime();
            sum = nums2.stream().reduce(0L, (a, b) -> a + b);
            costs[4] += System.nanoTime() - now;

            now = System.nanoTime();
            sum = nums2.stream().parallel().reduce(0L, (a, b) -> a + b);
            costs[5] += System.nanoTime() - now;
        }

        for (int i = 0; i < 6; i++) {
            System.out.println(costs[i] / 100);
        }
        System.out.println();
    }

    private static void test2() {
        int begin = 1;
        int end = 100000;

        long[] nums1 = LongStream.rangeClosed(begin, end).toArray();

        double sum = 0;
        long now;
        long []costs = new long[3];

        for (int j = 0; j < 100; ++j) {
            now = System.nanoTime();
            for (long aNums1 : nums1) {
                sum += Math.sin(aNums1);
            }
            costs[0] += System.nanoTime() - now;

            now = System.nanoTime();
            sum = Arrays.stream(nums1).mapToDouble(Math::sin).sum();
            costs[1] += System.nanoTime() - now;

            now = System.nanoTime();
            sum = Arrays.stream(nums1).parallel().mapToDouble(Math::sin).sum();
            costs[2] += System.nanoTime() - now;
        }

        for (int i = 0; i < 3; i++) {
            System.out.println(costs[i] / 100);
        }
        System.out.println();
    }

}