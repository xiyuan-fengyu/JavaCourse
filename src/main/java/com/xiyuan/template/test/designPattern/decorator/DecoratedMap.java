package com.xiyuan.template.test.designPattern.decorator;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Created by xiyuan_fengyu on 2018/8/2 16:29.
 */
public class DecoratedMap<K, V> implements Map<K, V> {

    protected Map<K, V> origin;

    public DecoratedMap(Map<K, V> origin) {
        this.origin = origin;
    }

    @Override
    public int size() {
        return origin.size();
    }

    @Override
    public boolean isEmpty() {
        return origin.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return origin.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return origin.containsValue(value);
    }

    @Override
    public V get(Object key) {
        return origin.get(key);
    }

    @Override
    public V put(K key, V value) {
        return origin.put(key, value);
    }

    @Override
    public V remove(Object key) {
        return origin.remove(key);
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        origin.putAll(m);
    }

    @Override
    public void clear() {
        origin.clear();
    }

    @Override
    public Set<K> keySet() {
        return origin.keySet();
    }

    @Override
    public Collection<V> values() {
        return origin.values();
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return origin.entrySet();
    }

    @Override
    public V getOrDefault(Object key, V defaultValue) {
        return origin.getOrDefault(key, defaultValue);
    }

    @Override
    public void forEach(BiConsumer<? super K, ? super V> action) {
        origin.forEach(action);
    }

    @Override
    public void replaceAll(BiFunction<? super K, ? super V, ? extends V> function) {
        origin.replaceAll(function);
    }

    @Override
    public V putIfAbsent(K key, V value) {
        return origin.putIfAbsent(key, value);
    }

    @Override
    public boolean remove(Object key, Object value) {
        return origin.remove(key, value);
    }

    @Override
    public boolean replace(K key, V oldValue, V newValue) {
        return origin.replace(key, oldValue, newValue);
    }

    @Override
    public V replace(K key, V value) {
        return origin.replace(key, value);
    }

    @Override
    public V computeIfAbsent(K key, Function<? super K, ? extends V> mappingFunction) {
        return origin.computeIfAbsent(key, mappingFunction);
    }

    @Override
    public V computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        return origin.computeIfPresent(key, remappingFunction);
    }

    @Override
    public V compute(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        return origin.compute(key, remappingFunction);
    }

    @Override
    public V merge(K key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction) {
        return origin.merge(key, value, remappingFunction);
    }

}
