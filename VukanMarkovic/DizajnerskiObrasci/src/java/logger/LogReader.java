package logger;

import shapes.Shape;

public interface LogReader {
	void addShapeFromLog(String[] line);

	void selectShapeFromLog(String[] line);

	void deselectShapeFromLog(String[] line);

	void modifyShapeFromLog(String[] line, Shape selectedShape);
}