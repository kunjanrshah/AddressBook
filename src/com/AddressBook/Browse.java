package com.AddressBook;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Browse extends Activity {
    /** Called when the activity is first created. */
    int count;
	@Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.browse);
        
        final TextView lblFirst_ = (TextView)findViewById(R.id.lblFirstName_);
        final TextView lblLast_ = (TextView)findViewById(R.id.lblLastName_);
        final TextView lblPhone_ = (TextView)findViewById(R.id.lblPhone_);
        final TextView lblEmail_ = (TextView)findViewById(R.id.lblEmail_);
        final TextView lblAddress_ = (TextView)findViewById(R.id.lblAddress_);
        
        Button btnNext = (Button)findViewById(R.id.btnNext);
        Button btnPrevious = (Button)findViewById(R.id.btnPrevious);
        
        final DBAdapter dba = new DBAdapter(this);
        
        dba.open();
		final Cursor c1 = dba.getAllContacts();
		
		if (c1.moveToFirst()) 
		{
			lblFirst_.setText(c1.getString(1));
		    lblLast_.setText(c1.getString(2));
		    lblPhone_.setText(c1.getString(3));
		    lblEmail_.setText(c1.getString(4));
		    lblAddress_.setText(c1.getString(5));
		    
		    count = 0;
		}
		else
		{
			dba.close();
		}
		
        btnNext.setOnClickListener(new OnClickListener() 
        {
			@Override
			public void onClick(View v) 
			{
				if(count < c1.getCount()-1)
				{
					c1.moveToNext();
					
					lblFirst_.setText(c1.getString(1));
				    lblLast_.setText(c1.getString(2));
				    lblPhone_.setText(c1.getString(3));
				    lblEmail_.setText(c1.getString(4));
				    lblAddress_.setText(c1.getString(5));
				    count++;
				}
				else
				{
					showLastContact();
					dba.close();
				}
				
			}
        });

        btnPrevious.setOnClickListener(new OnClickListener() 
        {
			@Override
			public void onClick(View v) 
			{
				if(count >0)
				{
					c1.moveToPrevious();
					
					lblFirst_.setText(c1.getString(1));
				    lblLast_.setText(c1.getString(2));
				    lblPhone_.setText(c1.getString(3));
				    lblEmail_.setText(c1.getString(4));
				    lblAddress_.setText(c1.getString(5));
				    count--;
				}
				else
				{
					showFirstContact();
					dba.close();
				}
				
			}
        });

    }
	public void showLastContact()
	{
		Toast.makeText(this, "You are at last contact", 
          		Toast.LENGTH_LONG).show();	
	}
	public void showFirstContact()
	{
		Toast.makeText(this, "You are at First Contact", 
          		Toast.LENGTH_LONG).show();	
	}
}
