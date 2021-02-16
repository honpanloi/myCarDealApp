package com.app.model;

public class Car {
	private long id;
	private String vin;
	private String manufacturer;
	private String model;
	private int year_made;
	private int mileage;
	private int city_mpg;
	private int highway_mpg;
	private String fuel_type;
	private String drive_train;
	private String ex_color;
	private String in_color;
	private String transmission;
	private long owner_id;
	private String park_location;
	private String status;
	private double offer_desired;
	private long loan_id;
	private long add_by_employee;
	private String date_added;
	
	public long getAdd_by_employee() {
		return add_by_employee;
	}
	public void setAdd_by_employee(long add_by_employee) {
		this.add_by_employee = add_by_employee;
	}
	public String getDate_added() {
		return date_added;
	}
	public void setDate_added(String date_added) {
		this.date_added = date_added;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getVin() {
		return vin;
	}
	public void setVin(String vin) {
		this.vin = vin;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public int getYear_made() {
		return year_made;
	}
	public void setYear_made(int year_made) {
		this.year_made = year_made;
	}
	public int getMileage() {
		return mileage;
	}
	public void setMileage(int mileage) {
		this.mileage = mileage;
	}
	public int getCity_mpg() {
		return city_mpg;
	}
	public void setCity_mpg(int city_mpg) {
		this.city_mpg = city_mpg;
	}
	public int getHighway_mpg() {
		return highway_mpg;
	}
	public void setHighway_mpg(int highway_mpg) {
		this.highway_mpg = highway_mpg;
	}
	public String getFuel_type() {
		return fuel_type;
	}
	public void setFuel_type(String fuel_type) {
		this.fuel_type = fuel_type;
	}
	public String getDrive_train() {
		return drive_train;
	}
	public void setDrive_train(String drive_train) {
		this.drive_train = drive_train;
	}
	public String getEx_color() {
		return ex_color;
	}
	public void setEx_color(String ex_color) {
		this.ex_color = ex_color;
	}
	public String getIn_color() {
		return in_color;
	}
	public void setIn_color(String in_color) {
		this.in_color = in_color;
	}
	public String getTransmission() {
		return transmission;
	}
	public void setTransmission(String transmission) {
		this.transmission = transmission;
	}
	public long getOwner_id() {
		return owner_id;
	}
	public void setOwner_id(long owner_id) {
		this.owner_id = owner_id;
	}
	public String getPark_location() {
		return park_location;
	}
	public void setPark_location(String park_location) {
		this.park_location = park_location;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public double getOffer_desired() {
		return offer_desired;
	}
	public void setOffer_desired(double offer_desired) {
		this.offer_desired = offer_desired;
	}
	public long getLoan_id() {
		return loan_id;
	}
	public void setLoan_id(long loan_id) {
		this.loan_id = loan_id;
	}
	
	public Car() {}

	public Car(long id, String vin, String manufacturer, String model, int year_made, int mileage, int city_mpg,
			int highway_mpg, String fuel_type, String drive_train, String ex_color, String in_color,
			String transmission, long owner_id, String park_location, String status, double offer_desired, long loan_id,
			long add_by_employee, String date_added) {
		super();
		this.id = id;
		this.vin = vin;
		this.manufacturer = manufacturer;
		this.model = model;
		this.year_made = year_made;
		this.mileage = mileage;
		this.city_mpg = city_mpg;
		this.highway_mpg = highway_mpg;
		this.fuel_type = fuel_type;
		this.drive_train = drive_train;
		this.ex_color = ex_color;
		this.in_color = in_color;
		this.transmission = transmission;
		this.owner_id = owner_id;
		this.park_location = park_location;
		this.status = status;
		this.offer_desired = offer_desired;
		this.loan_id = loan_id;
		this.add_by_employee = add_by_employee;
		this.date_added = date_added;
	}

	
	
	@Override
	public String toString() {
		return "Car [id=" + id + ", vin=" + vin + ", manufacturer=" + manufacturer + ", model=" + model + ", year_made="
				+ year_made + ", mileage=" + mileage + ", city_mpg=" + city_mpg + ", highway_mpg=" + highway_mpg
				+ ", fuel_type=" + fuel_type + ", drive_train=" + drive_train + ", ex_color=" + ex_color + ", in_color="
				+ in_color + ", transmission=" + transmission + ", owner_id=" + owner_id + ", park_location="
				+ park_location + ", status=" + status + ", offer_desired=" + offer_desired + ", loan_id=" + loan_id
				+ ", add_by_employee=" + add_by_employee + ", date_added=" + date_added + "]";
	}
	
	public String getBasicInfo() {
		return "ID:"+id +" "+ manufacturer +" "+ model +" "+ year_made +" "+ ex_color  +
				" Mileage:"+ mileage +" "+ status +" $"+ offer_desired +
				" Location:"+park_location;
	}
}
