package com.Codelabs.crimezone;

import java.util.ArrayList;

import org.json.JSONObject;

import com.Codelabs.crimezone.adapter.AdapterLaporanKegiatan;
import com.Codelabs.crimezone.api.ApiReferences;
import com.Codelabs.crimezone.model.ModelLaporanKegiatan;
import com.Codelabs.crimezone.parser.Parser;
import com.Codelabs.crimezone.utils.NetworkChecking;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class LaporanKegiatanPolisi extends Activity {

	private Context context;
	ListView listKegiatan;
	RequestQueue mRequestQueue;
	ModelLaporanKegiatan model;
	AdapterLaporanKegiatan adapter;
	ArrayList<ModelLaporanKegiatan.Item> array;

	NetworkChecking ch;
	Boolean checking;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_kegiatan_polisi);

		context = getApplicationContext();

		ch = new NetworkChecking(context);
		checking = null;
		checking = ch.isConnectingToInternet();

		if (checking) {
			load();
		} else {
			Toast.makeText(context, "Please Check Your Internet Connection!",
					Toast.LENGTH_LONG).show();
		}

		listKegiatan = (ListView) findViewById(R.id.list_kegiatan_polisi);
		listKegiatan.setOnItemClickListener(lsK);
	}

	private void load() {
		loadData();
	}

	private void loadData() {
		MyVolley.init(context);
		String url = ApiReferences.getUrlLaporanKegiatan();
		mRequestQueue = MyVolley.getRequestQueue();

		JsonObjectRequest jObject = new JsonObjectRequest(Request.Method.GET,
				url, null, new Response.Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {
						Log.i("sukses", response.toString());

						model = Parser.getDataLaporanKegiatan(response
								.toString());
						array = model.getItem();
						adapter = new AdapterLaporanKegiatan(array, context);
						listKegiatan.setAdapter(adapter);
					}
				}, new Response.ErrorListener() {
					public void onErrorResponse(VolleyError error) {
						Log.i("error", error.toString());
					}
				});

		mRequestQueue.add(jObject);

	}

	private OnItemClickListener lsK = new OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView arg0, View arg1, int position,
				long arg3) {
			String idKP = array.get(position).getId_kegiatan();

			Intent detailKegiatan = new Intent(context, DetailKegiatan.class);
			detailKegiatan.putExtra("id_kegiatan", idKP);
			startActivity(detailKegiatan);
		}
	};

	// @Override
	// public boolean onCreateOptionsMenu(Menu menu) {
	// // Inflate the menu; this adds items to the action bar if it is present.
	// getMenuInflater().inflate(R.menu.laporan_kegiatan_polisi, menu);
	// return true;
	// }

}
