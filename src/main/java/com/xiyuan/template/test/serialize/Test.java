package com.xiyuan.template.test.serialize;

import com.google.gson.Gson;
import com.google.protobuf.ByteString;
import com.xiyuan.template.test.serialize.protobuf.Protos;

import java.io.*;
import java.util.Arrays;

/**
 * Created by xiyuan_fengyu on 2018/7/26 17:31.
 */
public class Test {

    private static class A implements Serializable {

        private static final long serialVersionUID = 4977111192189722598L;

        public String name = "A";

        public B b;

    }

    private static class B implements Serializable {

        private static final long serialVersionUID = -1802929870104850418L;

        public String name = "B";

        public A a;

    }

    private static class C implements Serializable {

        private static final long serialVersionUID = 9212474990003937616L;

        public String name = "C";

        public A a = new A();

        public B b = new B();

        public C() {
            a.b = b;
            b.a = a;
        }

    }

    private static class D {

        public String name = "D";

    }

    private static class E implements Serializable {

        private static final long serialVersionUID = 5716339428372681955L;

        public String name = "E";

        public D d = new D();

    }

    private static class F implements Serializable {

        private static final long serialVersionUID = -783363014488545403L;

        public String name = "F";

        public transient D d = new D();

    }

    @SuppressWarnings("unchecked")
    private static <T extends Serializable> T deepClone(T from) throws Exception {
        try (ByteArrayOutputStream bytesOut = new ByteArrayOutputStream();
             ObjectOutputStream objOut = new ObjectOutputStream(bytesOut)) {
            objOut.writeObject(from);
            try (ByteArrayInputStream bytesIn = new ByteArrayInputStream(bytesOut.toByteArray(), 0, bytesOut.size());
                 ObjectInputStream objIn = new ObjectInputStream(bytesIn)) {
                return (T) objIn.readObject();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        A a = new A();
        a.b = new B();

        Gson gson = new Gson();

        String aStr = gson.toJson(a);
        A aFromStr = gson.fromJson(aStr, A.class);
        System.out.println(aFromStr);

        C c = new C();
//        String cStr = gson.toJson(c); // 循环引用，导致栈溢出异常，无法被catch
        C cClone = deepClone(c);
        System.out.println(cClone);

        try {
            E e = new E();
            E eClone = deepClone(e);
            System.out.println(eClone);
        }
        catch (Exception e) {
            System.err.println(e); // D 未实现 Serializable, 报错
        }

        F f = new F();
        F fClone = deepClone(f);
        System.out.println(fClone); // d 有 transient 标示，不参与序列化，所以不报错，但同时其信息也丢失了


        // protobuf
        Protos.User user = Protos.User.newBuilder()
                .setId(1L)
                .setName("Test")
                .setAge(25)
                .setSex(0)
                .build();
        System.out.println(user);
        String testUtf8Str = user.toByteString().toStringUtf8();
        System.out.println(testUtf8Str);

        byte[] userBytes = user.toByteArray();
        System.out.println(Arrays.toString(userBytes));

        Protos.User user1 = Protos.User.parseFrom(ByteString.copyFromUtf8(testUtf8Str));
        System.out.println(user1);

        Protos.User user2 = Protos.User.parseFrom(userBytes);
        System.out.println(user2);
    }

}
