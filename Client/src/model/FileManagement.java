/**
 * Handles reading and writing of files
 * 
 * @author	Erik Hermansson
 * @version 2014-02-23
 */

package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileManagement {
	private static FileManagement fm;

	private FileManagement() {

	}

	/**
	 * Singleton method.
	 * 
	 * @return The single instance of the object.
	 */
	public static FileManagement getInstance() {
		if (fm == null)
			fm = new FileManagement();
		return fm;
	}

	/**
	 * Reads one line and returns it as String
	 * 
	 * @param file
	 *            Name of the file to be read.
	 * @return First line of file as String
	 */
	public String readLine(String file) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;
			line = br.readLine();
			br.close();

			return line;

			// IOException
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * @param s
	 * @param args
	 */
	public void writeStrings(String file, String... args) {
		try {
			PrintWriter out = new PrintWriter(new FileWriter(file));
			for (String o : args) {
				out.write(o);
				out.write(":");
			}
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
