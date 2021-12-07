package project2;

/**
 * This class represents a Date. 
 * It represents by year, month and day
 * 
 * @author Wenqi Liao
 * @version 03/08/2021
 *
 */
public class Date implements Comparable<Date> {

	private int year;
	private int month;
	private int day;

	/**
	 * Constructs a new Date object with specified year, month and day.
	 * 
	 * @param year, month and day to be used for this Date;
	 * @throws IllegalArgumentException if hexValue parameter is invalid
	 */
	public Date(int year, int month, int day) throws IllegalArgumentException {

		setYear(year);
		setMonth(month);
		setDay(day);
	}

	/**
	 * Validates and sets the day for this Date object.
	 * 
	 * @param day value to be examined and set.
	 * @throws IllegalArgumentException if the dat is invalid
	 */
	private void setDay(int day) throws IllegalArgumentException {

		// a valid day depends on the month of the year and the year

		// for month have 31 days
		if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || 
				month == 10 || month == 12) {
			if (day > 31 || day < 1) {
				throw new IllegalArgumentException("Invalid day. the range of day " 
						+ "in this month shoud be 1-31");
			}

			// for February
		} else if (month == 2) {
			// if leap year, 29 days
			if (year % 4 == 0 && year % 100 != 0 || (year % 4 == 0) && (year % 100 == 0) 
					&& (year % 400 == 0)) {
				if (day > 29 || day < 1) {
					throw new IllegalArgumentException("Invalid day. Feb in a leap year " 
							+ "should be 1-29");
				}
				// a normal February, 28 days
			} else {
				if (day > 28 || day < 1) {
					throw new IllegalArgumentException("Invalid day. Feb in a normal year " 
							+ "should be 1-28");
				}
			}

			// all other months, 30 days
		} else {
			if (day > 30 || day < 1) {
				throw new IllegalArgumentException("Invalid day. the range of day in this month" 
						+ " shoud be 1-30");
			}
		}
		this.day = day;

	}

	/**
	 * Validates and sets the month for this Date object.
	 * 
	 * @param month value to be examined and set.
	 * @throws IllegalArgumentException if the month is invalid
	 */
	private void setMonth(int month) throws IllegalArgumentException {

		// a valid month has to be a positive integer in the range of 1-12
		if (month > 12 || month < 1) {
			throw new IllegalArgumentException(
				"Invalid month. A month should be a positive integer in the range of 1-12.");
		}
		this.month = month;
	}

	/**
	 * Validates and sets the year for this Date object.
	 * 
	 * @param year value to be examined and set.
	 * @throws IllegalArgumentException if the year is invalid
	 */
	private void setYear(int year) throws IllegalArgumentException {

		// a valid year should be positive integer
		if (year <= 0) {
			throw new IllegalArgumentException(
				"Invalid year. A year should be a positive integer");
		}
		this.year = year;
	}

	/**
	 * Indicates whether some object obj is "equal to" this one. To Date objects are
	 * considered equal if they are the same date
	 * 
	 * @return true if this object is the same as the obj argument; false otherwise.
	 */
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Date)) {
			return false;
		}

		Date other = (Date) obj;
		if (this.compareTo(other) == 0) {
			return true;
		}
		return false;
	}

	/**
	 * Returns the string representation of this Date.
	 * 
	 * @returns the string representation of this Date object
	 */
	public String toString() {
		
		if (month < 10 && day > 10) {
			return year + "-0" + month + "-" + day;
		} else if (month < 10 && day < 10) {
			return year + "-0" + month + "-0" + day;
		} else if (month > 10 && day < 10) {
			return year + "-" + month + "-0" + day;
		} else {
			return year + "-" + month + "-" + day;
		}

	}

	/**
	 * Returns the integer representation which Date is larger.
	 * 
	 * @returns the integer representation of this Date comparison
	 */
	public int compareTo(Date o) {

		// compare year
		if (o == null) {
			return 1;
		} else if (this == null) {
			return -1;
		}
		if (this.year != o.year) {
			if (this.year > o.year) {
				return 1;
			} else {
				return -1;
			}
		} else if (this.month != o.month) {
			if (this.month > o.month) {
				return 1;
			} else {
				return -1;
			}
		} else if (this.day != o.day) {
			if (this.day > o.day) {
				return 1;
			} else {
				return -1;
			}
		} else {
			return 0;
		}
	}

	/**
	 * Returns the year of this Date object.
	 * 
	 * @return the year component of this Date object
	 */
	public int getYear() {

		return year;
	}

	/**
	 * Returns the month of this Date object.
	 * 
	 * @return the month component of this Date object
	 */
	public int getMonth() {

		return month;
	}

	/**
	 * Returns the day of this Date object.
	 * 
	 * @return the day component of this Date object
	 */
	public int getDay() {

		return day;
	}

}
