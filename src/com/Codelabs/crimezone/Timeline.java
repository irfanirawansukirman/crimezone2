package com.Codelabs.crimezone;

import java.util.ArrayList;

import org.json.JSONObject;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.Codelabs.crimezone.adapter.AdapterTimeline;
import com.Codelabs.crimezone.api.ApiReferences;
import com.Codelabs.crimezone.model.ModelLaporanKejahatan;
import com.Codelabs.crimezone.parser.Parser;
import com.Codelabs.crimezone.utils.NetworkChecking;

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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Timeline extends Activity {

	private Context context;
	AdapterView.AdapterContextMenuInfo info;
	ListView listLaporan;
	RequestQueue mRequestQueue;
	ModelLaporanKejahatan model;
	AdapterTimeline adapter;
	ArrayList<ModelLaporanKejahatan.Item> array;
	String id_laporan_kejahatan, judul, url_ic;
	TextView txtJudulLaporan;
	ImageView img_ic;

	NetworkChecking ch;
	Boolean checking;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_timeline);

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

		listLaporan = (ListView) findViewById(R.id.list);
		listLaporan.setOnItemClickListener(ls);

	}

	private void load() {
		loadData();
	}

	private void loadData() {
		MyVolley.init(context);
		String url = ApiReferences.getUrlLaporanKejahatan();
		mRequestQueue = MyVolley.getRequestQueue();

		JsonObjectRequest jObject = new JsonObjectRequest(Request.Method.GET,
				url, null, new Response.Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {
						Log.i("sukses", response.toString());

						model = Parser.getDataLaporanKejahatan(response
								.toString());
						array = model.getItem();
						adapter = new AdapterTimeline(array, context);
						listLaporan.setAdapter(adapter);
					}

				}, new Response.ErrorListener() {

					public void onErrorResponse(VolleyError error) {
						Log.i("error", error.toString());
					}

				});
		mRequestQueue.add(jObject);

	}

	private OnItemClickListener ls = new OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView arg0, View arg1, int position,
				long arg3) {

			String idLK = array.get(position).getId_laporan_kejahatan();

			Intent detailTimeline = new Intent(context, DetailTimeline.class);
			detailTimeline.putExtra("id_laporan_kejahatan", idLK);
			startActivity(detailTimeline);
		}
	};

	// @Override
	// public boolean onCreateOptionsMenu(Menu menu) {
	// // Inflate the menu; this adds items to the action bar if it is present.
	// getMenuInflater().inflate(R.menu.main, menu);
	// return true;
	// }

}
