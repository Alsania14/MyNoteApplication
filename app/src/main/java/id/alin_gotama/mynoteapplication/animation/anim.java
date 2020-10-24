package id.alin_gotama.mynoteapplication.animation;

import android.app.Activity;
import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;

import id.alin_gotama.mynoteapplication.R;

public class anim {
    private Context context;

    public anim(Context context) {
        this.context = context;
    }

    public void runFadeInAnimation(int id) {
        Animation a = AnimationUtils.loadAnimation(context, R.anim.fadein);
        a.reset();
        FrameLayout ll = (FrameLayout) ((Activity) this.context).findViewById(id);
        ll.clearAnimation();
        ll.startAnimation(a);
    }

    public void runFadeOutAnimation(int id) {
        Animation a = AnimationUtils.loadAnimation(context, R.anim.fadeout);
        a.reset();
        FrameLayout ll = (FrameLayout) ((Activity) this.context).findViewById(id);
        ll.clearAnimation();
        ll.startAnimation(a);
    }
}
