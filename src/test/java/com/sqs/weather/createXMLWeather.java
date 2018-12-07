package com.sqs.weather;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class createXMLWeather {
    static void createPrettyXMLUsingDOM()
    {
        try
        {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("TemperatureForecast");
            doc.appendChild(rootElement);

            Element cityName = doc.createElement("city");
            cityName.setTextContent("Durban");
            rootElement.appendChild(cityName);

            String[] minText = {"minday1", "minday2", "minday3", "minday4"};
            String[] maxText = {"maxday1", "maxday2", "maxday3", "maxday4"};

            for(int i=1; i<=4; i++)
            {
                Element minTextDay = doc.createElement(minText[i-1]);
                minTextDay.setTextContent("Min" + i);
                rootElement.appendChild(minTextDay);
            }

            for(int i=1; i<=4; i++)
            {
                Element maxTextDay = doc.createElement(maxText[i-1]);
                maxTextDay.setTextContent("Max" + i);
                rootElement.appendChild(maxTextDay);
            }

            // Write the content into XML file
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("weather-forecast.xml"));

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            // Beautify the format of the resulted XML
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            transformer.transform(source, result);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
