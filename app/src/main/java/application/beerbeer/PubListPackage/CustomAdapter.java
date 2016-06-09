package application.beerbeer.PubListPackage;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;

import application.beerbeer.R;
import application.beerbeer.ResponsePack.Response;

/**
 * Created by KOMPUTOR on 2016-05-16.
 */
public class CustomAdapter extends BaseAdapter implements Filterable{
    private List<Response.PubsBean> pubs;
    private Context context;
    private LayoutInflater layoutInflater;
    private List<Response.PubsBean> pubmem;
    private Filter filter;
    private Response objResponse;
    public CustomAdapter(Context context, List<Response.PubsBean> pubs) {
        this.context = context;
        this.pubs = pubs;
        this.pubmem = pubs;
    }


    public void setObjResponse(Response objResponse)
    {
        this.objResponse = objResponse;
    }

    public void setPubs( List<Response.PubsBean> pubs)
    {
        this.pubs = pubs;
    }
    public  List<Response.PubsBean> getpubmem()
    {
        return pubmem;
    }
    @Override
    public int getCount() {
        return pubs.size();
    }

    @Override
    public Object getItem(int position) {
        return pubs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    /**
     *
     * @param position
     * @param convertView
     * @param parent
     * getting data from PubListActivity by intents
     * @return row which contains pub name, image of pub(setting by Picasso),
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rowView = layoutInflater.inflate(R.layout.rowpubs, parent, false);
            TextView pub = (TextView) rowView.findViewById(R.id.textView);
            ImageView pubImage = (ImageView) rowView.findViewById(R.id.pubImage);
            Response.PubsBean item = (Response.PubsBean) getItem(position);
            pub.setText(item.getPub());

            try{
                Picasso.with(context).load(item.getLink()).into(pubImage);
            } catch (IllegalArgumentException e)
            {
                Log.d("blad","blad");
            }


            return rowView;

    }

    @Override
    public Filter getFilter() {
            if(filter == null)
            {
                filter = new CustomFilter();

            }

        return filter;
    }



private class CustomFilter extends Filter
{

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults filterResults = new FilterResults();
        List<Response.PubsBean> pubslist= pubs;
        List<Response.BeersBean> beerlist= objResponse.getBeers();
        beerlist = objResponse.getBeers();
        if(constraint == null || constraint.length() == 0)
        {
            filterResults.values = pubslist;
            filterResults.count = pubslist.size();
        }else {
            ArrayList<Response.PubsBean> filteredPubs = new ArrayList<Response.PubsBean>();
            for (Response.BeersBean b : beerlist)
            {
                Log.d("beerlist ",b.getPiwo().toLowerCase()+" "+ constraint.toString().toLowerCase());
                if (b.getPiwo().toLowerCase().contains(constraint.toString().toLowerCase()))
                {
                    for (Response.PubsBean p : pubslist) {

                        if (p.getPub().toLowerCase().equals(b.getPub().toLowerCase())) {
                            //Log.d("filteredpubs",p.getPub().toLowerCase());
                            filteredPubs.add(p);
                        }
                    }
                }
            }
            HashSet<Response.PubsBean> temp = new HashSet<Response.PubsBean>();
            temp.addAll(filteredPubs);
            filteredPubs.clear();
            filteredPubs.addAll(temp);
            filterResults.values = filteredPubs;
            filterResults.count = filteredPubs.size();

        /*    for(Response.PubsBean j: pubslist)
            {
                if(j.getPub().toLowerCase().contains(constraint.toString().toLowerCase()))
                {

                    filteredPubs.add(j);
                }
                filterResults.values = filteredPubs;
                filterResults.count = filteredPubs.size();
            }
        */
        }
        return filterResults;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        if(results.count ==0)
        {

            notifyDataSetInvalidated();
        }else
        {

            pubs = (List<Response.PubsBean>) results.values;
            notifyDataSetChanged();


        }
    }

};

}
