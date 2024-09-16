/*
 * Author: Harrison Hassler
 * Course: CSI 3471
 * Assignment: Practicum Redo
 * File: Make.java
 * Description: This file implements the make class
 * which has a make name, id and set of ModeSettings.
 */

package edu.baylor.cs.csi3471;

import java.util.*;

import java.util.Collection;

public class Make {
	private String makeName;
	private int id;
	private static int lastId = 0;
	private Set<ModelSettings> modelSettingSet;

	public Make(String[] line) {
		this.id = ++lastId;
		makeName = line[6];
		modelSettingSet = new HashSet<>();
		modelSettingSet.add(new ModelSettings((line)));
	}

	public String getMakeName() {
		return makeName;
	}

	public void setMakeName(String makeName) {
		this.makeName = makeName;
	}

	public Set<ModelSettings> getModelSettings() {
		return modelSettingSet;
	}

	public void setModelSettingSet(Set<ModelSettings> modelSettingSet) {
		this.modelSettingSet = modelSettingSet;
	}

	private Set<ModelSettings> sortModelSettings() {
		Set<ModelSettings> sortedModelSettings = new TreeSet<>(Comparator
				.comparingInt(ModelSettings::getModelYear)
				.thenComparing(ModelSettings::getTransmission)
				.thenComparingDouble(ModelSettings::getEngineDisplacement));

		sortedModelSettings.addAll(modelSettingSet);
		return sortedModelSettings;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Make: ").append(makeName);
		sb.append(" ModelSettings:\n");

		Set<ModelSettings> sortedModelSettings = sortModelSettings();

		for (ModelSettings modelSettings : sortedModelSettings) {
			sb.append(modelSettings.toString()).append("\n");
		}
		return sb.toString();
	}

	public Collection<Make> creatorPattern(String[] line, Collection<Make> makes) {
		Make existingMake = null;
		for (Make make : makes) {
			if (make.getMakeName().equalsIgnoreCase(line[6])) {
				existingMake = make;
				break;
			}
		}
		if (existingMake == null) {
			Make newMake = new Make(line);
			makes.add(newMake);
		}
		else{
			ModelSettings newModelSettings = new ModelSettings(line);
			existingMake.getModelSettings().remove(newModelSettings); 
			existingMake.getModelSettings().add(newModelSettings);

		}
		return makes;
	}
}

