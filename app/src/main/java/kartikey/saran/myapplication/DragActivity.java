package kartikey.saran.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class DragActivity extends AppCompatActivity implements View.OnTouchListener, View.OnDragListener{

    ImageView head, leftL, rightL, leftH, rightH, center, hideShowPanel;
    int headI = 0, lLI = 0, rLI = 0, lHI = 0, rHI = 0, centerI = 0;
    ImageView hD, lLD, rLD, lHD, rHD, cD;
    ImageView destination;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag);

        head = findViewById(R.id.imgHead);
        leftH = findViewById(R.id.imgLeftHand);
        leftL = findViewById(R.id.imgLeftLeg);
        rightH = findViewById(R.id.imgRightHand);
        rightL = findViewById(R.id.imgRightLeg);
        center = findViewById(R.id.imgCenter);
        hideShowPanel = findViewById(R.id.imgShowPanel);
        hD = findViewById(R.id.finalImgHead);
        cD = findViewById(R.id.finalImgC);
        lLD = findViewById(R.id.finalImgLL);
        rLD = findViewById(R.id.finalImgRL);
        lHD = findViewById(R.id.finalImgLH);
        rHD = findViewById(R.id.finalImgRH);
        destination = findViewById(R.id.destination);


        if(Application.objs != null) {
            for(int i = 0; i < Application.objs.size(); i++) {
                if(Application.objs.get(i).getTitle().contains("Head")) {
                    headI = 1;
                }
                if(Application.objs.get(i).getTitle().contains("Center")) {
                    centerI = 1;
                }
                if(Application.objs.get(i).getTitle().contains("Left Leg")) {
                    lLI = 1;
                }
                if(Application.objs.get(i).getTitle().contains("Right Leg")) {
                    rLI = 1;
                }
                if(Application.objs.get(i).getTitle().contains("Left Hand")) {
                    lHI = 1;
                }
                if(Application.objs.get(i).getTitle().contains("Right Hand")) {
                    rHI = 1;
                }
            }
        }

        if(headI == 0) {
            head.setVisibility(View.GONE);
            hD.setVisibility(View.VISIBLE);
        }
        if(lLI == 0) {
            leftL.setVisibility(View.GONE);
            lLD.setVisibility(View.VISIBLE);
        }
        if(rLI == 0) {
            rightL.setVisibility(View.GONE);
            rLD.setVisibility(View.VISIBLE);
        }
        if(lHI == 0) {
            leftH.setVisibility(View.GONE);
            lHD.setVisibility(View.VISIBLE);
        }
        if(rHI == 0) {
            rightH.setVisibility(View.GONE);
            rHD.setVisibility(View.VISIBLE);
        }


        hideShowPanel.setOnClickListener(v->{
            if(findViewById(R.id.linearLayout).getVisibility() == View.VISIBLE) {
                findViewById(R.id.linearLayout).setVisibility(View.GONE);
                hideShowPanel.setImageResource(R.drawable.ic_show_more);
            } else {
                findViewById(R.id.linearLayout).setVisibility(View.VISIBLE);
                hideShowPanel.setImageResource(R.drawable.ic_show_less);
            }
        });

        findViewById(R.id.btn).setOnClickListener(v->{
            startActivity(new Intent(DragActivity.this, ColorActivity.class));
        });

        head.setOnTouchListener(this);
        leftL.setOnTouchListener(this);
        rightL.setOnTouchListener(this);
        leftH.setOnTouchListener(this);
        rightH.setOnTouchListener(this);
        center.setOnTouchListener(this);

        destination.setOnDragListener(this);

    }


    @Override
    public boolean onDrag(View v, DragEvent event) {

        switch (event.getAction()) {
            case DragEvent.ACTION_DRAG_STARTED:
                findViewById(R.id.linearLayout).setVisibility(View.GONE);

                ((ImageView) v).setColorFilter(Color.YELLOW);
                v.invalidate();
                return true;

            case DragEvent.ACTION_DRAG_ENTERED:

                String clipData = event.getClipDescription().getLabel().toString();
                ((ImageView) v).setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.teal_200), android.graphics.PorterDuff.Mode.MULTIPLY);
                v.invalidate();
                return true;

            case DragEvent.ACTION_DRAG_LOCATION:
                return true;

            case DragEvent.ACTION_DRAG_EXITED:

                ((ImageView) v).clearColorFilter();
                ((ImageView) v).setColorFilter(Color.YELLOW);

                v.invalidate();
                return true;

            case DragEvent.ACTION_DROP:


                clipData = event.getClipDescription().getLabel().toString();
                switch (clipData){
                    case "head":
                        hD.setVisibility(View.VISIBLE);
                        hideShowPanel.setImageResource(R.drawable.ic_show_more);
                        head.setVisibility(View.GONE);
                        break;
                    case "center" :
                        cD.setVisibility(View.VISIBLE);
                        center.setVisibility(View.GONE);
                        hideShowPanel.setImageResource(R.drawable.ic_show_more);
                        break;
                    case "lefthand":
                        lHD.setVisibility(View.VISIBLE);
                        leftH.setVisibility(View.GONE);
                        hideShowPanel.setImageResource(R.drawable.ic_show_more);
                        break;
                    case "righthand":
                        rHD.setVisibility(View.VISIBLE);
                        rightH.setVisibility(View.GONE);
                        hideShowPanel.setImageResource(R.drawable.ic_show_more);
                        break;
                    case "leftleg":
                        lLD.setVisibility(View.VISIBLE);
                        leftL.setVisibility(View.GONE);
                        hideShowPanel.setImageResource(R.drawable.ic_show_more);
                        break;
                    case "rightleg":
                        rLD.setVisibility(View.VISIBLE);
                        rightL.setVisibility(View.GONE);
                        hideShowPanel.setImageResource(R.drawable.ic_show_more);
                        break;
                    default:
                        Toast.makeText(this, "Try Again", Toast.LENGTH_SHORT).show();
                        break;
                }

                v.invalidate();
                return true;

            case DragEvent.ACTION_DRAG_ENDED:


                ((ImageView) v).clearColorFilter();
                if (event.getResult()) {
                    Toast.makeText(this, "Awesome!", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(this, "Aw Snap! Try dropping it again", Toast.LENGTH_SHORT).show();
                }

                if(hD.getVisibility() == cD.getVisibility() && cD.getVisibility() == lLD.getVisibility() && lLD.getVisibility() == rLD.getVisibility() && rLD.getVisibility() == lHD.getVisibility() && lHD.getVisibility() == rHD.getVisibility() && rHD.getVisibility() == View.VISIBLE) {
                    findViewById(R.id.btn).setEnabled(true);
                }

                return true;

            default:
                return false;
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        View.DragShadowBuilder mShadow = new View.DragShadowBuilder(v);
        ClipData.Item item = new ClipData.Item(v.getTag().toString());
        String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};
        ClipData data = new ClipData(v.getTag().toString(), mimeTypes, item);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            v.startDragAndDrop(data, mShadow, null, 0);
        } else {
            v.startDrag(data, mShadow, null, 0);
        }
        return false;
    }
}