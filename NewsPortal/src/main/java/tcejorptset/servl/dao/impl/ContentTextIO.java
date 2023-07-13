package tcejorptset.servl.dao.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ContentTextIO {

	private File file;

	public boolean createFile(int newsId) throws IOException {
		File folder = new File(String.format("src/main/webapp/articles/art%d", newsId));
		folder.mkdirs();
		file = new File(String.format("src/main/webapp/articles/art%d/content.txt", newsId));
		return file.createNewFile();
	}

	public boolean fileExists(int newsId) {
		file = new File(String.format("src/main/webapp/articles/art%d/content.txt", newsId));
		return file.exists();
	}

	public String getContent(int newsId) throws FileNotFoundException, IOException {
		String text = "NO CONTENT FOUND!";
		StringBuffer content = new StringBuffer();
		if (fileExists(newsId)) {
			try (var fileRead = new BufferedReader(new FileReader(file))) {
				String lineText;
				do {
					lineText = fileRead.readLine();
					if (lineText != null) {
						content.append(lineText + "\n");
					}
				} while (lineText != null);
				text = content.toString();
			}
		}
		return text;
	}

	public boolean setContent(int newsId, String content) throws IOException {
		boolean recorded = false;
		if (!fileExists(newsId)) {
			createFile(newsId);
		}
		try (var fileWrite = new BufferedWriter(new FileWriter(file))) {
			fileWrite.write(content);
			recorded = true;
		}
		return recorded;
	}
}
