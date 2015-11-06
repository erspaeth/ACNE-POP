package CS415;

public class FileManager {

	private static FileManager instance = null;

	protected FileManager() {
		// Exists only to defeat instantiation.
	}

	public static FileManager getInstance() {
		if (instance == null) {
			instance = new FileManager();
		}
		return instance;
	}
	
}
