
package com.travelport.schema.air_v42_0;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import com.travelport.schema.common_v42_0.BaseRsp;
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
 *     &lt;extension base="{http://www.travelport.com/schema/common_v42_0}BaseRsp"&gt;
 *       &lt;choice&gt;
 *         &lt;element ref="{http://www.travelport.com/schema/air_v42_0}EMDInfo"/&gt;
 *         &lt;element ref="{http://www.travelport.com/schema/air_v42_0}EMDSummaryInfo" maxOccurs="unbounded"/&gt;
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
    "emdInfo",
    "emdSummaryInfo"
})
@XmlRootElement(name = "EMDRetrieveRsp")
@Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:51:28-06:00", comments = "JAXB RI v2.2.11")
public class EMDRetrieveRsp
    extends BaseRsp
{

    @XmlElement(name = "EMDInfo")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:51:28-06:00", comments = "JAXB RI v2.2.11")
    protected EMDInfo emdInfo;
    @XmlElement(name = "EMDSummaryInfo")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:51:28-06:00", comments = "JAXB RI v2.2.11")
    protected List<EMDSummaryInfo> emdSummaryInfo;

    /**
     * Provider: 1G/1V/1P/1J.
     * 
     * @return
     *     possible object is
     *     {@link EMDInfo }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:51:28-06:00", comments = "JAXB RI v2.2.11")
    public EMDInfo getEMDInfo() {
        return emdInfo;
    }

    /**
     * Sets the value of the emdInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link EMDInfo }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:51:28-06:00", comments = "JAXB RI v2.2.11")
    public void setEMDInfo(EMDInfo value) {
        this.emdInfo = value;
    }

    /**
     * Provider: 1G/1V/1P/1J.Gets the value of the emdSummaryInfo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the emdSummaryInfo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEMDSummaryInfo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EMDSummaryInfo }
     * 
     * 
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:51:28-06:00", comments = "JAXB RI v2.2.11")
    public List<EMDSummaryInfo> getEMDSummaryInfo() {
        if (emdSummaryInfo == null) {
            emdSummaryInfo = new ArrayList<EMDSummaryInfo>();
        }
        return this.emdSummaryInfo;
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
