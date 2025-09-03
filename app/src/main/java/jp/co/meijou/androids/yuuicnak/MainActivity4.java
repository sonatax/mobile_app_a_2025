package jp.co.meijou.androids.yuuicnak;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import jp.co.meijou.androids.yuuicnak.databinding.ActivityMain4Binding;

public class MainActivity4 extends AppCompatActivity {
    private ActivityMain4Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMain4Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // MainActivity2 から送られたデータを取得
        var editText = getIntent().getStringExtra("title");
        binding.textView5.setText(editText);

        // OK button
        binding.button11.setOnClickListener(v -> {
            var intent = new Intent();
            intent.putExtra("ret", "OK");
            setResult(RESULT_OK, intent);
            finish();
        });

        // Cancel button
        binding.button12.setOnClickListener(v -> {
            setResult(RESULT_CANCELED);
            finish();
        });
    }
}