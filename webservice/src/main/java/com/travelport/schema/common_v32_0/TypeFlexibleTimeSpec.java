
package com.travelport.schema.common_v32_0;

import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import com.sun.xml.bind.Locatable;
import com.sun.xml.bind.annotation.XmlLocation;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.cxf.xjc.runtime.JAXBToStringStyle;
import org.xml.sax.Locator;


/**
 * A type which can be used for flexible date/time specification -extends the generic type typeTimeSpec to provide extra options for search.
 * 
 * <p>Java class for typeFlexibleTimeSpec complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="typeFlexibleTimeSpec"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.travelport.com/schema/common_v32_0}typeTimeSpec"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="SearchExtraDays" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;attribute name="DaysBefore" type="{http://www.w3.org/2001/XMLSchema}int" /&gt;
 *                 &lt;attribute name="DaysAfter" type="{http://www.w3.org/2001/XMLSchema}int" /&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "typeFlexibleTimeSpec", propOrder = {
    "searchExtraDays"
})
@Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:54:46-06:00", comments = "JAXB RI v2.2.11")
public class TypeFlexibleTimeSpec
    extends TypeTimeSpec
{

    @XmlElement(name = "SearchExtraDays")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:54:46-06:00", comments = "JAXB RI v2.2.11")
    protected TypeFlexibleTimeSpec.SearchExtraDays searchExtraDays;

    /**
     * Gets the value of the searchExtraDays property.
     * 
     * @return
     *     possible object is
     *     {@link TypeFlexibleTimeSpec.SearchExtraDays }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:54:46-06:00", comments = "JAXB RI v2.2.11")
    public TypeFlexibleTimeSpec.SearchExtraDays getSearchExtraDays() {
        return searchExtraDays;
    }

    /**
     * Sets the value of the searchExtraDays property.
     * 
     * @param value
     *     allowed object is
     *     {@link TypeFlexibleTimeSpec.SearchExtraDays }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:54:46-06:00", comments = "JAXB RI v2.2.11")
    public void setSearchExtraDays(TypeFlexibleTimeSpec.SearchExtraDays value) {
        this.searchExtraDays = value;
    }

    /**
     * Generates a String representation of the contents of this type.
     * This is an extension method, produced by the 'ts' xjc plugin
     * 
     */
    @Override
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:54:46-06:00", comments = "JAXB RI v2.2.11")
    public String toString() {
        return ToStringBuilder.reflectionToString(this, JAXBToStringStyle.MULTI_LINE_STYLE);
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;attribute name="DaysBefore" type="{http://www.w3.org/2001/XMLSchema}int" /&gt;
     *       &lt;attribute name="DaysAfter" type="{http://www.w3.org/2001/XMLSchema}int" /&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:54:46-06:00", comments = "JAXB RI v2.2.11")
    public static class SearchExtraDays
        implements Locatable
    {

        @XmlAttribute(name = "DaysBefore")
        @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:54:46-06:00", comments = "JAXB RI v2.2.11")
        protected Integer daysBefore;
        @XmlAttribute(name = "DaysAfter")
        @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:54:46-06:00", comments = "JAXB RI v2.2.11")
        protected Integer daysAfter;
        @XmlLocation
        @XmlTransient
        @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:54:46-06:00", comments = "JAXB RI v2.2.11")
        protected Locator locator;

        /**
         * Gets the value of the daysBefore property.
         * 
         * @return
         *     possible object is
         *     {@link Integer }
         *     
         */
        @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:54:46-06:00", comments = "JAXB RI v2.2.11")
        public Integer getDaysBefore() {
            return daysBefore;
        }

        /**
         * Sets the value of the daysBefore property.
         * 
         * @param value
         *     allowed object is
         *     {@link Integer }
         *     
         */
        @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:54:46-06:00", comments = "JAXB RI v2.2.11")
        public void setDaysBefore(Integer value) {
            this.daysBefore = value;
        }

        /**
         * Gets the value of the daysAfter property.
         * 
         * @return
         *     possible object is
         *     {@link Integer }
         *     
         */
        @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:54:46-06:00", comments = "JAXB RI v2.2.11")
        public Integer getDaysAfter() {
            return daysAfter;
        }

        /**
         * Sets the value of the daysAfter property.
         * 
         * @param value
         *     allowed object is
         *     {@link Integer }
         *     
         */
        @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:54:46-06:00", comments = "JAXB RI v2.2.11")
        public void setDaysAfter(Integer value) {
            this.daysAfter = value;
        }

        /**
         * Generates a String representation of the contents of this type.
         * This is an extension method, produced by the 'ts' xjc plugin
         * 
         */
        @Override
        @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:54:46-06:00", comments = "JAXB RI v2.2.11")
        public String toString() {
            return ToStringBuilder.reflectionToString(this, JAXBToStringStyle.MULTI_LINE_STYLE);
        }

        @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:54:46-06:00", comments = "JAXB RI v2.2.11")
        public Locator sourceLocation() {
            return locator;
        }

        @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:54:46-06:00", comments = "JAXB RI v2.2.11")
        public void setSourceLocation(Locator newLocator) {
            locator = newLocator;
        }

    }

}
