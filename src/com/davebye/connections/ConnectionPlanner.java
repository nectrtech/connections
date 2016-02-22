package com.davebye.connections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Created by dbye on 2016-02-19.
 */
public final class ConnectionPlanner {
    private List<Connection> connections = Collections.emptyList();

    private ConnectionPlanner(List<Connection> connections) {
        this.connections = Collections.unmodifiableList(connections);
    }

    public List<Connection> findConnections(String from, String to) {
        return connections.stream().filter(connection -> connection.getFrom().equalsIgnoreCase(from) && connection.getTo().equalsIgnoreCase(to)).collect(Collectors.toList());
    }

    public static ConnectionPlanner newConnectionPlanner(String dataName) throws IllegalStateException {
        List<Connection> originalConnections = new ArrayList<>();

        Scanner lineReader = new Scanner(Main.class.getClassLoader().getResourceAsStream(dataName));

        while (lineReader.hasNext()) {
            String[] path = lineReader.nextLine().split(",");
            originalConnections.add(Connection.newConnection(path[0], path[1], path[0] + "->" + path[1]));
            // uncomment the line below to add the reverse route too - it's implied and should work with the same recursive algorithm in buildConnections anyway
            //originalConnections.add(Connection.newConnection(path[1], path[0], path[1] + "->" + path[0]));
        }

        lineReader.close();
        List<Connection> derivedConnections = new ArrayList<>();
        originalConnections.forEach(connection -> derivedConnections.addAll(buildConnections(originalConnections, connection, connection.getFrom())));
        originalConnections.addAll(derivedConnections);

        return new ConnectionPlanner(originalConnections);
    }

    private static List<Connection> buildConnections(List<Connection> origins, Connection current, String excludes) {
        List<Connection> connections = new ArrayList<>();

        // find anything from the origins list that matches the next step - the from matches the to and the to
        // from the potential next step is not excluded
        for (Connection conn : origins) {
            if (current.getTo().equalsIgnoreCase(conn.getFrom()) && !(conn.getTo().matches(excludes))) {
                Connection newConn = Connection.newConnection(current.getFrom(), conn.getTo(), current.getPath() + "->" + conn.getTo());
                connections.add(newConn);
                connections.addAll(buildConnections(origins, newConn, excludes + "|" + conn.getFrom()));
            }
        }

        return connections;
    }
}
