package com.example.logging;

public class LoggingField {

    /**
     * Prevent instantiation of this utility class.
     */
    private LoggingField() {
    }

    /**
     * The action performed by the client.
     */
    public static final String ACTION = "action";

    /**
     * The application name.
     */
    public static final String APP = "app";

    /**
     * The number of bytes sent by the server in the response.
     */
    public static final String BYTES = "bytes";

    /**
     * The cache status of the requested resource.
     */
    public static final String CACHED = "cached";

    /**
     * The cookie sent by the client.
     */
    public static final String COOKIE = "cookie";

    /**
     * The destination of the network traffic (the server receiving the connection).
     */
    public static final String DEST = "dest";

    /**
     * The destination port of the network traffic.
     */
    public static final String DEST_PORT = "dest_port";

    /**
     * The content-type of the requested HTTP resource.
     */
    public static final String HTTP_CONTENT_TYPE = "http_content_type";

    /**
     * The HTTP method used in the request.
     */
    public static final String HTTP_METHOD = "http_method";

    /**
     * The HTTP referrer used in the request. This is the URL of the previous web
     * page from which a link to the currently requested page was followed.
     */
    public static final String HTTP_REFERRER = "http_referrer";

    /**
     * The user agent of the client making the request.
     */
    public static final String HTTP_USER_AGENT = "http_user_agent";

    /**
     * The source of the network traffic (the client requesting the connection).
     */
    public static final String SRC = "src";

    /**
     * The HTTP response code indicating the status of the request.
     */
    public static final String STATUS = "status";

    /**
     * The path of the resource served by the webserver.
     */
    public static final String URI_PATH = "uri_path";

    /**
     * The path of the resource requested by the client.
     */
    public static final String URI_QUERY = "uri_query";

    /**
     * The URL of the resource requested by the client.
     */
    public static final String URL = "url";

    /**
     * The username of the authenticated user.
     */
    public static final String USER = "user";

}
