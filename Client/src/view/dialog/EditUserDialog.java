/**
 * Dialog for editing a user in the admin GUI. Receives a list from the server on all the users which can be filtered.
 * 
 * @author David Stromner
 * @version 2014-02-25
 */

package view.dialog;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import model.Communication;
import model.User;
import controller.ActionHandler;

public class EditUserDialog extends GetUserDialog implements Observer {
	private static final long serialVersionUID = -274172269787570689L;

	public EditUserDialog(Object ... o) {
		super(o[0]);

		setTitle("Edit User");
	}

	public void update(Observable o, Object arg) {
		super.update(o, arg);
		if (o instanceof Communication) {
			LinkedList<Object> argsList = (LinkedList<Object>) arg;

			// Check what type of message was received
			if (((String) argsList.get(0)).compareToIgnoreCase("GetUser") == 0) {
				User user = (User) argsList.get(1);
				((JTextField) components.get("uIUsernameText")).setText(user
						.getPerNr());
				((JTextField) components.get("uIPasswordText")).setText(user
						.getPassword());
				((JTextField) components.get("uIAuthorityText")).setText(user
						.getStatus());
			}
		}
	}

	/**
	 * Create all the components that is going to be used inside the dialog.
	 * 
	 */
	@Override
	protected void create() {
		super.create();
		Container temp;

		initUserInformation();
		initGetUser();
		initEditUser();

		// An OKButton needs to be created in a subclass to CustomDialog since
		// each OKButton is going to trigger different things.
		// OkButton
		temp = new JButton("Ok");
		((JButton) temp).addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ActionHandler.getInstance().editUserDialogOk(customDialog,
						components.get("uIUsernameText"),
						components.get("eUPasswordPF"),
						components.get("eUConfirmPasswordPF"),
						components.get("eUAuthorityComboBox"));
			}
		});
		components.put("okButton", temp);
	}

	/**
	 * Create all the components that is going to be used inside the original
	 * info dialog.
	 */
	private void initUserInformation() {
		Container temp;

		// Panel
		temp = new JPanel();
		((JPanel) temp).setLayout(new GridBagLayout());
		components.put("uIPanel", temp);

		// Label
		temp = new JLabel("User information:");
		components.put("uILabel", temp);

		// UsernameLabel
		temp = new JLabel("Username:");
		components.put("uIUsernameLabel", temp);

		// PasswordLabel
		temp = new JLabel("Password:");
		components.put("uIPasswordLabel", temp);

		// AuthorityLabel
		temp = new JLabel("Authority Level:");
		components.put("uIAuthorityLabel", temp);

		// UsernameText
		temp = new JTextField();
		((JTextField) temp).setEditable(false);
		((JTextField) temp).setEnabled(false);
		components.put("uIUsernameText", temp);

		// PasswordText
		temp = new JTextField();
		((JTextField) temp).setEditable(false);
		((JTextField) temp).setEnabled(false);
		components.put("uIPasswordText", temp);

		// AuthorityText
		temp = new JTextField();
		((JTextField) temp).setEditable(false);
		((JTextField) temp).setEnabled(false);
		components.put("uIAuthorityText", temp);
	}

	/**
	 * Create all the components that is going to be used inside the get dialog.
	 */
	private void initGetUser() {
		Container temp;

		// Label
		temp = new JLabel("Get user:");
		components.put("gULabel", temp);

		// Button
		temp = new JButton("Fetch user");
		((JButton) temp).addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ActionHandler.getInstance().getUser(
						components.get("gUComboBox"));
			}
		});
		components.put("fetchUserButton", temp);
	}

	/**
	 * Create all the components that is going to be used inside the edit
	 * dialog.
	 */
	private void initEditUser() {
		Container temp;

		// Panel
		temp = new JPanel();
		((JPanel) temp).setLayout(new GridBagLayout());
		components.put("eUPanel", temp);

		// Label
		temp = new JLabel("Edit user:");
		components.put("eULabel", temp);

		// PasswordLabel
		temp = new JLabel("Password:");
		components.put("eUPasswordLabel", temp);

		// ConfirmPasswordLabel
		temp = new JLabel("Confirm Password:");
		components.put("eUConfirmPasswordLabel", temp);

		// AuthorityLabel
		temp = new JLabel("Authority Level:");
		components.put("eUAuthorityLabel", temp);

		// PasswordPasswordField
		temp = new JPasswordField();
		components.put("eUPasswordPF", temp);

		// ConfirmPasswordPasswordField
		temp = new JPasswordField();
		components.put("eUConfirmPasswordPF", temp);

		// AuthorityComboBox
		String[] sArr = { "Employee", "Admin" };
		temp = new JComboBox<String>(sArr);
		components.put("eUAuthorityComboBox", temp);

		// DelteUserButton
		temp = new JButton("Delete user");
		((JButton) temp).addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ActionHandler.getInstance().deleteUser(customDialog,
						components.get("uIUsernameText"));
			}
		});
		components.put("eUDeleteUserButton", temp);
	}

	/**
	 * Place all the created components.
	 */
	@Override
	protected void build() {
		super.build();

		buildUserInformation();
		buildGetUser();
		buildEditUser();
	}

	/**
	 * Place all the original info components.
	 */
	private void buildUserInformation() {
		GridBagConstraints c;
		JPanel canvas = getCanvas();
		JPanel original = (JPanel) components.get("uIPanel");

		// Panel
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.anchor = GridBagConstraints.WEST;
		canvas.add(original, c);

		// Label
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(0, 0, 30, 0);
		original.add(components.get("uILabel"), c);

		// UsernameLabel
		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 1;
		c.anchor = GridBagConstraints.EAST;
		c.insets = new Insets(0, 0, 0, 10);
		original.add(components.get("uIUsernameLabel"), c);

		// PasswordLabel
		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 2;
		c.anchor = GridBagConstraints.EAST;
		c.insets = new Insets(2, 0, 2, 10);
		original.add(components.get("uIPasswordLabel"), c);

		// AuthorityLabel
		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 3;
		c.anchor = GridBagConstraints.EAST;
		c.insets = new Insets(0, 0, 0, 10);
		original.add(components.get("uIAuthorityLabel"), c);

		// UsernameText
		c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 1;
		c.ipadx = 100;
		c.fill = GridBagConstraints.HORIZONTAL;
		original.add(components.get("uIUsernameText"), c);

		// PasswordText
		c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 2;
		c.ipadx = 100;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(2, 0, 2, 0);
		original.add(components.get("uIPasswordText"), c);

		// AuthorityText
		c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 3;
		c.ipadx = 100;
		c.fill = GridBagConstraints.HORIZONTAL;
		original.add(components.get("uIAuthorityText"), c);
	}

	/**
	 * Place all the get components.
	 */
	private void buildGetUser() {
		GridBagConstraints c;
		JPanel canvas = getCanvas();
		JPanel getUser = (JPanel) components.get("gUPanel");

		// Label
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(0, 0, 30, 0);
		getUser.add(components.get("gULabel"), c);

		// Button
		c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 3;
		c.weighty = 1;
		c.insets = new Insets(50, 0, 0, 0);
		getUser.add(components.get("fetchUserButton"), c);

	}

	/**
	 * Place all the edit components.
	 */
	private void buildEditUser() {
		GridBagConstraints c;
		JPanel canvas = getCanvas();
		JPanel editUser = (JPanel) components.get("eUPanel");

		// Panel
		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		c.gridheight = 2;
		c.anchor = GridBagConstraints.NORTH;
		canvas.add(editUser, c);

		// Label
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(0, 0, 30, 0);
		editUser.add(components.get("eULabel"), c);

		// PasswordLabel
		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 1;
		c.anchor = GridBagConstraints.EAST;
		c.insets = new Insets(0, 0, 0, 10);
		editUser.add(components.get("eUPasswordLabel"), c);

		// ConfirmPasswordLabel
		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 2;
		c.anchor = GridBagConstraints.EAST;
		c.insets = new Insets(0, 0, 0, 10);
		editUser.add(components.get("eUConfirmPasswordLabel"), c);

		// AuthorityLabel
		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 3;
		c.anchor = GridBagConstraints.EAST;
		c.insets = new Insets(0, 0, 0, 10);
		editUser.add(components.get("eUAuthorityLabel"), c);

		// PasswordPasswordField
		c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 1;
		c.ipadx = 65;
		c.fill = GridBagConstraints.HORIZONTAL;
		editUser.add(components.get("eUPasswordPF"), c);

		// ConfirmPasswordPasswordField
		c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 2;
		c.ipadx = 65;
		c.fill = GridBagConstraints.HORIZONTAL;
		editUser.add(components.get("eUConfirmPasswordPF"), c);

		// AuthorityComboBox
		c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 3;
		c.ipadx = 65;
		c.fill = GridBagConstraints.HORIZONTAL;
		editUser.add(components.get("eUAuthorityComboBox"), c);

		// DeleteUserButton
		c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 4;
		c.insets = new Insets(50, 0, 0, 0);
		c.anchor = GridBagConstraints.SOUTH;
		editUser.add(components.get("eUDeleteUserButton"), c);
	}
}
