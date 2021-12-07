package project2;

import java.util.ArrayList;


/**
 * DataSetList class is used to store a collection of DataSet objects. 
 * This class inherits all of its properties from an ArrayList<DataSet>. It 
 * adds DataSet-specific functions that allow search by title/Description/URL. 
 * 
 * This class does store DataSet objects in particular order. 
 * 
 * @author Wenqi Liao
 * @version 03/08/2021
 *
 */
public class DataSetList extends ArrayList<DataSet> {

	/**
	 * Search through the list of DataSet for an object matching the given keyword.
	 * 
	 * @param keyword the name of the DataSet for which to search
	 * @return the reference to a matching DataSet object in the list, or null if
	 *         the matching DataSet is not found
	 */
	public DataSetList getByTitle(String keyword) throws IllegalArgumentException {

		if (keyword == null || keyword == "") {
			throw new IllegalArgumentException("keyword should not be null or empty string");
		}

		DataSetList titles = new DataSetList();

		for (DataSet ds : this) {
			String title = ds.getTitle().toUpperCase();

			if (title.contains(keyword.toUpperCase())) {
				titles.add(ds);
			}
		}
		if (titles.size() == 0) {
			return null;
		}

		Sort(titles);

		return titles;
	}

	/**
	 * Sort the DataSetList by order of natural DataSet.
	 * 
	 * @param the DataSetList needed to be sort
	 */
	private void Sort(DataSetList needSort) {

		// sort by compareTo in the DataSet class
		for (int pass = needSort.size() - 1; pass > 0; pass--) {
			for (int i = 0; i < pass; i++) {
				if (needSort.get(i).compareTo(needSort.get(i + 1)) > 0) {
					DataSet temp = needSort.get(i);
					needSort.set(i, needSort.get(i + 1));
					needSort.set(i + 1, temp);
				}
			}
		}

	}

	/**
	 * Search through the list of DataSet for an object matching the given keyword.
	 * 
	 * @param keyword the description of the DataSet for which to search
	 * @return the reference to a matching DataSet object in the list, or null if
	 *         the matching DataSet is not found
	 */
	public DataSetList getByDescription(String keyword) throws IllegalArgumentException {

		if (keyword == null || keyword == "") {
			throw new IllegalArgumentException("keyword should not be null or empty string");
		}

		DataSetList descriptions = new DataSetList();

		for (DataSet ds : this) {
			String description = ds.getDescription().toUpperCase();
			if (description.contains(keyword.toUpperCase())) {
				descriptions.add(ds);
			}
		}
		if (descriptions.size() == 0) {
			return null;
		}

		Sort(descriptions);

		return descriptions;
	}

	/**
	 * Search through the list of DataSet for an object matching the given keyword.
	 * 
	 * @param keyword the URL of the DataSet for which to search
	 * @return the reference to a matching DataSet object in the list, or null if
	 *         the matching DataSet is not found
	 */
	public DataSetList getByURL(String keyword) throws IllegalArgumentException {
		
		if (keyword == null || keyword == "") {
			throw new IllegalArgumentException("keyword should not be null or empty string");
		}

		DataSetList urls = new DataSetList();

		for (DataSet ds : this) {
			String url = ds.getURL().toUpperCase();
			if (url.contains(keyword.toUpperCase())) {
				urls.add(ds);
			}
		}
		if (urls.size() == 0) {
			return null;
		}

		Sort(urls);

		return urls;
	}

}
