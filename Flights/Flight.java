package flightsproject;

import static org.apache.commons.lang3.StringUtils.*;

public class Flight {
	String airline;
	String from;
	String to;
	String price;


	public Flight(String words) {
		super();
		String[] flightSplit = words.split("\\s|,|=>");
		// String[] flightSplit = substringsBetween(words, "=>", " ");

		from = flightSplit[0];
		to = flightSplit[3];
		airline = flightSplit[5];
		price = flightSplit[7];

	}


	public Flight(String airline, String from, String to, String price) {
		super();
		this.airline = airline;
		this.from = from;
		this.to = to;
		this.price = price;
	}


	public String getAirline() {
		return airline;
	}


	public void setAirline(String airline) {
		this.airline = airline;
	}


	public String getFrom() {
		return from;
	}


	public void setFrom(String from) {
		this.from = from;
	}


	public String getTo() {
		return to;
	}


	public void setTo(String to) {
		this.to = to;
	}


	public String getPrice() {
		return price;
	}


	public void setPrice(String price) {
		this.price = price;
	}

}
