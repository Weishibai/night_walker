
package com.travelport.service.universal_v42_0;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 3.1.5
 * 2017-07-31T15:56:04.581-06:00
 * Generated source version: 3.1.5
 */

@WebFault(name = "AvailabilityErrorInfo", targetNamespace = "http://www.travelport.com/schema/air_v42_0")
public class AvailabilityFaultMessage extends Exception {
    
    private com.travelport.schema.air_v42_0.AvailabilityErrorInfo availabilityErrorInfo;

    public AvailabilityFaultMessage() {
        super();
    }
    
    public AvailabilityFaultMessage(String message) {
        super(message);
    }
    
    public AvailabilityFaultMessage(String message, Throwable cause) {
        super(message, cause);
    }

    public AvailabilityFaultMessage(String message, com.travelport.schema.air_v42_0.AvailabilityErrorInfo availabilityErrorInfo) {
        super(message);
        this.availabilityErrorInfo = availabilityErrorInfo;
    }

    public AvailabilityFaultMessage(String message, com.travelport.schema.air_v42_0.AvailabilityErrorInfo availabilityErrorInfo, Throwable cause) {
        super(message, cause);
        this.availabilityErrorInfo = availabilityErrorInfo;
    }

    public com.travelport.schema.air_v42_0.AvailabilityErrorInfo getFaultInfo() {
        return this.availabilityErrorInfo;
    }
}
