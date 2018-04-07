package com.sunshine.publicserver.service.markup.util;

import com.sunshine.publicserver.service.markup.pojo.markup.MarkupParameter;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

/**
 * 生成XML
 * 
 * @author Administrator
 * 
 */
public class MarkupParameterXmlUtils {
	public static String createXml(MarkupParameter markupParameterVo) {
		String xmlString = "";
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.newDocument();
			document.setXmlStandalone(true);

			Element itemInfo = document.createElement("ArrayOfMarkupParameter");
			document.appendChild(itemInfo);

			Element items = document.createElement("MarkupParameter");
			itemInfo.appendChild(items);

			if (markupParameterVo.getId() != null) {
				Element Id = document.createElement("Id");
				Id.setTextContent(markupParameterVo.getId().toString());
				items.appendChild(Id);
			}

			Element FareType = document.createElement("FareType");
			FareType.setTextContent(markupParameterVo.getFareType());
			items.appendChild(FareType);

			Element TripType = document.createElement("TripType");
			TripType.setTextContent(markupParameterVo.getTripType());
			items.appendChild(TripType);

			Element Airline = document.createElement("Airline");
			Airline.setTextContent(markupParameterVo.getAirline());
			items.appendChild(Airline);

			Element DepartureDateTime = document
					.createElement("DepartureDateTime");
			DepartureDateTime.setTextContent(markupParameterVo
					.getDepartureDateTime());
			items.appendChild(DepartureDateTime);

			Element DepartureAirport = document
					.createElement("DepartureAirport");
			DepartureAirport.setTextContent(markupParameterVo
					.getDepartureAirport());
			items.appendChild(DepartureAirport);

			Element Destinations = document.createElement("Destinations");
			for (String Stringinfo : markupParameterVo.getDestinations()) {
				Element info = document.createElement("string");
				info.setTextContent(Stringinfo);
				Destinations.appendChild(info);
			}
			items.appendChild(Destinations);

			Element BookingClasses = document.createElement("BookingClasses");
			for (String BookingClassesString : markupParameterVo
					.getBookingClasses()) {
				Element info = document.createElement("string");
				info.setTextContent(BookingClassesString);
				BookingClasses.appendChild(info);
			}
			items.appendChild(BookingClasses);
			Element FareBasises = document.createElement("FareBasises");
			for (String FareBasisesString : markupParameterVo.getFareBasises()) {
				Element info = document.createElement("string");
				info.setTextContent(FareBasisesString);
				FareBasises.appendChild(info);
			}
			items.appendChild(FareBasises);
			Element FlightNumbers = document.createElement("FlightNumbers");
			for (String FlightNumbersString : markupParameterVo
					.getFlightNumbers()) {
				Element info = document.createElement("string");
				info.setTextContent(FlightNumbersString);
				FlightNumbers.appendChild(info);
			}
			items.appendChild(FlightNumbers);
			Element MarketAirlines = document.createElement("MarketAirlines");
			for (String MarketAirlinesString : markupParameterVo
					.getMarketAirlines()) {
				Element info = document.createElement("string");
				info.setTextContent(MarketAirlinesString);
				MarketAirlines.appendChild(info);
			}
			items.appendChild(MarketAirlines);

			if (markupParameterVo.getOperatorAirlines() != null) {

				Element OperatorAirlines = document
						.createElement("OperatorAirlines");
				for (String OperatorAirlinesString : markupParameterVo
						.getOperatorAirlines()) {
					Element info = document.createElement("string");
					info.setTextContent(OperatorAirlinesString);
					OperatorAirlines.appendChild(info);
				}
				items.appendChild(OperatorAirlines);
			}
			TransformerFactory transFactory = TransformerFactory.newInstance();
			Transformer transformer = transFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			DOMSource domSource = new DOMSource(document);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			transformer.transform(domSource, new StreamResult(bos));
			xmlString = bos.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return xmlString;
	}
}
