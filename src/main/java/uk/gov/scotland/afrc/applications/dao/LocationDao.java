/*
 * Project : AFRC Futures
 * Copyright (C) 2013 Scottish Government AFRC Programme
 * AFRC PROPRIETARY/CONFIDENTIAL
 */
package uk.gov.scotland.afrc.applications.dao;

import java.util.Date;

import uk.gov.scotland.afrc.applications.entity.base.EntityManagerBase;


public interface LocationDao extends EntityManagerBase<LocationDao.Location, Long> {
	public Location getLocationByBrn(Long id,long brn);
	public Location getLocation(Long id,long mainLocationFlag);
	class Location {
		
		private String locationName;
		private boolean mainLocation;
		private String locationCode;
		private long brn;
		private Date occupancyEndDate;
		private long locationId;
		
		public long getBrn() {
			return brn;
		}

		public void setBrn(long brn) {
			this.brn = brn;
		}

		public String getLocationName() {
			return locationName;
		}
		
		public void setLocationName(String locationName) {
			this.locationName = locationName;
		}
		
		public boolean isMainLocation() {
			return mainLocation;
		}
		
		public void setMainLocation(boolean mainLocation) {
			this.mainLocation = mainLocation;
		}
	
		public String getLocationCode() {
			return locationCode;
		}

		public void setLocationCode(String locationCode) {
			this.locationCode = locationCode;
		}

		public Date getOccupancyEndDate() {
			return occupancyEndDate;
		}

		public void setOccupancyEndDate(Date occupancyEndDate) {
			this.occupancyEndDate = occupancyEndDate;
		}

		public Long getLocationId() {
			return locationId;
		}

		public void setLocationId(long locationId) {
			this.locationId = locationId;
		}

		public Location(String locationName, boolean mainLocation,String locationCode) {
			super();
			this.locationName = locationName;
			this.mainLocation = mainLocation;
			this.locationCode = locationCode;
			
		}
		
		public Location(String locationName, boolean mainLocation,String locationCode,Date occupancyEndDate,long locationId) {
			super();
			this.locationName = locationName;
			this.mainLocation = mainLocation;
			this.locationCode = locationCode;
			this.occupancyEndDate = occupancyEndDate;
			this.locationId = locationId;
	}
    }

    LocationDao.Location findByCode(String locationCode);

}
