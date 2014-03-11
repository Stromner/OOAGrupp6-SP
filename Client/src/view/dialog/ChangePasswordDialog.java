/** 
 * Dialog for changing password in the employee GUI.
 * 
 * @author David Stromner
 * @version 2014-02-26
 */

package view.dialog;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import controller.ActionHandler;

public class ChangePasswordDialog extends StandardButtonsDialog {

	private static final long serialVersionUID = -4565077328498919365L;

	public ChangePasswordDialog(Object ... o) {
		super();

		setTitle("Edit Password");
	}

	/**
	 * Create all the components that is going to be used inside the dialog.
	 */
	@Override
	protected void create() {
		super.create();
		Container temp;

		// PasswordLabel
		temp = new JLabel("Password:");
		components.put("passwordLabel", temp);

		// ConfirmPasswordLabel
		temp = new JLabel("Confirm Password:");
		components.put("confirmPasswordLabel", temp);

		// PasswordPasswordField
		temp = new JPasswordField();
		components.put("passwordPF", temp);

		// ConfirmPasswordPasswordField
		temp = new JPasswordField();
		components.put("confirmPasswordPF", temp);

		// An OKButton needs to be created in a subclass to CustomDialog since
		// each OKButton is going to trigger different things.
		// OkButton
		temp = new JButton("Ok");
		((JButton) temp).addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ActionHandler.getInstance().passwordDialogOk(customDialog,
						components.get("passwordPF"),
						components.get("confirmPasswordPF"));
			}
		});
		components.put("okButton", temp);
	}

	/**
	 * Place all the created components.
	 */
	@Override
	protected void build() {
		super.build();
		GridBagConstraints c;
		JPanel canvas = getCanvas();

		// PasswordLabel
		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 1;
		c.anchor = GridBagConstraints.EAST;
		c.insets = new Insets(0, 0, 0, 10);
		canvas.add(components.get("passwordLabel"), c);

		// ConfirmPasswordLabel
		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 2;
		c.anchor = GridBagConstraints.EAST;
		c.insets = new Insets(0, 0, 0, 10);
		canvas.add(components.get("confirmPasswordLabel"), c);

		// PasswordPasswordField
		c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 1;
		c.ipadx = 65;
		c.fill = GridBagConstraints.HORIZONTAL;
		canvas.add(components.get("passwordPF"), c);

		// ConfirmPasswordPasswordField
		c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 2;
		c.ipadx = 65;
		c.fill = GridBagConstraints.HORIZONTAL;
		canvas.add(components.get("confirmPasswordPF"), c);

	}
}
