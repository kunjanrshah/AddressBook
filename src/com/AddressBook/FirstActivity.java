package com.AddressBook;

import com.AddressBook.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class FirstActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Button btnAddNew = (Button)findViewById(R.id.btnAddNew);
        Button btnSearch = (Button)findViewById(R.id.btnSearch);
        Button btnBrowse = (Button)findViewById(R.id.btnBrowse);
        Button btnDelete = (Button)findViewById(R.id.btnDelete);
        
        btnAddNew.setOnClickListener(new OnClickListener() 
        {
			@Override
			public void onClick(View v) 
			{
				Intent intent_addnew = new Intent(FirstActivity.this,AddNew.class);
				startActivity(intent_addnew);
			}
		});

        btnSearch.setOnClickListener(new OnClickListener() 
        {
			@Override
			public void onClick(View v) 
			{
				Intent intent_search = new Intent(FirstActivity.this,Search.class);
				startActivity(intent_search);
			}
		});

        btnBrowse.setOnClickListener(new OnClickListener() 
        {
			@Override
			public void onClick(View v) 
			{
				Intent intent_browse = new Intent(FirstActivity.this,Browse.class);
				startActivity(intent_browse);
			}
		});

        btnDelete.setOnClickListener(new OnClickListener() 
        {
			@Override
			public void onClick(View v) 
			{
				Intent intent_delete = new Intent(FirstActivity.this,Delete.class);
				startActivity(intent_delete);
			}
		});

        
    }
}