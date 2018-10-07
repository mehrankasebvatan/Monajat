package app.mehran.monajat.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import app.mehran.monajat.R;
import app.mehran.monajat.models.DoaModel;

public class DoaAdapter extends RecyclerView.Adapter<DoaAdapter.ViewHolder> {
    private ArrayList<DoaModel> doaRV ;
    private Context context ;

    public DoaAdapter (Context context , ArrayList<DoaModel> doaRV){
        this.context = context ;
        this.doaRV = doaRV;
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView arabic , persian ;

        public ViewHolder(View itemView){
            super(itemView);
            arabic = itemView.findViewById(R.id.Arabic);
            persian = itemView.findViewById(R.id.Persian);
            Typeface arabi = Typeface.createFromAsset(itemView.getContext().getAssets(),"fonts/taha.ttf");
            arabic.setTypeface(arabi);


        }

        @Override
        public void onClick(View v) {

        }
    }


    @Override
    public DoaAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.doa_view, parent, false);
        return new ViewHolder(view);

    }


    @Override
    public void onBindViewHolder(DoaAdapter.ViewHolder holder, int position) {
        final DoaModel modelItem = doaRV.get(position);

        holder.persian.setText(modelItem.getPersian());
        holder.arabic.setText(modelItem.getArabic());

    }



    @Override
    public int getItemCount() {
        return doaRV.size();
    }










}

