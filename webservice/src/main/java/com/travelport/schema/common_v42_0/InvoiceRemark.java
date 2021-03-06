
package com.travelport.schema.common_v42_0;

import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.cxf.xjc.runtime.JAXBToStringStyle;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.travelport.com/schema/common_v42_0}typeAssociatedRemark"&gt;
 *       &lt;choice&gt;
 *         &lt;element name="AirSegmentRef" type="{http://www.travelport.com/schema/common_v42_0}typeSegmentRef" minOccurs="0"/&gt;
 *         &lt;element name="HotelReservationRef" type="{http://www.travelport.com/schema/common_v42_0}typeNonAirReservationRef" minOccurs="0"/&gt;
 *         &lt;element name="VehicleReservationRef" type="{http://www.travelport.com/schema/common_v42_0}typeNonAirReservationRef" minOccurs="0"/&gt;
 *         &lt;element name="PassiveSegmentRef" type="{http://www.travelport.com/schema/common_v42_0}typeSegmentRef" minOccurs="0"/&gt;
 *       &lt;/choice&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "airSegmentRef",
    "hotelReservationRef",
    "vehicleReservationRef",
    "passiveSegmentRef"
})
@XmlRootElement(name = "InvoiceRemark")
@Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T04:06:27-06:00", comments = "JAXB RI v2.2.11")
public class InvoiceRemark
    extends TypeAssociatedRemark
{

    @XmlElement(name = "AirSegmentRef")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T04:06:27-06:00", comments = "JAXB RI v2.2.11")
    protected TypeSegmentRef airSegmentRef;
    @XmlElement(name = "HotelReservationRef")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T04:06:27-06:00", comments = "JAXB RI v2.2.11")
    protected TypeNonAirReservationRef hotelReservationRef;
    @XmlElement(name = "VehicleReservationRef")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T04:06:27-06:00", comments = "JAXB RI v2.2.11")
    protected TypeNonAirReservationRef vehicleReservationRef;
    @XmlElement(name = "PassiveSegmentRef")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T04:06:27-06:00", comments = "JAXB RI v2.2.11")
    protected TypeSegmentRef passiveSegmentRef;

    /**
     * Gets the value of the airSegmentRef property.
     * 
     * @return
     *     possible object is
     *     {@link TypeSegmentRef }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T04:06:27-06:00", comments = "JAXB RI v2.2.11")
    public TypeSegmentRef getAirSegmentRef() {
        return airSegmentRef;
    }

    /**
     * Sets the value of the airSegmentRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link TypeSegmentRef }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T04:06:27-06:00", comments = "JAXB RI v2.2.11")
    public void setAirSegmentRef(TypeSegmentRef value) {
        this.airSegmentRef = value;
    }

    /**
     * Gets the value of the hotelReservationRef property.
     * 
     * @return
     *     possible object is
     *     {@link TypeNonAirReservationRef }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T04:06:27-06:00", comments = "JAXB RI v2.2.11")
    public TypeNonAirReservationRef getHotelReservationRef() {
        return hotelReservationRef;
    }

    /**
     * Sets the value of the hotelReservationRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link TypeNonAirReservationRef }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T04:06:27-06:00", comments = "JAXB RI v2.2.11")
    public void setHotelReservationRef(TypeNonAirReservationRef value) {
        this.hotelReservationRef = value;
    }

    /**
     * Gets the value of the vehicleReservationRef property.
     * 
     * @return
     *     possible object is
     *     {@link TypeNonAirReservationRef }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T04:06:27-06:00", comments = "JAXB RI v2.2.11")
    public TypeNonAirReservationRef getVehicleReservationRef() {
        return vehicleReservationRef;
    }

    /**
     * Sets the value of the vehicleReservationRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link TypeNonAirReservationRef }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T04:06:27-06:00", comments = "JAXB RI v2.2.11")
    public void setVehicleReservationRef(TypeNonAirReservationRef value) {
        this.vehicleReservationRef = value;
    }

    /**
     * Gets the value of the passiveSegmentRef property.
     * 
     * @return
     *     possible object is
     *     {@link TypeSegmentRef }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T04:06:27-06:00", comments = "JAXB RI v2.2.11")
    public TypeSegmentRef getPassiveSegmentRef() {
        return passiveSegmentRef;
    }

    /**
     * Sets the value of the passiveSegmentRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link TypeSegmentRef }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T04:06:27-06:00", comments = "JAXB RI v2.2.11")
    public void setPassiveSegmentRef(TypeSegmentRef value) {
        this.passiveSegmentRef = value;
    }

    /**
     * Generates a String representation of the contents of this type.
     * This is an extension method, produced by the 'ts' xjc plugin
     * 
     */
    @Override
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T04:06:27-06:00", comments = "JAXB RI v2.2.11")
    public String toString() {
        return ToStringBuilder.reflectionToString(this, JAXBToStringStyle.MULTI_LINE_STYLE);
    }

}
