package utils;

import java.util.Arrays;
import java.util.List;

public class Normaliser {
    private final List<String> normalisedTitles;

    public Normaliser() {
        normalisedTitles = Arrays.asList("Architect", "Software engineer", "Quantity surveyor", "Accountant");
    }

    private double calculateQualityScore(String s1, String s2) {
        if (s1 == null || s2 == null) {
            throw new IllegalArgumentException("Input strings cannot be null");
        }

        int maxLength = Math.max(s1.length(), s2.length());
        int editDistance = calculateEditDistance(s1, s2);

        return 1.0 - ((double) editDistance / maxLength);
    }

    // Levenshtein Distance
    private int calculateEditDistance(String str1, String str2) {
        int m = str1.length();
        int n = str2.length();

        // Initialize the matrix
        int[][] dp = new int[m + 1][n + 1];

        // Fill the base cases
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }

        // Compute the values in the matrix
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], // substitution
                            Math.min(dp[i - 1][j],    // deletion
                                    dp[i][j - 1]));  // insertion
                }
            }
        }

        return dp[m][n];
    }

    public String normalise(String jobTitle) {
        if (jobTitle == null) {
            throw new IllegalArgumentException("Job title cannot be null");
        }

        String bestMatch = "";
        double highestQuality = 0.0;

        for (String normalisedTitle : normalisedTitles) {
            double quality = calculateQualityScore(jobTitle, normalisedTitle);
            if (quality > highestQuality) {
                highestQuality = quality;
                bestMatch = normalisedTitle;
            }
        }

        if (highestQuality < 0.5 || bestMatch.isEmpty()) {
            return "No match found";
        } else {
            return bestMatch;
        }
    }
}
