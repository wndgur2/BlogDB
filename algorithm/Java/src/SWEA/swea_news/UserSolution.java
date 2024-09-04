package SWEA.swea_news;

import java.util.*;

class Channel{
	int id;
	ArrayList<User> users = new ArrayList<>();
	TreeSet<News> newss = new TreeSet<>();
	
	Channel(int id){
		this.id = id;
	}
	
	void addUser(User user) {
		this.users.add(user);
	}
	
	void addNews(News news) {
		this.newss.add(news);
	}
	
	void removeNews(News news) {
		newss.remove(news);
	}
	
	TreeSet<News> getNewsBetween(int stTime, int curTime){
		News from = new News(1000000000, curTime);
		News to = new News(0, stTime);
		return (TreeSet<News>)newss.subSet(from, to);
	}
}

class UserChannel{
	int lastViewed;
	Channel channel;
	
	UserChannel(int lastViewed, Channel channel){
		this.lastViewed = lastViewed;
		this.channel = channel;
	}
}

class User{
	int id;
	ArrayList<UserChannel> channels = new ArrayList<>(30);
	
	User(int id){
		this.id = id;
	}
	
	void addChannel(int curTime, Channel channel) {
		channels.add(new UserChannel(curTime, channel));
	}
	
	void update(int curTime) {
		for(UserChannel uc: channels) {
			uc.lastViewed = curTime;
		}
	}
}

class News implements Comparable<News>{
	int id;
	int time;
	
	News(int id, int time){
		this.id = id;
		this.time = time;
	}
	
	@Override
	public int compareTo(News other) {
		if(other.time == time) return other.id - id;
		return other.time - time; // 내림차순
	}
	
	@Override
	public String toString() {
		return "news[" + id + " " + time + "] ";
	}
	
}

class UserSolution {
	
	// 새로운 뉴스의 딜레이 사이에, 새로운 유저가 등록되면 이 유저도 나중에 그 뉴스를 받아야함.
	HashMap<Integer, Channel> channels;
	HashMap<Integer, User> users;
	HashMap<Integer, News> newss;
    
    void init(int N, int K)
    {
    	channels = new HashMap<>(K);
    	users = new HashMap<>(N);
    	newss = new HashMap<>();
    }

    void registerUser(int mTime, int mUID, int mNum, int mChannelIDs[])
    {
    	// 채널이 없으면 채널을 만들어 등록
    	for(int i=0; i<mNum; i++) {
    		int id = mChannelIDs[i];
    		if(!channels.containsKey(id))
    			channels.put(id, new Channel(id));
    	}
    	
    	// 유저가 없으면 유저를 만들어 등록한다.
    	User user;
    	if(users.containsKey(mUID)) user = users.get(mUID);
    	else {
    		user = new User(mUID);
    		users.put(mUID, user);
    	}
    	
    	// 유저를 더한다.
    	Channel channel;
    	for(int i=0; i<mNum; i++) {
    		channel = channels.get(mChannelIDs[i]);
			channel.addUser(user);
			user.addChannel(mTime+1, channel);
    	}
    }
    
    int offerNews(int mTime, int mNewsID, int mDelay, int mChannelID)
    {
    	News news = new News(mNewsID, mTime+mDelay);
    	newss.put(mNewsID, news);
    	Channel channel = channels.get(mChannelID);
    	channel.newss.add(news);
        return channel.users.size();
    }

    void cancelNews(int mTime, int mNewsID)
    {
    	if(!newss.containsKey(mNewsID)) return;
    	News news = newss.get(mNewsID);
    	newss.remove(mNewsID);
		for(Channel channel: channels.values()) {
			channel.removeNews(news);
		}
    }

    int checkUser(int mTime, int mUID, int mRetIDs[])
    {
    	int res = 0;
    	User user = users.get(mUID);
    	TreeSet<News> newsSet = new TreeSet<>();

    	// 유저의 last viewed부터 현재까지의 시간에 포함되는 뉴스들.
    	for(UserChannel userChannel:user.channels) {
    		int lastTime = userChannel.lastViewed;
    		Channel channel = userChannel.channel;
    		TreeSet<News> tempSet;
    		tempSet = (TreeSet<News>) channel.getNewsBetween(lastTime, mTime);
    		res += tempSet.size();
    		int i =0;
    		Iterator<News> iter = tempSet.iterator();
    		while(iter.hasNext() && i++<3) {
    			newsSet.add(iter.next());
    		}
    	}
    	for(int i=0; !newsSet.isEmpty() && i<3; i++)
    		mRetIDs[i] = newsSet.pollFirst().id;
    	user.update(mTime+1);
    	
        return res;
    }
}