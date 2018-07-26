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

    public interface IBuilder {

        String getCpu();

        String getMemory();

        IBuilder setCpu(String cpu);

        IBuilder setMemory(String memory);

        default Pc build() {
            Pc pc = new Pc();
            pc.cpu = getCpu();
            pc.memory = getMemory();
            return pc;
        }

    }

    public static class Builder implements IBuilder {

        private String cpu;

        private String memory;

        @Override
        public String getCpu() {
            return Builder.this.cpu;
        }

        @Override
        public String getMemory() {
            return Builder.this.memory;
        }

        @Override
        public IBuilder setCpu(String cpu) {
            Builder.this.cpu = cpu;
            return this;
        }

        @Override
        public IBuilder setMemory(String memory) {
            Builder.this.memory = memory;
            return this;
        }
    }

}
