import java.awt.BasicStroke;
import java.awt.Color;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import org.jfree.chart.annotations.XYPointerAnnotation;
import org.jfree.data.time.Day;
import org.jfree.data.xy.XYDataset;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;

public class analysis {
	public static XYPointerAnnotation indicator(XYDataset set)
			throws IOException {
		XYPointerAnnotation indicator = null;
		double x = 0;
		double y1, y2;
		boolean temp = true;
		Boolean sell = null;

		int count = set.getItemCount(1) - 1;
		y1 = set.getYValue(1, count);
		y2 = set.getYValue(4, count);
		if (y2 > y1)
			temp = true;
		else if (y2 < y1)
			temp = false;
		count--;
	
		while (count >= 0) {
			y1 = set.getYValue(1, count);
			y2 = set.getYValue(4, count);
			if ((temp && y2 <= y1) || ((!temp) && y2 >= y1)) {
				sell = new Boolean(temp);
				x = set.getXValue(1, count);
				break;
			}
			count--;
		}
		if (sell == null) {
			return null;
		} else if (sell) {
			indicator = new XYPointerAnnotation("sell", x, y1, Math.PI * 3 / 2);
			indicator.setArrowPaint(Color.RED);
		} else if (!sell) {
			indicator = new XYPointerAnnotation("buy", x, y1, Math.PI / 2);
			indicator.setArrowPaint(Color.GREEN);
		}
		indicator.setArrowWidth(6.0);
		indicator.setArrowStroke(new BasicStroke(6.0f));
		return indicator;
	}
}
