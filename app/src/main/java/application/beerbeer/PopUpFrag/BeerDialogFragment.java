package application.beerbeer.PopUpFrag;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import application.beerbeer.R;

/**
 * Created by KOMPUTOR on 2016-06-07.
 */
public class BeerDialogFragment extends DialogFragment {
        private String BeerName = "";
        private String beerLink = "";
        private String halfPricestr = "";
        private String threePricestr = "";
        TextView beerName,halfPrice,threePrice;
        ImageView imageView;

    public BeerDialogFragment()
    {

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dialog_layout,container,false );
        getDialog().setTitle("");
        halfPrice = (TextView) rootView.findViewById(R.id.halfpricefrag);
        threePrice = (TextView) rootView.findViewById(R.id.threepricefrag);
        beerName = (TextView) rootView.findViewById(R.id.beernamefrag);

        halfPrice.setText("0,5l = "+halfPricestr + "zł");
        threePrice.setText("0,3l = "+threePricestr + "zł");
        beerName.setText(BeerName);


        imageView = (ImageView) rootView.findViewById(R.id.fragImage);
        try{
            Picasso.with(getActivity()).load(beerLink).into(imageView);
            Log.d("git","git");
        } catch (IllegalArgumentException e)
        {
            Log.d("blad", "blad");
        }



        return rootView;
    }






    public void setBeerName(String beerName)
    {
        this.BeerName = beerName;
    }
    public void setImage(String link)
    {
        this.beerLink = link;
    }


    public void setHalfPricestr(String halfPricestr) {
        this.halfPricestr = halfPricestr;
    }

    public void setThreePricestr(String threePricestr) {
        this.threePricestr = threePricestr;
    }
}
