package com.opteamix.library.util;

public class LibraryApiUtil {

	public static boolean doesStringValueExist(String string) {
		if(string != null && string.trim().length() > 0) {
			return true;
		} else {
			return false;
		}
	}

	
}
