
package com.travelport.schema.vehicle_v42_0;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import com.travelport.schema.common_v42_0.BaseSearchRsp;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.cxf.xjc.runtime.JAXBToStringStyle;


/**
 * Base response of vehicle availability response.
 * 
 * <p>Java class for BaseVehicleSearchAvailabilityRsp complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BaseVehicleSearchAvailabilityRsp"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.travelport.com/schema/common_v42_0}BaseSearchRsp"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{http://www.travelport.com/schema/vehicle_v42_0}VehicleDateLocation"/&gt;
 *         &lt;element ref="{http://www.travelport.com/schema/vehicle_v42_0}Vehicle" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BaseVehicleSearchAvailabilityRsp", propOrder = {
    "vehicleDateLocation",
    "vehicle"
})
@XmlSeeAlso({
    VehicleUpsellSearchAvailabilityRsp.class,
    VehicleSearchAvailabilityRsp.class
})
@Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:56:32-06:00", comments = "JAXB RI v2.2.11")
public class BaseVehicleSearchAvailabilityRsp
    extends BaseSearchRsp
{

    @XmlElement(name = "VehicleDateLocation", required = true)
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:56:32-06:00", comments = "JAXB RI v2.2.11")
    protected VehicleDateLocation vehicleDateLocation;
    @XmlElement(name = "Vehicle")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:56:32-06:00", comments = "JAXB RI v2.2.11")
    protected List<Vehicle> vehicle;

    /**
     * Gets the value of the vehicleDateLocation property.
     * 
     * @return
     *     possible object is
     *     {@link VehicleDateLocation }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:56:32-06:00", comments = "JAXB RI v2.2.11")
    public VehicleDateLocation getVehicleDateLocation() {
        return vehicleDateLocation;
    }

    /**
     * Sets the value of the vehicleDateLocation property.
     * 
     * @param value
     *     allowed object is
     *     {@link VehicleDateLocation }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:56:32-06:00", comments = "JAXB RI v2.2.11")
    public void setVehicleDateLocation(VehicleDateLocation value) {
        this.vehicleDateLocation = value;
    }

    /**
     * Gets the value of the vehicle property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the vehicle property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getVehicle().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Vehicle }
     * 
     * 
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:56:32-06:00", comments = "JAXB RI v2.2.11")
    public List<Vehicle> getVehicle() {
        if (vehicle == null) {
            vehicle = new ArrayList<Vehicle>();
        }
        return this.vehicle;
    }

    /**
     * Generates a String representation of the contents of this type.
     * This is an extension method, produced by the 'ts' xjc plugin
     * 
     */
    @Override
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:56:32-06:00", comments = "JAXB RI v2.2.11")
    public String toString() {
        return ToStringBuilder.reflectionToString(this, JAXBToStringStyle.MULTI_LINE_STYLE);
    }

}
