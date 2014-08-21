package com.Codelabs.crimezone.adapter;

import java.util.ArrayList;


import com.Codelabs.crimezone.R;
import com.Codelabs.crimezone.model.ModelLaporanKegiatan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AdapterLaporanKegiatan extends BaseAdapter {

	private ArrayList<ModelLaporanKegiatan.Item> _data;
	Context _context;

	public AdapterLaporanKegiatan(ArrayList<ModelLaporanKegiatan.Item> data,
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
			v = inflateLayout.inflate(R.layout.list_item_kegiatan, null);
		}

		TextView titleLaporan = (TextView) v
				.findViewById(R.id.txt_timeline_nama_kegiatan);
		TextView tglKegiatan = (TextView) v
				.findViewById(R.id.txt_timeline_tgl_kegiatan);
		TextView alamatKejadian = (TextView) v
				.findViewById(R.id.txt_timeline_alamat_kegiatan);

		titleLaporan.setText(_data.get(position).getJudul_kegiatan());
		tglKegiatan.setText(_data.get(position).getTanggal_kegiatan());
		alamatKejadian.setText(_data.get(position).getAlamat_kegiatan());

		return v;
	}
}