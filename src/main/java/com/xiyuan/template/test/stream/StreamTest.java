package com.xiyuan.template.test.stream;

import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by xiyuan_fengyu on 2018/2/6 10:42.
 */
public class StreamTest {

    public static void main(String[] args) {
        //将 1~10 中的偶数拼接为字符串，通过", "来分割
        int start = 1;
        int end = 100;
        {
            //常规方式
            StringBuilder builder = new StringBuilder();
            for (int i = start; i <= end; i++) {
                if (i % 2 == 0) {
                    builder.append(i);
                    if (i != end) builder.append(", ");
                }
            }
            System.out.println(builder.toString());
        }

        {
            //stream
            //串行
            System.out.println(IntStream.rangeClosed(start, end)
                    .filter(i -> i % 2 == 0)
                    .mapToObj(Objects::toString)
                    .collect(Collectors.joining(", ")));

            //并行
            System.out.println(IntStream.rangeClosed(start, end).parallel()
                    .filter(i -> i % 2 == 0)
                    .mapToObj(Objects::toString)
                    .collect(Collectors.joining(", ")));
        }
    }

}