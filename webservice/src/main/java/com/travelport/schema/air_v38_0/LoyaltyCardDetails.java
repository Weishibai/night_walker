
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
 *       &lt;attribute name="SupplierCode" use="required" type="{http://www.travelport.com/schema/common_v38_0}typeCarrier" /&gt;
 *       &lt;attribute name="PriorityCode" use="required" type="{http://www.travelport.com/schema/common_v38_0}typePriorityCode" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "LoyaltyCardDetails")
@Generated(value = "com.sun.tools.xjc.Driver", date = "2016-09-13T11:39:20-06:00", comments = "JAXB RI v2.2.11")
public class LoyaltyCardDetails
    implements Locatable
{

    @XmlAttribute(name = "SupplierCode", required = true)
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2016-09-13T11:39:20-06:00", comments = "JAXB RI v2.2.11")
    protected String supplierCode;
    @XmlAttribute(name = "PriorityCode", required = true)
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2016-09-13T11:39:20-06:00", comments = "JAXB RI v2.2.11")
    protected String priorityCode;
    @XmlLocation
    @XmlTransient
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2016-09-13T11:39:20-06:00", comments = "JAXB RI v2.2.11")
    protected Locator locator;

    /**
     * Gets the value of the supplierCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2016-09-13T11:39:20-06:00", comments = "JAXB RI v2.2.11")
    public String getSupplierCode() {
        return supplierCode;
    }

    /**
     * Sets the value of the supplierCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2016-09-13T11:39:20-06:00", comments = "JAXB RI v2.2.11")
    public void setSupplierCode(String value) {
        this.supplierCode = value;
    }

    /**
     * Gets the value of the priorityCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2016-09-13T11:39:20-06:00", comments = "JAXB RI v2.2.11")
    public String getPriorityCode() {
        return priorityCode;
    }

    /**
     * Sets the value of the priorityCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2016-09-13T11:39:20-06:00", comments = "JAXB RI v2.2.11")
    public void setPriorityCode(String value) {
        this.priorityCode = value;
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
