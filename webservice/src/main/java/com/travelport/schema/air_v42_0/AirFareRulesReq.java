
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
import com.travelport.schema.common_v42_0.BaseReq;
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
 *     &lt;extension base="{http://www.travelport.com/schema/common_v42_0}BaseReq"&gt;
 *       &lt;sequence&gt;
 *         &lt;choice&gt;
 *           &lt;element name="AirReservationSelector"&gt;
 *             &lt;complexType&gt;
 *               &lt;complexContent&gt;
 *                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                   &lt;sequence&gt;
 *                     &lt;element ref="{http://www.travelport.com/schema/air_v42_0}FareInfoRef" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                   &lt;/sequence&gt;
 *                   &lt;attribute name="AirReservationLocatorCode" use="required" type="{http://www.travelport.com/schema/common_v42_0}typeLocatorCode" /&gt;
 *                 &lt;/restriction&gt;
 *               &lt;/complexContent&gt;
 *             &lt;/complexType&gt;
 *           &lt;/element&gt;
 *           &lt;element ref="{http://www.travelport.com/schema/air_v42_0}FareRuleLookup" minOccurs="0"/&gt;
 *           &lt;element ref="{http://www.travelport.com/schema/air_v42_0}FareRuleKey" maxOccurs="unbounded"/&gt;
 *           &lt;element ref="{http://www.travelport.com/schema/air_v42_0}AirFareDisplayRuleKey"/&gt;
 *         &lt;/choice&gt;
 *         &lt;element ref="{http://www.travelport.com/schema/air_v42_0}AirFareRulesModifier" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="FareRuleType" type="{http://www.travelport.com/schema/air_v42_0}typeFareRuleType" default="long" /&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "airReservationSelector",
    "fareRuleLookup",
    "fareRuleKey",
    "airFareDisplayRuleKey",
    "airFareRulesModifier"
})
@XmlRootElement(name = "AirFareRulesReq")
@Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:51:28-06:00", comments = "JAXB RI v2.2.11")
public class AirFareRulesReq
    extends BaseReq
{

    @XmlElement(name = "AirReservationSelector")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:51:28-06:00", comments = "JAXB RI v2.2.11")
    protected AirFareRulesReq.AirReservationSelector airReservationSelector;
    @XmlElement(name = "FareRuleLookup")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:51:28-06:00", comments = "JAXB RI v2.2.11")
    protected FareRuleLookup fareRuleLookup;
    @XmlElement(name = "FareRuleKey")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:51:28-06:00", comments = "JAXB RI v2.2.11")
    protected List<FareRuleKey> fareRuleKey;
    @XmlElement(name = "AirFareDisplayRuleKey")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:51:28-06:00", comments = "JAXB RI v2.2.11")
    protected AirFareDisplayRuleKey airFareDisplayRuleKey;
    @XmlElement(name = "AirFareRulesModifier")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:51:28-06:00", comments = "JAXB RI v2.2.11")
    protected AirFareRulesModifier airFareRulesModifier;
    @XmlAttribute(name = "FareRuleType")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:51:28-06:00", comments = "JAXB RI v2.2.11")
    protected TypeFareRuleType fareRuleType;

    /**
     * Gets the value of the airReservationSelector property.
     * 
     * @return
     *     possible object is
     *     {@link AirFareRulesReq.AirReservationSelector }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:51:28-06:00", comments = "JAXB RI v2.2.11")
    public AirFareRulesReq.AirReservationSelector getAirReservationSelector() {
        return airReservationSelector;
    }

    /**
     * Sets the value of the airReservationSelector property.
     * 
     * @param value
     *     allowed object is
     *     {@link AirFareRulesReq.AirReservationSelector }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:51:28-06:00", comments = "JAXB RI v2.2.11")
    public void setAirReservationSelector(AirFareRulesReq.AirReservationSelector value) {
        this.airReservationSelector = value;
    }

    /**
     * Used to look up fare rules based on the origin, destination, and carrier of the air segment, the fare basis code and the provider code.  Providers: 1P, 1J.
     * 
     * @return
     *     possible object is
     *     {@link FareRuleLookup }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:51:28-06:00", comments = "JAXB RI v2.2.11")
    public FareRuleLookup getFareRuleLookup() {
        return fareRuleLookup;
    }

    /**
     * Sets the value of the fareRuleLookup property.
     * 
     * @param value
     *     allowed object is
     *     {@link FareRuleLookup }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:51:28-06:00", comments = "JAXB RI v2.2.11")
    public void setFareRuleLookup(FareRuleLookup value) {
        this.fareRuleLookup = value;
    }

    /**
     * Used to look up fare rules based on a fare rule key. Providers: 1G, 1V, 1P, 1J, ACH.Gets the value of the fareRuleKey property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the fareRuleKey property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFareRuleKey().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FareRuleKey }
     * 
     * 
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:51:28-06:00", comments = "JAXB RI v2.2.11")
    public List<FareRuleKey> getFareRuleKey() {
        if (fareRuleKey == null) {
            fareRuleKey = new ArrayList<FareRuleKey>();
        }
        return this.fareRuleKey;
    }

    /**
     * Provider: 1G,1V,1P,1J.
     * 
     * @return
     *     possible object is
     *     {@link AirFareDisplayRuleKey }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:51:28-06:00", comments = "JAXB RI v2.2.11")
    public AirFareDisplayRuleKey getAirFareDisplayRuleKey() {
        return airFareDisplayRuleKey;
    }

    /**
     * Sets the value of the airFareDisplayRuleKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link AirFareDisplayRuleKey }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:51:28-06:00", comments = "JAXB RI v2.2.11")
    public void setAirFareDisplayRuleKey(AirFareDisplayRuleKey value) {
        this.airFareDisplayRuleKey = value;
    }

    /**
     * Provider: 1G,1V.
     * 
     * @return
     *     possible object is
     *     {@link AirFareRulesModifier }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:51:28-06:00", comments = "JAXB RI v2.2.11")
    public AirFareRulesModifier getAirFareRulesModifier() {
        return airFareRulesModifier;
    }

    /**
     * Sets the value of the airFareRulesModifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link AirFareRulesModifier }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:51:28-06:00", comments = "JAXB RI v2.2.11")
    public void setAirFareRulesModifier(AirFareRulesModifier value) {
        this.airFareRulesModifier = value;
    }

    /**
     * Gets the value of the fareRuleType property.
     * 
     * @return
     *     possible object is
     *     {@link TypeFareRuleType }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:51:28-06:00", comments = "JAXB RI v2.2.11")
    public TypeFareRuleType getFareRuleType() {
        if (fareRuleType == null) {
            return TypeFareRuleType.LONG;
        } else {
            return fareRuleType;
        }
    }

    /**
     * Sets the value of the fareRuleType property.
     * 
     * @param value
     *     allowed object is
     *     {@link TypeFareRuleType }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:51:28-06:00", comments = "JAXB RI v2.2.11")
    public void setFareRuleType(TypeFareRuleType value) {
        this.fareRuleType = value;
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
     *         &lt;element ref="{http://www.travelport.com/schema/air_v42_0}FareInfoRef" maxOccurs="unbounded" minOccurs="0"/&gt;
     *       &lt;/sequence&gt;
     *       &lt;attribute name="AirReservationLocatorCode" use="required" type="{http://www.travelport.com/schema/common_v42_0}typeLocatorCode" /&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "fareInfoRef"
    })
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:51:28-06:00", comments = "JAXB RI v2.2.11")
    public static class AirReservationSelector
        implements Locatable
    {

        @XmlElement(name = "FareInfoRef")
        @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:51:28-06:00", comments = "JAXB RI v2.2.11")
        protected List<FareInfoRef> fareInfoRef;
        @XmlAttribute(name = "AirReservationLocatorCode", required = true)
        @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:51:28-06:00", comments = "JAXB RI v2.2.11")
        protected String airReservationLocatorCode;
        @XmlLocation
        @XmlTransient
        @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:51:28-06:00", comments = "JAXB RI v2.2.11")
        protected Locator locator;

        /**
         * Gets the value of the fareInfoRef property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the fareInfoRef property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getFareInfoRef().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link FareInfoRef }
         * 
         * 
         */
        @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:51:28-06:00", comments = "JAXB RI v2.2.11")
        public List<FareInfoRef> getFareInfoRef() {
            if (fareInfoRef == null) {
                fareInfoRef = new ArrayList<FareInfoRef>();
            }
            return this.fareInfoRef;
        }

        /**
         * Gets the value of the airReservationLocatorCode property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:51:28-06:00", comments = "JAXB RI v2.2.11")
        public String getAirReservationLocatorCode() {
            return airReservationLocatorCode;
        }

        /**
         * Sets the value of the airReservationLocatorCode property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:51:28-06:00", comments = "JAXB RI v2.2.11")
        public void setAirReservationLocatorCode(String value) {
            this.airReservationLocatorCode = value;
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

        @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:51:28-06:00", comments = "JAXB RI v2.2.11")
        public Locator sourceLocation() {
            return locator;
        }

        @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:51:28-06:00", comments = "JAXB RI v2.2.11")
        public void setSourceLocation(Locator newLocator) {
            locator = newLocator;
        }

    }

}
