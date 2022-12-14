package com.example.manualrpc.rpc.reference;

import java.util.concurrent.ConcurrentHashMap;

public class NettyRpcRequestTimeHolder {

    private static ConcurrentHashMap<String, Long> requestTimes =
            new ConcurrentHashMap<String, Long>();

    public static void put(String requestId, long requestTime) {
        requestTimes.put(requestId, requestTime);
    }

    public static long get(String requestId) {
        return requestTimes.get(requestId);
    }

    public static void remove(String requestId) {
        requestTimes.remove(requestId);
    }

}
