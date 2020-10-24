package id.alin_gotama.mynoteapplication;

import android.content.Context;
import android.util.Log;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CustomScrollListener extends RecyclerView.OnScrollListener {
    private Button btnTambah;

    public CustomScrollListener(Button btnTambah) {
        this.btnTambah = btnTambah;
    }

    @Override
    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        switch (newState) {
            case RecyclerView.SCROLL_STATE_IDLE:
                btnTambah.setAlpha((float) 0.75);
                break;
            case RecyclerView.SCROLL_STATE_DRAGGING:
                btnTambah.setAlpha((float) 0.25);
                break;
            case RecyclerView.SCROLL_STATE_SETTLING:
                btnTambah.setAlpha((float) 0.75);
                break;

        }
    }
}
