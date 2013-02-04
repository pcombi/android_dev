package com.example.draganddropexampleforandroid4;

import android.app.Activity;
import android.content.ClipData;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnDragListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class DragActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_drag);
		findViewById(R.id.myimage1).setOnTouchListener(new MyTouchListener());
		findViewById(R.id.myimage2).setOnTouchListener(new MyTouchListener());
		findViewById(R.id.myimage3).setOnTouchListener(new MyTouchListener());
		findViewById(R.id.myimage4).setOnTouchListener(new MyTouchListener());
		findViewById(R.id.topleft).setOnDragListener(new MyDragListener());
		findViewById(R.id.topright).setOnDragListener(new MyDragListener());
		findViewById(R.id.bottomleft).setOnDragListener(new MyDragListener());
		findViewById(R.id.bottomright).setOnDragListener(new MyDragListener());
	}

	/*
	 * Android 4.0 supports Drag and Drop of Views. You register a listener on
	 * the View and you define other Views as possible drop targets, e.g. drop
	 * zones.
	 * 
	 * To use this you register a listener on the View, e.g. a OnTouchListener
	 * or a LongClickListener.
	 * 
	 * In this method you construct an object of type ClipData. This object can
	 * be used to contain data which can be passed to the Views which are
	 * defined a drop zones.
	 * 
	 * The DragShadowBuilder allow you specify the look of the dragging
	 * operation. Typically you pass in the View directly, which will be shown
	 * for the drag operation.
	 */

	private final class MyTouchListener implements OnTouchListener {
		public boolean onTouch(View view, MotionEvent motionEvent) {
			if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
				ClipData data = ClipData.newPlainText("", "");
				DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(
						view);
				view.startDrag(data, shadowBuilder, view, 0);
				view.setVisibility(View.INVISIBLE);
				return true;
			} else {
				return false;
			}
		}
	}

	/*
	 * A View which can be used as drag zone, gets a OnDragListener assigned via
	 * the setOnDragListener() method. In this OnDragListener you can evaluate
	 * the events and react accordingly.
	 */

	class MyDragListener implements OnDragListener {
		Drawable enterShape = getResources().getDrawable(
				R.drawable.shape_droptarget);
		Drawable normalShape = getResources().getDrawable(R.drawable.shape);

		@Override
		public boolean onDrag(View v, DragEvent event) {
			int action = event.getAction();
			switch (event.getAction()) {
			case DragEvent.ACTION_DRAG_STARTED:
				// Do nothing
				break;
			case DragEvent.ACTION_DRAG_ENTERED:
				// v.setBackgroundDrawable(enterShape);
				v.setBackgroundResource(R.drawable.shape_droptarget);
				break;
			case DragEvent.ACTION_DRAG_EXITED:
				// v.setBackgroundDrawable(normalShape);
				v.setBackgroundResource(R.drawable.shape);
				break;
			case DragEvent.ACTION_DROP:
				// Dropped, reassign View to ViewGroup
				View view = (View) event.getLocalState();
				ViewGroup owner = (ViewGroup) view.getParent();
				owner.removeView(view);
				LinearLayout container = (LinearLayout) v;
				container.addView(view);
				view.setVisibility(View.VISIBLE);
				break;
			case DragEvent.ACTION_DRAG_ENDED:
				// v.setBackgroundDrawable(normalShape);
				v.setBackgroundResource(R.drawable.shape);
			default:
				break;
			}
			return true;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_drag, menu);

		return true;
	}

}
