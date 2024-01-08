package com.example.quote_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class MainActivity extends AppCompatActivity {
    private TextView quoteTextView;
    private ImageView shareImageView;
    private ImageView saveImageView;

    private ImageView savedImageView;
    private DatabaseReference ref;

    private String[] quotes = {
            "Your limitation—it's only your imagination.",
    "Push yourself, because no one else is going to do it for you.",
            "It is hard to fail, but it is worse never to have tried to succeed" ,
            "Straight roads do not make skilful drivers",
    "Great things never come from comfort zones.",
    "To live a creative life, we must lose our fear of being wrong"// Add more quotes here
    };
    private List<String> savedQuotes = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        quoteTextView = findViewById(R.id.quote);
        shareImageView = findViewById(R.id.imageView2);
        saveImageView = findViewById(R.id.imageView4);
        savedImageView = findViewById(R.id.imageView3);

        // Set up initial quote
        setRandomQuote();

        // Share button click listener
        shareImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentQuote = quoteTextView.getText().toString();
                Intent intent=new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT,"check this beautiful quote");
                intent.putExtra(Intent.EXTRA_TEXT, currentQuote);
                startActivity(Intent.createChooser(intent,"Share via"));
            }
        });


// ...

        saveImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentQuote = quoteTextView.getText().toString();
                savedQuotes.add(currentQuote);
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                ref = database.getReference("users").child("codsoft").child("myquotes");
                DatabaseReference quoteRef = ref.push();
                quoteRef.setValue(currentQuote);
                Toast.makeText(MainActivity.this, "quote saved", Toast.LENGTH_SHORT).show();

            }
        });


        savedImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), myquotes.class);
                intent.putStringArrayListExtra("savedQuotes", (ArrayList<String>) savedQuotes);
                startActivity(intent);
            }
        });

    }

    private void setRandomQuote() {
        Random random = new Random();
        int randomIndex = random.nextInt(quotes.length);
        String randomQuote = quotes[randomIndex];
        quoteTextView.setText(randomQuote);

    }
}

