package com.app.email;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.app.email.adapter.EmailAdapter;
import com.app.email.model.Email;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FloatingActionButton mFab;
    private RecyclerView mRecycle;
    private EmailAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        setData();
    }

    private void init() {
        //init component
        mFab = findViewById(R.id.fab_main);
        mRecycle = findViewById(R.id.recycle_main);
    }

    private void setData() {
        //set data
        mFab.setOnClickListener(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, layoutManager.getOrientation());

        mRecycle.setLayoutManager(layoutManager);
        mRecycle.addItemDecoration(itemDecoration);

        List<Email> emails = new ArrayList<>();

        //adapter content set
        mAdapter = new EmailAdapter(this, emails);
        mRecycle.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab_main:
                openSnackBar(view);
                break;
        }
    }

    private void openSnackBar(View view) {
        //methode for opening snackbar
        Snackbar.make(MainActivity.this, view, "Compose new email?", Snackbar.LENGTH_SHORT)
                .setAction("New", v -> {
                    initializeDialog();
                }).show();
    }

    private void initializeDialog() {
        //function for open dialog and create email
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        View view = getLayoutInflater().inflate(R.layout.dialog_create_email, null);

        //set view
        builder.setView(view);

        EditText etTo = view.findViewById(R.id.editTextText_dialog_email_to);
        EditText etSubject = view.findViewById(R.id.editText_dialog_email_subject);
        EditText etContent = view.findViewById(R.id.editText_dialog_email_content);

        //set title
        builder.setTitle("Compose Email");

        //button send
        builder.setPositiveButton("Send", (dialog, which) -> {
            //create email

            //validasi isi content
            if (isEdittextEmpty(etTo) && isEdittextEmpty(etSubject) && isEdittextEmpty(etContent)) {
                Toast.makeText(MainActivity.this, "Form cannot be empty", Toast.LENGTH_SHORT).show();
                return;
            }

            //isi variable
            Email email = new Email(
                    etTo.getText().toString(),
                    etSubject.getText().toString(),
                    etContent.getText().toString()
            );

            //update recycle
            mAdapter.updateView(email);
        });

        //button discard
        builder.setNegativeButton("Discard", (dialog, which) -> dialog.dismiss());

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private boolean isEdittextEmpty(EditText editText) {
        //fungsi untuk check isi dari edittext
        return TextUtils.isEmpty(editText.getText().toString());
    }
}