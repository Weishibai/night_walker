
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
 *       &lt;sequence&gt;
 *         &lt;element ref="{http://www.travelport.com/schema/air_v42_0}ETR" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element ref="{http://www.travelport.com/schema/air_v42_0}TCR" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="RefundFailureInfo" type="{http://www.travelport.com/schema/air_v42_0}typeTicketFailureInfo" maxOccurs="unbounded" minOccurs="0"/&gt;
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
    "etr",
    "tcr",
    "refundFailureInfo"
})
@XmlRootElement(name = "AirRefundRsp")
@Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:51:28-06:00", comments = "JAXB RI v2.2.11")
public class AirRefundRsp
    extends BaseRsp
{

    @XmlElement(name = "ETR")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:51:28-06:00", comments = "JAXB RI v2.2.11")
    protected List<ETR> etr;
    @XmlElement(name = "TCR")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:51:28-06:00", comments = "JAXB RI v2.2.11")
    protected List<TCR> tcr;
    @XmlElement(name = "RefundFailureInfo")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:51:28-06:00", comments = "JAXB RI v2.2.11")
    protected List<TypeTicketFailureInfo> refundFailureInfo;

    /**
     * Provider: ACH.Gets the value of the etr property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the etr property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getETR().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ETR }
     * 
     * 
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:51:28-06:00", comments = "JAXB RI v2.2.11")
    public List<ETR> getETR() {
        if (etr == null) {
            etr = new ArrayList<ETR>();
        }
        return this.etr;
    }

    /**
     * Provider: ACH.Gets the value of the tcr property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the tcr property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTCR().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TCR }
     * 
     * 
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:51:28-06:00", comments = "JAXB RI v2.2.11")
    public List<TCR> getTCR() {
        if (tcr == null) {
            tcr = new ArrayList<TCR>();
        }
        return this.tcr;
    }

    /**
     * Gets the value of the refundFailureInfo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the refundFailureInfo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRefundFailureInfo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TypeTicketFailureInfo }
     * 
     * 
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:51:28-06:00", comments = "JAXB RI v2.2.11")
    public List<TypeTicketFailureInfo> getRefundFailureInfo() {
        if (refundFailureInfo == null) {
            refundFailureInfo = new ArrayList<TypeTicketFailureInfo>();
        }
        return this.refundFailureInfo;
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
