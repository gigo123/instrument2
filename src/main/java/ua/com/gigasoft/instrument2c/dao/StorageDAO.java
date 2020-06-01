package ua.com.gigasoft.instrument2c.dao;

import java.util.List;

import ua.com.gigasoft.instrument2c.mainModel.Storage;
public interface StorageDAO {
	public Storage getStorageByID(long id);
	public List<Storage> getStorageByinstrument( long instrumentId);
	public List<Storage> getStorageByBox(long boxId);
	public boolean createStorage(Storage storage);
	public boolean deleteStorage(long id);
	public boolean hasError();
	public boolean updateStorage(long id,Storage storage);
	public void closeConection();
}
