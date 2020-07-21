package ua.com.gigasoft.instrument2c.secondModel;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class ExDocWEBList {
	@Valid
	private List<ExDocWEB> docList;
	
	public List<ExDocWEB> getDocList() {
		return docList;
	}
	public void setDocList(List<ExDocWEB> docList) {
		this.docList = docList;
	}
	
	public ExDocWEBList() {
	}
	public ExDocWEBList(@Valid List<ExDocWEB> docList) {
		super();
		this.docList = docList;
	}
	@Override
	public String toString() {
		return "ExDocWEBList [docList=" + docList + "]";
	}
	
}
