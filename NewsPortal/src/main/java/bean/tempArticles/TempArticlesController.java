package bean.tempArticles;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import bean.News;

public class TempArticlesController {

	public News article(int id) {
		News news = null;
		String lineText;
		String path = String.format("D:/_Java/_Workspace/Forge/NewsPortal/src/main/resources/art%d.txt", id);

		try (var fileRead = new BufferedReader(new FileReader(path))) {
			news = new News();
			lineText = fileRead.readLine();
			while (lineText != null) {
				switch (lineText) {
				case "<head>" -> news.setTitle(fileRead.readLine());
				case "<img>" -> news.setImg(fileRead.readLine());
				case "<body>" -> news.setContent(fileRead.readLine());
				}
				lineText = fileRead.readLine();
			}

			news.setIdNews(id);

			Pattern pattern = Pattern.compile("^(.*?[?!.])(?=\\s*[A-ZA-ЯЁ]|$)");
			Matcher matcher = pattern.matcher(news.getContent());
			if (matcher.find())
				news.setBriefNews(matcher.group());

			Date currenDate = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
			news.setNewsDate(dateFormat.format(currenDate));
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return news;
	}
}
