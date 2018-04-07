
package com.travelport.schema.air_v42_0;

import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
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
 *     &lt;extension base="{http://www.travelport.com/schema/air_v42_0}AirBaseReq"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{http://www.travelport.com/schema/air_v42_0}AirReservationLocatorCode" minOccurs="0"/&gt;
 *         &lt;element ref="{http://www.travelport.com/schema/air_v42_0}AirPricingSolution"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="FareRuleType" type="{http://www.travelport.com/schema/air_v42_0}typeFareRuleType" default="none" /&gt;
 *       &lt;attribute name="IgnoreAvailability" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" /&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "airReservationLocatorCode",
    "airPricingSolution"
})
@XmlRootElement(name = "AirRepriceReq")
@Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:51:28-06:00", comments = "JAXB RI v2.2.11")
public class AirRepriceReq
    extends AirBaseReq
{

    @XmlElement(name = "AirReservationLocatorCode")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:51:28-06:00", comments = "JAXB RI v2.2.11")
    protected AirReservationLocatorCode airReservationLocatorCode;
    @XmlElement(name = "AirPricingSolution", required = true)
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:51:28-06:00", comments = "JAXB RI v2.2.11")
    protected AirPricingSolution airPricingSolution;
    @XmlAttribute(name = "FareRuleType")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:51:28-06:00", comments = "JAXB RI v2.2.11")
    protected TypeFareRuleType fareRuleType;
    @XmlAttribute(name = "IgnoreAvailability")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:51:28-06:00", comments = "JAXB RI v2.2.11")
    protected Boolean ignoreAvailability;

    /**
     * Gets the value of the airReservationLocatorCode property.
     * 
     * @return
     *     possible object is
     *     {@link AirReservationLocatorCode }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:51:28-06:00", comments = "JAXB RI v2.2.11")
    public AirReservationLocatorCode getAirReservationLocatorCode() {
        return airReservationLocatorCode;
    }

    /**
     * Sets the value of the airReservationLocatorCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link AirReservationLocatorCode }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:51:28-06:00", comments = "JAXB RI v2.2.11")
    public void setAirReservationLocatorCode(AirReservationLocatorCode value) {
        this.airReservationLocatorCode = value;
    }

    /**
     * Gets the value of the airPricingSolution property.
     * 
     * @return
     *     possible object is
     *     {@link AirPricingSolution }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:51:28-06:00", comments = "JAXB RI v2.2.11")
    public AirPricingSolution getAirPricingSolution() {
        return airPricingSolution;
    }

    /**
     * Sets the value of the airPricingSolution property.
     * 
     * @param value
     *     allowed object is
     *     {@link AirPricingSolution }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:51:28-06:00", comments = "JAXB RI v2.2.11")
    public void setAirPricingSolution(AirPricingSolution value) {
        this.airPricingSolution = value;
    }

    /**
     * Gets the value of the fareRuleType property.
     * 
     * @return
     *     possible object is
     *     {@link TypeFareRuleType }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:51:28-06:00", comments = "JAXB RI v2.2.11")
    public TypeFareRuleType getFareRuleType() {
        if (fareRuleType == null) {
            return TypeFareRuleType.NONE;
        } else {
            return fareRuleType;
        }
    }

    /**
     * Sets the value of the fareRuleType property.
     * 
     * @param value
     *     allowed object is
     *     {@link TypeFareRuleType }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:51:28-06:00", comments = "JAXB RI v2.2.11")
    public void setFareRuleType(TypeFareRuleType value) {
        this.fareRuleType = value;
    }

    /**
     * Gets the value of the ignoreAvailability property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:51:28-06:00", comments = "JAXB RI v2.2.11")
    public boolean isIgnoreAvailability() {
        if (ignoreAvailability == null) {
            return false;
        } else {
            return ignoreAvailability;
        }
    }

    /**
     * Sets the value of the ignoreAvailability property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:51:28-06:00", comments = "JAXB RI v2.2.11")
    public void setIgnoreAvailability(Boolean value) {
        this.ignoreAvailability = value;
    }

    /**
     * Generates a String representation of the contents of this type.
     * This is an extension method, produced by the 'ts' xjc plugin
     * 
     */
    @Override
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:51:28-06:00", comments = "JAXB RI v2.2.11")
    public String toString() {
        return ToStringBuilder.reflectionToString(this, JAXBToStringStyle.MULTI_LINE_STYLE);
    }

}
