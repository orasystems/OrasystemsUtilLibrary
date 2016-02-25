package com.orasystems.libs.utils.preferences;

import org.androidannotations.annotations.sharedpreferences.DefaultBoolean;
import org.androidannotations.annotations.sharedpreferences.SharedPref;

@SharedPref
public interface AtalhoPreferences {
	@DefaultBoolean(false)
	boolean atalhoCriado();

}