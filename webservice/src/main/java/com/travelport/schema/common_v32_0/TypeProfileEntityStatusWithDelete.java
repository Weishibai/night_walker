
package com.travelport.schema.common_v32_0;

import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for typeProfileEntityStatusWithDelete.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="typeProfileEntityStatusWithDelete"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Deleted"/&gt;
 *     &lt;enumeration value="Active"/&gt;
 *     &lt;enumeration value="Inactive"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "typeProfileEntityStatusWithDelete")
@XmlEnum
@Generated(value = "com.sun.tools.xjc.Driver", date = "2017-07-31T03:54:46-06:00", comments = "JAXB RI v2.2.11")
public enum TypeProfileEntityStatusWithDelete {

    @XmlEnumValue("Deleted")
    DELETED("Deleted"),
    @XmlEnumValue("Active")
    ACTIVE("Active"),
    @XmlEnumValue("Inactive")
    INACTIVE("Inactive");
    private final String value;

    TypeProfileEntityStatusWithDelete(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TypeProfileEntityStatusWithDelete fromValue(String v) {
        for (TypeProfileEntityStatusWithDelete c: TypeProfileEntityStatusWithDelete.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
