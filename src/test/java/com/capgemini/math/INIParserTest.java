package com.capgemini.math;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;

public class INIParserTest {
	private INIParser parser;
	private InputStream validXMLStream;
	private InputStream invalidXMLStream1;
	private InputStream invalidXMLStream2;

	@Before
	public void setUp() throws Exception {
		parser = new INIParser();
		validXMLStream = new ByteArrayInputStream(VALID_XML.getBytes());
		invalidXMLStream1 = new ByteArrayInputStream(INVALID_XML_1.getBytes());
		invalidXMLStream2 = new ByteArrayInputStream(INVALID_XML_2.getBytes());
	}

	@Test
	public void testParseValidXMLSectionsCount() throws IOException, ParseException {
		int sectionsExpected = 2;
		parser.parse(validXMLStream);
		assertEquals(sectionsExpected, parser.getSectionsNames().size());
	}
	
	@Test(expected=ParseException.class)
	public void testParseInvalidXML1() throws IOException, ParseException {
		parser.parse(invalidXMLStream1);
	}
	
	@Test(expected=ParseException.class)
	public void testParseInvalidXML2() throws IOException, ParseException {
		parser.parse(invalidXMLStream2);
	}
	
	@Test
	public void testParseValidXMLExpectedPropertyExists() throws IOException, ParseException {
		String sectionName = "Section1";
		String propertyName = "prop2";

		parser.parse(validXMLStream);
		assertTrue(parser.sectionExists(sectionName));
		
		Properties prop = parser.getSection(sectionName);
		assertTrue(prop.containsKey(propertyName));
	}
	
	@Test
	public void testParseValidXMLExpectedPropertiesCount() throws IOException, ParseException {
		String sectionName = "Section1";
		int sectionPropertiesExpectedQuantity = 2;
		
		parser.parse(validXMLStream);
		Properties prop = parser.getSection(sectionName);
		
		assertEquals(sectionPropertiesExpectedQuantity, prop.size());
	}
	
	@Test
	public void testParseValidXMLExpectedPropertyValue() throws IOException, ParseException {
		String sectionName = "Section1";
		String propertyName = "prop2";
		String propertyValueExpected = "val2";
		
		parser.parse(validXMLStream);
		Properties prop = parser.getSection(sectionName);
		Object propValue = prop.getProperty(propertyName);
		
		assertNotNull(propValue);
		assertEquals(propertyValueExpected, propValue);
	}

	@Test(expected=IllegalStateException.class)
	public void testGetSectionsNamesUninitedParser() {
		parser.getSectionsNames();
	}
	
	@Test
	public void testGetSectionsNamesInitedParser() throws IOException, ParseException {
		parser.parse(validXMLStream);
		assertTrue(parser.sectionExists("Section1"));
		assertTrue(parser.sectionExists("Section2"));
	}

	@Test(expected=IllegalStateException.class)
	public void testSectionExistsUninitedParser() {
		parser.sectionExists("Section1");
	}
	
	@Test
	public void testSectionExistsInitedParser() throws IOException, ParseException {
		String expectedSection = "Section2";
		
		parser.parse(validXMLStream);
		assertTrue(parser.sectionExists(expectedSection));
	}

	@Test(expected=IllegalStateException.class)
	public void testGetSectionUninitedParser() {
		parser.getSection("Section1");
	}
	
	
	private static final String VALID_XML = 
			"[Section1]\n"
			+ "prop1=val1\n"
			+ "prop2=val2\n"
			+ "\n"
			+ "; section 2 begins\n"
			+ "[Section2]\n"
			+ "prop3=val3\n";
			
	private static final String INVALID_XML_1 =
			"[Sec!)(((]\n"
			+ "propx=valx";
	
	private static final String INVALID_XML_2 =
			"[SectionV]\n"
			+ "; simple comment\n"
			+ "prop1: val1";
}
