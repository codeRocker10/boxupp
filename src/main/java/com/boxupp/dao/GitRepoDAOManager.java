package com.boxupp.dao;

import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jackson.JsonNode;

import com.boxupp.db.DAOProvider;
import com.boxupp.db.beans.GitRepoBean;
import com.boxupp.db.beans.GitRepoMapping;
import com.boxupp.responseBeans.StatusBean;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.j256.ormlite.dao.Dao;

public class GitRepoDAOManager implements DAOImplInterface {
	private static Logger logger = LogManager.getLogger(GitRepoDAOManager.class.getName());
	protected static Dao<GitRepoBean, Integer> gitRepoDao = null;
	protected static Dao<GitRepoMapping, Integer> gitRepoMappingDao = null;
	private static GitRepoDAOManager gitRepoManager = null;
	
	public static GitRepoDAOManager getInstance(){
		if(gitRepoManager == null){
			gitRepoManager = new GitRepoDAOManager();
		}
		return gitRepoManager;
		
	}
	
	private  GitRepoDAOManager() {
		gitRepoDao = (Dao<GitRepoBean, Integer>) DAOProvider.getInstance().fetchDao(GitRepoBean.class);
		gitRepoMappingDao = (Dao<GitRepoMapping, Integer>) DAOProvider.getInstance().fetchDao(GitRepoMapping.class);
		
	}

	@Override
	public StatusBean create(JsonNode newData) {
		GitRepoBean gitRepoBean  = null;
		Gson gitRepoData = new GsonBuilder().setDateFormat("yyyy'-'MM'-'dd HH':'mm':'ss").create();
		gitRepoBean = gitRepoData.fromJson(newData.toString(), GitRepoBean.class);
		StatusBean statusBean = new StatusBean();
		try {
			gitRepoDao.create(gitRepoBean);
		} catch (SQLException e) {
			logger.error("Error saving a git repo  : " + e.getMessage());
			statusBean.setStatusCode(1);
			statusBean.setStatusMessage("Error saving a git repo : "+e.getMessage());
			e.printStackTrace();
		}
		statusBean.setStatusCode(0);
		statusBean.setStatusMessage("git repo saved successfully");
		return statusBean;
	}

	

	@Override
	public StatusBean update(JsonNode updatedData) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StatusBean delete(String ID) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public<T>T read(String ID) {
		// TODO Auto-generated method stub
		return null;
	}

	
	

}
