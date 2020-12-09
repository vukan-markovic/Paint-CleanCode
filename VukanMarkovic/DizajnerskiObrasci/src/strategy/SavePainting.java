package strategy;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

import geometry.Shape;

public class SavePainting implements Save{
	
	private List<Shape> shapes;
	

	@Override
	public void saveFile(String filePath) {
	
		try {
			FileOutputStream fos = new FileOutputStream(filePath);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(shapes);
			oos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	public List<Shape> getShapes() {
		return shapes;
	}


	public void setShapes(List<Shape> shapes) {
		this.shapes = shapes;
	}
	
	

}
