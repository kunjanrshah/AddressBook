package com.AddressBook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddNew extends Activity {

	
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addnew);
     
        final DBAdapter dba = new DBAdapter(this);
        
        final EditText txtFirstName = (EditText)findViewById(R.id.txtFirstName);
        final EditText txtLastName = (EditText)findViewById(R.id.txtLastName);
        final EditText txtPhone = (EditText)findViewById(R.id.txtPhone);
        final EditText txtEmail = (EditText)findViewById(R.id.txtEmail);
        final EditText txtAddress = (EditText)findViewById(R.id.txtAddress);
        
        Button btnSave = (Button)findViewById(R.id.btnSave);
        
        btnSave.setOnClickListener(new OnClickListener() 
        {
			@Override
			public void onClick(View v) 
			{
				try
				{
					dba.open();
					dba.insertContact(txtFirstName.getText().toString().toLowerCase(),txtLastName.getText().toString().toLowerCase(),txtPhone.getText().toString(),txtEmail.getText().toString(),txtAddress.getText().toString());
					dba.close();
					
					showSaveMsg();
									
					txtLastName.setText("");
					txtPhone.setText("");
					txtEmail.setText("");
					txtAddress.setText("");
					txtFirstName.setText("");
					
					Intent intent_first = new Intent(AddNew.this,FirstActivity.class);
					startActivity(intent_first);
				}
				catch(Exception e)
				{
					System.out.println("Error in insert.."+e);
				}
			}
		});
    }
    public void showSaveMsg()
	{
		Toast.makeText(this, "Record saved successfully..!!", 
          		Toast.LENGTH_LONG).show();	
	}
}
