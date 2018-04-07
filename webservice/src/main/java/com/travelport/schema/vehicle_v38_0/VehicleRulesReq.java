
package com.travelport.schema.vehicle_v38_0;

import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import com.sun.xml.bind.Locatable;
import com.sun.xml.bind.annotation.XmlLocation;
import com.travelport.schema.common_v38_0.BaseSearchReq;
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
 *     &lt;extension base="{http://www.travelport.com/schema/common_v38_0}BaseSearchReq"&gt;
 *       &lt;choice&gt;
 *         &lt;element name="VehicleReservationLocatorCode" type="{http://www.travelport.com/schema/common_v38_0}typeLocatorCode"/&gt;
 *         &lt;element name="VehicleRulesLookup"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element ref="{http://www.travelport.com/schema/vehicle_v38_0}VehicleDateLocation"/&gt;
 *                   &lt;element ref="{http://www.travelport.com/schema/vehicle_v38_0}VehicleSearchModifiers"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
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
    "vehicleReservationLocatorCode",
    "vehicleRulesLookup"
})
@XmlRootElement(name = "VehicleRulesReq")
@Generated(value = "com.sun.tools.xjc.Driver", date = "2016-09-13T11:40:15-06:00", comments = "JAXB RI v2.2.11")
public class VehicleRulesReq
    extends BaseSearchReq
{

    @XmlElement(name = "VehicleReservationLocatorCode")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2016-09-13T11:40:15-06:00", comments = "JAXB RI v2.2.11")
    protected String vehicleReservationLocatorCode;
    @XmlElement(name = "VehicleRulesLookup")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2016-09-13T11:40:15-06:00", comments = "JAXB RI v2.2.11")
    protected VehicleRulesReq.VehicleRulesLookup vehicleRulesLookup;

    /**
     * Gets the value of the vehicleReservationLocatorCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2016-09-13T11:40:15-06:00", comments = "JAXB RI v2.2.11")
    public String getVehicleReservationLocatorCode() {
        return vehicleReservationLocatorCode;
    }

    /**
     * Sets the value of the vehicleReservationLocatorCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2016-09-13T11:40:15-06:00", comments = "JAXB RI v2.2.11")
    public void setVehicleReservationLocatorCode(String value) {
        this.vehicleReservationLocatorCode = value;
    }

    /**
     * Gets the value of the vehicleRulesLookup property.
     * 
     * @return
     *     possible object is
     *     {@link VehicleRulesReq.VehicleRulesLookup }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2016-09-13T11:40:15-06:00", comments = "JAXB RI v2.2.11")
    public VehicleRulesReq.VehicleRulesLookup getVehicleRulesLookup() {
        return vehicleRulesLookup;
    }

    /**
     * Sets the value of the vehicleRulesLookup property.
     * 
     * @param value
     *     allowed object is
     *     {@link VehicleRulesReq.VehicleRulesLookup }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2016-09-13T11:40:15-06:00", comments = "JAXB RI v2.2.11")
    public void setVehicleRulesLookup(VehicleRulesReq.VehicleRulesLookup value) {
        this.vehicleRulesLookup = value;
    }

    /**
     * Generates a String representation of the contents of this type.
     * This is an extension method, produced by the 'ts' xjc plugin
     * 
     */
    @Override
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2016-09-13T11:40:15-06:00", comments = "JAXB RI v2.2.11")
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
     *         &lt;element ref="{http://www.travelport.com/schema/vehicle_v38_0}VehicleDateLocation"/&gt;
     *         &lt;element ref="{http://www.travelport.com/schema/vehicle_v38_0}VehicleSearchModifiers"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "vehicleDateLocation",
        "vehicleSearchModifiers"
    })
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2016-09-13T11:40:15-06:00", comments = "JAXB RI v2.2.11")
    public static class VehicleRulesLookup
        implements Locatable
    {

        @XmlElement(name = "VehicleDateLocation", required = true)
        @Generated(value = "com.sun.tools.xjc.Driver", date = "2016-09-13T11:40:15-06:00", comments = "JAXB RI v2.2.11")
        protected VehicleDateLocation vehicleDateLocation;
        @XmlElement(name = "VehicleSearchModifiers", required = true)
        @Generated(value = "com.sun.tools.xjc.Driver", date = "2016-09-13T11:40:15-06:00", comments = "JAXB RI v2.2.11")
        protected VehicleSearchModifiers vehicleSearchModifiers;
        @XmlLocation
        @XmlTransient
        @Generated(value = "com.sun.tools.xjc.Driver", date = "2016-09-13T11:40:15-06:00", comments = "JAXB RI v2.2.11")
        protected Locator locator;

        /**
         * Gets the value of the vehicleDateLocation property.
         * 
         * @return
         *     possible object is
         *     {@link VehicleDateLocation }
         *     
         */
        @Generated(value = "com.sun.tools.xjc.Driver", date = "2016-09-13T11:40:15-06:00", comments = "JAXB RI v2.2.11")
        public VehicleDateLocation getVehicleDateLocation() {
            return vehicleDateLocation;
        }

        /**
         * Sets the value of the vehicleDateLocation property.
         * 
         * @param value
         *     allowed object is
         *     {@link VehicleDateLocation }
         *     
         */
        @Generated(value = "com.sun.tools.xjc.Driver", date = "2016-09-13T11:40:15-06:00", comments = "JAXB RI v2.2.11")
        public void setVehicleDateLocation(VehicleDateLocation value) {
            this.vehicleDateLocation = value;
        }

        /**
         * Gets the value of the vehicleSearchModifiers property.
         * 
         * @return
         *     possible object is
         *     {@link VehicleSearchModifiers }
         *     
         */
        @Generated(value = "com.sun.tools.xjc.Driver", date = "2016-09-13T11:40:15-06:00", comments = "JAXB RI v2.2.11")
        public VehicleSearchModifiers getVehicleSearchModifiers() {
            return vehicleSearchModifiers;
        }

        /**
         * Sets the value of the vehicleSearchModifiers property.
         * 
         * @param value
         *     allowed object is
         *     {@link VehicleSearchModifiers }
         *     
         */
        @Generated(value = "com.sun.tools.xjc.Driver", date = "2016-09-13T11:40:15-06:00", comments = "JAXB RI v2.2.11")
        public void setVehicleSearchModifiers(VehicleSearchModifiers value) {
            this.vehicleSearchModifiers = value;
        }

        /**
         * Generates a String representation of the contents of this type.
         * This is an extension method, produced by the 'ts' xjc plugin
         * 
         */
        @Override
        @Generated(value = "com.sun.tools.xjc.Driver", date = "2016-09-13T11:40:15-06:00", comments = "JAXB RI v2.2.11")
        public String toString() {
            return ToStringBuilder.reflectionToString(this, JAXBToStringStyle.MULTI_LINE_STYLE);
        }

        @Generated(value = "com.sun.tools.xjc.Driver", date = "2016-09-13T11:40:15-06:00", comments = "JAXB RI v2.2.11")
        public Locator sourceLocation() {
            return locator;
        }

        @Generated(value = "com.sun.tools.xjc.Driver", date = "2016-09-13T11:40:15-06:00", comments = "JAXB RI v2.2.11")
        public void setSourceLocation(Locator newLocator) {
            locator = newLocator;
        }

    }

}
