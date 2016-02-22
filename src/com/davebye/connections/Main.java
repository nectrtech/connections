/**
 * Finds connections from one place to many destinations...
 */
package com.davebye.connections;

import java.util.List;
import java.util.Scanner;

public class Main {
    // this will be loaded from the classpath to make deployment across environments (dev, qa, uat, prod) easier and less file-system dependent.
    private static final String INPUT = "stations.csv";

    public static void main(String[] args) {
        String from = null;
        String to = null;
        ConnectionPlanner planner = ConnectionPlanner.newConnectionPlanner(INPUT);
        Scanner consoleIn = new Scanner(System.in);

        System.out.println("(type 'quit' at some point to quit)");

        while (!(isInQuitState(from, to))) {
            System.out.print("enter start station>");
            from = consoleIn.nextLine();
            System.out.print("enter end station>");
            to = consoleIn.nextLine();
            System.out.println();

            List<Connection> availableConns = planner.findConnections(from, to);

            if (availableConns.size() > 0) {
                System.out.println("Available routes are:");
                availableConns.forEach(System.out::println);
            } else if (!(isInQuitState(from, to))) {
                System.out.println("No routes were found.");
            }
        }
    }

    private static boolean isInQuitState(String from, String to) {
        return "quit".equalsIgnoreCase(from) || "quit".equalsIgnoreCase(to);
    }
}
