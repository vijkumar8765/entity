package uk.gov.scotland.afrc.applications.bean;

import java.util.Date;

public class FilterDao {

		private Long landParcelId;
	    private Long schemeId;
	    private Long sectionName;
	    private Long errorType;
	    private Date fromDate;
	    private Date toDate;
		
	    public FilterDao(){}
	    
	    public FilterDao(Long landParcelId, Long schemeId, Long sectionName,
				Long errorType, Date fromDate, Date toDate) {
			super();
			this.landParcelId = landParcelId;
			this.schemeId = schemeId;
			this.sectionName = sectionName;
			this.errorType = errorType;
			this.fromDate = fromDate;
			this.toDate = toDate;
		}
		/**
		 * @return the landParcelId
		 */
		public Long getLandParcelId() {
			return landParcelId;
		}
		/**
		 * @param landParcelId the landParcelId to set
		 */
		public void setLandParcelId(Long landParcelId) {
			this.landParcelId = landParcelId;
		}
		/**
		 * @return the schemeId
		 */
		public Long getSchemeId() {
			return schemeId;
		}
		/**
		 * @param schemeId the schemeId to set
		 */
		public void setSchemeId(Long schemeId) {
			this.schemeId = schemeId;
		}
		/**
		 * @return the sectionName
		 */
		public Long getSectionName() {
			return sectionName;
		}
		/**
		 * @param sectionName the sectionName to set
		 */
		public void setSectionName(Long sectionName) {
			this.sectionName = sectionName;
		}
		/**
		 * @return the errorType
		 */
		public Long getErrorType() {
			return errorType;
		}
		/**
		 * @param errorType the errorType to set
		 */
		public void setErrorType(Long errorType) {
			this.errorType = errorType;
		}
		/**
		 * @return the fromDate
		 */
		public Date getFromDate() {
			return fromDate;
		}
		/**
		 * @param fromDate the fromDate to set
		 */
		public void setFromDate(Date fromDate) {
			this.fromDate = fromDate;
		}
		/**
		 * @return the toDate
		 */
		public Date getToDate() {
			return toDate;
		}
		/**
		 * @param toDate the toDate to set
		 */
		public void setToDate(Date toDate) {
			this.toDate = toDate;
		}
		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "FilterDao [landParcelId=" + landParcelId + ", schemeId="
					+ schemeId + ", sectionName=" + sectionName
					+ ", errorType=" + errorType + ", fromDate=" + fromDate
					+ ", toDate=" + toDate + "]";
		}
	    
}
