package commandsExecutors;

import shapes.Shape;

public interface SurfaceShapeCommandsExecutor {
	void addShape();

	void modifyShape(Shape selectedShape);
}