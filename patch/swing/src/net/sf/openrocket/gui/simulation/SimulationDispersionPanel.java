package net.sf.openrocket.gui.simulation;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import net.miginfocom.swing.MigLayout;
import net.sf.openrocket.document.Simulation;
import net.sf.openrocket.gui.SpinnerEditor;
import net.sf.openrocket.gui.adaptors.BooleanModel;
import net.sf.openrocket.gui.adaptors.DoubleModel;
import net.sf.openrocket.gui.adaptors.IntegerModel;
import net.sf.openrocket.gui.components.BasicSlider;
import net.sf.openrocket.gui.components.UnitSelector;
import net.sf.openrocket.l10n.Translator;
import net.sf.openrocket.models.atmosphere.ExtendedISAModel;
import net.sf.openrocket.simulation.DefaultSimulationOptionFactory;
import net.sf.openrocket.simulation.DispersionOptions;
import net.sf.openrocket.simulation.SimulationOptions;
import net.sf.openrocket.startup.Application;
import net.sf.openrocket.unit.UnitGroup;
import net.sf.openrocket.util.Chars;

//Modified SimulationConditionsPanel
public class SimulationDispersionPanel extends JPanel {
	private static final Translator trans = Application.getTranslator();
	
	SimulationDispersionPanel(final Simulation simulation, final DispersionOptions conditions) {
		super(new MigLayout("fill"));
		
		//final SimulationOptions conditions = simulation.getOptions();

		final SimulationOptions simconditions = simulation.getOptions();
		
		JPanel sub;
		UnitSelector unit;
		DoubleModel m;
		JSpinner spin;
		
		
		///------------------------------------------------------------------------------------------------------
		sub = new JPanel(new GridLayout(0,3));
		sub.setBorder(BorderFactory.createTitledBorder("Dispersion Options, VALUES NOT SAVED"));
		this.add(sub);

		JLabel label = new JLabel("Wind Speed Average Override");
		m = new DoubleModel(conditions, "WindSpeed", UnitGroup.UNITS_WINDSPEED, 0);
		sub.add(label);spin = new JSpinner(m.getSpinnerModel());spin.setEditor(new SpinnerEditor(spin));sub.add(spin, "w 65lp!");unit = new UnitSelector(m);sub.add(unit);
		label = new JLabel("Wind Speed Average Deviation");
		m = new DoubleModel(conditions, "WindSpeedDeviation", UnitGroup.UNITS_WINDSPEED, 0);
		sub.add(label);spin = new JSpinner(m.getSpinnerModel());spin.setEditor(new SpinnerEditor(spin));sub.add(spin, "w 65lp!");unit = new UnitSelector(m);sub.add(unit);	
		label = new JLabel("Rod Angle");
		m = new DoubleModel(conditions, "RodAngle", UnitGroup.UNITS_ANGLE,-SimulationOptions.MAX_LAUNCH_ROD_ANGLE, SimulationOptions.MAX_LAUNCH_ROD_ANGLE);
		sub.add(label);spin = new JSpinner(m.getSpinnerModel());spin.setEditor(new SpinnerEditor(spin));sub.add(spin, "w 65lp!");unit = new UnitSelector(m);sub.add(unit);
		label = new JLabel("Rod Angle Deviation");
		m = new DoubleModel(conditions, "RodAngleDeviation", UnitGroup.UNITS_ANGLE,-SimulationOptions.MAX_LAUNCH_ROD_ANGLE, SimulationOptions.MAX_LAUNCH_ROD_ANGLE);
		sub.add(label);spin = new JSpinner(m.getSpinnerModel());spin.setEditor(new SpinnerEditor(spin));sub.add(spin, "w 65lp!");unit = new UnitSelector(m);sub.add(unit);
		/*label = new JLabel("!Temperature");
		m = new DoubleModel(conditions, "Temperature", UnitGroup.UNITS_TEMPERATURE, 0);
		sub.add(label);spin = new JSpinner(m.getSpinnerModel());spin.setEditor(new SpinnerEditor(spin));sub.add(spin, "w 65lp!");unit = new UnitSelector(m);sub.add(unit);
		label = new JLabel("!TemperatureDeviation");
		m = new DoubleModel(conditions, "TemperatureDeviation", UnitGroup.UNITS_TEMPERATURE, 0);
		sub.add(label);spin = new JSpinner(m.getSpinnerModel());spin.setEditor(new SpinnerEditor(spin));sub.add(spin, "w 65lp!");unit = new UnitSelector(m);sub.add(unit);
		label = new JLabel("!Pressure");
		m = new DoubleModel(conditions, "Pressure", UnitGroup.UNITS_PRESSURE, 0);
		sub.add(label);spin = new JSpinner(m.getSpinnerModel());spin.setEditor(new SpinnerEditor(spin));sub.add(spin, "w 65lp!");unit = new UnitSelector(m);sub.add(unit);
		label = new JLabel("!PressureDeviation");
		m = new DoubleModel(conditions, "PressureDeviation", UnitGroup.UNITS_PRESSURE, 0);
		sub.add(label);spin = new JSpinner(m.getSpinnerModel());spin.setEditor(new SpinnerEditor(spin));sub.add(spin, "w 65lp!");unit = new UnitSelector(m);sub.add(unit);
		label = new JLabel("!LaunchDirection");
		m = new DoubleModel(conditions, "LaunchDirection", 1.0, UnitGroup.UNITS_ANGLE,0, 2*Math.PI);
		sub.add(label);spin = new JSpinner(m.getSpinnerModel());spin.setEditor(new SpinnerEditor(spin));sub.add(spin, "w 65lp!");unit = new UnitSelector(m);sub.add(unit);
		label = new JLabel("!LaunchDirectionDeviation");
		m = new DoubleModel(conditions, "LaunchDirectionDeviation", 1.0, UnitGroup.UNITS_ANGLE,0, 2*Math.PI);
		sub.add(label);spin = new JSpinner(m.getSpinnerModel());spin.setEditor(new SpinnerEditor(spin));sub.add(spin, "w 65lp!");unit = new UnitSelector(m);sub.add(unit);
		label = new JLabel("!ImpulseDeviationRatio");
		m = new DoubleModel(conditions, "ImpulseDeviation", UnitGroup.UNITS_NONE, 0);
		sub.add(label);spin = new JSpinner(m.getSpinnerModel());spin.setEditor(new SpinnerEditor(spin));sub.add(spin, "w 65lp!");unit = new UnitSelector(m);sub.add(unit);
		label = new JLabel("!MassDeviationRatio");
		m = new DoubleModel(conditions, "MassDeviation", UnitGroup.UNITS_NONE, 0);
		sub.add(label);spin = new JSpinner(m.getSpinnerModel());spin.setEditor(new SpinnerEditor(spin));sub.add(spin, "w 65lp!");unit = new UnitSelector(m);sub.add(unit);
		label = new JLabel("!DragDeviationRatio");
		m = new DoubleModel(conditions, "DragDeviation", UnitGroup.UNITS_NONE, 0);
		sub.add(label);spin = new JSpinner(m.getSpinnerModel());spin.setEditor(new SpinnerEditor(spin));sub.add(spin, "w 65lp!");unit = new UnitSelector(m);sub.add(unit);
		*/
		label = new JLabel("Number of Iterations (<300)");
		IntegerModel m2 = new IntegerModel(conditions, "NumberSims", 1,300);
		sub.add(label);spin = new JSpinner(m2.getSpinnerModel());spin.setEditor(new SpinnerEditor(spin));sub.add(spin, "w 65lp!");
		
	}
	
}