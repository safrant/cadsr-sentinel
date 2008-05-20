// Copyright (c) 2008 ScenPro, Inc.

// $Header: /share/content/gforge/sentinel/sentinel/src/gov/nih/nci/cadsr/sentinel/ui/AlertRootForm.java,v 1.1 2008-05-20 21:41:20 hebell Exp $
// $Name: not supported by cvs2svn $

package gov.nih.nci.cadsr.sentinel.ui;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 * @author lhebel
 *
 */
public class AlertRootForm extends ActionForm
{
    /** * */
    private static final long serialVersionUID = 6661046459700393220L;

    /** The unique session key hidden from the browser **/
    private String _sessionKey;

    /** **/
    public String _nextScreen;
    
    private static final Logger _logger = Logger.getLogger(AlertRootForm.class);

    /**
     * 
     */
    public AlertRootForm()
    {
        super();
    }

    /**
     * Get the next action/screen name.
     * 
     * @return The action/screen name.
     */
    public String getNextScreen()
    {
        return _nextScreen;
    }

    /**
     * Set the next action/screen name to transfer control to.
     * 
     * @param val_
     *        The action/screen name.
     */
    public void setNextScreen(String val_)
    {
        _nextScreen = val_;
    }

    /**
     * Set the session key
     * 
     * @param val_
     */
    public void setSessionKey(String val_)
    {
        _sessionKey = val_;
    }
    
    /**
     * Get the session key
     * 
     * @return session key
     */
    public String getSessionKey()
    {
        return _sessionKey;
    }
    
    /**
     * Validate the form.
     * 
     * @param mapping_ The action map from struts-config.xml.
     * @param request_ The servlet request object.
     * @return ActionErrors when an error is found, otherwise null.
     */
    public ActionErrors validate(ActionMapping mapping_,
        HttpServletRequest request_)
    {
        ActionErrors errors = new ActionErrors();

        HttpSession session = request_.getSession();
        AlertBean ub = (AlertBean) session.getAttribute(AlertBean._SESSIONNAME);
        if (ub == null)
        {
            _logger.info("No session bean exists. [" + this.getClass().getName() + "]");
            errors.add("bean", new ActionMessage("error.nobean"));
            return errors;
        }
        else if (!ub.getKey().equals(_sessionKey))
        {
            _logger.warn("Sessionkeys do not match. [" + this.getClass().getName() + "] [Page " + _sessionKey + "] [Bean " + ub.getKey() + "]");
            errors.add("bean", new ActionMessage("error.nobean"));
            return errors;
        }

        return validate(mapping_, request_, ub, errors);
    }
    
    /**
     * Validate the form.
     * 
     * @param mapping_ The action map from struts-config.xml.
     * @param request_ The servlet request object.
     * @param ub_ the user AlertBean
     * @param errors_ the current errors list
     * @return ActionErrors when an error is found, otherwise null.
     */
    public ActionErrors validate(ActionMapping mapping_,
        HttpServletRequest request_, AlertBean ub_, ActionErrors errors_)
    {
        return errors_;
    }
}
