
public class StepCounter {
	public StepCounter() {

	}

	public static int countSteps(double[][] sensorData) {
		double[] magnitudes = calculateMagnitudesFor(sensorData);
		int timesPassedStandardDeviation = 0;
		double stepThreshold = 2.0 * calculateStandardDeviation(magnitudes, calculateMean(magnitudes));
		for (int i = 0; i < magnitudes.length - 1; i++) {
			double firstValue = magnitudes[i];
			double secondValue = magnitudes[i + 1];
			if (firstValue < secondValue && firstValue < stepThreshold && secondValue > stepThreshold) {
				timesPassedStandardDeviation++;
			}
		}
		return timesPassedStandardDeviation * 2;
	}

	public static double calculateMagnitude(double x, double y, double z) {
		return Math.sqrt((x * x) + (y * y) + (z * z));
	}

	public static double[] calculateMagnitudesFor(double[][] sensorData) {
		double[] vectors = new double[sensorData.length];
		for (int i = 0; i < sensorData.length; i++) {
			vectors[i] = calculateMagnitude(sensorData[i][0], sensorData[i][1], sensorData[i][2]);
		}
		return vectors;
	}

	private static double calculateStandardDeviation(double[] arr, double mean) {
		double value = 0;
		for (int i = 0; i < arr.length; i++) {
			value += (arr[i] - mean) * (arr[i] - mean);
		}
		value /= arr.length - 1;
		value = Math.sqrt(value);
		return value;
	}

	private static double calculateMean(double[] arr) {
		double total = 0;
		for (int i = 0; i < arr.length; i++) {
			total += arr[i];
		}
		return total / ((double) (arr.length));
	}
}
