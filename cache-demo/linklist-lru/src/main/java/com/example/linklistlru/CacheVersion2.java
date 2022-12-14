package com.example.linklistlru;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * 基于linkedList实现LRU驱逐算法
 */
public class CacheVersion2 implements Cache {
    //缓存容量
    private int capacity;
    //用来维护缓存key的顺序
    private LinkedList<Object> keyList;
    //通过组合关系持有一个内部的真正缓存对象
    private Map<Object,Object> internalCache;

    public CacheVersion2(int capacity) {
        this.capacity = capacity;
        internalCache = new HashMap<>();
        keyList = new LinkedList<>();
    }


    @Override
    public void put(Object key, Object value) {
        //调用put此方式时，已存在的元素个数==容量
        if(size() == capacity){
            //移除第一个(get()中设置，最后访问的在最后)
            Object firstKey = keyList.removeFirst();
            internalCache.remove(firstKey);
        }
        //加入当前key
        keyList.addLast(key);
        internalCache.put(key,value);
    }

    @Override
    public void remove(Object key, Object value) {
        keyList.remove(key);
        internalCache.remove(key,value);
    }

    @Override
    public void clear() {
        keyList.clear();
        internalCache.clear();
    }

    @Override
    public Object get(Object key) {
        //true：key存在，false：key在keyList中不存在
        boolean removeResult = keyList.remove(key);
        if(removeResult){
            Object value = internalCache.get(key);
            //把现在访问的key排序，最后访问的放在最后
            keyList.addLast(key);
            return value;
        }
        return null;
    }

    @Override
    public int size() {
        //sinternalCache.get(key)
        return keyList.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Object key : keyList) {
            sb.append("key:").append(key).append(",")
                    .append("value:").append(internalCache.get(key))
                    .append("-");
        }
        return sb.substring(0,sb.length()-1);
    }
}
