package commandsExecutors;

import shapes.Shape;

public interface ShapeCommandsExecutor {
	void addShape(int xCoordinate, int yCoordinate);

	void modifyShape(Shape selectedShape);
}