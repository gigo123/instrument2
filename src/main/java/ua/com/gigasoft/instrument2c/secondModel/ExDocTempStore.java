package ua.com.gigasoft.instrument2c.secondModel;


import ua.com.gigasoft.instrument2c.mainModel.DocumentRow;


public class ExDocTempStore {
	private String errorString;
	private DocumentRow docRow;
	private long outStorageId;
	
	public long getOutStorageId() {
		return outStorageId;
	}
	public void setOutStorageId(long outStorageId) {
		this.outStorageId = outStorageId;
	}
	public String getErrorString() {
		return errorString;
	}
	public void setErrorString(String errorString) {
		this.errorString = errorString;
	}
	public DocumentRow getDocRow() {
		return docRow;
	}
	public void setDocRow(DocumentRow doc) {
		this.docRow = doc;
	}
	
	@Override
	public String toString() {
		return "ExDocTempStore [errorString=" + errorString + ", doc=" + docRow + ", outStorageId=" + outStorageId + "]";
	}
	
	public ExDocTempStore(String errorString, DocumentRow doc, long outStorageId) {
		super();
		this.errorString = errorString;
		this.docRow = doc;
		this.outStorageId = outStorageId;
	}
	public ExDocTempStore() {
		
	}

}
