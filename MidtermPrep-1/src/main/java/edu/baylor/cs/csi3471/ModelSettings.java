/*
 * Author: Harrison Hassler
 * Course: CSI 3471
 * Assignment: Practicum Redo
 * File: ModelSettings.java
 * Description: This implements the model
 * settings class and holds the unique models
 * of each make name. It is also associated with an
 * MPG class for each model.
 */

package edu.baylor.cs.csi3471;

import java.util.Objects;

public class ModelSettings {

	public static class MPG {
		private int city;
		private int avg;
		private int highway;

		public int getCity() {
			return city;
		}

		public void setCity(int city) {
			this.city = city;
		}

		public int getAvg() {
			return avg;
		}

		public void setAvg(int avg) {
			this.avg = avg;
		}

		public int getHighway() {
			return highway;
		}

		public void setHighway(int highway) {
			this.highway = highway;
		}

		public MPG(int city, int avg, int highway) {
			this.city = city;
			this.avg = avg;
			this.highway = highway;
		}

		@Override
		public String toString() {
			return "MPG{" +
					"city=" + city +
					", avg=" + avg +
					", highway=" + highway +
					'}';
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;
			MPG mpg = (MPG) o;
			return city == mpg.city && avg == mpg.avg && highway == mpg.highway;
		}

		@Override
		public int hashCode() {
			return Objects.hash(city, avg, highway);
		}
	}

	private MPG mpg = null;

	private double engineDisplacement;
	private String modelName;
	private String vClass;
	private String transmission;
	private int modelYear;
	private String fuelType;
	private int engineCylinders;
	private static int lastId = 0;
	private int id;


	public MPG getMpg() {
		return mpg;
	}

	public void setMpg(MPG mpg) {
		this.mpg = mpg;
	}

	public double getEngineDisplacement() {
		return engineDisplacement;
	}

	public void setEngineDisplacement(double engineDisplacement) {
		this.engineDisplacement = engineDisplacement;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getvClass() {
		return vClass;
	}

	public void setvClass(String vClass) {
		this.vClass = vClass;
	}

	public String getTransmission() {
		return transmission;
	}

	public void setTransmission(String transmission) {
		this.transmission = transmission;
	}

	public int getModelYear() {
		return modelYear;
	}

	public void setModelYear(int modelYear) {
		this.modelYear = modelYear;
	}

	public String getFuelType() {
		return fuelType;
	}

	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}

	public int getEngineCylinders() {
		return engineCylinders;
	}

	public void setEngineCylinders(int engineCylinders) {
		this.engineCylinders = engineCylinders;
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ModelSettings that = (ModelSettings) o;
		return Double.compare(engineDisplacement, that.engineDisplacement) == 0 && modelYear == that.modelYear && engineCylinders == that.engineCylinders && Objects.equals(mpg, that.mpg) && Objects.equals(modelName, that.modelName) && Objects.equals(vClass, that.vClass) && Objects.equals(transmission, that.transmission) && Objects.equals(fuelType, that.fuelType);
	}

	@Override
	public int hashCode() {
		return Objects.hash(mpg, engineDisplacement, modelName, vClass, transmission, modelYear, fuelType, engineCylinders);
	}

	@Override
	public String toString() {
		return "ModelSettings{" +
				"id=" + id +
				"mpg=" + mpg +
				", engineDisplacement=" + engineDisplacement +
				", modelName='" + modelName + '\'' +
				", vClass='" + vClass + '\'' +
				", transmission='" + transmission + '\'' +
				", modelYear=" + modelYear +
				", fuelType='" + fuelType + '\'' +
				", engineCylinders=" + engineCylinders +
				'}';
	}

	public ModelSettings(String[] line) {
		this.id = ++lastId;

		this.mpg = new MPG(Integer.parseInt(line[0]), Integer.parseInt(line[1]), Integer.parseInt(line[5]));
		this.engineCylinders = Integer.parseInt(line[2]);
		this.engineDisplacement = Double.parseDouble(line[3]);
		this.fuelType = line[4];
		this.modelName = line[7];
		this.transmission = line[8];
		this.vClass = line[9];
		this.modelYear = Integer.parseInt(line[10]);


	}


}
