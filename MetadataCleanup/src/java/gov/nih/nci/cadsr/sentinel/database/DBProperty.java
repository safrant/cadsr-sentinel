/*L
 * Copyright ScenPro Inc, SAIC-F
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/cadsr-sentinal/LICENSE.txt for details.
 */

// Copyright (c) 2006 ScenPro, Inc.

// $Header: /share/content/gforge/sentinel/sentinel/src/gov/nih/nci/cadsr/sentinel/database/DBProperty.java,v 1.2 2007-07-19 15:26:45 hebell Exp $
// $Name: not supported by cvs2svn $

package gov.nih.nci.cadsr.sentinel.database;

/**
 * This class maps the tool options properties into key value pairs.
 * 
 * @author lhebel
 *
 */
public class DBProperty
{
    /**
     * Constructor
     * 
     * @param key_ the property key
     * @param value_ the property value
     */
    public DBProperty(String key_, String value_)
    {
        _key = key_;
        _value = value_;
    }
    
    /**
     * The key
     */
    public String _key;
    
    /**
     * The value
     */
    public String _value;
}
