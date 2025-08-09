package com.org;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ApiRateLimiter {
    private static final int RATE_LIMIT_COUNT = 5;
    private static final long RATE_LIMIT_WINDOW_MS = 5000;
    private final Map<Integer, UserState> userStates;

    public ApiRateLimiter() {
        this.userStates = new ConcurrentHashMap<>();
    }

    public static void main(String[] args) {
        ApiRateLimiter limiter = new ApiRateLimiter();

        // Input data as provided in the problem description
        // Each element is an array: {user_id, timestamp_ms}
        int[][] inputRequests = {{1, 0}, {1, 800}, {1, 1200}, {1, 2000}, {1, 2500}, {1, 3000}, {1, 4000}, {1, 6000}, {1, 7000}, {1, 10000}, {2, 7000}, {2, 12000}};

        // Process each request and print the result
        for (int[] request : inputRequests) {
            int userId = request[0];
            long timestamp = request[1];
            String status = limiter.checkRequest(userId, timestamp);
            System.out.println(status);
        }
    }

    public String checkRequest(int userId, long timestamp) {
        UserState userState = userStates.computeIfAbsent(userId, k -> new UserState());

        synchronized (userState) {
            long currentWindowStart = userState.getWindowStartTime();
            int currentCallCount = userState.getCallCount();

            long windowEndTime = currentWindowStart + RATE_LIMIT_WINDOW_MS;

            boolean isAllowed = false;

            if (currentWindowStart == -1 || timestamp >= windowEndTime) {
                isAllowed = true;
                userState.setWindowStartTime(timestamp);
                userState.setCallCount(1);
            } else if (timestamp < windowEndTime && currentCallCount < RATE_LIMIT_COUNT) {

                isAllowed = true;
                userState.incrementCallCount();
            }

            return "User " + userId + " request at " + timestamp + "ms -> " + (isAllowed ? "Allowed" : "Not Allowed");
        }
    }

    private static class UserState {
        long windowStartTime;
        int callCount;

        public UserState() {
            this.windowStartTime = -1;
            this.callCount = 0;
        }

        public long getWindowStartTime() {
            return windowStartTime;
        }

        public void setWindowStartTime(long windowStartTime) {
            this.windowStartTime = windowStartTime;
        }

        public int getCallCount() {
            return callCount;
        }

        public void setCallCount(int callCount) {
            this.callCount = callCount;
        }

        public void incrementCallCount() {
            this.callCount++;
        }
    }
}
