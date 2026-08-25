import java.io.*;
import java.util.ArrayList;

public class GradeAnalyzer {

    public static void main(String[] args) {
        String inputFile = "scores.txt";
        String outputFile = "report.txt";

        // Step 1: read scores from file
        ArrayList<Integer> scores = readScores(inputFile);

        // Step 2: calculate statistics
        double average = calculateAverage(scores);

        // Step 5: Find the Highest and Lowest Scores directly in main
        int highest = Integer.MIN_VALUE;
        int lowest = Integer.MAX_VALUE;

        if (!scores.isEmpty()) {
            for (int score : scores) {
                if (score > highest) {
                    highest = score;
                }
                if (score < lowest) {
                    lowest = score;
                }
            }
        } else {
            // Handle edge case where there are no valid scores at all
            highest = 0;
            lowest = 0;
        }

        // Step 3: write and print report
        writeReport(scores, average, highest, lowest, outputFile);
    } 

    // Returns a list of valid scores read from the file
    public static ArrayList<Integer> readScores(String filename) {
        ArrayList<Integer> scores = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String trimmedLine = line.trim();
                
                if (trimmedLine.isEmpty()) {
                    continue; // Skip blank lines
                }
                
                try {
                    int score = Integer.parseInt(trimmedLine);
                    scores.add(score);
                } catch (NumberFormatException e) {
                    System.out.println("Warning: Invalid score skipped -> " + trimmedLine);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        
        return scores;
    }

    // Returns the average of a list of scores, or 0.0 if the list is empty
    public static double calculateAverage(ArrayList<Integer> scores) {
        if (scores.isEmpty()) {
            return 0.0;
        }
        
        double total = 0.0;
        for (int score : scores) {
            total += score;
        }
        
        return total / scores.size();
    } 

    // Writes and prints the report
    public static void writeReport(ArrayList<Integer> scores, double avg, int high, int low, String outputFile) {
        
        // Step 6: Count the Grade Bands
        int countA = 0, countB = 0, countC = 0, countD = 0, countF = 0;
        
        for (int score : scores) {
            if (score >= 90) {
                countA++;
            } else if (score >= 80) {
                countB++;
            } else if (score >= 70) {
                countC++;
            } else if (score >= 60) {
                countD++;
            } else {
                countF++;
            }
        }

        // Step 7: Format the report
        StringBuilder reportText = new StringBuilder();
        reportText.append("=== Grade Analysis Report ===\n");
        reportText.append(String.format("Total scores processed:  %d%n%n", scores.size()));
        
        reportText.append(String.format("Average score:   %.2f%n", avg));
        
        if (scores.isEmpty()) {
            reportText.append("Highest score:   N/A\n");
            reportText.append("Lowest score:    N/A\n\n");
        } else {
            reportText.append(String.format("Highest score:   %d%n", high));
            reportText.append(String.format("Lowest score:    %d%n%n", low));
        }
        
        reportText.append("Grade distribution:\n");
        reportText.append(String.format("  A (90-100):   %d%n", countA));
        reportText.append(String.format("  B (80-89):    %d%n", countB));
        reportText.append(String.format("  C (70-79):    %d%n", countC));
        reportText.append(String.format("  D (60-69):    %d%n", countD));
        reportText.append(String.format("  F (below 60): %d%n", countF));

        // Convert StringBuilder to a final string
        String output = reportText.toString();

        // Print to terminal
        System.out.println(output);

        // Write to report.txt using BufferedWriter
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            writer.write(output);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}