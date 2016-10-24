import java.util.Arrays;
import java.util.Random;
import javax.swing.JFrame;
import org.math.plot.Plot2DPanel;

public class BasicPlotting {
	public static void main(String[] args) {
		String[] columnNames = { "time", "xg", "yg", "zg", "g" };
		CSVData data = CSVData.readCSVFile("data/walkingSampleData-out.csv", columnNames, 1);

		double[][] sample1 = data.getColumns(new String[] { "xg", "yg", "zg" });

		Plot2DPanel plot = new Plot2DPanel();

		// add a line plot to the PlotPanel
		plot.addLinePlot("X acceleration", StepCounter.calculateMagnitudesFor(sample1));
		
		System.out.println(StepCounter.countSteps(sample1));
		// put the PlotPanel in a JFrame, as a JPanel
		JFrame frame = new JFrame("Results");
		frame.setSize(800, 600);
		frame.setContentPane(plot);
		frame.setVisible(true);
	}

	private static void addNoise(double[] sample, int max) {
		for (int i = 0; i < sample.length; i++) {
			sample[i] += (-max + Math.random() * 2 * max);
		}
	}
}
