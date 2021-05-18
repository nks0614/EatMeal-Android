package com.project.eatmeal.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

import com.project.domain.model.response.Food;
import com.project.eatmeal.R;


public class MenuCustomDialog extends Dialog {

    private MenuCustomDialog dialog;
    private Food food;

    public MenuCustomDialog(Context context, Food food) {
        super(context);
        this.food = food;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowManager.LayoutParams window = new WindowManager.LayoutParams();
        window.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        window.dimAmount = 0.5f;
        getWindow().setAttributes(window);

        setContentView(R.layout.dialog_menu_detail);
        dialog = this;

        TextView breakfast = (TextView) this.findViewById(R.id.breakfastFreText);
        TextView lunch = (TextView) this.findViewById(R.id.lunchFreText);
        TextView dinner = (TextView) this.findViewById(R.id.dinnerFreText);

        breakfast.setText(String.valueOf(food.getBreakfast()));
        lunch.setText(String.valueOf(food.getLunch()));
        dinner.setText(String.valueOf(food.getDinner()));
    }
}
