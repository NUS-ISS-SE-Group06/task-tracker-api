package com.nus.iss.tasktracker.util;

public class TaskTrackerConstant {

    public static final String JWT_SECRET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

    public static final int JWT_EXPIRATION_MINS = 10;

    public static final String JWT_ISSUER = "TASK_TRACKER";

    public static final String SIGNUP_INVALID_INPUT = "%s - Please input value!";

    public static final String SIGNUP_INVALID_INPUT_PASSWORD = "Invalid Password. Password must be at least 8 characters long\n" +
            " and contain a combination of letters, numbers, and special characters.";

    public static final String SIGNUP_INVALID_INPUT_USERNAME_UNAVAILABLE = "Username not available!";

    public static final String SIGNUP_INVALID_GROUP_NAME = "Invalid Group Name. Group Name must be at least 6 characters long\n" +
            " and contain a combination of letters, numbers, and space.";

    public static final String TASK_ADMIN = "ADMIN";

    public static final String DELETE_FLAG_TRUE = "TRUE";

    public static final String DELETE_FLAG_FALSE = "FALSE";

    public static final String TASK_PWD_CHANGE_MANDATORY_TRUE = "TRUE";

    public static final String TASK_PWD_CHANGE_MANDATORY_FALSE = "FALSE";

    public static final String REGISTRATION_ROLE_ADMIN = "ROLE_ADMIN";

    public static final String REGISTRATION_ROLE_USER = "ROLE_USER";

}
