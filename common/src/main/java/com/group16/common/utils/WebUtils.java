package com.group16.common.utils;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.Map;

@Slf4j
public class WebUtils {

    /**
     * Retrieves the current ServletRequestAttributes.
     *
     * @return ServletRequestAttributes or null if not available.
     */
    public static ServletRequestAttributes getServletRequestAttributes() {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        if (ra == null) {
            return null;
        }
        return (ServletRequestAttributes) ra;
    }

    /**
     * Retrieves the current HttpServletRequest.
     *
     * @return HttpServletRequest or null if not available.
     */
    public static HttpServletRequest getRequest() {
        ServletRequestAttributes servletRequestAttributes = getServletRequestAttributes();
        return servletRequestAttributes == null ? null : servletRequestAttributes.getRequest();
    }

    /**
     * Retrieves the current HttpServletResponse.
     *
     * @return HttpServletResponse or null if not available.
     */
    public static HttpServletResponse getResponse() {
        ServletRequestAttributes servletRequestAttributes = getServletRequestAttributes();
        return servletRequestAttributes == null ? null : servletRequestAttributes.getResponse();
    }

    /**
     * Retrieves the value of a specific request header.
     *
     * @param headerName The name of the header.
     * @return The value of the specified header or null if not available.
     */
    public static String getHeader(String headerName) {
        HttpServletRequest request = getRequest();
        if (request == null) {
            return null;
        }
        return request.getHeader(headerName);
    }

    // Sets a response header.
    public static void setResponseHeader(String key, String value){
        HttpServletResponse response = getResponse();
        if (response == null) {
            return;
        }
        response.setHeader(key, value);
    }

    // Checks if the response status code is in the success range.
    public static boolean isSuccess() {
        HttpServletResponse response = getResponse();
        return response != null && response.getStatus() < 300;
    }

    /**
     * Converts the request parameters to a string format: key1=value1&key2=value2.
     * If a key corresponds to multiple values, they are separated by commas.
     *
     * @param request The HttpServletRequest object.
     * @return A string representation of the parameters.
     */
    public static String getParameters(HttpServletRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        return getParameters(parameterMap);
    }

    /**
     * Converts a map of query parameters into a string format: key1=value1&key2=value2.
     * If a key corresponds to multiple values, they are separated by commas.
     *
     * @param queries A map containing the query parameters.
     * @return A string representation of the parameters.
     */
    public static <T> String getParameters(final Map<String, T> queries) {
        StringBuilder buffer = new StringBuilder();
        for (Map.Entry<String, T> entry : queries.entrySet()) {
            if (entry.getValue() instanceof String[]) {
                buffer.append(entry.getKey()).append("=").append(String.join(",", (String[]) entry.getValue()))
                        .append("&");
            } else if (entry.getValue() instanceof Collection) {
                buffer.append(entry.getKey()).append("=").append(
                        CollUtil.join(((Collection<String>) entry.getValue()), ",")
                ).append("&");
            }
        }
        return buffer.length() > 0 ? buffer.substring(0, buffer.length() - 1) : StrUtil.EMPTY;
    }

    /**
     * Extracts the URI from a URL string.
     *
     * @param url The URL string.
     * @return The URI part of the URL.
     */
    public static String getUri(String url) {
        if (StrUtil.isEmpty(url)) {
            return null;
        }

        String uri = url;
        // Remove http:// or https:// from the URI
        if (uri.contains("http://")) {
            uri = uri.substring("http://".length());
        } else if (uri.contains("https://")) {
            uri = uri.substring("https://".length());
        }

        // Cut the URI from the rest of the URL
        int endIndex = uri.indexOf("?") > -1 ? uri.indexOf("?") : uri.length();
        return uri.substring(uri.indexOf("/"), endIndex);
    }

    // Retrieves the remote address from the request.
    public static String getRemoteAddr() {
        HttpServletRequest request = getRequest();
        if (request == null) {
            return "";
        }
        return request.getRemoteAddr();
    }

    // Constructs a CookieBuilder instance for the current request and response.
    public static CookieBuilder cookieBuilder() {
        return new CookieBuilder(getRequest(), getResponse());
    }
}
