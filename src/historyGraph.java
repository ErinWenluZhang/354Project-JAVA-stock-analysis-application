import java.awt.Color;
import java.awt.BasicStroke;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import org.jfree.chart.ChartMouseEvent;
import org.jfree.chart.ChartMouseListener;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RefineryUtilities;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.CrosshairLabelGenerator;
import org.jfree.chart.panel.CrosshairOverlay;
import org.jfree.chart.plot.Crosshair;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;

public class historyGraph extends JFrame {
	private String stock;
	private int intv;
	private XYDataset set;

	public historyGraph(String applicationTitle, String chartTitle, String stock)
			throws IOException {
		super(applicationTitle);
		this.stock = stock;
		this.intv = 1;
		JPanel bar = new JPanel();
		JLabel his = new JLabel("History interval: ");
		bar.add(his);
		String[] interval = { "1 year", "2 years", "5 years" };
		final JComboBox<String> drop = new JComboBox<String>(interval);
		bar.add(drop);
		// JButton enter = new JButton("Enter");
		// bar.add(enter);
		set = createDataset(intv);
		JFreeChart xylineChart = ChartFactory.createTimeSeriesChart(chartTitle,
				"Time(days)", "Close Price",
				// createDataset(intv) ,
				set,
				// PlotOrientation.VERTICAL ,
				true, true, false);

		ChartPanel chartPanel = new ChartPanel(xylineChart);
		chartPanel.setPreferredSize(new java.awt.Dimension(900, 500));
		final XYPlot plot = xylineChart.getXYPlot();
		/*
		 * NumberAxis range = (NumberAxis) plot.getRangeAxis();
		 * range.setRange(50.00, 700.00); range.setTickUnit(new
		 * NumberTickUnit(0.01));
		 */
		XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer(true,
				false);
		renderer.setSeriesPaint(0, Color.RED);
		renderer.setSeriesStroke(0, new BasicStroke(1.0f));
		plot.setRenderer(renderer);
		JPanel contentPane = new JPanel();
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		contentPane.add(bar);
		contentPane.add(chartPanel);
		setContentPane(contentPane);
		drop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// JComboBox cb = (JComboBox)e.getSource();
				// String year = (String)cb.getSelectedItem();
				String year = (String) drop.getSelectedItem();
				if (year.equals("1 year")) {
					intv = 1;
				} else if (year.equals("2 years")) {
					intv = 2;
				} else if (year.equals("5 years")) {
					intv = 5;
				}
				try {
					set = createDataset(intv);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				plot.setDataset(set);

			}
		});
		traceValue(chartPanel);
	}

	private void traceValue(ChartPanel x) {
		CrosshairOverlay overlay = new CrosshairOverlay();
		Crosshair xCrosshair = new Crosshair(Double.NaN, Color.GRAY,
				new BasicStroke(0f));
		xCrosshair.setLabelVisible(true);
		Crosshair yCrosshair = new Crosshair(Double.NaN, Color.GRAY,
				new BasicStroke(0f));
		yCrosshair.setLabelVisible(true);
		overlay.addDomainCrosshair(xCrosshair);
		overlay.addRangeCrosshair(yCrosshair);
		x.addOverlay(overlay);
		x.addChartMouseListener(new ChartMouseListener() {
			@Override
			public void chartMouseClicked(ChartMouseEvent arg0) {
				// ignore
			}

			@Override
			public void chartMouseMoved(ChartMouseEvent event) {
				Rectangle2D dataArea = x.getScreenDataArea();
				JFreeChart chart = event.getChart();
				XYPlot plot = (XYPlot) chart.getPlot();
				ValueAxis xAxis = plot.getDomainAxis();
				double x = xAxis.java2DToValue(event.getTrigger().getX(),
						dataArea, RectangleEdge.BOTTOM);
				double y = DatasetUtilities.findYValue(plot.getDataset(), 0, x);
				xCrosshair.setValue(x);
				yCrosshair.setValue(y);
				newGenerator gene = new newGenerator();
				xCrosshair.setLabelGenerator(gene);

			}
		});
	}

	private class newGenerator implements CrosshairLabelGenerator {

		@Override
		public String generateLabel(Crosshair x) {
			long y = (long) x.getValue();
			Day d = new Day(new Date(y));
			return d.toString();
		}

	}

	private XYDataset createDataset(int intv) throws IOException {
		final TimeSeries Year = new TimeSeries(intv + " year");
		Stock a = YahooFinance.get(stock);
		Calendar from = Calendar.getInstance();
		Calendar to = Calendar.getInstance();
		from.add(Calendar.YEAR, 0 - intv);
		List<HistoricalQuote> test = a.getHistory(from, to, Interval.DAILY);
		Iterator<HistoricalQuote> i = test.iterator();
		while (i.hasNext()) {
			HistoricalQuote it = i.next();
			Year.add(new Day(it.getDate().getTime()), it.getClose());
		}

		final TimeSeriesCollection dataset = new TimeSeriesCollection();
		dataset.addSeries(Year);

		return dataset;
	}

}