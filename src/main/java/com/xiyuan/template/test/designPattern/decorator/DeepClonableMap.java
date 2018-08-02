package com.xiyuan.template.test.designPattern.decorator;

import java.io.*;
import java.util.Map;

/**
 * Created by xiyuan_fengyu on 2018/8/2 16:16.
 */
public class DeepClonableMap<K, V> extends DecoratedMap<K, V> {

    private static final long serialVersionUID = -986228498431273882L;

    public DeepClonableMap(Map<K, V> origin) {
        super(origin);
    }

    @SuppressWarnings("unchecked")
    public Map<K, V> deepClone() throws Exception {
        try (ByteArrayOutputStream bytesOut = new ByteArrayOutputStream();
             ObjectOutputStream objOut = new ObjectOutputStream(bytesOut)) {
            objOut.writeObject(origin);
            try (ByteArrayInputStream bytesIn = new ByteArrayInputStream(bytesOut.toByteArray(), 0, bytesOut.size());
                 ObjectInputStream objIn = new ObjectInputStream(bytesIn)) {
                return (Map<K, V>) objIn.readObject();
            }
        }
    }

}
