package project2;

import java.net.URL;
import java.util.ArrayList;

/**
 * This class represents a DataSet. 
 * It represents by title,description,and links
 * 
 * @author Wenqi Liao
 * @version 03/08/2021
 *
 */
public class DataSet implements Comparable<DataSet> {

	private String title;
	private String description;
	private ArrayList<URL> links;
	private Date day;
	private String hatTips;

	/**
	 * Constructs a new DataSet object with specified value.
	 * 
	 * @param title, description, links value to be used for this DataSet;
	 * @throws IllegalArgumentException if parameters are invalid
	 */
	public DataSet(String title, String description, ArrayList<URL> links) throws IllegalArgumentException {

		checkTitle(title);
		checkDescription(description);
		checkLinks(links);
	}

	/**
	 * Validates the links for this DataSet object.
	 * 
	 * @param links value to be examined and set.
	 * @throws IllegalArgumentException if the links is invalid
	 */
	private void checkLinks(ArrayList<URL> links) throws IllegalArgumentException {

		// links should be a non-empty list of URL objects
		if (links.size() == 0 || links == null) {
			throw new IllegalArgumentException("links should not be null or empty ");
		}
		this.links = links;
	}

	/**
	 * Validates the description for this DataSet object.
	 * 
	 * @param description value to be examined and set.
	 * @throws IllegalArgumentException if the description is invalid
	 */
	private void checkDescription(String description) throws IllegalArgumentException {

		// description should not be null or empty
		if (description == null || description.equals("")) {
			throw new IllegalArgumentException("description should not be null or empty ");
		}
		this.description = description;
	}

	/**
	 * Validates the title for this DataSet object.
	 * 
	 * @param title value to be examined and set.
	 * @throws IllegalArgumentException if the title is invalid
	 */
	private void checkTitle(String title) throws IllegalArgumentException {

		// title should not be null or empty
		if (title == null || title.equals("")) {
			throw new IllegalArgumentException("title should not be null or empty ");
		}
		this.title = title;
	}

	/**
	 * Validates and sets the date for this DataSet object.
	 * 
	 * @param Date value to be examined and set.
	 * @throws IllegalArgumentException if the Date is invalid
	 */
	public void setDate(Date date) throws IllegalArgumentException {

		if (date.getYear() < 2000 || date == null) {
			throw new IllegalArgumentException("date is invalid ");
		}
		day = new Date(date.getYear(), date.getMonth(), date.getDay());
	}

	/**
	 * Returns the Date of this DataSet object.
	 * 
	 * @return the Date of this DataSet object
	 */
	public Date getDate() {

		return day;
	}

	/**
	 * Validates and sets the HatTips for this DataSet object.
	 * 
	 * @param HatTips value to be examined and set.
	 * @throws IllegalArgumentException if the HatTips is invalid
	 */
	public void setHatTips(String hatTips) throws IllegalArgumentException {

		if (hatTips == null) {
			throw new IllegalArgumentException("hatTips is invalid ");
		}
		this.hatTips = hatTips;
	}

	/**
	 * Returns the HatTips of this DataSet object.
	 * 
	 * @return the HatTips of this DataSet object
	 */
	public String getHatTips() {

		if (hatTips == null || hatTips.equals("")) {
			return "";
		} else {
			return hatTips;
		}
	}

	/**
	 * Returns the integer representation which DataSet is larger.
	 * 
	 * @returns the integer representation of this DataSet comparison
	 */
	public int compareTo(DataSet o) {

		if (o.getDate() == this.getDate() || o.getDate() == null || this.getDate() == null) {
			return this.title.compareToIgnoreCase(o.title);
		} else {
			return this.getDate().compareTo(o.getDate());
		}
	}

	/**
	 * Indicates whether some object obj is "equal to" this one. To DataSet objects
	 * are considered equal if they are the same date and title
	 * 
	 * @return true if this object is the same as the obj argument; false otherwise.
	 */
	public boolean equals(Object obj) {
		
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof DataSet)) {
			return false;
		}

		if (this == obj) {
			return true;
		}

		DataSet other = (DataSet) obj;
		if (other.compareTo(this) == 0 && this.title.equalsIgnoreCase(other.title)) {
			return true;
		}
		return false;
	}

	/**
	 * Returns the string representation of this DataSet.
	 * 
	 * @returns the string representation of this DataSet object
	 */
	public String toString() {

		String link = "";
		for (int i = 0; i < links.size(); i++) {
			link += links.get(i) + "\n";
		}
		if (day == null) {
			return title.toLowerCase() + "\n" + description.toLowerCase() + "\n" + link;
		} else {
			return day + "\n" + title.toLowerCase() + "\n" + description.toLowerCase() + "\n" + link;
		}
	}

	/**
	 * Returns the title of this DataSet object.
	 * 
	 * @return the title component of this DataSet object
	 */
	public String getTitle() {

		return title;
	}

	/**
	 * Returns the description of this DataSet object.
	 * 
	 * @return the description component of this DataSet object
	 */
	public String getDescription() {

		return description;
	}

	/**
	 * Returns the URL of this DataSet object.
	 * 
	 * @return the URL component of this DataSet object
	 */
	public String getURL() {
		
		return links.toString();
	}

}
