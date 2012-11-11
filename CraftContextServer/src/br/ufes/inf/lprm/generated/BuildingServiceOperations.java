package br.ufes.inf.lprm.generated;


/**
* br/ufes/inf/lprm/generated/BuildingServiceOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from building.idl
* Sunday, October 21, 2012 10:12:54 PM BRST
*/


// --- SERVICES --- //
public interface BuildingServiceOperations 
{
  br.ufes.inf.lprm.generated.Building[] getExistingBuildings () throws br.ufes.inf.lprm.generated.BuildingException;
  br.ufes.inf.lprm.generated.Building getBuilding (String buildingName) throws br.ufes.inf.lprm.generated.BuildingNotFound;
  br.ufes.inf.lprm.generated.Building[] getBuildingByType (String type) throws br.ufes.inf.lprm.generated.BuildingException;
  void listBuildings (String playerName) throws br.ufes.inf.lprm.generated.BuildingException;
  void addBuilding (String name, String type, String address, double height, double xWidth, double zWidth, double xCenter, double yCenter, double zCenter) throws br.ufes.inf.lprm.generated.BuildingException, br.ufes.inf.lprm.generated.BuildingAlreadyExists;
  void removeBuilding (String buildingName, boolean physicalDestruction) throws br.ufes.inf.lprm.generated.BuildingException, br.ufes.inf.lprm.generated.BuildingNotFound;
} // interface BuildingServiceOperations