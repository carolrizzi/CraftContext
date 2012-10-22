package br.ufes.inf.lprm.bukkit.common;

import br.ufes.inf.lprm.generated.Area;
import br.ufes.inf.lprm.generated.Position;

public class AreaImpl extends Area{

	private static final long serialVersionUID = 1L;

	public AreaImpl() {}
	
	public AreaImpl(Position center, double xWidth, double zWidth) {
		this.center = center;
		this.xWidth = xWidth;
		this.zWidth = zWidth;
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
