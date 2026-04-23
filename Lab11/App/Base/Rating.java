package Lab11.App.Base;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Rating
{
    public static final double MIN_RATING = 0;
    public static final double MAX_RATING = 10;

    private final double initialRating;
    private double currentRating;

    private final Map<Date, Double> ratingsHistory = new HashMap<>();
    private int ratingCount = 0;

    public Rating(double initialRating)
    {
        this.initialRating = initialRating;
        rate(initialRating);
    }

    public double latest() { return currentRating; }

    public double average()
    {
        return ratingsHistory.values().stream().mapToDouble(Double::doubleValue).average().orElse(0);
    }

    public double minimum()
    {
        return ratingsHistory.values().stream().mapToDouble(Double::doubleValue).min().orElse(0);
    }

    public Rating rate(double newRating)
    {
        this.currentRating = Math.clamp(newRating, MIN_RATING, MAX_RATING);
        ratingCount++;
        ratingsHistory.put(new Date(), newRating);
        return this;
    }

    public Rating increment(double amount) { return rate(initialRating + amount); }

    public int getRatingCount() { return ratingCount; }

    public Map<Date, Double> getRatingsHistory() { return ratingsHistory; }

    /* UTILITIES */

    public String getFormatted()
    {
        return switch ((int) (Math.floor(currentRating) / 2))
        {
            //@formatter:off
            case 0  -> "[ - - - - - ]";
            case 1  -> "[ * - - - - ]";
            case 2  -> "[ * * - - - ]";
            case 3  -> "[ * * * - - ]";
            case 4  -> "[ * * * * - ]";
            case 5  -> "[ * * * * * ]";
            default -> "[  INVALID  ]";
            //@formatter:on
        };
    }
}
