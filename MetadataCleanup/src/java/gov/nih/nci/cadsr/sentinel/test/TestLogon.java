/*L
 * Copyright ScenPro Inc, SAIC-F
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/cadsr-sentinal/LICENSE.txt for details.
 */

// Copyright (c) 2004 ScenPro, Inc

// $Header: /share/content/gforge/sentinel/sentinel/src/gov/nih/nci/cadsr/sentinel/test/TestLogon.java,v 1.13 2007-07-19 15:26:45 hebell Exp $
// $Name: not supported by cvs2svn $

package gov.nih.nci.cadsr.sentinel.test;

import gov.nih.nci.cadsr.sentinel.tool.Constants;
import gov.nih.nci.cadsr.sentinel.ui.AlertBean;
import gov.nih.nci.cadsr.sentinel.ui.LogonForm;

/**
 * Test the Alert Login logic.
 * 
 * @author James McAndrew
 */
public class TestLogon extends DSRAlertTestCase
{
    /**
     * The main entry to run the test case.
     * 
     * @param args Command line arguments - none at this time.
     */
  public static void main(String[] args)
  {
    junit.textui.TestRunner.run(TestLogon.class);
  }

  /**
   * Constructor
   * 
   * @param testName The name of the class to test.
   */
  public TestLogon(String testName)
  {
    super(testName);
  }
  
  /**
   * The setUp method overrides the DSRAlertTestCase setUp to setup the
   * request path info and action form.
   * 
   * @throws Exception Any exceptions encountered during setup
   */
  public void setUp() throws Exception
  {
    super.setUp();
    
    setUpPool();

    setRequestPathInfo(getPath(Constants._ACTLOGON));
    setActionForm(new LogonForm());
  }

  /**
   * The testSuccessfulLogin method is the test for a valid login.
   */
  public void testSuccessfulLogin()
  {
    // Setup a valid logon request
    addRequestParameter("userid", _validUserid);
    addRequestParameter("pswd", _validPswd);

    // Then perform the action
    actionPerform();

    // Verify there were no errors and we got to the launcher page
    verifyNoActionErrors();
    verifyForward(Constants._ACTLIST);

    // Verify there is a user bean in the session
    assertNotNull(getSession().getAttribute(AlertBean._SESSIONNAME));
  }

  /**
   * The testInvalidLogin method is the test for an invalid login.
   */
  public void testInvalidLogin()
  {
    // Setup an invalid logon request
    addRequestParameter("userid", _invalidUserid);
    addRequestParameter("pswd", _invalidPswd);

    // Then perform the action
    actionPerform();

    // Verify there was an invalid password error and we got to the logon page
    verifyActionErrors(new String[] { "DB.1017" });
  }

  /**
   * The testBlankLogin method is the test for a blank login.
   */
  public void testBlankLogin()
  {
    // Setup an invalid logon request
    addRequestParameter("userid", "");
    addRequestParameter("pswd", "");

    // Then perform the action
    actionPerform();

    // Verify there was an blank password error and we got to the logon page
    verifyActionErrors(new String[] { "error.logon.blankuser" });
  }
}