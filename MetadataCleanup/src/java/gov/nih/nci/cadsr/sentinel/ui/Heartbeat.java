/*L
 * Copyright ScenPro Inc, SAIC-F
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/cadsr-sentinal/LICENSE.txt for details.
 */

// Copyright (c) 2007 ScenPro, Inc.

// $Header: /share/content/gforge/sentinel/sentinel/src/gov/nih/nci/cadsr/sentinel/ui/Heartbeat.java,v 1.2 2007-07-19 15:26:45 hebell Exp $
// $Name: not supported by cvs2svn $

package gov.nih.nci.cadsr.sentinel.ui;

import java.io.PrintWriter;
import java.net.HttpURLConnection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author lhebel
 *
 */
public class Heartbeat extends Action
{
    private static final Logger _logger = Logger.getLogger(Heartbeat.class);

    /**
     * Constructor
     */
    public Heartbeat()
    {
        super();
    }

    @Override
    public ActionForward execute(ActionMapping mapping_, ActionForm form_,
        HttpServletRequest request_, HttpServletResponse response_)
    {

        PrintWriter out = null;
        try
        {
            // Give a response the caller can understand.
            out = response_.getWriter();
            response_.setStatus(HttpURLConnection.HTTP_OK);
            out.println("<html><body>heartbeat</body></html>");
        }
        catch (java.io.IOException ex)
        {
            response_.setStatus(HttpURLConnection.HTTP_INTERNAL_ERROR);
            _logger.error(ex.toString());
        }
        finally
        {
            if (out != null)
                out.close();
        }
        return null;
    }
}
