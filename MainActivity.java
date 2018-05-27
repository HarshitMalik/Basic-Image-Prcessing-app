package com.example.harshit.myapplication;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageView img;
    Bitmap orginalImg,currentImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button click = (Button)findViewById(R.id.bCapture);
        img = (ImageView)findViewById(R.id.imageView);
        if(!hasCamera()) {
            click.setEnabled(false);
        }
        img.setImageBitmap(orginalImg);
        Toast myToast = Toast.makeText(getApplicationContext(), "This App is created by Harshit", Toast.LENGTH_LONG);
        myToast.show();
    }
    public static final int REQUEST_CAPTURE = 1;

    public boolean hasCamera(){
      return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_CAPTURE && resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            Bitmap photo = (Bitmap) extras.get("data");
            img.setImageBitmap(photo);
            Toast myToast = Toast.makeText(getApplicationContext(), "photo captured", Toast.LENGTH_SHORT);
            myToast.show();
            orginalImg = photo;
        }
    }

    void launchCamera(View view ){
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(i,REQUEST_CAPTURE);
    }

    void onInvertClicked(View view){

        Bitmap orgImg = ((BitmapDrawable)img.getDrawable()).getBitmap();
        Bitmap photo = Bitmap.createBitmap(orgImg.getWidth(),orgImg.getHeight(),orgImg.getConfig());


        int A,R,G,B;
        int height=orgImg.getHeight();
        int width=orgImg.getWidth();
        int pixColor;
        int x,y;
        for(x=0; x<height; x++){
            for(y=0; y<width; y++){
                pixColor=orgImg.getPixel(y,x);
                A=Color.alpha(pixColor);
                R=255- Color.red(pixColor);
                G=255- Color.green(pixColor);
                B=255- Color.blue(pixColor);
              photo.setPixel(y,x,Color.argb(A,R,G,B));
            }
        }
        img.setImageBitmap(photo);
    }

    void onBrightClicked(View view){

        Bitmap orgImg = ((BitmapDrawable)img.getDrawable()).getBitmap();
        Bitmap photo = Bitmap.createBitmap(orgImg.getWidth(),orgImg.getHeight(),orgImg.getConfig());


        int A,R,G,B;
        int height=orgImg.getHeight();
        int width=orgImg.getWidth();
        int pixColor;
        int x,y;
        for(x=0; x<height; x++){
            for(y=0; y<width; y++){
                pixColor=orgImg.getPixel(y,x);
                A=Color.alpha(pixColor);
                R=Color.red(pixColor);
                G=Color.green(pixColor);
                B=Color.blue(pixColor);
                if((R+20)<=255)
                    R+=20;
                if((G+20)<=255)
                    G+=20;
                if((B+20)<=255)
                    B+=20;
                photo.setPixel(y,x,Color.argb(A,R,G,B));
            }
        }
        img.setImageBitmap(photo);
    }

    void onGreyClicked(View view){

        Bitmap orgImg = ((BitmapDrawable)img.getDrawable()).getBitmap();
        Bitmap photo = Bitmap.createBitmap(orgImg.getWidth(),orgImg.getHeight(),orgImg.getConfig());


        int A,R,G,B;
        int height=orgImg.getHeight();
        int width=orgImg.getWidth();
        int pixColor;
        int x,y;
        for(x=0; x<height; x++){
            for(y=0; y<width; y++){
                pixColor=orgImg.getPixel(y,x);
                A=Color.alpha(pixColor);
                R=255- Color.red(pixColor);
                G=255- Color.green(pixColor);
                B=255- Color.blue(pixColor);
                pixColor=(R+G+B)/3;
                photo.setPixel(y,x,Color.argb(A,pixColor,pixColor,pixColor));
            }
        }
        img.setImageBitmap(photo);
    }

    void onFlipClicked(View view){

        Bitmap orgImg = ((BitmapDrawable)img.getDrawable()).getBitmap();
        Bitmap photo = Bitmap.createBitmap(orgImg.getWidth(),orgImg.getHeight(),orgImg.getConfig());


        int A,R,G,B;
        int height=orgImg.getHeight();
        int width=orgImg.getWidth();
        int pixColor;
        int x,y;
        for(x=0; x<height; x++){
            for(y=0; y<width; y++){
                pixColor=orgImg.getPixel(y,x);
                A=Color.alpha(pixColor);
                R=Color.red(pixColor);
                G=Color.green(pixColor);
                B=Color.blue(pixColor);
                photo.setPixel((width-y-1),x,Color.argb(A,R,G,B));
            }
        }
        img.setImageBitmap(photo);
    }

    void onUndoAllClicked(View view){
        currentImg= ((BitmapDrawable)img.getDrawable()).getBitmap();
        img.setImageBitmap(orginalImg);
        Toast myToast = Toast.makeText(getApplicationContext(),"Undo All",Toast.LENGTH_SHORT);
        myToast.show();
    }

    void onRedoAllClicked(View view){
        img.setImageBitmap(currentImg);
        Toast myToast = Toast.makeText(getApplicationContext(),"Redo All",Toast.LENGTH_SHORT);
        myToast.show();
    }
}
