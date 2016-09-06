package data_access_object;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import interpreter.Context;

public class DefaultContextFDAO extends ContextFDAO {
	
	private FileOutputStream fos;
	private ObjectOutputStream oos;
	private FileInputStream fis;
	private ObjectInputStream ois;

	@Override
	public void save(Context context, File file) throws IOException {
		this.fos = new FileOutputStream(file);
		this.oos = new ObjectOutputStream(fos);
		this.oos.writeObject(context);
	}

	@Override
	public Context load(File file) throws IOException, ClassNotFoundException {
		this.fis = new FileInputStream(file);
		this.ois = new ObjectInputStream(fis);
		Context context = (Context) this.ois.readObject();
		return context;
	}

}
