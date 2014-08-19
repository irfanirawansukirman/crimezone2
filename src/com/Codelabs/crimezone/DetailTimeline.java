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
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DetailTimeline extends Activity {

	private Context context;
	ModelLaporanKejahatan model;
	RequestQueue mRequestQueue;
	ArrayList<ModelLaporanKejahatan.Item> array;
	String id_laporan_kejahatan, judul, desc, imgUrl;
	TextView txtJudul, txtDesc;
	ImageView img;
	ImageLoader imgLoader;

	NetworkChecking ch;
	Boolean checking;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detail_timeline);

		context = getApplicationContext();

		declareView();
		initData();
		Toast.makeText(context, id_laporan_kejahatan, Toast.LENGTH_LONG);

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
		desc = array.get(0).getDeskripsi_laporan_kegiatan();
		imgUrl = (ApiReferences.getUrlImage() + array.get(0)
				.getFoto_kejahatan()).replaceAll(" ", "%20");

		imgLoader = VolleySingleton.getInstance(context).getImageLoader();
		imgLoader.get(imgUrl, new ImageListener() {

			@Override
			public void onErrorResponse(VolleyError error) {

				img.setImageResource(R.drawable.ic_launcher);
			}

			@Override
			public void onResponse(ImageContainer response, boolean arg1) {

				if (response.getBitmap() != null) {
					img.setImageBitmap(response.getBitmap());
				}
			}
		});

		txtJudul.setText(judul);
		txtDesc.setText(desc);
	}

	public void declareView() {
		txtJudul = (TextView) findViewById(R.id.judul);
		txtDesc = (TextView) findViewById(R.id.desc);
		img = (ImageView) findViewById(R.id.img_detail);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		getMenuInflater().inflate(R.menu.detail_timeline, menu);
		return true;
	}

}
