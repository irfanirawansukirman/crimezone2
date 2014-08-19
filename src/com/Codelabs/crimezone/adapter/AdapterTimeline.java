package com.Codelabs.crimezone.adapter;

import java.util.ArrayList;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageContainer;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.Codelabs.crimezone.R;
import com.Codelabs.crimezone.VolleySingleton;
import com.Codelabs.crimezone.api.ApiReferences;
import com.Codelabs.crimezone.model.ModelLaporanKejahatan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AdapterTimeline extends BaseAdapter {

	ImageView img;

	private ArrayList<ModelLaporanKejahatan.Item> _data;
	Context _context;

	public AdapterTimeline(ArrayList<ModelLaporanKejahatan.Item> data,
			Context context) {
		_data = data;
		_context = context;
	}

	@Override
	public int getCount() {
		return _data.size();
	}

	@Override
	public Object getItem(int position) {
		return _data.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;

		if (v == null) {
			LayoutInflater inflateLayout = (LayoutInflater) _context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = inflateLayout.inflate(R.layout.list_item_timeline, null);
		}

		img = (ImageView) v.findViewById(R.id.img_timeline);
		TextView titleLaporan = (TextView) v
				.findViewById(R.id.txt_title_timeline);
		TextView tagPost = (TextView) v.findViewById(R.id.txt_tag_timeline);
		TextView tglPost = (TextView) v
				.findViewById(R.id.txt_tgl_post_timeline);
		TextView alamatKejadian = (TextView) v
				.findViewById(R.id.txt_address_post_timeline);

		titleLaporan.setText(_data.get(position).getJudul_laporan_kegiatan());
		tagPost.setText("#" + _data.get(position).getNama_jenis_kejahatan());
		tglPost.setText(_data.get(position).getTanggal_kejadian());
		alamatKejadian.setText(_data.get(position).getAlamat_kejahatan());
		String imgUrl = (ApiReferences.getUrlImage() + _data.get(position)
				.getFoto_kejahatan()).replaceAll(" ", "%20");
		ImageLoader imgLoader = VolleySingleton.getInstance(_context)
				.getImageLoader();
		imgLoader.get(imgUrl, new ImageListener() {
			public void onErrorResponse(VolleyError error) {
				img.setImageResource(R.drawable.ic_launcher);
			}

			public void onResponse(ImageContainer response, boolean arg1) {
				if (response.getBitmap() != null) {
					img.setImageBitmap(response.getBitmap());
				}
			}
		});

		return v;
	}
}