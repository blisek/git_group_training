package com.capgemini.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

public class INIParser {
	
	/**
	 * Parsuje plik ini. Jesli format danych jest niepoprawny rzuca wyjatek ParseException.
	 * @param is zrodlo danych.
	 * @throws IOException rzucany, gdy wystapil blad odczytu.
	 * @throws ParseException rzucany, gdy dane wejsciowe maja niepoprawny format.
	 */
	public void parse(InputStream is) throws IOException, ParseException {
		try(@SuppressWarnings("resource") BufferedReader reader = 
				new BufferedReader(new InputStreamReader(is))) {
			String currentSection = null;
			Properties currentProperties = null;
			for(String line; (line = reader.readLine()) != null;) {
				if(isComment(line) || "".equals(line)) {
					continue;
				}
				else if(isSection(line)) {
					String parsedSectionName = parseSectionString(line);
					if(parsedSectionName.equals(currentSection) || parsedIni.containsKey(parsedSectionName)) {
						String errMsg = String.format("Section \"%s\" appears more than once.", parsedSectionName);
						LOGGER.error(errMsg);
						throw new ParseException(errMsg, 0);
					}
					currentSection = parsedSectionName;
					currentProperties = new Properties();
					parsedIni.put(currentSection, currentProperties);
				}
				else if(isProperty(line)) {
					String[] parsedProperty = parseProperty(line);
					if(currentSection == null || currentProperties == null) {
						String errMsg = String.format("Property \"%s\" must be under section", line);
						LOGGER.error(errMsg);
						throw new ParseException(errMsg, 0);
					}
					else if(currentProperties.containsKey(parsedProperty[0])) {
						String errMsg = String.format("Property cannot contains more than one same key: %s", parsedProperty[0]);
						LOGGER.error(errMsg);
						throw new ParseException(errMsg, 0);
					}
					currentProperties.put(parsedProperty[0], parsedProperty[1]);
				}
				else {
					String msg = String.format("Unknows char sequence \"%s\".", line);
					LOGGER.error(msg);
					throw new ParseException(msg, 0);
				}
			}
		}
		
		parsed = true;
	}
	
	/**
	 * Zwraca wszystkie sekcje jako liste Stringow.
	 * @return lista Stringow z nazwami sekcji.
	 */
	public List<String> getSectionsNames() {
		assureParsedState();
		return new ArrayList<String>(parsedIni.keySet());
	}
	
	/**
	 * Informuje czy istnieje dana sekcja.
	 * @param sectionName nazwa sekcji
	 * @return true jesli sekcja istnieje.
	 */
	public boolean sectionExists(String sectionName) {
		assureParsedState();
		return parsedIni.containsKey(sectionName.toLowerCase());
	}
	
	/**
	 * Zwraca wlasciwosci z danej sekcji (np. [Section1]). Jesli sekcja nie
	 * istnieje jest tworzona, a nastepnie zostaje zwrocony pusty zbior wlasciwosci.
	 * @param sectionName nazwa sekcji, wielkosc liter nie ma znaczenia.
	 * @return zbior wlasciwosci.
	 */
	public Properties getSection(String sectionName) {
		assureParsedState();
		sectionName = sectionName.toLowerCase();
		Properties prop = parsedIni.get(sectionName);
		if(prop == null) {
			prop = new Properties();
			parsedIni.put(sectionName, prop);
		}
		return prop;
	}
	
	public boolean isInited() {
		return parsed;
	}

	private boolean isSection(String line) {
		return line.startsWith("[") && line.endsWith("]");
	}
	
	private String parseSectionString(String line) throws ParseException {
		String parsedLine = line.substring(1, line.length()-1);
		if(parsedLine.length() >= 1 && StringUtils.isAlphanumeric(parsedLine)) {
			return parsedLine.toLowerCase();
		} else {
			String errMessage = String.format("Error while parsing line \"%s\". Section name is invalid.", parsedLine);
			LOGGER.error(errMessage);
			throw new ParseException(errMessage, 0);
		}
	}
	
	private boolean isProperty(String line) {
		return line.indexOf('=') >= 0;
	}
	
	private String[] parseProperty(String line) throws ParseException {
		LOGGER.debug(String.format("Parsing \"%s\" as property.", line));
		String[] parsedProperty = line.split("=");
		if(parsedProperty.length != 2 || !StringUtils.isAlphanumeric(parsedProperty[0])
				|| !StringUtils.isAlphanumeric(parsedProperty[1])) {
			String errMsg = String.format("Line \"%s\" is not valid property.", line);
			LOGGER.error(errMsg);
			throw new ParseException(errMsg, 0);
		}
		
		return parsedProperty;
	}
	
	private boolean isComment(String line) {
		return line.startsWith(";");
	}
	
	private void assureParsedState() {
		if(!parsed)
			throw new IllegalStateException("First parse ini file");
	}
	

	
	private boolean parsed = false;
	private final HashMap<String, Properties> parsedIni = new HashMap<String, Properties>();
	private static final Logger LOGGER = Logger.getLogger(INIParser.class);
}
