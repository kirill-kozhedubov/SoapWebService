package com.netcracker.stcenter.common.utils.xmlparser;

import com.netcracker.stcenter.common.exceptions.SaxInvalidDateFormatException;
import com.netcracker.stcenter.common.exceptions.SaxTerminatorException;
import com.netcracker.stcenter.common.utils.dateconverter.DateFormat;
import com.netcracker.stcenter.common.utils.dateconverter.StringToDateConverter;
import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.math.BigDecimal;
import java.util.*;

public class XmlHandler extends DefaultHandler {

    public static final Logger LOGGER = Logger.getLogger(XmlHandler.class);

    private boolean isRow = false;
    private boolean isElement = false;
    private List<Map<String, Object>> rows;
    private Map<String, Object> elements;
    private String elementName;
    private int countOfRows = 0;
    private boolean stopFlag = false;
    private DateFormat dateFormat;

    XmlHandler(DateFormat dateFormat) {
        this.dateFormat = dateFormat;
    }

    XmlHandler(DateFormat dateFormat, boolean stopper, int countOfRows) {
        this(dateFormat);
        this.stopFlag = stopper;
        this.countOfRows = countOfRows;
    }

    @Override
    public void startDocument() throws SAXException {
        LOGGER.debug("Start parsing of file...");
    }

    @Override
    public void endDocument() throws SAXException {
        LOGGER.debug("End parsing of file...");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        switch (qName) {
            case "tes:rows":
                rows = new ArrayList<>();
                break;
            case "tes:Row":
                if (stopFlag && countOfRows == 0) {
                    throw new SaxTerminatorException();
                }
                isRow = true;
                elements = new LinkedHashMap<>();
                break;
            default:
                if (isRow) {
                    isElement = true;
                    elementName = qName;
                }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName) {
            case "tes:Row":
                if (stopFlag) {
                    countOfRows--;
                }
                isRow = false;
                rows.add(elements);
                break;
        }
        if (isElement) {
            isElement = false;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String dateWithoutTime = "^[0-9]*(\\.|-|/)[0-9]*(\\.|-|/)[0-9]*$";
        String dateWithTime = "^[0-9]*(\\.|-|/)[0-9]*(\\.|-|/)[0-9]*\\s[0-9]*:[0-9]*:[0-9]*$";
        String dateWithTimeWithoutSeconds = "^[0-9]*(\\.|-|/)[0-9]*(\\.|-|/)[0-9]*\\s[0-9]*:[0-9]*$";
        String elementValue = String.copyValueOf(ch, start, length).trim();
        if (isElement) {
            if (elementValue.matches("^([0-9]+\\.[0-9]+)|([0-9])$")) {
                elements.put(elementName, BigDecimal.valueOf(Double.parseDouble(elementValue)));
            } else
            if (elementValue.matches("("+dateWithoutTime+")|("+dateWithTime+")|("+dateWithTimeWithoutSeconds+")")) {
                Date date = new StringToDateConverter(dateFormat).convertDateFromString(elementValue);
                if (date == null) throw new SaxInvalidDateFormatException();
                elements.put(elementName, date);
            } else {
                elements.put(elementName, elementValue);
            }
        }
    }

    List<Map<String, Object>> getRows() {
        return rows;
    }
}
