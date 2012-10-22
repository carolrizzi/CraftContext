package br.ufes.inf.lprm.bukkit.building.command.data;

import br.ufes.inf.lprm.generated.Area;
import br.ufes.inf.lprm.generated.BuildingAddCommandDataPOA;

public class BuildingAddCommandDataImpl extends BuildingAddCommandDataPOA{

	private String playerName;
	private String buildingName;
	private String type;
	private String address;
	private Area area;
	private double height;
	
	public BuildingAddCommandDataImpl(String playerName, String buildingName, String type, String address, Area area, double height) {
		this.playerName = playerName;
		this.buildingName = buildingName;
		this.type = type;
		this.address = address;
		this.area = area;
		this.height = height;
	}

	@Override
	public String getPlayerName() {
		return playerName;
	}

	@Override
	public String getBuildingName() {
		return buildingName;
	}

	@Override
	public String getBuildingType() {
		return type;
	}

	@Override
	public String getBuildingAddress() {
		return address;
	}

	@Override
	public Area getArea() {
		return area;
	}

	@Override
	public double getHeight() {
		return height;
	}

}
