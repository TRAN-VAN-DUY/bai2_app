package com.example.ticket_app.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ticket_app.R;

import java.util.List;

public class DisplayCardAdapter extends BaseAdapter {
    private final List<DisplayItem> items;
    private final LayoutInflater inflater;

    public DisplayCardAdapter(Context context, List<DisplayItem> items) {
        this.items = items;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.item_list_card, parent, false);
        }

        TextView tvTitle = view.findViewById(R.id.tvItemTitle);
        TextView tvSubtitle = view.findViewById(R.id.tvItemSubtitle);

        DisplayItem item = items.get(position);
        tvTitle.setText(item.title);
        tvSubtitle.setText(item.subtitle);
        return view;
    }
}
