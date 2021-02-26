package news.laptrinhjavaweb.model;

public class commentModel extends AbstractModel<commentModel>{
	
	private String content;
	private long user_id;
	private long news_id;
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public long getUser_id() {
		return user_id;
	}
	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}
	public long getNews_id() {
		return news_id;
	}
	public void setNews_id(long news_id) {
		this.news_id = news_id;
	}
	
	
}
