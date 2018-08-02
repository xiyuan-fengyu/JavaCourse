package com.xiyuan.template.test.designPattern.decorator;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Map;

/**
 * Created by xiyuan_fengyu on 2018/8/2 16:00.
 */
public class PrettyPrintMap<K, V> extends DecoratedMap<K, V> {

    private static final Gson prettyGson = new GsonBuilder()
            .setPrettyPrinting()
            .serializeNulls()
            .disableHtmlEscaping()
            .setDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
            .create();

    public PrettyPrintMap(Map<K, V> origin) {
        super(origin);
    }

    public String pretty() {
        if (origin == null) {
            return null;
        }
        return prettyGson.toJson(origin);
    }

}
