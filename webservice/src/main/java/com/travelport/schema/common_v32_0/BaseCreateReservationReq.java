
package com.travelport.schema.common_v32_0;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.cxf.xjc.runtime.JAXBToStringStyle;


/**
 * <p>Java class for BaseCreateReservationReq complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BaseCreateReservationReq"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.travelport.com/schema/common_v32_0}BaseReq"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{http://www.travelport.com/schema/common_v32_0}LinkedUniversalRecord" maxOccurs="999" minOccurs="0"/&gt;
 *         &lt;element ref="{http://www.travelport.com/schema/common_v32_0}BookingTraveler" maxOccurs="999" minOccurs="0"/&gt;
 *         &lt;element ref="{http://www.travelport.com/schema/common_v32_0}OSI" maxOccurs="999" minOccurs="0"/&gt;
 *         &lt;element ref="{http://www.travelport.com/schema/common_v32_0}AccountingRemark" maxOccurs="999" minOccurs="0"/&gt;
 *         &lt;element ref="{http://www.travelport.com/schema/common_v32_0}GeneralRemark" maxOccurs="999" minOccurs="0"/&gt;
 *         &lt;element ref="{http://www.travelport.com/schema/common_v32_0}XMLRemark" maxOccurs="999" minOccurs="0"/&gt;
 *         &lt;element ref="{http://www.travelport.com/schema/common_v32_0}UnassociatedRemark" maxOccurs="999" minOccurs="0"/&gt;
 *         &lt;element ref="{http://www.travelport.com/schema/common_v32_0}Postscript" minOccurs="0"/&gt;
 *         &lt;element ref="{http://www.travelport.com/schema/common_v32_0}PassiveInfo" minOccurs="0"/&gt;
 *         &lt;element ref="{http://www.travelport.com/schema/common_v32_0}ContinuityCheckOverride" minOccurs="0"/&gt;
 *         &lt;element ref="{http://www.travelport.com/schema/common_v32_0}AgencyContactInfo" minOccurs="0"/&gt;
 *         &lt;element ref="{http://www.travelport.com/schema/common_v32_0}CustomerID" minOccurs="0"/&gt;
 *         &lt;element ref="{http://www.travelport.com/schema/common_v32_0}FileFinishingInfo" minOccurs="0"/&gt;
 *         &lt;element ref="{http://www.travelport.com/schema/common_v32_0}CommissionRemark" minOccurs="0"/&gt;
 *         &lt;element ref="{http://www.travelport.com/schema/common_v32_0}ConsolidatorRemark" minOccurs="0"/&gt;
 *         &lt;element ref="{http://www.travelport.com/schema/common_v32_0}InvoiceRemark" maxOccurs="999" minOccurs="0"/&gt;
 *         &lt;element ref="{http://www.travelport.com/schema/common_v32_0}SSR" maxOccurs="999" minOccurs="0"/&gt;
 *         &lt;element ref="{http://www.travelport.com/schema/common_v32_0}EmailNotification" minOccurs="0"/&gt;
 *         &lt;element ref="{http://www.travelport.com/schema/common_v32_0}QueuePlace" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="RuleName"&gt;
 *         &lt;simpleType&gt;
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *             &lt;maxLength value="10"/&gt;
 *           &lt;/restriction&gt;
 *         &lt;/simpleType&gt;
 *       &lt;/attribute&gt;
 *       &lt;attribute name="UniversalRecordLocatorCode" type="{http://www.travelport.com/schema/common_v32_0}typeLocatorCode" /&gt;
 *       &lt;attribute name="ProviderLocatorCode" type="{http://www.travelport.com/schema/common_v32_0}typeLocatorCode" /&gt;
 *       &lt;attribute name="ProviderCode" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="CustomerNumber" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="Version" type="{http://www.w3.org/2001/XMLSchema}integer" /&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BaseCreateReservationReq", propOrder = {
    "linkedUniversalRecord",
    "bookingTraveler",
    "osi",
    "accountingRemark",
    "generalRemark",
    "xmlRemark",
    "unassociatedRemark",
    "postscript",
    "passiveInfo",
    "continuityCheckOverride",
    "agencyContactInfo",
    "customerID",
    "fileFinishingInfo",
    "commissionRemark",
    "consolidatorRemark",
    "invoiceRemark",
    "ssr",
    "emailNotification",
    "queuePlace"
})
@XmlSeeAlso({
    BaseCreateWithFormOfPaymentReq.class
})
@Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:54:46-06:00", comments = "JAXB RI v2.2.11")
public class BaseCreateReservationReq
    extends BaseReq
{

    @XmlElement(name = "LinkedUniversalRecord")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:54:46-06:00", comments = "JAXB RI v2.2.11")
    protected List<LinkedUniversalRecord> linkedUniversalRecord;
    @XmlElement(name = "BookingTraveler")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:54:46-06:00", comments = "JAXB RI v2.2.11")
    protected List<BookingTraveler> bookingTraveler;
    @XmlElement(name = "OSI")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:54:46-06:00", comments = "JAXB RI v2.2.11")
    protected List<OSI> osi;
    @XmlElement(name = "AccountingRemark")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:54:46-06:00", comments = "JAXB RI v2.2.11")
    protected List<AccountingRemark> accountingRemark;
    @XmlElement(name = "GeneralRemark")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:54:46-06:00", comments = "JAXB RI v2.2.11")
    protected List<GeneralRemark> generalRemark;
    @XmlElement(name = "XMLRemark")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:54:46-06:00", comments = "JAXB RI v2.2.11")
    protected List<XMLRemark> xmlRemark;
    @XmlElement(name = "UnassociatedRemark")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:54:46-06:00", comments = "JAXB RI v2.2.11")
    protected List<UnassociatedRemark> unassociatedRemark;
    @XmlElement(name = "Postscript")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:54:46-06:00", comments = "JAXB RI v2.2.11")
    protected Postscript postscript;
    @XmlElement(name = "PassiveInfo")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:54:46-06:00", comments = "JAXB RI v2.2.11")
    protected PassiveInfo passiveInfo;
    @XmlElement(name = "ContinuityCheckOverride")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:54:46-06:00", comments = "JAXB RI v2.2.11")
    protected ContinuityCheckOverride continuityCheckOverride;
    @XmlElement(name = "AgencyContactInfo")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:54:46-06:00", comments = "JAXB RI v2.2.11")
    protected AgencyContactInfo agencyContactInfo;
    @XmlElement(name = "CustomerID")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:54:46-06:00", comments = "JAXB RI v2.2.11")
    protected CustomerID customerID;
    @XmlElement(name = "FileFinishingInfo")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:54:46-06:00", comments = "JAXB RI v2.2.11")
    protected FileFinishingInfo fileFinishingInfo;
    @XmlElement(name = "CommissionRemark")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:54:46-06:00", comments = "JAXB RI v2.2.11")
    protected CommissionRemark commissionRemark;
    @XmlElement(name = "ConsolidatorRemark")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:54:46-06:00", comments = "JAXB RI v2.2.11")
    protected ConsolidatorRemark consolidatorRemark;
    @XmlElement(name = "InvoiceRemark")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:54:46-06:00", comments = "JAXB RI v2.2.11")
    protected List<InvoiceRemark> invoiceRemark;
    @XmlElement(name = "SSR")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:54:46-06:00", comments = "JAXB RI v2.2.11")
    protected List<SSR> ssr;
    @XmlElement(name = "EmailNotification")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:54:46-06:00", comments = "JAXB RI v2.2.11")
    protected EmailNotification emailNotification;
    @XmlElement(name = "QueuePlace")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:54:46-06:00", comments = "JAXB RI v2.2.11")
    protected QueuePlace queuePlace;
    @XmlAttribute(name = "RuleName")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:54:46-06:00", comments = "JAXB RI v2.2.11")
    protected String ruleName;
    @XmlAttribute(name = "UniversalRecordLocatorCode")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:54:46-06:00", comments = "JAXB RI v2.2.11")
    protected String universalRecordLocatorCode;
    @XmlAttribute(name = "ProviderLocatorCode")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:54:46-06:00", comments = "JAXB RI v2.2.11")
    protected String providerLocatorCode;
    @XmlAttribute(name = "ProviderCode")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:54:46-06:00", comments = "JAXB RI v2.2.11")
    protected String providerCode;
    @XmlAttribute(name = "CustomerNumber")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:54:46-06:00", comments = "JAXB RI v2.2.11")
    protected String customerNumber;
    @XmlAttribute(name = "Version")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:54:46-06:00", comments = "JAXB RI v2.2.11")
    protected BigInteger version;

    /**
     * Gets the value of the linkedUniversalRecord property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the linkedUniversalRecord property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLinkedUniversalRecord().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LinkedUniversalRecord }
     * 
     * 
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:54:46-06:00", comments = "JAXB RI v2.2.11")
    public List<LinkedUniversalRecord> getLinkedUniversalRecord() {
        if (linkedUniversalRecord == null) {
            linkedUniversalRecord = new ArrayList<LinkedUniversalRecord>();
        }
        return this.linkedUniversalRecord;
    }

    /**
     * Gets the value of the bookingTraveler property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the bookingTraveler property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBookingTraveler().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BookingTraveler }
     * 
     * 
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:54:46-06:00", comments = "JAXB RI v2.2.11")
    public List<BookingTraveler> getBookingTraveler() {
        if (bookingTraveler == null) {
            bookingTraveler = new ArrayList<BookingTraveler>();
        }
        return this.bookingTraveler;
    }

    /**
     * Gets the value of the osi property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the osi property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOSI().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link OSI }
     * 
     * 
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:54:46-06:00", comments = "JAXB RI v2.2.11")
    public List<OSI> getOSI() {
        if (osi == null) {
            osi = new ArrayList<OSI>();
        }
        return this.osi;
    }

    /**
     * Gets the value of the accountingRemark property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the accountingRemark property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAccountingRemark().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AccountingRemark }
     * 
     * 
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:54:46-06:00", comments = "JAXB RI v2.2.11")
    public List<AccountingRemark> getAccountingRemark() {
        if (accountingRemark == null) {
            accountingRemark = new ArrayList<AccountingRemark>();
        }
        return this.accountingRemark;
    }

    /**
     * Gets the value of the generalRemark property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the generalRemark property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGeneralRemark().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link GeneralRemark }
     * 
     * 
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:54:46-06:00", comments = "JAXB RI v2.2.11")
    public List<GeneralRemark> getGeneralRemark() {
        if (generalRemark == null) {
            generalRemark = new ArrayList<GeneralRemark>();
        }
        return this.generalRemark;
    }

    /**
     * Gets the value of the xmlRemark property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the xmlRemark property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getXMLRemark().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link XMLRemark }
     * 
     * 
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:54:46-06:00", comments = "JAXB RI v2.2.11")
    public List<XMLRemark> getXMLRemark() {
        if (xmlRemark == null) {
            xmlRemark = new ArrayList<XMLRemark>();
        }
        return this.xmlRemark;
    }

    /**
     * Gets the value of the unassociatedRemark property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the unassociatedRemark property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUnassociatedRemark().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link UnassociatedRemark }
     * 
     * 
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:54:46-06:00", comments = "JAXB RI v2.2.11")
    public List<UnassociatedRemark> getUnassociatedRemark() {
        if (unassociatedRemark == null) {
            unassociatedRemark = new ArrayList<UnassociatedRemark>();
        }
        return this.unassociatedRemark;
    }

    /**
     * Gets the value of the postscript property.
     * 
     * @return
     *     possible object is
     *     {@link Postscript }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:54:46-06:00", comments = "JAXB RI v2.2.11")
    public Postscript getPostscript() {
        return postscript;
    }

    /**
     * Sets the value of the postscript property.
     * 
     * @param value
     *     allowed object is
     *     {@link Postscript }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:54:46-06:00", comments = "JAXB RI v2.2.11")
    public void setPostscript(Postscript value) {
        this.postscript = value;
    }

    /**
     * Gets the value of the passiveInfo property.
     * 
     * @return
     *     possible object is
     *     {@link PassiveInfo }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:54:46-06:00", comments = "JAXB RI v2.2.11")
    public PassiveInfo getPassiveInfo() {
        return passiveInfo;
    }

    /**
     * Sets the value of the passiveInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link PassiveInfo }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:54:46-06:00", comments = "JAXB RI v2.2.11")
    public void setPassiveInfo(PassiveInfo value) {
        this.passiveInfo = value;
    }

    /**
     * This element will be used if user wants to override segment continuity check.
     * 
     * @return
     *     possible object is
     *     {@link ContinuityCheckOverride }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:54:46-06:00", comments = "JAXB RI v2.2.11")
    public ContinuityCheckOverride getContinuityCheckOverride() {
        return continuityCheckOverride;
    }

    /**
     * Sets the value of the continuityCheckOverride property.
     * 
     * @param value
     *     allowed object is
     *     {@link ContinuityCheckOverride }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:54:46-06:00", comments = "JAXB RI v2.2.11")
    public void setContinuityCheckOverride(ContinuityCheckOverride value) {
        this.continuityCheckOverride = value;
    }

    /**
     * Gets the value of the agencyContactInfo property.
     * 
     * @return
     *     possible object is
     *     {@link AgencyContactInfo }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:54:46-06:00", comments = "JAXB RI v2.2.11")
    public AgencyContactInfo getAgencyContactInfo() {
        return agencyContactInfo;
    }

    /**
     * Sets the value of the agencyContactInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link AgencyContactInfo }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:54:46-06:00", comments = "JAXB RI v2.2.11")
    public void setAgencyContactInfo(AgencyContactInfo value) {
        this.agencyContactInfo = value;
    }

    /**
     * Gets the value of the customerID property.
     * 
     * @return
     *     possible object is
     *     {@link CustomerID }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:54:46-06:00", comments = "JAXB RI v2.2.11")
    public CustomerID getCustomerID() {
        return customerID;
    }

    /**
     * Sets the value of the customerID property.
     * 
     * @param value
     *     allowed object is
     *     {@link CustomerID }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:54:46-06:00", comments = "JAXB RI v2.2.11")
    public void setCustomerID(CustomerID value) {
        this.customerID = value;
    }

    /**
     * Gets the value of the fileFinishingInfo property.
     * 
     * @return
     *     possible object is
     *     {@link FileFinishingInfo }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:54:46-06:00", comments = "JAXB RI v2.2.11")
    public FileFinishingInfo getFileFinishingInfo() {
        return fileFinishingInfo;
    }

    /**
     * Sets the value of the fileFinishingInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link FileFinishingInfo }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:54:46-06:00", comments = "JAXB RI v2.2.11")
    public void setFileFinishingInfo(FileFinishingInfo value) {
        this.fileFinishingInfo = value;
    }

    /**
     * Gets the value of the commissionRemark property.
     * 
     * @return
     *     possible object is
     *     {@link CommissionRemark }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:54:46-06:00", comments = "JAXB RI v2.2.11")
    public CommissionRemark getCommissionRemark() {
        return commissionRemark;
    }

    /**
     * Sets the value of the commissionRemark property.
     * 
     * @param value
     *     allowed object is
     *     {@link CommissionRemark }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:54:46-06:00", comments = "JAXB RI v2.2.11")
    public void setCommissionRemark(CommissionRemark value) {
        this.commissionRemark = value;
    }

    /**
     * Gets the value of the consolidatorRemark property.
     * 
     * @return
     *     possible object is
     *     {@link ConsolidatorRemark }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:54:46-06:00", comments = "JAXB RI v2.2.11")
    public ConsolidatorRemark getConsolidatorRemark() {
        return consolidatorRemark;
    }

    /**
     * Sets the value of the consolidatorRemark property.
     * 
     * @param value
     *     allowed object is
     *     {@link ConsolidatorRemark }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:54:46-06:00", comments = "JAXB RI v2.2.11")
    public void setConsolidatorRemark(ConsolidatorRemark value) {
        this.consolidatorRemark = value;
    }

    /**
     * Gets the value of the invoiceRemark property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the invoiceRemark property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInvoiceRemark().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link InvoiceRemark }
     * 
     * 
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:54:46-06:00", comments = "JAXB RI v2.2.11")
    public List<InvoiceRemark> getInvoiceRemark() {
        if (invoiceRemark == null) {
            invoiceRemark = new ArrayList<InvoiceRemark>();
        }
        return this.invoiceRemark;
    }

    /**
     * SSR element outside Booking Traveler without any Segment Ref or Booking Traveler Ref.Gets the value of the ssr property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the ssr property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSSR().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SSR }
     * 
     * 
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:54:46-06:00", comments = "JAXB RI v2.2.11")
    public List<SSR> getSSR() {
        if (ssr == null) {
            ssr = new ArrayList<SSR>();
        }
        return this.ssr;
    }

    /**
     * Gets the value of the emailNotification property.
     * 
     * @return
     *     possible object is
     *     {@link EmailNotification }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:54:46-06:00", comments = "JAXB RI v2.2.11")
    public EmailNotification getEmailNotification() {
        return emailNotification;
    }

    /**
     * Sets the value of the emailNotification property.
     * 
     * @param value
     *     allowed object is
     *     {@link EmailNotification }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:54:46-06:00", comments = "JAXB RI v2.2.11")
    public void setEmailNotification(EmailNotification value) {
        this.emailNotification = value;
    }

    /**
     * Allow queue placement of a PNR at the time of booking in AirCreateReservationReq,HotelCreateReservationReq,PassiveCreateReservationReq and VehicleCreateReservationReq for providers 1G,1V,1P and 1J.
     * 
     * @return
     *     possible object is
     *     {@link QueuePlace }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:54:46-06:00", comments = "JAXB RI v2.2.11")
    public QueuePlace getQueuePlace() {
        return queuePlace;
    }

    /**
     * Sets the value of the queuePlace property.
     * 
     * @param value
     *     allowed object is
     *     {@link QueuePlace }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:54:46-06:00", comments = "JAXB RI v2.2.11")
    public void setQueuePlace(QueuePlace value) {
        this.queuePlace = value;
    }

    /**
     * Gets the value of the ruleName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:54:46-06:00", comments = "JAXB RI v2.2.11")
    public String getRuleName() {
        return ruleName;
    }

    /**
     * Sets the value of the ruleName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:54:46-06:00", comments = "JAXB RI v2.2.11")
    public void setRuleName(String value) {
        this.ruleName = value;
    }

    /**
     * Gets the value of the universalRecordLocatorCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:54:46-06:00", comments = "JAXB RI v2.2.11")
    public String getUniversalRecordLocatorCode() {
        return universalRecordLocatorCode;
    }

    /**
     * Sets the value of the universalRecordLocatorCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:54:46-06:00", comments = "JAXB RI v2.2.11")
    public void setUniversalRecordLocatorCode(String value) {
        this.universalRecordLocatorCode = value;
    }

    /**
     * Gets the value of the providerLocatorCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:54:46-06:00", comments = "JAXB RI v2.2.11")
    public String getProviderLocatorCode() {
        return providerLocatorCode;
    }

    /**
     * Sets the value of the providerLocatorCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:54:46-06:00", comments = "JAXB RI v2.2.11")
    public void setProviderLocatorCode(String value) {
        this.providerLocatorCode = value;
    }

    /**
     * Gets the value of the providerCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:54:46-06:00", comments = "JAXB RI v2.2.11")
    public String getProviderCode() {
        return providerCode;
    }

    /**
     * Sets the value of the providerCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:54:46-06:00", comments = "JAXB RI v2.2.11")
    public void setProviderCode(String value) {
        this.providerCode = value;
    }

    /**
     * Gets the value of the customerNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:54:46-06:00", comments = "JAXB RI v2.2.11")
    public String getCustomerNumber() {
        return customerNumber;
    }

    /**
     * Sets the value of the customerNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:54:46-06:00", comments = "JAXB RI v2.2.11")
    public void setCustomerNumber(String value) {
        this.customerNumber = value;
    }

    /**
     * Gets the value of the version property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:54:46-06:00", comments = "JAXB RI v2.2.11")
    public BigInteger getVersion() {
        return version;
    }

    /**
     * Sets the value of the version property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:54:46-06:00", comments = "JAXB RI v2.2.11")
    public void setVersion(BigInteger value) {
        this.version = value;
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

}
