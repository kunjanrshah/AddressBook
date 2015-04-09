package com.AddressBook;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Delete extends Activity {
    /** Called when the activity is first created. */

	String firstName,lastName;

	@Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete);
        
        final EditText txtDel = (EditText)findViewById(R.id.txtDel);
        
        Button btnFirstDel = (Button)findViewById(R.id.btnDelFirst);
        Button btnLastDel = (Button)findViewById(R.id.btnDelLast);
        
        final DBAdapter dba = new DBAdapter(this);

        btnFirstDel.setOnClickListener(new OnClickListener() 
        {
			@Override
			public void onClick(View v) 
			{
				firstName = txtDel.getText().toString().toLowerCase();
				
				dba.open();
			    Cursor c1 = dba.getContact(firstName);
			    
			    if (c1.moveToFirst())
			    {
			    	int row_id = c1.getInt(0);
			    	try
			    	{
			    		dba.deleteContact(row_id);
			    		showMsg();
			    	}
			    	catch(Exception e)
			    	{
			    		System.out.println("error.."+e);	
			    	}
			    }
			      else
			    	  showError();
			          
				dba.close();
			}
        });
        
        btnLastDel.setOnClickListener(new OnClickListener() 
        {
			@Override
			public void onClick(View v) 
			{
				lastName = txtDel.getText().toString().toLowerCase();
				int row_id;
				dba.open();
			    Cursor c1 = dba.getContactLastName(lastName);
			    if (c1.moveToFirst())
			     {
			    	row_id = c1.getInt(0);
			    	dba.deleteContact(row_id);
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
		Toast.makeText(this, "No Such Contact found", 
          		Toast.LENGTH_LONG).show();	
	}
	public void showMsg()
	{
		Toast.makeText(this, "Contact Deleted", 
          		Toast.LENGTH_LONG).show();	
	}   
}
