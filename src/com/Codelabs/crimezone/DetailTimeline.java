package com.Codelabs.crimezone;

import java.util.ArrayList;

import org.json.JSONObject;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.ImageLoader.ImageContainer;
import com.Codelabs.crimezone.api.ApiReferences;
import com.Codelabs.crimezone.model.ModelLaporanKejahatan;
import com.Codelabs.crimezone.parser.Parser;
import com.Codelabs.crimezone.utils.NetworkChecking;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DetailTimeline extends Activity {

	private Context context;
	ModelLaporanKejahatan model;
	RequestQueue mRequestQueue;
	ArrayList<ModelLaporanKejahatan.Item> array;
	String id_laporan_kejahatan, judul, tag, tgl, alamat, desc, user,
			idPetugas, idPengguna, imgUrl;
	TextView txtJudul, txtDesc, txtUser, txtTag, txtTgl, txtAlamat;
	ImageView imgKejahatan;
	ImageLoader imgLoader;
	int width;

	NetworkChecking ch;
	Boolean checking;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.detail_timeline);

		Display display = getWindowManager().getDefaultDisplay();
		width = display.getWidth();

		context = getApplicationContext();

		declareView();
		initData();
		imgKejahatan.getLayoutParams().width = width;

		ch = new NetworkChecking(context);
		checking = null;
		checking = ch.isConnectingToInternet();
		if (checking) {
			load();
		} else {
			Toast.makeText(context, "Please Check Your Connection!",
					Toast.LENGTH_LONG).show();
		}
	}

	private void load() {
		loadData();
	}

	private void initData() {
		Bundle extras = getIntent().getExtras();
		id_laporan_kejahatan = extras.getString("id_laporan_kejahatan");
	}

	private void loadData() {
		MyVolley.init(context);
		String filter = "?id_laporan_kejahatan=" + id_laporan_kejahatan;
		String url = ApiReferences.getUrlLaporanKejahatan() + filter;
		mRequestQueue = MyVolley.getRequestQueue();

		JsonObjectRequest jObject = new JsonObjectRequest(Request.Method.GET,
				url, null, new Response.Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {
						Log.i("sukses", response.toString());

						model = Parser.getDataLaporanKejahatan(response
								.toString());
						array = model.getItem();

						setData();
					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						Log.i("error", error.toString());
					}
				});
		mRequestQueue.add(jObject);
	}

	private void setData() {
		judul = array.get(0).getJudul_laporan_kegiatan();
		tag = array.get(0).getNama_jenis_kejahatan();
		desc = array.get(0).getDeskripsi_laporan_kegiatan();
		tgl = array.get(0).getTanggal_kejadian();
		alamat = array.get(0).getAlamat_kejahatan();
		idPetugas = array.get(0).getId_petugas();
		idPengguna = array.get(0).getId_pengguna();

		if (idPetugas != null) {
			user = "Kepolisian Kota Bandung";
		} else if (idPengguna != null) {
			user = array.get(0).getNama_pengguna();
		}

		imgUrl = (ApiReferences.getUrlImage() + array.get(0)
				.getFoto_kejahatan()).replaceAll(" ", "%20");

		imgLoader = VolleySingleton.getInstance(context).getImageLoader();
		imgLoader.get(imgUrl, new ImageListener() {

			@Override
			public void onErrorResponse(VolleyError error) {

				imgKejahatan.setImageResource(R.drawable.ic_launcher);
			}

			@Override
			public void onResponse(ImageContainer response, boolean arg1) {

				if (response.getBitmap() != null) {
					imgKejahatan.setImageBitmap(response.getBitmap());
				}
			}
		});

		txtJudul.setText(judul);
		txtTag.setText("#" + tag.toLowerCase());
		txtDesc.setText(desc);
		txtUser.setText(user);
		txtTgl.setText(tgl);
		txtAlamat.setText(alamat);
	}

	public void declareView() {
		txtJudul = (TextView) findViewById(R.id.txt_detail_judul);
		txtDesc = (TextView) findViewById(R.id.txt_detail_desc);
		txtTag = (TextView) findViewById(R.id.txt_detail_tag);
		txtUser = (TextView) findViewById(R.id.txt_detail_user);
		txtTgl = (TextView) findViewById(R.id.txt_detail_tgl);
		txtAlamat = (TextView) findViewById(R.id.txt_detail_alamat);
		imgKejahatan = (ImageView) findViewById(R.id.img_detail_foto_kejahatan);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		getMenuInflater().inflate(R.menu.detail_timeline, menu);
		return true;
	}

}
