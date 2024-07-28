import android.annotation.Nullable;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

class Scratch {
    public static void main(String[] args) {

    }


    public void setOnClickListener(@Nullable View.OnClickListener l) {
        if (!isClickable()) {
            setClickable(true);
        }
        getListenerInfo().mOnClickListener = l;
    }

}