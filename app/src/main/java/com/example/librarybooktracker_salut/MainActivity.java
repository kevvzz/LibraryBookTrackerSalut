package com.example.librarybooktracker_salut;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {
    EditText code, days;
    Button borrow;
    TextView author,  title, price;
    String bookcode, numDays;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        code = findViewById(R.id.bookCode);
        days = findViewById(R.id.days);
        borrow = findViewById(R.id.borrowBtn);
        author = findViewById(R.id.author);
        title = findViewById(R.id.titleText);
        price = findViewById(R.id.price);

        borrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bookcode = code.getText().toString();
                numDays = days.getText().toString();

                db.collection("Book")
                        .document(bookcode)
                        .get()
                        .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                if (documentSnapshot.exists()) {
                                    // Document exists, book code found
                                    if (!documentSnapshot.getBoolean("isBorrowed")) {
                                        author.setText(documentSnapshot.getString("author"));
                                        title.setText(documentSnapshot.getString("title"));

                                        if (documentSnapshot.getString("type").equalsIgnoreCase("Premium")) {

                                            Premium premium = new Premium(Integer.parseInt(numDays));
                                            price.setText(Double.toString(premium.calculateTotalPrice()));
                                        }
                                        if (documentSnapshot.getString("type").equalsIgnoreCase("Regular")) {
                                            Regular regular = new Regular(Integer.parseInt(numDays));
                                            price.setText(Double.toString(regular.calculateTotalPrice()));
                                        }

                                    } else {
                                        Toast.makeText(MainActivity.this, "The Book is Already Borrowed!!", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    // Document doesn't exist, book code not found
                                    Toast.makeText(MainActivity.this, "Book Code not Found!!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Error occurred while fetching the document
                                Toast.makeText(MainActivity.this, "Error fetching document: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

    }
}