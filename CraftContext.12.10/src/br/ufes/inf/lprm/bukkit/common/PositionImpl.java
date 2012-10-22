package br.ufes.inf.lprm.bukkit.common;

import br.ufes.inf.lprm.generated.Position;

public class PositionImpl extends Position{

	private static final long serialVersionUID = 1L;

	public PositionImpl() {}
	
	public PositionImpl(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	@Override
	public double getX() {
		return x;
	}

	@Override
	public double getY() {
		return y;
	}

	@Override
	public double getZ() {
		return z;
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Position)) return false;
		
		Position position = (Position) obj;
		if(this.x != position.getX()) return false;
		if(this.y != position.getY()) return false;
		if(this.z != position.getZ()) return false;
		
		return true;
	}
	
}
