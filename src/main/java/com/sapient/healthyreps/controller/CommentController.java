package com.sapient.healthyreps.controller;

import java.util.List;

import com.sapient.healthyreps.dao.CommentDAO;
import com.sapient.healthyreps.dao.PermissionDAO;
import com.sapient.healthyreps.entity.Comment;
import com.sapient.healthyreps.exception.InvalidId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentController {

    @Autowired
	CommentDAO comDAO;
    @Autowired
	PermissionDAO permissionDAO;

	@PostMapping("answer/{aid}/comment")
	public boolean insertComment(@RequestBody Comment comment) {
		return comDAO.insertComment(comment);
	}

	@GetMapping("answer/{aid}/comment{cid}")
	public String getCommentbyID(@PathVariable int cid) {
		try {
			permissionDAO.isIDPresent(cid,"category");
		} catch (InvalidId e1) {
			e1.printStackTrace();
			return null;
		} 
		return comDAO.getCommentByCommentID(cid).toString();
	}

	@GetMapping("answer/{aid}/comment")
	public List<Comment> getAllComments(@PathVariable int aid) {
		return comDAO.getAllComments(aid);
	}

	@DeleteMapping("comment/{cid}")
	public String deleteComment(@PathVariable int cid) {
		try {
			permissionDAO.isIDPresent(cid,"category");
		} catch (InvalidId e1) {
			e1.printStackTrace();
			return null;
		} 

		return comDAO.deleteComment(cid) ? "Deleted" : "Not Deleted";
	}

	@PutMapping("answer/{aid}/comment/{cid}")
	public String updateComment(@RequestBody Comment comment) {
		try {
			permissionDAO.isIDPresent(comment.getCommentID(),"category");
		} catch (InvalidId e1) {
			e1.printStackTrace();
			return null;
		} 
		return comDAO.updateComment(comment) ? "Updated" : "Not Updated";
	}
}
