/**
 * Extensions of custom dialog, contains an empty Ok-button and an Cancel-button.
 * 
 * @author David Stromner
 * @version 2014-02-24
 */

package view.dialog;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import controller.ActionHandler;

public abstract class StandardButtonsDialog extends CustomDialog {
	private static final long serialVersionUID = 4131677888057562310L;

	/**
	 * Create and build all components for the dialog. Set the dialog to
	 * blocking(no other window can be touched) and non-resizable.
	 */
	public StandardButtonsDialog(Object ... o) {
		super();

		setTitle("Standard Button Dialog");
	}

	/**
	 * Create all the components that is going to be used inside the dialog.
	 * Except for the Ok-button since it needs to have different actionlistners
	 * connected to it depending on the dialog.
	 */
	protected void create() {
		super.create();
		Container temp;

		// ToolbarPanel
		temp = new JPanel();
		components.put("toolbarPanel", temp);

		// CancelButton
		temp = new JButton("Cancel");
		((JButton) temp).addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ActionHandler.getInstance().dialogCancel(customDialog);
			}
		});
		components.put("cancelButton", temp);

		// An OKButton needs to be created in a subclass since each OKButton is
		// going to trigger different things.
	}

	/**
	 * Place all the created components.
	 */
	protected void build() {
		super.build();
		GridBagConstraints c;

		// ToolbarPanel
		c = new GridBagConstraints();
		c.ipadx = 60;
		c.ipady = 20;
		c.gridx = 0;
		c.gridy = 1;
		components.get("wrapperPanel").add(components.get("toolbarPanel"), c);

		// OkButton
		components.get("toolbarPanel").add(components.get("okButton"));

		// CancelButton
		components.get("toolbarPanel").add(components.get("cancelButton"));

		add(components.get("wrapperPanel"));
	}
}
