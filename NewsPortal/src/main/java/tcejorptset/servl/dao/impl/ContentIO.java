package tcejorptset.servl.dao.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ResourceBundle;

import jakarta.servlet.http.Part;

public class ContentIO {

	private static final ContentIO instance = new ContentIO();
	private File file;
	private String imageURL;
	private final String path = ResourceBundle.getBundle("articles.art").getString("art.url");

	private ContentIO() {
	}

	public static ContentIO getInstance() {
		return instance;
	}

	private boolean createTextFile(int newsId) throws IOException {
		File folder = new File(String.format(path + "/art%d", newsId));
		folder.mkdirs();

		file = new File(String.format(path + "/art%d/content%d.txt", newsId, newsId));
		return file.createNewFile();
	}

	private boolean textFileExists(int newsId) {
		file = new File(String.format(path + "/art%d/content%d.txt", newsId, newsId));
		return file.exists();
	}

	public String getImagePath(int newsId) {
		return String.format("articles/art%d/img%d.jpg", newsId, newsId);
	}

	public String getContent(int newsId) throws FileNotFoundException, IOException {
		String text = "NO CONTENT FOUND!";
		StringBuffer content = new StringBuffer();
		if (textFileExists(newsId)) {
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
		if (!textFileExists(newsId)) {
			createTextFile(newsId);
		}
		try (var fileWrite = new BufferedWriter(new FileWriter(file))) {
			fileWrite.write(content);
			recorded = true;
		}
		return recorded;
	}

	public void setImage(int newsId, Part imgPart) throws IOException {
		if (imgPart.getSize() > 0) {
			imageURL = String.format(path + "/art%d/img%d.jpg", newsId, newsId);
			imgPart.write(imageURL);
		}
	}

}
