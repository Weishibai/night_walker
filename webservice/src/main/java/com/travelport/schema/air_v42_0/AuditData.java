
package com.travelport.schema.air_v42_0;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import com.sun.xml.bind.Locatable;
import com.sun.xml.bind.annotation.XmlLocation;
import com.travelport.schema.common_v42_0.TypeTaxInfo;
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
 *       &lt;sequence&gt;
 *         &lt;element ref="{http://www.travelport.com/schema/air_v42_0}TaxInfo" maxOccurs="999" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attGroup ref="{http://www.travelport.com/schema/common_v42_0}attrPrices"/&gt;
 *       &lt;attribute name="Key" type="{http://www.travelport.com/schema/common_v42_0}typeRef" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "taxInfo"
})
@XmlRootElement(name = "AuditData")
@Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T04:06:27-06:00", comments = "JAXB RI v2.2.11")
public class AuditData
    implements Locatable
{

    @XmlElement(name = "TaxInfo")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T04:06:27-06:00", comments = "JAXB RI v2.2.11")
    protected List<TypeTaxInfo> taxInfo;
    @XmlAttribute(name = "Key")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T04:06:27-06:00", comments = "JAXB RI v2.2.11")
    protected String key;
    @XmlAttribute(name = "TotalPrice")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T04:06:27-06:00", comments = "JAXB RI v2.2.11")
    protected String totalPrice;
    @XmlAttribute(name = "BasePrice")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T04:06:27-06:00", comments = "JAXB RI v2.2.11")
    protected String basePrice;
    @XmlAttribute(name = "ApproximateTotalPrice")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T04:06:27-06:00", comments = "JAXB RI v2.2.11")
    protected String approximateTotalPrice;
    @XmlAttribute(name = "ApproximateBasePrice")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T04:06:27-06:00", comments = "JAXB RI v2.2.11")
    protected String approximateBasePrice;
    @XmlAttribute(name = "EquivalentBasePrice")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T04:06:27-06:00", comments = "JAXB RI v2.2.11")
    protected String equivalentBasePrice;
    @XmlAttribute(name = "Taxes")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T04:06:27-06:00", comments = "JAXB RI v2.2.11")
    protected String taxes;
    @XmlAttribute(name = "Fees")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T04:06:27-06:00", comments = "JAXB RI v2.2.11")
    protected String fees;
    @XmlAttribute(name = "Services")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T04:06:27-06:00", comments = "JAXB RI v2.2.11")
    protected String services;
    @XmlAttribute(name = "ApproximateTaxes")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T04:06:27-06:00", comments = "JAXB RI v2.2.11")
    protected String approximateTaxes;
    @XmlAttribute(name = "ApproximateFees")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T04:06:27-06:00", comments = "JAXB RI v2.2.11")
    protected String approximateFees;
    @XmlLocation
    @XmlTransient
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T04:06:27-06:00", comments = "JAXB RI v2.2.11")
    protected Locator locator;

    /**
     * Gets the value of the taxInfo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the taxInfo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTaxInfo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TypeTaxInfo }
     * 
     * 
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T04:06:27-06:00", comments = "JAXB RI v2.2.11")
    public List<TypeTaxInfo> getTaxInfo() {
        if (taxInfo == null) {
            taxInfo = new ArrayList<TypeTaxInfo>();
        }
        return this.taxInfo;
    }

    /**
     * Gets the value of the key property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T04:06:27-06:00", comments = "JAXB RI v2.2.11")
    public String getKey() {
        return key;
    }

    /**
     * Sets the value of the key property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T04:06:27-06:00", comments = "JAXB RI v2.2.11")
    public void setKey(String value) {
        this.key = value;
    }

    /**
     * Gets the value of the totalPrice property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T04:06:27-06:00", comments = "JAXB RI v2.2.11")
    public String getTotalPrice() {
        return totalPrice;
    }

    /**
     * Sets the value of the totalPrice property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T04:06:27-06:00", comments = "JAXB RI v2.2.11")
    public void setTotalPrice(String value) {
        this.totalPrice = value;
    }

    /**
     * Gets the value of the basePrice property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T04:06:27-06:00", comments = "JAXB RI v2.2.11")
    public String getBasePrice() {
        return basePrice;
    }

    /**
     * Sets the value of the basePrice property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T04:06:27-06:00", comments = "JAXB RI v2.2.11")
    public void setBasePrice(String value) {
        this.basePrice = value;
    }

    /**
     * Gets the value of the approximateTotalPrice property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T04:06:27-06:00", comments = "JAXB RI v2.2.11")
    public String getApproximateTotalPrice() {
        return approximateTotalPrice;
    }

    /**
     * Sets the value of the approximateTotalPrice property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T04:06:27-06:00", comments = "JAXB RI v2.2.11")
    public void setApproximateTotalPrice(String value) {
        this.approximateTotalPrice = value;
    }

    /**
     * Gets the value of the approximateBasePrice property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T04:06:27-06:00", comments = "JAXB RI v2.2.11")
    public String getApproximateBasePrice() {
        return approximateBasePrice;
    }

    /**
     * Sets the value of the approximateBasePrice property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T04:06:27-06:00", comments = "JAXB RI v2.2.11")
    public void setApproximateBasePrice(String value) {
        this.approximateBasePrice = value;
    }

    /**
     * Gets the value of the equivalentBasePrice property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T04:06:27-06:00", comments = "JAXB RI v2.2.11")
    public String getEquivalentBasePrice() {
        return equivalentBasePrice;
    }

    /**
     * Sets the value of the equivalentBasePrice property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T04:06:27-06:00", comments = "JAXB RI v2.2.11")
    public void setEquivalentBasePrice(String value) {
        this.equivalentBasePrice = value;
    }

    /**
     * Gets the value of the taxes property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T04:06:27-06:00", comments = "JAXB RI v2.2.11")
    public String getTaxes() {
        return taxes;
    }

    /**
     * Sets the value of the taxes property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T04:06:27-06:00", comments = "JAXB RI v2.2.11")
    public void setTaxes(String value) {
        this.taxes = value;
    }

    /**
     * Gets the value of the fees property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T04:06:27-06:00", comments = "JAXB RI v2.2.11")
    public String getFees() {
        return fees;
    }

    /**
     * Sets the value of the fees property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T04:06:27-06:00", comments = "JAXB RI v2.2.11")
    public void setFees(String value) {
        this.fees = value;
    }

    /**
     * Gets the value of the services property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T04:06:27-06:00", comments = "JAXB RI v2.2.11")
    public String getServices() {
        return services;
    }

    /**
     * Sets the value of the services property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T04:06:27-06:00", comments = "JAXB RI v2.2.11")
    public void setServices(String value) {
        this.services = value;
    }

    /**
     * Gets the value of the approximateTaxes property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T04:06:27-06:00", comments = "JAXB RI v2.2.11")
    public String getApproximateTaxes() {
        return approximateTaxes;
    }

    /**
     * Sets the value of the approximateTaxes property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T04:06:27-06:00", comments = "JAXB RI v2.2.11")
    public void setApproximateTaxes(String value) {
        this.approximateTaxes = value;
    }

    /**
     * Gets the value of the approximateFees property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T04:06:27-06:00", comments = "JAXB RI v2.2.11")
    public String getApproximateFees() {
        return approximateFees;
    }

    /**
     * Sets the value of the approximateFees property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T04:06:27-06:00", comments = "JAXB RI v2.2.11")
    public void setApproximateFees(String value) {
        this.approximateFees = value;
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

    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T04:06:27-06:00", comments = "JAXB RI v2.2.11")
    public Locator sourceLocation() {
        return locator;
    }

    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T04:06:27-06:00", comments = "JAXB RI v2.2.11")
    public void setSourceLocation(Locator newLocator) {
        locator = newLocator;
    }

}
