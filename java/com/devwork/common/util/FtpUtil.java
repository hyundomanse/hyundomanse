package com.devwork.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Vector;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

/**
 * com.devwork.common.util 
 *    |_ FtpUtil.java
 * 
 * @comment: 
 * @date   : 2022. 02. 12. 오전 12:00:00
 * @version: 1.0 
 * @author : j2dev 
 */
public class FtpUtil {
 
	private String server				= "";
	private int port 					= 22;
	private String password 			= "";
	private String username 			= "";
	
	@SuppressWarnings("unused")
	private String folder 				= "";
	
	@SuppressWarnings("unused")
	private String destinationFolder	= "";
	
	JSch jsch 							= null;
	Session session 					= null;
	Channel channel 					= null;
    ChannelSftp channelSftp 			= null;
 
    public FtpUtil(){
    }
 
    public void setServer(String server){
    	this.server = server;
    }
 
    public void setPort(int port){
    	this.port = port;
	}
	
    public void setUser(String username , String password){
    	this.username = username;
    	this.password = password;
    }
	 
	public void setFolder(String folder){
		this.folder = folder;
	}
	
	public void setDestinationFolder(String destinationFolder){
		this.destinationFolder = destinationFolder;
	}
 
	public boolean login(){
		jsch = new JSch();
		
		try{
			session = jsch.getSession(username, server, port);
			session.setPassword(password);
			java.util.Properties config = new java.util.Properties();
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);
			session.connect();
			channel = session.openChannel("sftp");
			channel.connect();
			channelSftp = (ChannelSftp)channel;
			return true;
		}catch(JSchException e) {
			System.out.println("SFTP LOGIN ERROR = "+e.getMessage());
            return false;
        }
	}
 
	public boolean logout(){
		try{
			channel.disconnect();
			return true;
		}catch(Exception e) {
			System.out.println("SFTP LOGOUT ERROR = "+e.getMessage());
	        	return false;
		}
	}
 
	@SuppressWarnings("unchecked")
	public boolean fileCheck(String serverDir, String fileName){
		boolean result = false;
  
		try{
			Vector<ChannelSftp.LsEntry> list = channelSftp.ls(serverDir);
			for(ChannelSftp.LsEntry entry : list) {
				if(entry.getFilename().equals(fileName)){
					result = true;
				}
			}
		}catch(SftpException e){
			System.out.println("Get file list ERROR = "+e.getMessage());
		}
		return result;
	}
 
	@SuppressWarnings("unchecked")
	public String getList(String serverDir){
		StringBuffer sb = new StringBuffer();
		try{
			Vector<ChannelSftp.LsEntry> list = channelSftp.ls(serverDir);
			for(ChannelSftp.LsEntry entry : list) {
				if(!entry.getFilename().equals(".") && !entry.getFilename().equals("..") && entry.getFilename().indexOf(".") != 0 ){
					sb.append("FILE NAME = "+entry.getFilename()+"\t\n");
				}
			}
		}catch(SftpException e){
			System.out.println("Get file list ERROR = "+e.getMessage());
		}
		return sb.toString();
	}
 
	public boolean changeDirectory(String serverDir){
		boolean result = false;
		try{
			channelSftp.cd(serverDir);
			result = true;
		}catch(SftpException e){
			System.out.println("Change Directory ERROR = "+e.getMessage());
		}
		return result;
	}
 
	public boolean downloadFile (String serverFile, String localFile){
		boolean result = false;
		try{
			FileOutputStream out = new FileOutputStream(localFile);
			channelSftp.get(serverFile, out);
			out.close();
		}catch(IOException e){
			System.out.println("SFTP File Download IO ERROR = "+e.getMessage());
		}catch(SftpException e){
			System.out.println("SFTP File Download ERROR = "+e.getMessage());
		}finally{
			result = true;
		}
		return result;
	}
 
	public boolean uploadFile(String serverDir, File file){
		boolean result = false;
		FileInputStream in = null;
		try{
			in = new FileInputStream(file);
			channelSftp.cd(serverDir);
			channelSftp.put(in,file.getName());
		}catch(SftpException e){
			System.out.println("SFTP File Upload ERROR = "+e.getMessage());
		}catch(FileNotFoundException e){
			System.out.println("SFTP File Upload(file) ERROR = "+e.getMessage());
		}finally{
			try{
				in.close();
				return true;
			}catch(IOException e){
				System.out.println("SFTP File Upload IO ERROR = "+e.getMessage());
			}
		}
		return result;
	}
 
} 
