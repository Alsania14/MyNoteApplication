package id.alin_gotama.mynoteapplication.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import id.alin_gotama.mynoteapplication.AfterMainActivity;
import id.alin_gotama.mynoteapplication.DetailActivity;
import id.alin_gotama.mynoteapplication.R;
import id.alin_gotama.mynoteapplication.entity.Note;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {
    private ArrayList<Note> notes = new ArrayList<Note>();
    private Context context;

    public CustomAdapter(ArrayList<Note> notes, Context context) {
        this.notes = notes;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context = parent.getContext();

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row,parent,false);

        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
            holder.tvDate.setText(this.notes.get(position).getDate());
            holder.tvDescription.setText(this.notes.get(position).getDescription());
            holder.tvTitle.setText(this.notes.get(position).getTitle());

            holder.cvNote.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent detailactivity = new Intent(context, DetailActivity.class);
                    Bundle bundle = new Bundle();

                    Note note = new Note();
                    note = notes.get(position);

                    bundle.putParcelable("note",note);
                    detailactivity.putExtra("bundle",bundle);
                    ((Activity) context).finish();
                    context.startActivity(detailactivity);
                }
            });

            holder.btnDelete.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    ((AfterMainActivity) context).dialog(notes.get(position),context);
                }

            });
    }

    @Override
    public int getItemCount() {
        return this.notes.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle;
        private TextView tvDescription;
        private TextView tvDate;
        private CardView cvNote;
        private Button btnDelete;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tvTitle = itemView.findViewById(R.id.tvTitle);
            this.tvDescription = itemView.findViewById(R.id.tvDescription);
            this.tvDate = itemView.findViewById(R.id.tvDate);
            this.cvNote = itemView.findViewById(R.id.cvNote);
            this.btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}
