
package com.travelport.schema.air_v38_0;

import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import com.sun.xml.bind.Locatable;
import com.sun.xml.bind.annotation.XmlLocation;
import com.travelport.schema.common_v38_0.TypeReqSeat;
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
 *       &lt;attribute name="SegmentRef" type="{http://www.travelport.com/schema/common_v38_0}typeRef" /&gt;
 *       &lt;attribute name="Smoking" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" /&gt;
 *       &lt;attribute name="SeatType" use="required" type="{http://www.travelport.com/schema/common_v38_0}typeReqSeat" /&gt;
 *       &lt;attribute name="Group" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" /&gt;
 *       &lt;attribute name="BookingTravelerRef" type="{http://www.travelport.com/schema/common_v38_0}typeRef" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "AutoSeatAssignment")
@Generated(value = "com.sun.tools.xjc.Driver", date = "2016-09-13T11:39:20-06:00", comments = "JAXB RI v2.2.11")
public class AutoSeatAssignment
    implements Locatable
{

    @XmlAttribute(name = "SegmentRef")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2016-09-13T11:39:20-06:00", comments = "JAXB RI v2.2.11")
    protected String segmentRef;
    @XmlAttribute(name = "Smoking")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2016-09-13T11:39:20-06:00", comments = "JAXB RI v2.2.11")
    protected Boolean smoking;
    @XmlAttribute(name = "SeatType", required = true)
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2016-09-13T11:39:20-06:00", comments = "JAXB RI v2.2.11")
    protected TypeReqSeat seatType;
    @XmlAttribute(name = "Group")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2016-09-13T11:39:20-06:00", comments = "JAXB RI v2.2.11")
    protected Boolean group;
    @XmlAttribute(name = "BookingTravelerRef")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2016-09-13T11:39:20-06:00", comments = "JAXB RI v2.2.11")
    protected String bookingTravelerRef;
    @XmlLocation
    @XmlTransient
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2016-09-13T11:39:20-06:00", comments = "JAXB RI v2.2.11")
    protected Locator locator;

    /**
     * Gets the value of the segmentRef property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2016-09-13T11:39:20-06:00", comments = "JAXB RI v2.2.11")
    public String getSegmentRef() {
        return segmentRef;
    }

    /**
     * Sets the value of the segmentRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2016-09-13T11:39:20-06:00", comments = "JAXB RI v2.2.11")
    public void setSegmentRef(String value) {
        this.segmentRef = value;
    }

    /**
     * Gets the value of the smoking property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2016-09-13T11:39:20-06:00", comments = "JAXB RI v2.2.11")
    public boolean isSmoking() {
        if (smoking == null) {
            return false;
        } else {
            return smoking;
        }
    }

    /**
     * Sets the value of the smoking property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2016-09-13T11:39:20-06:00", comments = "JAXB RI v2.2.11")
    public void setSmoking(Boolean value) {
        this.smoking = value;
    }

    /**
     * Gets the value of the seatType property.
     * 
     * @return
     *     possible object is
     *     {@link TypeReqSeat }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2016-09-13T11:39:20-06:00", comments = "JAXB RI v2.2.11")
    public TypeReqSeat getSeatType() {
        return seatType;
    }

    /**
     * Sets the value of the seatType property.
     * 
     * @param value
     *     allowed object is
     *     {@link TypeReqSeat }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2016-09-13T11:39:20-06:00", comments = "JAXB RI v2.2.11")
    public void setSeatType(TypeReqSeat value) {
        this.seatType = value;
    }

    /**
     * Gets the value of the group property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2016-09-13T11:39:20-06:00", comments = "JAXB RI v2.2.11")
    public boolean isGroup() {
        if (group == null) {
            return false;
        } else {
            return group;
        }
    }

    /**
     * Sets the value of the group property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2016-09-13T11:39:20-06:00", comments = "JAXB RI v2.2.11")
    public void setGroup(Boolean value) {
        this.group = value;
    }

    /**
     * Gets the value of the bookingTravelerRef property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2016-09-13T11:39:20-06:00", comments = "JAXB RI v2.2.11")
    public String getBookingTravelerRef() {
        return bookingTravelerRef;
    }

    /**
     * Sets the value of the bookingTravelerRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2016-09-13T11:39:20-06:00", comments = "JAXB RI v2.2.11")
    public void setBookingTravelerRef(String value) {
        this.bookingTravelerRef = value;
    }

    /**
     * Generates a String representation of the contents of this type.
     * This is an extension method, produced by the 'ts' xjc plugin
     * 
     */
    @Override
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2016-09-13T11:39:20-06:00", comments = "JAXB RI v2.2.11")
    public String toString() {
        return ToStringBuilder.reflectionToString(this, JAXBToStringStyle.MULTI_LINE_STYLE);
    }

    @Generated(value = "com.sun.tools.xjc.Driver", date = "2016-09-13T11:39:20-06:00", comments = "JAXB RI v2.2.11")
    public Locator sourceLocation() {
        return locator;
    }

    @Generated(value = "com.sun.tools.xjc.Driver", date = "2016-09-13T11:39:20-06:00", comments = "JAXB RI v2.2.11")
    public void setSourceLocation(Locator newLocator) {
        locator = newLocator;
    }

}
