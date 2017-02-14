package com.example.pc.testandroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by PC on 14/02/2017.
 */

public class ListMatchCalendrierAdapter extends ArrayAdapter<Equipe> {

//tweets est la liste des models à afficher
public ListMatchCalendrierAdapter(Context context, List<Equipe> equipes){
        super(context, 0, equipes);
        }

@Override
public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_match_calendrier,parent, false);
        }

        TweetViewHolder viewHolder = (TweetViewHolder) convertView.getTag();
        if(viewHolder == null){
        viewHolder = new TweetViewHolder();
        viewHolder.EQ1 = (TextView) convertView.findViewById(R.id.equipe1Text);
        viewHolder.EQ2 = (TextView) convertView.findViewById(R.id.equipe2Text);
        viewHolder.IMG = (ImageView) convertView.findViewById(R.id.ImageEQ1);
        convertView.setTag(viewHolder);
        }

        //getItem(position) va récupérer l'item [position] de la List<Tweet> tweets
        Equipe mesEquipes = getItem(position);

        //il ne reste plus qu'à remplir notre vue
        viewHolder.EQ1.setText(mesEquipes.getNom());
        viewHolder.EQ2.setText(mesEquipes.getEntraineur());
        viewHolder.IMG.setImageResource(R.drawable.logo_asm);

        return convertView;
        }

private class TweetViewHolder{
    public TextView EQ1;
    public TextView EQ2;
    public ImageView IMG;
}
}