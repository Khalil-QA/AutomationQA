package com.tests.project_name.customtypes;

import com.tests.project_name.constants.Language;

import io.cucumber.java.ParameterType;

public class CustomParameterType {

	@ParameterType(".*")
	public Language language(String languageName) {
		return Language.valueOf(languageName);
	}
}
