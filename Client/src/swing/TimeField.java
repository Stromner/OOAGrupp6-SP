/**
 * A JTextField that accepts only integers.
 *
 * @author David Buzatto
 */

package swing;

import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

public class TimeField extends JTextField {
	private static final long serialVersionUID = 5642529363921554133L;
	private int threshold;

	public TimeField(String text, int threshold) {
		super(text);
		
		this.threshold = threshold;
	}

	public TimeField(int cols) {
		super(cols);
	}

	@Override
	protected Document createDefaultModel() {
		return new UpperCaseDocument();
	}

	static class UpperCaseDocument extends PlainDocument {
		private static final long serialVersionUID = -2059127026594440185L;

		@Override
		public void insertString(int offs, String str, AttributeSet a)
				throws BadLocationException {

			if (str == null) {
				return;
			}
			if (getLength() < 2) {
				char[] chars = str.toCharArray();
				boolean ok = true;
				
				for (int i = 0; i < chars.length; i++) {

					try {
						Integer.parseInt(String.valueOf(chars[i]));
					} catch (NumberFormatException exc) {
						ok = false;
						break;
					}
				}

				if (ok) {
					super.insertString(offs, new String(chars), a);
				}
			}

		}
	}

}