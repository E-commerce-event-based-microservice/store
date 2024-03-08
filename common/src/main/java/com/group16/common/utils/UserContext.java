package com.group16.common.utils;

public class UserContext {
    private static final ThreadLocal<Long> tl = new ThreadLocal<>(); // ThreadLocal to hold the user ID for each thread separately.

    /**
     * Saves the current logged-in user's ID into ThreadLocal.
     * This allows each thread to have its own copy of the user's ID,
     * isolated from other threads.
     *
     * @param userId The user ID of the logged-in user.
     */
    public static void setUser(Long userId) {
        tl.set(userId);
    }

    /**
     * Retrieves the current logged-in user's ID from ThreadLocal.
     * Each thread accesses its own copy of the user's ID.
     *
     * @return The user ID of the current logged-in user.
     */
    public static Long getUser() {
        return tl.get();
    }

    /**
     * Removes the current logged-in user's ID from ThreadLocal.
     * This should be called to prevent memory leaks, especially
     * when the execution of the thread is completed.
     */
    public static void removeUser() {
        tl.remove();
    }
}
