package util.temp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import bean.News;

public class TempArticleSource {

	public News article(int id) {
		News news = null;
		String lineText;
		String filePath = String.format("articles/art%d.txt", id);
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filePath);
		

		try (var fileRead = new BufferedReader(new InputStreamReader(inputStream))) {
			news = new News();
			lineText = fileRead.readLine();
			while (lineText != null) {
				switch (lineText) {
				case "<head>" -> news.setTitle(fileRead.readLine());
				case "<img>" -> news.setImage(fileRead.readLine());
				case "<body>" -> news.setContent(fileRead.readLine());
				}
				lineText = fileRead.readLine();
			}

			news.setId(id);

			Pattern pattern = Pattern.compile("^(.*?[?!.])(?=\\s*[A-ZA-ЯЁ]|$)");
			Matcher matcher = pattern.matcher(news.getContent());
			if (matcher.find())
				news.setBrief(matcher.group());

			Date currenDate = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
			news.setDate(dateFormat.format(currenDate));
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return news;
	}
}
