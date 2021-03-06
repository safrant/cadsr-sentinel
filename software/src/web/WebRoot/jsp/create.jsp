<%--L
  Copyright ScenPro Inc, SAIC-F

  Distributed under the OSI-approved BSD 3-Clause License.
  See http://ncip.github.com/cadsr-sentinal/LICENSE.txt for details.
L--%>

<!-- Copyright ScenPro, Inc. 2005
     $Header: /share/content/gforge/sentinel/sentinel/WebRoot/jsp/create.jsp,v 1.4 2009-04-08 17:56:18 hebell Exp $
     $Name: not supported by cvs2svn $
-->
<%@ page contentType="text/html;charset=WINDOWS-1252"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/dsralert" prefix="dtags" %>
<%@ page import="java.util.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <title><bean:message key="create.title" /></title>
        
        <div style="position:absolute;">
 			<a href="#skip">
  			<img src="/cadsrsentinel/images/skipnav.gif" border="0" height="1" width="1" alt="Skip Navigation" title="Skip Navigation" />
	 		</a>
		</div>
        
        <html:base />
        <meta http-equiv="Content-Language" content="en-us">
        <META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=WINDOWS-1252">
        <meta HTTP-EQUIV="Pragma" CONTENT="no-cache">
        <meta HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
        <LINK href="/cadsrsentinel/css/sentinel.css" rel="stylesheet" type="text/css">
    </head>

<body onload="loaded();">

    <script language="javascript">
        <dtags:create section="script" />
    </script>
    <script language="javascript" src="/cadsrsentinel/js/create.js"></script>

	<a name="skip" id="skip"></a>
	
    <html:form method="post" action="/create" focus="propName">
    <dtags:create section="field" />
    <html:hidden property="nextScreen" />

    <table class="secttable"><colgroup></colgroup><tbody class="secttbody" />
    <tr><td align="center">

        <dtags:head key="create.title" />
        <table class="table3">
        <colgroup><col style="text-align: left" /><col style="text-align: right" /></colgroup><tbody class="secttbody" /><tr>
            <td>
                <html:submit styleClass="but1" property="save1" onclick="cmdSave();"><bean:message key="all.save" /></html:submit>
                <html:button styleClass="but1" property="edit1" onclick="cmdEdit();"><bean:message key="all.edit" /></html:button>
                <html:button styleClass="but2" property="back1" onclick="cmdCancel();"><bean:message key="all.back" /></html:button>
            </td><td>
                <html:button styleClass="but1" property="butList1" onclick="cmdLogout();"><bean:message key="all.logout" /></html:button>
                <html:button styleClass="but2" property="help1" onclick="cmdHelp();"><bean:message key="all.help" /></html:button>
            </td>
        </tr></table>

        <p class="std12" align="left"><bean:message key="create.line1" /></p>
        <p class="bstd" style="text-align: left; margin-left: 0.5in"><label for="AlertName"><bean:message key="create.line2" /></label><br><html:text styleId="AlertName" property="propName" styleClass="std" size="70" maxlength="30" /></p>
        <p class="std12" align="left"><br><label for="AlertSetting"><bean:message key="create.line3" /></label></p>
        <p class="std05"><html:radio styleId="AlertSetting" property="initial" value="1" onclick="setBlank(0, this.value)" />&nbsp;<bean:message key="create.line4" /></p>
        <p class="std05"><html:radio styleId="AlertSetting" property="initial" value="6" onclick="setBlank(1, this.value)" />&nbsp;<bean:message key="create.line9" /></p>
        <p class="std05"><br><html:radio styleId="AlertSetting" property="initial" value="0" onclick="setBlank(2, this.value)" />&nbsp;<bean:message key="create.line10" /></p>

        <p class="bstd" style="text-align: left; margin-left: 0.5in; color: #888888"
            ><label for="textareaSummary">Summary:</label><br><html:textarea styleId="textareaSummary" styleClass="std" property="propDesc" cols="90" rows="5" style="color: #888888" readonly="true"></html:textarea></p>

        <table class="table3">
        <colgroup><col style="text-align: left" /><col style="text-align: right" /></colgroup><tbody class="secttbody" /><tr>
            <td>
                <html:submit styleClass="but1" property="save2" onclick="cmdSave();"><bean:message key="all.save" /></html:submit>
                <html:button styleClass="but1" property="edit2" onclick="cmdEdit();"><bean:message key="all.edit" /></html:button>
                <html:button styleClass="but2" property="back2" onclick="cmdCancel();"><bean:message key="all.back" /></html:button>
            </td><td>
                <html:button styleClass="but1" property="butList2" onclick="cmdLogout();"><bean:message key="all.logout" /></html:button>
                <html:button styleClass="but2" property="help2" onclick="cmdHelp();"><bean:message key="all.help" /></html:button>
            </td>
        </tr></table>
        <dtags:foot />
    </td></tr></table>
    </html:form>
</body>
    <head>
        <meta HTTP-EQUIV="Pragma" CONTENT="no-cache">
        <meta HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
    </head>
</html>
