package com.softalien.englishgrammar;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class TestsActivity extends Activity implements OnClickListener {

	int displayWidth = 0;
	Button done;
	LinearLayout answerLayout;
	TextView tvQuestion;
	TextView tvAnswer;
	int currentAnswerLayoutWidth = 10;   // because margin is 10 dp
	LinearLayout newLayout;
	String question;
	String answer;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tests);
		initialize();
		
		done.setOnClickListener(this);
		
		
		Display display = getWindowManager().getDefaultDisplay();
		displayWidth = display.getWidth();
		question = "He didn't want to go home";
		answer = "He didn't want to go home";
		String[] options1 = {"He", "She", "It", "I", "We"};
		String[] options2 = { "wanted", "didn't wanted", "didn't want", "will want",
				"wants"};
		String[] options3 = {"to go", "go", "went", "to went", "gone"};
		String[] options4 = {"home", "to home", "at home", "at house"};
		
		buildLayoutWithOptions(options1, 1);
		buildLayoutWithOptions(options2, 2);
		buildLayoutWithOptions(options3, 3);
		buildLayoutWithOptions(options4, 4);
		
		tvQuestion.setText(question);
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bDone:
			String userAnswer = getUserAnswer();
			if (userAnswer.trim().equals(answer.trim())) {
				Toast t = Toast.makeText(TestsActivity.this, "This is correct answer",
						Toast.LENGTH_SHORT);
				t.show();
			}
			break;
		}
		
	}
	
	private String getUserAnswer() {
		String result = "";
		for (int i = 0; i < answerLayout.getChildCount(); i++) {
			for (int j = 0; j < ((LinearLayout)answerLayout.getChildAt(i)).getChildCount(); j ++) {
				result += (((TextView)((LinearLayout)answerLayout.getChildAt(i)).getChildAt(j))).getText().toString();
			}
		}
		return result;
	}

	public void initialize() {
		answerLayout = (LinearLayout) findViewById(R.id.lAnswers);
		done = (Button) findViewById(R.id.bDone);
		tvQuestion = (TextView) findViewById(R.id.tvQuestion);
		tvAnswer = (TextView)findViewById(R.id.tvAnswer);
	}
	
	public void buildLayoutWithOptions(String[] options, int layoutId) {
		LinearLayout ll = null;
		int drawable = 0;
		switch (layoutId) {
		case 1:
			drawable = R.drawable.btn_green;
			ll = (LinearLayout) findViewById(R.id.buttonOptions1);
		break;
		case 2:
			drawable = R.drawable.btn_red;
			ll = (LinearLayout) findViewById(R.id.buttonOptions2);
		break;
		case 3:
			drawable = R.drawable.btn_blue;
			ll = (LinearLayout) findViewById(R.id.buttonOptions3);
		break;
		case 4:
			drawable = R.drawable.btn_yellow;
			ll = (LinearLayout) findViewById(R.id.buttonOptions4);
		break;

		}
		int currentWidth = 0;
		LinearLayout newLL = getNewLinearLayout();
		ll.addView(newLL);
		for (int i = 0; i < options.length; i++) {

			final String name = options[i];
			final Button myButton = createNewButton(name);
			myButton.setBackgroundResource(drawable);
			myButton.measure(0, 0);
			myButton.setPadding(10, 10, 10, 10);
			//set margins
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			params.setMargins(5, 5, 5, 5);
			myButton.setPadding(0, 0, 0, 0);
			myButton.setLayoutParams(params);
			//set text style which is saved in Strings
			myButton.setTextAppearance(this, R.style.ButtonText);
			myButton.setId(drawable);
			currentWidth += myButton.getMeasuredWidth();
			if (currentWidth > displayWidth) {
				newLL = getNewLinearLayout();
				ll.addView(newLL);
				currentWidth = myButton.getMeasuredWidth();
			}
			newLL.addView(myButton);
			myButton.setOnClickListener(new OnClickListener() {
			
				@Override
				public void onClick(View v) {
					String text = myButton.getText().toString();
					if (!isTextAlreadyClicked(text, answerLayout)) {
						myButton.setBackgroundResource(R.drawable.btn_purple);
						//addButton(text);
						addTextToTextView(text);
					} else {
						myButton.setBackgroundResource(myButton.getId());
						removeTextFromLayout(text, answerLayout, false);
					}
				}


			});
		}
	}

	private void addTextToTextView(String text) {
		if (newLayout == null) {
			newLayout = getNewLinearLayout();
			answerLayout.addView(newLayout);
		}
		if (!isTextAlreadyClicked(text, answerLayout)) {
			
			TextView newText = createNewTextView(text + " ");
			newText.setTextSize(20);
			newText.setPadding(0, 0, 0, 0);
			newText.measure(0, 0);
			currentAnswerLayoutWidth += newText.getMeasuredWidth() + 5;
			if (currentAnswerLayoutWidth > displayWidth) {
				newLayout = getNewLinearLayout();
				answerLayout.addView(newLayout);
				//newLayout.addView(newButton);
				currentAnswerLayoutWidth = newText.getMeasuredWidth();
			}
			newLayout.addView(newText);
			Toast t = Toast.makeText(TestsActivity.this, text,
					Toast.LENGTH_SHORT);
			t.show();
		} else {
			removeTextFromLayout(text, answerLayout, false);
		}
	}
	
	private void removeTextFromLayout(String name, LinearLayout layout,
			boolean last) {
		if (name != null) {
			for (int i = 0; i < layout.getChildCount(); i++) {
				for (int j = 0; j < ((LinearLayout)layout.getChildAt(i)).getChildCount(); j ++) {
					if (((TextView)((LinearLayout)layout.getChildAt(i)).getChildAt(j)).getText().equals(name + " ")) {
						currentAnswerLayoutWidth -= ((TextView)((LinearLayout)layout.getChildAt(i)).getChildAt(j)).getWidth() + 5;
						((LinearLayout)layout.getChildAt(i)).removeViewAt(j);
					}
				}
			}
		} else {
			if (layout.getChildCount() > 0) {
				LinearLayout ll = ((LinearLayout)layout.getChildAt(layout.getChildCount() - 1));
				if (ll.getChildCount() > 0) {
					int index = ll.getChildCount() - 1;
					currentAnswerLayoutWidth -= ll.getChildAt(index).getWidth();
					String text = ((TextView)ll.getChildAt(index)).getText().toString().trim();
					ll.removeViewAt(index);
				} else {
					//TODO: show error message 
				}
			} else {
				//TODO: show error message 
			}
		}
	}

	/*private void addButton(String name) {
		
		if (newLayout == null) {
			newLayout = getNewLinearLayout();
			answerLayout.addView(newLayout);
		}
		if (!isButtonAlreadyClicked(name, answerLayout)) {
			
			Button newButton = createNewButton(name);
			newButton.setPadding(0, 0, 0, 0);
			newButton.measure(0, 0);
			currentAnswerLayoutWidth += newButton.getMeasuredWidth() + 5;
			if (currentAnswerLayoutWidth > displayWidth) {
				newLayout = getNewLinearLayout();
				answerLayout.addView(newLayout);
				//newLayout.addView(newButton);
				currentAnswerLayoutWidth = newButton.getMeasuredWidth();
			}
			newLayout.addView(newButton);
			Toast t = Toast.makeText(TestsActivity.this, name,
					Toast.LENGTH_LONG);
			t.show();
		} else {
			removeButtonFromLayout(name, answerLayout, false);
		}

	}*/
	private LinearLayout getNewLinearLayout() {
		LinearLayout newLL = new LinearLayout(this);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				displayWidth, 80);
		params.setMargins(2, 2, 2, 2);
		newLL.setLayoutParams(params);
		newLL.setPadding(0, 5, 0, 0);
		newLL.setOrientation(LinearLayout.HORIZONTAL);
		return newLL;
	}
	
	public boolean isTextAlreadyClicked(String name, LinearLayout layout) {
		boolean result = false;
		for (int i = 0; i < layout.getChildCount(); i++) {
			for (int j = 0; j < ((LinearLayout)layout.getChildAt(i)).getChildCount(); j ++) {
				if (((TextView)((LinearLayout)layout.getChildAt(i)).getChildAt(j)).getText().toString().equals(name + " ")) {
					/*currentAnswerLayoutWidth -= ((TextView)((LinearLayout)layout.getChildAt(i)).getChildAt(j)).getWidth() + 5;  
					((LinearLayout)layout.getChildAt(i)).removeViewAt(j);*/
					/*if (((LinearLayout) layout.getChildAt(i)).getChildCount() == 0) {
						layout.removeViewAt(i);
					}*/
					result = true;
					break;
				}
			}
		}
		return result;
	}
	
	/*public boolean isButtonAlreadyClicked(String name, LinearLayout layout) {
		boolean result = false;
		for (int i = 0; i < layout.getChildCount(); i++) {
			for (int j = 0; j < ((LinearLayout)layout.getChildAt(i)).getChildCount(); j ++) {
				if (((Button)((LinearLayout)layout.getChildAt(i)).getChildAt(j)).getText().equals(name)) {
					currentAnswerLayoutWidth -= ((Button)((LinearLayout)layout.getChildAt(i)).getChildAt(j)).getWidth() + 5;  
					((LinearLayout)layout.getChildAt(i)).removeViewAt(j);
					if (((LinearLayout) layout.getChildAt(i)).getChildCount() == 0) {
						layout.removeViewAt(i);
					}
					result = true;
					break;
				}
			}
		}
		return result;
	}*/
	
	private TextView createNewTextView(String text) {
		TextView myTextView = new TextView(TestsActivity.this);
		myTextView.setText(text);
		return myTextView;
	}
	
	private Button createNewButton(String name) {
		Button myButton = new Button(TestsActivity.this);
		myButton.setText(name);
		return myButton;
	}

	/*private void removeButtonFromLayout(String name, LinearLayout layout,
			boolean last) {
		if (name != null) {
			for (int i = 0; i < layout.getChildCount(); i++) {
				for (int j = 0; j < ((LinearLayout)layout.getChildAt(i)).getChildCount(); j ++) {
					if (((Button)((LinearLayout)layout.getChildAt(i)).getChildAt(j)).getText().equals(name)) {
						currentAnswerLayoutWidth -= ((Button)((LinearLayout)layout.getChildAt(i)).getChildAt(j)).getWidth() + 5;
						((LinearLayout)layout.getChildAt(i)).removeViewAt(j);
					}
				}
			}
		} else {
			LinearLayout ll = ((LinearLayout)layout.getChildAt(layout.getChildCount() -1));
			layout.removeViewAt(layout.getChildCount() - 1);
		}
	}*/

	

	// Resources r = this.getBaseContext().getResources();
	// myButton.setBackground(r.getDrawable(R.drawable.btn_blue));
	// or
	// InputStream is = getResources().openRawResource(R.drawable.btn_blue);
	// myButton.setBackground(Drawable.createFromStream(is, null);
	// myButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_blue));

	// myButton.setBackgroundResource(R.drawable.btn_blue);

	// LayoutParams pars = new LayoutParams(LayoutParams.WRAP_CONTENT,
	// LayoutParams.WRAP_CONTENT);
	// myButton.setLayoutParams(pars);
	// myButton.setBackgroundColor(Color.BLUE);
	// myButton.setBackgroundColor(Color.BLUE);
}
