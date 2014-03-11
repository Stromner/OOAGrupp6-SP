/**
 * Base abstract class for all custom dialogs.
 * 
 * @author David Stromner
 * @version 2014-02-24
 */

package view.dialog;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.HashMap;

import javax.swing.JDialog;
import javax.swing.JPanel;

public abstract class CustomDialog extends JDialog {
	private static final long serialVersionUID = -5915821852060912326L;
	private JPanel canvas;
	protected final CustomDialog customDialog = this;
	protected HashMap<String, Container> components;

	/**
	 * Create and build all components for the dialog. Set the dialog to
	 * blocking(no other window can be touched) and non-resizable.
	 */
	public CustomDialog(Object ... o) {
		super();
		components = new HashMap<String, Container>();

		create();
		build();

		pack();
		setLocationRelativeTo(this.getParent());
		setTitle("Custom Dialog");
		setModal(true); // Block all other components when visible
		setResizable(false);
	}

	/**
	 * @return panel to put all components inside.
	 */
	public JPanel getCanvas() {
		return canvas;
	}

	/**
	 * Create all the components that is going to be used inside the dialog.
	 * Except for the Ok-button since it needs to have different actionlistners
	 * connected to it depending on the dialog.
	 */
	protected void create() {
		canvas = new JPanel();
		canvas.setLayout(new GridBagLayout());
		Container temp;

		// WrapperPanel
		temp = new JPanel();
		temp.setLayout(new GridBagLayout());
		components.put("wrapperPanel", temp);
	}

	/**
	 * Place all the created components.
	 */
	protected void build() {
		GridBagConstraints c;

		// Canvas
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.ipadx = 60;
		c.ipady = 20;
		components.get("wrapperPanel").add(canvas, c);

		add(components.get("wrapperPanel"));
	}
}
