/**
 * All unique swing components for the LoginGUI.
 * 
 * @author David Stromner
 * @version 2013-02-07
 */

package view.gui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controller.ActionHandler;

public class LoginGUI extends GUI  {
	private static final long serialVersionUID = 5285405077713830734L;

	public LoginGUI() {
		super();
	}

	/**
	 * Create all the labels.
	 */
	@Override
	protected void initLabels() {
		super.initLabels();

		// Init IDLabel
		components.put("usernameLabel", new JLabel("Username:"));

		// Init passwordLabel
		components.put("passwordLabel", new JLabel("Password:"));
	}

	/**
	 * Create all the panels.
	 */
	@Override
	protected void initPanels() {
		super.initPanels();

		JPanel tempPanel;

		// Init logInWindow to put logInButton, userName, and password into.
		tempPanel = new JPanel();
		tempPanel.setLayout(new GridBagLayout());
		tempPanel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createRaisedBevelBorder(), "Log In"));
		components.put("logInWindow", tempPanel);

		// Init wrapperPanel for logInWindow
		tempPanel = new JPanel();
		tempPanel.setLayout(new GridBagLayout());
		components.put("wrapperPanel", tempPanel);
	}

	/**
	 * Create all the text fields.
	 */
	@Override
	protected void initTextFields() {
		super.initTextFields();

		JTextField tempField;

		// Init userName
		tempField = new JTextField();
		components.put("usernameTF", tempField);

		// Init password
		tempField = new JPasswordField();
		components.put("passwordPF", tempField);
	}

	/**
	 * Create all the buttons.
	 */
	@Override
	protected void initButtons() {
		super.initButtons();

		JButton tempButton;

		// Init logIn
		tempButton = new JButton("Log In");
		tempButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ActionHandler.getInstance().logIn(components.get("usernameTF"),
						components.get("passwordPF"));
			}
		});
		components.put("logInButton", tempButton);

	}

	/**
	 * Place all the components.
	 */
	@Override
	protected void buildGUI() {
		super.buildGUI();

		JPanel panel = (JPanel) components.get("logInWindow");
		JPanel wrapper = (JPanel) components.get("wrapperPanel");
		GridBagConstraints c;

		// usernameLabel
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(0, 0, 0, 10);
		panel.add(components.get("usernameLabel"), c);

		// passwordLabel
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(0, 0, 0, 10);
		panel.add(components.get("passwordLabel"), c);

		// usernameTF
		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		c.ipadx = 270;
		panel.add(components.get("usernameTF"), c);

		// passwordPF
		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 1;
		c.ipadx = 270;
		panel.add(components.get("passwordPF"), c);
		c.ipadx = 0;

		// logInButton
		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 2;
		c.anchor = GridBagConstraints.SOUTHEAST;
		c.insets = new Insets(16, 0, 0, 0);
		panel.add(components.get("logInButton"), c);

		// logInWindow
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.ipadx = 120;
		c.ipady = 50;
		c.anchor = GridBagConstraints.CENTER;
		wrapper.add(panel, c);

		getCanvas().add(wrapper, BorderLayout.CENTER);
	}
}