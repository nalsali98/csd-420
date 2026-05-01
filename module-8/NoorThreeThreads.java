/**
 * Program:     ThreeThreads.java
 * Course:      CSD420-340A Advanced Java Programming (2265-DD)
 * Module:      8.2 Programming Assignment
 * Author:      NOOR AL SALIHI
 * Date:        April 30, 2026
 * Description: A JavaFX application that uses three concurrent threads to generate
 *              and display random letters, digits, and special characters interleaved
 *              in a TextArea. Each thread produces a minimum of 10,000 characters.
 */

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ThreeThreads - JavaFX application demonstrating concurrent thread output.
 * Three threads simultaneously generate characters that are interleaved in a TextArea.
 */
public class NoorThreeThreads extends Application {

    // -----------------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------------
    /** Minimum characters each thread must produce. */
    private static final int MIN_CHARS = 10_000;

    /** Alphabet pool for Thread 1. */
    private static final char[] LETTERS =
            "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    /** Digit pool for Thread 2. */
    private static final char[] DIGITS = "0123456789".toCharArray();

    /** Special character pool for Thread 3. */
    private static final char[] SPECIALS = "!@#$%&*".toCharArray();

    // -----------------------------------------------------------------------
    // UI Controls (accessed from multiple threads via Platform.runLater)
    // -----------------------------------------------------------------------
    private TextArea outputArea;
    private Label    statusLabel;
    private Button   startButton;

    // -----------------------------------------------------------------------
    // Counters (for status display)
    // -----------------------------------------------------------------------
    private final AtomicInteger letterCount  = new AtomicInteger(0);
    private final AtomicInteger digitCount   = new AtomicInteger(0);
    private final AtomicInteger specialCount = new AtomicInteger(0);

    // -----------------------------------------------------------------------
    // JavaFX Entry Point
    // -----------------------------------------------------------------------

    /**
     * Builds and shows the primary Stage.
     *
     * @param primaryStage the main application window
     */
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("ThreeThreads – Module 8.2 | CSD420");

        // --- Output TextArea ---
        outputArea = new TextArea();
        outputArea.setEditable(false);
        outputArea.setWrapText(true);
        outputArea.setFont(Font.font("Monospaced", 13));
        outputArea.setPrefRowCount(22);
        VBox.setVgrow(outputArea, Priority.ALWAYS);

        // --- Status label ---
        statusLabel = new Label("Press Start to begin generating characters.");
        statusLabel.setStyle("-fx-font-size: 12px;");

        // --- Counter labels ---
        Label counterHeader = new Label("Character Counts  |  Letters: 0  |  Digits: 0  |  Specials: 0");
        counterHeader.setId("counterLabel");
        counterHeader.setStyle("-fx-font-family: monospace; -fx-font-size: 12px;");

        // --- Start Button ---
        startButton = new Button("▶  Start Threads");
        startButton.setStyle("-fx-font-size: 13px; -fx-padding: 6 20;");
        startButton.setOnAction(e -> runThreads(counterHeader));

        // --- Clear Button ---
        Button clearButton = new Button("✕  Clear");
        clearButton.setStyle("-fx-font-size: 13px; -fx-padding: 6 20;");
        clearButton.setOnAction(e -> {
            outputArea.clear();
            letterCount.set(0);
            digitCount.set(0);
            specialCount.set(0);
            counterHeader.setText("Character Counts  |  Letters: 0  |  Digits: 0  |  Specials: 0");
            statusLabel.setText("Cleared. Press Start to run again.");
            startButton.setDisable(false);
        });

        // --- Button Bar ---
        HBox buttonBar = new HBox(12, startButton, clearButton);
        buttonBar.setAlignment(Pos.CENTER_LEFT);

        // --- Root Layout ---
        VBox root = new VBox(10,
                new Label("Concurrent Character Generator – Three Threads"),
                outputArea,
                counterHeader,
                buttonBar,
                statusLabel
        );
        root.setPadding(new Insets(14));
        root.setStyle("-fx-background-color: #f4f4f4;");

        primaryStage.setScene(new Scene(root, 780, 520));
        primaryStage.show();
    }

    // -----------------------------------------------------------------------
    // Thread Orchestration
    // -----------------------------------------------------------------------

    /**
     * Creates and starts three daemon threads. Each thread appends characters to
     * the shared TextArea via {@link Platform#runLater(Runnable)}.
     *
     * @param counterLabel the Label that displays running character counts
     */
    private void runThreads(Label counterLabel) {
        // Reset counters and UI state
        letterCount.set(0);
        digitCount.set(0);
        specialCount.set(0);
        outputArea.clear();
        startButton.setDisable(true);
        statusLabel.setText("Threads running…");

        Thread t1 = buildThread("Letter-Thread",  LETTERS,  letterCount,  counterLabel);
        Thread t2 = buildThread("Digit-Thread",   DIGITS,   digitCount,   counterLabel);
        Thread t3 = buildThread("Special-Thread", SPECIALS, specialCount, counterLabel);

        // Monitor thread completion
        Thread monitor = new Thread(() -> {
            try {
                t1.join();
                t2.join();
                t3.join();
                Platform.runLater(() -> {
                    statusLabel.setText("All threads finished!  "
                            + "Letters: " + letterCount.get()
                            + "  |  Digits: " + digitCount.get()
                            + "  |  Specials: " + specialCount.get());
                    startButton.setDisable(false);
                });
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }, "Monitor-Thread");

        t1.setDaemon(true);
        t2.setDaemon(true);
        t3.setDaemon(true);
        monitor.setDaemon(true);

        t1.start();
        t2.start();
        t3.start();
        monitor.start();
    }

    // -----------------------------------------------------------------------
    // Thread Factory
    // -----------------------------------------------------------------------

    /**
     * Builds a thread that randomly picks characters from {@code pool} and
     * appends each one to the TextArea on the JavaFX Application Thread.
     * The thread terminates after producing {@link #MIN_CHARS} characters.
     *
     * @param name         thread name (visible in debuggers/profilers)
     * @param pool         character pool to draw from
     * @param counter      atomic counter tracking this thread's output
     * @param counterLabel UI label refreshed after each character
     * @return a configured, not-yet-started Thread
     */
    private Thread buildThread(String name, char[] pool,
                               AtomicInteger counter, Label counterLabel) {
        Random rng = new Random();
        return new Thread(() -> {
            for (int i = 0; i < MIN_CHARS; i++) {
                char ch = pool[rng.nextInt(pool.length)];
                int  n  = counter.incrementAndGet();

                // Append character on the JavaFX thread
                Platform.runLater(() -> {
                    outputArea.appendText(String.valueOf(ch));
                    counterLabel.setText(
                            "Character Counts  |  Letters: " + letterCount.get()
                                    + "  |  Digits: "   + digitCount.get()
                                    + "  |  Specials: " + specialCount.get()
                    );
                });

                // Small yield to allow other threads to interleave
                if (i % 5 == 0) {
                    Thread.yield();
                }
            }
        }, name);
    }

    // -----------------------------------------------------------------------
    // Main
    // -----------------------------------------------------------------------

    /**
     * Application entry point.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        launch(args);
    }
}