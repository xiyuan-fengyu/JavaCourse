package com.xiyuan.template.test.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Created by xiyuan_fengyu on 2018/7/26 15:08.
 */
public class IOTest {

    private static void intBytesTest() {
        try (ByteArrayOutputStream bytesOut = new ByteArrayOutputStream();
             DataOutputStream out = new DataOutputStream(bytesOut)) {
            for (int i = 0; i < 1024; i++) {
                out.writeInt(i);
                byte[] intBytes = Arrays.copyOfRange(bytesOut.toByteArray(), i * 4, out.size());
                System.out.println(i + " " + Arrays.toString(intBytes));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void intStringBytesCompare(int num) {
        try (ByteArrayOutputStream bytesOut = new ByteArrayOutputStream();
             DataOutputStream out = new DataOutputStream(bytesOut)) {
            out.writeInt(num);
            byte[] intBytes = Arrays.copyOfRange(bytesOut.toByteArray(), 0, out.size());
            System.out.println("int(" + num + ") " + Arrays.toString(intBytes));
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("string(\"" + num + "\") " + Arrays.toString(("" + num).getBytes(StandardCharsets.UTF_8)) + "\n");
    }

    private static void fileIO(String filePath) {
        try (InputStream in = new FileInputStream(filePath)) {
            byte[] bytes = new byte[in.available()];
            if (in.read(bytes) != -1) {
                String content = new String(bytes, StandardCharsets.UTF_8);
                System.out.println(content);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        try (OutputStream out = new FileOutputStream(filePath, true)) {
            out.write((System.currentTimeMillis() + "\n").getBytes(StandardCharsets.UTF_8));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void resourceInput(String resource) {
        try (InputStream in = IOTest.class.getClassLoader().getResourceAsStream(resource)) {
            byte[] bytes = new byte[in.available()];
            if (in.read(bytes) != -1) {
                String content = new String(bytes, StandardCharsets.UTF_8);
                System.out.println(content);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void readLine1() {
        Scanner scanner = new Scanner(System.in);
        String line;
        while ((line = scanner.nextLine()) != null && !line.equals("quit")) {
            System.out.println(line);
        }
    }

    private static void readLine2() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null && !line.equals("quit")) {
                System.out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static class A implements Serializable {

        private static final long serialVersionUID = 3028497735779343428L;

        public String name = "A";

        public List<String> data = Arrays.asList("a", "bb", "ccc");

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


    public static void pipedTest() throws IOException {
        PipedInputStream pipedInputStream = new PipedInputStream();
        PipedOutputStream pipedOutputStream = new PipedOutputStream();
        pipedOutputStream.connect(pipedInputStream);

        new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            String line;
            try {
                while ((line = scanner.nextLine()) != null && !line.equals("quit")) {
                    pipedOutputStream.write((line + "\n").getBytes(StandardCharsets.UTF_8));
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            Scanner scanner = new Scanner(pipedInputStream);
            String line;
            try {
                while ((line = scanner.nextLine()) != null) {
                    System.out.println(line);
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static void main(String[] args) throws Exception {
//        intBytesTest();

//        intStringBytesCompare(1);
//        intStringBytesCompare(1024);

//        fileIO("src/main/resources/ioTest.txt");

//        resourceInput("ioTest.txt");

//        readLine1();
//        readLine2();

//        A a = new A();
//        A aClone = deepClone(a);
//        System.out.println(a);
//        System.out.println(aClone);

//        pipedTest();


    }

}
