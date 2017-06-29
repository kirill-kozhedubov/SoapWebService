package com.netcracker.stcenter.project.models;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

public class DataVisualizationProjectImpl implements DataVisualizationProject {
    private BigInteger id;
    private String name;
    private Date creationDate;
    private BigInteger author;
    private String description;
    private ProjectTypes type;
    private String authorFullName;
    private List<Graphic> graphics;

    private DataVisualizationProjectImpl(Builder builder) {
        this.setId(builder.id);
        this.setName(builder.name);
        this.setCreationDate(builder.creationDate);
        this.setAuthor(builder.author);
        this.setAuthorFullName(builder.authorFullName);
        this.setDescription(builder.description);
        this.setGraphics(builder.graphics);
        this.setType(ProjectTypes.DATA_VISUALIZATION);
    }

    public List<Graphic> getGraphics() {
        return graphics;
    }

    public void setGraphics(List<Graphic> graphics) {
        this.graphics = graphics;
    }

    public String getAuthorFullName() {
        return this.authorFullName;
    }

    public void setAuthorFullName(String authorFullName) {
        this.authorFullName = authorFullName;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    @Override
    public BigInteger getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public Date getCreationDate() {
        return creationDate;
    }

    public void setAuthor(BigInteger author) {
        this.author = author;
    }

    @Override
    public BigInteger getAuthor() {
        return author;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setType(ProjectTypes type) {
        this.type = type;
    }

    @Override
    public ProjectTypes getType() {
        return type;
    }


    public static class Builder {
        private BigInteger id;
        private String name;
        private Date creationDate;
        private BigInteger author;
        private String authorFullName;
        private String description;
        private List<Graphic> graphics;

        public Builder(String name, Date creationDate, BigInteger author, String authorFullName) {
            this.name = name;
            this.creationDate = creationDate;
            this.author = author;
            this.authorFullName = authorFullName;
        }

        public Builder buildId(BigInteger id) {
            this.id = id;
            return this;
        }

        public Builder buildDescription(String val) {
            description = val;
            return this;
        }

        public Builder buildGraphics(List<Graphic> val) {
            graphics = val;
            return this;
        }

        public DataVisualizationProject buildProject() {
            return new DataVisualizationProjectImpl(this);
        }
    }
}







