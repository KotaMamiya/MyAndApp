package jp.ac.meijou.android.s231205172;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Optional;

import jp.ac.meijou.android.s231205172.databinding.ActivityMain3Binding;
import jp.ac.meijou.android.s231205172.databinding.ActivityMainBinding;
import jp.ac.meijou.android.s231205172.databinding.ActivitySubBinding;

public class MainActivity3 extends AppCompatActivity {

    private ActivityMain3Binding binding;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityMain3Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding.buttonA.setOnClickListener(view -> {
            var intent = new Intent(this, SubActivity.class);
            startActivity(intent);
        });

        binding.buttonB.setOnClickListener(view -> {
            var intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://www.yahoo.co.jp/"));
            startActivity(intent);
        });

        binding.buttonC.setOnClickListener(view -> {
            var intent = new Intent(this, SubActivity.class);
            var text = binding.editTextText.getText().toString();
            intent.putExtra("imp_text", text);
            startActivity(intent);
        });
        binding.setButton.setOnClickListener(view -> {
            var intent = new Intent(this, SubActivity.class);
            getActivityResult.launch(intent);
        });
    }

    private final ActivityResultLauncher<Intent> getActivityResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                switch (result.getResultCode()) {
                    case RESULT_OK:
                        Optional.ofNullable(result.getData())
                                .map(data -> data.getStringArrayExtra("ret"))
                                .map(text -> "Result: " + text)
                                .ifPresent(text -> binding.textView.setText(text));
                        break;
                    case RESULT_CANCELED:
                        binding.textView.setText("Result: Canceled");
                        break;
                    default:
                        binding.textView.setText("Result: Unknown(" + result.getResultCode() + ")");
                }
            }
    );
}