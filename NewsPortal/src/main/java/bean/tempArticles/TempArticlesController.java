package bean.tempArticles;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import bean.News;

public class TempArticlesController {
	
	public News article (int id) {
		News news = new News();
	String lineText;
	File file = null;
	try {
		file = new File(getClass().getClassLoader().getResource("art"+id+".txt").toURI());
	} catch (URISyntaxException e) {
		e.printStackTrace();
	}
    try (var fileRead = new BufferedReader(new FileReader(file))) {
        lineText = fileRead.readLine();
        while (lineText != null) {
            switch (lineText) {
                case "<head>" -> news.setTitle(fileRead.readLine());
                case "<img>" -> news.setImg(fileRead.readLine());
                case "<body>" -> news.setContent(fileRead.readLine());
            }
            lineText = fileRead.readLine();
        }
    } catch (IOException e) {
		e.printStackTrace();
	}
    Pattern pattern = Pattern.compile("^(.*?[?!.])(?=\\s*[A-ZA-ЯЁ]|$)");
    Matcher matcher = pattern.matcher(news.getContent());
    if (matcher.find())
        news.setBriefNews(matcher.group());

    Date currenDate = new Date();
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
    news.setNewsDate(dateFormat.format(currenDate));    
    return news;
	}
}
