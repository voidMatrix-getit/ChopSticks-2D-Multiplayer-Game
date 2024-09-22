package sahil.chopsticks;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class ChopSticksGameClient {

	private JFrame frame;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChopSticksGameClient window = new ChopSticksGameClient();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ChopSticksGameClient() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 706, 504);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
