package com.Codelabs.crimezone.parser;

import com.Codelabs.crimezone.model.ModelLaporanKegiatan;
import com.Codelabs.crimezone.model.ModelLaporanKejahatan;
import com.google.gson.Gson;

public class Parser {

	public static ModelLaporanKejahatan getDataLaporanKejahatan(String respon) {
		Gson gs = new Gson();
		ModelLaporanKejahatan mkj = gs.fromJson(respon,
				ModelLaporanKejahatan.class);
		return mkj;
	}

	public static ModelLaporanKegiatan getDataLaporanKegiatan(String respon) {
		Gson gs = new Gson();
		ModelLaporanKegiatan mlk = gs.fromJson(respon,
				ModelLaporanKegiatan.class);
		return mlk;
	}

}
