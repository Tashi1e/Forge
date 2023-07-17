package tcejorptset.servl.dao.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ContentTextIO {
	
	private static final ContentTextIO instance = new ContentTextIO();
	private File file;
	private final String path = "d:/_Java/_Workspace/Forge/NewsPortal/src/main/webapp/articles";
	
	private ContentTextIO() {};
	
	public boolean createFile(int newsId) throws IOException {
		
		File folder = new File(String.format(path + "/art%d", newsId));
		folder.mkdirs();
		
		file = new File(String.format(path + "/art%d/content%d.txt", newsId, newsId));
		return file.createNewFile(); 
	}

	public boolean fileExists(int newsId) {
		file = new File(String.format(path + "/art%d/content%d.txt", newsId, newsId));
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
		
		System.out.println(newsId + " " + content); //TEST
		
		try (var fileWrite = new BufferedWriter(new FileWriter(file))) {
			fileWrite.write(content);
			recorded = true;
		}
		return recorded;
	}
	
	public static ContentTextIO getInstance () {
		return instance;
	};
}
