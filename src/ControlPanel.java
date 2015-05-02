import java.awt.Button;
import java.awt.Checkbox;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Label;
import java.awt.Panel;

/////////////////////////////////////////////////////////////////////////////
//Control panel for FftLab.

class ControlPanel extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ControlPanel(FftLabController c) {
		this.c = c;
		add(new Checkbox("Origin Centered"));
		length = new LabeledChoice("Length:");
		length.choice.addItem("16");
		length.choice.addItem("32");
		length.choice.addItem("64");
		length.choice.select("32");
		add(length);
		mode = new LabeledChoice("Editing:");
		mode.choice.addItem("Draw");
		mode.choice.addItem("Negate");
		mode.choice.addItem("Zero");
		mode.choice.addItem("Shift");
		mode.choice.addItem("None");
		mode.choice.select("Draw");
		add(mode);
		add(new Button("Zero All"));
	}

	@SuppressWarnings("deprecation")
	public void paint(Graphics g) {
		Dimension d = size();
		g.setColor(Color.blue);
		g.draw3DRect(0, 0, d.width - 1, d.height - 1, true);
	}

	public Insets insets() {
		return new Insets(1, 1, 1, 1);
	}

	public boolean handleEvent(Event e) {
		if (e.target instanceof Button) {
			c.zeroAll();
			return true;
		} else if (e.target instanceof Checkbox) {
			Checkbox cb = (Checkbox) e.target;
			c.setOriginCentered(cb.getState());
			return true;
		} else if (e.target instanceof Choice) {
			if (e.target == length.choice) {
				String item = length.choice.getSelectedItem();
				c.setLength(Integer.parseInt(item));
				return true;
			} else if (e.target == mode.choice) {
				String item = mode.choice.getSelectedItem();
				if (item == "None") {
					c.setEditMode(SamplesView.EDIT_NONE);
				} else if (item == "Draw") {
					c.setEditMode(SamplesView.EDIT_DRAW);
				} else if (item == "Negate") {
					c.setEditMode(SamplesView.EDIT_NEGATE);
				} else if (item == "Zero") {
					c.setEditMode(SamplesView.EDIT_ZERO);
				} else if (item == "Shift") {
					c.setEditMode(SamplesView.EDIT_SHIFT);
				}
				return true;
			}
		}
		return false;
	}

	private FftLabController c;
	private LabeledChoice length;
	private LabeledChoice mode;
}

class LabeledChoice extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Choice choice;

	public LabeledChoice(String label) {
		add(new Label(label, Label.RIGHT));
		choice = new Choice();
		add(choice);
	}
}