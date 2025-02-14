package com.sapient.healthyreps.interfaces;

import java.util.List;

import com.sapient.healthyreps.entity.Media;

public interface IMediaDAO {

		public boolean createMedia(int postId, String link);
		
		public String getMediaByPostId(int postId);
		
		public boolean updateMediaByPostId(int postId, String link);
		
		public boolean deleteMediaByPostId(int postId);
	
}
