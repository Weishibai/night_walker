
package com.travelport.schema.universal_v38_0;

import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import com.sun.xml.bind.Locatable;
import com.sun.xml.bind.annotation.XmlLocation;
import com.travelport.schema.common_v38_0.TypeElement;
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
 *       &lt;attribute name="Element" use="required" type="{http://www.travelport.com/schema/common_v38_0}typeElement" /&gt;
 *       &lt;attribute name="Key" type="{http://www.travelport.com/schema/common_v38_0}typeRef" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "UniversalDelete")
@Generated(value = "com.sun.tools.xjc.Driver", date = "2016-09-13T11:38:31-06:00", comments = "JAXB RI v2.2.11")
public class UniversalDelete
    implements Locatable
{

    @XmlAttribute(name = "Element", required = true)
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2016-09-13T11:38:31-06:00", comments = "JAXB RI v2.2.11")
    protected TypeElement element;
    @XmlAttribute(name = "Key")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2016-09-13T11:38:31-06:00", comments = "JAXB RI v2.2.11")
    protected String key;
    @XmlLocation
    @XmlTransient
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2016-09-13T11:38:31-06:00", comments = "JAXB RI v2.2.11")
    protected Locator locator;

    /**
     * Gets the value of the element property.
     * 
     * @return
     *     possible object is
     *     {@link TypeElement }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2016-09-13T11:38:31-06:00", comments = "JAXB RI v2.2.11")
    public TypeElement getElement() {
        return element;
    }

    /**
     * Sets the value of the element property.
     * 
     * @param value
     *     allowed object is
     *     {@link TypeElement }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2016-09-13T11:38:31-06:00", comments = "JAXB RI v2.2.11")
    public void setElement(TypeElement value) {
        this.element = value;
    }

    /**
     * Gets the value of the key property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2016-09-13T11:38:31-06:00", comments = "JAXB RI v2.2.11")
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
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2016-09-13T11:38:31-06:00", comments = "JAXB RI v2.2.11")
    public void setKey(String value) {
        this.key = value;
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
