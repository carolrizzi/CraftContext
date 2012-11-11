package br.ufes.inf.lprm.bukkit.building;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;

import br.ufes.inf.lprm.bukkit.Server;
import br.ufes.inf.lprm.bukkit.common.PositionImpl;
import br.ufes.inf.lprm.generated.Building;
import br.ufes.inf.lprm.generated.BuildingAlreadyExists;
import br.ufes.inf.lprm.generated.BuildingException;
import br.ufes.inf.lprm.generated.BuildingNotFound;
import br.ufes.inf.lprm.generated.BuildingServicePOA;
import br.ufes.inf.lprm.generated.Position;

public class BuildingServiceImpl extends BuildingServicePOA{
	
	@Override
	public Building[] getExistingBuildings() throws BuildingException {
		try{
			Building[] buildings = new BuildingImpl[BuildingDataBase.buildings.size()]; 
			int count = 0;
	
			for(Building building : BuildingDataBase.buildings.values()){
				buildings[count++] = building;
			}
			
			return buildings;
		}catch (Exception e) {
			throw new BuildingException(BuildingProperties.Errors.SERVICE_01);
		}
	}

	@Override
	public Building getBuilding(String buildingName) throws BuildingNotFound {
		Building building = BuildingDataBase.buildings.get(buildingName);
		if (building == null) throw new BuildingNotFound(buildingName);
		return building;
	}

	@Override
	public Building[] getBuildingByType(String type) throws BuildingException {
		try{
			ArrayList<Building> buildingsArray = new ArrayList<Building>();
			
			for(Building building : BuildingDataBase.buildings.values()){
				if(building.getType().equals(type))
					buildingsArray.add(building);
			}
			
			Building[] buildings = new BuildingImpl[buildingsArray.size()]; 
			int count = 0;
			
			for(Building building : buildingsArray){
				buildings[count++] = building;
			}
					
			return buildings;
		}catch (Exception e) {
			throw new BuildingException(BuildingProperties.Errors.SERVICE_02);
		}
	}

	@Override
	public void listBuildings(String playerName) throws BuildingException {
		try{
			Player player = Bukkit.getServer().getPlayer(playerName);
			if(BuildingDataBase.buildings.size() <= 0){
				player.sendMessage("There is no buildings.");
				return;
			}
			
			String msg = "Existent Buildings: ";
			
			for(Building building : BuildingDataBase.buildings.values()){
				msg += building.getName() + ", ";
			}
			msg += "$%$";
			msg = msg.replace(", $%$", ".");
			
			player.sendMessage(msg);
		}catch (Exception e) {
			throw new BuildingException(BuildingProperties.Errors.SERVICE_03);
		}
	}

	@Override
	public void addBuilding(String name, String type, String address, double height, double xWidth, double zWidth, double xCenter, double yCenter, double zCenter) throws BuildingException, BuildingAlreadyExists {
		if(BuildingDataBase.buildings.get(name) != null) throw new BuildingAlreadyExists(name);
		
		try{
			Position center = new PositionImpl(xCenter, yCenter, zCenter);
			Building building = new BuildingImpl(name, address, type, height, center, xWidth, zWidth);
			BuildingDataBase.buildings.put(name, building);
			
			destructBuilding(building);
			wireframeBuilding(building);
		}catch (Exception e) {
			throw new BuildingException(BuildingProperties.Errors.SERVICE_04);
		}
	}

	@Override
	public void removeBuilding(String buildingName, boolean physicalDestruction) throws BuildingException, BuildingNotFound {
		Building building = BuildingDataBase.buildings.remove(buildingName);
		if (building == null) throw new BuildingNotFound(buildingName);
		
		try{
			if(physicalDestruction) destructBuilding(building);
		}catch (Exception e) {
			throw new BuildingException(BuildingProperties.Errors.SERVICE_05);
		}
	}

	private void wireframeBuilding(Building building){
		Position center = building.getCenter();
		Location location = new Location(Server.world, center.getX(), center.getY(), center.getZ());
		Block block = location.getBlock();
		int xCenter = (int) block.getX();
		int yCenter = (int) block.getY();
		int zCenter = (int) block.getZ(); 
		
		block.setType(org.bukkit.Material.SIGN_POST);
		
		Sign sign = (Sign) block.getState();
		sign.setLine(1, "Building Name:");
		sign.setLine(2, building.getName());
		sign.update();
		
		int xWidth = (int) building.getXWidth();
		int zWidth = (int) building.getZWidth();
		
		wireframeSurface(Material.STONE, xCenter, yCenter, zCenter, xWidth, zWidth);
		wireframeSurface(Material.STONE, xCenter, yCenter + (int)building.getHeight(), zCenter, xWidth, zWidth);
	}
	
	private void wireframeSurface(Material material, int xCenter, int yCenter, int zCenter, int xWidth, int zWidth) {
		try{
			Server.world.getBlockAt(xCenter - xWidth, yCenter, zCenter - zWidth).setType(material);
			Server.world.getBlockAt(xCenter + xWidth, yCenter, zCenter - zWidth).setType(material);
			Server.world.getBlockAt(xCenter - xWidth, yCenter, zCenter + zWidth).setType(material);
			Server.world.getBlockAt(xCenter + xWidth, yCenter, zCenter + zWidth).setType(material);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void destructBuilding(Building building){
		Position center = building.getCenter();
		Location location = new Location(Server.world, center.getX(), center.getY(), center.getZ());
		Block block = location.getBlock();
		int xCenter = (int) block.getX();
		int yCenter = (int) block.getY();
		int zCenter = (int) block.getZ(); 

		int xBegin = (int) (xCenter - building.getXWidth());
		int xEnd = (int) (xCenter + building.getXWidth());
		int yBegin = (int) yCenter;
		int yEnd = (int) (yCenter + building.getHeight());
		int zBegin = (int) (zCenter - building.getZWidth());
		int zEnd = (int) (zCenter + building.getZWidth());
		
		for(int x = xBegin; x <= xEnd; x++){
			for(int y = yBegin; y <= yEnd; y++){
				for(int z = zBegin; z <= zEnd; z++){
					Server.world.getBlockAt(x, y, z).setType(Material.AIR);
				}
			}
		}
	}
	
}
