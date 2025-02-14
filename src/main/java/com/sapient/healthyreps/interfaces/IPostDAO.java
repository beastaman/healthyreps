package com.sapient.healthyreps.interfaces;

import java.sql.Timestamp;
import java.util.List;

import com.sapient.healthyreps.entity.Post;

public interface IPostDAO {

	public boolean insertPost(int pid, int uid, String title, int categoryId, String content, int votes, Timestamp timestamp, int reported);
	public boolean insertPost(int uid, String title, int categoryId, String content, int votes, Timestamp timestamp, int reported);
	
	//added today
	public boolean insertPost(Post post);
	
	public List<Post> getAllPosts();
	public boolean deletePostbyId(int pid);
	public boolean updatePost(int pid,String title, String content,  int categoryId, int votes, Timestamp timestamp, int reported);
	
	//added today
	public boolean updatePost(Post post);
	public Post getPostbyId(int pid);
	
	public boolean updateVoteCountbyId(int pid, int votes);
	
	public List<Post> getAllPostsByCategoryId(int categoryId);
	
	
//	public HashMap<Integer,HashMap<String,String>> getinBrief();
	
	public List<Post> getAllPostbyUserId(int pid);
	
	public List<Post> getAllVisiblePosts();
	
	public boolean updateStatusofPost(int pid,int newstatus);
	
	public List<Post> getAllDraftPosts(int uid);
	
	public List<Post> getAllReportedPosts();
	
}
