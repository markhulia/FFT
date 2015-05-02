import java.awt.BorderLayout;
import java.awt.Event;
import java.awt.Frame;

@SuppressWarnings("serial")
public class MainRecognizer extends java.applet.Applet {
	MainPanel mainPanel;
	ControlPanel controlPanel;

	public void init() {
		FftLabController controller = new FftLabController();
		setLayout(new BorderLayout());
		mainPanel = new MainPanel(controller.fRealView, controller.fImagView,
				controller.gRealView, controller.gImagView);
		add("Center", mainPanel);
		controlPanel = new ControlPanel(controller);
		add("South", controlPanel);
	}

	@SuppressWarnings("deprecation")
	public void start() {
		mainPanel.enable();
		controlPanel.enable();
	}

	@SuppressWarnings("deprecation")
	public void stop() {
		mainPanel.disable();
		controlPanel.disable();
	}

	public boolean handleEvent(Event e) {
		if (e.id == Event.WINDOW_DESTROY) {
			System.exit(0);
		}
		return false;
	}

	@SuppressWarnings("deprecation")
	public static void main(String args[]) {
		Frame frame = new Frame("FFT Laboratory");
		MainRecognizer fftLab = new MainRecognizer();
		fftLab.init();
		fftLab.start();
		frame.add("Center", fftLab);
		frame.resize(600, 400);
		frame.show();
	}
}