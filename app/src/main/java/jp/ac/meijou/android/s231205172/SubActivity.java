package jp.ac.meijou.android.s231205172;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import jp.ac.meijou.android.s231205172.databinding.ActivityMain3Binding;
import jp.ac.meijou.android.s231205172.databinding.ActivityMainBinding;
import jp.ac.meijou.android.s231205172.databinding.ActivitySubBinding;

public class SubActivity extends AppCompatActivity {

    private ActivitySubBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivitySubBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Intent intent = getIntent();
        String text = intent.getStringExtra("imp_text");
        binding.textView2.setText(text);

        binding.okButton.setOnClickListener(view -> {
            intent.putExtra("ret", "OK");
            setResult(RESULT_OK);
            finish();
        });
        binding.canButton.setOnClickListener(view -> {
            setResult(RESULT_CANCELED);
            finish();
        });
    }
}