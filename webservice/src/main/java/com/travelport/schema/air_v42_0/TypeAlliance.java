
package com.travelport.schema.air_v42_0;

import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for typeAlliance.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="typeAlliance"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="StarAlliance"/&gt;
 *     &lt;enumeration value="OneWorld"/&gt;
 *     &lt;enumeration value="KLMNorthwestAlliance"/&gt;
 *     &lt;enumeration value="SkyTeam"/&gt;
 *     &lt;enumeration value="OWCode"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "typeAlliance")
@XmlEnum
@Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T04:06:27-06:00", comments = "JAXB RI v2.2.11")
public enum TypeAlliance {

    @XmlEnumValue("StarAlliance")
    STAR_ALLIANCE("StarAlliance"),
    @XmlEnumValue("OneWorld")
    ONE_WORLD("OneWorld"),
    @XmlEnumValue("KLMNorthwestAlliance")
    KLM_NORTHWEST_ALLIANCE("KLMNorthwestAlliance"),
    @XmlEnumValue("SkyTeam")
    SKY_TEAM("SkyTeam"),
    @XmlEnumValue("OWCode")
    OW_CODE("OWCode");
    private final String value;

    TypeAlliance(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TypeAlliance fromValue(String v) {
        for (TypeAlliance c: TypeAlliance.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
