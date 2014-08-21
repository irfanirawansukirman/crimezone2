package com.Codelabs.crimezone.model;

import java.util.ArrayList;

public class ModelLaporanKegiatan {

	private String result;
	private ArrayList<Item> item;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public ArrayList<Item> getItem() {
		return item;
	}

	public void setItem(ArrayList<Item> item) {
		this.item = item;
	}

	public class Item {
		private String id_kegiatan;
		private String judul_kegiatan;
		private String deskripsi_kegiatan;
		private String tanggal_kegiatan;
		private String waktu_kegiatan;
		private String alamat_kegiatan;

		public String getId_kegiatan() {
			return id_kegiatan;
		}

		public void setId_kegiatan(String id_kegiatan) {
			this.id_kegiatan = id_kegiatan;
		}

		public String getJudul_kegiatan() {
			return judul_kegiatan;
		}

		public void setJudul_kegiatan(String judul_kegiatan) {
			this.judul_kegiatan = judul_kegiatan;
		}

		public String getDeskripsi_kegiatan() {
			return deskripsi_kegiatan;
		}

		public void setDeskripsi_kegiatan(String deskripsi_kegiatan) {
			this.deskripsi_kegiatan = deskripsi_kegiatan;
		}

		public String getTanggal_kegiatan() {
			return tanggal_kegiatan;
		}

		public void setTanggal_kegiatan(String tanggal_kegiatan) {
			this.tanggal_kegiatan = tanggal_kegiatan;
		}

		public String getWaktu_kegiatan() {
			return waktu_kegiatan;
		}

		public void setWaktu_kegiatan(String waktu_kegiatan) {
			this.waktu_kegiatan = waktu_kegiatan;
		}

		public String getAlamat_kegiatan() {
			return alamat_kegiatan;
		}

		public void setAlamat_kegiatan(String alamat_kegiatan) {
			this.alamat_kegiatan = alamat_kegiatan;
		}

	}

}
