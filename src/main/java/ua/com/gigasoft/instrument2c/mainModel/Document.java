package ua.com.gigasoft.instrument2c.mainModel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

import ua.com.gigasoft.instrument2c.secondModel.DocType;
@Entity
@Table(name = "Document")  
public class Document {
	 @Id 
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	 
	private int year;
	private int number;
	private String numberString;
	private LocalDate date;
	private int totalInstrum;
	private float totalAmount;
	@ElementCollection
	private List<DocumentRow> rowsList= new ArrayList<DocumentRow>();
	//@ElementCollection(targetClass=DocumentRow.class)
//	@CollectionTable(name = "DOCROW")
	//@OrderColumn
	//@Column
	//@OneToMany
	public List<DocumentRow> getRowsList() {
		return rowsList;
	}
	public void setRowsList(List<DocumentRow> rowsList) {
		this.rowsList = rowsList;
	}
	private DocType docType; 
	
	public int getTotalInstrum() {
		return totalInstrum;
	}
	public void setTotalInstrum(int totalInstrum) {
		this.totalInstrum = totalInstrum;
	}
	public float getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(float totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getNumberString() {
		return numberString;
	}
	public void setNumberString(String numberString) {
		this.numberString = numberString;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	
	
	public DocType getDocType() {
		return docType;
	}
	public void setDocType(DocType docType) {
		this.docType = docType;
	}
	public Document(int year, int number, String numberString, LocalDate date, DocType docType) {
		super();
		this.year = year;
		this.number = number;
		this.numberString = numberString;
		this.date = date;
		this.docType = docType;
	}
	
	
	public Document(int year, int number, String numberString, LocalDate date, int totalInstrum, float totalAmount, DocType docType) {
		super();
		this.year = year;
		this.number = number;
		this.numberString = numberString;
		this.date = date;
		this.totalInstrum = totalInstrum;
		this.totalAmount = totalAmount;
		this.docType = docType;
	}
	
	@Override
	public String toString() {
		return "Document [id=" + id + ", year=" + year + ", number=" + number + ", numberString=" + numberString
				+ ", date=" + date + ", totalInstrum=" + totalInstrum + ", totalAmount=" + totalAmount + ", docType="
				+ docType + "]";
	}
	public Document() {
		
	}
	
}
