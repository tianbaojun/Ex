package tabjin.viewex.ui;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import tabjin.viewex.R;

public class MotionActivity extends AppCompatActivity {

    private ConstraintLayout constraintLayout;

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motion);
        button = findViewById(R.id.btn);
        constraintLayout = findViewById(R.id.con);
    }
}
