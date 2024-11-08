package com.jonathan.services;

import rawhttp.core.RawHttp;
import rawhttp.core.RawHttpOptions;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Server {
    public static final int PORT = 3000;
    private volatile boolean shutdownServer = false;
    private final RequestRouterImpl router;
    private static Path currentDir = Path.of(System.getProperty("user.dir"));
    private static final Path PROPERTIES_FILE = Paths.get(currentDir.toString(), "BooksProjectServer-main", "server", "src", "main", "resources", "server.properties");

    public Server(RequestRouterImpl router) {
        this.router = router;
    }

    public void start() throws IOException {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(this::checkShutdown, 0, 5, TimeUnit.SECONDS);

        try (var serverSocket = new ServerSocket(PORT)) {
            ExecutorService executor = Executors.newFixedThreadPool(10);

            System.out.println("Server started on port " + PORT + ". Waiting for connections...");

            while (!shutdownServer) {
                var clientSocket = serverSocket.accept();
                executor.execute(() -> handleRequest(clientSocket));
            }

            executor.shutdown();
            scheduler.shutdown();
            System.out.println("Server is shutting down...");
        }
    }

    private void checkShutdown() {
        Properties properties = new Properties();

        try (FileInputStream input = new FileInputStream(String.valueOf(PROPERTIES_FILE))) {
            properties.load(input);
            String shutdownValue = properties.getProperty("shutdown", "false").trim().toLowerCase();

            if ("true".equals(shutdownValue)) {
                System.out.println("Shutdown signal detected. Preparing to terminate server...");
                shutdownServer = true;
            } else {
                System.out.println("Application running... No shutdown detected.");
            }
        } catch (IOException e) {
            System.err.println("Failed to read properties file: " + e.getMessage());
        }
    }

    private void handleRequest(java.net.Socket clientSocket) {
        try (clientSocket) {
            var rawHttp = new RawHttp(RawHttpOptions.newBuilder().doNotInsertHostHeaderIfMissing().build());
            var request = rawHttp.parseRequest(clientSocket.getInputStream());
            var response = router.execRequest(request);
            response.writeTo(clientSocket.getOutputStream());
        } catch (IOException e) {
            System.err.println("Error handling request: " + e.getMessage());
        }
    }
}
