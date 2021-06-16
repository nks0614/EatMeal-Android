package com.project.eatmeal.widget;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.project.domain.model.body.StarBody;
import com.project.domain.model.response.Food;
import com.project.domain.model.response.MemberStarFood;
import com.project.domain.usecase.GiveStarUseCase;
import com.project.eatmeal.R;
import com.project.eatmeal.data.CashingData;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;


public class MenuCustomDialog extends Dialog {

    private MenuCustomDialog dialog;
    private Food food;
    private GiveStarUseCase giveStarUseCase;
    private Disposable disposable;
    private Boolean isGivenStar = false;
    private String mac = CashingData.INSTANCE.getMAC_ADDRESS();
    public MutableLiveData<Boolean> event = new MutableLiveData<>();


    public MenuCustomDialog(Context context, Food food, GiveStarUseCase giveStarUseCase) {
        super(context);
        this.food = food;
        this.giveStarUseCase = giveStarUseCase;
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

        TextView name = (TextView) this.findViewById(R.id.nameText);
        TextView breakfast = (TextView) this.findViewById(R.id.breakfastFreText);
        TextView lunch = (TextView) this.findViewById(R.id.lunchFreText);
        TextView dinner = (TextView) this.findViewById(R.id.dinnerFreText);
        TextView star = (TextView) this.findViewById(R.id.starText);
        RatingBar ratingBar = (RatingBar) this.findViewById(R.id.starRatingBar);
        TextView giveStarBtn = (TextView) this.findViewById(R.id.giveStarBtn);

        name.setText(food.getName());
        breakfast.setText(String.valueOf(food.getBreakfast()));
        lunch.setText(String.valueOf(food.getLunch()));
        dinner.setText(String.valueOf(food.getDinner()));
        star.setText(String.format("%.1f", food.getStar()));


        for(MemberStarFood m : food.getStarMember()) {
            if(m.getMac().equals(mac)) {
                giveStarBtn.setText("이미 별점을 주셨습니다");
                isGivenStar = true;
            }
        }

        giveStarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isGivenStar) return;
                if(ratingBar.getRating() == 0) {
                    Toast.makeText(getContext(), "별점은 1점 이상 주셔야 합니다.", Toast.LENGTH_SHORT).show();
                    return;
                }
                disposable = giveStarUseCase.execute(
                        new StarBody(mac, (int) (ratingBar.getRating() * 2), food.getName()))
                        .subscribe(new Consumer<Integer>() {
                            @Override
                            public void accept(Integer integer) throws Exception {
                                String str = "별점 "+(int) (ratingBar.getRating() * 2)+"점을 성공적으로 주셨습니다!";
                                Toast.makeText(getContext(), str, Toast.LENGTH_SHORT).show();
                                event.setValue(false);
                                dismiss();
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                Toast.makeText(getContext(), "별점 주기에 실패하셨습니다!", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });


    }

    @Override
    public void dismiss() {
        if(disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }

        super.dismiss();
    }
}
