package com.diabunity.diabunityapi.utils;

/**
 * The {@code LinearRegression} class performs a simple linear regression
 * on an set of <em>n</em> data points (<em>y<sub>i</sub></em>, <em>x<sub>i</sub></em>).
 * That is, it fits a straight line <em>y</em> = &alpha; + &beta; <em>x</em>,
 * (where <em>y</em> is the response variable, <em>x</em> is the predictor variable,
 * &alpha; is the <em>y-intercept</em>, and &beta; is the <em>slope</em>)
 * that minimizes the sum of squared residuals of the linear regression model.
 * It also computes associated statistics, including the coefficient of
 * determination <em>R</em><sup>2</sup> and the standard deviation of the
 * estimates for the slope and <em>y</em>-intercept.
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 */
public class LinearRegression {
    private final double intercept, slope;

    /**
     * Performs a linear regression on the data points {@code (y[i], x[i])}.
     *
     * @param y the corresponding values of the response variable
     * @throws IllegalArgumentException if the lengths of the two arrays are not equal
     */
    public LinearRegression(int[] y) {
        int n = y.length;

        int[] x = new int[n];

        for (int i = 0; i < n; i++) {
            x[i] = i;
        }

        // first pass
        double sumx = 0.0, sumy = 0.0;
        for (int i = 0; i < n; i++) {
            sumx += x[i];
            sumy += y[i];
        }
        double xbar = sumx / n;
        double ybar = sumy / n;

        // second pass: compute summary statistics
        double xxbar = 0.0, xybar = 0.0;
        for (int i = 0; i < n; i++) {
            xxbar += (x[i] - xbar) * (x[i] - xbar);
            xybar += (x[i] - xbar) * (y[i] - ybar);
        }
        slope = xybar / xxbar;
        intercept = ybar - slope * xbar;

    }

    /**
     * Returns the expected response {@code y} given the value of the predictor
     * variable {@code x}.
     *
     * @param x the value of the predictor variable
     * @return the expected response {@code y} given the value of the predictor
     * variable {@code x}
     */
    public double predict(double x) {
        return slope * x + intercept;
    }
}
