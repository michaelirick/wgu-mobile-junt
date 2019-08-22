package com.michaelirick.wguscheduler;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public class CollapsePanel {
    public Button toggle;
    public LinearLayout body;

    public CollapsePanel(Button t, LinearLayout b) {
        toggle = t;
        body = b;
    }

    public void toggleView() {
        ViewGroup.LayoutParams lp = body.getLayoutParams();
        if(body.getVisibility() == View.INVISIBLE) {
            body.setVisibility(View.VISIBLE);
            lp.height = WRAP_CONTENT;
        } else {
            body.setVisibility(View.INVISIBLE);
            lp.height = 0;
        }
        body.setLayoutParams(lp);
    }

    public void create() {
        toggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleView();
            }
        });
    }
}
