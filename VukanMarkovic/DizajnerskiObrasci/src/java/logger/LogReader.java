package logger;

import shapes.Shape;

public interface LogReader {
	void addShapeFromLog(String[] line);

	void modifyShapeFromLog(String[] line, Shape selectedShape);

	void selectShapeFromLog(String[] line);

	void deselectShapeFromLog(String[] line);
}