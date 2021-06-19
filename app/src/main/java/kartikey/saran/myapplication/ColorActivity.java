package kartikey.saran.myapplication;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class ColorActivity extends AppCompatActivity implements View.OnTouchListener, View.OnClickListener{

    ImageView black, purple, white, blue;
    ImageView head, center, rightLeg, leftLeg, rightHand, leftHand;
    String currentSelectedImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color);
        checkPerm();
        black = findViewById(R.id.black);
        purple = findViewById(R.id.purple);
        white = findViewById(R.id.white);
        blue = findViewById(R.id.blue);
        head = findViewById(R.id.finalImgHead);
        center = findViewById(R.id.finalImgC);
        rightHand = findViewById(R.id.finalImgRH);
        leftHand = findViewById(R.id.finalImgLH);
        leftLeg = findViewById(R.id.finalImgLL);
        rightLeg = findViewById(R.id.finalImgRL);

        head.setOnTouchListener(this);
        center.setOnTouchListener(this);
        rightLeg.setOnTouchListener(this);
        leftLeg.setOnTouchListener(this);
        rightHand.setOnTouchListener(this);
        leftHand.setOnTouchListener(this);

        black.setOnClickListener(this);
        purple.setOnClickListener(this);
        white.setOnClickListener(this);
        blue.setOnClickListener(this);
        findViewById(R.id.imgShowPanel).setOnClickListener(this);
        findViewById(R.id.btn).setOnClickListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if(currentSelectedImage == "blue") {
            ((ImageView) v).setColorFilter(Color.BLUE);
        } else if(currentSelectedImage == "black") {
            ((ImageView) v).setColorFilter(Color.BLACK);
        } else if(currentSelectedImage == "white") {
            ((ImageView) v).setColorFilter(Color.WHITE);
        } else if(currentSelectedImage == "yellow") {
            ((ImageView) v).setColorFilter(Color.YELLOW);
        } else {
            Toast.makeText(this, "Select a color first", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.black:
                currentSelectedImage = "black";
                break;
            case R.id.blue:
                currentSelectedImage = "blue";
                break;
            case R.id.white:
                currentSelectedImage = "white";
                break;

            case R.id.purple:
                currentSelectedImage = "yellow";
                break;

            case R.id.imgShowPanel:
                if(findViewById(R.id.linearLayout).getVisibility() == View.GONE) {
                    findViewById(R.id.linearLayout).setVisibility(View.VISIBLE);
                    ((ImageView) findViewById(R.id.imgShowPanel)).setImageResource(R.drawable.ic_show_less);
                } else {
                    findViewById(R.id.linearLayout).setVisibility(View.GONE);
                    ((ImageView) findViewById(R.id.imgShowPanel)).setImageResource(R.drawable.ic_show_more);
                }
                break;
            case R.id.btn:
                try{
                    Bitmap b = getBitmapFromView(findViewById(R.id.contraintLayout));
                    Intent share = new Intent(Intent.ACTION_SEND);
                    share.setType("image/jpeg");
                    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                    b.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
                    String path = MediaStore.Images.Media.insertImage(getContentResolver(),
                            b, "Title", null);
                    Uri imageUri =  Uri.parse(path);
                    share.putExtra(Intent.EXTRA_STREAM, imageUri);
                    startActivity(Intent.createChooser(share, "Select"));

                }catch (Exception e) {
                    Toast.makeText(this, "Error! "+e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
                break;

            default:
                Toast.makeText(this, "Please select a color", Toast.LENGTH_SHORT).show();
                break;

        }
    }

    private Bitmap getBitmapFromView(View view) {
        //Define a bitmap with the same size as the view
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(),Bitmap.Config.ARGB_8888);
        //Bind a canvas to it
        Canvas canvas = new Canvas(returnedBitmap);
        //Get the view's background
        Drawable bgDrawable =view.getBackground();
        if (bgDrawable!=null) {
            //has background drawable, then draw it on the canvas
            bgDrawable.draw(canvas);
        }   else{
            //does not have background drawable, then draw white background on the canvas
            canvas.drawColor(Color.WHITE);
        }
        // draw the view on the canvas
        view.draw(canvas);
        //return the bitmap
        return returnedBitmap;
    }
    private void checkPerm() {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
                alertBuilder.setCancelable(true);
                alertBuilder.setMessage("Write external storage is necessary to share image!!!");
                alertBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityCompat.requestPermissions(ColorActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                    }
                });
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            }
        }
    }
}