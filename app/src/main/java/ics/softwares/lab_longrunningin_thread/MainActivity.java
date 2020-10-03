package ics.softwares.lab_longrunningin_thread;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    boolean nonStop=true;
    TextView mytextView;
    Button btnStartThread;
    Button btnStopThread;
    ImageView imageView;
    Bitmap bitmap;
    String TAG="UIThread";
    ArrayList<Integer> imageFiles=new ArrayList<Integer>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView=(ImageView)findViewById(R.id.imageView);
        imageFiles.add(new Integer(R.drawable.image1));
        imageFiles.add(new Integer(R.drawable.image2));
        imageFiles.add(new Integer(R.drawable.image3));
        imageFiles.add(new Integer(R.drawable.image4));
        mytextView=(TextView)findViewById(R.id.textview);
        btnStartThread=(Button)findViewById(R.id.startThread);
        btnStartThread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    int index=0;
                    @Override
                    public void run() {
                        while (nonStop){
                            int index=0;
                            Log.i(TAG, "Threa in loop"+ Thread.currentThread().getId()+"; current index: "+index);
                            try {
                                Thread.sleep(1000);
                                bitmap = BitmapFactory.decodeResource(getResources(),imageFiles.get(index));
                                imageView.setImageBitmap(bitmap);
                                index=(index+1)%4;
                            }
                            catch (InterruptedException e){
                                Log.e(TAG, e.getMessage());
                            }
                        }

                    }
                }).start();
            }
        });
        btnStopThread=(Button)findViewById(R.id.stopThread);
        btnStopThread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nonStop=false;
                Toast toast = Toast.makeText(MainActivity.this, "I am a message", Toast.LENGTH_LONG);
                toast.show();
                //nonStop=false;
            }
        });
    }
}
