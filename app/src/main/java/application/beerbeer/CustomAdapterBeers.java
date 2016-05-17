package application.beerbeer;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

/**
 * Created by KOMPUTOR on 2016-05-17.
 */
public class CustomAdapterBeers extends BaseAdapter {
    private List<Response.BeersBean> beers;
    private Context context;
    private LayoutInflater layoutInflater;

    public CustomAdapterBeers(Context context, List<Response.BeersBean> beers) {
        this.context = context;
        this.beers = beers;
    }


    @Override
    public int getCount() {
        return beers.size();
    }

    @Override
    public Object getItem(int position) {
        return beers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = layoutInflater.inflate(R.layout.rowbeers, parent,false);
        TextView beer = (TextView) rowView.findViewById(R.id.textViewbeers);
        ProgressBar progressBar = (ProgressBar) rowView.findViewById(R.id.progbar);
        Response.BeersBean item = (Response.BeersBean) getItem(position);
        beer.setText(item.getPiwo());
        progressBar.setProgress(Integer.parseInt(item.getProgress()));
        return rowView;
    }
}
