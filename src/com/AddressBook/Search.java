package com.AddressBook;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Search extends Activity {
    /** Called when the activity is first created. */
    
	String firstName,lastName;
	@Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
        
        final EditText txtSearch = (EditText)findViewById(R.id.txtSearch);
     
        Button btnFirstSearch = (Button)findViewById(R.id.btnSearchFirst);
        Button btnLastSearch = (Button)findViewById(R.id.btnSearchLast);
        
        final TextView lblFirst = (TextView)findViewById(R.id.lblFirstName);
        final TextView lblLast = (TextView)findViewById(R.id.lblLastName);
        final TextView lblPhone = (TextView)findViewById(R.id.lblPhone);
        final TextView lblEmail = (TextView)findViewById(R.id.lblEmail);
        final TextView lblAddress = (TextView)findViewById(R.id.lblAddress);
        
        final DBAdapter dba = new DBAdapter(this);
        
        btnFirstSearch.setOnClickListener(new OnClickListener() 
        {
			@Override
			public void onClick(View v) 
			{
				firstName = txtSearch.getText().toString().toLowerCase();
				
				dba.open();
			      Cursor c1 = dba.getContact(firstName);
			      if (c1.moveToFirst())
			      {
			    	  lblFirst.setText(c1.getString(1));
			    	  lblLast.setText(c1.getString(2));
			    	  lblPhone.setText(c1.getString(3));
			    	  lblEmail.setText(c1.getString(4));
			    	  lblAddress.setText(c1.getString(5));
			    	  showMsg();
			          //DisplayContact(c1);
			      }
			      else
			    	  showError();
			          
				dba.close();
			}
        });
        
        btnLastSearch.setOnClickListener(new OnClickListener() 
        {
			@Override
			public void onClick(View v) 
			{
				lastName = txtSearch.getText().toString().toLowerCase();
				
				dba.open();
			    Cursor c1 = dba.getContactLastName(lastName);
			    if (c1.moveToFirst())
			     {
			    	lblFirst.setText(c1.getString(1));
			    	lblLast.setText(c1.getString(2));
			    	lblPhone.setText(c1.getString(3));
			    	lblEmail.setText(c1.getString(4));
			    	lblAddress.setText(c1.getString(5));
			    	  
			    	showMsg();
			    	//DisplayContact(c1);
			     }
			     else
			    	 showError();
				dba.close();
			}
        });
    }
	public void showError()
	{
		Toast.makeText(this, "No Contact found", 
          		Toast.LENGTH_LONG).show();	
	}
	public void showMsg()
	{
		Toast.makeText(this, "Contact found", 
          		Toast.LENGTH_LONG).show();	
	}
    
}
