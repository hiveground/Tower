package com.droidplanner.fragments.mission;

import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

import com.droidplanner.R;
import com.droidplanner.drone.variables.mission.waypoints.Loiter;
import com.droidplanner.drone.variables.mission.waypoints.LoiterTime;
import com.droidplanner.widgets.SeekBarWithText.SeekBarWithText;
import com.droidplanner.widgets.SeekBarWithText.SeekBarWithText.OnTextSeekBarChangedListner;

public class MissionLoiterTFragment extends MissionDetailFragment implements
		OnTextSeekBarChangedListner, OnCheckedChangeListener {
	private SeekBarWithText altitudeSeekBar;
	private SeekBarWithText loiterTimeSeekBar;
	private SeekBarWithText loiterRadiusSeekBar;
	private CheckBox loiterCCW;
	private SeekBarWithText yawSeekBar;

	@Override
	protected int getResource() {
		return R.layout.fragment_detail_loitert;
	}
	

	@Override
	protected void setupViews(View view) {
		super.setupViews(view);		
		typeSpinner.setSelection(commandAdapter.getPosition(MissionItemTypes.LOITERT));

		LoiterTime item = (LoiterTime) this.item;
		
		loiterCCW = (CheckBox) view.findViewById(R.string.loiter_ccw);
		loiterCCW.setChecked(item.isOrbitCCW());
		loiterCCW.setOnCheckedChangeListener(this);

		
		altitudeSeekBar = (SeekBarWithText) view
				.findViewById(R.id.altitudeView);
		altitudeSeekBar.setValue(item.getAltitude().valueInMeters());
		altitudeSeekBar.setOnChangedListner(this);
		
		loiterTimeSeekBar = (SeekBarWithText) view
				.findViewById(R.id.loiterTime);
		loiterTimeSeekBar .setOnChangedListner(this);
		loiterTimeSeekBar.setValue(item.getTime());
		
		loiterRadiusSeekBar = (SeekBarWithText) view
				.findViewById(R.id.loiterRadius);
		loiterRadiusSeekBar.setAbsValue(item.getOrbitalRadius());
		loiterRadiusSeekBar .setOnChangedListner(this);

		yawSeekBar = (SeekBarWithText) view
				.findViewById(R.id.waypointAngle);
		yawSeekBar.setValue(item.getYawAngle());
		yawSeekBar.setOnChangedListner(this);
	}

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		((Loiter) item).setOrbitCCW(isChecked);
    }
	

	@Override
	public void onSeekBarChanged() {
		LoiterTime item = (LoiterTime) this.item;
		item.getAltitude().set(altitudeSeekBar.getValue());
		item.setTime(loiterTimeSeekBar.getValue());
		item.setOrbitalRadius(loiterRadiusSeekBar.getValue());
		item.setYawAngle(yawSeekBar.getValue());
	}


}