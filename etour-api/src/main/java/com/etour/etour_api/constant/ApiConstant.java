package com.etour.etour_api.constant;

/**
 * @version 1.0
 * @project etour-api
 * @since 28-01-2025
 */

public class ApiConstant {
    public static final String ROLE = "role";
    public static final String EMPTY_VALUE = "empty";
    public static final String TYPE = "typ";
    public static final String JWT_TYPE = "JWT";
    public static final String E_TOUR_LLC = "E_TOUR_LLC";

    public static final String LOGIN_PATH = "/user/login";

    public static final int STRENGTH = 12;

    public static final String[] PUBLIC_ROUTES = {
            "/user/register", "/user/verify/account", "/user/login", "/user/resetpassword", "/user/verify/password",
            "/user/resetpassword/reset", "/user/image", "/user/logout"
    };

    public static final String[] PUBLIC_URLS = {
            "/user/register/**", "/user/verify/account/**", "/user/login/**", "/user/resetpassword/**",
            "/user/verify/password/**", "/user/resetpassword/reset/**", "/user/image/**", "/user/logout/**"
    };

    public static final String BASE_PATH = "/**";
    public static final String FILE_NAME = "File-Name";

    public static final String USER_IMAGE_FILE_STORAGE = System.getProperty("user.home") + "/Pictures/Etour/UserImages/";
}
