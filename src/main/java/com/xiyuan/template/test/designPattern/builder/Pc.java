package com.xiyuan.template.test.designPattern.builder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by xiyuan_fengyu on 2018/7/26 16:59.
 */
public class Pc {

    private String cpu;

    private String memory;

    private Pc() {}

    @Override
    public String toString() {
        return super.toString() + " " +  new GsonBuilder()
                .setPrettyPrinting()
                .serializeNulls()
                .disableHtmlEscaping()
                .create().toJson(this);
    }

    public static class Builder {

        private String cpu;

        private String memory;

        public String getCpu() {
            return cpu;
        }

        public String getMemory() {
            return memory;
        }

        public Builder setCpu(String cpu) {
            Builder.this.cpu = cpu;
            return this;
        }

        public Builder setMemory(String memory) {
            Builder.this.memory = memory;
            return this;
        }

        public Pc build() {
            Pc pc = new Pc();
            pc.cpu = Builder.this.cpu;
            pc.memory = Builder.this.memory;
            return pc;
        }

    }

}
