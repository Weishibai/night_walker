
package com.travelport.schema.vehicle_v42_0;

import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
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
 *     &lt;extension base="{http://www.travelport.com/schema/vehicle_v42_0}typeVehicleRates"&gt;
 *       &lt;attribute name="DiscountAmount" type="{http://www.travelport.com/schema/common_v42_0}typeMoney" /&gt;
 *       &lt;attribute name="MandatoryChargeTotal" type="{http://www.travelport.com/schema/common_v42_0}typeMoney" /&gt;
 *       &lt;attribute name="ApproximateTotal" type="{http://www.travelport.com/schema/common_v42_0}typeMoney" /&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "SupplierRate")
@Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:56:32-06:00", comments = "JAXB RI v2.2.11")
public class SupplierRate
    extends TypeVehicleRates
{

    @XmlAttribute(name = "DiscountAmount")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:56:32-06:00", comments = "JAXB RI v2.2.11")
    protected String discountAmount;
    @XmlAttribute(name = "MandatoryChargeTotal")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:56:32-06:00", comments = "JAXB RI v2.2.11")
    protected String mandatoryChargeTotal;
    @XmlAttribute(name = "ApproximateTotal")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:56:32-06:00", comments = "JAXB RI v2.2.11")
    protected String approximateTotal;

    /**
     * Gets the value of the discountAmount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:56:32-06:00", comments = "JAXB RI v2.2.11")
    public String getDiscountAmount() {
        return discountAmount;
    }

    /**
     * Sets the value of the discountAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:56:32-06:00", comments = "JAXB RI v2.2.11")
    public void setDiscountAmount(String value) {
        this.discountAmount = value;
    }

    /**
     * Gets the value of the mandatoryChargeTotal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:56:32-06:00", comments = "JAXB RI v2.2.11")
    public String getMandatoryChargeTotal() {
        return mandatoryChargeTotal;
    }

    /**
     * Sets the value of the mandatoryChargeTotal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:56:32-06:00", comments = "JAXB RI v2.2.11")
    public void setMandatoryChargeTotal(String value) {
        this.mandatoryChargeTotal = value;
    }

    /**
     * Gets the value of the approximateTotal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:56:32-06:00", comments = "JAXB RI v2.2.11")
    public String getApproximateTotal() {
        return approximateTotal;
    }

    /**
     * Sets the value of the approximateTotal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:56:32-06:00", comments = "JAXB RI v2.2.11")
    public void setApproximateTotal(String value) {
        this.approximateTotal = value;
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
