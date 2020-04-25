package lab1;

import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, XMLStreamException, SAXException {
        final var xml = new File("powerTools.xml");

        // Validate XML file against XSD.
        final var schema = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI).newSchema(new File("powerTools.xsd"));
        final var validator = schema.newValidator();
        try {
            validator.validate(new StreamSource(xml));
            System.out.println(xml.getName() + " is valid.");
        } catch (SAXException e) {
            System.err.println(xml.getName() + " is not valid: " + e);
            return;
        }

        // Parse and print XML.
        final var factory = XMLInputFactory.newInstance(); // instance of the class which helps on reading tags
        final var eventReader = factory.createXMLEventReader(new FileReader(xml)); // handler to access the tags in the XML file

        var powerTools = new PowerTools();
        Tool tool = null;
        Specifications spec = null;

        while (eventReader.hasNext()) {
            var xmlEvent = eventReader.nextEvent();
            if (xmlEvent.isStartElement()) {
                Characters chars;
                var startElement = xmlEvent.asStartElement();
                switch (startElement.getName().getLocalPart()) {
                    case "tool":
                        tool = new Tool();
                        break;
                    case "model":
                        chars = (Characters) eventReader.nextEvent();
                        tool.setModel(chars.getData());
                        break;
                    case "handy":
                        chars = (Characters) eventReader.nextEvent();
                        tool.setHandy(Integer.parseInt(chars.getData()));
                        break;
                    case "origin":
                        chars = (Characters) eventReader.nextEvent();
                        tool.setOrigin(chars.getData());
                        break;
                    case "specifications":
                        spec = new Specifications();
                        break;
                    case "energy":
                        chars = (Characters) eventReader.nextEvent();
                        spec.setEnergy(chars.getData());
                        break;
                    case "power":
                        chars = (Characters) eventReader.nextEvent();
                        spec.setPower(Integer.parseInt(chars.getData()));
                        break;
                    case "autonomous":
                        chars = (Characters) eventReader.nextEvent();
                        spec.setAutonomous(Boolean.parseBoolean(chars.getData()));
                        break;
                    case "material":
                        chars = (Characters) eventReader.nextEvent();
                        tool.setMaterial(chars.getData());
                        break;
                }
            } else if (xmlEvent.isEndElement()) {
                var endElement = xmlEvent.asEndElement();
                if ("tool".equalsIgnoreCase(endElement.getName().getLocalPart())) {
                    powerTools.addTool(tool);
                } else if ("specifications".equalsIgnoreCase(endElement.getName().getLocalPart())) {
                    tool.setSpecifications(spec);
                }
            }
        }
        System.out.println(powerTools);

        // Apply XSL transformation.
        try {
            var transformer = TransformerFactory.newInstance().newTransformer(new StreamSource("powerTools.xsl"));
            transformer.transform(new StreamSource(xml), new StreamResult("new-powerTools.xml"));
            System.out.println("Transformation completed.");
        }
        catch (TransformerException e) {
            System.err.println("Impossible transform file " + xml.getName() + ": " + e);
        }
    }
}

