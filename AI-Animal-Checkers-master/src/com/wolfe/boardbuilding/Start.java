package com.wolfe.boardbuilding;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
/**
 * @class Start.java
 * @author Brandon Wolfe
 * @brief Start screen, allows user to select game type.
 * @pre none
 * @post MainActivity.java is launched
 *
 */
public class Start extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.player_select_screen);
		
		  //for user button interface 
	      Button p1vp2, aivp2, p1vai;
		    
	       //set listener for the button 
	        p1vp2=(Button) findViewById(R.id.p1vp2);
		    p1vp2.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					//option 1 is p1 v p2
					String option1 = "1";
					Intent intent = new Intent(Start.this, MainActivity.class);     
	     			
					//add the flag to be passed to next activity
	     			Bundle extras = new Bundle();
	     			extras.putString("option", option1);
	     			intent.putExtras(extras);
			 		
	     			//start the game
	     			startActivity(intent); 			
	     		    
			 		//finish this activity
			 		finish();
				}
			});
	
		   aivp2=(Button) findViewById(R.id.aivp2);
		    aivp2.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {

					//option 2 is AI v p2
					String option2 = "2";
					Intent intent = new Intent(Start.this, MainActivity.class);     
	     			 
	     			Bundle extras = new Bundle();
	     			extras.putString("option", option2);
	     			intent.putExtras(extras);
			 		startActivity(intent); 			

			 		finish();
				}
			});
	
		    p1vai=(Button) findViewById(R.id.p1vai);
		    p1vai.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {

					//option 3 is p1 v AI
					String option3 = "3";
	     			Intent intent = new Intent(Start.this, MainActivity.class);     
	     			 
	     			Bundle extras = new Bundle();
	     			extras.putString("option", option3);
	     			intent.putExtras(extras);
			 		
	     			startActivity(intent); 			
	     		    finish();
				}
			});    
	}
}
