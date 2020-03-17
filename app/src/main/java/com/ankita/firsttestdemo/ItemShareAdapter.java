package com.ankita.firsttestdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class ItemShareAdapter extends RecyclerView.Adapter<ItemShareAdapter.MyViewHolder> {
    private  ArrayList<InvestedShare> arrayList;
    private Context context;

    public ItemShareAdapter(ArrayList<InvestedShare> arrayList,Context context){
        this.arrayList=arrayList;
        this.context=context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_share, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tvShareName.setText(arrayList.get(position).getShare());
        holder.tvBuy.setText(String.valueOf(arrayList.get(position).getBuy()));
        holder.tvSell.setText(String.valueOf(arrayList.get(position).getSell()));
        double profit = arrayList.get(position).getSell() - arrayList.get(position).getBuy();
        holder.tvProfit.setText(String.valueOf(getDoubleOfSomePrecision(profit)));
    }
    private Double getDoubleOfSomePrecision(Double val){
      return BigDecimal.valueOf(val)
                .setScale(3, RoundingMode.HALF_UP)
                .doubleValue();
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvShareName,tvBuy,tvSell,tvProfit;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvShareName=itemView.findViewById(R.id.tvShareName);
            tvBuy=itemView.findViewById(R.id.tvBuy);
            tvSell=itemView.findViewById(R.id.tvSell);
            tvProfit=itemView.findViewById(R.id.tvProfit);



        }
    }
}
