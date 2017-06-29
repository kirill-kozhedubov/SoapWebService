package com.netcracker.stcenter.project.models;

import java.math.BigInteger;
import java.util.Date;

public interface DataVisualizationProject {

    BigInteger getId();

    String getName();

    Date getCreationDate();

    BigInteger getAuthor();

    String getDescription();

    ProjectTypes getType();
    
}


