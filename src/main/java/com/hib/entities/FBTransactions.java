package com.hib.entities;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.log4j.Logger;


@Entity
@Table(name="student")
public class FBTransactions {
	
	
	int fbReportId;
	int reportId;
	int impressions;
	int fans;
	int newFans;
	int engagement;
	int clicks;
	int shares;
	int likes;
	int comments;
	
	public int getReportId() {
		return reportId;
	}
	public void setReportId(int reportId) {
		this.reportId = reportId;
	}
	
	public int getImpressions() {
		return impressions;
	}
	public void setImpressions(int impressions) {
		this.impressions = impressions;
	}
	public int getFans() {
		return fans;
	}
	public void setFans(int fans) {
		this.fans = fans;
	}
	public int getNewFans() {
		return newFans;
	}
	public void setNewFans(int newFans) {
		this.newFans = newFans;
	}
	public int getEngagement() {
		return engagement;
	}
	public void setEngagement(int engagement) {
		this.engagement = engagement;
	}
	public int getClicks() {
		return clicks;
	}
	public void setClicks(int clicks) {
		this.clicks = clicks;
	}
	public int getShares() {
		return shares;
	}
	public void setShares(int shares) {
		this.shares = shares;
	}
	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}
	public int getComments() {
		return comments;
	}
	public void setComments(int comments) {
		this.comments = comments;
	}
	public int getFbReportId() {
		return fbReportId;
	}
	public void setFbReportId(int fbReportId) {
		this.fbReportId = fbReportId;
	}
	
	public void insertData(HashMap<String , Integer> dataMap){
		setImpressions(dataMap.get("IMPRESSIONS"));
		setFans(dataMap.get("FANS"));
		setNewFans(dataMap.get("NEW FANS"));
		setEngagement(dataMap.get("ENGAGEMENT"));
		setClicks(dataMap.get("CLICKS"));
		setShares(dataMap.get("SHARES"));
		setLikes(dataMap.get("LIKES"));
		setComments(dataMap.get("COMMENTS"));
		inserData();
	}
	
	public void inserData(){
		
		if(isRecordExists(getReportId())){
		updateData();
		return;
		}
		 try {
			 preparedStatement = connect
			          .prepareStatement("INSERT INTO fb_transactions"
			          		+ " (ReportMappingID,Impressions,Fans,New_Fans,Engagement,Clicks,Shares,Likes,Comments)"
			          		+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);");
			 // Parameters start with 1
			 preparedStatement.setInt(1,getReportId());
		     preparedStatement.setInt(2,getImpressions());		   
		     preparedStatement.setInt(3,getFans());		
		     preparedStatement.setInt(4,getNewFans());		
		     preparedStatement.setInt(5,getEngagement());		
		     preparedStatement.setInt(6,getClicks());
		     preparedStatement.setInt(7,getShares());		 
		     preparedStatement.setInt(8,getLikes());		  
		     preparedStatement.setInt(9,getComments());		   
		     preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Failed To Create Statement in FBTransaction Class");
			e.printStackTrace();
		}
	}
	private boolean isRecordExists(int reporMappingId) {
		try{
			 preparedStatement = connect
			          .prepareStatement("SELECT FBReport_ID FROM fb_transactions WHERE ReportMappingID= ? ");
			 // Parameters start with 1
			 preparedStatement.setInt(1,reporMappingId);
		    resultSet = preparedStatement.executeQuery();
		    
		    if(resultSet.next()){
				  setFbReportId(resultSet.getInt(1));	
				return true;
			}
			}catch(Exception e){
				log.fatal(e);
				e.printStackTrace();
			}
		return false;
	}
	
	
	public void updateData(){
		try{
		String query = "UPDATE fb_transactions SET Impressions = ? ,Fans = ?,New_Fans = ?,Engagement= ?,Clicks=?,Shares= ?,Likes= ?,Comments=? WHERE FBReport_ID = ?";
		preparedStatement = connect.prepareStatement(query);
		 preparedStatement.setInt(1,getImpressions());		   
	     preparedStatement.setInt(2,getFans());		
	     preparedStatement.setInt(3,getNewFans());		
	     preparedStatement.setInt(4,getEngagement());		
	     preparedStatement.setInt(5,getClicks());
	     preparedStatement.setInt(6,getShares());		 
	     preparedStatement.setInt(7,getLikes());		  
	     preparedStatement.setInt(8,getComments());
	     preparedStatement.setInt(9,getFbReportId());
	    preparedStatement.executeUpdate();
		}catch(Exception e){
			log.fatal(e);
			e.printStackTrace();
		}
	}
}
