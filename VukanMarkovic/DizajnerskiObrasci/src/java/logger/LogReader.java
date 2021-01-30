package logger;

import shapes.Shape;

public interface LogReader {
	void addShapeFromLog(String[] logLine);

	void modifyShapeFromLog(String[] logLine, Shape selectedShape);

	void selectShapeFromLog(String[] logLine);

	void deselectShapeFromLog(String[] logLine);
}