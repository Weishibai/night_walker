
package com.travelport.schema.cruise_v38_0;

import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import com.sun.xml.bind.Locatable;
import com.sun.xml.bind.annotation.XmlLocation;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.cxf.xjc.runtime.JAXBToStringStyle;
import org.xml.sax.Locator;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;attribute name="PickUpLocation" type="{http://www.travelport.com/schema/common_v38_0}StringLength1to5" /&gt;
 *       &lt;attribute name="PickUpTime" type="{http://www.w3.org/2001/XMLSchema}time" /&gt;
 *       &lt;attribute name="PickUpDescription" type="{http://www.travelport.com/schema/common_v38_0}StringLength1to30" /&gt;
 *       &lt;attribute name="PickUpCarrier" type="{http://www.travelport.com/schema/common_v38_0}typeCarrier" /&gt;
 *       &lt;attribute name="PickUpFlightNumber" type="{http://www.travelport.com/schema/common_v38_0}typeFlightNumber" /&gt;
 *       &lt;attribute name="ReturnLocation" type="{http://www.travelport.com/schema/common_v38_0}StringLength1to5" /&gt;
 *       &lt;attribute name="ReturnTime" type="{http://www.w3.org/2001/XMLSchema}time" /&gt;
 *       &lt;attribute name="ReturnDescription" type="{http://www.travelport.com/schema/common_v38_0}StringLength1to30" /&gt;
 *       &lt;attribute name="ReturnCarrier" type="{http://www.travelport.com/schema/common_v38_0}typeCarrier" /&gt;
 *       &lt;attribute name="ReturnFlightNumber" type="{http://www.travelport.com/schema/common_v38_0}typeFlightNumber" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "OptionJourneyDetails")
@Generated(value = "com.sun.tools.xjc.Driver", date = "2016-09-13T11:38:31-06:00", comments = "JAXB RI v2.2.11")
public class OptionJourneyDetails
    implements Locatable
{

    @XmlAttribute(name = "PickUpLocation")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2016-09-13T11:38:31-06:00", comments = "JAXB RI v2.2.11")
    protected String pickUpLocation;
    @XmlAttribute(name = "PickUpTime")
    @XmlSchemaType(name = "time")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2016-09-13T11:38:31-06:00", comments = "JAXB RI v2.2.11")
    protected XMLGregorianCalendar pickUpTime;
    @XmlAttribute(name = "PickUpDescription")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2016-09-13T11:38:31-06:00", comments = "JAXB RI v2.2.11")
    protected String pickUpDescription;
    @XmlAttribute(name = "PickUpCarrier")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2016-09-13T11:38:31-06:00", comments = "JAXB RI v2.2.11")
    protected String pickUpCarrier;
    @XmlAttribute(name = "PickUpFlightNumber")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2016-09-13T11:38:31-06:00", comments = "JAXB RI v2.2.11")
    protected String pickUpFlightNumber;
    @XmlAttribute(name = "ReturnLocation")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2016-09-13T11:38:31-06:00", comments = "JAXB RI v2.2.11")
    protected String returnLocation;
    @XmlAttribute(name = "ReturnTime")
    @XmlSchemaType(name = "time")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2016-09-13T11:38:31-06:00", comments = "JAXB RI v2.2.11")
    protected XMLGregorianCalendar returnTime;
    @XmlAttribute(name = "ReturnDescription")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2016-09-13T11:38:31-06:00", comments = "JAXB RI v2.2.11")
    protected String returnDescription;
    @XmlAttribute(name = "ReturnCarrier")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2016-09-13T11:38:31-06:00", comments = "JAXB RI v2.2.11")
    protected String returnCarrier;
    @XmlAttribute(name = "ReturnFlightNumber")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2016-09-13T11:38:31-06:00", comments = "JAXB RI v2.2.11")
    protected String returnFlightNumber;
    @XmlLocation
    @XmlTransient
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2016-09-13T11:38:31-06:00", comments = "JAXB RI v2.2.11")
    protected Locator locator;

    /**
     * Gets the value of the pickUpLocation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2016-09-13T11:38:31-06:00", comments = "JAXB RI v2.2.11")
    public String getPickUpLocation() {
        return pickUpLocation;
    }

    /**
     * Sets the value of the pickUpLocation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2016-09-13T11:38:31-06:00", comments = "JAXB RI v2.2.11")
    public void setPickUpLocation(String value) {
        this.pickUpLocation = value;
    }

    /**
     * Gets the value of the pickUpTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2016-09-13T11:38:31-06:00", comments = "JAXB RI v2.2.11")
    public XMLGregorianCalendar getPickUpTime() {
        return pickUpTime;
    }

    /**
     * Sets the value of the pickUpTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2016-09-13T11:38:31-06:00", comments = "JAXB RI v2.2.11")
    public void setPickUpTime(XMLGregorianCalendar value) {
        this.pickUpTime = value;
    }

    /**
     * Gets the value of the pickUpDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2016-09-13T11:38:31-06:00", comments = "JAXB RI v2.2.11")
    public String getPickUpDescription() {
        return pickUpDescription;
    }

    /**
     * Sets the value of the pickUpDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2016-09-13T11:38:31-06:00", comments = "JAXB RI v2.2.11")
    public void setPickUpDescription(String value) {
        this.pickUpDescription = value;
    }

    /**
     * Gets the value of the pickUpCarrier property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2016-09-13T11:38:31-06:00", comments = "JAXB RI v2.2.11")
    public String getPickUpCarrier() {
        return pickUpCarrier;
    }

    /**
     * Sets the value of the pickUpCarrier property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2016-09-13T11:38:31-06:00", comments = "JAXB RI v2.2.11")
    public void setPickUpCarrier(String value) {
        this.pickUpCarrier = value;
    }

    /**
     * Gets the value of the pickUpFlightNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2016-09-13T11:38:31-06:00", comments = "JAXB RI v2.2.11")
    public String getPickUpFlightNumber() {
        return pickUpFlightNumber;
    }

    /**
     * Sets the value of the pickUpFlightNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2016-09-13T11:38:31-06:00", comments = "JAXB RI v2.2.11")
    public void setPickUpFlightNumber(String value) {
        this.pickUpFlightNumber = value;
    }

    /**
     * Gets the value of the returnLocation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2016-09-13T11:38:31-06:00", comments = "JAXB RI v2.2.11")
    public String getReturnLocation() {
        return returnLocation;
    }

    /**
     * Sets the value of the returnLocation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2016-09-13T11:38:31-06:00", comments = "JAXB RI v2.2.11")
    public void setReturnLocation(String value) {
        this.returnLocation = value;
    }

    /**
     * Gets the value of the returnTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2016-09-13T11:38:31-06:00", comments = "JAXB RI v2.2.11")
    public XMLGregorianCalendar getReturnTime() {
        return returnTime;
    }

    /**
     * Sets the value of the returnTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2016-09-13T11:38:31-06:00", comments = "JAXB RI v2.2.11")
    public void setReturnTime(XMLGregorianCalendar value) {
        this.returnTime = value;
    }

    /**
     * Gets the value of the returnDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2016-09-13T11:38:31-06:00", comments = "JAXB RI v2.2.11")
    public String getReturnDescription() {
        return returnDescription;
    }

    /**
     * Sets the value of the returnDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2016-09-13T11:38:31-06:00", comments = "JAXB RI v2.2.11")
    public void setReturnDescription(String value) {
        this.returnDescription = value;
    }

    /**
     * Gets the value of the returnCarrier property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2016-09-13T11:38:31-06:00", comments = "JAXB RI v2.2.11")
    public String getReturnCarrier() {
        return returnCarrier;
    }

    /**
     * Sets the value of the returnCarrier property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2016-09-13T11:38:31-06:00", comments = "JAXB RI v2.2.11")
    public void setReturnCarrier(String value) {
        this.returnCarrier = value;
    }

    /**
     * Gets the value of the returnFlightNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2016-09-13T11:38:31-06:00", comments = "JAXB RI v2.2.11")
    public String getReturnFlightNumber() {
        return returnFlightNumber;
    }

    /**
     * Sets the value of the returnFlightNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2016-09-13T11:38:31-06:00", comments = "JAXB RI v2.2.11")
    public void setReturnFlightNumber(String value) {
        this.returnFlightNumber = value;
    }

    /**
     * Generates a String representation of the contents of this type.
     * This is an extension method, produced by the 'ts' xjc plugin
     * 
     */
    @Override
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2016-09-13T11:38:31-06:00", comments = "JAXB RI v2.2.11")
    public String toString() {
        return ToStringBuilder.reflectionToString(this, JAXBToStringStyle.MULTI_LINE_STYLE);
    }

    @Generated(value = "com.sun.tools.xjc.Driver", date = "2016-09-13T11:38:31-06:00", comments = "JAXB RI v2.2.11")
    public Locator sourceLocation() {
        return locator;
    }

    @Generated(value = "com.sun.tools.xjc.Driver", date = "2016-09-13T11:38:31-06:00", comments = "JAXB RI v2.2.11")
    public void setSourceLocation(Locator newLocator) {
        locator = newLocator;
    }

}
