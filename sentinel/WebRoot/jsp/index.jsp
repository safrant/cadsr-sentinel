<!--
    Copyright 2007, ScenPro, Inc
    
    $Header: /share/content/gforge/sentinel/sentinel/WebRoot/jsp/index.jsp,v 1.1 2007-07-19 15:26:46 hebell Exp $
    $Name: not supported by cvs2svn $
-->
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ page contentType="text/html" %>

<jsp:forward page="/do/logon" />

