// https://towardsdatascience.com/linear-regression-using-gradient-descent-97a6c8700931
public class LinearRegression2 {
    public static void main(String[] args) {
        double[][] data = {{1, 2}, {3, 2}, {5, 6}, {8, 7}, {6,6}, {20, 2}, {10, 1}};
        double query = 11;

        // Gradient Descent
        double[] parameters = gradientDescent(data, 100);
        System.out.println("m: " + parameters[0]+ "  b: " + parameters[1]);

        // make prediction
        System.out.println(prediction(parameters[0], parameters[1], query));
    }

    /*
        formula of a line: y = mx + b
        loss function: mean square error --> J = 1/n sigma(y - y`)^2 --> 1/n sigma(y - (mx + b))^2
        Derivatives of loss function:
        dm = -2/n sigma x(y - y`)
        db = -2/n sigma (y - y`)
        how to update current value of m and b at each iteration:
        m = m - learningRate * dm
        b = b - learningRate * db
     */
    public static double[] gradientDescent(double[][] data, int epochs) {
        double m = 0;
        double b = 0;
        double learningRate = 0.05;
        int n = data.length;

        for (int i = 0; i < epochs; i++) {

            // calculate the guess and actual y for all the samples
            for (double[] d : data) {
                double x = d[0];
                double y = d[1];
                double yPredicted = (m * x) + b;
                double error = y - yPredicted;
                // calculate the derivative
                m = m + (error * x) * learningRate;
                b = b + error * learningRate;
            }
        }
        return new double[]{m, b};
    }

    public static double prediction(double m, double b, double query) {
        return m * query + b;
    }
}
