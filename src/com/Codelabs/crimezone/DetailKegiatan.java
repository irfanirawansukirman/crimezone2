package com.Codelabs.crimezone;

import java.util.ArrayList;

import org.json.JSONObject;

import com.Codelabs.crimezone.api.ApiReferences;
import com.Codelabs.crimezone.model.ModelLaporanKegiatan;
import com.Codelabs.crimezone.model.ModelLaporanKejahatan;
import com.Codelabs.crimezone.parser.Parser;
import com.Codelabs.crimezone.utils.NetworkChecking;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Menu;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DetailKegiatan extends Activity {

	private Context context;
	ModelLaporanKegiatan model;
	RequestQueue mRequestQueue;
	ArrayList<ModelLaporanKegiatan.Item> array;
	String id_kegiatan, judul, desc, tgl, waktu, alamat, imgUrl;
	TextView txtJudul, txtTgl, txtAlamat, txtDesc;
	ImageView imgKegiatan;
	ImageLoader imgLoader;
	int width;

	NetworkChecking ch;
	Boolean checking;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.detail_kegiatan);

		context = getApplicationContext();

		declareView();
		initData();

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

	public void load() {
		loadData();
	}

	public void initData() {
		Bundle extras = getIntent().getExtras();
		id_kegiatan = extras.getString("id_kegiatan");
	}

	private void loadData() {
		MyVolley.init(context);
		String filter = "?id_kegiatan=" + id_kegiatan;
		String url = ApiReferences.getUrlLaporanKegiatan() + filter;
		mRequestQueue = MyVolley.getRequestQueue();

		JsonObjectRequest jObject = new JsonObjectRequest(Request.Method.GET,
				url, null, new Response.Listener<JSONObject>() {
					@Override
					public void onResponse(JSONObject response) {
						Log.i("sukses", response.toString());

						model = Parser.getDataLaporanKegiatan(response
								.toString());
						array = model.getItem();

						setData();
					}
				}, new Response.ErrorListener() {
					public void onErrorResponse(VolleyError error) {
						Log.i("error", error.toString());
					}
				});
		mRequestQueue.add(jObject);
	}

	private void setData() {
		judul = array.get(0).getJudul_kegiatan();
		tgl = array.get(0).getTanggal_kegiatan();
		waktu = array.get(0).getWaktu_kegiatan();
		alamat = array.get(0).getAlamat_kegiatan();
		desc = array.get(0).getDeskripsi_kegiatan();

		txtJudul.setText(judul);
		txtTgl.setText(tgl + ", " + waktu);
		txtAlamat.setText(alamat);
		txtDesc.setText(desc);
	}

	public void declareView() {
		txtJudul = (TextView) findViewById(R.id.txt_detail_kegiatan_judul);
		txtTgl = (TextView) findViewById(R.id.txt_detail_kegiatan_tgl);
		txtAlamat = (TextView) findViewById(R.id.txt_detail_kegiatan_lokasi);
		txtDesc = (TextView) findViewById(R.id.txt_detail_kegiatan_desc);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.detail_kegiatan, menu);
		return true;
	}

}
