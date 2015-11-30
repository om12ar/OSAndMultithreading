package cmd;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Controller {
	@FXML
	private TextField current = new TextField();
	@FXML
	private TextArea history = new TextArea();

	ArrayList<String> output;
	ArrayList<String> allCommands = new ArrayList<>();

	int NumOfCommands = 0;
	boolean isMore = false;
	boolean isLess = false;
	int startLine = 0;
	int endLine = 10;
	int stIndex = -1;
	public static ArrayList<String> parameters = new ArrayList<>();

	public void initialize(URL url, ResourceBundle rb) {

	}

	@FXML
	public void commandEnterd(KeyEvent event) throws IOException {
		if (event.getCode() == KeyCode.ENTER) {

			history.appendText(current.getText() + "\n");
			InputHandler(current.getText() + "\n");

			current.setText("");

		} else if (event.getCode() == KeyCode.UP) {
			if (NumOfCommands > 0) {
				current.setText(allCommands.get(--NumOfCommands));
			}

		} else if (event.getCode() == KeyCode.DOWN) {
			if (NumOfCommands < allCommands.size() - 1) {
				current.setText(allCommands.get(++NumOfCommands));
			}

		} else if ((isMore || isLess) && event.getCode() == KeyCode.SPACE) {

			history.deleteText(stIndex, history.getLength());
			endLine += 10;
			stIndex = history.getLength();

			Print(Model.moreLess(parameters.get(1), startLine, endLine));
			if (isMore)
				history.appendText("Press SPACE to show more or ESC to quit\n");
			if (isLess) {
				history.appendText("Press SPACE to show more SHIFT to show less or ESC to quit\n");
			}

		} else if ((isMore || isLess) && event.getCode() == KeyCode.ESCAPE) {
			startLine = 0;
			endLine = 10;
			isMore = false;
			isLess = false;
			parameters.clear();
			history.appendText("quit\n");

		} else if (isLess && event.getCode() == KeyCode.SHIFT) {

			if (endLine > 0)
				endLine -= 10;
			history.deleteText(stIndex, history.getLength());
			Print(Model.moreLess(parameters.get(1), startLine, endLine));
			if (isMore)
				history.appendText("Press SPACE to show more or ESC to quit\n");
			if (isLess) {
				history.appendText("Press SPACE to show more SHIFT to show less or ESC to quit\n");
			}
		} else {
			NumOfCommands = allCommands.size();

		}

	}

	public void InputHandler(String userInput) throws IOException {

		if (userInput.charAt(0) == '.' && userInput.charAt(1) == '/') {
			System.out.println("READ FROM FILES " + Model.file.getAbsolutePath() + "\\" + userInput.substring(2));
			userInput = openFile(userInput) + "\n";
		}
		String currentCommand = "";
		for (int i = 0; i < userInput.length(); i++) {

			if (userInput.charAt(i) != ';' && userInput.charAt(i) != '\n') {
				System.err.println(userInput.charAt(i) + " - " + userInput.length());
				currentCommand += userInput.charAt(i);
			} else {

				allCommands.add(currentCommand);
				NumOfCommands++;
				split(currentCommand + "\n");
				System.err.println(parameters.toString());
				String command = parameters.get(0);

				switch (command) {
				case "cat":
					output = new ArrayList<String>(Model.cat(parameters));
					break;
				case "cd":
					output = new ArrayList<String>(Model.cd(parameters.get(1)));
					break;
				case "clear":

					history.setText(Model.file.getAbsolutePath() + "\n");
					output.clear();
					break;
				case "cp":
					parameters.remove(0);
					output = new ArrayList<String>(Model.copyFiles(parameters));
					break;
				case "mkdir":
					parameters.remove(0);
					output = new ArrayList<String>(Model.makeDir(parameters));
					break;
				case "mv":
					parameters.remove(0);
					output = new ArrayList<String>(Model.move(parameters));
					break;
				case "rm":
					parameters.remove(0);
					output = new ArrayList<String>(Model.remove(parameters));
					break;
				case "rmdir":
					parameters.remove(0);
					output = new ArrayList<String>(Model.removeDir(parameters));

					break;
				case "pwd":
					output = new ArrayList<String>(Model.pwd());

					break;
				case "ls":
					output = new ArrayList<String>(Model.listFiles());

					break;
				case "date":
					output = new ArrayList<String>(Model.date());

					break;
				case "help":
					output = new ArrayList<String>(Model.help());
					break;
				case "args":
					output = new ArrayList<String>();
					output.add(Model.args(parameters.get(1)));
					break;
				case "more":
					stIndex = history.getLength();
					isMore = true;
					output = new ArrayList<String>(Model.moreLess(parameters.get(1), startLine, endLine));
					history.appendText("Press SPACE to show more or ESC to quit\n");
					break;
				case "less":
					stIndex = history.getLength();
					isLess = true;
					output = new ArrayList<String>(Model.moreLess(parameters.get(1), startLine, endLine));
					history.appendText("Press SPACE to show more or ESC to quit\n");
					break;
				case "find":
					output = new ArrayList<String>(Model.find(parameters.get(1), Model.file));
					break;
				case "grep":
					parameters.remove(0);
					output = new ArrayList<String>(Model.grep(parameters));
					break;
				case "exit":
					System.exit(0);
				default:
					output.add("Invalid Command !");
					break;

				}
				if (currentCommand.contains(">>")) {
					ArrayList<String> temp = new ArrayList<>();
					temp.add(output.toString());
					temp.add(parameters.get(parameters.lastIndexOf(">>") + 1));

					Model.doubleRedirect(temp);
				} else if (currentCommand.contains(">")) {
					ArrayList<String> temp = new ArrayList<>();
					temp.add(output.toString());
					temp.add(parameters.get(parameters.lastIndexOf(">") + 1));
					Model.redirect(temp);
				}

				else {
					Print(output);
				}
				System.err.println(output);

				currentCommand = "";
			}
		}
	}

	private void Print(ArrayList<String> output2) {
		for (String s : output2) {
			history.appendText(s);
			history.appendText("\n");
		}

	}

	public static void split(String input) {
		parameters.clear();
		int startQuote = -1;
		int lastSpace = -1;
		for (int i = 0; i < input.length(); i++) {
			if (input.charAt(i) == '"') {
				if (startQuote == -1)
					startQuote = i;
				else {
					parameters.add(input.substring(startQuote, i));
					startQuote = -1;
				}
			}

			if ((input.charAt(i) == ' ' || i == input.length() - 1) && startQuote == -1) {
				String t = input.substring(lastSpace + 1, i);// .replaceAll("^\\s+|\\s+$",
																// "") ;
				System.out.println("|" + t + "|");
				parameters.add(t);

				lastSpace = i;
			}
		}
		parameters.add("\n");
	}

	public static String openFile(String userInput) {

		String path = Model.file.getAbsolutePath() + userInput.substring(2, userInput.length() - 1);
		String s = "";
		try {

			File inFile = new File(path);
			if (!inFile.exists()) {
				System.out.println(path);
				return "";
			}
			FileReader fr = null;
			fr = new FileReader(inFile);

			int k;
			while ((k = fr.read()) != -1) {
				s += (char) k;
			}

			fr.close();

		} catch (IOException ex) {
			Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
		}

		return s;

	}

}
