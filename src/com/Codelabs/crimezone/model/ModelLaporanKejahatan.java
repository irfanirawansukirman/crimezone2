package com.Codelabs.crimezone.model;

import java.util.ArrayList;

public class ModelLaporanKejahatan {

	String result;
	ArrayList<Item> item;

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
		private String id_laporan_kejahatan;
		private String id_daftar_pelapor;
		private String foto_kejahatan;
		private String judul_laporan_kegiatan;
		private String deskripsi_laporan_kegiatan;
		private String id_jenis_kejahatan;
		private String id_lokasi_kejahatan;
		private String tanggal_kejadian;
		private String waktu_kejadian;
		private String latitude;
		private String longtitude;
		private String alamat_kejahatan;
		private String id_status_laporan;
		private String nama_status_laporan;
		private String nama_jenis_kejahatan;
		private String id_petugas;
		private String nama_petugas;
		private String id_pengguna;
		private String nama_pengguna;
		private String daerah_kejahatan;

		public String getId_laporan_kejahatan() {
			return id_laporan_kejahatan;
		}

		public void setId_laporan_kejahatan(String id_laporan_kejahatan) {
			this.id_laporan_kejahatan = id_laporan_kejahatan;
		}

		public String getId_daftar_pelapor() {
			return id_daftar_pelapor;
		}

		public void setId_daftar_pelapor(String id_daftar_pelapor) {
			this.id_daftar_pelapor = id_daftar_pelapor;
		}

		public String getFoto_kejahatan() {
			return foto_kejahatan;
		}

		public void setFoto_kejahatan(String foto_kejahatan) {
			this.foto_kejahatan = foto_kejahatan;
		}

		public String getJudul_laporan_kegiatan() {
			return judul_laporan_kegiatan;
		}

		public void setJudul_laporan_kegiatan(String judul_laporan_kegiatan) {
			this.judul_laporan_kegiatan = judul_laporan_kegiatan;
		}

		public String getDeskripsi_laporan_kegiatan() {
			return deskripsi_laporan_kegiatan;
		}

		public void setDeskripsi_laporan_kegiatan(
				String deskripsi_laporan_kegiatan) {
			this.deskripsi_laporan_kegiatan = deskripsi_laporan_kegiatan;
		}

		public String getId_jenis_kejahatan() {
			return id_jenis_kejahatan;
		}

		public void setId_jenis_kejahatan(String id_jenis_kejahatan) {
			this.id_jenis_kejahatan = id_jenis_kejahatan;
		}

		public String getId_lokasi_kejahatan() {
			return id_lokasi_kejahatan;
		}

		public void setId_lokasi_kejahatan(String id_lokasi_kejahatan) {
			this.id_lokasi_kejahatan = id_lokasi_kejahatan;
		}

		public String getTanggal_kejadian() {
			return tanggal_kejadian;
		}

		public void setTanggal_kejadian(String tanggal_kejadian) {
			this.tanggal_kejadian = tanggal_kejadian;
		}

		public String getWaktu_kejadian() {
			return waktu_kejadian;
		}

		public void setWaktu_kejadian(String waktu_kejadian) {
			this.waktu_kejadian = waktu_kejadian;
		}

		public String getLatitude() {
			return latitude;
		}

		public void setLatitude(String latitude) {
			this.latitude = latitude;
		}

		public String getLongtitude() {
			return longtitude;
		}

		public void setLongtitude(String longtitude) {
			this.longtitude = longtitude;
		}

		public String getAlamat_kejahatan() {
			return alamat_kejahatan;
		}

		public void setAlamat_kejahatan(String alamat_kejahatan) {
			this.alamat_kejahatan = alamat_kejahatan;
		}

		public String getId_status_laporan() {
			return id_status_laporan;
		}

		public void setId_status_laporan(String id_status_laporan) {
			this.id_status_laporan = id_status_laporan;
		}

		public String getNama_status_laporan() {
			return nama_status_laporan;
		}

		public void setNama_status_laporan(String nama_status_laporan) {
			this.nama_status_laporan = nama_status_laporan;
		}

		public String getNama_jenis_kejahatan() {
			return nama_jenis_kejahatan;
		}

		public void setNama_jenis_kejahatan(String nama_jenis_kejahatan) {
			this.nama_jenis_kejahatan = nama_jenis_kejahatan;
		}

		public String getId_petugas() {
			return id_petugas;
		}

		public void setId_petugas(String id_petugas) {
			this.id_petugas = id_petugas;
		}

		public String getNama_petugas() {
			return nama_petugas;
		}

		public void setNama_petugas(String nama_petugas) {
			this.nama_petugas = nama_petugas;
		}

		public String getId_pengguna() {
			return id_pengguna;
		}

		public void setId_pengguna(String id_pengguna) {
			this.id_pengguna = id_pengguna;
		}

		public String getNama_pengguna() {
			return nama_pengguna;
		}

		public void setNama_pengguna(String nama_pengguna) {
			this.nama_pengguna = nama_pengguna;
		}

		public String getDaerah_kejahatan() {
			return daerah_kejahatan;
		}

		public void setDaerah_kejahatan(String daerah_kejahatan) {
			this.daerah_kejahatan = daerah_kejahatan;
		}

	}
}
