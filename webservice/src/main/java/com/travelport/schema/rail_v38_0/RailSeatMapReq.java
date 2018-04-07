
package com.travelport.schema.rail_v38_0;

import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import com.travelport.schema.common_v38_0.BaseReq;
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
 *     &lt;extension base="{http://www.travelport.com/schema/common_v38_0}BaseReq"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{http://www.travelport.com/schema/rail_v38_0}RailInfo"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "railInfo"
})
@XmlRootElement(name = "RailSeatMapReq")
@Generated(value = "com.sun.tools.xjc.Driver", date = "2016-09-13T11:33:11-06:00", comments = "JAXB RI v2.2.11")
public class RailSeatMapReq
    extends BaseReq
{

    @XmlElement(name = "RailInfo", required = true)
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2016-09-13T11:33:11-06:00", comments = "JAXB RI v2.2.11")
    protected RailInfo railInfo;

    /**
     * Gets the value of the railInfo property.
     * 
     * @return
     *     possible object is
     *     {@link RailInfo }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2016-09-13T11:33:11-06:00", comments = "JAXB RI v2.2.11")
    public RailInfo getRailInfo() {
        return railInfo;
    }

    /**
     * Sets the value of the railInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link RailInfo }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2016-09-13T11:33:11-06:00", comments = "JAXB RI v2.2.11")
    public void setRailInfo(RailInfo value) {
        this.railInfo = value;
    }

    /**
     * Generates a String representation of the contents of this type.
     * This is an extension method, produced by the 'ts' xjc plugin
     * 
     */
    @Override
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2016-09-13T11:33:11-06:00", comments = "JAXB RI v2.2.11")
    public String toString() {
        return ToStringBuilder.reflectionToString(this, JAXBToStringStyle.MULTI_LINE_STYLE);
    }

}
