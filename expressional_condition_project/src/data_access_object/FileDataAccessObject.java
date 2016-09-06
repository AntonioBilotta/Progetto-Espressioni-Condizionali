package data_access_object;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;

public interface FileDataAccessObject<T extends Serializable> {
	
	void save(T object, File file) throws FileNotFoundException, IOException;
	
	T load(File file) throws IOException, ClassNotFoundException;

}
