package jp.co.meijou.androids.yuuicnak;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Optional;

import jp.co.meijou.androids.yuuicnak.databinding.ActivityMain2Binding;

public class MainActivity2 extends AppCompatActivity {

    private ActivityMain2Binding binding;

    private final ActivityResultLauncher<Intent> getActivityResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                switch (result.getResultCode()) {
                    case RESULT_OK -> {
                        Optional.ofNullable(result.getData())
                                .map(data -> data.getStringExtra("ret"))
                                .map(text -> "Result: " + text)
                                .ifPresent(text -> binding.textviewResult.setText(text));
                    }
                    case RESULT_CANCELED -> {
                        binding.textviewResult.setText("Result: Canceled");
                    }
                    default -> {
                        binding.textviewResult.setText("Result: Unknown(" + result.getResultCode() + ")");
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 明示的intent
        binding.button3.setOnClickListener(v -> {
            var intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });

        // 暗黙的intent
        binding.button4.setOnClickListener(v -> {
            var intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://www.yahoo.co.jp"));
            startActivity(intent);
        });

        // send button
        binding.button10.setOnClickListener(v -> {
            // EditTextの文字列を取得
           var text = binding.editTextText2.getText().toString();
           var intent = new Intent(this, MainActivity4.class);
           intent.putExtra("title", text);
           startActivity(intent);
        });

        // exec button
        binding.buttonexec.setOnClickListener(v -> {
            var intent = new Intent(this, MainActivity4.class);
            getActivityResult.launch(intent);
        });
    }
}