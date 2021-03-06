package com.Codelabs.crimezone.api;

public class ApiReferences {

	private static final String SITENAME = "http://crimezoneapp.com/";

	private static final String MODUL_API = "api/";
	private static final String MODUL_IMAGE = "foto_kejahatan/";

	private static final String MODUL_LAPORAN_KEJAHATAN = "bind_laporan_kejahatan.php";

	public static String getUrlLaporanKejahatan() {
		return SITENAME + MODUL_API + MODUL_LAPORAN_KEJAHATAN;
	}

	public static String getUrlImage() {
		return SITENAME + MODUL_IMAGE;
	}

}
