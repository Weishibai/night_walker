
/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

package com.travelport.service.system_v32_0;

import java.util.logging.Logger;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 3.1.5
 * 2017-07-31T15:54:47.937-06:00
 * Generated source version: 3.1.5
 * 
 */

@javax.jws.WebService(
                      serviceName = "ExternalCacheAccessService",
                      portName = "ExternalCacheAccessPort",
                      targetNamespace = "http://www.travelport.com/service/system_v32_0",
                      wsdlLocation = "file:/C:/Travelport_1/SamplesAutoMation/AutoWork/travelport-uapi-tutorial-v42/wsdl/system_v32_0/System.wsdl",
                      endpointInterface = "com.travelport.service.system_v32_0.ExternalCacheAccessPortType")
                      
public class ExternalCacheAccessPortImpl implements ExternalCacheAccessPortType {

    private static final Logger LOG = Logger.getLogger(ExternalCacheAccessPortImpl.class.getName());

    /* (non-Javadoc)
     * @see com.travelport.service.system_v32_0.ExternalCacheAccessPortType#service(com.travelport.schema.system_v32_0.ExternalCacheAccessReq  parameters )*
     */
    public com.travelport.schema.system_v32_0.ExternalCacheAccessRsp service(com.travelport.schema.system_v32_0.ExternalCacheAccessReq parameters) throws SystemFaultMessage    { 
        LOG.info("Executing operation service");
        System.out.println(parameters);
        try {
            com.travelport.schema.system_v32_0.ExternalCacheAccessRsp _return = new com.travelport.schema.system_v32_0.ExternalCacheAccessRsp();
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new SystemFaultMessage("SystemFaultMessage...");
    }

}
