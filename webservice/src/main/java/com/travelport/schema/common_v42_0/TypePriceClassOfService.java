
package com.travelport.schema.common_v42_0;

import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for typePriceClassOfService.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="typePriceClassOfService"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="ClassBooked"/&gt;
 *     &lt;enumeration value="LowestClass"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "typePriceClassOfService")
@XmlEnum
@Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T04:06:27-06:00", comments = "JAXB RI v2.2.11")
public enum TypePriceClassOfService {

    @XmlEnumValue("ClassBooked")
    CLASS_BOOKED("ClassBooked"),
    @XmlEnumValue("LowestClass")
    LOWEST_CLASS("LowestClass");
    private final String value;

    TypePriceClassOfService(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TypePriceClassOfService fromValue(String v) {
        for (TypePriceClassOfService c: TypePriceClassOfService.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
