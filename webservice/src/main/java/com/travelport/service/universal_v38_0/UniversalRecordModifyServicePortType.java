package com.travelport.service.universal_v38_0;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 3.1.5
 * 2016-09-13T11:38:38.286-06:00
 * Generated source version: 3.1.5
 * 
 */
@WebService(targetNamespace = "http://www.travelport.com/service/universal_v38_0", name = "UniversalRecordModifyServicePortType")
@XmlSeeAlso({com.travelport.schema.universal_v38_0.ObjectFactory.class, com.travelport.schema.hotel_v38_0.ObjectFactory.class, com.travelport.schema.cruise_v38_0.ObjectFactory.class, com.travelport.schema.common_v38_0.ObjectFactory.class, com.travelport.schema.rail_v38_0.ObjectFactory.class, com.travelport.schema.air_v38_0.ObjectFactory.class, com.travelport.schema.passive_v38_0.ObjectFactory.class, com.travelport.schema.vehicle_v38_0.ObjectFactory.class, com.travelport.soa.common.security.sessioncontext_v1.ObjectFactory.class})
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface UniversalRecordModifyServicePortType {

    @WebResult(name = "UniversalRecordModifyRsp", targetNamespace = "http://www.travelport.com/schema/universal_v38_0", partName = "result")
    @WebMethod(action = "http://localhost:8080/kestrel/UniversalRecordService")
    public com.travelport.schema.universal_v38_0.UniversalRecordModifyRsp service(
        @WebParam(partName = "parameters", name = "UniversalRecordModifyReq", targetNamespace = "http://www.travelport.com/schema/universal_v38_0")
        com.travelport.schema.universal_v38_0.UniversalRecordModifyReq parameters,
        @WebParam(partName = "sessionContext", name = "SessionContext", targetNamespace = "http://www.travelport.com/soa/common/security/SessionContext_v1", header = true)
        com.travelport.soa.common.security.sessioncontext_v1.SessionContext sessionContext,
        @WebParam(partName = "supportedVersions", name = "SupportedVersions", targetNamespace = "http://www.travelport.com/schema/universal_v38_0", header = true)
        com.travelport.schema.universal_v38_0.SupportedVersions supportedVersions
    ) throws AvailabilityFaultMessage, UniversalModifyFaultMessage, UniversalRecordFaultMessage;
}
