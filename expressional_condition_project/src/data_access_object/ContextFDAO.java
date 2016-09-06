package data_access_object;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import interpreter.Context;

public abstract class ContextFDAO implements FileDataAccessObject<Context>{
	
	public abstract void save(Context context, File file) throws FileNotFoundException, IOException;
	
	public abstract Context load(File file) throws IOException, ClassNotFoundException;

}
