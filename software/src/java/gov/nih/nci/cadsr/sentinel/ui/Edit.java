/*L
 * Copyright ScenPro Inc, SAIC-F
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/cadsr-sentinal/LICENSE.txt for details.
 */

// Copyright (c) 2004 ScenPro, Inc.

// $Header: /share/content/gforge/sentinel/sentinel/src/gov/nih/nci/cadsr/sentinel/ui/Edit.java,v 1.3 2007-07-19 15:26:45 hebell Exp $
// $Name: not supported by cvs2svn $

package gov.nih.nci.cadsr.sentinel.ui;

import gov.nih.nci.cadsr.sentinel.database.DBAlert;
import gov.nih.nci.cadsr.sentinel.database.DBAlertUtil;
import gov.nih.nci.cadsr.sentinel.tool.AlertRec;
import gov.nih.nci.cadsr.sentinel.tool.Constants;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Process the edit.jsp page.
 * 
 * @author Larry Hebel
 */

public class Edit extends Action
{
    /**
     * Constructor.
     */
    public Edit()
    {
    }

    /**
     * Action process to Edit an Alert Definition.
     * 
     * @param mapping_
     *        The action map from the struts-config.xml.
     * @param form_
     *        The form bean for the edit.jsp page.
     * @param request_
     *        The servlet request object.
     * @param response_
     *        The servlet response object.
     * @return The action to continue processing.
     */
    public ActionForward execute(ActionMapping mapping_, ActionForm form_,
        HttpServletRequest request_, HttpServletResponse response_)
    {
        // Get data.
        EditForm form = (EditForm) form_;
        AlertBean ub = (AlertBean) request_.getSession().getAttribute(
            AlertBean._SESSIONNAME);
        ub.setRunPrev(Constants._ACTEDIT);

        // If we are going back this will effectively cancel any edit operation.
        if (form.getNextScreen().equals(Constants._ACTBACK))
        {
            form.setNextScreen(ub.getEditPrev());
        }

        // The user requests a Run.
        else if (form.getNextScreen().equals(Constants._ACTRUN))
        {
            // Get the data from the form.
            AlertRec rec = readForm(ub, form);
            ub.setWorking(rec);
        }

        // We must save the edits made.
        else if (form.getNextScreen().equals(Constants._ACTSAVE))
        {
            // We will return to the edit screen to display the message box.
            form.setNextScreen(Constants._ACTEDIT);

            // Get the data from the form.
            AlertRec rec = readForm(ub, form);

            // Connect to the database.
            DBAlert db = DBAlertUtil.factory();
            if (db.open(request_, ub.getUser()) == 0)
            {
                // If we started with a database record this is an update
                // operation.
                if (rec.getAlertRecNum() != null)
                {
                    // This should always work but you never know.
                    if (db.updateAlert(rec) == 0)
                    {
                        request_.setAttribute(Constants._ACTSAVE, "Y");
                        form.setNextScreen(Constants._ACTLIST);
                    }
                    else
                        request_.setAttribute(Constants._ACTSAVE, db
                            .getErrorMsg(true));
                }

                // This is a new alert definition.
                else
                {
                    // Of course it should work.
                    if (db.insertAlert(rec) == 0)
                    {
                        request_.setAttribute(Constants._ACTSAVE, "Y");
                        form.setNextScreen(Constants._ACTLIST);
                    }
                    else
                        request_.setAttribute(Constants._ACTSAVE, db
                            .getErrorMsg(true));
                }
            }
            else
            {
                // We couldn't connect.
                request_.setAttribute(Constants._ACTSAVE, db.getErrorMsg(true));
            }

            // Always close the connection.
            db.close();
            ub.setWorking(rec);
        }

        // On to the next page.
        return mapping_.findForward(form.getNextScreen());
    }

    /**
     * Move the data from the form into the standard AlertRec object.
     * 
     * @param ub_
     *        The session bean.
     * @param form_
     *        The edit form from the request.
     * @return An AlertRec object.
     */
    private AlertRec readForm(AlertBean ub_, EditForm form_)
    {
        AlertRec rec = new AlertRec(ub_.getWorking());

        rec.setName(form_.getPropName());
        rec.setSummary(form_.getPropDesc(), true);
        rec.setFreq(form_.getFreqUnit(), form_.getFreqWeekly(), form_
            .getFreqMonthly());
        rec.setActive(form_.getPropStatus(), form_.getPropBeginDate(), form_
            .getPropEndDate());
        rec.setInactiveReason(form_.getPropStatusReason());
        rec.setInfoVerNum(form_.getInfoVerNum());
        rec.setActVerNum(form_.getActVerNum());
        rec.setIntro(form_.getPropIntro(), true);
        rec.setIncPropSect(form_.getRepIncProp());
        rec.setReportStyle(form_.getRepStyle());
        rec.setReportEmpty(form_.getFreqEmpty());
        rec.setReportAck(form_.getFreqAck());
        rec.setVDTE(form_.getInfoVDTE());
        rec.setVDTN(form_.getInfoVDTN());
        rec.setIUse(form_.getInfoContextUse());
        rec.setAUse(form_.getActContextUse());
        rec.setTerm(form_.getInfoSearchTerm());
        rec.setIVersion(form_.getInfoVersion());
        rec.setAVersion(form_.getActVersion());
        rec.setRelated(form_.getActDependantChg());
        rec.setAdminChg(form_.getActAdminChg());
        rec.setAdminCopy(form_.getActAdminCopy());
        rec.setAdminNew(form_.getActAdminNew());
        rec.setAdminDel(form_.getActAdminDel());
        rec.setAVDT(form_.getActVDT());
        rec.setRecipients(form_.getPropRecipients());
        rec.setAttrs(form_.getRepAttributes());
        rec.setSearchIn(form_.getInfoSearchIn());
        rec.setAWorkflow(form_.getActWorkflowStatus());
        rec.setCWorkflow(form_.getInfoWorkflow());
        rec.setARegis(form_.getActRegStatus());
        rec.setCRegStatus(form_.getInfoRegStatus());
        rec.setCreators(form_.getInfoCreator());
        rec.setModifiers(form_.getInfoModifier());
        rec.setSearchAC(form_.getInfoSearchFor());
        rec.setSchemes(form_.getInfoSchemes());
        rec.setSchemeItems(form_.getInfoSchemeItems());
        rec.setDomains(form_.getInfoConceptDomain());
        rec.setContexts(form_.getInfoContext());
        rec.setProtocols(form_.getInfoProtos());
        rec.setForms(form_.getInfoForms());
        rec.setDateFilter((form_.getInfoDateFilter()));
        rec.setACTypes(form_.getInfoACTypes());
        rec.setIAssocLvl(form_.getInfoAssocLvl());

        return rec;
    }
}