package net.sf.openrocket.simulation;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.EventObject;
import java.util.List;

import net.sf.openrocket.models.atmosphere.ExtendedISAModel;
import net.sf.openrocket.startup.Application;
import net.sf.openrocket.startup.Preferences;
import net.sf.openrocket.util.ChangeSource;
import net.sf.openrocket.util.MathUtil;
import net.sf.openrocket.util.StateChangeListener;

// Modified SimulationOptions
public class DispersionOptions implements ChangeSource {
	
	public static final double MAX_LAUNCH_ROD_ANGLE = Math.PI / 3;
	protected final Preferences preferences = Application.getPreferences();
	
	
	private double rodAngle = preferences.getDouble(Preferences.LAUNCH_ROD_ANGLE, 0);
	private double rodAngleDeviation = 0.1;
	private double launchDirection = preferences.getDouble(Preferences.LAUNCH_ROD_DIRECTION, Math.PI / 2);
	private double launchDirectionDeviation = preferences.getDouble(Preferences.LAUNCH_ROD_DIRECTION, Math.PI / 2);
	private double windSpeed = preferences.getDouble(Preferences.WIND_AVERAGE, 2.0);
	private double windSpeedDeviation = 2.0;
	private double temperature = preferences.getDouble(Preferences.LAUNCH_TEMPERATURE, ExtendedISAModel.STANDARD_TEMPERATURE);
	private double temperatureDeviation = 15.0;
	private double pressure = ExtendedISAModel.STANDARD_PRESSURE;
	private double pressureDeviation = 140.0;
	private double impulseDeviation = 0.0;
	private double massDeviation = 0.0;
	private double dragDeviation = 0.0;
	private int numberSims = 100;
	
	
	private List<EventListener> listeners = new ArrayList<EventListener>();
	
	public DispersionOptions() {
	}
	
	public double getWindSpeed() {
		return windSpeed;
	}
	
	public double getWindSpeedDeviation() {
		return windSpeedDeviation;
	}
	
	public double getTemperature() {
		return temperature;
	}
	
	public double getTemperatureDeviation() {
		return temperatureDeviation;
	}
	
	public double getPressure() {
		return pressure;
	}
	
	public double getPressureDeviation() {
		return pressureDeviation;
	}
	
	public double getRodAngle() {
		return rodAngle;
	}
	
	public double getRodAngleDeviation() {
		return rodAngleDeviation;
	}
	
	public double getLaunchDirection() {
		return launchDirection;
	}
	
	public double getLaunchDirectionDeviation() {
		return launchDirectionDeviation;
	}
	
	public double getImpulseDeviation() {
		return impulseDeviation;
	}
	
	public double getMassDeviation() {
		return massDeviation;
	}
	
	public double getDragDeviation() {
		return dragDeviation;
	}
	
	public int getNumberSims() {
		return numberSims;
	}
	
	public void setWindSpeed(double val) {
		if (MathUtil.equals(this.windSpeed, val))
			return;
		windSpeed = val;
		fireChangeEvent();
	}
	
	public void setWindSpeedDeviation(double val) {
		if (MathUtil.equals(this.windSpeedDeviation, val))
			return;
		windSpeedDeviation = val;
		fireChangeEvent();
	}
	
	public void setTemperature(double val) {
		if (MathUtil.equals(this.temperature, val))
			return;
		temperature = val;
		fireChangeEvent();
	}
	
	public void setTemperatureDeviation(double val) {
		if (MathUtil.equals(this.temperatureDeviation, val))
			return;
		temperatureDeviation = val;
		fireChangeEvent();
	}
	
	public void setPressure(double val) {
		if (MathUtil.equals(this.pressure, val))
			return;
		pressure = val;
		fireChangeEvent();
	}
	
	public void setPressureDeviation(double val) {
		if (MathUtil.equals(this.pressureDeviation, val))
			return;
		pressureDeviation = val;
		fireChangeEvent();
	}
	
	public void setRodAngle(double val) {
		if (MathUtil.equals(this.rodAngle, val))
			return;
		rodAngle = val;
		fireChangeEvent();
	}
	
	public void setRodAngleDeviation(double val) {
		if (MathUtil.equals(this.rodAngleDeviation, val))
			return;
		rodAngleDeviation = val;
		fireChangeEvent();
	}
	
	public void setLaunchDirection(double val) {
		if (MathUtil.equals(this.launchDirection, val))
			return;
		launchDirection = val;
		fireChangeEvent();
	}
	
	public void setLaunchDirectionDeviation(double val) {
		if (MathUtil.equals(this.launchDirectionDeviation, val))
			return;
		launchDirectionDeviation = val;
		fireChangeEvent();
	}
	
	public void setImpulseDeviation(double val) {
		if (MathUtil.equals(this.impulseDeviation, val))
			return;
		impulseDeviation = val;
		fireChangeEvent();
	}
	
	public void setMassDeviation(double val) {
		if (MathUtil.equals(this.massDeviation, val))
			return;
		massDeviation = val;
		fireChangeEvent();
	}
	
	public void setDragDeviation(double val) {
		if (MathUtil.equals(this.dragDeviation, val))
			return;
		dragDeviation = val;
		fireChangeEvent();
	}
	
	public void setNumberSims(int val) {
		if (numberSims == val)
			return;
		if (val > 300)
			numberSims = 300;
		numberSims = val;
		fireChangeEvent();
	}
	
	
	@Override
	public void addChangeListener(StateChangeListener listener) {
		listeners.add(listener);
	}
	
	@Override
	public void removeChangeListener(StateChangeListener listener) {
		listeners.remove(listener);
	}
	
	private final EventObject event = new EventObject(this);
	
	private void fireChangeEvent() {
		
		EventListener[] list = listeners.toArray(new EventListener[0]);
		for (EventListener l : list) {
			if (l instanceof StateChangeListener) {
				((StateChangeListener) l).stateChanged(event);
			}
		}
	}
	
}
