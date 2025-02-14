package com.sapient.healthyreps.dao;

import java.sql.*;
import java.util.*;

import com.sapient.healthyreps.exception.InvalidId;
import com.sapient.healthyreps.interfaces.ICommentsPostDAO;
import com.sapient.healthyreps.utils.DbConnect;
import com.sapient.healthyreps.entity.CommentsPost;


public class CommentsPostDAO implements ICommentsPostDAO{
	
	@Override
	public boolean insertComment(CommentsPost comment)
	{
		String sql = "insert into commentsPost (votes, content , postId, userId, timeStamp, reported) values(?,?,?,?,?,?)";
		try {

			PreparedStatement ps = DbConnect.getMySQLConn().prepareStatement(sql);
			ps.setInt(1, comment.getVotes());
			ps.setString(2, comment.getContent());
			ps.setInt(3, comment.getPostId());
			ps.setInt(4, comment.getUserId());
			ps.setTimestamp(5, comment.getTimestamp());
			ps.setInt(6, comment.getReported());

			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public boolean insertComment(int commentId, int votes, String content, int postId, int userId, Timestamp timestamp, int reported)
	{
		String sql = "insert into commentsPost (commentId, votes, content , postId, userId, timeStamp, reported) values(?,?,?,?,?,?,?)";
		try {

			PreparedStatement ps = DbConnect.getMySQLConn().prepareStatement(sql);
			ps.setInt(1, commentId);
			ps.setInt(2, votes);
			ps.setString(3, content);
			ps.setInt(4, postId);
			ps.setInt(5, userId);
			ps.setTimestamp(6, timestamp);
			ps.setInt(7, reported);

			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;

	}
	
	
	@Override 
	public boolean updateComment(int commentId, int votes, String content, int postId, int userId, Timestamp timestamp, int reported)
	{
		String sql= "UPDATE commentsPost SET votes=?, content= ?, postId= ?, userId = ?, timeStamp= ?, reported= ? WHERE commentId=?";
		
		try {
			PreparedStatement ps= DbConnect.getMySQLConn().prepareStatement(sql);
			ps.setInt(1,  votes);
			ps.setString(2,  content);
			ps.setInt(3, postId);
			ps.setInt(4, userId);
			ps.setTimestamp(5, timestamp);
			ps.setInt(6, reported);
			ps.setInt(7, commentId);
			
			return ps.executeUpdate()>0;
		}
		
		catch(SQLException e)
		{
			//TODO Auto-generated
			e.printStackTrace();
		}
		
		return false;
	}
	 @Override
	 public boolean updateComment(CommentsPost comment)
	 {
		 
		 String sql = "UPDATE commentsPost SET votes=?, content=?, postId=?, userId=?, timeStamp=?, reported= ? WHERE commentId = ?";

			try {
				PreparedStatement ps = DbConnect.getMySQLConn().prepareStatement(sql);
				ps.setInt(1, comment.getVotes());
				ps.setString(2, comment.getContent());
				ps.setInt(3, comment.getPostId());
				ps.setInt(4, comment.getUserId());
				ps.setTimestamp(5, comment.getTimestamp());
				ps.setInt(6, comment.getReported());
				ps.setInt(7, comment.getId());

				return ps.executeUpdate() > 0;

			} catch (SQLException e) {
				e.printStackTrace();
			}

			return false;
	 }
	 @Override
	 public List<CommentsPost> getAllComments()
	 {
		 List<CommentsPost> commentsPost = new ArrayList<>();
			String statement = "select * from commentsPost";
			try {
					PreparedStatement ps= DbConnect.getMySQLConn().prepareStatement(statement);
					ResultSet rs = ps.executeQuery();
					while(rs.next()) {
						CommentsPost commentPost = new CommentsPost(rs.getInt(1),
								rs.getInt(2),
								rs.getString(3),
								rs.getInt(4),
								rs.getInt(5),
								rs.getTimestamp(6),
								rs.getInt(7)); 
							
						commentsPost.add(commentPost); 
					}
				}catch(SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			return commentsPost;
	 }
	 
	 @Override
		public CommentsPost getCommentByCommentId(int commentId) {

			try {
				checkCommentId(commentId);
			} catch (InvalidId e1) {
				
				e1.printStackTrace();
				return null;
			}

			String sql = "select commentId, votes, content, postId, userId, timeStamp,reported from commentsPost where commentId=?";
			try {

				PreparedStatement ps = DbConnect.getMySQLConn().prepareStatement(sql);
				ps.setInt(1, commentId);
				ResultSet rs = ps.executeQuery();

				rs.next();

				CommentsPost comment = new CommentsPost();
				comment.setId(rs.getInt(1));
				comment.setVotes(rs.getInt(2));
				comment.setContent(rs.getString(3));
				comment.setPostId(rs.getInt(4));
				comment.setUserId(rs.getInt(5));
				comment.setTimestamp(rs.getTimestamp(6));
				comment.setReported(rs.getInt(7));

				return comment;

			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;

		}

	 @Override
	 public List<CommentsPost> getAllCommentsByPostId(int postId) {
			List<CommentsPost> commentsPost = new ArrayList<>();
			String statement = "select commentId, votes, content, postId, userId, timeStamp, reported from commentsPost where postId = ?";
			try {
					PreparedStatement ps= DbConnect.getMySQLConn().prepareStatement(statement);
					ps.setInt(1,postId);
					ResultSet rs = ps.executeQuery();
					while(rs.next()) {
						CommentsPost commentPost = new CommentsPost(rs.getInt(1),
								rs.getInt(2),
								rs.getString(3),
								rs.getInt(4),
								rs.getInt(5),
								rs.getTimestamp(6),
								rs.getInt(7)); 
							
						commentsPost.add(commentPost); 
					}
				}catch(SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			return commentsPost;
		}
	 
	 
	 @Override
	 public boolean deleteCommentById(int commentId)
	 {
		 try {
				checkCommentId(commentId);
			} catch(InvalidId e1) {
				e1.printStackTrace();
				return false;
			}
			
			String sql = "DELETE FROM commentsPost WHERE commentId = ?";
			try {
				PreparedStatement ps = DbConnect.getMySQLConn().prepareStatement(sql);
				ps.setInt(1, commentId);
				
				return ps.executeUpdate() > 0;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
	 }
	 

	
	 @Override
		public boolean updateVotebyCommentId(int commentId, int votes) {
			
			
			String sql = "UPDATE commentsPost SET votes=? WHERE commentId=?";
			
			try {
				PreparedStatement ps = DbConnect.getMySQLConn().prepareStatement(sql);
				ps.setInt(1, votes);
				ps.setInt(2, commentId);
				
				return ps.executeUpdate() > 0;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
		}
	 
	
	 
	 @Override
		public boolean updateStatusofComment(int commentId,int newstatus) {
			
			
			String sql= "UPDATE commentsPost SET reported = ? where commentId = ?";
			
			try {
				PreparedStatement ps = DbConnect.getMySQLConn().prepareStatement(sql);
				ps.setInt(1, newstatus);
				ps.setInt(2, commentId);
				
				return ps.executeUpdate() > 0;
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return false;
		}
	 
	 @Override
	 public CommentsPost getMostVotedComment(int postId) {
		
		 String sql="SELECT * from commentsPost where postId=? order by votes desc limit 1;";

		 CommentsPost mostVotedComment= new CommentsPost();


		 //=new Comments_post();
		 try {
				PreparedStatement ps= DbConnect.getMySQLConn().prepareStatement(sql);
				ps.setInt(1,postId);
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					CommentsPost commentPost = new CommentsPost(rs.getInt(1),
							rs.getInt(2),
							rs.getString(3),
							rs.getInt(4),
							rs.getInt(5),
							rs.getTimestamp(6),
							rs.getInt(7)); 
					mostVotedComment=commentPost;
					 break;
				}
			}catch(SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 return mostVotedComment;
		 
		 
	 }
	 
	 public void checkCommentId(int commentId) throws InvalidId {
			// TODO Auto-generated method stub

			String sqlForException = "SELECT * FROM commentsPost WHERE commentId = ?";
			try {
				PreparedStatement psException = DbConnect.getMySQLConn().prepareStatement(sqlForException);
				psException.setInt(1, commentId);
				ResultSet rs = psException.executeQuery();
				if (!rs.next()) {
					throw new InvalidId("Comment");
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	


		
		
	
}
