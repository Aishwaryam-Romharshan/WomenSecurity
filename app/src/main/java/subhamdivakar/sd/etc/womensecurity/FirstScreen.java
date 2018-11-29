package subhamdivakar.sd.etc.womensecurity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.Toast;

import subhamdivakar.sd.etc.womensecurity.R;
import com.flaviofaria.kenburnsview.KenBurnsView;
import com.flaviofaria.kenburnsview.RandomTransitionGenerator;
import com.flaviofaria.kenburnsview.Transition;

public class FirstScreen extends AppCompatActivity {
    private KenBurnsView kbv;
    private boolean moving = true;
    ImageView imageViewOriginal;
    BitmapDrawable originalBitmapDrawable ;
    Bitmap originalBitmap ;
    int originalImageWith ;
    int originalImageHeight;
    Bitmap.Config originalImageConfig;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_screen);

//         ImageView imageViewOriginal = (ImageView)findViewById(R.id.img);
//         originalBitmapDrawable = (BitmapDrawable) imageViewOriginal.getDrawable();
//            originalBitmap = originalBitmapDrawable.getBitmap();
//         originalImageWith = originalBitmap.getWidth();
//         originalImageHeight = originalBitmap.getHeight();
//         Bitmap.Config originalImageConfig = originalBitmap.getConfig();

        kbv = findViewById(R.id.kvb);
        //this.translateImage(0, 1000);
        AccelerateDecelerateInterpolator adi = new AccelerateDecelerateInterpolator();
        RandomTransitionGenerator generator = new RandomTransitionGenerator(2000, adi);
        kbv.setTransitionGenerator(generator);

        kbv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (moving) {
                    kbv.pause();
                    moving = false;
                } else {
                    kbv.resume();
                    moving = true;
                }
            }
        });

        kbv.setTransitionListener(new KenBurnsView.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {
                Toast.makeText(FirstScreen.this, "Started", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTransitionEnd(Transition transition) {
                Toast.makeText(FirstScreen.this, "Finished", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void translateImage(int xTranslate, int yTranslate)
    {
        // According to the skew ratio of the picture, calculate the size of the image after the transformation.
        Bitmap translateBitmap = Bitmap.createBitmap(originalImageWith + xTranslate, originalImageHeight + yTranslate, originalImageConfig);

        Canvas translateCanvas = new Canvas(translateBitmap);

        Matrix translateMatrix = new Matrix();

        // Set x y translate value..
        translateMatrix.setTranslate(xTranslate, yTranslate);

        Paint paint = new Paint();
        translateCanvas.drawBitmap(originalBitmap, translateMatrix, paint);
        imageViewOriginal.setImageBitmap(translateBitmap);
    }
}