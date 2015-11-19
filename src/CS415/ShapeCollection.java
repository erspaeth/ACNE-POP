package CS415;

public class ShapeCollection {
	
	private static ShapeCollection instance = null;

	protected ShapeCollection() {
		// Exists only to defeat instantiation.
	}

	public static ShapeCollection getInstance() {
		if (instance == null) {
			instance = new ShapeCollection();
		}
		return instance;
	}
}
