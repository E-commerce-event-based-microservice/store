package com.group16.common.utils;

import cn.hutool.core.util.StrUtil;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

@Slf4j
@Data
@Accessors(chain = true, fluent = true)
public class CookieBuilder {
    private Charset charset = StandardCharsets.UTF_8; // Charset for encoding and decoding the cookie values.
    private int maxAge = -1; // Maximum age of the cookie in seconds.
    private String path = "/"; // Path for which the cookie is valid.
    private boolean httpOnly; // If true, makes the cookie HTTP only.
    private String name; // Name of the cookie.
    private String value; // Value of the cookie.
    private String domain; // Domain for which the cookie is valid.
    private final HttpServletRequest request; // HTTP request object.
    private final HttpServletResponse response; // HTTP response object.

    public CookieBuilder(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    /**
     * Builds and adds a cookie to the response. The cookie value is URL encoded using UTF-8 to avoid issues with special characters.
     */
    public void build() {
        if (response == null) {
            log.error("Response is null, cannot write cookie");
            return;
        }
        Cookie cookie = new Cookie(name, URLEncoder.encode(value, charset)); // Encode the value using specified charset.
        if (StrUtil.isNotBlank(domain)) {
            cookie.setDomain(domain); // Set cookie domain if specified.
        } else if (request != null) {
            // Automatically determine the domain from request if not specified.
            String serverName = request.getServerName();
            serverName = StrUtil.subAfter(serverName, ".", false);
            cookie.setDomain("." + serverName);
        }
        cookie.setHttpOnly(httpOnly); // Set HTTP only flag.
        cookie.setMaxAge(maxAge); // Set cookie max age.
        cookie.setPath(path); // Set cookie path.
        log.debug("Creating cookie, charset: {}, [{}={} ,domain:{}; maxAge={}; path={}; httpOnly={}]",
                charset.name(), name, value, domain, maxAge, path, httpOnly);
        response.addCookie(cookie); // Add the constructed cookie to the response.
    }

    /**
     * Decodes a cookie value using UTF-8 to handle special characters properly.
     *
     * @param cookieValue The original cookie value.
     * @return The decoded value of the cookie.
     */
    public String decode(String cookieValue) {
        return URLDecoder.decode(cookieValue, charset); // Decode the cookie value using specified charset.
    }
}
