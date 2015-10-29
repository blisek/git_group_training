package com.capgemini.math;

import info.debatty.java.stringsimilarity.Cosine;
import info.debatty.java.stringsimilarity.Levenshtein;

public class StringDummy {
	public double compareIfStringsHaveTheSameLengthsInDifferentMethods(String a, String b) {
		Levenshtein levenshtein = new Levenshtein();
		Cosine cosine = new Cosine();
		
		return levenshtein.distance(a, b) - cosine.distance(a, b);
	}
}
