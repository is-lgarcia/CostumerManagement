package com.luisgarciasv.costumermanagement.view.fragments;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.luisgarciasv.costumermanagement.R;
import com.luisgarciasv.costumermanagement.model.Costumer;
import com.luisgarciasv.costumermanagement.view.CostumerListener;

import java.util.List;

class CostumerAdapter extends RecyclerView.Adapter<CostumerAdapter.ViewHolder> {

    private List<Costumer> mValues;
    private CostumerListener listener;

    public CostumerAdapter(List<Costumer> mValues, CostumerListener listener) {
        this.mValues = mValues;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_costumer,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Costumer mItem = mValues.get(position);
        String nameInitial = mItem.getName().substring(0,1).toUpperCase();
        String lastNameInitial = mItem.getLastName().substring(0,1).toUpperCase();

        holder.txtLetter.setText(String.format("%s%s", nameInitial, lastNameInitial));
        holder.txtFullName.setText(String.format("%s %s",mItem.getName(),mItem.getLastName()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.getDuiClicked(mItem.getDui());
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mValues== null){
            return 0;
        } else {
            return mValues.size();
        }
    }

    public void setData (List<Costumer> newItems){
        mValues = newItems;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final TextView txtLetter, txtFullName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtLetter = itemView.findViewById(R.id.txtLetter);
            txtFullName = itemView.findViewById(R.id.txtName);
        }
    }
}
