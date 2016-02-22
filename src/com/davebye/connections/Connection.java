package com.davebye.connections;

/**
 * Used for encapsulating a connection with from, to and path information as an immutable entity.
 *
 * Created by dbye on 2016-02-19.
 */
public class Connection {
private String from = null;
    private String to = null;
    private String path = null;

    private Connection(String from, String to, String path) {
        this.from = from;
        this.to = to;
        this.path = path;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getPath() {
        return path;
    }

    public String toString() {
        return this.path;
    }

    public static Connection newConnection(String from, String to, String path) {
        return new Connection(from, to, path);
    }
}
