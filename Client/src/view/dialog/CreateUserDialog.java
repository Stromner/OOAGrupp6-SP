/**
 * Dialog for creating a new user in the admin GUI.
 * 
 * @author David Stromner
 * @version 2014-02-25
 */

package view.dialog;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controller.ActionHandler;

public class CreateUserDialog extends StandardButtonsDialog {
	private static final long serialVersionUID = 3349531183617491149L;

	public CreateUserDialog(Object ... o) {
		super();

		setTitle("Create User");
	}

	/**
	 * Create all components that is going to be used inside the dialog.
	 */
	@Override
	protected void create() {
		super.create();
		Container temp;

		// UsernameLabel
		temp = new JLabel("Username:");
		components.put("usernameLabel", temp);

		// PassLabel
		temp = new JLabel("Password:");
		components.put("passwordLabel", temp);

		// AuthorityLabel
		temp = new JLabel("Authority Level:");
		components.put("authorityLabel", temp);

		// UsernameText
		temp = new JTextField();
		components.put("usernameText", temp);

		// PasswordText
		temp = new JPasswordField();
		components.put("passwordText", temp);

		// AuthorityText
		String[] sArr = { "Employee", "Admin" };
		temp = new JComboBox<String>(sArr);
		components.put("authorityText", temp);

		// An OKButton needs to be created in a subclass to CustomDialog since
		// each OKButton is going to trigger different things.
		// OkButton
		temp = new JButton("Ok");
		((JButton) temp).addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ActionHandler.getInstance().createUserDialogOk(customDialog,
						components.get("usernameText"),
						components.get("passwordText"),
						components.get("authorityText"));
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

		// UsernameLabel
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(0, 0, 0, 10);
		c.anchor = GridBagConstraints.EAST;
		canvas.add(components.get("usernameLabel"), c);

		// PasswordLabel
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(0, 0, 0, 10);
		c.anchor = GridBagConstraints.EAST;
		canvas.add(components.get("passwordLabel"), c);

		// AuthorityLabel
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 2;
		c.insets = new Insets(0, 0, 0, 10);
		canvas.add(components.get("authorityLabel"), c);

		// UsernameText
		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		c.ipadx = 150;
		c.fill = GridBagConstraints.HORIZONTAL;
		canvas.add(components.get("usernameText"), c);

		// PasswordText
		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 1;
		c.ipadx = 150;
		c.fill = GridBagConstraints.HORIZONTAL;
		canvas.add(components.get("passwordText"), c);

		// AuthorityText
		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		canvas.add(components.get("authorityText"), c);
	}
}
