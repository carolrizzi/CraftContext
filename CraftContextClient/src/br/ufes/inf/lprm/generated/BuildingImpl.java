package br.ufes.inf.lprm.generated;

import br.ufes.inf.lprm.generated.Building;
import br.ufes.inf.lprm.generated.Position;

public class BuildingImpl extends Building{
	private static final long serialVersionUID = 1L;
	
	public BuildingImpl() {}
	
	public BuildingImpl(String name, String address, String type, double height, Position center, double xWidth, double zWidth) {
		this.name = name;
		this.address = address;
		this.type = type;
		this.height = height;
		this.center = center;
		this.xWidth = xWidth;
		this.zWidth = zWidth;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getAddress() {
		return address;
	}

	@Override
	public String getType() {
		return type;
	}

	@Override
	public double getHeight() {
		return height;
	}

	@Override
	public Position getCenter() {
		return center;
	}

	@Override
	public double getXWidth() {
		return xWidth;
	}

	@Override
	public double getZWidth() {
		return zWidth;
	}

}
